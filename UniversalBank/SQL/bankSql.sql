create database if not exists `unibank`;
USE `unibank`;
-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: unibank
-- ------------------------------------------------------
-- Server version	5.6.26-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accounts` (
  `AccountID` varchar(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `AccountType` varchar(10) NOT NULL DEFAULT 'Checking',
  `Balance` float NOT NULL DEFAULT '0',
  `PIN` varchar(6) DEFAULT NULL,
  `CreationTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`AccountID`),
  UNIQUE KEY `AccountID_UNIQUE` (`AccountID`),
  KEY `username_idx` (`username`),
  CONSTRAINT `username` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

CREATE TABLE IF NOT EXISTS `LOGS` (
  `USER_ID` varchar(20) NOT NULL,
  `DATED` date NOT NULL,
  `MESSAGE` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `del_user_roles`
--

DROP TABLE IF EXISTS `del_user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `del_user_roles` (
  `user_role_id` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `del_user_roles`
--

LOCK TABLES `del_user_roles` WRITE;
/*!40000 ALTER TABLE `del_user_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `del_user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delete_user_roles`
--

DROP TABLE IF EXISTS `delete_user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `delete_user_roles` (
  `user_role_id` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delete_user_roles`
--

LOCK TABLES `delete_user_roles` WRITE;
/*!40000 ALTER TABLE `delete_user_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `delete_user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deletedusers`
--

DROP TABLE IF EXISTS `deletedusers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `deletedusers` (
  `username` varchar(45) NOT NULL,
  `password` varchar(60) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `AccountType` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `SSN` varchar(45) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `userLocked` tinyint(1) NOT NULL,
  `userAccountExpired` tinyint(1) NOT NULL DEFAULT '1',
  `piiAccess` tinyint(1) NOT NULL,
  `SupervisorName` varchar(40) DEFAULT NULL,
  `otp` varchar(10) NOT NULL DEFAULT '0',
  `otpValidity` varchar(25) NOT NULL DEFAULT '0',
  `phonenumber` varchar(20) DEFAULT NULL,
  `address` varchar(55) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deletedusers`
--

LOCK TABLES `deletedusers` WRITE;
/*!40000 ALTER TABLE `deletedusers` DISABLE KEYS */;
/*!40000 ALTER TABLE `deletedusers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edit_info`
--

DROP TABLE IF EXISTS `edit_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `edit_info` (
  `username` varchar(40) NOT NULL,
  `phonenumber` varchar(40) NOT NULL,
  `address` varchar(40) NOT NULL,
  `sex` varchar(10) NOT NULL,
  `Completed` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edit_info`
--

LOCK TABLES `edit_info` WRITE;
/*!40000 ALTER TABLE `edit_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `edit_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transactions` (
  `TransactionID` int(20) NOT NULL AUTO_INCREMENT,
  `TransactionType` varchar(100) NOT NULL,
  `Amount` double NOT NULL,
  `FromTransactionAccountID` varchar(20) DEFAULT NULL,
  `ToTransactionAccountID` varchar(20) DEFAULT NULL,
  `AuthorizedManagerID` varchar(100) DEFAULT NULL,
  `TransactionTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Approved` tinyint(1) NOT NULL DEFAULT '0',
  `ApprovalTime` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000',
  `Comments` varchar(140) DEFAULT NULL,
  `AuthoriseBank` tinyint(1) NOT NULL DEFAULT '1',
  `Critical_transactions` tinyint(1) NOT NULL DEFAULT '0',
  `IsDeleted` tinyint(1) NOT NULL DEFAULT '0',
  `SupervisorName` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`TransactionID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_attpts`
--

DROP TABLE IF EXISTS `user_attpts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_attpts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `attempts` varchar(45) NOT NULL,
  `lastModified` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_attpts`
--

LOCK TABLES `user_attpts` WRITE;
/*!40000 ALTER TABLE `user_attpts` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_attpts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_requests`
--

DROP TABLE IF EXISTS `user_requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_requests` (
  `RequestID` int(10) NOT NULL AUTO_INCREMENT,
  `requestBy` varchar(40) NOT NULL,
  `requstType` varchar(40) NOT NULL,
  `approvedBy` varchar(40) DEFAULT NULL,
  `Approved` tinyint(1) NOT NULL DEFAULT '0',
  `ApprovedTime` datetime DEFAULT NULL,
  `Completed` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`RequestID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_requests`
--

LOCK TABLES `user_requests` WRITE;
/*!40000 ALTER TABLE `user_requests` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_roles` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `uni_username_role` (`role`,`username`),
  KEY `fk_username_idx` (`username`),
  CONSTRAINT `fk_username` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (2,'sadmin','ROLE_ADMIN'),(1,'sgovern','ROLE_GOV');
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `username` varchar(45) NOT NULL,
  `password` varchar(60) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `AccountType` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `SSN` varchar(45) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `userLocked` tinyint(1) NOT NULL,
  `userAccountExpired` tinyint(1) NOT NULL,
  `piiAccess` tinyint(1) NOT NULL,
  `SupervisorName` varchar(40) DEFAULT NULL,
  `otp` varchar(10) NOT NULL DEFAULT '0',
  `otpValidity` varchar(25) NOT NULL DEFAULT '0',
  `phonenumber` varchar(20) DEFAULT NULL,
  `address` varchar(55) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('sadmin','$2a$10$TH0VZ3U2pA3ZkoihFRyL.OpivhaNak2O2vhD8aUuCwKSNtRisWDzm','sai','patcha','Admin','sai@admin.com','213546879',1,1,1,1,NULL,'0','0','1234567894','Laguna Miguel, CA','male'),('sgovern','$2a$10$wtTOeUmqQCYTa1d7cOgDFOWn8f62zv2ZY0DOk15e/FTQjlJ7ISp2u','sai','patcha','Gov','sai@govern.com','978645312',1,1,1,0,NULL,'0','0','1234567895','Chicago, IL','female');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-28 22:57:03
