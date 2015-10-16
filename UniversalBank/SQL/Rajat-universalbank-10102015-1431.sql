-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 10, 2015 at 11:30 PM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `universalbank`
--

-- --------------------------------------------------------

--
-- Table structure for table `administrator_nonce`
--

CREATE TABLE IF NOT EXISTS `administrator_nonce` (
  `Admin_ID` varchar(20) NOT NULL,
  `Access_Level` int(5) NOT NULL,
  `Transaction_Type` varchar(100) NOT NULL,
  `Transaction_ID` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE IF NOT EXISTS `customer` (
  `Customer_User_Name` varchar(100) NOT NULL,
  `Transaction_ID` int(20) NOT NULL,
  `Transaction_Type` varchar(100) NOT NULL,
  `Access_Level` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE IF NOT EXISTS `employee` (
  `Emp_ID` varchar(20) NOT NULL,
  `Transaction_ID` int(20) NOT NULL,
  `Transaction_Type` varchar(100) NOT NULL,
  `Access_Level` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `internal_userinfo`
--

CREATE TABLE IF NOT EXISTS `internal_userinfo` (
  `User_Type` varchar(20) NOT NULL,
  `User_Name` varchar(100) NOT NULL,
  `Password` varchar(100) NOT NULL,
  `Time_Of_Login` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Only internal users';

-- --------------------------------------------------------

--
-- Table structure for table `merchant`
--

CREATE TABLE IF NOT EXISTS `merchant` (
  `User_Name` varchar(100) NOT NULL,
  `Transaction_ID` int(20) NOT NULL,
  `Transaction_Type` varchar(100) NOT NULL,
  `Access_Level` int(5) NOT NULL,
  `Login_User_Name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pii`
--

CREATE TABLE IF NOT EXISTS `pii` (
  `User_ID` varchar(100) NOT NULL,
  `Email_ID` varchar(100) NOT NULL,
  `SSN` bigint(9) NOT NULL,
  `Phone_No` bigint(10) DEFAULT NULL,
  `Admin_ID` varchar(100) NOT NULL,
  `Customer_User_Name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `systemmanager`
--

CREATE TABLE IF NOT EXISTS `systemmanager` (
  `Manager_ID` varchar(100) NOT NULL,
  `Transaction_ID` int(20) NOT NULL,
  `Transaction_Type` varchar(100) NOT NULL,
  `Access_Level` int(5) NOT NULL,
  `User_Name` varchar(100) NOT NULL,
  `Password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE IF NOT EXISTS `transactions` (
`Transaction_ID` int(20) NOT NULL,
  `Transaction_Type` varchar(100) NOT NULL,
  `Transaction_Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Transaction_End_User` varchar(100) NOT NULL,
  `Amount` bigint(20) NOT NULL,
  `Authorized_Manager_ID` varchar(100) NOT NULL,
  `Merchant_User_Name` varchar(100) NOT NULL,
  `Emp_ID` varchar(100) NOT NULL,
  `Customer_User_Name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `userinfo`
--

CREATE TABLE IF NOT EXISTS `userinfo` (
  `User_ID` varchar(100) NOT NULL,
  `First_Name` varchar(100) NOT NULL,
  `Last_Name` varchar(100) NOT NULL,
  `Address` varchar(1000) DEFAULT NULL,
  `SSN` bigint(9) NOT NULL,
  `Phone_No` bigint(10) DEFAULT NULL,
  `E_Mail` varchar(100) NOT NULL,
  `User_Name` varchar(100) NOT NULL,
  `Password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='User info table for UB';

--
-- Indexes for dumped tables
--

--
-- Indexes for table `administrator_nonce`
--
ALTER TABLE `administrator_nonce`
 ADD PRIMARY KEY (`Admin_ID`), ADD UNIQUE KEY `Transaction_ID` (`Transaction_ID`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
 ADD PRIMARY KEY (`Customer_User_Name`), ADD UNIQUE KEY `Transaction_ID` (`Transaction_ID`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
 ADD PRIMARY KEY (`Emp_ID`), ADD UNIQUE KEY `Transaction_ID` (`Transaction_ID`);

--
-- Indexes for table `internal_userinfo`
--
ALTER TABLE `internal_userinfo`
 ADD PRIMARY KEY (`User_Name`);

--
-- Indexes for table `merchant`
--
ALTER TABLE `merchant`
 ADD PRIMARY KEY (`User_Name`), ADD UNIQUE KEY `Transaction_ID` (`Transaction_ID`), ADD UNIQUE KEY `Login_User_Name` (`Login_User_Name`);

--
-- Indexes for table `pii`
--
ALTER TABLE `pii`
 ADD PRIMARY KEY (`User_ID`), ADD UNIQUE KEY `SSN` (`SSN`);

--
-- Indexes for table `systemmanager`
--
ALTER TABLE `systemmanager`
 ADD PRIMARY KEY (`Manager_ID`), ADD UNIQUE KEY `Transaction_ID` (`Transaction_ID`), ADD UNIQUE KEY `User_Name` (`User_Name`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
 ADD PRIMARY KEY (`Transaction_ID`);

--
-- Indexes for table `userinfo`
--
ALTER TABLE `userinfo`
 ADD PRIMARY KEY (`User_ID`), ADD UNIQUE KEY `email` (`E_Mail`), ADD UNIQUE KEY `username` (`User_Name`), ADD UNIQUE KEY `SSN` (`SSN`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `transactions`
--
ALTER TABLE `transactions`
MODIFY `Transaction_ID` int(20) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
