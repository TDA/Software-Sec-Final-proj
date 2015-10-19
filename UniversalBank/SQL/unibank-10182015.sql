-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 18, 2015 at 11:30 AM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `unibank`
--

-- --------------------------------------------------------

--
-- Table structure for table `accounts`
--

CREATE TABLE IF NOT EXISTS `accounts` (
  `AccountID` varchar(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `AccountType` varchar(10) NOT NULL DEFAULT 'Checking',
  `Balance` float NOT NULL DEFAULT '0',
  `PIN` varchar(6) NOT NULL,
  `OTP` varchar(6) NOT NULL,
  `OTPExpiry` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE IF NOT EXISTS `transactions` (
`TransactionID` int(20) NOT NULL,
  `TransactionType` varchar(100) DEFAULT NULL,
  `Amount` bigint(20) NOT NULL,
  `TransactionAccountID` varchar(20) DEFAULT NULL,
  `AuthorizedManagerID` varchar(100) DEFAULT NULL,
  `TransactionTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Approved` tinyint(1) NOT NULL DEFAULT '0',
  `ApprovalTime` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000',
  `Comments` varchar(140) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`TransactionID`, `TransactionType`, `Amount`, `TransactionAccountID`, `AuthorizedManagerID`, `TransactionTime`, `Approved`, `ApprovalTime`, `Comments`) VALUES
(1, 'Debit', 100, NULL, NULL, '2015-10-18 04:10:26', 0, '2015-10-18 04:10:26.252902', 'Withdraw from ATM'),
(2, 'Debit', 100, 'kenilabc', NULL, '2015-10-18 04:17:45', 0, '2015-10-18 04:17:45.973427', 'Withdraw from ATM'),
(3, 'Credit', 100, NULL, NULL, '2015-10-18 04:19:54', 0, '2015-10-18 04:19:54.496131', 'Deposit at branch'),
(4, 'Debit', 200, NULL, NULL, '2015-10-18 04:22:06', 0, '2015-10-18 04:22:06.414306', 'Merchant initiated transaction'),
(5, 'Credit', 200, 'kenilabc', NULL, '2015-10-18 04:22:06', 0, '2015-10-18 04:22:06.427770', 'Merchant getting credited for merchant initiated transaction'),
(6, 'Debit', 100, NULL, NULL, '2015-10-18 04:23:20', 0, '2015-10-18 04:23:20.425110', 'Merchant initiated transaction'),
(7, 'Credit', 100, 'kenilabc', NULL, '2015-10-18 04:23:20', 0, '2015-10-18 04:23:20.441109', 'Merchant getting credited for merchant initiated transaction'),
(8, 'Debit', 900, NULL, NULL, '2015-10-18 04:26:10', 0, '2015-10-18 04:26:10.639749', 'Merchant initiated transaction'),
(9, 'Credit', 900, 'kenilabc', NULL, '2015-10-18 04:26:10', 0, '2015-10-18 04:26:10.660263', 'Merchant getting credited for merchant initiated transaction'),
(10, 'Debit', 2, NULL, NULL, '2015-10-18 04:27:09', 0, '2015-10-18 04:27:09.210392', 'Merchant initiated transaction'),
(11, 'Credit', 2, 'kenilabc', NULL, '2015-10-18 04:27:09', 0, '2015-10-18 04:27:09.226391', 'Merchant getting credited for merchant initiated transaction'),
(12, 'Debit', 3, '3', NULL, '2015-10-18 04:30:12', 0, '2015-10-18 04:30:12.246440', 'Merchant initiated transaction'),
(13, 'Credit', 3, 'kenilabc', NULL, '2015-10-18 04:30:12', 0, '2015-10-18 04:30:12.258422', 'Merchant getting credited for merchant initiated transaction'),
(14, NULL, 100, '100', NULL, '2015-10-18 06:02:35', 0, '2015-10-18 06:02:35.373415', NULL),
(15, NULL, 100, '100', NULL, '2015-10-18 06:23:44', 0, '2015-10-18 06:23:44.251373', NULL),
(16, 'Inserting', 1, '1', NULL, '2015-10-18 06:32:42', 0, '2015-10-18 06:32:42.808655', NULL),
(17, 'Debit', 200, 'kenilabc', NULL, '2015-10-18 06:36:21', 0, '2015-10-18 06:36:21.240090', 'User transfer Debit'),
(18, 'Credit', 200, '100', NULL, '2015-10-18 06:36:21', 0, '2015-10-18 06:36:21.255608', 'User transfer Credit');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(45) NOT NULL,
  `password` varchar(60) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `AccountType` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `SSN` varchar(45) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `userLocked` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`, `firstname`, `lastname`, `AccountType`, `email`, `SSN`, `enabled`, `userLocked`) VALUES
('kenilabc', '$2a$10$23sLqI0HtA8xkxudo7ntxu0WAmxEcjgaTjrmvc1MOt.yNkEk7XrZm', 'Kenil', 'Bhatt', 'Individual', 'fake@fake.com', '123456789', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user_requests`
--

CREATE TABLE IF NOT EXISTS `user_requests` (
`RequestID` int(10) NOT NULL,
  `username` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `EmailID` varchar(40) NOT NULL,
  `SSN` int(9) NOT NULL,
  `Approved` varchar(3) CHARACTER SET latin1 COLLATE latin1_bin NOT NULL DEFAULT 'No',
  `Approval_Time` timestamp(6) NULL DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_requests`
--

INSERT INTO `user_requests` (`RequestID`, `username`, `password`, `EmailID`, `SSN`, `Approved`, `Approval_Time`) VALUES
(1, 'kenilabc', 'Bhatt1!', 'r@r.com', 123123123, 'No', '2015-10-18 05:47:48.926739');

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

CREATE TABLE IF NOT EXISTS `user_roles` (
`user_role_id` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_roles`
--

INSERT INTO `user_roles` (`user_role_id`, `username`, `role`) VALUES
(1, 'kenilabc', 'ROLE_MANAGER');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
 ADD PRIMARY KEY (`TransactionID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
 ADD PRIMARY KEY (`username`);

--
-- Indexes for table `user_requests`
--
ALTER TABLE `user_requests`
 ADD PRIMARY KEY (`RequestID`);

--
-- Indexes for table `user_roles`
--
ALTER TABLE `user_roles`
 ADD PRIMARY KEY (`user_role_id`), ADD UNIQUE KEY `uni_username_role` (`role`,`username`), ADD KEY `fk_username_idx` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `transactions`
--
ALTER TABLE `transactions`
MODIFY `TransactionID` int(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `user_requests`
--
ALTER TABLE `user_requests`
MODIFY `RequestID` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `user_roles`
--
ALTER TABLE `user_roles`
MODIFY `user_role_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `user_roles`
--
ALTER TABLE `user_roles`
ADD CONSTRAINT `fk_username` FOREIGN KEY (`username`) REFERENCES `users` (`username`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
