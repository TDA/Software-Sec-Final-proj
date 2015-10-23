-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 23, 2015 at 02:20 PM
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
  `PIN` varchar(6) DEFAULT NULL,
  `CreationTime` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`AccountID`, `username`, `AccountType`, `Balance`, `PIN`, `CreationTime`) VALUES
('0784-101', 'kenilabc', 'Checking', 0, NULL, '2015-10-22 05:23:09'),
('0784-102', 'kenilabc', 'Savings', 250, NULL, '2015-10-22 05:23:10'),
('1', 'kenilabc', 'Individual', 0, '1234', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `edit_info`
--

CREATE TABLE IF NOT EXISTS `edit_info` (
  `username` varchar(40) NOT NULL,
  `password` varchar(40) DEFAULT NULL,
  `EmailID` varchar(40) NOT NULL,
  `SSN` int(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `edit_info`
--

INSERT INTO `edit_info` (`username`, `password`, `EmailID`, `SSN`) VALUES
('kenilabc', 'Bhatt1!', 'r@r.com', 123123123),
('bhaddy', 'Arya@123', 'baryasom@asu.edu', 123456780),
('kenilabc', NULL, 'e@e.com', 121212125),
('kenilabc', NULL, 'e@e.com', 125125125);

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE IF NOT EXISTS `transactions` (
`TransactionID` int(20) NOT NULL,
  `TransactionType` varchar(100) NOT NULL,
  `Amount` double NOT NULL,
  `FromTransactionAccountID` varchar(20) DEFAULT NULL,
  `ToTransactionAccountID` varchar(20) DEFAULT NULL,
  `AuthorizedManagerID` varchar(100) DEFAULT NULL,
  `TransactionTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Approved` tinyint(1) NOT NULL DEFAULT '0',
  `ApprovalTime` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000',
  `Comments` varchar(140) DEFAULT NULL,
  `Authorise_bank` tinyint(1) NOT NULL DEFAULT '1',
  `Critical_transactions` tinyint(1) NOT NULL DEFAULT '0',
  `IsDeleted` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`TransactionID`, `TransactionType`, `Amount`, `FromTransactionAccountID`, `ToTransactionAccountID`, `AuthorizedManagerID`, `TransactionTime`, `Approved`, `ApprovalTime`, `Comments`, `Authorise_bank`, `Critical_transactions`, `IsDeleted`) VALUES
(1, 'Credit', 1000, NULL, '0784-101', NULL, '2015-10-23 10:08:37', 0, '2015-10-23 10:08:37.946663', 'Deposit at branch', 1, 0, 0),
(2, 'Debit', 123, '0784-102', NULL, NULL, '2015-10-23 10:09:38', 0, '2015-10-23 10:09:38.607029', 'Withdraw from ATM', 1, 0, 0),
(3, 'UserTransfer', 10, '0784-101', '10', NULL, '2015-10-23 10:40:59', 0, '2015-10-23 10:40:59.782675', 'User transfer Debit', 1, 0, 0),
(4, 'Credit', 1, NULL, '0784-101', NULL, '2015-10-23 12:01:51', 0, '2015-10-23 12:01:51.922665', 'Deposit at branch', 1, 0, 0),
(5, 'Credit', 1, NULL, '0784-101', NULL, '2015-10-23 12:02:25', 0, '2015-10-23 12:02:25.036205', 'Deposit at branch', 1, 0, 0),
(6, 'Credit', 1, NULL, '0784-101', NULL, '2015-10-23 12:07:17', 0, '2015-10-23 12:07:17.285388', 'Deposit at branch', 1, 0, 0),
(7, 'Credit', 10, NULL, '0784-101', NULL, '2015-10-23 12:16:59', 0, '2015-10-23 12:16:59.399708', 'Deposit at branch', 1, 0, 0),
(8, 'UserTransfer', 100, '0784-101', '100', NULL, '2015-10-23 12:18:24', 0, '2015-10-23 12:18:24.619554', 'User transfer Debit', 1, 0, 0);

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
  `userLocked` tinyint(1) NOT NULL,
  `SupervisorName` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`, `firstname`, `lastname`, `AccountType`, `email`, `SSN`, `enabled`, `userLocked`, `SupervisorName`) VALUES
('bhaddy', '$2a$10$23sLqI0HtA8xkxudo7ntxu0WAmxEcjgaTjrmvc1MOt.yNkEk7XrZm', 'bhaddy', 'bhaddy', 'Clerk', 'fake@fake.com', '123456780', 1, 1, 'cborde'),
('cborde', '$2a$10$QGbutUwJv4B2IpYr1.2Q7.Y0zL9gxgR8iFCa1V7Tqkp/AR7UrcyCy', 'Chandu', 'Borde', 'Manager', 'kenil.p.bhatt@gmail.com', '124785369', 1, 1, NULL),
('kenilabc', '$2a$10$23sLqI0HtA8xkxudo7ntxu0WAmxEcjgaTjrmvc1MOt.yNkEk7XrZm', 'Kenil', 'Bhatt', 'Individual', 'kenilabcl@gmail.com', '123456789', 1, 1, 'bhaddy');

-- --------------------------------------------------------

--
-- Table structure for table `user_attempts`
--

CREATE TABLE IF NOT EXISTS `user_attempts` (
`id` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `attempts` varchar(45) NOT NULL,
  `lastModified` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user_requests`
--

CREATE TABLE IF NOT EXISTS `user_requests` (
`RequestID` int(10) NOT NULL,
  `requestBy` varchar(40) NOT NULL,
  `requstType` varchar(40) NOT NULL,
  `approvedBy` varchar(40) DEFAULT NULL,
  `Approved` tinyint(1) NOT NULL DEFAULT '0',
  `ApprovedTime` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

CREATE TABLE IF NOT EXISTS `user_roles` (
`user_role_id` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_roles`
--

INSERT INTO `user_roles` (`user_role_id`, `username`, `role`) VALUES
(1, 'kenilabc', 'ROLE_INDIVIDUAL'),
(2, 'cborde', 'ROLE_MANAGER');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accounts`
--
ALTER TABLE `accounts`
 ADD PRIMARY KEY (`AccountID`), ADD UNIQUE KEY `AccountID_UNIQUE` (`AccountID`), ADD KEY `username_idx` (`username`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
 ADD PRIMARY KEY (`TransactionID`), ADD KEY `TransactionAccountID_idx` (`ToTransactionAccountID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
 ADD PRIMARY KEY (`username`);

--
-- Indexes for table `user_attempts`
--
ALTER TABLE `user_attempts`
 ADD PRIMARY KEY (`id`);

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
MODIFY `TransactionID` int(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `user_attempts`
--
ALTER TABLE `user_attempts`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user_requests`
--
ALTER TABLE `user_requests`
MODIFY `RequestID` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user_roles`
--
ALTER TABLE `user_roles`
MODIFY `user_role_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `accounts`
--
ALTER TABLE `accounts`
ADD CONSTRAINT `username` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `user_roles`
--
ALTER TABLE `user_roles`
ADD CONSTRAINT `fk_username` FOREIGN KEY (`username`) REFERENCES `users` (`username`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
