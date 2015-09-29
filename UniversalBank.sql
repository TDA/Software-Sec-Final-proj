-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Sep 29, 2015 at 03:14 AM
-- Server version: 5.6.26
-- PHP Version: 5.6.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `UniversalBank`
--

-- --------------------------------------------------------

--
-- Table structure for table `Administrator_nonce`
--

CREATE TABLE IF NOT EXISTS `Administrator_nonce` (
  `Admin_ID` varchar(20) NOT NULL,
  `Access_Level` int(5) NOT NULL,
  `Transaction_Type` varchar(100) NOT NULL,
  `Transaction_ID` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Customer`
--

CREATE TABLE IF NOT EXISTS `Customer` (
  `Customer_User_Name` varchar(100) NOT NULL,
  `Transaction_ID` int(20) NOT NULL,
  `Transaction_Type` varchar(100) NOT NULL,
  `Access_Level` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Employee`
--

CREATE TABLE IF NOT EXISTS `Employee` (
  `Emp_ID` varchar(20) NOT NULL,
  `Transaction_ID` int(20) NOT NULL,
  `Transaction_Type` varchar(100) NOT NULL,
  `Access_Level` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Internal_UserInfo`
--

CREATE TABLE IF NOT EXISTS `Internal_UserInfo` (
  `User_Type` varchar(20) NOT NULL,
  `User_Name` varchar(100) NOT NULL,
  `Password` varchar(100) NOT NULL,
  `Time_Of_Login` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Only internal users';

-- --------------------------------------------------------

--
-- Table structure for table `Merchant`
--

CREATE TABLE IF NOT EXISTS `Merchant` (
  `User_Name` varchar(100) NOT NULL,
  `Transaction_ID` int(20) NOT NULL,
  `Transaction_Type` varchar(100) NOT NULL,
  `Access_Level` int(5) NOT NULL,
  `Login_User_Name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `PII`
--

CREATE TABLE IF NOT EXISTS `PII` (
  `User_ID` varchar(100) NOT NULL,
  `Email_ID` varchar(100) NOT NULL,
  `SSN` bigint(9) NOT NULL,
  `Phone_No` bigint(10) DEFAULT NULL,
  `Admin_ID` varchar(100) NOT NULL,
  `Customer_User_Name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `SystemManager`
--

CREATE TABLE IF NOT EXISTS `SystemManager` (
  `Manager_ID` varchar(100) NOT NULL,
  `Transaction_ID` int(20) NOT NULL,
  `Transaction_Type` varchar(100) NOT NULL,
  `Access_Level` int(5) NOT NULL,
  `User_Name` varchar(100) NOT NULL,
  `Password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Transactions`
--

CREATE TABLE IF NOT EXISTS `Transactions` (
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
-- Table structure for table `UserInfo`
--

CREATE TABLE IF NOT EXISTS `UserInfo` (
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
-- Indexes for table `Administrator_nonce`
--
ALTER TABLE `Administrator_nonce`
  ADD PRIMARY KEY (`Admin_ID`),
  ADD UNIQUE KEY `Transaction_ID` (`Transaction_ID`);

--
-- Indexes for table `Customer`
--
ALTER TABLE `Customer`
  ADD PRIMARY KEY (`Customer_User_Name`),
  ADD UNIQUE KEY `Transaction_ID` (`Transaction_ID`);

--
-- Indexes for table `Employee`
--
ALTER TABLE `Employee`
  ADD PRIMARY KEY (`Emp_ID`),
  ADD UNIQUE KEY `Transaction_ID` (`Transaction_ID`);

--
-- Indexes for table `Internal_UserInfo`
--
ALTER TABLE `Internal_UserInfo`
  ADD PRIMARY KEY (`User_Name`);

--
-- Indexes for table `Merchant`
--
ALTER TABLE `Merchant`
  ADD PRIMARY KEY (`User_Name`),
  ADD UNIQUE KEY `Transaction_ID` (`Transaction_ID`),
  ADD UNIQUE KEY `Login_User_Name` (`Login_User_Name`);

--
-- Indexes for table `PII`
--
ALTER TABLE `PII`
  ADD PRIMARY KEY (`User_ID`),
  ADD UNIQUE KEY `SSN` (`SSN`);

--
-- Indexes for table `SystemManager`
--
ALTER TABLE `SystemManager`
  ADD PRIMARY KEY (`Manager_ID`),
  ADD UNIQUE KEY `Transaction_ID` (`Transaction_ID`),
  ADD UNIQUE KEY `User_Name` (`User_Name`);

--
-- Indexes for table `Transactions`
--
ALTER TABLE `Transactions`
  ADD PRIMARY KEY (`Transaction_ID`);

--
-- Indexes for table `UserInfo`
--
ALTER TABLE `UserInfo`
  ADD PRIMARY KEY (`User_ID`),
  ADD UNIQUE KEY `email` (`E_Mail`),
  ADD UNIQUE KEY `username` (`User_Name`),
  ADD UNIQUE KEY `SSN` (`SSN`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Transactions`
--
ALTER TABLE `Transactions`
  MODIFY `Transaction_ID` int(20) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
