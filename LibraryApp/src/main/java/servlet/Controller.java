package servlet;

import Exceptions.DaoException;
import business.Book;
import business.User;
import daos.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "Controller", urlPatterns = {"/controller"})
public class Controller extends HttpServlet {

    private final UserDao userDao;
    private  final BookDao bookDao;

    public Controller() {
        super();
        // Instantiate UserDao for database operations
        userDao = new UserDao("libraryapp");
        bookDao = new BookDao("libraryapp");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String forwardToJsp = "index.html";
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "login":
                    forwardToJsp = loginCommand(request, response);
                    break;
                case "register":
                    forwardToJsp = registerCommand(request, response);
                    break;
                case "changePassword":
                    forwardToJsp = changePasswordCommand(request, response);
                    break;
                case "displayallbooks":
                    forwardToJsp = displaybooksCommand(request, response);
                    break;
                case "searchbooks":
                    forwardToJsp = searchbookbytitleCommand(request, response);
                    break;

                default:
                    forwardToJsp = "error.jsp";
                    String error = "No such action defined for this application. Please try again.";
                    session.setAttribute("errorMessage", error);
            }
        } else {
            forwardToJsp = "error.jsp";
            String error = "No action supplied. Please try again.";
            session.setAttribute("errorMessage", error);
        }
        response.sendRedirect(forwardToJsp);
    }

    private String loginCommand(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "index.jsp";
        HttpSession session = request.getSession(true);
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) {
            User u = userDao.findUserByUsernamePassword(username, password);
            if (u == null) {
                forwardToJsp = "error.jsp";
                String error = "Incorrect credentials supplied. Please <a href=\"login.jsp\">try again.</a>";
                session.setAttribute("errorMessage", error);
            } else {
                forwardToJsp = "loginSuccessful.jsp";
                session.setAttribute("username", username);
                session.setAttribute("user", u);
            }
        } else {
            forwardToJsp = "error.jsp";
            String error = "No username and/or password supplied. Please <a href=\"login.jsp\">try again.</a>";
            session.setAttribute("errorMessage", error);
        }
        return forwardToJsp;
    }

    private String registerCommand(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "index.jsp";
        HttpSession session = request.getSession(true);
        String uname = request.getParameter("username");
        String pword = request.getParameter("password");
        String first = request.getParameter("fName");
        String last = request.getParameter("lName");
        String email = request.getParameter("email");
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");
        String eircode = request.getParameter("eircode");
        String phoneNumber = request.getParameter("phoneNumber");
        String registrationDate = String.valueOf(LocalDate.now());

        if (uname != null && pword != null && !uname.isEmpty() && !pword.isEmpty() &&
                first != null && !first.isEmpty() && last != null && !last.isEmpty() &&
                email != null && !email.isEmpty() && address1 != null && !address1.isEmpty()) {

            int id = userDao.addUser(uname, pword, first, last, email, address1, address2, eircode, phoneNumber, registrationDate);
            if (id == -1) {
                forwardToJsp = "error.jsp";
                String error = "This user could not be added. Please <a href=\"register.jsp\">try again.</a>";
                session.setAttribute("errorMessage", error);
            } else {
                forwardToJsp = "loginSuccessful.jsp";
                session.setAttribute("username", uname);
                User u = new User(first, last, uname, pword, email, address1, address2, eircode, phoneNumber, registrationDate);
                session.setAttribute("user", u);
                String msg = "You are now Logged In :D";
                session.setAttribute("msg", msg);
            }
        } else {
            forwardToJsp = "error.jsp";
            String error = "Please <a href=\"register.jsp\">try again.</a>. Please Enter info carefully.";
            session.setAttribute("errorMessage", error);
        }
        return forwardToJsp;
    }
    private String changePasswordCommand(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "index.jsp";
        HttpSession session = request.getSession(true);
        User u = (User) session.getAttribute("user");

        if (u != null) {
            String oldPass = request.getParameter("oldPassword");
            String newPassOne = request.getParameter("newPassword");
            String newPassTwo = request.getParameter("newPasswordCopy");

            if (oldPass != null && newPassOne != null && newPassTwo != null &&
                    !oldPass.isBlank() && !newPassOne.isBlank() && !newPassTwo.isBlank()) {
                if (newPassOne.equals(newPassTwo)) {
                    int result = userDao.changePassword(u.getUsername(), oldPass, newPassOne);
                    if (result == 1) {
                        forwardToJsp = "loginSuccessful.jsp";
                        String msg = "Password has changed.";
                        session.setAttribute("msg", msg);
                    } else {
                        forwardToJsp = "error.jsp";
                        String error = "Previous Password Incorrect. Please <a href=\"changePassword.jsp\">go back</a> and try again.";
                        session.setAttribute("errorMessage", error);
                    }
                } else {
                    forwardToJsp = "error.jsp";
                    String error = "Supplied new passwords do not match. Please <a href=\"changePassword.jsp\">go back</a> and try again.";
                    session.setAttribute("errorMessage", error);
                }
            } else {
                forwardToJsp = "error.jsp";
                String error = "One or more password fields were not provided. Please <a href=\"changePassword.jsp\">go back</a> and try again.";
                session.setAttribute("errorMessage", error);
            }
        } else {
            forwardToJsp = "error.jsp";
            String error = "You are not currently logged in. Please <a href=\"login.jsp\">login</a> and try again.";
            session.setAttribute("errorMessage", error);
        }
        return forwardToJsp;
    }
    private String displaybooksCommand(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "index.jsp";
        HttpSession session = request.getSession(true);
        try {
            List<Book> bookdisplay = bookDao.findAllBooks();
            session.setAttribute("bookList", bookdisplay);

            forwardToJsp = "displaybooks.jsp";
        } catch(DaoException e) {
            e.printStackTrace();
            forwardToJsp = "error.jsp";
            String error = "Yo my bad Failed to recieve book list";
            session.setAttribute("errorMessage",error);
        }
        return forwardToJsp;

    }
    private String searchbookbytitleCommand(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "index.jsp";
        HttpSession session = request.getSession(true);
        String title = request.getParameter("Title");
        try {

            List<Book> returnbooktitle= bookDao.searchBookTitle(title);
            session.setAttribute("foundbook", returnbooktitle);
            forwardToJsp = "displaysearchtitle.jsp";

        } catch(DaoException e) {
            e.getMessage();
            forwardToJsp = "error.jsp";
            String error = "Yo my mans the thing your searching does not exist ";
            session.setAttribute("errorMessage", error);
        }
        return forwardToJsp;

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Controller Servlet";
    }
}