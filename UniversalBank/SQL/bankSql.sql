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

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES ('008080101','unibankindiv','Checking',0,NULL,'2015-10-29 19:43:12'),('008080102','unibankindiv','Savings',0,NULL,'2015-10-29 19:43:12'),('072626101','unibankindiv','Checking',0,NULL,'2015-10-29 19:49:47'),('072626102','unibankindiv','Savings',0,NULL,'2015-10-29 19:49:47'),('291325101','unibankmerchant','Checking',0,NULL,'2015-10-29 19:49:50'),('291325102','unibankmerchant','Savings',0,NULL,'2015-10-29 19:49:50'),('368035101','unibankmerchant','Checking',0,NULL,'2015-10-29 19:43:15'),('368035102','unibankmerchant','Savings',0,NULL,'2015-10-29 19:43:15');
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
-- Table structure for table `logs`
--

DROP TABLE IF EXISTS `logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logs` (
  `USER_ID` varchar(20) NOT NULL,
  `DATED` date NOT NULL,
  `MESSAGE` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logs`
--

LOCK TABLES `logs` WRITE;
/*!40000 ALTER TABLE `logs` DISABLE KEYS */;
INSERT INTO `logs` VALUES ('unibankmanager','2015-10-29','Employee Approval from unibankmanager To unibankindiv'),('unibankmanager','2015-10-29','Employee Approval from unibankmanager To unibankmerchant'),('unibankadmin','2015-10-29','Employee Approval from unibankadmin To unibankmanager'),('unibankmanager','2015-10-29','Employee Approval from unibankmanager To unibankindiv'),('unibankmanager','2015-10-29','Employee Approval from unibankmanager To unibankmerchant');
/*!40000 ALTER TABLE `logs` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,'unibankadmin','ROLE_ADMIN'),(4,'unibankclerk','ROLE_CLERK'),(2,'unibankgov','ROLE_GOV'),(17,'unibankindiv','ROLE_INDIVIDUAL'),(3,'unibankmanager','ROLE_MANAGER'),(18,'unibankmerchant','ROLE_MERCHANT');
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
INSERT INTO `users` VALUES ('unibankadmin','$2a$10$zrPyUWnNUXpGQr0.vRUpeebxygfAh8sHzvryT2c8ceFgF30tZtU/2','Admin','Admin','Admin','unibank@Admin.com','111111111',1,1,1,0,NULL,'0','0','4805556666','Tempe, AZ','male'),('unibankclerk','$2a$10$e4wkznW/obkqWYsgnZk2bOo0YAoiS5MOdlaLmIlNHcqiQ2IYjn1aK','Unibank','Clerk','Clerk','unbank@clerk.com','444444444',1,1,1,0,'unibankmanager','0','0','4806327887','Tempe, AZ','male'),('unibankgov','$2a$10$vr4ol415Bvv1hANMTaJcbOcKOJJtxTWS/OR8pAZILVX/qLkt37cr.','Gov','Gov','Gov','unibank@Gov.com','222222222',1,1,1,0,NULL,'0','0','4809623232','Washington DC','male'),('unibankindiv','$2a$10$/cZIaoRZxa7kya7TXvrRF.Up4xxjGYwXwzt/PPYuj68zZFKXvNc5S','Unibank','Individual','Individual','individual@unibank.com','999999999',1,0,1,0,'unibankclerk','131152','1446148787499','4802589632','Tempe,Az','male'),('unibankmanager','$2a$10$HyRLWWLZQq90JyQInaJOJO6uSWuzOZl2dzhJfWFjSB9Ln6MQM37e.','Unibank','Manager','Manager','unibank@manager.com','333333333',1,1,1,0,NULL,'0','0','4802146325','Tempe, AZ','female'),('unibankmerchant','$2a$10$Ri988n1YqNsVO4azmK1HTe6u/mxaaq/EhI0xIIfRKPVx43fRbegye','Unibank','Merchant','Merchant','merchant@unibank.com','963852741',1,0,1,0,'unibankclerk','180194','1446148789905','4801472589','San Diego, CA','male');
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

-- Dump completed on 2015-10-29 13:04:40

