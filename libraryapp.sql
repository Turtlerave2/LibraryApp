-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 02, 2024 at 11:34 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.0.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `libraryapp`
--

-- --------------------------------------------------------

--
-- Table structure for table `author`
--

CREATE TABLE `author` (
  `authorid` int(11) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `bookid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `author`
--

INSERT INTO `author` (`authorid`, `firstname`, `lastname`, `bookid`) VALUES
(1, 'Joanne ', 'Kathleen Rowling', 1),
(6, 'Roald ', 'Dahl', 2);

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `Bookid` int(11) NOT NULL,
  `Title` varchar(255) NOT NULL,
  `AuthorID` int(11) NOT NULL,
  `ISBN` int(11) NOT NULL,
  `PublicationYear` int(11) NOT NULL,
  `GenreID` int(11) NOT NULL,
  `TotalCopies` int(11) NOT NULL,
  `Description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`Bookid`, `Title`, `AuthorID`, `ISBN`, `PublicationYear`, `GenreID`, `TotalCopies`, `Description`) VALUES
(1, 'Harry Potter and the Philosopher\'s Stone\r\n', 1, 2147483647, 1997, 1, 1200000, 'An 11-year-old orphan living with his unwelcoming aunt, uncle, and cousin, who learns of his own fame as a wizard known to have survived his parents\' murder at the hands of the dark wizard Lord Voldemort as an infant when he is accepted to Hogwarts School'),
(2, 'Harry Potter and the Chamber of Secrets', 1, 123589026, 1998, 2, 770000, 'Throughout the summer holidays after his first year at Hogwarts School of Witchcraft and Wizardry, Harry Potter has been receiving sinister warnings from a house-elf called Dobby. Now, back at school to start his second year, Harry hears unintelligible wh'),
(3, 'Harry Potter and the Prisoner of Azkaban', 1, 830827838, 1999, 3, 650000, 'Harry Potter & The Prisoner of Azkaban is about Harry\'s 3rd year at Hogwarts. Along with friends Ron and Hermione, Harry investigates the case of Sirius Black, an escaped prisoner from Azkaban, the wizard prison.'),
(4, 'Harry Potter and the Goblet of Fire', 1, 387086705, 2000, 4, 40000, 'It follows Harry Potter, a wizard in his fourth year at Hogwarts School of Witchcraft and Wizardry, and the mystery surrounding the entry of Harry\'s name into the Triwizard Tournament, in which he is forced to compete. The book was published in the United'),
(5, 'Harry Potter and the Order of the Phoenix', 1, 691461178, 2003, 5, 660000, 'Dark times have come to Hogwarts. After the Dementors\' attack on his cousin Dudley, Harry Potter knows that Voldemort will stop at nothing to find him. There are many who deny the Dark Lord\'s return, but Harry is not alone: a secret order gathers at Grimm'),
(6, 'Charlie and the Chocolate Factory', 6, 927491739, 1964, 6, 2600000, 'Charlie And The Chocolate Factory is a delightful children\'s book by Roald Dahl. It tells the story of a young boy named Charlie Bucket, who wins a golden ticket and gets the opportunity to tour the mysterious and magical chocolate factory owned by the ec');

-- --------------------------------------------------------

--
-- Table structure for table `genre`
--

CREATE TABLE `genre` (
  `genreid` int(11) NOT NULL,
  `genre1` varchar(255) NOT NULL,
  `genre2` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `genre`
--

INSERT INTO `genre` (`genreid`, `genre1`, `genre2`) VALUES
(1, 'mystery', 'horror'),
(2, 'fairy tail', 'fantasy fictions');

-- --------------------------------------------------------

--
-- Table structure for table `loans`
--

CREATE TABLE `loans` (
  `LoanID` int(11) NOT NULL,
  `MemberID` int(11) DEFAULT NULL,
  `BookID` int(11) DEFAULT NULL,
  `LoanDate` date DEFAULT NULL,
  `DueDate` date DEFAULT NULL,
  `ReturnDate` date DEFAULT NULL,
  `LateFee` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `loans`
--

INSERT INTO `loans` (`LoanID`, `MemberID`, `BookID`, `LoanDate`, `DueDate`, `ReturnDate`, `LateFee`) VALUES
(1, 1, 1, '2023-11-02', '2023-11-16', NULL, '0.00'),
(2, 1, 2, '2023-11-03', '2023-11-17', NULL, '10.00'),
(3, 2, 3, '2023-11-04', '2023-11-18', NULL, NULL),
(4, 3, 4, '2023-11-05', '2023-11-19', NULL, NULL),
(5, 4, 5, '2023-11-06', '2023-11-20', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `members`
--

CREATE TABLE `members` (
`MemberID` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
`Username` varchar(255) NOT NULL,
`Password` varchar(255) NOT NULL,
`First_Name` varchar(255) DEFAULT NULL,
`Last_Name` varchar(255) DEFAULT NULL,
`Email` varchar(255) DEFAULT NULL,
`Address1` varchar(255) DEFAULT NULL,
`Address2` varchar(255) DEFAULT NULL,
`Eircode` varchar(10) DEFAULT NULL,
`Phone_Number` varchar(20) DEFAULT NULL,
`Registration_Date` date DEFAULT NULL,
`Admin` bit(1) NOT NULL DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


--
-- Dumping data for table `members`
--

INSERT INTO `members` (`MemberID`, `Username`, `Password`, `First_Name`, `Last_Name`, `Email`, `Address1`, `Address2`, `Eircode`, `Phone_Number`, `Registration_Date`, `Admin`) VALUES
(1, 'CoolGuy', 'password1', 'John', 'Doe', 'john.doe@email.com', '123 Main St', 'Apt 45', 'EIR123', '555-123-4567', '2023-11-01', b'1'),
(2, 'AwesomeFella', 'password1', 'Jane', 'Smith', 'jane.smith@email.com', '456 Elm St', NULL, 'EIR456', '555-987-6543', '2023-11-01', b'0'),
(3, 'ReaderOfBooks', 'password1', 'Bob', 'Johnson', 'bob.johnson@email.com', '789 Oak St', 'Suite 101', 'EIR789', '555-456-7890', '2023-11-02', b'0'),
(4, 'Bookaholic', 'password1', 'Alice', 'Brown', 'alice.brown@email.com', '456 Birch Ave', NULL, 'EIR111', '555-111-2222', '2023-11-02', b'0'),
(5, 'BookNerdAlert', 'password1', 'Sarah', 'Davis', 'sarah.davis@email.com', '789 Cedar Rd', 'Apt 22', 'EIR789', '555-333-4444', '2023-11-03', b'0');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `members`
--
ALTER TABLE `members`
  ADD PRIMARY KEY (`MemberID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
