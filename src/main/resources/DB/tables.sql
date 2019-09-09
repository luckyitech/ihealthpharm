-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: emedistoresdb
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
-- Table structure for table `country_lookup`
--

DROP TABLE IF EXISTS `country_lookup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country_lookup` (
  `country_id` int(11) NOT NULL AUTO_INCREMENT,
  `audit_id` int(11) DEFAULT NULL,
  `country_code` varchar(255) DEFAULT NULL,
  `country_nm` varchar(255) DEFAULT NULL,
  `creation_user_id` int(11) DEFAULT NULL,
  `creation_ts` datetime DEFAULT NULL,
  `last_update_ts` datetime DEFAULT NULL,
  `last_update_user_id` int(11) DEFAULT NULL,
  `state_alias` int(11) DEFAULT NULL,
  PRIMARY KEY (`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country_lookup`
--

LOCK TABLES `country_lookup` WRITE;
/*!40000 ALTER TABLE `country_lookup` DISABLE KEYS */;
INSERT INTO `country_lookup` VALUES (1,0,'string','string',0,'2019-08-20 20:39:52','2019-08-20 20:39:52',0,0);
/*!40000 ALTER TABLE `country_lookup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `distrubutor`
--

DROP TABLE IF EXISTS `distrubutor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `distrubutor` (
  `distrubutor_id` int(11) NOT NULL AUTO_INCREMENT,
  `late_payment_intrest` int(11) DEFAULT NULL,
  `accept_damaged_returns` varchar(255) DEFAULT NULL,
  `accept_expiry_returns` varchar(255) DEFAULT NULL,
  `accept_good_returns` varchar(255) DEFAULT NULL,
  `active_s` varchar(255) DEFAULT NULL,
  `address_line1` varchar(255) DEFAULT NULL,
  `address_line2` varchar(255) DEFAULT NULL,
  `address_line3` varchar(255) DEFAULT NULL,
  `allow_manual_orders` varchar(255) DEFAULT NULL,
  `allow_online_orders` varchar(255) DEFAULT NULL,
  `allow_phone_orders` varchar(255) DEFAULT NULL,
  `city_nm` varchar(255) DEFAULT NULL,
  `contact_person_email_id` varchar(255) DEFAULT NULL,
  `contact_person_first_name` varchar(255) DEFAULT NULL,
  `contact_person_last_name` varchar(255) DEFAULT NULL,
  `contact_person_middle_name` varchar(255) DEFAULT NULL,
  `contact_person_phone_nbr` varchar(255) DEFAULT NULL,
  `creation_ts` datetime DEFAULT NULL,
  `creation_user_id` varchar(255) DEFAULT NULL,
  `cst_no` varchar(255) DEFAULT NULL,
  `dl_no` varchar(255) DEFAULT NULL,
  `email_id` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `last_update_ts` datetime DEFAULT NULL,
  `last_update_user_id` varchar(255) DEFAULT NULL,
  `license` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `payment_credit_net_days` int(11) DEFAULT NULL,
  `payment_terms` varchar(255) DEFAULT NULL,
  `phone_nbr` varchar(255) DEFAULT NULL,
  `pin_no` varchar(255) DEFAULT NULL,
  `return_credit_type_id` int(11) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `zip_cd` varchar(255) DEFAULT NULL,
  `country_id` int(11) DEFAULT NULL,
  `province_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`distrubutor_id`),
  KEY `FKkyg7rt3axivdxt1jwpavmyhd7` (`country_id`),
  KEY `FKetx0t3rsv7lrtb6g5slqb2t3n` (`province_id`),
  CONSTRAINT `FKetx0t3rsv7lrtb6g5slqb2t3n` FOREIGN KEY (`province_id`) REFERENCES `provinces_lookup` (`province_id`),
  CONSTRAINT `FKkyg7rt3axivdxt1jwpavmyhd7` FOREIGN KEY (`country_id`) REFERENCES `country_lookup` (`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `distrubutor`
--

LOCK TABLES `distrubutor` WRITE;
/*!40000 ALTER TABLE `distrubutor` DISABLE KEYS */;
/*!40000 ALTER TABLE `distrubutor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `employee_id` int(11) NOT NULL AUTO_INCREMENT,
  `address_line1` varchar(255) DEFAULT NULL,
  `address_line2` varchar(255) DEFAULT NULL,
  `address_line3` varchar(255) DEFAULT NULL,
  `biography_desc` varchar(255) DEFAULT NULL,
  `blood_group` varchar(255) DEFAULT NULL,
  `city_nm` varchar(255) DEFAULT NULL,
  `creation_user_id` varchar(255) DEFAULT NULL,
  `creation_ts` datetime DEFAULT NULL,
  `dob_dt` datetime DEFAULT NULL,
  `date_of_joining` datetime DEFAULT NULL,
  `designation_nm` varchar(255) DEFAULT NULL,
  `email_id` varchar(255) DEFAULT NULL,
  `employee_type_full_part_time` varchar(255) DEFAULT NULL,
  `first_nm` varchar(255) DEFAULT NULL,
  `gender_cd` varchar(255) DEFAULT NULL,
  `last_nm` varchar(255) DEFAULT NULL,
  `last_update_ts` datetime DEFAULT NULL,
  `last_update_user_id` varchar(255) DEFAULT NULL,
  `middle_nm` varchar(255) DEFAULT NULL,
  `are_you_work_on_shifts` varchar(255) DEFAULT NULL,
  `pharma_id` int(11) DEFAULT NULL,
  `phone_nbr` varchar(255) DEFAULT NULL,
  `provider_image_path` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `zip_cd` varchar(255) DEFAULT NULL,
  `country_id` int(11) DEFAULT NULL,
  `employee_type_cd` varchar(50) DEFAULT NULL,
  `province_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  KEY `FK1qaoto70q6kh9gbi8tqbim12c` (`country_id`),
  KEY `FKnaiv1fxmd0f2eculjo8f2aoso` (`employee_type_cd`),
  KEY `FKbiqluqa43cmfdv85vcj25ap25` (`province_id`),
  CONSTRAINT `FK1qaoto70q6kh9gbi8tqbim12c` FOREIGN KEY (`country_id`) REFERENCES `country_lookup` (`country_id`),
  CONSTRAINT `FKbiqluqa43cmfdv85vcj25ap25` FOREIGN KEY (`province_id`) REFERENCES `provinces_lookup` (`province_id`),
  CONSTRAINT `FKnaiv1fxmd0f2eculjo8f2aoso` FOREIGN KEY (`employee_type_cd`) REFERENCES `employee_type_lookup` (`employee_type_cd`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (4,'string','string','string','string','string','hyd','string','2019-08-20 20:58:17','2019-08-20 20:39:52','2019-08-20 20:39:52','string','string','string','string','string','string','2019-08-20 20:58:17','string','string','string',0,'string','string','awdesfs','string',1,NULL,1),(5,'string','string','string','string','string','hyd','string','2019-08-20 20:58:17','2019-08-20 20:39:52','2019-08-20 20:39:52','string','string','string','string','string','string','2019-08-20 20:58:17','string','string','string',0,'string','string','awdesfs','string',1,NULL,1),(6,'string','string','string','string','string','hyd','string','2019-08-20 20:58:17','2019-08-20 20:39:52','2019-08-20 20:39:52','string','string','string','string','string','string','2019-08-20 20:58:17','string','string','string',0,'string','string','awdesfs','string',1,NULL,1),(7,'string','string','string','string','string','hyd','string','2019-08-20 20:58:17','2019-08-20 20:39:52','2019-08-20 20:39:52','string','string','string','string','string','string','2019-08-20 20:58:17','string','string','string',0,'string','string','awdesfs','string',1,NULL,1),(8,'string','string','string','string','string','hyd','string','2019-08-20 20:58:17','2019-08-20 20:39:52','2019-08-20 20:39:52','string','string','string','string','string','string','2019-08-20 20:58:17','string','string','string',0,'string','string','awdesfs','string',1,NULL,1),(9,'string','string','string','string','string','hyd','string','2019-08-20 20:58:17','2019-08-20 20:39:52','2019-08-20 20:39:52','string','string','string','string','string','string','2019-08-20 20:58:17','string','string','string',0,'string','string','awdesfs','string',1,NULL,1),(10,'hyd','string','string','string','string','hyd','string','2019-08-20 20:39:52','2019-08-20 20:39:52','2019-08-20 20:39:52','string','string','string','string','string','string','2019-08-20 21:01:12','string','string','string',0,'string','string','awdesfs','string',1,NULL,1),(11,'string','string','string','string','string','hyd','string','2019-08-20 20:58:17','2019-08-20 20:39:52','2019-08-20 20:39:52','string','string','string','string','string','string','2019-08-20 20:58:17','string','string','string',0,'string','string','awdesfs','string',1,NULL,1),(13,'string','string','string','string','string','hyd','string','2019-08-20 20:58:17','2019-08-20 20:39:52','2019-08-20 20:39:52','string','string','string','string','string','string','2019-08-20 20:58:17','string','string','string',0,'string','string','awdesfs','string',1,NULL,1);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_education`
--

DROP TABLE IF EXISTS `employee_education`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_education` (
  `employee_education_id` int(11) NOT NULL AUTO_INCREMENT,
  `creation_ts` datetime DEFAULT NULL,
  `creation_user_id` varchar(255) DEFAULT NULL,
  `degree` varchar(255) DEFAULT NULL,
  `garduation_date` datetime DEFAULT NULL,
  `last_update_ts` datetime DEFAULT NULL,
  `last_update_user_id` varchar(255) DEFAULT NULL,
  `studied_at` varchar(255) DEFAULT NULL,
  `employee_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`employee_education_id`),
  KEY `FK6pm2cgawncfp6nw1ngw484wh4` (`employee_id`),
  CONSTRAINT `FK6pm2cgawncfp6nw1ngw484wh4` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_education`
--

LOCK TABLES `employee_education` WRITE;
/*!40000 ALTER TABLE `employee_education` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_education` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_honor`
--

DROP TABLE IF EXISTS `employee_honor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_honor` (
  `employee_honor_id` int(11) NOT NULL AUTO_INCREMENT,
  `creation_user_id` varchar(255) DEFAULT NULL,
  `creation_ts` datetime DEFAULT NULL,
  `honor_desc` varchar(255) DEFAULT NULL,
  `honor_nm` varchar(255) DEFAULT NULL,
  `last_update_ts` datetime DEFAULT NULL,
  `last_update_user_id` varchar(255) DEFAULT NULL,
  `receive_dt` datetime DEFAULT NULL,
  `employee_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`employee_honor_id`),
  KEY `FK5jlvbhp90t40runobav101aob` (`employee_id`),
  CONSTRAINT `FK5jlvbhp90t40runobav101aob` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_honor`
--

LOCK TABLES `employee_honor` WRITE;
/*!40000 ALTER TABLE `employee_honor` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_honor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_interest`
--

DROP TABLE IF EXISTS `employee_interest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_interest` (
  `employee_interest_id` int(11) NOT NULL AUTO_INCREMENT,
  `area_of_interest_desc` varchar(255) DEFAULT NULL,
  `creation_user_id` varchar(255) DEFAULT NULL,
  `creation_ts` datetime DEFAULT NULL,
  `intrested_at` varchar(255) DEFAULT NULL,
  `last_update_ts` datetime DEFAULT NULL,
  `last_update_user_id` varchar(255) DEFAULT NULL,
  `employee_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`employee_interest_id`),
  KEY `FKoxmb6b94sfj962imqtlt39wxu` (`employee_id`),
  CONSTRAINT `FKoxmb6b94sfj962imqtlt39wxu` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_interest`
--

LOCK TABLES `employee_interest` WRITE;
/*!40000 ALTER TABLE `employee_interest` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_interest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_prof_membership`
--

DROP TABLE IF EXISTS `employee_prof_membership`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_prof_membership` (
  `employee_prof_membership_id` int(11) NOT NULL AUTO_INCREMENT,
  `creation_user_id` varchar(255) DEFAULT NULL,
  `creation_ts` datetime DEFAULT NULL,
  `end_dt` datetime DEFAULT NULL,
  `last_update_ts` datetime DEFAULT NULL,
  `last_update_user_id` varchar(255) DEFAULT NULL,
  `membership_nm` varchar(255) DEFAULT NULL,
  `start_dt` datetime DEFAULT NULL,
  `employee_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`employee_prof_membership_id`),
  KEY `FKk0468wvyt3y7mg204uyj26mhw` (`employee_id`),
  CONSTRAINT `FKk0468wvyt3y7mg204uyj26mhw` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_prof_membership`
--

LOCK TABLES `employee_prof_membership` WRITE;
/*!40000 ALTER TABLE `employee_prof_membership` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_prof_membership` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_publication`
--

DROP TABLE IF EXISTS `employee_publication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_publication` (
  `employee_publication_id` int(11) NOT NULL AUTO_INCREMENT,
  `creation_user_id` varchar(255) DEFAULT NULL,
  `creation_ts` datetime DEFAULT NULL,
  `last_update_ts` datetime DEFAULT NULL,
  `last_update_user_id` varchar(255) DEFAULT NULL,
  `publication_desc` varchar(255) DEFAULT NULL,
  `publication_nm` varchar(255) DEFAULT NULL,
  `publish_dt` datetime DEFAULT NULL,
  `employee_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`employee_publication_id`),
  KEY `FKd2xtgxo1le87iwn7n7wcso8w9` (`employee_id`),
  CONSTRAINT `FKd2xtgxo1le87iwn7n7wcso8w9` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_publication`
--

LOCK TABLES `employee_publication` WRITE;
/*!40000 ALTER TABLE `employee_publication` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_publication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_type_lookup`
--

DROP TABLE IF EXISTS `employee_type_lookup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_type_lookup` (
  `employee_type_cd` varchar(50) NOT NULL,
  `creation_ts` datetime DEFAULT NULL,
  `creation_user_id` varchar(255) DEFAULT NULL,
  `employee_type_desc` varchar(255) DEFAULT NULL,
  `last_update_ts` datetime DEFAULT NULL,
  `last_update_user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`employee_type_cd`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_type_lookup`
--

LOCK TABLES `employee_type_lookup` WRITE;
/*!40000 ALTER TABLE `employee_type_lookup` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_type_lookup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employment_history`
--

DROP TABLE IF EXISTS `employment_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employment_history` (
  `employment_history_id` int(11) NOT NULL AUTO_INCREMENT,
  `reference1` varchar(255) DEFAULT NULL,
  `reference2` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `company_address` varchar(255) DEFAULT NULL,
  `creation_user_id` varchar(255) DEFAULT NULL,
  `creation_ts` datetime DEFAULT NULL,
  `designation` varchar(255) DEFAULT NULL,
  `employment_type` varchar(255) DEFAULT NULL,
  `end_dt` datetime DEFAULT NULL,
  `last_update_ts` datetime DEFAULT NULL,
  `last_update_user_id` varchar(255) DEFAULT NULL,
  `manager_email` varchar(255) DEFAULT NULL,
  `manager_nm` varchar(255) DEFAULT NULL,
  `manager_phone_nbr` varchar(255) DEFAULT NULL,
  `reporting_person_details` varchar(255) DEFAULT NULL,
  `start_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`employment_history_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employment_history`
--

LOCK TABLES `employment_history` WRITE;
/*!40000 ALTER TABLE `employment_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `employment_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hospital`
--

DROP TABLE IF EXISTS `hospital`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hospital` (
  `hospital_id` int(11) NOT NULL AUTO_INCREMENT,
  `address_line1` varchar(250) DEFAULT NULL,
  `address_line2` varchar(250) DEFAULT NULL,
  `address_line3` varchar(250) DEFAULT NULL,
  `audit_id` int(11) DEFAULT NULL,
  `city_nm` varchar(50) DEFAULT NULL,
  `contact_email_id` varchar(255) DEFAULT NULL,
  `contact_first_name` varchar(80) DEFAULT NULL,
  `contact_last_name` varchar(80) DEFAULT NULL,
  `contact_middle_name` varchar(80) DEFAULT NULL,
  `contact_phone_nbr` varchar(255) DEFAULT NULL,
  `creation_user_id` int(11) DEFAULT NULL,
  `creation_ts` datetime DEFAULT NULL,
  `email_id` varchar(255) DEFAULT NULL,
  `fax` varchar(20) DEFAULT NULL,
  `helpline` varchar(50) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `last_update_ts` datetime DEFAULT NULL,
  `last_update_user_id` int(11) DEFAULT NULL,
  `license` varchar(255) DEFAULT NULL,
  `phone_nbr` varchar(255) DEFAULT NULL,
  `active_s` varchar(255) DEFAULT NULL,
  `website` varchar(50) DEFAULT NULL,
  `zip_cd` varchar(10) DEFAULT NULL,
  `country_id` int(11) DEFAULT NULL,
  `state_cd` int(11) DEFAULT NULL,
  PRIMARY KEY (`hospital_id`),
  KEY `FKmev9o8bq05kcjwt4770m2wyqt` (`country_id`),
  KEY `FK4rw7stn0dv67a8awayi8ahumj` (`state_cd`),
  CONSTRAINT `FK4rw7stn0dv67a8awayi8ahumj` FOREIGN KEY (`state_cd`) REFERENCES `provinces_lookup` (`province_id`),
  CONSTRAINT `FKmev9o8bq05kcjwt4770m2wyqt` FOREIGN KEY (`country_id`) REFERENCES `country_lookup` (`country_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hospital`
--

LOCK TABLES `hospital` WRITE;
/*!40000 ALTER TABLE `hospital` DISABLE KEYS */;
/*!40000 ALTER TABLE `hospital` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manufacturer`
--

DROP TABLE IF EXISTS `manufacturer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manufacturer` (
  `manufacturer_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_contact_email_id` varchar(255) DEFAULT NULL,
  `first_contact_first_name` varchar(80) DEFAULT NULL,
  `first_contact_person_last_name` varchar(80) DEFAULT NULL,
  `first_contact_middle_name` varchar(80) DEFAULT NULL,
  `first_contact_phone_nbr` varchar(255) DEFAULT NULL,
  `second_contact_person_email_id` varchar(80) DEFAULT NULL,
  `second_contact_person_first_name` varchar(80) DEFAULT NULL,
  `second_contact_person_last_name` varchar(80) DEFAULT NULL,
  `second_contact_person_middle_name` varchar(80) DEFAULT NULL,
  `second_contact_person_phone_nbr` varchar(80) DEFAULT NULL,
  `active_s` varchar(255) DEFAULT NULL,
  `address_line1` varchar(250) DEFAULT NULL,
  `address_line2` varchar(250) DEFAULT NULL,
  `address_line3` varchar(250) DEFAULT NULL,
  `audit_id` int(11) DEFAULT NULL,
  `city_nm` varchar(50) DEFAULT NULL,
  `creation_ts` datetime DEFAULT NULL,
  `creation_user_id` varchar(255) DEFAULT NULL,
  `email_id` varchar(255) DEFAULT NULL,
  `fax` varchar(20) DEFAULT NULL,
  `last_update_ts` datetime DEFAULT NULL,
  `last_update_user_id` varchar(255) DEFAULT NULL,
  `license` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_nbr` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `zip_cd` varchar(255) DEFAULT NULL,
  `country_id` int(11) DEFAULT NULL,
  `province_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`manufacturer_id`),
  KEY `FK1yypef2866ixcvbc26gswk58r` (`country_id`),
  KEY `FK14lors8ec6fi33gt03dy7f4u6` (`province_id`),
  CONSTRAINT `FK14lors8ec6fi33gt03dy7f4u6` FOREIGN KEY (`province_id`) REFERENCES `provinces_lookup` (`province_id`),
  CONSTRAINT `FK1yypef2866ixcvbc26gswk58r` FOREIGN KEY (`country_id`) REFERENCES `country_lookup` (`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manufacturer`
--

LOCK TABLES `manufacturer` WRITE;
/*!40000 ALTER TABLE `manufacturer` DISABLE KEYS */;
/*!40000 ALTER TABLE `manufacturer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pharmacy`
--

DROP TABLE IF EXISTS `pharmacy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pharmacy` (
  `pharmacy_id` int(11) NOT NULL AUTO_INCREMENT,
  `active_s` varchar(255) DEFAULT NULL,
  `address_line1` varchar(255) DEFAULT NULL,
  `audit_id` int(11) DEFAULT NULL,
  `city_nm` varchar(255) DEFAULT NULL,
  `contact_no` varchar(255) DEFAULT NULL,
  `creation_ts` datetime DEFAULT NULL,
  `creation_user_id` varchar(255) DEFAULT NULL,
  `email_id` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `last_update_ts` datetime DEFAULT NULL,
  `last_update_user_id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_nbr` varchar(255) DEFAULT NULL,
  `state_cd` int(11) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `zip_cd` varchar(255) DEFAULT NULL,
  `country_id` int(11) DEFAULT NULL,
  `province_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`pharmacy_id`),
  KEY `FKjxdkwwuw70enrpm22yke8tdcb` (`country_id`),
  KEY `FKdp3ehy9o99aj3x1gq0lrtb9bw` (`province_id`),
  CONSTRAINT `FKdp3ehy9o99aj3x1gq0lrtb9bw` FOREIGN KEY (`province_id`) REFERENCES `provinces_lookup` (`province_id`),
  CONSTRAINT `FKjxdkwwuw70enrpm22yke8tdcb` FOREIGN KEY (`country_id`) REFERENCES `country_lookup` (`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pharmacy`
--

LOCK TABLES `pharmacy` WRITE;
/*!40000 ALTER TABLE `pharmacy` DISABLE KEYS */;
INSERT INTO `pharmacy` VALUES (1,'Y','string',0,'hyderabadsssssssss','string','2019-08-20 20:58:18','string','string','string','2019-08-20 21:05:47','string','string','string',0,'string','string',1,1);
/*!40000 ALTER TABLE `pharmacy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pharmastockpointtype`
--

DROP TABLE IF EXISTS `pharmastockpointtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pharmastockpointtype` (
  `stock_point_id` int(11) NOT NULL AUTO_INCREMENT,
  `audit_id` int(11) DEFAULT NULL,
  `creation_ts` datetime DEFAULT NULL,
  `creation_user_id` varchar(255) DEFAULT NULL,
  `last_update_ts` datetime DEFAULT NULL,
  `last_update_user_id` varchar(255) DEFAULT NULL,
  `sp_name` varchar(255) DEFAULT NULL,
  `sp_cd` varchar(255) DEFAULT NULL,
  `pharmacy_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`stock_point_id`),
  KEY `FK1wr9h5ij18jcxfeh2025sbn7o` (`pharmacy_id`),
  CONSTRAINT `FK1wr9h5ij18jcxfeh2025sbn7o` FOREIGN KEY (`pharmacy_id`) REFERENCES `pharmacy` (`pharmacy_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pharmastockpointtype`
--

LOCK TABLES `pharmastockpointtype` WRITE;
/*!40000 ALTER TABLE `pharmastockpointtype` DISABLE KEYS */;
/*!40000 ALTER TABLE `pharmastockpointtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provider`
--

DROP TABLE IF EXISTS `provider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `provider` (
  `provider_id` int(11) NOT NULL AUTO_INCREMENT,
  `dea_number` varchar(255) DEFAULT NULL,
  `dob` datetime DEFAULT NULL,
  `last_update_user_id` varchar(255) DEFAULT NULL,
  `active_s` varchar(255) DEFAULT NULL,
  `address_line1` varchar(255) DEFAULT NULL,
  `address_line2` varchar(255) DEFAULT NULL,
  `city_nm` varchar(255) DEFAULT NULL,
  `creation_ts` datetime DEFAULT NULL,
  `creation_user_id` varchar(255) DEFAULT NULL,
  `credit` varchar(255) DEFAULT NULL,
  `email_id` varchar(255) DEFAULT NULL,
  `first_nm` varchar(255) DEFAULT NULL,
  `gender_cd` varchar(255) DEFAULT NULL,
  `last_nm` varchar(255) DEFAULT NULL,
  `last_update_ts` datetime DEFAULT NULL,
  `licence_number` varchar(255) DEFAULT NULL,
  `middle_nm` varchar(255) DEFAULT NULL,
  `phone_nbr` varchar(255) DEFAULT NULL,
  `provider_type_cd` varchar(255) DEFAULT NULL,
  `speciality` varchar(255) DEFAULT NULL,
  `zip_cd` varchar(255) DEFAULT NULL,
  `hospital_id` int(11) DEFAULT NULL,
  `country_id` int(11) DEFAULT NULL,
  `province_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`provider_id`),
  KEY `FK9rnqcrcntvv31rk8fw2h2ih5k` (`hospital_id`),
  KEY `FKngthuvexg5h6jh0cod5xk6yfg` (`country_id`),
  KEY `FKh6v2j30d8taisangy03aljqi3` (`province_id`),
  CONSTRAINT `FK9rnqcrcntvv31rk8fw2h2ih5k` FOREIGN KEY (`hospital_id`) REFERENCES `hospital` (`hospital_id`),
  CONSTRAINT `FKh6v2j30d8taisangy03aljqi3` FOREIGN KEY (`province_id`) REFERENCES `provinces_lookup` (`province_id`),
  CONSTRAINT `FKngthuvexg5h6jh0cod5xk6yfg` FOREIGN KEY (`country_id`) REFERENCES `country_lookup` (`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provider`
--

LOCK TABLES `provider` WRITE;
/*!40000 ALTER TABLE `provider` DISABLE KEYS */;
/*!40000 ALTER TABLE `provider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provinces_lookup`
--

DROP TABLE IF EXISTS `provinces_lookup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `provinces_lookup` (
  `province_id` int(11) NOT NULL AUTO_INCREMENT,
  `audit_id` int(11) DEFAULT NULL,
  `creation_user_id` int(11) DEFAULT NULL,
  `creation_ts` datetime DEFAULT NULL,
  `last_update_ts` datetime DEFAULT NULL,
  `last_update_user_id` int(11) DEFAULT NULL,
  `province_nm` varchar(255) DEFAULT NULL,
  `country_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`province_id`),
  KEY `FKpt40qyn3xwddkn7guucg0677m` (`country_id`),
  CONSTRAINT `FKpt40qyn3xwddkn7guucg0677m` FOREIGN KEY (`country_id`) REFERENCES `country_lookup` (`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provinces_lookup`
--

LOCK TABLES `provinces_lookup` WRITE;
/*!40000 ALTER TABLE `provinces_lookup` DISABLE KEYS */;
INSERT INTO `provinces_lookup` VALUES (1,0,0,'2019-08-20 20:39:52','2019-08-20 20:39:52',0,'Andhra',1);
/*!40000 ALTER TABLE `provinces_lookup` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-20 21:24:01
