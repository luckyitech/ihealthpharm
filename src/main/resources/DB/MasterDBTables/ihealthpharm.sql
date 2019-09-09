-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: sqlfilestesting
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `company_terms_and_conditions`
--

DROP TABLE IF EXISTS `company_terms_and_conditions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company_terms_and_conditions` (
  `COMPANY_TERMS_AND_CONDITIONS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TERMS_AND_CONDITIONS` text CHARACTER SET latin1 COLLATE latin1_swedish_ci,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `ACTIVE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  PRIMARY KEY (`COMPANY_TERMS_AND_CONDITIONS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_terms_and_conditions`
--

LOCK TABLES `company_terms_and_conditions` WRITE;
/*!40000 ALTER TABLE `company_terms_and_conditions` DISABLE KEYS */;
INSERT INTO `company_terms_and_conditions` VALUES (6,'you should be admin','2019-08-26 05:53:36','string','2019-08-26 05:58:51','string','Y'),(7,'hai','2019-08-26 05:53:36','string','2019-08-26 05:58:51','string','Y'),(8,'hello','2019-08-26 05:53:36','string','2019-08-26 05:58:51','string','Y'),(9,'ASDCDSAC SVSDVSVDV','2019-08-24 08:42:21',NULL,'2019-08-24 08:42:21',NULL,'Y'),(11,'ASFEWCCDSSDCDS\n\n\nvs\ncdac\nsd\nfd\ndvsfd\nvas\nsd\nvsdc\ndc\nds\ncasdcs\ndcds\ncds\ncds\ncdsc\ndsc\ndsc\ndsc\ndc','2019-08-24 08:43:53',NULL,'2019-08-24 08:43:53',NULL,'Y'),(14,'ZDCVsdcsSCD','2019-08-24 08:58:53',NULL,'2019-08-24 08:58:53',NULL,'Y'),(15,'afdsczacdscdsc','2019-08-24 09:00:06',NULL,'2019-08-24 09:00:06',NULL,'Y'),(16,'afdsczacdscdsccdwcAXA','2019-08-24 09:00:13',NULL,'2019-08-24 09:00:13',NULL,'Y'),(17,'ASCQC sEWDC  sdSCSDC scewcdcdc dsasc wsa dsddsc','2019-08-24 09:00:30',NULL,'2019-08-24 09:00:30',NULL,'Y'),(18,'ASCQC sEWDC  sdSCSDC scewcdcdc dsasc wsa dsddsccdCS','2019-08-24 09:00:39',NULL,'2019-08-24 09:00:39',NULL,'Y');
/*!40000 ALTER TABLE `company_terms_and_conditions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country_lookup`
--

DROP TABLE IF EXISTS `country_lookup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country_lookup` (
  `COUNTRY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `COUNTRY_CD` varchar(10) DEFAULT NULL,
  `COUNTRY_NM` varchar(50) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`COUNTRY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country_lookup`
--

LOCK TABLES `country_lookup` WRITE;
/*!40000 ALTER TABLE `country_lookup` DISABLE KEYS */;
INSERT INTO `country_lookup` VALUES (1,0,'string','India','2019-08-23 16:48:53','0','2019-08-23 16:48:53','0'),(2,0,'string','Africa','2019-08-23 16:48:53','0','2019-08-23 16:48:53','0');
/*!40000 ALTER TABLE `country_lookup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `distributor`
--

DROP TABLE IF EXISTS `distributor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `distributor` (
  `DISTRIBUTOR_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) DEFAULT NULL,
  `LICENSE` varchar(100) DEFAULT NULL,
  `PHONE_NBR` varchar(20) DEFAULT NULL,
  `EMAIL_ID` varchar(50) DEFAULT NULL,
  `CREATION_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `ACTIVE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `FAX` varchar(20) DEFAULT NULL,
  `CONTACT_PERSON_EMAIL_ID` varchar(50) DEFAULT NULL,
  `CONTACT_PERSON_PHONE_NBR` varchar(20) DEFAULT NULL,
  `CONTACT_PERSON_FIRST_NAME` varchar(50) DEFAULT NULL,
  `CONTACT_PERSON_MIDDLE_NAME` varchar(50) DEFAULT NULL,
  `CONTACT_PERSON_LAST_NAME` varchar(50) DEFAULT NULL,
  `WEBSITE` varchar(500) DEFAULT NULL,
  `ADDRESS_LINE1` varchar(250) DEFAULT NULL,
  `ADDRESS_LINE2` varchar(250) DEFAULT NULL,
  `ADDRESS_LINE3` varchar(250) DEFAULT NULL,
  `CITY_NM` varchar(50) DEFAULT NULL,
  `ZIP_CD` varchar(10) DEFAULT NULL,
  `PAYMENT_TERMS` text,
  `PAYMENT_CREDIT_NET_DAYS` int(11) DEFAULT NULL,
  `LATE_PAYMENT_INTEREST` int(11) DEFAULT NULL,
  `PIN_NO` varchar(20) DEFAULT NULL,
  `DL_NO` varchar(20) DEFAULT NULL,
  `CST_NO` varchar(20) DEFAULT NULL,
  `ALLOW_ONLINE_ORDERS` char(1) DEFAULT NULL,
  `ALLOW_MANUAL_ORDERS` char(1) DEFAULT NULL,
  `ALLOW_PHONE_ORDERS` char(1) DEFAULT NULL,
  `ACCEPT_EXPIRY_RETURNS` char(1) DEFAULT NULL,
  `ACCEPT_DAMAGED_RETURNS` char(1) DEFAULT NULL,
  `ACCEPT_GOOD_RETURNS` char(1) DEFAULT NULL,
  `RETURN_CREDIT_TYPE_ID` int(11) DEFAULT NULL,
  `COUNTRY_ID` int(11) DEFAULT NULL,
  `PROVINCES_ID` int(11) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`DISTRIBUTOR_ID`),
  KEY `FK_DISTRUBUTOR_RETURN_CREDIT_TYPE` (`RETURN_CREDIT_TYPE_ID`),
  KEY `FK_DISTRIBUTOR_COUNTRY` (`COUNTRY_ID`),
  KEY `FK_DISTRIBUTOR_PROVINCES_LOOKUP` (`PROVINCES_ID`),
  CONSTRAINT `FK_DISTRIBUTOR_COUNTRY_LOOKUP` FOREIGN KEY (`COUNTRY_ID`) REFERENCES `country_lookup` (`COUNTRY_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_DISTRIBUTOR_PROVINCES_LOOKUP` FOREIGN KEY (`PROVINCES_ID`) REFERENCES `provinces_lookup` (`PROVINCES_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_DISTRIBUTOR_RETURN_CREDIT_TYPE` FOREIGN KEY (`RETURN_CREDIT_TYPE_ID`) REFERENCES `return_credit_type` (`RETURN_CREDIT_TYPE_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `distributor`
--

LOCK TABLES `distributor` WRITE;
/*!40000 ALTER TABLE `distributor` DISABLE KEYS */;
INSERT INTO `distributor` VALUES (5,'Jagadesh','string','string','string','2019-08-26 04:19:13','string','2019-08-26 04:20:46','string','Y','string','string','string','string','string','string','string','string','string','string','string','string','string',0,0,'string','string','string','Y','Y','Y','Y','Y','Y',1,1,4,NULL);
/*!40000 ALTER TABLE `distributor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `EMPLOYEE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PHONE_NBR` varchar(20) DEFAULT NULL,
  `TITLE` varchar(5) DEFAULT NULL,
  `FIRST_NM` varchar(50) NOT NULL,
  `MIDDLE_NM` varchar(50) DEFAULT NULL,
  `LAST_NM` varchar(50) NOT NULL,
  `GENDER_CD` char(1) DEFAULT NULL,
  `DOB_DT` date DEFAULT NULL,
  `DATE_OF_JOINING` date DEFAULT NULL,
  `EMAIL_ID` varchar(50) DEFAULT NULL,
  `DESIGNATION_NM` varchar(250) DEFAULT NULL,
  `BIOGRAPHY_DESC` varchar(5000) DEFAULT NULL,
  `ADDRESS_LINE1` varchar(250) DEFAULT NULL,
  `ADDRESS_LINE2` varchar(250) DEFAULT NULL,
  `CITY_NM` varchar(50) DEFAULT NULL,
  `BLOOD_GROUP` varchar(20) DEFAULT NULL,
  `ARE_YOU_WORK_ON_SHIFTS` char(2) DEFAULT NULL,
  `ZIP_CD` varchar(20) DEFAULT NULL,
  `CREATION_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATION_USER_ID` varchar(50) NOT NULL,
  `LAST_UPDATE_TS` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `PROVIDER_IMAGE_PATH` varchar(500) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `EMPLOYEE_TYPE_LOOKUP_ID` int(11) DEFAULT NULL,
  `PHARMACY_ID` int(11) DEFAULT NULL,
  `SALARY` double DEFAULT NULL,
  `EMPLOYEE_TYPE_FULL_PART_TIME` varchar(50) DEFAULT NULL,
  `COUNTRY_ID` int(11) DEFAULT NULL,
  `PROVINCES_ID` int(11) DEFAULT NULL,
  `ACTIVE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `EMPLOYEE_CD` varchar(20) DEFAULT NULL,
  `ADDRESS_LINE3` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`EMPLOYEE_ID`),
  KEY `FK_EMPLOYEE_COUNTRY_LOOKUP` (`COUNTRY_ID`),
  KEY `FK_EMPLOYEE_EMPLOYEE_TYPE_LOOKUP` (`EMPLOYEE_TYPE_LOOKUP_ID`),
  KEY `FK_EMPLOYEE_PROVINCES_LOOKUP` (`PROVINCES_ID`),
  CONSTRAINT `FK_EMPLOYEE_COUNTRY_LOOKUP` FOREIGN KEY (`COUNTRY_ID`) REFERENCES `country_lookup` (`COUNTRY_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMPLOYEE_EMPLOYEE_TYPE_LOOKUP` FOREIGN KEY (`EMPLOYEE_TYPE_LOOKUP_ID`) REFERENCES `employee_type_lookup` (`EMPLOYEE_TYPE_LOOKUP_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMPLOYEE_PROVINCES_LOOKUP` FOREIGN KEY (`PROVINCES_ID`) REFERENCES `provinces_lookup` (`PROVINCES_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (38,'string','tt','string','string','string','M','2019-08-26','2019-08-26','string','string','string','string','string','string','string','ys','string','2019-08-26 05:36:31',' ','2019-08-26 05:36:31',NULL,'string',NULL,1,0,2000,'string',1,4,'\0','string','string'),(39,'string','t','string','string','string','M','2019-08-26','2019-08-26','string','string','string','string','string','string','string','Y','string','2019-08-26 05:43:29',' ','2019-08-26 06:02:01',NULL,'string',NULL,2,0,0,'string',1,5,'Y','string','string'),(40,'string','t','string','string','string','M','2019-08-26','2019-08-26','string','string','string','string','string','string','string','Y','string','2019-08-26 05:46:36',' ','2019-08-26 05:59:58',NULL,'string',NULL,2,0,0,'string',1,5,'Y','string','string'),(41,'string','t','string','string','string','M','2019-08-26','2019-08-26','string','string','string','string','string','string','string','Y','string','2019-08-26 05:47:50',' ','2019-08-26 06:02:01',NULL,'string',NULL,2,0,0,'string',1,5,'Y','string','string'),(42,'string','tt','string','string','string','M','2019-08-26','2019-08-26','string','string','string','string','string','string','string','ys','string','2019-08-26 05:49:38',' ','2019-08-26 05:49:38',NULL,'string',NULL,1,0,2000,'string',1,4,'Y','string','string');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_education`
--

DROP TABLE IF EXISTS `employee_education`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_education` (
  `EMPLOYEE_EDUCATION_ID` int(11) NOT NULL AUTO_INCREMENT,
  `EMPLOYEE_ID` int(11) NOT NULL,
  `STUDIED_AT` varchar(250) DEFAULT NULL,
  `DEGREE` varchar(250) DEFAULT NULL,
  `GARDUATION_DATE` date DEFAULT NULL,
  `CREATION_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATION_USER_ID` varchar(50) NOT NULL,
  `LAST_UPDATE_TS` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`EMPLOYEE_EDUCATION_ID`),
  KEY `FK_EMPLOYEE_EDUCATION_PVDR` (`EMPLOYEE_ID`),
  CONSTRAINT `FK_EMPLOYEE_EDUCATION_EMPLOYEE` FOREIGN KEY (`EMPLOYEE_ID`) REFERENCES `employee` (`EMPLOYEE_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_education`
--

LOCK TABLES `employee_education` WRITE;
/*!40000 ALTER TABLE `employee_education` DISABLE KEYS */;
INSERT INTO `employee_education` VALUES (41,38,'SVP','B.Tech','2019-08-26','2019-08-26 05:36:31',' ','2019-08-26 05:36:31',NULL,NULL),(42,39,'SVP','B.Tech','2019-08-26','2019-08-26 05:43:29',' ','2019-08-26 05:43:29',NULL,NULL),(43,40,'SVP','B.Tech','2019-08-26','2019-08-26 05:46:36',' ','2019-08-26 05:46:36',NULL,NULL),(44,41,'SVP','B.Tech','2019-08-26','2019-08-26 05:47:50',' ','2019-08-26 05:47:50',NULL,NULL),(45,42,'SVP','B.Tech','2019-08-26','2019-08-26 05:49:38',' ','2019-08-26 05:49:38',NULL,NULL),(46,40,'string','string','2019-08-26','2019-08-26 05:59:58',' ','2019-08-26 05:59:58',NULL,NULL),(47,39,'string','string','2019-08-26','2019-08-26 06:02:01',' ','2019-08-26 06:02:01',NULL,NULL),(48,41,'string','string','2019-08-26','2019-08-26 06:02:01',' ','2019-08-26 06:02:01',NULL,NULL);
/*!40000 ALTER TABLE `employee_education` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_honor`
--

DROP TABLE IF EXISTS `employee_honor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_honor` (
  `EMPLOYEE_HONOR_ID` int(11) NOT NULL AUTO_INCREMENT,
  `EMPLOYEE_ID` int(11) NOT NULL,
  `HONOR_NM` varchar(250) DEFAULT NULL,
  `HONOR_DESC` varchar(250) DEFAULT NULL,
  `RECEIVE_DT` date DEFAULT NULL,
  `CREATION_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATION_USER_ID` varchar(50) NOT NULL,
  `LAST_UPDATE_TS` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`EMPLOYEE_HONOR_ID`),
  KEY `FK_EMPLOYEE_HONOR_PVDR` (`EMPLOYEE_ID`),
  CONSTRAINT `FK_EMPLOYEE_HONOR_EMPLOYEE` FOREIGN KEY (`EMPLOYEE_ID`) REFERENCES `employee` (`EMPLOYEE_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_honor`
--

LOCK TABLES `employee_honor` WRITE;
/*!40000 ALTER TABLE `employee_honor` DISABLE KEYS */;
INSERT INTO `employee_honor` VALUES (8,38,'string','string','2019-08-26','2019-08-26 05:36:31',' ','2019-08-26 05:36:31',NULL,NULL),(9,39,'string','string','2019-08-26','2019-08-26 05:43:29',' ','2019-08-26 05:43:29',NULL,NULL),(10,40,'string','string','2019-08-26','2019-08-26 05:46:36',' ','2019-08-26 05:46:36',NULL,NULL),(11,41,'string','string','2019-08-26','2019-08-26 05:47:50',' ','2019-08-26 05:47:50',NULL,NULL),(12,42,'string','string','2019-08-26','2019-08-26 05:49:38',' ','2019-08-26 05:49:38',NULL,NULL),(13,40,'string','string','2019-08-26','2019-08-26 05:59:58',' ','2019-08-26 05:59:58',NULL,NULL),(14,39,'string','string','2019-08-26','2019-08-26 06:02:01',' ','2019-08-26 06:02:01',NULL,NULL),(15,41,'string','string','2019-08-26','2019-08-26 06:02:01',' ','2019-08-26 06:02:01',NULL,NULL);
/*!40000 ALTER TABLE `employee_honor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_interest`
--

DROP TABLE IF EXISTS `employee_interest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_interest` (
  `EMPLOYEE_INTEREST_ID` int(11) NOT NULL AUTO_INCREMENT,
  `EMPLOYEE_ID` int(11) NOT NULL,
  `AREA_OF_INTEREST_DESC` varchar(250) DEFAULT NULL,
  `CREATION_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATION_USER_ID` varchar(50) NOT NULL,
  `LAST_UPDATE_TS` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `INTRESTED_AT` varchar(250) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`EMPLOYEE_INTEREST_ID`),
  KEY `FK_EMPLOYEE_INTREST_EMPLOYEE_idx` (`EMPLOYEE_ID`),
  CONSTRAINT `FK_EMPLOYEE_INTREST_EMPLOYEE` FOREIGN KEY (`EMPLOYEE_ID`) REFERENCES `employee` (`EMPLOYEE_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_interest`
--

LOCK TABLES `employee_interest` WRITE;
/*!40000 ALTER TABLE `employee_interest` DISABLE KEYS */;
INSERT INTO `employee_interest` VALUES (8,38,'string','2019-08-26 05:36:31','  ','2019-08-26 05:36:31',NULL,NULL,NULL),(9,39,'string','2019-08-26 05:43:29','  ','2019-08-26 05:43:29',NULL,NULL,NULL),(10,40,'string','2019-08-26 05:46:36','  ','2019-08-26 05:46:36',NULL,NULL,NULL),(11,41,'string','2019-08-26 05:47:50','  ','2019-08-26 05:47:50',NULL,NULL,NULL),(12,42,'string','2019-08-26 05:49:38','  ','2019-08-26 05:49:38',NULL,NULL,NULL),(13,40,'string','2019-08-26 05:59:58','  ','2019-08-26 05:59:58',NULL,NULL,NULL),(14,39,'string','2019-08-26 06:02:01','  ','2019-08-26 06:02:01',NULL,NULL,NULL),(15,41,'string','2019-08-26 06:02:01','  ','2019-08-26 06:02:01',NULL,NULL,NULL);
/*!40000 ALTER TABLE `employee_interest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_prof_membership`
--

DROP TABLE IF EXISTS `employee_prof_membership`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_prof_membership` (
  `EMPLOYEE_PROF_MEMBERSHIP_ID` int(11) NOT NULL AUTO_INCREMENT,
  `EMPLOYEE_ID` int(11) NOT NULL,
  `MEMBERSHIP_NM` varchar(250) DEFAULT NULL,
  `START_DT` date DEFAULT NULL,
  `END_DT` date DEFAULT NULL,
  `CREATION_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATION_USER_ID` varchar(50) NOT NULL,
  `LAST_UPDATE_TS` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`EMPLOYEE_PROF_MEMBERSHIP_ID`),
  KEY `FK_EMPLOYEE_PROF_MEMBERSHIP_PVDR` (`EMPLOYEE_ID`),
  CONSTRAINT `FK_EMPLOYEE_PROF_MEMBERSHIP_EMPLOYEE` FOREIGN KEY (`EMPLOYEE_ID`) REFERENCES `employee` (`EMPLOYEE_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_prof_membership`
--

LOCK TABLES `employee_prof_membership` WRITE;
/*!40000 ALTER TABLE `employee_prof_membership` DISABLE KEYS */;
INSERT INTO `employee_prof_membership` VALUES (7,38,'string','2019-08-26','2019-08-26','2019-08-26 05:36:31','  ','2019-08-26 05:36:31',NULL,NULL),(8,39,'string','2019-08-26','2019-08-26','2019-08-26 05:43:29','  ','2019-08-26 05:43:29',NULL,NULL),(9,40,'string','2019-08-26','2019-08-26','2019-08-26 05:46:36','  ','2019-08-26 05:46:36',NULL,NULL),(10,41,'string','2019-08-26','2019-08-26','2019-08-26 05:47:50','  ','2019-08-26 05:47:50',NULL,NULL),(11,42,'string','2019-08-26','2019-08-26','2019-08-26 05:49:38','  ','2019-08-26 05:49:38',NULL,NULL),(12,40,'string','2019-08-26','2019-08-26','2019-08-26 05:59:58','  ','2019-08-26 05:59:58',NULL,NULL),(13,39,'string','2019-08-26','2019-08-26','2019-08-26 06:02:01','  ','2019-08-26 06:02:01',NULL,NULL),(14,41,'string','2019-08-26','2019-08-26','2019-08-26 06:02:01','  ','2019-08-26 06:02:01',NULL,NULL);
/*!40000 ALTER TABLE `employee_prof_membership` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_publication`
--

DROP TABLE IF EXISTS `employee_publication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_publication` (
  `EMPLOYEE_PUBLICATION_ID` int(11) NOT NULL AUTO_INCREMENT,
  `EMPLOYEE_ID` int(11) NOT NULL,
  `PUBLICATION_NM` varchar(250) DEFAULT NULL,
  `PUBLICATION_DESC` varchar(250) DEFAULT NULL,
  `PUBLISH_DT` date DEFAULT NULL,
  `CREATION_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATION_USER_ID` varchar(50) NOT NULL,
  `LAST_UPDATE_TS` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`EMPLOYEE_PUBLICATION_ID`),
  KEY `FK_EMPLOYEE_PUBLICATION_PVDR` (`EMPLOYEE_ID`),
  CONSTRAINT `FK_EMPLOYEE_PUBLICATION_EMPLOYEE` FOREIGN KEY (`EMPLOYEE_ID`) REFERENCES `employee` (`EMPLOYEE_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_publication`
--

LOCK TABLES `employee_publication` WRITE;
/*!40000 ALTER TABLE `employee_publication` DISABLE KEYS */;
INSERT INTO `employee_publication` VALUES (6,38,'string','string','2019-08-26','2019-08-26 05:36:31',' ','2019-08-26 05:36:31',NULL,NULL),(7,39,'string','string','2019-08-26','2019-08-26 05:43:29',' ','2019-08-26 05:43:29',NULL,NULL),(8,40,'string','string','2019-08-26','2019-08-26 05:46:36',' ','2019-08-26 05:46:36',NULL,NULL),(9,41,'string','string','2019-08-26','2019-08-26 05:47:50',' ','2019-08-26 05:47:50',NULL,NULL),(10,42,'string','string','2019-08-26','2019-08-26 05:49:38',' ','2019-08-26 05:49:38',NULL,NULL),(11,40,'string','string','2019-08-26','2019-08-26 05:59:58',' ','2019-08-26 05:59:58',NULL,NULL),(12,39,'string','string','2019-08-26','2019-08-26 06:02:01',' ','2019-08-26 06:02:01',NULL,NULL),(13,41,'string','string','2019-08-26','2019-08-26 06:02:01',' ','2019-08-26 06:02:01',NULL,NULL);
/*!40000 ALTER TABLE `employee_publication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_salary`
--

DROP TABLE IF EXISTS `employee_salary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_salary` (
  `EMPLOYEE_SALARY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `EMPLOYEE_ID` int(11) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `SALARY_DT` date DEFAULT NULL,
  `BASIC` double DEFAULT NULL,
  `HRA` double DEFAULT NULL,
  `DA` double DEFAULT NULL,
  `MEDICAL` double DEFAULT NULL,
  `P_TAX` double DEFAULT NULL,
  `PF_EMPLOYEE` double DEFAULT NULL,
  `PF_EMPLOYER` double DEFAULT NULL,
  `TDS` double DEFAULT NULL,
  `ESI` double DEFAULT NULL,
  `GROSS_SALARY` double DEFAULT NULL,
  PRIMARY KEY (`EMPLOYEE_SALARY_ID`),
  KEY `FK_EMPLOYEE_SALARY_EMPLOYEE` (`EMPLOYEE_ID`),
  CONSTRAINT `FK_EMPLOYEE_SALARY_EMPLOYEE` FOREIGN KEY (`EMPLOYEE_ID`) REFERENCES `employee` (`EMPLOYEE_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_salary`
--

LOCK TABLES `employee_salary` WRITE;
/*!40000 ALTER TABLE `employee_salary` DISABLE KEYS */;
INSERT INTO `employee_salary` VALUES (6,NULL,38,'2019-08-26 05:36:31',' ','2019-08-26 05:36:31',NULL,'2019-08-26',0,0,0,0,0,0,0,0,0,0),(7,NULL,39,'2019-08-26 05:43:29',' ','2019-08-26 05:43:29',NULL,'2019-08-26',0,0,0,0,0,0,0,0,0,0),(8,NULL,40,'2019-08-26 05:46:36',' ','2019-08-26 05:46:36',NULL,'2019-08-26',0,0,0,0,0,0,0,0,0,0),(9,NULL,41,'2019-08-26 05:47:50',' ','2019-08-26 05:47:50',NULL,'2019-08-26',0,0,0,0,0,0,0,0,0,0),(10,NULL,42,'2019-08-26 05:49:38',' ','2019-08-26 05:49:38',NULL,'2019-08-26',0,0,0,0,0,0,0,0,0,0),(11,NULL,40,'2019-08-26 05:59:58',' ','2019-08-26 05:59:58',NULL,'2019-08-26',0,0,0,0,0,0,0,0,0,0),(12,NULL,39,'2019-08-26 06:02:01',' ','2019-08-26 06:02:01',NULL,'2019-08-26',0,0,0,0,0,0,0,0,0,0),(13,NULL,41,'2019-08-26 06:02:01',' ','2019-08-26 06:02:01',NULL,'2019-08-26',0,0,0,0,0,0,0,0,0,0);
/*!40000 ALTER TABLE `employee_salary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_type_lookup`
--

DROP TABLE IF EXISTS `employee_type_lookup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_type_lookup` (
  `EMPLOYEE_TYPE_LOOKUP_ID` int(11) NOT NULL AUTO_INCREMENT,
  `EMPLOYEE_TYPE_CD` char(2) NOT NULL,
  `EMPLOYEE_TYPE_DESC` varchar(250) DEFAULT NULL,
  `CREATION_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATION_USER_ID` varchar(50) NOT NULL,
  `LAST_UPDATE_TS` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`EMPLOYEE_TYPE_LOOKUP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_type_lookup`
--

LOCK TABLES `employee_type_lookup` WRITE;
/*!40000 ALTER TABLE `employee_type_lookup` DISABLE KEYS */;
INSERT INTO `employee_type_lookup` VALUES (1,'PA','PHARMACIST','2019-08-21 13:00:00','SYS_ADMIN','2019-08-21 13:00:00',NULL,NULL),(2,'SM','STORE MANAGER','2019-08-21 13:00:00','SYS_ADMIN','2019-08-21 13:00:00',NULL,NULL);
/*!40000 ALTER TABLE `employee_type_lookup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employment_history`
--

DROP TABLE IF EXISTS `employment_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employment_history` (
  `EMPLOYMENT_HISTORY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `COMPANY_NAME` varchar(100) NOT NULL,
  `START_DT` date DEFAULT NULL,
  `END_DT` date DEFAULT NULL,
  `COMPANY_ADDRESS` varchar(250) DEFAULT NULL,
  `REPORTING_PERSON_DETAILS` varchar(50) DEFAULT NULL,
  `MANAGER_NM` varchar(50) DEFAULT NULL,
  `MANAGER_PHONE_NBR` varchar(20) DEFAULT NULL,
  `MANAGER_EMAIL` varchar(50) DEFAULT NULL,
  `DESIGNATION` varchar(50) DEFAULT NULL,
  `EMPLOYMENT_TYPE` varchar(50) DEFAULT NULL,
  `REFERENCE1` varchar(50) DEFAULT NULL,
  `REFERENCE2` varchar(50) DEFAULT NULL,
  `CREATION_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATION_USER_ID` varchar(50) NOT NULL,
  `LAST_UPDATE_TS` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `EMPLOYEE_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`EMPLOYMENT_HISTORY_ID`),
  KEY `FK_EMPLOYEE_HISTORY_EMPLOYEE` (`EMPLOYEE_ID`),
  CONSTRAINT `FK_EMPLOYEE_HISTORY_EMPLOYEE` FOREIGN KEY (`EMPLOYEE_ID`) REFERENCES `employee` (`EMPLOYEE_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employment_history`
--

LOCK TABLES `employment_history` WRITE;
/*!40000 ALTER TABLE `employment_history` DISABLE KEYS */;
INSERT INTO `employment_history` VALUES (11,'string','2019-08-26','2019-08-26','string','string','string','string','string','string','string','string','string','2019-08-26 05:36:31',' ','2019-08-26 05:36:31',NULL,NULL,38),(12,'string','2019-08-26','2019-08-26','string','string','string','string','string','string','string','string','string','2019-08-26 05:43:29',' ','2019-08-26 05:43:29',NULL,NULL,39),(13,'string','2019-08-26','2019-08-26','string','string','string','string','string','string','string','string','string','2019-08-26 05:46:36',' ','2019-08-26 05:46:36',NULL,NULL,40),(14,'string','2019-08-26','2019-08-26','string','string','string','string','string','string','string','string','string','2019-08-26 05:47:50',' ','2019-08-26 05:47:50',NULL,NULL,41),(15,'string','2019-08-26','2019-08-26','string','string','string','string','string','string','string','string','string','2019-08-26 05:49:38',' ','2019-08-26 05:49:38',NULL,NULL,42),(16,'string','2019-08-26','2019-08-26','string','string','string','string','string','string','string','string','string','2019-08-26 05:59:58',' ','2019-08-26 05:59:58',NULL,NULL,40),(17,'string','2019-08-26','2019-08-26','string','string','string','string','string','string','string','string','string','2019-08-26 06:02:01',' ','2019-08-26 06:02:01',NULL,NULL,39),(18,'string','2019-08-26','2019-08-26','string','string','string','string','string','string','string','string','string','2019-08-26 06:02:01',' ','2019-08-26 06:02:01',NULL,NULL,41);
/*!40000 ALTER TABLE `employment_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hospital`
--

DROP TABLE IF EXISTS `hospital`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hospital` (
  `HOSPITAL_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) DEFAULT NULL,
  `LICENSE` varchar(100) DEFAULT NULL,
  `PHONE_NBR` varchar(20) DEFAULT NULL,
  `EMAIL_ID` varchar(50) DEFAULT NULL,
  `CREATION_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `FAX` varchar(20) DEFAULT NULL,
  `ACTIVE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `ADDRESS_LINE1` varchar(250) DEFAULT NULL,
  `ADDRESS_LINE2` varchar(250) DEFAULT NULL,
  `CITY_NM` varchar(50) DEFAULT NULL,
  `ZIP_CD` varchar(10) DEFAULT NULL,
  `WEBSITE` varchar(50) DEFAULT NULL,
  `HELPLINE` varchar(20) DEFAULT NULL,
  `CONTACT_NBR` varchar(20) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `COUNTRY_ID` int(11) DEFAULT NULL,
  `PROVINCES_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`HOSPITAL_ID`),
  KEY `FK_HOSPITAL_COUNTRY_LOOKUP` (`COUNTRY_ID`),
  KEY `FK_HOSPITAL_PROVINCES_LOOKUP` (`PROVINCES_ID`),
  CONSTRAINT `FK_HOSPITAL_COUNTRY_LOOKUP` FOREIGN KEY (`COUNTRY_ID`) REFERENCES `country_lookup` (`COUNTRY_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_HOSPITAL_PROVINCES_LOOKUP` FOREIGN KEY (`PROVINCES_ID`) REFERENCES `provinces_lookup` (`PROVINCES_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hospital`
--

LOCK TABLES `hospital` WRITE;
/*!40000 ALTER TABLE `hospital` DISABLE KEYS */;
/*!40000 ALTER TABLE `hospital` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items` (
  `ITEM_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ITEM_NM` varchar(100) DEFAULT NULL,
  `ITEM_DESC` mediumtext CHARACTER SET latin1 COLLATE latin1_swedish_ci,
  `MEDICAL_OR_NON_MEDICAL` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'M',
  `ITEM_GROUP_ID` int(11) DEFAULT NULL,
  `ITEM_GENERIC_NAME_ID` int(11) DEFAULT NULL,
  `ITEM_FORM_ID` int(11) DEFAULT NULL,
  `ITEM_CATEGORIE_ID` int(11) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `ACTIVE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `MANUFACTURER_ID` int(11) DEFAULT NULL,
  `SPECIFICATION` varchar(100) DEFAULT NULL,
  `DRUG_DOSE` varchar(20) DEFAULT NULL,
  `IS_ASSET` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `USAGE` mediumtext CHARACTER SET latin1 COLLATE latin1_swedish_ci,
  `DRUG_SCHEDULE` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `REORDER_ITEM` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ITEM_ID`),
  KEY `FK_ITEMS_ITEMS_CATEGORIES` (`ITEM_CATEGORIE_ID`),
  KEY `FK_ITEMS_ITEMS_FORM_ID` (`ITEM_FORM_ID`),
  KEY `FK_ITEMS_ITEMS_GENERIC_NAMES` (`ITEM_GENERIC_NAME_ID`),
  KEY `FK_ITEMS_ITEMS_GROUP_ID` (`ITEM_GROUP_ID`),
  KEY `FK_ITEMS_MANUFACTURER` (`MANUFACTURER_ID`),
  CONSTRAINT `FK_ITEMS_ITEMS_CATEGORIES` FOREIGN KEY (`ITEM_CATEGORIE_ID`) REFERENCES `items_categories` (`ITEM_CATEGORIE_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_ITEMS_ITEMS_FORM_ID` FOREIGN KEY (`ITEM_FORM_ID`) REFERENCES `items_forms` (`ITEM_FORM_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_ITEMS_ITEMS_GENERIC_NAMES` FOREIGN KEY (`ITEM_GENERIC_NAME_ID`) REFERENCES `items_generic_names` (`ITEM_GENERIC_NAME_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_ITEMS_ITEMS_GROUP_ID` FOREIGN KEY (`ITEM_GROUP_ID`) REFERENCES `items_group` (`ITEM_GROUP_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_ITEMS_MANUFACTURER` FOREIGN KEY (`MANUFACTURER_ID`) REFERENCES `manufacturer` (`MANUFACTURER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (14,'string','string','Y',2,1,3,1,'2019-08-26 10:21:24','VUDO','2019-08-26 10:21:24','0',NULL,'Y',1,'string','2MG','Y',NULL,'Y','string'),(15,'string','string','Y',2,1,3,1,'2019-08-26 10:22:43','VUDO','2019-08-26 10:22:43','0',NULL,'Y',1,'string','2MG','Y',NULL,'Y','string'),(16,'string','string','Y',2,1,3,1,'2019-08-26 10:26:13','VUDO','2019-08-26 10:26:13','0',NULL,'Y',1,'string','2MG','Y',NULL,'Y','string');
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items_categories`
--

DROP TABLE IF EXISTS `items_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items_categories` (
  `ITEM_CATEGORIE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CATEGORIE_NM` varchar(100) DEFAULT NULL,
  `CATEGORIE_DESC` mediumtext CHARACTER SET latin1 COLLATE latin1_swedish_ci,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `ACTIVE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  PRIMARY KEY (`ITEM_CATEGORIE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items_categories`
--

LOCK TABLES `items_categories` WRITE;
/*!40000 ALTER TABLE `items_categories` DISABLE KEYS */;
INSERT INTO `items_categories` VALUES (1,'Antibiotics',' ','2019-08-21 13:00:00','SYS_ADMIN','2019-08-21 13:00:00',NULL,NULL,'Y');
/*!40000 ALTER TABLE `items_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items_distributor`
--

DROP TABLE IF EXISTS `items_distributor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items_distributor` (
  `ITEM_DISTRIBUTOR_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ITEM_ID` int(11) DEFAULT NULL,
  `DISTRIBUTOR_ID` int(11) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ITEM_DISTRIBUTOR_ID`),
  KEY `FK_ITEMS_DISTRIBUTOR_ITEMS` (`ITEM_ID`),
  KEY `FK_ITEMS_DISTRIBUTOR_DISTRIBUTOR` (`DISTRIBUTOR_ID`),
  CONSTRAINT `FK_ITEMS_DISTRIBUTOR_DISTRIBUTOR` FOREIGN KEY (`DISTRIBUTOR_ID`) REFERENCES `distributor` (`DISTRIBUTOR_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_ITEMS_DISTRIBUTOR_ITEMS` FOREIGN KEY (`ITEM_ID`) REFERENCES `items` (`ITEM_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items_distributor`
--

LOCK TABLES `items_distributor` WRITE;
/*!40000 ALTER TABLE `items_distributor` DISABLE KEYS */;
INSERT INTO `items_distributor` VALUES (1,16,5,'2019-08-25 22:50:46','string','2019-08-26 10:26:13','0');
/*!40000 ALTER TABLE `items_distributor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items_forms`
--

DROP TABLE IF EXISTS `items_forms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items_forms` (
  `ITEM_FORM_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FORM` varchar(50) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `ACTIVE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `MEDICAL_OR_NON_MEDICAL` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'M',
  PRIMARY KEY (`ITEM_FORM_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items_forms`
--

LOCK TABLES `items_forms` WRITE;
/*!40000 ALTER TABLE `items_forms` DISABLE KEYS */;
INSERT INTO `items_forms` VALUES (3,'PD','2019-08-21 07:30:00',NULL,'2019-08-22 15:40:49',NULL,NULL,'Y','Y');
/*!40000 ALTER TABLE `items_forms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items_generic_names`
--

DROP TABLE IF EXISTS `items_generic_names`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items_generic_names` (
  `ITEM_GENERIC_NAME_ID` int(11) NOT NULL AUTO_INCREMENT,
  `GENERIC_NAME` varchar(100) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `ACTIVE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `MEDICAL_OR_NON_MEDICAL` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'M',
  `ITEM_GROUP_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ITEM_GENERIC_NAME_ID`),
  KEY `FK_ITEM_GENERICS_ITEMS_GROUP` (`ITEM_GROUP_ID`),
  CONSTRAINT `FK_ITEM_GENERICS_ITEMS_GROUP` FOREIGN KEY (`ITEM_GROUP_ID`) REFERENCES `items_group` (`ITEM_GROUP_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items_generic_names`
--

LOCK TABLES `items_generic_names` WRITE;
/*!40000 ALTER TABLE `items_generic_names` DISABLE KEYS */;
INSERT INTO `items_generic_names` VALUES (1,'Paracetamol ','2019-08-21 13:00:00','SYS_ADMIN','2019-08-21 13:00:00',NULL,NULL,'Y','M',3);
/*!40000 ALTER TABLE `items_generic_names` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items_group`
--

DROP TABLE IF EXISTS `items_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items_group` (
  `ITEM_GROUP_ID` int(11) NOT NULL AUTO_INCREMENT,
  `GROUP_NAME` varchar(100) DEFAULT NULL,
  `GROUP_DESC` mediumtext CHARACTER SET latin1 COLLATE latin1_swedish_ci,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `ACTIVE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `MEDICAL_OR_NON_MEDICAL` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'M',
  PRIMARY KEY (`ITEM_GROUP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items_group`
--

LOCK TABLES `items_group` WRITE;
/*!40000 ALTER TABLE `items_group` DISABLE KEYS */;
INSERT INTO `items_group` VALUES (1,'Stimulants','Stimulants (or â€œuppersâ€) impact the bodyâ€™s central nervous system (CNS), causing the user to feel as if they are â€œspeeding up.â€ These drugs increase the userâ€™s level of alertness, pumping up heart rate, blood pressure, breathing and blood glucose levels.','2019-08-21 13:00:00','SYS_ADMIN','2019-08-21 13:00:00',NULL,NULL,'Y','M'),(2,'Inhalants','Inhalants are substances and medications which have to be inhaled for the recipient to feel its effects. An example of a medication that is an inhalant would be nitrates, which help patients with chest pain. Other substances such as aerosol sprays, gases, spray paints, markers, glues and cleaning fluids can also be misused as inhalants even though they are not medications.','2019-08-21 13:00:00','SYS_ADMIN','2019-08-21 13:00:00',NULL,NULL,'Y','M'),(3,'Acetaminophen',' ','2019-08-21 13:00:00','SYS_ADMIN','2019-08-21 13:00:00',NULL,NULL,'Y','M');
/*!40000 ALTER TABLE `items_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manufacturer`
--

DROP TABLE IF EXISTS `manufacturer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manufacturer` (
  `MANUFACTURER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) DEFAULT NULL,
  `LICENSE` varchar(100) DEFAULT NULL,
  `PHONE_NBR` varchar(20) DEFAULT NULL,
  `EMAIL_ID` varchar(50) DEFAULT NULL,
  `CREATION_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `ACTIVE_S` char(1) DEFAULT NULL,
  `FAX` varchar(20) DEFAULT NULL,
  `CONTACT_PERSON_EMAIL_ID` varchar(50) DEFAULT NULL,
  `CONTACT_PERSON_PHONE_NBR` varchar(20) DEFAULT NULL,
  `CONTACT_PERSON_FIRST_NM` varchar(50) DEFAULT NULL,
  `WEBSITE` varchar(500) DEFAULT NULL,
  `ADDRESS_LINE1` varchar(250) DEFAULT NULL,
  `ADDRESS_LINE2` varchar(250) DEFAULT NULL,
  `CITY_NM` varchar(50) DEFAULT NULL,
  `ZIP_CD` varchar(10) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `COUNTRY_ID` int(11) DEFAULT NULL,
  `PROVINCES_ID` int(11) DEFAULT NULL,
  `CONTACT_PERSON_MIDDLE_NM` varchar(50) DEFAULT NULL,
  `CONTACT_PERSON_LAST_NM` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`MANUFACTURER_ID`),
  KEY `FK_MANUFACTURER_COUNTRY_LOOKUP` (`COUNTRY_ID`),
  KEY `FK_MANUFACTURER_PROVINCES_LOOKUP` (`PROVINCES_ID`),
  CONSTRAINT `FK_MANUFACTURER_COUNTRY_LOOKUP` FOREIGN KEY (`COUNTRY_ID`) REFERENCES `country_lookup` (`COUNTRY_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_MANUFACTURER_PROVINCES_LOOKUP` FOREIGN KEY (`PROVINCES_ID`) REFERENCES `provinces_lookup` (`PROVINCES_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manufacturer`
--

LOCK TABLES `manufacturer` WRITE;
/*!40000 ALTER TABLE `manufacturer` DISABLE KEYS */;
INSERT INTO `manufacturer` VALUES (1,'string','string','string','string','2019-08-25 14:51:53','string','2019-08-25 15:02:34','string','Y','string','string','string','string','string','string','string','string','string',0,1,4,'string','string');
/*!40000 ALTER TABLE `manufacturer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pharmacy`
--

DROP TABLE IF EXISTS `pharmacy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pharmacy` (
  `PHARMACY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PHARMACY_NM` varchar(250) NOT NULL,
  `TAX_ID` varchar(20) NOT NULL,
  `PHONE_NBR` varchar(20) NOT NULL,
  `EMAIL_ID` varchar(50) NOT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `ACTIVE_S` char(1) NOT NULL DEFAULT 'Y',
  `CREATION_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATION_USER_ID` varchar(50) NOT NULL,
  `LAST_UPDATE_TS` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUTHORIZED_PERSON_FIRST_NM` varchar(50) NOT NULL,
  `AUTHORIZED_PERSON_EMAIL_ID` varchar(50) DEFAULT NULL,
  `AUTHORIZED_PERSON_PHONE_NBR` varchar(20) DEFAULT NULL,
  `PHARMACY_LOGO_PATH` varchar(500) DEFAULT NULL,
  `ADDRESS_LINE1` varchar(250) DEFAULT NULL,
  `ADDRESS_LINE2` varchar(250) DEFAULT NULL,
  `CITY_NM` varchar(50) DEFAULT NULL,
  `PROVINCES_ID` int(11) DEFAULT NULL,
  `COUNTRY_ID` int(11) DEFAULT NULL,
  `ZIP_CD` varchar(10) DEFAULT NULL,
  `WEBSITE_URL` varchar(500) DEFAULT NULL,
  `FAX_NBR` varchar(20) DEFAULT NULL,
  `AUTHORIZED_PERSON_MIDDLE_NM` varchar(50) DEFAULT NULL,
  `AUTHORIZED_PERSON_LAST_NM` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`PHARMACY_ID`),
  KEY `FK_PHARMACY_PROVINCES_LOOKUP` (`PROVINCES_ID`),
  KEY `FK_PHARMACY_COUNTRY_LOOKUP` (`COUNTRY_ID`),
  CONSTRAINT `FK_PHARMACY_COUNTRY_LOOKUP` FOREIGN KEY (`COUNTRY_ID`) REFERENCES `country_lookup` (`COUNTRY_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_PHARMACY_PROVINCES_LOOKUP` FOREIGN KEY (`PROVINCES_ID`) REFERENCES `provinces_lookup` (`PROVINCES_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pharmacy`
--

LOCK TABLES `pharmacy` WRITE;
/*!40000 ALTER TABLE `pharmacy` DISABLE KEYS */;
INSERT INTO `pharmacy` VALUES (2,'appolo','string','string','string',0,'Y','2019-08-23 16:51:49','string','2019-08-23 16:51:49','string','string','string','string','string','string','string','string',4,1,'23456','string','string','string','string');
/*!40000 ALTER TABLE `pharmacy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pharmacy_branch`
--

DROP TABLE IF EXISTS `pharmacy_branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pharmacy_branch` (
  `PHARMACY_BRANCH_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PHARMACY_BRANCH_NM` varchar(250) NOT NULL,
  `PHARMACY_ID` int(11) NOT NULL,
  `PHONE_NBR` varchar(20) DEFAULT NULL,
  `FAX_NBR` varchar(20) DEFAULT NULL,
  `EMAIL_ID` varchar(50) DEFAULT NULL,
  `WEBSITE_URL` varchar(250) DEFAULT NULL,
  `OTHER_DETAILS_DESC` varchar(250) DEFAULT NULL,
  `ADDRESS_LINE1` varchar(250) DEFAULT NULL,
  `ACTIVE_S` char(1) DEFAULT 'Y',
  `CREATION_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATION_USER_ID` varchar(50) NOT NULL,
  `LAST_UPDATE_TS` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `ADDRESS_LINE2` varchar(250) DEFAULT NULL,
  `CITY_NM` varchar(50) DEFAULT NULL,
  `PROVINCES_ID` int(11) DEFAULT NULL,
  `COUNTRY_ID` int(11) DEFAULT NULL,
  `ZIP_CD` varchar(20) DEFAULT NULL,
  `24_HOURS` char(1) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`PHARMACY_BRANCH_ID`),
  KEY `FK_PHARMACY_BRANCH_PHARMACY` (`PHARMACY_ID`),
  KEY `FK_PHARMACY_BRANCH_PROVINCES_LOOKUP` (`PROVINCES_ID`),
  KEY `FK_PHARMACY_BRANCH_COUNTRY_LOOKUP` (`COUNTRY_ID`),
  CONSTRAINT `FK_PHARMACY_BRANCH_COUNTRY_LOOKUP` FOREIGN KEY (`COUNTRY_ID`) REFERENCES `country_lookup` (`COUNTRY_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_PHARMACY_BRANCH_PHARMACY` FOREIGN KEY (`PHARMACY_ID`) REFERENCES `pharmacy` (`PHARMACY_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_PHARMACY_BRANCH_PROVINCES_LOOKUP` FOREIGN KEY (`PROVINCES_ID`) REFERENCES `provinces_lookup` (`PROVINCES_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pharmacy_branch`
--

LOCK TABLES `pharmacy_branch` WRITE;
/*!40000 ALTER TABLE `pharmacy_branch` DISABLE KEYS */;
/*!40000 ALTER TABLE `pharmacy_branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pharmacy_stock_points`
--

DROP TABLE IF EXISTS `pharmacy_stock_points`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pharmacy_stock_points` (
  `PHARMACY_STOCK_POINTS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PHARMACY_BRANCH_ID` int(11) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `ACTIVE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `STOCK_POINT_NM` varchar(50) DEFAULT NULL,
  `STOCK_POINT_TYPE` varchar(20) DEFAULT NULL,
  `STOCK_POINT_ALIAS_NM` varchar(20) DEFAULT NULL,
  `TRANSACTION_TYPE` char(2) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `RETURN_APPLICABLE` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `OT_TAX_APPLICABLE` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `PAYMENT_TYPE` varchar(20) DEFAULT NULL,
  `DISCOUNT_FACILITY` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`PHARMACY_STOCK_POINTS_ID`),
  KEY `FK_PHARMACY_STOCK_POINTS_PHARMACY_BRANCH` (`PHARMACY_BRANCH_ID`),
  CONSTRAINT `FK_PHARMACY_STOCK_POINTS_PHARMACY_BRANCH` FOREIGN KEY (`PHARMACY_BRANCH_ID`) REFERENCES `pharmacy_branch` (`PHARMACY_BRANCH_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pharmacy_stock_points`
--

LOCK TABLES `pharmacy_stock_points` WRITE;
/*!40000 ALTER TABLE `pharmacy_stock_points` DISABLE KEYS */;
/*!40000 ALTER TABLE `pharmacy_stock_points` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provider`
--

DROP TABLE IF EXISTS `provider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `provider` (
  `PROVIDER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FIRST_NM` varchar(50) DEFAULT NULL,
  `MIDDLE_NM` varchar(50) DEFAULT NULL,
  `LAST_NM` varchar(50) DEFAULT NULL,
  `PHONE_NBR` varchar(20) DEFAULT NULL,
  `EMAIL_ID` varchar(50) DEFAULT NULL,
  `CREATION_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `ACTIVE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `ADDRESS_LINE1` varchar(250) DEFAULT NULL,
  `ADDRESS_LINE2` varchar(250) DEFAULT NULL,
  `ADDRESS_LINE3` varchar(250) DEFAULT NULL,
  `CITY_NM` varchar(50) DEFAULT NULL,
  `ZIP_CD` varchar(10) DEFAULT NULL,
  `CREDIT` varchar(20) DEFAULT NULL,
  `LICENSE_NUMBER` varchar(20) DEFAULT NULL,
  `DEA_NUMBER` varchar(20) DEFAULT NULL,
  `GENDER_CD` char(1) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `SPECIALITY` varchar(20) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `PROVIDER_TYPE_LOOKUP_ID` int(11) DEFAULT NULL,
  `COUNTRY_ID` int(11) DEFAULT NULL,
  `PROVINCES_ID` int(11) DEFAULT NULL,
  `HOSPITAL_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`PROVIDER_ID`),
  KEY `FK_PROVIDER_COUNTRY_LOOKUP` (`COUNTRY_ID`),
  KEY `FK_PROVIDER_PROVIDER_LOOKUP_ID` (`PROVIDER_TYPE_LOOKUP_ID`),
  KEY `FK_PROVIDER_PROVINCES_LOOKUP` (`PROVINCES_ID`),
  KEY `FK_PROVIDER_HOSPITAL_ID_idx` (`HOSPITAL_ID`),
  CONSTRAINT `FK_PROVIDER_COUNTRY_LOOKUP` FOREIGN KEY (`COUNTRY_ID`) REFERENCES `country_lookup` (`COUNTRY_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_PROVIDER_HOSPITAL_ID` FOREIGN KEY (`HOSPITAL_ID`) REFERENCES `hospital` (`HOSPITAL_ID`),
  CONSTRAINT `FK_PROVIDER_PROVIDER_LOOKUP_ID` FOREIGN KEY (`PROVIDER_TYPE_LOOKUP_ID`) REFERENCES `provider_type_lookup` (`PROVIDER_TYPE_LOOKUP_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_PROVIDER_PROVINCES_LOOKUP` FOREIGN KEY (`PROVINCES_ID`) REFERENCES `provinces_lookup` (`PROVINCES_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provider`
--

LOCK TABLES `provider` WRITE;
/*!40000 ALTER TABLE `provider` DISABLE KEYS */;
INSERT INTO `provider` VALUES (4,'jagadeesh',NULL,'jagadeesh','7306806693','g.jagadeesh.j@gmail.com','2019-08-22 21:10:49',NULL,'2019-08-22 21:10:49',NULL,'Y',NULL,NULL,NULL,'','123456',NULL,'',NULL,NULL,NULL,NULL,NULL,2,1,4,NULL),(5,'Jagadeesh','string','string','string','string','2019-08-26 03:53:46','string','2019-08-26 03:59:05','string','Y','string','string',NULL,'string','string','string','string','string','M','2019-08-26','string',NULL,1,1,4,NULL);
/*!40000 ALTER TABLE `provider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provider_type_lookup`
--

DROP TABLE IF EXISTS `provider_type_lookup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `provider_type_lookup` (
  `PROVIDER_TYPE_LOOKUP_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PROVIDER_TYPE_CD` char(2) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `PROVIDER_TYPE_DESC` varchar(250) DEFAULT NULL,
  `CREATION_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATION_USER_ID` varchar(50) NOT NULL,
  `LAST_UPDATE_TS` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`PROVIDER_TYPE_LOOKUP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provider_type_lookup`
--

LOCK TABLES `provider_type_lookup` WRITE;
/*!40000 ALTER TABLE `provider_type_lookup` DISABLE KEYS */;
INSERT INTO `provider_type_lookup` VALUES (1,'PD','PHYSICIAN','2019-08-21 13:00:00','SYS_ADMIN','2019-08-21 13:00:00',NULL,NULL),(2,'PA','PHYSICIAN ASSISTANT','2019-08-21 13:00:00','SYS_ADMIN','2019-08-21 13:00:00',NULL,NULL);
/*!40000 ALTER TABLE `provider_type_lookup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provinces_lookup`
--

DROP TABLE IF EXISTS `provinces_lookup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `provinces_lookup` (
  `PROVINCES_ID` int(11) NOT NULL AUTO_INCREMENT,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `PROVINCES_NM` varchar(255) DEFAULT NULL,
  `COUNTRY_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`PROVINCES_ID`),
  KEY `FK_PROVINCES_LOOKUP_COUNTRY` (`COUNTRY_ID`),
  CONSTRAINT `FK_PROVINCES_LOOKUP_COUNTRY` FOREIGN KEY (`COUNTRY_ID`) REFERENCES `country_lookup` (`COUNTRY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provinces_lookup`
--

LOCK TABLES `provinces_lookup` WRITE;
/*!40000 ALTER TABLE `provinces_lookup` DISABLE KEYS */;
INSERT INTO `provinces_lookup` VALUES (4,0,'2019-08-23 16:49:17','0','2019-08-23 16:48:53','0','Andhra',1),(5,0,'2019-08-23 16:49:41','0','2019-08-23 16:48:53','0','Telengana',1),(6,0,'2019-08-23 16:50:13','0','2019-08-23 16:48:53','0','Kenya',2);
/*!40000 ALTER TABLE `provinces_lookup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `return_credit_type`
--

DROP TABLE IF EXISTS `return_credit_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `return_credit_type` (
  `RETURN_CREDIT_TYPE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TYPE` varchar(20) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`RETURN_CREDIT_TYPE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `return_credit_type`
--

LOCK TABLES `return_credit_type` WRITE;
/*!40000 ALTER TABLE `return_credit_type` DISABLE KEYS */;
INSERT INTO `return_credit_type` VALUES (1,'CASH',NULL,NULL,NULL,NULL,NULL),(2,'GOODS',NULL,NULL,NULL,NULL,NULL),(3,'CREDIT NOTE',NULL,NULL,NULL,NULL,NULL),(4,'BOTH',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `return_credit_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-26 16:34:49
