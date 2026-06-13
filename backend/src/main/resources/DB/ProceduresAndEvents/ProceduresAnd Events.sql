-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: ihealthpharm
-- ------------------------------------------------------
-- Server version	8.0.14

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
-- Table structure for table `account_payables`
--

DROP TABLE IF EXISTS `account_payables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_payables` (
  `ACCOUNT_PAYABLES_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PAYMENT_NO` varchar(30) NOT NULL,
  `PAYMENT_DATE` date DEFAULT NULL,
  `SUPPLIER_ID` int(11) DEFAULT NULL,
  `STATUS` varchar(20) NOT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` int(11) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `PHARMACY_ID` int(11) NOT NULL,
  `TOTAL_INVOICE_AMOUNT` float DEFAULT NULL,
  `TOTAL_ADVANCE_AMOUNT` float DEFAULT NULL,
  `TOTAL_DEBIT_AMOUNT` float DEFAULT NULL,
  `TOTAL_CREDIT_AMOUNT` float DEFAULT NULL,
  `TOTAL_AMOUNT_TO_BE_PAID` float DEFAULT NULL,
  `CASH_AMOUNT` float DEFAULT NULL,
  `CREDIT_CARD_AMOUNT` float DEFAULT NULL,
  `CREDIT_CARD_NO` varchar(20) DEFAULT NULL,
  `UPI_PHONE_NO` varchar(20) DEFAULT NULL,
  `UPI_AMOUNT` float DEFAULT NULL,
  `CHEQUE_NUMBER` varchar(20) DEFAULT NULL,
  `CHEQUE_AMT` float DEFAULT NULL,
  `PAYMENT_STATUS` varchar(20) DEFAULT NULL,
  `SOURCE_ID` varchar(11) DEFAULT NULL,
  `APPROVED_BY` int(11) DEFAULT NULL,
  `SOURCE_REF` varchar(30) DEFAULT NULL,
  `APPROVED_DATE` date DEFAULT NULL,
  `SOURCE_TYPE` varchar(30) DEFAULT NULL,
  `CUSTOMER_NAME` varchar(100) DEFAULT NULL,
  `SUPPLIER_NAME` varchar(100) DEFAULT NULL,
  `AUTH_CODE` varchar(50) DEFAULT NULL,
  `CHEQUE_DATE` date DEFAULT NULL,
  `TOTAL_AMOUNT_PAID` float DEFAULT NULL,
  `ACTIVE_S` char(1) DEFAULT NULL,
  `INVOICE_NO` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ACCOUNT_PAYABLES_ID`),
  KEY `FK_ACCOUNT_PAYABLES_SUPPLIER` (`SUPPLIER_ID`),
  KEY `FK_ACCOUNT_PAYABLES_PHARMACY` (`PHARMACY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1072 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `account_payables_invoices`
--

DROP TABLE IF EXISTS `account_payables_invoices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_payables_invoices` (
  `ACCOUNT_PAYABLES_INVOICES_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ACCOUNT_PAYABLES_ID` int(11) DEFAULT NULL,
  `INVOICE_NUMBER` float NOT NULL,
  `INVOICE_DATE` date DEFAULT NULL,
  `INVOICE_AMOUNT` float NOT NULL,
  `CREDIT_NOTE_AMOUNT` float NOT NULL,
  `DEBIT_NOTE_AMOUNT` float NOT NULL,
  `ADVANCE` float NOT NULL,
  `AMOUNT_TO_BE_PAID` float NOT NULL,
  `REMARKS` varchar(50) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `PHARMACY_ID` int(11) NOT NULL,
  `SUPPLIER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ACCOUNT_PAYABLES_INVOICES_ID`),
  KEY `FK_ACCOUNT_PAYABLES_INVOICES_ACCOUNT_PAYABLES` (`ACCOUNT_PAYABLES_ID`),
  KEY `FK_ACCOUNT_PAYABLES_INVOICES_PHARMACY` (`PHARMACY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `account_payables_working`
--

DROP TABLE IF EXISTS `account_payables_working`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_payables_working` (
  `SUPPLIER_NAME` varchar(100) DEFAULT NULL,
  `PAYMENT_NO` varchar(30) DEFAULT NULL,
  `SOURCE_REF` varchar(30) DEFAULT NULL,
  `INVOICE_NO` varchar(20) DEFAULT NULL,
  `PAYMENT_DATE` date DEFAULT NULL,
  `STATUS` varchar(20) DEFAULT NULL,
  `TOTAL_AMOUNT_PAID` float DEFAULT NULL,
  `TOTAL_AMOUNT_TO_BE_PAID` float DEFAULT NULL,
  `PAYMENT_STATUS` varchar(20) DEFAULT NULL,
  `SOURCE_TYPE` varchar(30) DEFAULT NULL,
  `APPROVED_BY` int(11) DEFAULT NULL,
  `FROM_APPROVED_DATE` date DEFAULT NULL,
  `TO_APPROVED_DATE` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `account_receivables`
--

DROP TABLE IF EXISTS `account_receivables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_receivables` (
  `ACCOUNT_RECEIVABLES_ID` int(11) NOT NULL AUTO_INCREMENT,
  `RECEIPT_NO` varchar(30) NOT NULL,
  `RECEIPT_DATE` date DEFAULT NULL,
  `PAYMENT_TYPE_ID` int(11) DEFAULT NULL,
  `STATUS` varchar(20) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `PHARMACY_ID` int(11) NOT NULL,
  `UPI_PHONE_NO` varchar(20) DEFAULT NULL,
  `UPI_AMOUNT` float DEFAULT NULL,
  `CASH_AMOUNT` float DEFAULT NULL,
  `CREDIT_DAYS` int(11) DEFAULT NULL,
  `CHEQUE_NUMBER` int(11) DEFAULT NULL,
  `CHEQUE_AMT` float DEFAULT NULL,
  `AMOUNT_TO_BE_RECEIVED` float DEFAULT NULL,
  `CREDIT_CARD_AMOUNT` float DEFAULT NULL,
  `CREDIT_CARD_NO` varchar(25) DEFAULT NULL,
  `PAYMENT_STATUS` varchar(20) DEFAULT NULL,
  `SOURCE_ID` int(11) DEFAULT NULL,
  `SOURCE_TYPE` varchar(30) DEFAULT NULL,
  `APPROVED_DATE` date DEFAULT NULL,
  `SOURCE_REF` varchar(20) DEFAULT NULL,
  `APPROVED_BY` int(11) DEFAULT NULL,
  `CUSTOMER_NAME` varchar(100) CHARACTER SET armscii8 COLLATE armscii8_general_ci DEFAULT NULL,
  `SUPPLIER_NAME` varchar(100) DEFAULT NULL,
  `AMOUNT_RECEIVED` float DEFAULT NULL,
  `ACTIVE_S` char(1) DEFAULT NULL,
  PRIMARY KEY (`ACCOUNT_RECEIVABLES_ID`),
  KEY `FK_ACCOUNT_RECEIVABLES_PHARMACY` (`PHARMACY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=110833 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `account_receivables_bills`
--

DROP TABLE IF EXISTS `account_receivables_bills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_receivables_bills` (
  `ACCOUNT_RECEIVABLES_BILLS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ACCOUNT_RECEIVABLES_ID` int(11) DEFAULT NULL,
  `BILL_NUMBER` float NOT NULL,
  `BILL_DATE` date DEFAULT NULL,
  `BILL_AMOUNT` float NOT NULL,
  `CREDIT_NOTE_AMOUNT` float NOT NULL,
  `DEBIT_NOTE_AMOUNT` float NOT NULL,
  `ADVANCE` float NOT NULL,
  `REMARKS` varchar(50) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `PHARMACY_ID` int(11) NOT NULL,
  PRIMARY KEY (`ACCOUNT_RECEIVABLES_BILLS_ID`),
  KEY `FK_ACCOUNT_RECEIVABLES_BILLS_ACCOUNT_RECEIVABLES` (`ACCOUNT_RECEIVABLES_ID`),
  KEY `FK_ACCOUNT_RECEIVABLES_BILLS_PHARMACY` (`PHARMACY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `account_receivables_working`
--

DROP TABLE IF EXISTS `account_receivables_working`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_receivables_working` (
  `CUSTOMER_NAME` varchar(100) DEFAULT NULL,
  `RECEIPT_NO` varchar(30) DEFAULT NULL,
  `SOURCE_REF` varchar(20) DEFAULT NULL,
  `RECEIPT_DATE` date DEFAULT NULL,
  `STATUS` varchar(20) DEFAULT NULL,
  `AMOUNT_RECEIVED` float DEFAULT NULL,
  `AMOUNT_TO_BE_RECEIVED` float DEFAULT NULL,
  `PAYMENT_STATUS` varchar(20) DEFAULT NULL,
  `SOURCE_TYPE` varchar(30) DEFAULT NULL,
  `APPROVED_BY` int(11) DEFAULT NULL,
  `FROM_APPROVED_DATE` date DEFAULT NULL,
  `TO_APPROVED_DATE` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `account_type`
--

DROP TABLE IF EXISTS `account_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_type` (
  `ACCOUNT_TYPE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ACCOUNT_TYPE` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ACCOUNT_TYPE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bank_transactions`
--

DROP TABLE IF EXISTS `bank_transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bank_transactions` (
  `BANK_TXN_ID` int(11) NOT NULL AUTO_INCREMENT,
  `BANK_ACCOUNT` varchar(20) DEFAULT NULL,
  `BANK_NAME` varchar(20) DEFAULT NULL,
  `TRANSACTION_DATE` date DEFAULT NULL,
  `AMOUNT` double DEFAULT NULL,
  `BALANCE` double DEFAULT NULL,
  `TRANSACTION_REF` varchar(20) DEFAULT NULL,
  `TRANSACTION_TYPE` varchar(20) DEFAULT NULL,
  `VALUE_DATE` date DEFAULT NULL,
  `REASON` varchar(100) DEFAULT NULL,
  `MODE` varchar(20) DEFAULT NULL,
  `FLAG` char(1) DEFAULT NULL,
  `DONE_BY` int(11) DEFAULT NULL,
  `DONE_TIMESTAMP` date DEFAULT NULL,
  `CREATION_USER_ID` int(11) DEFAULT NULL,
  `CREATION_TS` date DEFAULT NULL,
  `LAST_UPDATE_USER_ID` int(11) DEFAULT NULL,
  `LAST_UPDATE_TS` date DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `CHEQUE_NO` varchar(20) DEFAULT NULL,
  `PARTY` int(11) DEFAULT NULL,
  `COUNTER_PARTY` int(11) DEFAULT NULL,
  `CARD_NO` varchar(20) DEFAULT NULL,
  `TRANSACTION_ID` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`BANK_TXN_ID`),
  KEY `ihealth_creation_user_id_idx` (`CREATION_USER_ID`),
  KEY `ihealth_last_update_user_id_idx` (`LAST_UPDATE_USER_ID`),
  KEY `ihealth_audit_id_idx` (`AUDIT_ID`),
  KEY `ihealth_done_id_bank_transactions` (`DONE_BY`),
  KEY `ihealth_bank_account_idx` (`BANK_NAME`),
  KEY `ihealth_bank_account_idx1` (`BANK_ACCOUNT`),
  KEY `ihealth_party_bank_transactions_idx` (`PARTY`),
  KEY `ihealth_counter_party_bank_transactions_idx` (`COUNTER_PARTY`),
  CONSTRAINT `ihealth_audit_id_bank_transactions` FOREIGN KEY (`AUDIT_ID`) REFERENCES `employee` (`EMPLOYEE_ID`),
  CONSTRAINT `ihealth_counter_party_bank_transactions` FOREIGN KEY (`COUNTER_PARTY`) REFERENCES `chart_of_accounts` (`ACCOUNT_ID`),
  CONSTRAINT `ihealth_creation_user_id_bank_transactions` FOREIGN KEY (`CREATION_USER_ID`) REFERENCES `employee` (`EMPLOYEE_ID`),
  CONSTRAINT `ihealth_done_id_bank_transactions` FOREIGN KEY (`DONE_BY`) REFERENCES `employee` (`EMPLOYEE_ID`),
  CONSTRAINT `ihealth_last_update_user_id_bank_transactions` FOREIGN KEY (`LAST_UPDATE_USER_ID`) REFERENCES `employee` (`EMPLOYEE_ID`),
  CONSTRAINT `ihealth_party_bank_transactions` FOREIGN KEY (`PARTY`) REFERENCES `chart_of_accounts` (`ACCOUNT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bank_transactions_working`
--

DROP TABLE IF EXISTS `bank_transactions_working`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bank_transactions_working` (
  `TRANSACTION_REF` varchar(20) DEFAULT NULL,
  `FROM_TRANSACTION_DATE` date DEFAULT NULL,
  `TO_TRANSACTION_DATE` date DEFAULT NULL,
  `ACCOUNT_NO_PARTY` varchar(45) DEFAULT NULL,
  `ACCOUNT_NAME_PARTY` varchar(45) DEFAULT NULL,
  `PARTY` varchar(50) DEFAULT NULL,
  `MODE` varchar(20) DEFAULT NULL,
  `TRANSACTION_TYPE` varchar(20) DEFAULT NULL,
  `AMOUNT` double DEFAULT NULL,
  `BALANCE` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `chart_of_accounts`
--

DROP TABLE IF EXISTS `chart_of_accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chart_of_accounts` (
  `ACCOUNT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ACCOUNT_NO` varchar(45) DEFAULT NULL,
  `ACCOUNT_NAME` varchar(45) DEFAULT NULL,
  `ACCOUNT_TYPE` int(11) DEFAULT NULL,
  `TRANSACTION_LIMIT` double DEFAULT NULL,
  `TOTAL_LIMIT` double DEFAULT NULL,
  `CREATION_USER_ID` int(11) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` int(11) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `DATE` date DEFAULT NULL,
  `CURRENT_BALANCE` double DEFAULT NULL,
  `AS_OF_DATE` date DEFAULT NULL,
  `PHARMACY_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ACCOUNT_ID`),
  UNIQUE KEY `ACCOUNT_NO_UNIQUE` (`ACCOUNT_NO`),
  KEY `ihealth_creation_user_id_idx` (`CREATION_USER_ID`),
  KEY `ihealth_char_of_accounts_creation_user_id_idx` (`LAST_UPDATE_USER_ID`),
  KEY `ihealth_chart_of_accounts_account_type_idx` (`ACCOUNT_TYPE`),
  KEY `ihealth_coa_pharmacy_id_idx` (`PHARMACY_ID`),
  CONSTRAINT `ihealth_chart_of_accounts_account_type` FOREIGN KEY (`ACCOUNT_TYPE`) REFERENCES `account_type` (`ACCOUNT_TYPE_ID`),
  CONSTRAINT `ihealth_coa_pharmacy_id` FOREIGN KEY (`PHARMACY_ID`) REFERENCES `pharmacy` (`PHARMACY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `checklist`
--

DROP TABLE IF EXISTS `checklist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `checklist` (
  `CHECKLIST_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SNO` int(20) DEFAULT NULL,
  `TITLE` varchar(100) NOT NULL,
  `ASSIGNED_TO` varchar(50) DEFAULT NULL,
  `ASSIGNED_BY` varchar(50) DEFAULT NULL,
  `TARGET_DATE` date DEFAULT NULL,
  `STATUS` varchar(50) DEFAULT NULL,
  `DONE_BY` varchar(50) DEFAULT NULL,
  `DONE_DATE_TS` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `CHECKLIST_NAME` varchar(150) NOT NULL,
  `TARGET_DATE_TS` varchar(20) DEFAULT NULL,
  `ASSIGNED_DATE` date DEFAULT NULL,
  PRIMARY KEY (`CHECKLIST_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=173 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `CITY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CITY_NAME` varchar(30) NOT NULL,
  `PROVINCES_ID` int(11) NOT NULL,
  PRIMARY KEY (`CITY_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=2322 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `CREATION_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`COUNTRY_ID`),
  KEY `ihp_country_lookup_idx` (`COUNTRY_NM`)
) ENGINE=MyISAM AUTO_INCREMENT=250 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `credit_note`
--

DROP TABLE IF EXISTS `credit_note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `credit_note` (
  `credit_note_id` int(11) NOT NULL AUTO_INCREMENT,
  `credit_note_no` varchar(20) NOT NULL,
  `credit_date` date DEFAULT NULL,
  `invoice_id` varchar(20) DEFAULT NULL,
  `bill_id` varchar(20) DEFAULT NULL,
  `amount` float NOT NULL,
  `remarks` varchar(200) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `PHARMACY_ID` int(11) DEFAULT NULL,
  `ACTIVE_S` char(1) DEFAULT 'Y',
  `return_type` varchar(20) DEFAULT NULL,
  `return_type_reason` varchar(100) DEFAULT NULL,
  `CUSTOMER_ID` int(11) DEFAULT NULL,
  `SUPPLIER_ID` int(11) DEFAULT NULL,
  `APPROVED_BY` int(11) DEFAULT NULL,
  `APPROVED_DATE` date DEFAULT NULL,
  `STATUS` varchar(20) DEFAULT NULL,
  `PAYMENT_TYPE_ID` int(11) DEFAULT NULL,
  `TAX` double DEFAULT NULL,
  `DISCOUNT` double DEFAULT NULL,
  `NET_AMOUNT` float DEFAULT NULL,
  PRIMARY KEY (`credit_note_id`),
  KEY `FK_CREDIT_NOTE_PHARMACY_ID` (`PHARMACY_ID`),
  KEY `FK_CREDIT_NOTE_CUSTOMER_idx` (`CUSTOMER_ID`),
  KEY `FK_CREDIT_NOTE_SUPPLIER_idx` (`SUPPLIER_ID`),
  KEY `FK_CREDIT_NOTE_EMPLOYEE_idx` (`APPROVED_BY`),
  CONSTRAINT `FK_CREDIT_NOTE_CUSTOMER` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`),
  CONSTRAINT `FK_CREDIT_NOTE_EMPLOYEE` FOREIGN KEY (`APPROVED_BY`) REFERENCES `employee` (`EMPLOYEE_ID`),
  CONSTRAINT `FK_CREDIT_NOTE_SUPPLIER` FOREIGN KEY (`SUPPLIER_ID`) REFERENCES `supplier` (`SUPPLIER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `CUSTOMER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PHONE_NBR` varchar(20) NOT NULL,
  `FIRST_NM` varchar(50) NOT NULL,
  `LAST_NM` varchar(50) DEFAULT NULL,
  `GENDER_CD` char(1) DEFAULT NULL,
  `DOB_DT` date DEFAULT NULL,
  `EMAIL_ID` varchar(50) DEFAULT NULL,
  `ADDRESS_LINE1` varchar(250) DEFAULT NULL,
  `ADDRESS_LINE2` varchar(250) DEFAULT NULL,
  `CITY_NM` varchar(50) DEFAULT NULL,
  `PIN_CD` varchar(20) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `ACTIVE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `COUNTRY_ID` int(11) DEFAULT NULL,
  `PROVINCES_ID` int(11) DEFAULT NULL,
  `CREDIT_LIMIT` float DEFAULT NULL,
  `DISCOUNT_PERCENTAGE` float DEFAULT NULL,
  `DISCOUNT_AMOUNT` float DEFAULT NULL,
  `CREDIT_DAYS` float DEFAULT NULL,
  `ORGANISATION` varchar(50) DEFAULT NULL,
  `PHARMACY_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`CUSTOMER_ID`),
  KEY `FK_CUSTOMER_COUNTRY_LOOKUP` (`COUNTRY_ID`),
  KEY `FK_CUSTOMER_PROVINCES_LOOKUP` (`PROVINCES_ID`),
  KEY `ihp_customer` (`FIRST_NM`),
  KEY `ihp_customer_id_idx` (`CUSTOMER_ID`),
  KEY `ihp_customer_first_nm_idx` (`FIRST_NM`)
) ENGINE=InnoDB AUTO_INCREMENT=7323 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `customer_insurance`
--

DROP TABLE IF EXISTS `customer_insurance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_insurance` (
  `CUSTOMER_INSURANCE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CUSTOMER_ID` int(11) DEFAULT NULL,
  `INSURANCE_POLICY_ID` int(11) DEFAULT NULL,
  `INSURANCE_POLICY_CODE` varchar(100) DEFAULT NULL,
  `INSURANCE_POLICY_START` date DEFAULT NULL,
  `INSURANCE_POLICY_END` date DEFAULT NULL,
  `INSURANCE_DURATION_IN_MONTHS` int(11) DEFAULT NULL,
  `INSURANCE_CONTRIBUTION_PERCENTAGE` float DEFAULT NULL,
  `INSURANCE_AMOUNT_LIMIT` float DEFAULT NULL,
  `CUSTOMER_POLICY_NO` varchar(100) DEFAULT NULL,
  `CUSTOMER_NAME` varchar(50) DEFAULT NULL,
  `PHARMACY_ID` int(11) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `ACTIVE_S` char(1) DEFAULT 'Y',
  PRIMARY KEY (`CUSTOMER_INSURANCE_ID`),
  KEY `FK_CUSTOMER_INSURANCE` (`INSURANCE_POLICY_ID`),
  KEY `FK_CUSINS_CUSTOMER` (`CUSTOMER_ID`),
  KEY `FK_CUSTOMER_INSURANCE_PHARMACY_idx` (`PHARMACY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8714 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `customer_membership`
--

DROP TABLE IF EXISTS `customer_membership`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_membership` (
  `CUSTOMER_MEMBERSHIP_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CUSTOMER_ID` int(11) DEFAULT NULL,
  `MEMBERSHIP_CARD_ID` int(11) DEFAULT NULL,
  `MEMBERSHIP_CARD_NAME` varchar(50) DEFAULT NULL,
  `MEMBERSHIP_START_DATE` date DEFAULT NULL,
  `MEMBERSHIP_END_DATE` date DEFAULT NULL,
  `MEMBERSHIP_DURATION_IN_MONTHS` int(11) DEFAULT NULL,
  `MEMBERSHIP_CREDIT_AMOUNT` float DEFAULT NULL,
  `MEMBERSHIP_DISCOUNT_PERCENTAGE` float DEFAULT NULL,
  `MEMBERSHIP_BONUS_PERCENTAGE` float DEFAULT NULL,
  `MEMBERSHIP_CREDIT_DAYS` float DEFAULT NULL,
  `MEMBERSHIP_CARD_NO` varchar(100) DEFAULT NULL,
  `CUSTOMER_NAME` varchar(50) DEFAULT NULL,
  `PHARMACY_ID` int(11) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `ACTIVE_S` char(1) DEFAULT NULL,
  PRIMARY KEY (`CUSTOMER_MEMBERSHIP_ID`),
  KEY `FK_CUSTOMER_MEMBERSHIP_MEMBERSHIP` (`MEMBERSHIP_CARD_ID`),
  KEY `FK_CUSMEM_CUSTOMER` (`CUSTOMER_ID`),
  KEY `FK_CUSTOMER_MEMBERSHIP_PHARMACY_idx` (`PHARMACY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8337 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `customer_prescription_images`
--

DROP TABLE IF EXISTS `customer_prescription_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_prescription_images` (
  `CUSTOMER_PRESCRIPTION_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PRESCRIPTION_NAME` varchar(50) DEFAULT NULL,
  `PRESCRIPTION_IMAGE` blob,
  `PRESCRIPTION_DATE` date DEFAULT NULL,
  `UPLOADED_DATE` date DEFAULT NULL,
  `BILL_NO` int(11) DEFAULT NULL,
  `PHARMACY_ID` int(11) DEFAULT NULL,
  `CUSTOMER_ID` int(11) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `ACTIVE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  PRIMARY KEY (`CUSTOMER_PRESCRIPTION_ID`),
  KEY `FK_PRESCRIPTION_PHARMACY` (`PHARMACY_ID`),
  KEY `FK_PRESCRIPTION_CUSTOMER` (`CUSTOMER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `debit_note`
--

DROP TABLE IF EXISTS `debit_note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `debit_note` (
  `debit_note_id` int(11) NOT NULL AUTO_INCREMENT,
  `debit_note_no` varchar(20) NOT NULL,
  `debit_date` date DEFAULT NULL,
  `invoice_id` varchar(20) DEFAULT NULL,
  `bill_id` varchar(20) DEFAULT NULL,
  `amount` float NOT NULL,
  `remarks` varchar(200) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `PHARMACY_ID` int(11) DEFAULT NULL,
  `ACTIVE_S` char(1) DEFAULT 'Y',
  `return_type` varchar(20) DEFAULT NULL,
  `return_type_reason` varchar(100) DEFAULT NULL,
  `SUPPLIER_ID` int(11) DEFAULT NULL,
  `approved_by` varchar(30) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `APPROVED_DATE` date DEFAULT NULL,
  `PAYMENT_TYPE_ID` int(11) DEFAULT NULL,
  `TAX` double DEFAULT NULL,
  `DISCOUNT` double DEFAULT NULL,
  `NET_AMOUNT` float DEFAULT NULL,
  PRIMARY KEY (`debit_note_id`),
  KEY `FK_DEBIT_NOTE_PHARMACY_ID` (`PHARMACY_ID`),
  KEY `FK_DEBIT_NOTE_SUPPLIER_idx` (`SUPPLIER_ID`),
  KEY `FK_DEBIT_NOTE_CUSTOMER_idx` (`customer_id`),
  CONSTRAINT `FK_DEBIT_NOTE_CUSTOMER` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`CUSTOMER_ID`),
  CONSTRAINT `FK_DEBIT_NOTE_SUPPLIER` FOREIGN KEY (`SUPPLIER_ID`) REFERENCES `supplier` (`SUPPLIER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=584 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `delivery_types`
--

DROP TABLE IF EXISTS `delivery_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `delivery_types` (
  `DELIVERY_TYPE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TYPE` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`DELIVERY_TYPE_ID`),
  KEY `ihp_delivery_types_idx` (`TYPE`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dummy5`
--

DROP TABLE IF EXISTS `dummy5`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dummy5` (
  `Multi` int(111) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `POSTAL_CD` varchar(20) DEFAULT NULL,
  `CREATION_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATION_USER_ID` varchar(50) NOT NULL,
  `LAST_UPDATE_TS` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `PROFILE_IMAGE` blob,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `EMPLOYEE_TYPE_LOOKUP_ID` int(11) DEFAULT NULL,
  `PHARMACY_ID` int(11) DEFAULT NULL,
  `SALARY` double DEFAULT NULL,
  `EMPLOYEE_TYPE_FULL_PART_TIME` varchar(50) DEFAULT NULL,
  `COUNTRY_ID` int(11) DEFAULT NULL,
  `PROVINCES_ID` int(11) DEFAULT NULL,
  `ACTIVE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `EMPLOYEE_CD` varchar(20) DEFAULT NULL,
  `KRA_PIN` varchar(20) DEFAULT NULL,
  `IDENTIFICATION_DOCUMENT` blob,
  `NHIF_NO` varchar(20) DEFAULT NULL,
  `POLICE_GOOD_CONDUCT_CERTIFICATE` blob,
  `RESUME` blob,
  `SIGNED_CONTRACT` blob,
  `LANDLINE_NUMBER` varchar(20) DEFAULT NULL,
  `POST_BOX` varchar(20) DEFAULT NULL,
  `IDENTIFICATION_NO` varchar(20) DEFAULT NULL,
  `NSSF_NO` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`EMPLOYEE_ID`),
  KEY `FK_EMPLOYEE_COUNTRY_LOOKUP` (`COUNTRY_ID`),
  KEY `FK_EMPLOYEE_EMPLOYEE_TYPE_LOOKUP` (`EMPLOYEE_TYPE_LOOKUP_ID`),
  KEY `FK_EMPLOYEE_PROVINCES_LOOKUP` (`PROVINCES_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=212 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `employee_access`
--

DROP TABLE IF EXISTS `employee_access`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_access` (
  `EMPLOYEE_ACCESS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `EMPLOYEE_ID` int(11) NOT NULL,
  `PHARMA_ACCESS_ID` int(11) NOT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `ACTIVE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  PRIMARY KEY (`EMPLOYEE_ACCESS_ID`),
  KEY `FK_EMPLOYEE_ACCESS_EMPLOYEE_ID` (`EMPLOYEE_ID`),
  KEY `FK_EMPLOYEE_ACCESS_PHARMA_ACCESS` (`PHARMA_ACCESS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3301 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `employee_credentials`
--

DROP TABLE IF EXISTS `employee_credentials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_credentials` (
  `EMPLOYEE_CREDENTIALS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `EMPLOYEE_ID` int(11) NOT NULL,
  `USER_NM` varchar(50) NOT NULL,
  `CURRENT_PASSWORD` varchar(50) NOT NULL,
  `PREVIOUS_PASSWORD1` varchar(50) DEFAULT NULL,
  `PREVIOUS_PASSWORD2` varchar(50) DEFAULT NULL,
  `ACTIVE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `PASSWORD_STATUS` tinyint(4) DEFAULT '0',
  `APPROVAL_ACCESS_PIN` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`EMPLOYEE_CREDENTIALS_ID`),
  KEY `FK_EMPLOYEE_CREDENTIALS_EMPLOYEE` (`EMPLOYEE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `CERTIFICATE` blob,
  PRIMARY KEY (`EMPLOYEE_EDUCATION_ID`),
  KEY `FK_EMPLOYEE_EDUCATION_PVDR` (`EMPLOYEE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  KEY `FK_EMPLOYEE_HONOR_PVDR` (`EMPLOYEE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `employee_images`
--

DROP TABLE IF EXISTS `employee_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_images` (
  `EMPLOYEE_IMAGE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `IMAGE_DESC` varchar(250) DEFAULT NULL,
  `IMAGE` longblob,
  `EMPLOYEE_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`EMPLOYEE_IMAGE_ID`),
  KEY `FK_EMPLOYEE_ID_idx` (`EMPLOYEE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  KEY `FK_EMPLOYEE_INTEREST_PVDR` (`EMPLOYEE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `employee_pharmacy_role`
--

DROP TABLE IF EXISTS `employee_pharmacy_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_pharmacy_role` (
  `EMPLOYEE_PHARMACY_ROLE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `EMPLOYEE_ID` int(11) NOT NULL,
  `PHARMACY_BRANCH_ID` int(11) NOT NULL,
  `ROLE_ID` int(11) NOT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`EMPLOYEE_PHARMACY_ROLE_ID`),
  KEY `FK_EMPLOYEE_PHARMACY_ROLE_EMPLOYEE` (`EMPLOYEE_ID`),
  KEY `FK_EMPLOYEE_PHARMACY_ROLE_PHARMACY_BRANCH_idx` (`PHARMACY_BRANCH_ID`),
  KEY `FK_EMPLOYEE_PHARMACY_ROLE_ROLES` (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  KEY `FK_EMPLOYEE_PROF_MEMBERSHIP_PVDR` (`EMPLOYEE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  KEY `FK_EMPLOYEE_PUBLICATION_PVDR` (`EMPLOYEE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  KEY `FK_EMPLOYEE_SALARY_EMPLOYEE` (`EMPLOYEE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  PRIMARY KEY (`EMPLOYEE_TYPE_LOOKUP_ID`),
  KEY `ihp_employee_type_lookup_code_idx` (`EMPLOYEE_TYPE_CD`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `MANAGER_EMAIL_ID` varchar(50) DEFAULT NULL,
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
  KEY `FK_EMPLOYMENT_HISTORY_EMPLOYEE` (`EMPLOYEE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `expenses`
--

DROP TABLE IF EXISTS `expenses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `expenses` (
  `EXPENSES_ID` int(11) NOT NULL AUTO_INCREMENT,
  `EXPENSES_REF` varchar(500) DEFAULT NULL,
  `DATE` date DEFAULT NULL,
  `PARTY_NO` int(11) DEFAULT NULL,
  `AMOUNT` double DEFAULT NULL,
  `BALANCE` double DEFAULT NULL,
  `CREATION_USER_ID` int(11) DEFAULT NULL,
  `CREATION_TS` date DEFAULT NULL,
  `LAST_UPDATE_USER_ID` int(11) DEFAULT NULL,
  `LAST_UPDATE_TS` date DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `EXPENSE_NO` varchar(45) DEFAULT NULL,
  `CATEGORIES` varchar(45) DEFAULT NULL,
  `TRANSACTION_ID` varchar(20) DEFAULT NULL,
  `CARD_NO` varchar(20) DEFAULT NULL,
  `CHEQUE_NO` varchar(20) DEFAULT NULL,
  `REASON` varchar(200) DEFAULT NULL,
  `MODE` varchar(20) DEFAULT NULL,
  `AS_OF_DATE` date DEFAULT NULL,
  PRIMARY KEY (`EXPENSES_ID`),
  KEY `ihealth_expenses_party_no_idx` (`PARTY_NO`),
  KEY `ihealth_creation_user_id_idx` (`CREATION_USER_ID`),
  KEY `ihealth_last_modified_user_id_idx` (`LAST_UPDATE_USER_ID`),
  KEY `ihealth_audit_id_idx` (`AUDIT_ID`),
  CONSTRAINT `ihealth_audit_id_expenses` FOREIGN KEY (`AUDIT_ID`) REFERENCES `employee` (`EMPLOYEE_ID`),
  CONSTRAINT `ihealth_creation_user_id_expenses` FOREIGN KEY (`CREATION_USER_ID`) REFERENCES `employee` (`EMPLOYEE_ID`),
  CONSTRAINT `ihealth_expenses_party_no` FOREIGN KEY (`PARTY_NO`) REFERENCES `chart_of_accounts` (`ACCOUNT_ID`),
  CONSTRAINT `ihealth_last_modified_user_id_expenses` FOREIGN KEY (`LAST_UPDATE_USER_ID`) REFERENCES `employee` (`EMPLOYEE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `expiry_profile`
--

DROP TABLE IF EXISTS `expiry_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `expiry_profile` (
  `EXPIRY_PROFILE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `EXPIRY_CD` varchar(20) DEFAULT NULL,
  `EXPIRY_DAYS` int(11) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`EXPIRY_PROFILE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `formulation_instructions`
--

DROP TABLE IF EXISTS `formulation_instructions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `formulation_instructions` (
  `FORMULATION_INSTRUCTION_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FORMULATION_NAME` varchar(100) NOT NULL,
  `INSTRUCTIONS` varchar(255) NOT NULL,
  `ITEM_ID` int(11) DEFAULT NULL,
  `PHARMACY_ID` int(11) DEFAULT NULL,
  `TIMES_IN_A_DAY` int(11) DEFAULT NULL,
  `MORNING` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `AFTER_NOON` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `EVENING` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `BEFORE_BED` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `TIMINGS` int(11) DEFAULT NULL,
  `AFTER_MEAL` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `BEFORE_MEAL` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `ANY_TIME` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `ACTIVE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  PRIMARY KEY (`FORMULATION_INSTRUCTION_ID`),
  KEY `FK_FORMULATION_ITEM` (`ITEM_ID`),
  KEY `FK_FORMULATION_PHARMACY` (`PHARMACY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `general_ledger`
--

DROP TABLE IF EXISTS `general_ledger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `general_ledger` (
  `General_Ledger_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Journal_ID` varchar(20) DEFAULT NULL,
  `Journal_Ref` varchar(20) DEFAULT NULL,
  `Entry_No` varchar(20) DEFAULT NULL,
  `Entry_Type` varchar(100) DEFAULT NULL,
  `Party` varchar(100) DEFAULT NULL,
  `Counter_Party` varchar(100) DEFAULT NULL,
  `Entry_Date` date DEFAULT NULL,
  `Debit` double DEFAULT NULL,
  `Credit` double DEFAULT NULL,
  `Balance` double DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` int(11) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` int(11) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `Pharmacy_ID` int(11) DEFAULT NULL,
  `Invoice_No` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`General_Ledger_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5917 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `general_ledger_working`
--

DROP TABLE IF EXISTS `general_ledger_working`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `general_ledger_working` (
  `Journal_ID` varchar(20) DEFAULT NULL,
  `Journal_Ref` varchar(20) DEFAULT NULL,
  `Entry_No` varchar(20) DEFAULT NULL,
  `Entry_Type` varchar(100) DEFAULT NULL,
  `Party` varchar(100) DEFAULT NULL,
  `Counter_Party` varchar(100) DEFAULT NULL,
  `From_Entry_Date` date DEFAULT NULL,
  `To_Entry_Date` date DEFAULT NULL,
  `Debit` double DEFAULT NULL,
  `Credit` double DEFAULT NULL,
  `Balance` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `generateuniquecode`
--

DROP TABLE IF EXISTS `generateuniquecode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `generateuniquecode` (
  `GENERATE_UNIQUE_CODE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `UNIQUE_CODE_NAME` varchar(50) DEFAULT NULL,
  `UNIQUE_CODE_NUMBER` int(11) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`GENERATE_UNIQUE_CODE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `LAST_UPDATE_TS` timestamp NULL DEFAULT '0000-00-00 00:00:00',
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
  KEY `hospital_name_idx` (`NAME`),
  KEY `ihp_hospital_name_idx` (`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=944 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ihealthpharm.item_temp`
--

DROP TABLE IF EXISTS `ihealthpharm.item_temp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ihealthpharm.item_temp` (
  `ITEM_CD` varchar(20) DEFAULT NULL,
  `ITEM_NM` varchar(100) DEFAULT NULL,
  `RACK_NO` varchar(50) DEFAULT NULL,
  `SHELF_NO` varchar(50) DEFAULT NULL,
  `QTY` int(11) DEFAULT NULL,
  `MEDICAL_OR_NON_MEDICAL` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `indent`
--

DROP TABLE IF EXISTS `indent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `indent` (
  `INDENT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `INDENT_NO` varchar(20) NOT NULL,
  `INDENT_DT` date NOT NULL,
  `REQUEST_DT` date DEFAULT NULL,
  `STATUS` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `REMARKS` varchar(200) DEFAULT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `EMERGENCY` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `MODIFIED_BY` int(11) DEFAULT NULL,
  `MODIFIED_DT` date DEFAULT NULL,
  `APPROVED_BY` int(11) DEFAULT NULL,
  `APPROVED_DT` date DEFAULT NULL,
  `CANCELLED_BY` int(11) DEFAULT NULL,
  `CANCELLED_DT` date DEFAULT NULL,
  `RFQ` varchar(20) DEFAULT NULL,
  `REQ` varchar(20) DEFAULT NULL,
  `SYSTEM_NM` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `REJECTED_REASON` varchar(200) DEFAULT NULL,
  `INDENT_STATUS_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`INDENT_ID`),
  KEY `FK_INDENT_STATUS` (`INDENT_STATUS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `indent_items`
--

DROP TABLE IF EXISTS `indent_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `indent_items` (
  `INDENT_ITEM_ID` int(11) NOT NULL AUTO_INCREMENT,
  `INDENT_ID` int(11) NOT NULL,
  `ITEM_ID` int(11) NOT NULL,
  `QUANTITY` int(11) NOT NULL,
  `REMARKS` varchar(200) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `STATUS` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`INDENT_ITEM_ID`),
  KEY `FK_INDENT_ITEM_INDENT` (`INDENT_ID`),
  KEY `FK_INDENT_ITEM_ITEM` (`ITEM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `indent_status`
--

DROP TABLE IF EXISTS `indent_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `indent_status` (
  `INDENT_STATUS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `STATUS` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`INDENT_STATUS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `insurance`
--

DROP TABLE IF EXISTS `insurance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `insurance` (
  `INSURANCE_POLICY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `POLICY_CD` varchar(100) NOT NULL,
  `POLICY_DESC` varchar(200) NOT NULL,
  `COMPANY_NM` varchar(100) NOT NULL,
  `POLICY_START` date NOT NULL,
  `POLICY_END` date NOT NULL,
  `POLICY_DURATION_IN_MONTHS` int(11) DEFAULT NULL,
  `POLICY_AMOUNT_LIMIT` float NOT NULL,
  `MEDICINES_NOT_COVERED` varchar(1000) DEFAULT NULL,
  `TERMS_AND_CONDITIONS` varchar(250) DEFAULT NULL,
  `TERMS_AND_CONDITIONS_FILE` longblob,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `ACTIVE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `CONTACT_FIRST_NM` varchar(50) DEFAULT NULL,
  `CONTACT_LAST_NM` varchar(50) DEFAULT NULL,
  `CONTACT_PERSON_EMAIL_ID` varchar(50) DEFAULT NULL,
  `CONTACT_PERSON_PHONE_NBR` varchar(20) DEFAULT NULL,
  `ADDRESS_LINE1` varchar(250) DEFAULT NULL,
  `ADDRESS_LINE2` varchar(250) DEFAULT NULL,
  `CITY_NM` varchar(50) DEFAULT NULL,
  `ZIP_CD` varchar(10) DEFAULT NULL,
  `COUNTRY_ID` int(11) DEFAULT NULL,
  `PROVINCES_ID` int(11) DEFAULT NULL,
  `EMAIL_ID` varchar(50) DEFAULT NULL,
  `CONTACT_NBR` varchar(20) DEFAULT NULL,
  `PHARMACY_ID` int(11) DEFAULT NULL,
  `CONTRIBUTION_PERCENTAGE` float DEFAULT NULL,
  PRIMARY KEY (`INSURANCE_POLICY_ID`),
  KEY `FK_INSURANCE_COUNTRY_LOOKUP` (`COUNTRY_ID`),
  KEY `FK_INSURANCE_PROVINCES_LOOKUP` (`PROVINCES_ID`),
  KEY `FK_INSURANCE_PHARMACY` (`PHARMACY_ID`),
  KEY `ihp_insurance_policy_code_idx` (`POLICY_CD`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice` (
  `INVOICE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `INVOICE_NO` varchar(20) DEFAULT NULL,
  `SUPPLIER_ID` int(11) DEFAULT NULL,
  `INVOICE_DT` date DEFAULT NULL,
  `REMARKS` varchar(200) DEFAULT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `CREATION_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `APPROVED_BY` int(11) DEFAULT NULL,
  `APPROVED_DT` date DEFAULT NULL,
  `MODIFIED_BY` int(11) DEFAULT NULL,
  `MODIFIED_DT` date DEFAULT NULL,
  `PHARMACY_ID` int(11) DEFAULT NULL,
  `PAYMENT_TYPE_ID` int(11) DEFAULT NULL,
  `CREDIT_DAYS` int(11) DEFAULT NULL,
  `INVOICE_STATUS_ID` int(11) DEFAULT NULL,
  `INVOICE` blob,
  `INVOICE_AMOUNT` double DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `INVOICE_ACTUAL_AMOUNT` double DEFAULT NULL,
  `MEDICAL_OR_NON_MEDICAL` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `PAID_AMOUNT` double DEFAULT NULL,
  `BALANCE` double DEFAULT NULL,
  `TAX_AMOUNT` double DEFAULT NULL,
  `DISCOUNT_PERCENTAGE` double DEFAULT NULL,
  `DISCOUNT` double DEFAULT NULL,
  `ADVANCE` double DEFAULT NULL,
  `HANDLING_CHARGES` double DEFAULT NULL,
  `TAX_PERCENTAGE` double DEFAULT NULL,
  `PURCHASE_TAX_AMOUNT` double DEFAULT NULL,
  `GRN_NO` varchar(20) DEFAULT NULL,
  `ROUND_OFF` double DEFAULT NULL,
  `PARCEL_NO` varchar(50) DEFAULT NULL,
  `BROUGHT_BY` varchar(50) DEFAULT NULL,
  `TOTAL_REJECTS` int(11) DEFAULT NULL,
  `DELIVERY_TYPE_ID` int(11) DEFAULT NULL,
  `REJECTED_REASON` varchar(200) DEFAULT NULL,
  `INVOICE_DESC` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`INVOICE_ID`),
  KEY `FK_PURCHASE_INVOICE_APPROVED_BY` (`APPROVED_BY`),
  KEY `FK_PURCHASE_INVOICE_CREATED_BY` (`CREATED_BY`),
  KEY `FK_PURCHASE_INVOICE_SUPPLIER` (`SUPPLIER_ID`),
  KEY `FK_PURCHASE_INVOICE_MODIFIED_BY` (`MODIFIED_BY`),
  KEY `FK_PURCHASE_INVOICE_PAYMENT_TYPE` (`PAYMENT_TYPE_ID`),
  KEY `FK_PURCHASE_INVOICE_PHARMACY` (`PHARMACY_ID`),
  KEY `FK_PURCHASE_INVOICE_STATUS` (`INVOICE_STATUS_ID`),
  KEY `ihp_invoice_date_idx` (`INVOICE_DT`),
  KEY `ihp_invoice_no_idx` (`INVOICE_NO`),
  KEY `ihp_invoice_supplier_id_idx` (`SUPPLIER_ID`),
  KEY `ihp_invoice_discount_idx` (`DISCOUNT`),
  KEY `ihp_invoice_round_off_idx` (`ROUND_OFF`),
  KEY `ihp_invoice_amount_idx` (`INVOICE_AMOUNT`),
  KEY `ihp_invoice_handling_charges_idx` (`HANDLING_CHARGES`),
  KEY `ihp_invoice_discount_per_idx` (`DISCOUNT_PERCENTAGE`),
  KEY `ihp_invoice_del_type_id_idx` (`DELIVERY_TYPE_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=6773 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `invoice_item_status`
--

DROP TABLE IF EXISTS `invoice_item_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice_item_status` (
  `INVOICE_ITEM_STATUS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `STATUS` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`INVOICE_ITEM_STATUS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `invoice_item_status_xref`
--

DROP TABLE IF EXISTS `invoice_item_status_xref`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice_item_status_xref` (
  `INVOICE_ITEM_STATUS_XREF_ID` int(11) NOT NULL AUTO_INCREMENT,
  `INVOICE_ITEM_ID` int(11) NOT NULL,
  `INVOICE_ITEM_STATUS_ID` int(11) NOT NULL,
  `QUANTITY` int(11) NOT NULL,
  `COMMENTS` varchar(200) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`INVOICE_ITEM_STATUS_XREF_ID`),
  KEY `FK_INVOICE_ITEM` (`INVOICE_ITEM_ID`),
  KEY `FK_INVOICE_ITEM_STATUS` (`INVOICE_ITEM_STATUS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `invoice_items`
--

DROP TABLE IF EXISTS `invoice_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice_items` (
  `INVOICE_ITEM_ID` int(11) NOT NULL AUTO_INCREMENT,
  `INVOICE_ID` int(11) DEFAULT NULL,
  `ITEM_ID` int(11) DEFAULT NULL,
  `PURCHASE_ORDER_ID` int(11) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `BATCH_NO` varchar(20) DEFAULT NULL,
  `ORDER_QUANTITY` int(11) DEFAULT NULL,
  `QUANTITY_APPROVED` int(11) DEFAULT NULL,
  `UNIT_RATE` double DEFAULT NULL,
  `UNIT_SALE_RATE` double DEFAULT NULL,
  `MRP` double DEFAULT NULL,
  `BONUS` int(11) DEFAULT NULL,
  `DISCOUNT_PERCENTAGE` double DEFAULT NULL,
  `DISCOUNT` double DEFAULT NULL,
  `TOTAL_VALUE` double DEFAULT NULL,
  `ACTUAL_VALUE` double DEFAULT NULL,
  `MANUFACTURE_DT` date DEFAULT NULL,
  `EXPIRY_DT` date DEFAULT NULL,
  `PACK` int(11) DEFAULT NULL,
  `REMARKS` varchar(200) DEFAULT NULL,
  `PURCHASE_TAX_PERCENTAGE` double DEFAULT NULL,
  `PURCHASE_TAX_AMOUNT` double DEFAULT NULL,
  `SALE_TAX_PERCENTAGE` double DEFAULT NULL,
  `SALE_TAX_AMOUNT` double DEFAULT NULL,
  `TOTAL_QUANTITY` int(11) DEFAULT NULL,
  `TAX` int(11) DEFAULT NULL,
  `SALE_DISCOUNT_PERCENTAGE` double DEFAULT NULL,
  `PACK_PRICE` double DEFAULT NULL,
  PRIMARY KEY (`INVOICE_ITEM_ID`),
  KEY `FK_PURCHASE_INVOICE_ITEMS` (`ITEM_ID`),
  KEY `PURCHASE_INVOICE_ITEMS_PURCHASE_INVOICE` (`INVOICE_ID`),
  KEY `ihp_invoice_items_batch_no_idx` (`BATCH_NO`),
  KEY `ihp_invoice_items_quantity_approved_idx` (`QUANTITY_APPROVED`),
  KEY `ihp_invoice_items_unit_rate_idx` (`UNIT_RATE`),
  KEY `ihp_invoice_items_mrp_idx` (`MRP`),
  KEY `ihp_invoice_items_bonus_idx` (`BONUS`),
  KEY `ihp_invoice_items_total_value_idx` (`TOTAL_VALUE`),
  KEY `ihp_invoice_items_actual_value_idx` (`ACTUAL_VALUE`),
  KEY `ihp_invoice_items_discount_idx` (`DISCOUNT`),
  KEY `ihp_invoice_items_expiry_dt_idx` (`EXPIRY_DT`),
  KEY `ihp_invoice_items_discount_percent_idx` (`DISCOUNT_PERCENTAGE`),
  KEY `ihp_invoice_items_sale_tax_per_idx` (`SALE_TAX_PERCENTAGE`),
  KEY `ihp_invoice_items_sale_tax_amt_idx` (`SALE_TAX_AMOUNT`),
  KEY `fk_invoice_item_tax_idx` (`TAX`),
  KEY `ihp_invoice_items_inv_item_idx` (`INVOICE_ID`,`ITEM_ID`),
  CONSTRAINT `fk_invoice_item_tax` FOREIGN KEY (`TAX`) REFERENCES `taxcategory` (`TAXCATEGORY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6917 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `invoice_items_working`
--

DROP TABLE IF EXISTS `invoice_items_working`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice_items_working` (
  `INVOICE_ITEM_ID` int(11) NOT NULL,
  `INVOICE_ID` int(11) DEFAULT NULL,
  `ITEM_ID` int(11) DEFAULT NULL,
  `PURCHASE_ORDER_ID` int(11) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `BATCH_NO` varchar(20) DEFAULT NULL,
  `ORDER_QUANTITY` int(11) DEFAULT NULL,
  `QUANTITY_APPROVED` int(11) DEFAULT NULL,
  `UNIT_RATE` double DEFAULT NULL,
  `UNIT_SALE_RATE` double DEFAULT NULL,
  `MRP` double DEFAULT NULL,
  `BONUS` int(11) DEFAULT NULL,
  `DISCOUNT_PERCENTAGE` double DEFAULT NULL,
  `DISCOUNT` double DEFAULT NULL,
  `TOTAL_VALUE` double DEFAULT NULL,
  `ACTUAL_VALUE` double DEFAULT NULL,
  `MANUFACTURE_DT` date DEFAULT NULL,
  `EXPIRY_DT` date DEFAULT NULL,
  `PACK` int(11) DEFAULT NULL,
  `REMARKS` varchar(200) DEFAULT NULL,
  `PURCHASE_TAX_PERCENTAGE` double DEFAULT NULL,
  `PURCHASE_TAX_AMOUNT` double DEFAULT NULL,
  `SALE_TAX_PERCENTAGE` double DEFAULT NULL,
  `SALE_TAX_AMOUNT` double DEFAULT NULL,
  `TOTAL_QUANTITY` int(11) DEFAULT NULL,
  `TAX` int(11) DEFAULT NULL,
  `SALE_DISCOUNT_PERCENTAGE` double DEFAULT NULL,
  `PACK_PRICE` double DEFAULT NULL,
  KEY `ihp_invoice_items_working_inv_item_idx` (`INVOICE_ID`,`ITEM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `invoice_status`
--

DROP TABLE IF EXISTS `invoice_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice_status` (
  `INVOICE_STATUS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `STATUS` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`INVOICE_STATUS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `invoice_working`
--

DROP TABLE IF EXISTS `invoice_working`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice_working` (
  `INVOICE_ID` int(11) NOT NULL,
  `INVOICE_NO` varchar(20) DEFAULT NULL,
  `SUPPLIER_ID` int(11) DEFAULT NULL,
  `INVOICE_DT` date DEFAULT NULL,
  `REMARKS` varchar(200) DEFAULT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `CREATION_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `APPROVED_BY` int(11) DEFAULT NULL,
  `APPROVED_DT` date DEFAULT NULL,
  `MODIFIED_BY` int(11) DEFAULT NULL,
  `MODIFIED_DT` date DEFAULT NULL,
  `PHARMACY_ID` int(11) DEFAULT NULL,
  `PAYMENT_TYPE_ID` int(11) DEFAULT NULL,
  `CREDIT_DAYS` int(11) DEFAULT NULL,
  `INVOICE_STATUS_ID` int(11) DEFAULT NULL,
  `INVOICE` blob,
  `INVOICE_AMOUNT` double DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `INVOICE_ACTUAL_AMOUNT` double DEFAULT NULL,
  `MEDICAL_OR_NON_MEDICAL` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `PAID_AMOUNT` double DEFAULT NULL,
  `BALANCE` double DEFAULT NULL,
  `TAX_AMOUNT` double DEFAULT NULL,
  `DISCOUNT_PERCENTAGE` double DEFAULT NULL,
  `DISCOUNT` double DEFAULT NULL,
  `ADVANCE` double DEFAULT NULL,
  `HANDLING_CHARGES` double DEFAULT NULL,
  `TAX_PERCENTAGE` double DEFAULT NULL,
  `PURCHASE_TAX_AMOUNT` double DEFAULT NULL,
  `GRN_NO` varchar(20) DEFAULT NULL,
  `ROUND_OFF` double DEFAULT NULL,
  `PARCEL_NO` varchar(50) DEFAULT NULL,
  `BROUGHT_BY` varchar(50) DEFAULT NULL,
  `TOTAL_REJECTS` int(11) DEFAULT NULL,
  `DELIVERY_TYPE_ID` int(11) DEFAULT NULL,
  `REJECTED_REASON` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`INVOICE_ID`),
  KEY `ihp_invoice_working_inv_date_idx` (`INVOICE_ID`,`INVOICE_DT`)
) ENGINE=MyISAM AUTO_INCREMENT=6225 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `item_alternative`
--

DROP TABLE IF EXISTS `item_alternative`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_alternative` (
  `ITEM_ALTERNATIVE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ITEM_ID` int(11) DEFAULT NULL,
  `ALTERNATIVE_ITEM_ID` int(11) DEFAULT NULL,
  `CREATION_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `ACTIVE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  PRIMARY KEY (`ITEM_ALTERNATIVE_ID`),
  KEY `FK_ITEM_ID_idx` (`ITEM_ID`),
  KEY `FK_ALTERNATIVE_ITEM_ID_idx` (`ALTERNATIVE_ITEM_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `item_instructions_working`
--

DROP TABLE IF EXISTS `item_instructions_working`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_instructions_working` (
  `ITEM_INSTRUCTION_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ITEM_ID` int(11) DEFAULT NULL,
  `FORMULATION_ID` int(11) DEFAULT NULL,
  `ITEM_NM` varchar(50) DEFAULT NULL,
  `INSTRUCTIONS` varchar(255) NOT NULL,
  `ITEM_QTY` int(11) DEFAULT NULL,
  `EXPIRY_DT` date DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CUSTOMER_NM` varchar(80) DEFAULT NULL,
  `CREATION_USER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ITEM_INSTRUCTION_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `item_temp`
--

DROP TABLE IF EXISTS `item_temp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_temp` (
  `ITEM_CD` varchar(50) DEFAULT NULL,
  `ITEM_NM` varchar(50) DEFAULT NULL,
  `RACK_NO` varchar(50) DEFAULT NULL,
  `SHELF_NO` varchar(50) DEFAULT NULL,
  `QTY` int(11) DEFAULT NULL,
  `MEDICAL_OR_NON_MEDICAL` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `item_tmp`
--

DROP TABLE IF EXISTS `item_tmp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_tmp` (
  `ITEM_CD` varchar(50) DEFAULT NULL,
  `ITEM_NM` varchar(50) DEFAULT NULL,
  `RACK_NO` varchar(50) DEFAULT NULL,
  `SHELF_NO` varchar(50) DEFAULT NULL,
  `QTY` int(11) DEFAULT NULL,
  `MEDICAL_OR_NON_MEDICAL` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items` (
  `ITEM_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ITEM_CD` varchar(20) DEFAULT NULL,
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
  `DRUG_DOSE` varchar(50) DEFAULT NULL,
  `IS_ASSET` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `ITEM_USAGE` mediumtext CHARACTER SET latin1 COLLATE latin1_swedish_ci,
  `DRUG_SCHEDULE` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `REORDER_ITEM` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `PURCHASE_UNIT_OF_MEASUREMENT_ID` int(11) DEFAULT NULL,
  `IS_FORMULARY` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'N',
  `SPECIALIZATION_ID` int(11) DEFAULT NULL,
  `TEMPERATURE` varchar(20) DEFAULT NULL,
  `BATCH_NO_REQ` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `REUSABLE` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `IMPORTED` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `HAZARDOUS` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `IS_BILLABLE` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `IS_LOOK_ALIKE` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `IS_SOUND_ALIKE` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `IS_NARCOTIC` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `IS_HIGH_RISK` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `IS_DRUG` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `IS_CRITICAL` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `IS_REVERSE_ANTIBIOTIC` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `IS_NON_REFUNDABLE_ITEM` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `EXPIRY_PROFILE_ID` int(11) DEFAULT NULL,
  `ISSUE_UNIT_OF_MEASUREMENT_ID` int(11) DEFAULT NULL,
  `PACK` varchar(20) DEFAULT NULL,
  `HSN_CODE` varchar(20) DEFAULT NULL,
  `IS_TAX_EXCEMPTED` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `TAX_ID` int(11) DEFAULT NULL,
  `DEFAULT_PO_QTY` int(11) DEFAULT NULL,
  `PO_TERMS` mediumtext CHARACTER SET latin1 COLLATE latin1_swedish_ci,
  `SALE_RATE_FOR_BILLING` varchar(20) DEFAULT NULL,
  `BILLING_MESSAGE` varchar(500) DEFAULT NULL,
  `DIRECTIONS` varchar(500) DEFAULT NULL,
  `INSTRUCTIONS` varchar(500) DEFAULT NULL,
  `EXPIRY_TYPE` varchar(20) DEFAULT NULL,
  `STORAGE_TYPE` varchar(20) DEFAULT NULL,
  `BATCH_NON_BATCH` varchar(20) DEFAULT NULL,
  `SEASONAL_NON_SEASONAL` varchar(20) DEFAULT NULL,
  `DRUG_SHORTAGES` varchar(20) DEFAULT NULL,
  `DRUG_VED` varchar(20) DEFAULT NULL,
  `DRUG_INTERNAL_EXTERNAL_BOTH` varchar(20) DEFAULT NULL,
  `RACK_NO` varchar(20) DEFAULT NULL,
  `SHELF_NO` varchar(20) DEFAULT NULL,
  `REORDER_LEVEL` int(11) DEFAULT NULL,
  `REORDER_QTY` int(11) DEFAULT NULL,
  `ALERT_MESG` varchar(50) DEFAULT NULL,
  `STORAGE` varchar(150) DEFAULT NULL,
  `SCHEDULE_CATEGORY_CODE_ID` int(11) DEFAULT NULL,
  `LATIN_SHORT_CODE_ID` int(11) DEFAULT NULL,
  `PHARMACY_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ITEM_ID`),
  KEY `FK_ITEMS_ITEMS_CATEGORIES` (`ITEM_CATEGORIE_ID`),
  KEY `FK_ITEMS_ITEMS_FORM_ID` (`ITEM_FORM_ID`),
  KEY `FK_ITEMS_ITEMS_GENERIC_NAMES` (`ITEM_GENERIC_NAME_ID`),
  KEY `FK_ITEMS_ITEMS_GROUP_ID` (`ITEM_GROUP_ID`),
  KEY `FK_ITEMS_MANUFACTURER` (`MANUFACTURER_ID`),
  KEY `FK_ITEMS_SPECIALIZATION` (`SPECIALIZATION_ID`),
  KEY `FK_ITEM_EXPIRY_PROFILE` (`EXPIRY_PROFILE_ID`),
  KEY `FK_ITEM_TAX` (`TAX_ID`),
  KEY `FK_ITEM_LATIN_CODE` (`LATIN_SHORT_CODE_ID`),
  KEY `FK_ITEM_SCHEDULE_CATEGORY_CODE` (`SCHEDULE_CATEGORY_CODE_ID`),
  KEY `ihp_items_name` (`ITEM_NM`),
  KEY `ihp_items_code` (`ITEM_CD`),
  KEY `ihp_items_generic_name` (`ITEM_GENERIC_NAME_ID`),
  KEY `ihp_items_form` (`ITEM_FORM_ID`),
  KEY `ihp_items_id` (`ITEM_ID`),
  KEY `ihp_items_medical_nonmedical` (`MEDICAL_OR_NON_MEDICAL`),
  KEY `ihp_sales_ITEM_NM_idx` (`ITEM_NM`),
  KEY `ihp_purchase_order_item_nm_idx` (`ITEM_NM`),
  KEY `ihp_items_idx` (`ITEM_NM`),
  KEY `ihp_item_item_code_idx` (`ITEM_CD`),
  KEY `ihp_item_manufacturer_idx` (`MANUFACTURER_ID`),
  KEY `ihp_item_code_idx` (`ITEM_CD`),
  KEY `ihp_items_name_idx` (`ITEM_NM`),
  KEY `ihp_pouom_items_idx` (`PURCHASE_UNIT_OF_MEASUREMENT_ID`),
  KEY `ihp_item_ctime_idx` (`CREATION_TS`),
  KEY `ihp_item_ltime_idx` (`LAST_UPDATE_TS`),
  KEY `FK_ITEMS_PHARMACY_ID_idx` (`PHARMACY_ID`),
  KEY `ihp_item_pharmacy_idx` (`ITEM_ID`,`PHARMACY_ID`),
  KEY `ihp_items_shelf_rack` (`ITEM_CD`,`ITEM_NM`,`RACK_NO`,`SHELF_NO`),
  CONSTRAINT `FK_ITEMS_PHARMACY_ID` FOREIGN KEY (`PHARMACY_ID`) REFERENCES `pharmacy` (`PHARMACY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7345 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `items_bin`
--

DROP TABLE IF EXISTS `items_bin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items_bin` (
  `ITEM_BIN_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ITEM_ID` int(11) DEFAULT NULL,
  `BIN_NO` varchar(20) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ITEM_BIN_ID`),
  KEY `FK_ITEMS_BIN_ITEM` (`ITEM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `items_categories`
--

DROP TABLE IF EXISTS `items_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items_categories` (
  `ITEM_CATEGORIE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CATEGORIE_CD` varchar(20) DEFAULT NULL,
  `CATEGORIE_NM` varchar(100) DEFAULT NULL,
  `CATEGORIE_DESC` mediumtext CHARACTER SET latin1 COLLATE latin1_swedish_ci,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `ACTIVE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `MEDICAL_OR_NON_MEDICAL` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'M',
  PRIMARY KEY (`ITEM_CATEGORIE_ID`),
  KEY `ihp_items_categories_code_idx` (`CATEGORIE_CD`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `items_forms`
--

DROP TABLE IF EXISTS `items_forms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items_forms` (
  `ITEM_FORM_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FORM_CD` varchar(20) DEFAULT NULL,
  `FORM` varchar(100) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `ACTIVE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `MEDICAL_OR_NON_MEDICAL` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'M',
  PRIMARY KEY (`ITEM_FORM_ID`),
  KEY `ihp_form_id_name` (`ITEM_FORM_ID`,`FORM`),
  KEY `ihp_forms_id_name` (`ITEM_FORM_ID`,`FORM`)
) ENGINE=InnoDB AUTO_INCREMENT=279 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `items_generic_names`
--

DROP TABLE IF EXISTS `items_generic_names`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items_generic_names` (
  `ITEM_GENERIC_NAME_ID` int(11) NOT NULL AUTO_INCREMENT,
  `GENERIC_NAME` varchar(500) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `ACTIVE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `MEDICAL_OR_NON_MEDICAL` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'M',
  `ITEM_GROUP_ID` int(11) DEFAULT NULL,
  `GENERIC_CD` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ITEM_GENERIC_NAME_ID`),
  KEY `FK_ITEM_GENERICS_ITEMS_GROUP` (`ITEM_GROUP_ID`),
  KEY `ihp_item_gen_name_idx` (`ITEM_GENERIC_NAME_ID`,`GENERIC_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=12030 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `items_group`
--

DROP TABLE IF EXISTS `items_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items_group` (
  `ITEM_GROUP_ID` int(11) NOT NULL AUTO_INCREMENT,
  `GROUP_CD` varchar(20) DEFAULT NULL,
  `GROUP_NAME` varchar(100) DEFAULT NULL,
  `GROUP_DESC` mediumtext CHARACTER SET latin1 COLLATE latin1_swedish_ci,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `ACTIVE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `MEDICAL_OR_NON_MEDICAL` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'M',
  PRIMARY KEY (`ITEM_GROUP_ID`),
  KEY `ihp_item_gRP_name_idx` (`ITEM_GROUP_ID`,`GROUP_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `items_prices`
--

DROP TABLE IF EXISTS `items_prices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items_prices` (
  `ITEM_ID` int(11) DEFAULT NULL,
  `ITEM_NM` varchar(250) DEFAULT NULL,
  `INVOICE_DATE` date DEFAULT NULL,
  `INVOICE_REF` varchar(100) DEFAULT NULL,
  `MRP` double DEFAULT NULL,
  `VAT` int(11) DEFAULT NULL,
  `TAX_CATEGORY` varchar(1) DEFAULT NULL,
  `PACK` int(11) DEFAULT NULL,
  `PURCHASE_DISCOUNT_PERCENTAGE` double DEFAULT NULL,
  `PURCHASE_DISCOUNT_AMOUNT` double DEFAULT NULL,
  `UNIT_PURCHASE_RATE` double DEFAULT NULL,
  `SP_VAT` double DEFAULT NULL,
  `INVOICE_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `items_supplier`
--

DROP TABLE IF EXISTS `items_supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items_supplier` (
  `ITEM_SUPPLIER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ITEM_ID` int(11) DEFAULT NULL,
  `SUPPLIER_ID` int(11) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `ACTIVE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `SUPPLIER_PRIORITY` int(11) DEFAULT NULL,
  `UNIT_RATE` double NOT NULL,
  `DISCOUNT_PERCENTAGE` double NOT NULL,
  `VALIDITY` date NOT NULL,
  PRIMARY KEY (`ITEM_SUPPLIER_ID`),
  KEY `FK_ITEMS_SUPPLIER_ITEMS` (`ITEM_ID`),
  KEY `FK_ITEMS_SUPPLIER_SUPPLIER` (`SUPPLIER_ID`),
  KEY `ihp_items_supplier_priority_idx` (`SUPPLIER_PRIORITY`)
) ENGINE=InnoDB AUTO_INCREMENT=6166 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `items_temp`
--

DROP TABLE IF EXISTS `items_temp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items_temp` (
  `ITEM_CD` varchar(50) DEFAULT NULL,
  `ITEM_NM` varchar(50) DEFAULT NULL,
  `RACK_NO` varchar(50) DEFAULT NULL,
  `SHELF_NO` varchar(50) DEFAULT NULL,
  `QTY` int(11) DEFAULT NULL,
  `MEDICAL_OR_NON_MEDICAL` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `latin_short_codes`
--

DROP TABLE IF EXISTS `latin_short_codes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `latin_short_codes` (
  `LATIN_SHORT_CODE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `LATIN_SHORT_CODE` varchar(50) DEFAULT NULL,
  `LATIN_SHORT_CODE_DESC` varchar(100) DEFAULT NULL,
  `ACTIVE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`LATIN_SHORT_CODE_ID`),
  KEY `ihp_latin_short_codes_desc_idx` (`LATIN_SHORT_CODE_DESC`)
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `manufacturer`
--

DROP TABLE IF EXISTS `manufacturer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manufacturer` (
  `MANUFACTURER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `MFR_NAME` varchar(100) DEFAULT NULL,
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
  KEY `ihp_manufacturer_name_idx` (`MFR_NAME`),
  KEY `ihp_mfg_name_lmt_idx` (`MFR_NAME`,`LAST_UPDATE_TS`),
  KEY `ihp_mfg_name_active_idx` (`MFR_NAME`,`ACTIVE_S`)
) ENGINE=InnoDB AUTO_INCREMENT=4821 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `membership`
--

DROP TABLE IF EXISTS `membership`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `membership` (
  `MEMBERSHIP_CARD_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PHARMACY_ID` int(11) DEFAULT NULL,
  `MEMBERSHIP_CARD_NAME` varchar(100) NOT NULL,
  `BONUS_PERCENTAGE` float DEFAULT NULL,
  `DISCOUNT_PERCENTAGE` float DEFAULT NULL,
  `CREDIT_AMOUNT` float DEFAULT NULL,
  `CREDIT_DAYS` float DEFAULT NULL,
  `OFFERS` varchar(100) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `ACTIVE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `START_DATE` date DEFAULT NULL,
  `END_DATE` date DEFAULT NULL,
  `DURATION_IN_MONTHS` int(11) NOT NULL,
  PRIMARY KEY (`MEMBERSHIP_CARD_ID`),
  KEY `FK_MEMBERSHIP_PHARMACY` (`PHARMACY_ID`),
  KEY `ihp_membership_card_name_idx` (`MEMBERSHIP_CARD_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `payment_types`
--

DROP TABLE IF EXISTS `payment_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_types` (
  `PAYMENT_TYPE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TYPE` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`PAYMENT_TYPE_ID`),
  KEY `ihp_payment_types_idx` (`TYPE`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `petty_cash`
--

DROP TABLE IF EXISTS `petty_cash`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `petty_cash` (
  `PETTYCASH_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PETTYCASH_REF` varchar(20) DEFAULT NULL,
  `DATE` date DEFAULT NULL,
  `PARTY_NO` int(11) DEFAULT NULL,
  `COUNTER_PARTY_NO` int(11) DEFAULT NULL,
  `AMOUNT` double DEFAULT NULL,
  `REFERENCE` varchar(100) DEFAULT NULL,
  `BALANCE` double DEFAULT NULL,
  `CREATION_USER_ID` int(11) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` int(11) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`PETTYCASH_ID`),
  KEY `ihealth_pettycash_party_no_idx` (`PARTY_NO`),
  KEY `ihealth_creation_user_id_idx` (`CREATION_USER_ID`),
  KEY `ihealth_last_modified_user_id_idx` (`LAST_UPDATE_USER_ID`),
  KEY `ihealth_pettycash_counter_party_no_idx` (`COUNTER_PARTY_NO`),
  CONSTRAINT `ihealth_pettycash_counter_party_no` FOREIGN KEY (`COUNTER_PARTY_NO`) REFERENCES `chart_of_accounts` (`ACCOUNT_ID`),
  CONSTRAINT `ihealth_pettycash_party_no` FOREIGN KEY (`PARTY_NO`) REFERENCES `chart_of_accounts` (`ACCOUNT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `petty_cash_expenditure`
--

DROP TABLE IF EXISTS `petty_cash_expenditure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `petty_cash_expenditure` (
  `ENTRY_TYPE` varchar(100) DEFAULT NULL,
  `PARTY` varchar(50) DEFAULT NULL,
  `EXPENSES_REF` varchar(200) DEFAULT NULL,
  `COUNTER_PARTY` varchar(50) DEFAULT NULL,
  `DEBIT` double DEFAULT NULL,
  `CREDIT` double DEFAULT NULL,
  `BALANCE` double DEFAULT NULL,
  `FROM_DATE` date DEFAULT NULL,
  `TO_DATE` date DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pharma_access`
--

DROP TABLE IF EXISTS `pharma_access`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pharma_access` (
  `PHARMA_ACCESS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ACCESS_CD` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `ACCESS_NAME` varchar(100) NOT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `ACTIVE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  PRIMARY KEY (`PHARMA_ACCESS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=424324344 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `PHARMACY_LOGO_PATH` blob,
  `WEBSITE_URL` varchar(500) DEFAULT NULL,
  `FAX_NBR` varchar(20) DEFAULT NULL,
  `24_HOURS` char(1) DEFAULT NULL,
  `ADDRESS_LINE1` varchar(250) DEFAULT NULL,
  `ADDRESS_LINE2` varchar(250) DEFAULT NULL,
  `CITY_NM` varchar(50) DEFAULT NULL,
  `PROVINCES_ID` int(11) DEFAULT NULL,
  `COUNTRY_ID` int(11) DEFAULT NULL,
  `ZIP_CD` varchar(10) DEFAULT NULL,
  `AUTHORIZED_PERSON_MIDDLE_NM` varchar(50) DEFAULT NULL,
  `AUTHORIZED_PERSON_LAST_NM` varchar(50) DEFAULT NULL,
  `PURCHASE_ORDER_APPROVAL` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `MAIN_PHARMACY_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`PHARMACY_ID`),
  KEY `FK_PHARMACY_ADDRESS_COUNTRY` (`COUNTRY_ID`),
  KEY `FK_PHARMACY_ADDRESS_PROVINCES` (`PROVINCES_ID`),
  KEY `FK_PHARMACY_MAIN_BRANCH` (`MAIN_PHARMACY_ID`),
  KEY `ihp_pharmacy_name_idx` (`PHARMACY_NM`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pharmacy_admin`
--

DROP TABLE IF EXISTS `pharmacy_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pharmacy_admin` (
  `ADMIN_USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ADMIN_USER_NM` varchar(50) DEFAULT NULL,
  `PASSWORD` varchar(50) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ADMIN_USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pharmacy_purchase_order_approval_process`
--

DROP TABLE IF EXISTS `pharmacy_purchase_order_approval_process`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pharmacy_purchase_order_approval_process` (
  `PHARMACY_PURCHASE_ORDER_APPROVAL_PROCESS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PHARMACY_ID` int(11) NOT NULL,
  `TYPE` varchar(50) DEFAULT NULL,
  `DESCRIPTION` varchar(200) DEFAULT NULL,
  `MIN_AMOUNT` double DEFAULT NULL,
  `MAX_AMOUNT` double DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `APPROVAL_USER` int(11) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`PHARMACY_PURCHASE_ORDER_APPROVAL_PROCESS_ID`),
  KEY `FK_PHARMACY_PURCHASE_ORDER_PHARMACY` (`PHARMACY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pharmacy_roles`
--

DROP TABLE IF EXISTS `pharmacy_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pharmacy_roles` (
  `ROLE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_NM` varchar(20) DEFAULT NULL,
  `DESCRIPTION` varchar(200) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pharmacy_setup`
--

DROP TABLE IF EXISTS `pharmacy_setup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pharmacy_setup` (
  `SETUP_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ADMIN_USER_ID` int(11) NOT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`SETUP_ID`),
  KEY `FK_ADMIN_ID_LOOKUP_idx` (`ADMIN_USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  KEY `FK_PHARMACY_STOCK_POINTS_PHARMACY_BRANCH` (`PHARMACY_BRANCH_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `prescription_images`
--

DROP TABLE IF EXISTS `prescription_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prescription_images` (
  `PRESCRIPTION_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PRESCRIPTION_IMAGE` longblob,
  `PRESCRIPTION_DATE` date DEFAULT NULL,
  `CUSTOMER_ID` int(11) NOT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `SALES_BILL_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`PRESCRIPTION_ID`),
  KEY `FK_PRESCRIPTION_CUSTOMER_ID_idx` (`CUSTOMER_ID`),
  KEY `FK_PRESCRIPTION_SALES_BILL_ID_idx` (`SALES_BILL_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `provider`
--

DROP TABLE IF EXISTS `provider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `provider` (
  `PROVIDER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FIRST_NM` varchar(50) DEFAULT NULL,
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
  `CITY_NM` varchar(50) DEFAULT NULL,
  `ZIP_CD` varchar(10) DEFAULT NULL,
  `CREDIT` varchar(20) DEFAULT NULL,
  `LICENSE_NUMBER` varchar(20) DEFAULT NULL,
  `DEA_NUMBER` varchar(20) DEFAULT NULL,
  `GENDER_CD` char(1) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `SPECIALITY` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `PROVIDER_TYPE_LOOKUP_ID` int(11) DEFAULT NULL,
  `COUNTRY_ID` int(11) DEFAULT NULL,
  `PROVINCES_ID` int(11) DEFAULT NULL,
  `MIDDLE_NM` varchar(50) DEFAULT NULL,
  `HOSPITAL_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`PROVIDER_ID`),
  KEY `FK_PROVIDER_COUNTRY_LOOKUP` (`COUNTRY_ID`),
  KEY `FK_PROVIDER_PROVIDER_LOOKUP_ID` (`PROVIDER_TYPE_LOOKUP_ID`),
  KEY `FK_PROVIDER_PROVINCES_LOOKUP` (`PROVINCES_ID`),
  KEY `FK_PROVIDER_HOSPITAL_ID` (`HOSPITAL_ID`),
  KEY `ihp_provicer_name_idx` (`FIRST_NM`),
  KEY `ihp_provider_name_idx` (`FIRST_NM`),
  KEY `ihp_provider_first_name_idx` (`FIRST_NM`)
) ENGINE=InnoDB AUTO_INCREMENT=518 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `provider_hospitals`
--

DROP TABLE IF EXISTS `provider_hospitals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `provider_hospitals` (
  `PROVIDER_HOSPITAL_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PROVIDER_ID` int(11) DEFAULT NULL,
  `HOSPITAL_ID` int(11) DEFAULT NULL,
  `CREATION_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`PROVIDER_HOSPITAL_ID`),
  KEY `FK_PROVIDER_HOSPITALS_PROVIDER` (`PROVIDER_ID`),
  KEY `FK_PROVIDER_HOSPITALS_HOSPITALS` (`HOSPITAL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  PRIMARY KEY (`PROVIDER_TYPE_LOOKUP_ID`),
  KEY `ihp_provider_type_lookup_pro_desc_idx` (`PROVIDER_TYPE_DESC`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `provinces_lookup`
--

DROP TABLE IF EXISTS `provinces_lookup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `provinces_lookup` (
  `PROVINCES_ID` int(11) NOT NULL AUTO_INCREMENT,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `CREATION_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `PROVINCES_NM` varchar(255) DEFAULT NULL,
  `COUNTRY_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`PROVINCES_ID`),
  KEY `FK_PROVINCES_LOOKUP_COUNTRY` (`COUNTRY_ID`),
  KEY `ihp_provinces_lookup_name` (`PROVINCES_NM`)
) ENGINE=MyISAM AUTO_INCREMENT=3473 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `purchase_order`
--

DROP TABLE IF EXISTS `purchase_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase_order` (
  `PURCHASE_ORDER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PURCHASE_ORDER_NO` varchar(20) DEFAULT NULL,
  `SUPPLIER_ID` int(11) NOT NULL,
  `QUOTATION_ID` int(11) DEFAULT NULL,
  `PURCHASE_ORDER_DT` date DEFAULT NULL,
  `REQUEST_DT` date DEFAULT NULL,
  `PURCHASE_ORDER_STATUS_ID` int(1) DEFAULT NULL,
  `REMARKS` varchar(200) DEFAULT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `EMERGENCY` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `MODIFIED_BY` int(11) DEFAULT NULL,
  `MODIFIED_DT` date DEFAULT NULL,
  `APPROVED_BY` int(11) DEFAULT NULL,
  `APPROVED_DT` date DEFAULT NULL,
  `REJECTED_BY` int(11) DEFAULT NULL,
  `REJECTED_DT` date DEFAULT NULL,
  `SENT_BY` int(11) DEFAULT NULL,
  `SENT_DT` date DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `PHARMACY_ID` int(11) DEFAULT NULL,
  `PO_AMOUNT` double DEFAULT NULL,
  `DELIVERY_TIME` int(11) DEFAULT NULL,
  `DELIVERY_DATE` date DEFAULT NULL,
  `PAYMENT_TIME` int(11) DEFAULT NULL,
  `PO_NATURE` varchar(200) DEFAULT NULL,
  `PO_CATEGORY` varchar(200) DEFAULT NULL,
  `PO_TERM` varchar(200) DEFAULT NULL,
  `ADVANCE` double DEFAULT NULL,
  `DISCOUNT` double DEFAULT NULL,
  `DISC_PERCENTAGE` double DEFAULT NULL,
  `OTHER_CHARGES` double DEFAULT NULL,
  `VARIATION_TYPE` varchar(20) DEFAULT NULL,
  `REQUESTED_BY` int(11) DEFAULT NULL,
  `DELIVERY_TYPE_ID` int(11) DEFAULT NULL,
  `MEDICAL_OR_NON_MEDICAL` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `PAYMENT_TYPE_ID` int(11) DEFAULT NULL,
  `SHIPPING_ADDRESS` varchar(500) DEFAULT NULL,
  `REJECT_REASON` varchar(200) DEFAULT NULL,
  `PO_DESC` varchar(50) DEFAULT NULL,
  `BALANCE` float DEFAULT NULL,
  `TOTAL_QUANTITY` int(11) DEFAULT NULL,
  `TOTAL_VALUE` double DEFAULT NULL,
  PRIMARY KEY (`PURCHASE_ORDER_ID`),
  KEY `FK_PURCHASE_ORDER_APPROVED_BY` (`APPROVED_BY`),
  KEY `FK_PURCHASE_ORDER_CANCELLED_BY` (`SENT_BY`),
  KEY `FK_PURCHASE_ORDER_CREATED_BY` (`CREATED_BY`),
  KEY `FK_PURCHASE_ORDER_DELIVERY_TYPE` (`DELIVERY_TYPE_ID`),
  KEY `FK_PURCHASE_ORDER_SUPPLIER` (`SUPPLIER_ID`),
  KEY `FK_PURCHASE_ORDER_MODIFIED_BY` (`MODIFIED_BY`),
  KEY `FK_PURCHASE_ORDER_PHARMACY` (`PHARMACY_ID`),
  KEY `FK_PURCHASE_ORDER_QUOTATION` (`QUOTATION_ID`),
  KEY `FK_PURCHASE_ORDER_REQUESTED_BY` (`REQUESTED_BY`),
  KEY `FK_PURCHASE_ORDER_STATUS` (`PURCHASE_ORDER_STATUS_ID`),
  KEY `FK_PURCHASE_ORDER_VERIFI_BY` (`REJECTED_BY`),
  KEY `ihp_purchase_order_supplier_id_idx` (`SUPPLIER_ID`),
  KEY `ihp_purchase_order_no_idx` (`PURCHASE_ORDER_NO`),
  KEY `ihp_purchase_order_quotation_id_idx` (`QUOTATION_ID`),
  KEY `ihp_purchase_order_date_idx` (`PURCHASE_ORDER_DT`),
  KEY `ihp_purchase_order_discount_idx` (`DISCOUNT`),
  KEY `ihp_purchase_order_discount_per_idx` (`DISC_PERCENTAGE`),
  KEY `ihp_purchase_order_po_amount_idx` (`PO_AMOUNT`)
) ENGINE=MyISAM AUTO_INCREMENT=968 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `purchase_order_items`
--

DROP TABLE IF EXISTS `purchase_order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase_order_items` (
  `PURCHASE_ORDER_ITEM_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PURCHASE_ORDER_ID` int(11) DEFAULT NULL,
  `ITEM_ID` int(11) DEFAULT NULL,
  `QUANTITY` int(11) DEFAULT NULL,
  `REMARKS` varchar(200) DEFAULT NULL,
  `STATUS` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `UNIT_PURCHASE_RATE` double DEFAULT NULL,
  `UNIT_SALE_RATE` double DEFAULT NULL,
  `BONUS` double DEFAULT NULL,
  `DISCOUNT_PERCENTAGE` float DEFAULT NULL,
  `TAX` int(11) DEFAULT NULL,
  `DISCOUNT` double DEFAULT NULL,
  `TOTAL_VALUE` double DEFAULT NULL,
  `TOTAL_QUANTITY` int(11) DEFAULT NULL,
  `ACTUAL_VALUE` double DEFAULT NULL,
  `PACK` int(11) DEFAULT NULL,
  `NET_AMT` double DEFAULT NULL,
  `PACK_PURCHASE_PRICE` float DEFAULT NULL,
  PRIMARY KEY (`PURCHASE_ORDER_ITEM_ID`),
  KEY `FK_PURCHASE_ORDER_ITEMS_ITEMS` (`ITEM_ID`),
  KEY `FK_PURCHASE_ORDER_ITEMS_PURCHASE_ORDER` (`PURCHASE_ORDER_ID`),
  KEY `FK_PURCHASE_ORDER_ITEMS_TAX` (`TAX`)
) ENGINE=MyISAM AUTO_INCREMENT=3795 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `purchase_order_status`
--

DROP TABLE IF EXISTS `purchase_order_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase_order_status` (
  `PURCHASE_ORDER_STATUS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `STATUS` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`PURCHASE_ORDER_STATUS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `purchase_return`
--

DROP TABLE IF EXISTS `purchase_return`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase_return` (
  `PURCHASE_RETURN_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PURCHASE_RETURN_NO` varchar(20) DEFAULT NULL,
  `PURCHASE_RETURN_DATE` date DEFAULT NULL,
  `INVOICE_ID` int(11) DEFAULT NULL,
  `STATUS` varchar(20) DEFAULT NULL,
  `SUPPLIER_ID` int(11) DEFAULT NULL,
  `CHARGES` float DEFAULT NULL,
  `GEN_DEBIT_NOTE` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `DELIVERY_MODE` varchar(20) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `ACTIVE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `TOTAL_AMOUNT` float DEFAULT NULL,
  PRIMARY KEY (`PURCHASE_RETURN_ID`),
  KEY `FK_PURCHASE_RETURN_INVOICE` (`INVOICE_ID`),
  KEY `FK_PURCHASE_RETURN_supplier` (`SUPPLIER_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=542 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `purchase_return_item`
--

DROP TABLE IF EXISTS `purchase_return_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase_return_item` (
  `PURCHASE_RETURN_ITEM_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PURCHASE_RETURN_ID` int(11) DEFAULT NULL,
  `PURCHASE_RETURN_TYPE` varchar(30) DEFAULT NULL,
  `ITEM_ID` int(11) DEFAULT NULL,
  `BATCH_NO` varchar(20) DEFAULT NULL,
  `PURCHASE_QUANTITY` int(11) DEFAULT NULL,
  `RETURN_QUANTITY` int(11) DEFAULT NULL,
  `BONUS_QUANTITY` int(11) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `ACTIVE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `AMOUNT` float DEFAULT NULL,
  PRIMARY KEY (`PURCHASE_RETURN_ITEM_ID`),
  KEY `FK_PURCHASE_RETURN_ITEM_ITEMS` (`ITEM_ID`),
  KEY `FK_PURCHASE_RETURN_ITEM_PURCHASE_RETURN` (`PURCHASE_RETURN_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=570 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `purchaseprice_value_for_current_stock_working`
--

DROP TABLE IF EXISTS `purchaseprice_value_for_current_stock_working`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchaseprice_value_for_current_stock_working` (
  `ITEM_NM` varchar(100) DEFAULT NULL,
  `BATCH_NO` varchar(20) DEFAULT NULL,
  `EXPIRY_DT` date DEFAULT NULL,
  `QUANTITY` int(11) DEFAULT NULL,
  `PURCHASE_DISCOUNT_PERCENTAGE` int(11) DEFAULT NULL,
  `UNIT_PURCHASE_RATE` double DEFAULT NULL,
  `CATEGORY_CODE` varchar(20) DEFAULT NULL,
  `VAT_AMT` double DEFAULT NULL,
  `PURCHASE_VALUE` double DEFAULT NULL,
  `FROM_UPDATED_DATE` date DEFAULT NULL,
  `TO_UPDATED_DATE` date DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `quotation`
--

DROP TABLE IF EXISTS `quotation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quotation` (
  `QUOTATION_ID` int(11) NOT NULL AUTO_INCREMENT,
  `QUOTATION_NO` varchar(20) DEFAULT NULL,
  `QUOTATION_DT` date DEFAULT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `DESCRIPTION` varchar(200) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `PHARMACY_ID` int(11) DEFAULT NULL,
  `QUOTATION_STATUS_ID` int(11) DEFAULT NULL,
  `SENT_BY` int(11) DEFAULT NULL,
  `SENT_DT` date DEFAULT NULL,
  `REQUESTED_BY` int(11) DEFAULT NULL,
  `MODIFIED_BY` int(11) DEFAULT NULL,
  `MODIFIED_DT` date DEFAULT NULL,
  `APPROVED_BY` int(11) DEFAULT NULL,
  `APPROVED_DT` date DEFAULT NULL,
  `QUOTATION_EXPIRY_DT` date DEFAULT NULL,
  `CANCELLED_REASON` varchar(200) DEFAULT NULL,
  `REJECTED_BY` int(11) DEFAULT NULL,
  `REJECTED_DT` date DEFAULT NULL,
  `REJECTED_REASON` varchar(200) DEFAULT NULL,
  `QUOTATION_SEND_MODE` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`QUOTATION_ID`),
  KEY `FK_QUOTATION_CREATED_BY` (`CREATED_BY`),
  KEY `FK_QUOTATION_PHARMACY_ID` (`PHARMACY_ID`),
  KEY `FK_QUOTATION_STATUS` (`QUOTATION_STATUS_ID`),
  KEY `FK_QUOTATION_REQUESTED_BY` (`REQUESTED_BY`),
  KEY `FK_QUOTATION_APPROVED_BY` (`APPROVED_BY`),
  KEY `FK_QUOTATION_REJECTED_BY` (`REJECTED_BY`),
  KEY `FK_QUOTATION_MODIFIED_BY` (`MODIFIED_BY`),
  KEY `FK_QUOTATION_SENT_BY` (`SENT_BY`),
  KEY `ihp_quotation_quot_no_idx` (`QUOTATION_NO`)
) ENGINE=MyISAM AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `quotation_item_status`
--

DROP TABLE IF EXISTS `quotation_item_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quotation_item_status` (
  `QUOTATION_ITEM_STATUS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `STATUS` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`QUOTATION_ITEM_STATUS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `quotation_items`
--

DROP TABLE IF EXISTS `quotation_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quotation_items` (
  `QUOTATION_ITEM_ID` int(11) NOT NULL AUTO_INCREMENT,
  `QUOTATION_ID` int(11) NOT NULL,
  `ITEM_ID` int(11) NOT NULL,
  `QUANTITY` int(11) DEFAULT NULL,
  `REMARKS` varchar(200) DEFAULT NULL,
  `STATUS` int(1) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `SUPPLIER_ID` int(11) DEFAULT NULL,
  `DISCOUNT` double DEFAULT NULL,
  `DISCOUNT_PERCENTAGE` float DEFAULT NULL,
  `BONUS` int(11) DEFAULT NULL,
  `DELIVERY_TIME` int(11) DEFAULT NULL,
  `UNIT_PURCHASE_PRICE` double DEFAULT NULL,
  `MRP` double DEFAULT NULL,
  `NET_CREDIT` int(11) DEFAULT NULL,
  `PAYMENT_DAYS` int(11) DEFAULT NULL,
  `CONDITIONS` mediumtext CHARACTER SET latin1 COLLATE latin1_swedish_ci,
  `ADVANCE` double DEFAULT NULL,
  `DELETE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'N',
  `APPROVED_BY` int(11) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `MIN_UNITS` int(11) DEFAULT NULL,
  `MAX_UNITS` int(11) DEFAULT NULL,
  `PRICE_EFFECTIVE_FROM_DT` date DEFAULT NULL,
  `PRICE_EFFECTIVE_TO_DT` date DEFAULT NULL,
  `TAX_INCLUDE_EXCLUDE` varchar(20) DEFAULT NULL,
  `UNIT_SALE_PRICE` double DEFAULT NULL,
  `EXCISE_DUTY` float DEFAULT NULL,
  `EXCISE_DUTY_INCLUDE_EXCLUDE` varchar(20) DEFAULT NULL,
  `PO_TERMS` mediumtext CHARACTER SET latin1 COLLATE latin1_swedish_ci,
  `TAX_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`QUOTATION_ITEM_ID`),
  KEY `FK_QUOTATION_ITEMS_APPROVED_BY` (`APPROVED_BY`),
  KEY `FK_QUOTATION_ITEMS_SUPPLIER` (`SUPPLIER_ID`),
  KEY `FK_QUOTATION_ITEMS_ITEMS` (`ITEM_ID`),
  KEY `FK_QUOTATION_ITEMS_QUOTATION` (`QUOTATION_ID`),
  KEY `FK_QUOTATION_ITEMS_STATUS` (`STATUS`),
  KEY `FK_QUOTATION_ITEM_TAX` (`TAX_ID`),
  KEY `ihp_quotation_items_quantity_idx` (`QUANTITY`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `quotation_status`
--

DROP TABLE IF EXISTS `quotation_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quotation_status` (
  `QUOTATION_STATUS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `STATUS` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`QUOTATION_STATUS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `reports_mapping`
--

DROP TABLE IF EXISTS `reports_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reports_mapping` (
  `REPORT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `REPORT_CODE` varchar(500) DEFAULT NULL,
  `REPORT_NAME` varchar(500) DEFAULT NULL,
  `STORED_PROCEDURE_NAME` varchar(500) DEFAULT NULL,
  `INPUT_PARAMETERS` varchar(2000) DEFAULT NULL,
  `REPORT_HEADER` varchar(2000) DEFAULT NULL,
  `REPORT_TITLE` varchar(2000) DEFAULT NULL,
  `HEADER_CONTENT` varchar(2000) DEFAULT NULL,
  `FOOTER_CONTENT` varchar(2000) DEFAULT NULL,
  `SHOW_VERTICAL_LINES` bit(1) NOT NULL,
  `SHOW_BAR_CHARTS` bit(1) NOT NULL,
  `CUSTOM_REPORT_GENERATOR` varchar(2000) DEFAULT NULL,
  `CUSTOM_EXCEL_REPORT_GENERATOR` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`REPORT_ID`),
  KEY `ihp_report_map_rep_code` (`REPORT_CODE`(255)),
  KEY `ihp_report_map_rep_id` (`REPORT_ID`),
  KEY `ihp_report_map_rep_title` (`REPORT_TITLE`(255))
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  PRIMARY KEY (`RETURN_CREDIT_TYPE_ID`),
  KEY `ihp_return_credit_type_idx` (`TYPE`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `ROLE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_NM` varchar(20) DEFAULT NULL,
  `DESCRIPTION` varchar(200) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `saleprice_value_for_current_stock_working`
--

DROP TABLE IF EXISTS `saleprice_value_for_current_stock_working`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `saleprice_value_for_current_stock_working` (
  `ITEM_NM` varchar(100) DEFAULT NULL,
  `BATCH_NO` varchar(20) DEFAULT NULL,
  `EXPIRY_DT` date DEFAULT NULL,
  `QUANTITY` int(11) DEFAULT NULL,
  `SALE_DISCOUNT_PERCENTAGE` int(11) DEFAULT NULL,
  `UNIT_SALE_RATE` double DEFAULT NULL,
  `CATEGORY_CODE` varchar(20) DEFAULT NULL,
  `VAT_AMT` double DEFAULT NULL,
  `SALE_VALUE` double DEFAULT NULL,
  `FROM_UPDATED_DATE` date DEFAULT NULL,
  `TO_UPDATED_DATE` date DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sales`
--

DROP TABLE IF EXISTS `sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales` (
  `BILL_ID` int(11) NOT NULL AUTO_INCREMENT,
  `BILL_CODE` varchar(30) DEFAULT NULL,
  `PREVIOUS_BILL_CODE` varchar(30) DEFAULT NULL,
  `BILL_DATE` date DEFAULT NULL,
  `CUSTOMER_ID` int(11) DEFAULT NULL,
  `CUSTOMER_NM` varchar(100) DEFAULT NULL,
  `CUSTOMER_PHONE_NO` varchar(20) DEFAULT NULL,
  `PROVIDER_ID` int(11) DEFAULT NULL,
  `PRESCRIPION` blob,
  `PRESCIPTION_DATE` date DEFAULT NULL,
  `TOTAL_PRODUCTS` int(11) DEFAULT NULL,
  `TOTAL_QTY` int(11) DEFAULT NULL,
  `ADJUSTED_QTY` int(11) DEFAULT NULL,
  `EFFECTIVE_OVERALL_DISCOUNT` float DEFAULT NULL,
  `OVERALL_DISCOUNT` float DEFAULT NULL,
  `EFFECTIVE_VAT` float DEFAULT NULL,
  `VAT_AMT` float DEFAULT NULL,
  `EFFECTIVE_MARGIN` float DEFAULT NULL,
  `MARGIN_AMT` float DEFAULT NULL,
  `EFFECTIVE_SALES_DISC` float DEFAULT NULL,
  `SALE_DISC_AMT` float DEFAULT NULL,
  `ROUNDED_OFF` float DEFAULT NULL,
  `CUSTOMER_INSURANCE_ID` int(11) DEFAULT NULL,
  `INSURANCE_CONTRIB_PERCENT` float DEFAULT NULL,
  `INSURANCE_CONTRIB_AMT` float DEFAULT NULL,
  `CUSTOMER_MEMBERSHIP_ID` int(11) DEFAULT NULL,
  `UPI_PHONE_NO` varchar(20) DEFAULT NULL,
  `UPI_AMOUNT` float DEFAULT NULL,
  `CASH_AMOUNT` float DEFAULT NULL,
  `UPI_TRANS_ID` varchar(50) DEFAULT NULL,
  `CREDIT_DAYS` int(11) DEFAULT NULL,
  `CREDIT_AMOUNT` float DEFAULT NULL,
  `CHEQUE_NUMBER` int(11) DEFAULT NULL,
  `CHEQUE_AMT` float DEFAULT NULL,
  `CHEQUE_DATE` date DEFAULT NULL,
  `TOTAL_AMOUNT` float DEFAULT NULL,
  `NET_AMOUNT` float DEFAULT NULL,
  `PAID_AMOUNT` float DEFAULT NULL,
  `PAYMENT_STATUS` varchar(20) DEFAULT NULL,
  `BALANCE_AMOUNT` float DEFAULT NULL,
  `REMARKS` varchar(100) DEFAULT NULL,
  `MEMBERSHIP_CONTRIB_PERCENT` float DEFAULT NULL,
  `MEMBERSHIP_CONTRIB_AMT` float DEFAULT NULL,
  `CREDIT_CARD_AMOUNT` float DEFAULT NULL,
  `CREDIT_CARD_NO` varchar(20) DEFAULT NULL,
  `TAXNO` varchar(50) DEFAULT NULL,
  `PHARMACY_ID` int(11) DEFAULT NULL,
  `HOSPITAL_ID` int(11) DEFAULT NULL,
  `EMPLOYEE_ID` int(11) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `ACTIVE_S` char(1) DEFAULT NULL,
  PRIMARY KEY (`BILL_ID`),
  UNIQUE KEY `ihp_sales_u_bill_code` (`BILL_CODE`),
  KEY `FK_SALES_INSURANCE` (`CUSTOMER_INSURANCE_ID`),
  KEY `FK_SALES_EMPLOYEE` (`EMPLOYEE_ID`),
  KEY `FK_SALES_PROVIDER` (`PROVIDER_ID`),
  KEY `FK_SALES_PHARMACY` (`PHARMACY_ID`),
  KEY `FK_SALES_CUSTOMER_MEMBERSHIP` (`CUSTOMER_MEMBERSHIP_ID`),
  KEY `FK_SALES_CUSTOMER_idx` (`CUSTOMER_ID`),
  KEY `FK_SALES_HOSPITAL_ID_idx` (`HOSPITAL_ID`),
  KEY `sales` (`CUSTOMER_NM`),
  KEY `ihp_sales_cash_amt_idx` (`BILL_ID`,`CASH_AMOUNT`),
  KEY `ihp_sales_credit_amt_idx` (`BILL_ID`,`CREDIT_AMOUNT`),
  KEY `ihp_sales_card_amt_idx` (`BILL_ID`,`CREDIT_CARD_AMOUNT`),
  KEY `ihp_sales_mpesa_amt_idx` (`BILL_ID`,`UPI_AMOUNT`),
  KEY `ihp_sales_cheque_amt_idx` (`BILL_ID`,`CHEQUE_AMT`),
  KEY `ihp_sales_bill_date_idx` (`BILL_DATE`)
) ENGINE=InnoDB AUTO_INCREMENT=42967 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sales_inventory_working`
--

DROP TABLE IF EXISTS `sales_inventory_working`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_inventory_working` (
  `Bill_ID` varchar(30) DEFAULT NULL,
  `PAYMENT_STATUS` varchar(20) DEFAULT NULL,
  `FROMDATE` date DEFAULT NULL,
  `TODATE` date DEFAULT NULL,
  KEY `sales_inventory_working_idx` (`Bill_ID`,`FROMDATE`,`TODATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sales_items`
--

DROP TABLE IF EXISTS `sales_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_items` (
  `SALES_ITEMS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `BILL_ID` int(11) DEFAULT NULL,
  `ITEM_ID` int(11) DEFAULT NULL,
  `STOCK_ID` int(11) DEFAULT NULL,
  `SALE_QTY` int(11) DEFAULT NULL,
  `MRP` double DEFAULT NULL,
  `UNIT_SALE_PRICE` double DEFAULT NULL,
  `UNIT_PURCHASE_PRICE` double DEFAULT NULL,
  `DISCOUNT` float DEFAULT NULL,
  `DISCOUNT_PERCENTAGE` float DEFAULT NULL,
  `VAT` int(11) DEFAULT NULL,
  `MARGIN` float DEFAULT NULL,
  `SALE_AMOUNT` double DEFAULT NULL,
  `SP_VAT` float DEFAULT NULL,
  `QTY_FREE` float DEFAULT NULL,
  `FREE_QTY_APPROVER` varchar(50) DEFAULT NULL,
  `BATCH_NO` varchar(20) DEFAULT NULL,
  `BARCODE` varchar(100) DEFAULT NULL,
  `REMARKS` varchar(100) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `SPPLIER_ID` int(11) DEFAULT NULL,
  `TAX_CATEGORY_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`SALES_ITEMS_ID`),
  KEY `FK_SALES_ITEM_ITEMS` (`ITEM_ID`),
  KEY `FK_SALES_ITEM_SALES` (`BILL_ID`),
  KEY `FK_SALES_SUPPLIER_ID_idx` (`SPPLIER_ID`),
  KEY `FK_SALES_ITEM_STOCK_ID_idx` (`STOCK_ID`),
  KEY `FK_TAXCATEGORY_ID_idx` (`TAX_CATEGORY_ID`),
  KEY `FK_SALES_ITEMS_TAXCATEGORY_ID_idx` (`TAX_CATEGORY_ID`),
  KEY `ihp_sales_items_item_qty_idx` (`ITEM_ID`,`SALE_QTY`),
  KEY `ihp_sales_items_stock_ID` (`ITEM_ID`,`STOCK_ID`),
  CONSTRAINT `FK_SALES_ITEMS_TAXCATEGORY_ID` FOREIGN KEY (`TAX_CATEGORY_ID`) REFERENCES `taxcategory` (`TAXCATEGORY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=106895 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sales_profit_attribution_working`
--

DROP TABLE IF EXISTS `sales_profit_attribution_working`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_profit_attribution_working` (
  `BILL_CODE` varchar(30) DEFAULT NULL,
  `FROM_BILL_DATE` date DEFAULT NULL,
  `TO_BILL_DATE` date DEFAULT NULL,
  `ITEM_NM` varchar(100) DEFAULT NULL,
  `BATCH_NO` varchar(20) DEFAULT NULL,
  `SALE_QTY` int(11) DEFAULT NULL,
  `QTY_FREE` float DEFAULT NULL,
  `UNIT_PURCHASE_PRICE` double DEFAULT NULL,
  `PURCHASE_DISCOUNT_PERCENTAGE` double DEFAULT NULL,
  `UNIT_SALE_PRICE` double DEFAULT NULL,
  `DISCOUNT_PERCENTAGE` double DEFAULT NULL,
  `VAT` int(11) DEFAULT NULL,
  `SALE_AMOUNT` double DEFAULT NULL,
  `PROFIT` double DEFAULT NULL,
  `PROFIT_PER` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sales_register_details_working`
--

DROP TABLE IF EXISTS `sales_register_details_working`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_register_details_working` (
  `BILL_CODE` varchar(30) DEFAULT NULL,
  `FROM_BILL_DATE` date DEFAULT NULL,
  `TO_BILL_DATE` date DEFAULT NULL,
  `CUSTOMER_ID` int(11) DEFAULT NULL,
  `TYPE` varchar(50) DEFAULT NULL,
  `AMOUNT` float DEFAULT NULL,
  `PAID_AMOUNT` float DEFAULT NULL,
  `BALANCE_AMOUNT` float DEFAULT NULL,
  `PAYMENT_STATUS` varchar(50) DEFAULT NULL,
  `VAT_AMT` double DEFAULT NULL,
  KEY `ihp_srd_working_idx` (`BILL_CODE`,`FROM_BILL_DATE`,`TO_BILL_DATE`,`TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sales_return`
--

DROP TABLE IF EXISTS `sales_return`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_return` (
  `SALES_RETURN_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SALES_RETURN_NO` varchar(20) NOT NULL,
  `SALES_RETURN_DATE` date DEFAULT NULL,
  `STATUS` varchar(20) NOT NULL,
  `BILL_ID` int(11) NOT NULL,
  `TOTAL_AMOUNT` float DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `ACTIVE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `PAYMENT_TYPE_ID` int(11) NOT NULL,
  `PHARMACY_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`SALES_RETURN_ID`),
  KEY `FK_SALES_RETURN_BILL_ID` (`BILL_ID`),
  KEY `FK_SALES_RETURN_PHARMACY_idx` (`PHARMACY_ID`),
  CONSTRAINT `FK_SALES_RETURN_PHARMACY` FOREIGN KEY (`PHARMACY_ID`) REFERENCES `pharmacy` (`PHARMACY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=198 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sales_return_item`
--

DROP TABLE IF EXISTS `sales_return_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_return_item` (
  `SALES_RETURN_ITEM_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SALES_RETURN_ID` int(11) DEFAULT NULL,
  `SALES_RETURN_TYPE` varchar(30) DEFAULT NULL,
  `ITEM_ID` int(11) DEFAULT NULL,
  `PURCHASE_QUANTITY` int(11) DEFAULT NULL,
  `RETURN_QUANTITY` int(11) DEFAULT NULL,
  `BONUS_QUANTITY` int(11) DEFAULT NULL,
  `AMOUNT` float DEFAULT NULL,
  `CHARGES` float DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `ACTIVE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  PRIMARY KEY (`SALES_RETURN_ITEM_ID`),
  KEY `FK_SALES_RETURN_ITEM_ITEMS` (`ITEM_ID`),
  KEY `FK_SALES_RETURN_ITEM_PURCHASE_RETURN` (`SALES_RETURN_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sales_tmp`
--

DROP TABLE IF EXISTS `sales_tmp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_tmp` (
  `Bill_ID` int(11) DEFAULT NULL,
  `Item_ID` int(11) DEFAULT NULL,
  `Sale_Qty` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sales_tmp1`
--

DROP TABLE IF EXISTS `sales_tmp1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_tmp1` (
  `Bill_ID` int(11) DEFAULT NULL,
  `Products_Qty` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sales_tmp2`
--

DROP TABLE IF EXISTS `sales_tmp2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_tmp2` (
  `Bill_ID` int(11) DEFAULT NULL,
  `TOTAL_QTY` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sales_tmp3`
--

DROP TABLE IF EXISTS `sales_tmp3`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_tmp3` (
  `Bill_ID` int(11) DEFAULT NULL,
  `TOTAL_AMOUNT` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `sales_view`
--

DROP TABLE IF EXISTS `sales_view`;
/*!50001 DROP VIEW IF EXISTS `sales_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `sales_view` AS SELECT 
 1 AS `Bill_ID`,
 1 AS `Payment_Status`,
 1 AS `FromDate`,
 1 AS `ToDate`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `sales_view_working`
--

DROP TABLE IF EXISTS `sales_view_working`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_view_working` (
  `Bill_ID` varchar(30) DEFAULT NULL,
  `PAYMENT_STATUS` varchar(20) DEFAULT NULL,
  `FROMDATE` date DEFAULT NULL,
  `TODATE` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sample1`
--

DROP TABLE IF EXISTS `sample1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sample1` (
  `MYFCol` varchar(20) DEFAULT NULL,
  `MyNum` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `schedule_category_code`
--

DROP TABLE IF EXISTS `schedule_category_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedule_category_code` (
  `SCHEDULE_CATEGORY_CODE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SCHEDULE_CATEGORY_CODE` varchar(20) DEFAULT NULL,
  `SCHEDULE_CATEGORY_DESC` varchar(255) DEFAULT NULL,
  `ACTIVE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`SCHEDULE_CATEGORY_CODE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `specialization`
--

DROP TABLE IF EXISTS `specialization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `specialization` (
  `SPECIALIZATION_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SPECIALIZATION_CD` varchar(20) DEFAULT NULL,
  `SPECIALIZATION_NAME` varchar(100) DEFAULT NULL,
  `SPECIALIZATION_DESC` mediumtext CHARACTER SET latin1 COLLATE latin1_swedish_ci,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `ACTIVE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'y',
  PRIMARY KEY (`SPECIALIZATION_ID`),
  KEY `ihp_specialization_name_idx` (`SPECIALIZATION_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock` (
  `STOCK_ID` int(11) NOT NULL AUTO_INCREMENT,
  `STOCK_NO` varchar(45) DEFAULT NULL,
  `ITEM_ID` int(11) DEFAULT NULL,
  `PHARMACY_ID` int(11) DEFAULT NULL,
  `INVOICE_ID` int(11) DEFAULT NULL,
  `QUANTITY` int(11) DEFAULT NULL,
  `UNIT_SALE_RATE` double DEFAULT NULL,
  `SP_VAT` double DEFAULT NULL,
  `MRP` double DEFAULT NULL,
  `MARGIN` double DEFAULT NULL,
  `MARGIN_AMT` double DEFAULT NULL,
  `SALE_DISCOUNT_PERCENTAGE` double DEFAULT NULL,
  `SALE_DISCOUNT_AMOUNT` double DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `MANUFACTURE_DT` date DEFAULT NULL,
  `EXPIRY_DT` date DEFAULT NULL,
  `BATCH_NO` varchar(20) DEFAULT NULL,
  `PURCHASE_DISCOUNT_AMOUNT` double DEFAULT NULL,
  `PURCHASE_DISCOUNT_PERCENTAGE` double DEFAULT NULL,
  `RACK` varchar(50) DEFAULT NULL,
  `UNIT_PURCHASE_RATE` double DEFAULT NULL,
  `SHELF` varchar(50) DEFAULT NULL,
  `BARCODE` varchar(50) DEFAULT NULL,
  `SUPPLIER_ID` int(11) DEFAULT NULL,
  `REMARKS` varchar(200) DEFAULT NULL,
  `STATUS` varchar(30) DEFAULT NULL,
  `VAT` double DEFAULT NULL,
  `STOCK_DATE` date DEFAULT NULL,
  `INVOICE_NO` varchar(20) DEFAULT NULL,
  `ENTRY_TYPE` varchar(50) DEFAULT NULL,
  `PACK` varchar(20) DEFAULT NULL,
  `TAX_CATEGORY_ID` int(11) DEFAULT NULL,
  `BONUS` int(11) DEFAULT NULL,
  PRIMARY KEY (`STOCK_ID`),
  KEY `FK_STOCK_ITEM` (`ITEM_ID`),
  KEY `FK_STOCK_PHARMACY` (`PHARMACY_ID`),
  KEY `FK_STOCK_SUPPLIER_idx` (`SUPPLIER_ID`),
  KEY `FK_STOCK_INVOICE_idx` (`INVOICE_ID`),
  KEY `ihp_stock_item_pharmacy_idx` (`ITEM_ID`,`PHARMACY_ID`),
  KEY `FK_TAXCATEGORY_ID_idx` (`TAX_CATEGORY_ID`),
  KEY `ihp_stock_batch_no_idx` (`BATCH_NO`,`QUANTITY`)
) ENGINE=InnoDB AUTO_INCREMENT=8594 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `stock_1221`
--

DROP TABLE IF EXISTS `stock_1221`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock_1221` (
  `Product_Id` int(11) DEFAULT NULL,
  `Item_Name` varchar(250) DEFAULT NULL,
  `Qty` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `stock_190120`
--

DROP TABLE IF EXISTS `stock_190120`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock_190120` (
  `Stock_Id` varchar(11) DEFAULT NULL,
  `Row_No` int(11) DEFAULT NULL,
  `Item_Nm` varchar(200) DEFAULT NULL,
  `Batch_No` varchar(30) DEFAULT NULL,
  `Expiry` date DEFAULT NULL,
  `Rack` varchar(100) DEFAULT NULL,
  `Shelf` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `stock_200120`
--

DROP TABLE IF EXISTS `stock_200120`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock_200120` (
  `Stock_Id` varchar(11) DEFAULT NULL,
  `Row_No` int(11) DEFAULT NULL,
  `Item_Nm` varchar(200) DEFAULT NULL,
  `Batch_No` varchar(30) DEFAULT NULL,
  `Expiry` date DEFAULT NULL,
  `Rack` varchar(100) DEFAULT NULL,
  `Shelf` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `stock_adjustment`
--

DROP TABLE IF EXISTS `stock_adjustment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock_adjustment` (
  `STOCK_ADJUSTMENT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ITEM_ID` int(11) DEFAULT NULL,
  `BATCH_NO` varchar(20) DEFAULT NULL,
  `EXPIRY_DT` date DEFAULT NULL,
  `ON_HAND_STOCK` int(11) DEFAULT NULL,
  `ON_HAND_STOCK_VALUE` float DEFAULT NULL,
  `ADJUSTEMENT_STOCK` int(11) DEFAULT NULL,
  `ADJUSTED_STOCK_VALUE` float DEFAULT NULL,
  `PHYSICAL_STOCK` int(11) DEFAULT NULL,
  `PHYSICAL_STOCK_VALUE` float DEFAULT NULL,
  `REMARKS` varchar(100) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `ACTIVE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `AUDIT_ID` int(11) DEFAULT NULL,
  `PHARMACY_ID` int(11) DEFAULT NULL,
  `STOCK_ID` int(11) NOT NULL,
  `SYSTEM_DATE` date NOT NULL,
  PRIMARY KEY (`STOCK_ADJUSTMENT_ID`),
  KEY `FK_STOCK_ADJUSTMENT_ITEMS` (`ITEM_ID`),
  KEY `FK_STOCK_ADJUSTMENT_PHARMACY` (`PHARMACY_ID`),
  KEY `FK_STOCK_ADJ_STOCK_idx` (`STOCK_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=182 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `stock_adjustment_working`
--

DROP TABLE IF EXISTS `stock_adjustment_working`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock_adjustment_working` (
  `ITEM_NM` varchar(100) DEFAULT NULL,
  `INVOICE_NO` varchar(20) DEFAULT NULL,
  `BATCH_NO` varchar(20) DEFAULT NULL,
  `EXPIRY_DT` date DEFAULT NULL,
  `RACK` varchar(50) DEFAULT NULL,
  `SHELF` varchar(50) DEFAULT NULL,
  `PHYSICAL_STOCK` int(11) DEFAULT NULL,
  `ADJUSTEMENT_STOCK` int(11) DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATED_DT` date DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `FROM_SYSTEM_DATE` date DEFAULT NULL,
  `TO_SYSTEM_DATE` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `stock_history`
--

DROP TABLE IF EXISTS `stock_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock_history` (
  `STOCK_HISTORY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `STOCK_ID` int(11) DEFAULT NULL,
  `STOCK_NO` varchar(45) DEFAULT NULL,
  `ITEM_ID` int(11) DEFAULT NULL,
  `PHARMACY_ID` int(11) DEFAULT NULL,
  `INVOICE_ID` int(11) DEFAULT NULL,
  `QUANTITY` int(11) DEFAULT NULL,
  `BONUS` int(11) DEFAULT NULL,
  `UNIT_SALE_RATE` double DEFAULT NULL,
  `SP_VAT` double DEFAULT NULL,
  `MRP` double DEFAULT NULL,
  `MARGIN` double DEFAULT NULL,
  `MARGIN_AMT` double DEFAULT NULL,
  `SALE_DISCOUNT_PERCENTAGE` double DEFAULT NULL,
  `SALE_DISCOUNT_AMOUNT` double DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `MANUFACTURE_DT` date DEFAULT NULL,
  `EXPIRY_DT` date DEFAULT NULL,
  `BATCH_NO` varchar(20) DEFAULT NULL,
  `PURCHASE_DISCOUNT_AMOUNT` double DEFAULT NULL,
  `PURCHASE_DISCOUNT_PERCENTAGE` double DEFAULT NULL,
  `RACK` varchar(50) DEFAULT NULL,
  `UNIT_PURCHASE_RATE` double DEFAULT NULL,
  `SHELF` varchar(50) DEFAULT NULL,
  `BARCODE` varchar(50) DEFAULT NULL,
  `SUPPLIER_ID` int(11) DEFAULT NULL,
  `REMARKS` varchar(200) DEFAULT NULL,
  `STATUS` varchar(30) DEFAULT NULL,
  `VAT` double DEFAULT NULL,
  `STOCK_DATE` date DEFAULT NULL,
  `INVOICE_NO` varchar(20) DEFAULT NULL,
  `ENTRY_TYPE` varchar(50) DEFAULT NULL,
  `PACK` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`STOCK_HISTORY_ID`),
  KEY `FK_STOCK_ITEM` (`ITEM_ID`),
  KEY `FK_STOCK_PHARMACY` (`PHARMACY_ID`),
  KEY `FK_STOCK_SUPPLIER_idx` (`SUPPLIER_ID`),
  KEY `FK_STOCK_INVOICE_idx` (`INVOICE_ID`),
  KEY `ihp_stock_item_pharmacy_idx` (`ITEM_ID`,`PHARMACY_ID`),
  KEY `ihp_stock_hist_last_update_ts` (`ITEM_ID`,`QUANTITY`,`LAST_UPDATE_TS`),
  KEY `ihp_stock_history_stockitem_idx` (`ITEM_ID`,`STOCK_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=35230 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `stock_history_working`
--

DROP TABLE IF EXISTS `stock_history_working`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock_history_working` (
  `ITEM_NM` varchar(100) DEFAULT NULL,
  `INVOICE_NO` varchar(20) DEFAULT NULL,
  `QUANTITY` int(11) DEFAULT NULL,
  `UNIT_SALE_RATE` double DEFAULT NULL,
  `SALE_PRICE` double DEFAULT NULL,
  `UNIT_PURCHASE_RATE` double DEFAULT NULL,
  `PURCHASE_PRICE` double DEFAULT NULL,
  `FROM_DATE` date DEFAULT NULL,
  `TO_DATE` date DEFAULT NULL,
  `EXPIRY_DT` date DEFAULT NULL,
  `LAST_UPDATE_USER` varchar(50) DEFAULT NULL,
  `SP_NAME` varchar(100) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `stock_qty_working`
--

DROP TABLE IF EXISTS `stock_qty_working`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock_qty_working` (
  `Item_ID` int(11) DEFAULT NULL,
  `Quantity` int(11) DEFAULT NULL,
  KEY `stock_qty_working_idx` (`Item_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `stock_tmp`
--

DROP TABLE IF EXISTS `stock_tmp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock_tmp` (
  `item_code` int(11) DEFAULT NULL,
  `item_name` varchar(50) DEFAULT NULL,
  `invoice_date` date DEFAULT NULL,
  `invoice_no` varchar(50) DEFAULT NULL,
  `unit_price` double DEFAULT NULL,
  `packing` int(11) DEFAULT NULL,
  `Bonus` int(11) DEFAULT NULL,
  `unit_discount` double DEFAULT NULL,
  `unit_vat` int(11) DEFAULT NULL,
  `mrp` int(11) DEFAULT NULL,
  `Item_ID` int(11) DEFAULT NULL,
  KEY `item_wise_price_list_idx` (`item_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `stock_tmp1`
--

DROP TABLE IF EXISTS `stock_tmp1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock_tmp1` (
  `item_cd` int(11) DEFAULT NULL,
  `item_nm` varchar(100) DEFAULT NULL,
  `unit_price` float DEFAULT NULL,
  `vat` int(11) DEFAULT NULL,
  `pack` int(11) DEFAULT NULL,
  `unit_discount` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `stock_view_qty`
--

DROP TABLE IF EXISTS `stock_view_qty`;
/*!50001 DROP VIEW IF EXISTS `stock_view_qty`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `stock_view_qty` AS SELECT 
 1 AS `Item_ID`,
 1 AS `Quantity`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `SUPPLIER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SP_NAME` varchar(100) DEFAULT NULL,
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
  `CONTACT_PERSON_FIRST_NM` varchar(50) DEFAULT NULL,
  `WEBSITE` varchar(500) DEFAULT NULL,
  `ADDRESS_LINE1` varchar(250) DEFAULT NULL,
  `ADDRESS_LINE2` varchar(250) DEFAULT NULL,
  `CITY_NM` varchar(50) DEFAULT NULL,
  `ZIP_CD` varchar(10) DEFAULT NULL,
  `PAYMENT_TERMS` text,
  `PAYMENT_CREDIT_NET_DAYS` int(11) DEFAULT NULL,
  `LATE_PAYMENT_INTEREST` int(11) DEFAULT NULL,
  `PIN_NO` varchar(20) DEFAULT NULL,
  `SL_NO` varchar(20) DEFAULT NULL,
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
  `CONTACT_PERSON_MIDDLE_NM` varchar(50) DEFAULT NULL,
  `CONTACT_PERSON_LAST_NM` varchar(50) DEFAULT NULL,
  `SUPPLIER_ALSO_MANUFACTURER` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'N',
  `SUPPLIES_MEDICAL_NON_MEDICAL_BOTH` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'B',
  `BANK_NAME` varchar(50) DEFAULT NULL,
  `ACCOUNT_NUMBER` varchar(30) DEFAULT NULL,
  `IFSC_CODE` varchar(30) DEFAULT NULL,
  `MICR_CODE` varchar(30) DEFAULT NULL,
  `PAYMENT_TYPE_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`SUPPLIER_ID`),
  KEY `FK_SUPPLIER_RETURN_CREDIT_TYPE` (`RETURN_CREDIT_TYPE_ID`),
  KEY `FK_SUPPLIER_COUNTRY` (`COUNTRY_ID`),
  KEY `FK_SUPPLIER_PROVINCES_LOOKUP` (`PROVINCES_ID`),
  KEY `supp_idx` (`SUPPLIER_ID`),
  KEY `ihp_supplier_name_idx` (`SP_NAME`),
  KEY `ihp_supplier_slno_idx` (`SL_NO`),
  KEY `ihp_supplier_pinno_idx` (`PIN_NO`),
  KEY `ihp_supplier_city_name_idx` (`CITY_NM`),
  KEY `ihp_supplier_pay_credit_net_days_idx` (`PAYMENT_CREDIT_NET_DAYS`),
  KEY `ihp_supplier_address_line_idx` (`ADDRESS_LINE1`),
  KEY `ihp_supplier_contact_person_phone_idx` (`CONTACT_PERSON_PHONE_NBR`),
  KEY `ihp_supplier_contact_person_email_idx` (`CONTACT_PERSON_EMAIL_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=152 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `supplier_contract`
--

DROP TABLE IF EXISTS `supplier_contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier_contract` (
  `SUPPLIER_CONTRACT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SUPPLIER_ID` int(11) NOT NULL,
  `CONTRACT_NO` varchar(20) NOT NULL,
  `CONTRACT_DT` date NOT NULL,
  `CONTRACT_EXPIRY_DT` date NOT NULL,
  `CONTRACT_VALUE` double DEFAULT NULL,
  `PAYMENT_TIME` int(11) NOT NULL,
  `DELIVERY_TIME` int(11) DEFAULT NULL,
  `CLASSIFICATION` varchar(100) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `ACTIVE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `REMARKS` varchar(500) DEFAULT NULL,
  `CONTRACT_DOCUMENT` blob,
  `QUOTATION_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`SUPPLIER_CONTRACT_ID`),
  KEY `FK_SUPPLIER_CONTRACT_SUPPLIER` (`SUPPLIER_ID`),
  KEY `FK_SUPPLIER_CONTRACT_QUOTATION` (`QUOTATION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `supplier_contract_items`
--

DROP TABLE IF EXISTS `supplier_contract_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier_contract_items` (
  `SUPPLIER_CONTRACT_ITEM_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SUPPLIER_ID` int(11) DEFAULT NULL,
  `SUPPLIER_CONTRACT_ID` int(11) DEFAULT NULL,
  `ITEM_ID` int(11) DEFAULT NULL,
  `UNIT_PURCHASE_PRICE` double DEFAULT NULL,
  `ACTIVE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `CURRENT_SUPPLIER` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `UNIT_TAX` float DEFAULT NULL,
  `QTY_BONUS` int(11) DEFAULT NULL,
  `DISCOUNT` float DEFAULT NULL,
  `DISCOUNT_PERCENTAGE` float DEFAULT NULL,
  `APPROVED_SUPPLIER` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  PRIMARY KEY (`SUPPLIER_CONTRACT_ITEM_ID`),
  KEY `FK_SUPPLIER_CONTRACT_ITEM_CONTRACT` (`SUPPLIER_CONTRACT_ID`),
  KEY `FK_SUPPLIER_CONTRACT_ITEM_SUPPLIER` (`SUPPLIER_ID`),
  KEY `FK_SUPPLIER_CONTRACT_ITEM_ITEMS` (`ITEM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `supplier_statement_working`
--

DROP TABLE IF EXISTS `supplier_statement_working`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier_statement_working` (
  `SP_NAME` varchar(100) DEFAULT NULL,
  `SOURCE_REF` varchar(30) DEFAULT NULL,
  `SOURCE_TYPE` varchar(30) DEFAULT NULL,
  `PAYMENT_DATE` date DEFAULT NULL,
  `INVOICE_NO` varchar(20) DEFAULT NULL,
  `debit` float DEFAULT NULL,
  `credit` float DEFAULT NULL,
  `balance` float DEFAULT NULL,
  `FROM_APPROVED_DATE` date DEFAULT NULL,
  `TO_APPROVED_DATE` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tax`
--

DROP TABLE IF EXISTS `tax`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tax` (
  `TAX_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TAX_CD` varchar(20) DEFAULT NULL,
  `TAX_DESC` varchar(200) DEFAULT NULL,
  `PERCENTAGE` float DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`TAX_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tax_temp`
--

DROP TABLE IF EXISTS `tax_temp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tax_temp` (
  `Item_NM` varchar(200) DEFAULT NULL,
  `tax_category_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `taxcategory`
--

DROP TABLE IF EXISTS `taxcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taxcategory` (
  `TAXCATEGORY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CATEGORY_CODE` varchar(20) DEFAULT NULL,
  `CATEGORY_VALUE` int(11) DEFAULT NULL,
  `START_DATE` datetime DEFAULT NULL,
  `END_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`TAXCATEGORY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `taxcodes_tmp`
--

DROP TABLE IF EXISTS `taxcodes_tmp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taxcodes_tmp` (
  `Item_ID` int(11) DEFAULT NULL,
  `TaxCode` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `temp_employee`
--

DROP TABLE IF EXISTS `temp_employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `temp_employee` (
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
  `POSTAL_CD` varchar(20) DEFAULT NULL,
  `CREATION_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATION_USER_ID` varchar(50) NOT NULL,
  `LAST_UPDATE_TS` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `PROFILE_IMAGE` blob,
  `AUDIT_ID` int(11) DEFAULT NULL,
  `EMPLOYEE_TYPE_LOOKUP_ID` int(11) DEFAULT NULL,
  `PHARMACY_ID` int(11) DEFAULT NULL,
  `SALARY` double DEFAULT NULL,
  `EMPLOYEE_TYPE_FULL_PART_TIME` varchar(50) DEFAULT NULL,
  `COUNTRY_ID` int(11) DEFAULT NULL,
  `PROVINCES_ID` int(11) DEFAULT NULL,
  `ACTIVE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `EMPLOYEE_CD` varchar(20) DEFAULT NULL,
  `KRA_PIN` varchar(20) DEFAULT NULL,
  `IDENTIFICATION_DOCUMENT` blob,
  `NHIF_NO` varchar(20) DEFAULT NULL,
  `POLICE_GOOD_CONDUCT_CERTIFICATE` blob,
  `RESUME` blob,
  `SIGNED_CONTRACT` blob,
  `LANDLINE_NUMBER` varchar(20) DEFAULT NULL,
  `POST_BOX` varchar(20) DEFAULT NULL,
  `IDENTIFICATION_NO` varchar(20) DEFAULT NULL,
  `NSSF_NO` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`EMPLOYEE_ID`),
  KEY `FK_TEMP_EMPLOYEE_COUNTRY_LOOKUP` (`COUNTRY_ID`),
  KEY `FK_TEMP_EMPLOYEE_EMPLOYEE_TYPE_LOOKUP` (`EMPLOYEE_TYPE_LOOKUP_ID`),
  KEY `FK_TEMP_EMPLOYEE_PROVINCES_LOOKUP` (`PROVINCES_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `temp_employee_education`
--

DROP TABLE IF EXISTS `temp_employee_education`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `temp_employee_education` (
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
  `CERTIFICATE` blob,
  PRIMARY KEY (`EMPLOYEE_EDUCATION_ID`),
  KEY `FK_TEMP_EMPLOYEE_EDUCATION_PVDR` (`EMPLOYEE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `temp_employee_honor`
--

DROP TABLE IF EXISTS `temp_employee_honor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `temp_employee_honor` (
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
  KEY `FK_TEMP_EMPLOYEE_HONOR_PVDR` (`EMPLOYEE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `temp_employee_interest`
--

DROP TABLE IF EXISTS `temp_employee_interest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `temp_employee_interest` (
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
  KEY `FK_TEMP_EMPLOYEE_INTEREST_PVDR` (`EMPLOYEE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `temp_employee_prof_membership`
--

DROP TABLE IF EXISTS `temp_employee_prof_membership`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `temp_employee_prof_membership` (
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
  KEY `FK_TEMP_EMPLOYEE_PROF_MEMBERSHIP_PVDR` (`EMPLOYEE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `temp_employee_publication`
--

DROP TABLE IF EXISTS `temp_employee_publication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `temp_employee_publication` (
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
  KEY `FK_TEMP_EMPLOYEE_PUBLICATION_PVDR` (`EMPLOYEE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `temp_employee_salary`
--

DROP TABLE IF EXISTS `temp_employee_salary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `temp_employee_salary` (
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
  KEY `FK_TEMP_EMPLOYEE_SALARY_EMPLOYEE` (`EMPLOYEE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `temp_employment_history`
--

DROP TABLE IF EXISTS `temp_employment_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `temp_employment_history` (
  `EMPLOYMENT_HISTORY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `COMPANY_NAME` varchar(100) NOT NULL,
  `START_DT` date DEFAULT NULL,
  `END_DT` date DEFAULT NULL,
  `COMPANY_ADDRESS` varchar(250) DEFAULT NULL,
  `REPORTING_PERSON_DETAILS` varchar(50) DEFAULT NULL,
  `MANAGER_NM` varchar(50) DEFAULT NULL,
  `MANAGER_PHONE_NBR` varchar(20) DEFAULT NULL,
  `MANAGER_EMAIL_ID` varchar(50) DEFAULT NULL,
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
  KEY `FK_TEMP_EMPLOYMENT_HISTORY_EMPLOYEE` (`EMPLOYEE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `test_report2`
--

DROP TABLE IF EXISTS `test_report2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test_report2` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(500) DEFAULT NULL,
  `Email` varchar(500) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `Phone` varchar(10) DEFAULT NULL,
  `Age` int(11) DEFAULT NULL,
  `Test` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `till_balance`
--

DROP TABLE IF EXISTS `till_balance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `till_balance` (
  `TILL_BALANCE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PREV_BALANCE` double DEFAULT NULL,
  `CURRENT_BALANCE` double DEFAULT NULL,
  `ASOFDATE` date DEFAULT NULL,
  `PHARMACY_ID` int(11) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` int(11) DEFAULT NULL,
  `LAST_UPDATE_USER_ID` int(11) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`TILL_BALANCE_ID`),
  KEY `ihealth_till_bal_pharmacy_id_idx` (`PHARMACY_ID`),
  CONSTRAINT `ihealth_till_bal_pharmacy_id` FOREIGN KEY (`PHARMACY_ID`) REFERENCES `pharmacy` (`PHARMACY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tmpexp`
--

DROP TABLE IF EXISTS `tmpexp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tmpexp` (
  `Batch_No` varchar(30) DEFAULT NULL,
  `Expiry` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `unit_of_measurement`
--

DROP TABLE IF EXISTS `unit_of_measurement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unit_of_measurement` (
  `UNIT_OF_MEASUREMENT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `MEASUREMENT_CD` varchar(20) DEFAULT NULL,
  `MEASUREMENT_NAME` varchar(100) DEFAULT NULL,
  `MEASUREMENT_DESC` mediumtext CHARACTER SET latin1 COLLATE latin1_swedish_ci,
  `ACTIVE_S` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'Y',
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`UNIT_OF_MEASUREMENT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `USER_ROLES_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) DEFAULT NULL,
  `ROLE_ID` int(11) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`USER_ROLES_ID`),
  KEY `FK_USER_ROLE_USERS` (`USER_ID`),
  KEY `FK_USER_ROLE_ROLES` (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_NM` varchar(50) DEFAULT NULL,
  `PASSWORD` varchar(50) DEFAULT NULL,
  `CREATION_TS` timestamp NULL DEFAULT NULL,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `AUDIT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'ihealthpharm'
--
/*!50106 SET @save_time_zone= @@TIME_ZONE */ ;
/*!50106 DROP EVENT IF EXISTS `generateuniquecodereset` */;
DELIMITER ;;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;;
/*!50003 SET character_set_client  = utf8mb4 */ ;;
/*!50003 SET character_set_results = utf8mb4 */ ;;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;;
/*!50003 SET @saved_time_zone      = @@time_zone */ ;;
/*!50003 SET time_zone             = 'SYSTEM' */ ;;
/*!50106 CREATE*/ /*!50117 DEFINER=`root`@`localhost`*/ /*!50106 EVENT `generateuniquecodereset` ON SCHEDULE EVERY 1 DAY STARTS '2019-12-10 00:00:00' ON COMPLETION NOT PRESERVE ENABLE DO BEGIN
update ihealthpharm.generateuniquecode set unique_code_number=0;
END */ ;;
/*!50003 SET time_zone             = @saved_time_zone */ ;;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;;
/*!50003 SET character_set_client  = @saved_cs_client */ ;;
/*!50003 SET character_set_results = @saved_cs_results */ ;;
/*!50003 SET collation_connection  = @saved_col_connection */ ;;
DELIMITER ;
/*!50106 SET TIME_ZONE= @save_time_zone */ ;

--
-- Dumping routines for database 'ihealthpharm'
--
/*!50003 DROP PROCEDURE IF EXISTS `ACCOUNT_PAYABLES` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ACCOUNT_PAYABLES`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

TRUNCATE TABLE account_payables_working;

insert into account_payables_working select ap.SUPPLIER_NAME,ap.PAYMENT_NO,ap.SOURCE_REF,ap.INVOICE_NO,ap.PAYMENT_DATE,
ap.STATUS,ap.TOTAL_AMOUNT_PAID,ap.TOTAL_AMOUNT_TO_BE_PAID,ap.PAYMENT_STATUS,ap.SOURCE_TYPE,ap.APPROVED_BY,
ap.APPROVED_DATE,ap.APPROVED_DATE from account_payables ap;

SET BASE_SQL='select ifnull(ap.SUPPLIER_NAME," ") SUPPLIER_NAME,ap.PAYMENT_NO,ifnull(ap.SOURCE_REF," ") SOURCE_REF,ifnull(ap.INVOICE_NO," ") INVOICE_NO,
ap.PAYMENT_DATE,ap.STATUS,
ifnull(ap.TOTAL_AMOUNT_PAID,0) TOTAL_AMOUNT_PAID,
ifnull(ap.TOTAL_AMOUNT_TO_BE_PAID,0) TOTAL_AMOUNT_TO_BE_PAID,
ap.PAYMENT_STATUS,ifnull(ap.SOURCE_TYPE," ") SOURCE_TYPE,ifnull(e.FIRST_NM," ") FIRST_NM,
ifnull(ap.FROM_APPROVED_DATE," ") FROM_APPROVED_DATE,ap.TO_APPROVED_DATE from account_payables_working ap
 left join employee e on e.EMPLOYEE_ID=ap.APPROVED_BY';

SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE);

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ACCOUNT_RECEIVABLES` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ACCOUNT_RECEIVABLES`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

TRUNCATE TABLE account_receivables_working;

insert into account_receivables_working select ar.CUSTOMER_NAME,ar.RECEIPT_NO,ar.SOURCE_REF,ar.RECEIPT_DATE,
ar.STATUS,ar.AMOUNT_RECEIVED,ar.AMOUNT_TO_BE_RECEIVED,ar.PAYMENT_STATUS,ar.SOURCE_TYPE,ar.APPROVED_BY,
ar.APPROVED_DATE,ar.APPROVED_DATE from account_receivables ar;

SET BASE_SQL=  'select ar.CUSTOMER_NAME,ar.RECEIPT_NO,ar.SOURCE_REF,ar.RECEIPT_DATE,
ar.STATUS,ifnull(ar.AMOUNT_RECEIVED,0) AMOUNT_RECEIVED,ifnull(ar.AMOUNT_TO_BE_RECEIVED,0) AMOUNT_TO_BE_RECEIVED,ar.PAYMENT_STATUS,ar.SOURCE_TYPE,e.FIRST_NM,
ar.FROM_APPROVED_DATE,ar.TO_APPROVED_DATE from account_receivables_working ar
 inner join employee e on ar.APPROVED_BY=e.EMPLOYEE_ID';

SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE);

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `BANK_TRANSACTIONS` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `BANK_TRANSACTIONS`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

TRUNCATE TABLE bank_transactions_working;

insert into bank_transactions_working select bt.TRANSACTION_REF,bt.TRANSACTION_DATE,bt.TRANSACTION_DATE,
c.ACCOUNT_NO,c.ACCOUNT_NAME, concat(c.ACCOUNT_NAME,' : ',c.ACCOUNT_NO) AS PARTY,bt.MODE,bt.TRANSACTION_TYPE,
bt.AMOUNT,bt.BALANCE from bank_transactions bt inner join chart_of_accounts c on c.ACCOUNT_ID = bt.PARTY;

SET BASE_SQL=  'select bt.TRANSACTION_REF,bt.FROM_TRANSACTION_DATE,bt.TO_TRANSACTION_DATE,bt.ACCOUNT_NO_PARTY,bt.ACCOUNT_NAME_PARTY,bt.PARTY,bt.MODE,bt.TRANSACTION_TYPE,
bt.AMOUNT,bt.BALANCE from bank_transactions_working bt';

SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE);

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `CONSOLIDATED_STOCK_LIST` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `CONSOLIDATED_STOCK_LIST`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

SET BASE_SQL='select i.ITEM_NM,st.BATCH_NO,st.EXPIRY_DT,i.RACK_NO,i.SHELF_NO,
sum(st.QUANTITY) AS QUANTITY from stock st 
INNER JOIN items i ON st.ITEM_ID=i.ITEM_ID';
SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE,'group by i.ITEM_NM,st.BATCH_NO,st.EXPIRY_DT,i.RACK_NO,i.SHELF_NO');

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `CREDIT_NOTE` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `CREDIT_NOTE`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

SET BASE_SQL=  'select c.credit_note_no,c.credit_date,cu.FIRST_NM,
c.bill_id,c.amount,c.remarks,e.First_NM as Approved_By
from credit_note c inner join customer cu on c.CUSTOMER_ID=cu.CUSTOMER_ID
INNER JOIN employee e on c.APPROVED_BY = e.Employee_ID';

SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE);

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `CURRENT_STOCK_WITH_SALES_PRICE_AND_MARGIN` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `CURRENT_STOCK_WITH_SALES_PRICE_AND_MARGIN`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

SET BASE_SQL=  'select i.ITEM_NM, st.INVOICE_NO, st.BATCH_NO, st.EXPIRY_DT, 
ifnull(st.UNIT_PURCHASE_RATE,0) AS PURCHASE_RATE,
ifnull(st.PURCHASE_DISCOUNT_PERCENTAGE,0) AS PURCHASE_DISCOUNT_RATE,
ifnull(st.UNIT_SALE_RATE,0) AS SALE_RATE, 
ifnull(st.SALE_DISCOUNT_PERCENTAGE,0) as SALE_DISCOUNT_RATE, 
st.QUANTITY, st.PACK ,
round(st.PACK * ifnull(UNIT_PURCHASE_RATE,0) ,2) AS PURCHASE_PACK_PRICE,
round(st.PACK * ifnull(UNIT_SALE_RATE,0) ,2) AS SALE_PACK_PRICE,
round(100 * ((st.PACK * ifnull(UNIT_SALE_RATE,0)  - st.PACK * ifnull(st.UNIT_PURCHASE_RATE,0))  / (st.Pack * ifnull(UNIT_PURCHASE_RATE,0) )),2) AS MARGIN_PER,
round( ((st.PACK * ifnull(UNIT_SALE_RATE,0)  - st.PACK * ifnull(st.UNIT_PURCHASE_RATE,0))),2) AS MARGIN_AMT
from stock st, items i where st.ITEM_ID = i.ITEM_ID 
and st.QUANTITY != 0 order by 1';

SET @FINAL_SQL= CONCAT(BASE_SQL);

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `CUSTOMER_STATEMENT` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `CUSTOMER_STATEMENT`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN

DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

TRUNCATE TABLE account_receivables_working;

insert into account_receivables_working select ar.CUSTOMER_NAME,ar.RECEIPT_NO,ar.SOURCE_REF,ar.RECEIPT_DATE,
ar.STATUS,ar.AMOUNT_RECEIVED,ar.AMOUNT_TO_BE_RECEIVED,ar.PAYMENT_STATUS,ar.SOURCE_TYPE,ar.APPROVED_BY,
ar.APPROVED_DATE,ar.APPROVED_DATE from account_receivables ar;

SET BASE_SQL=  'select ar.CUSTOMER_NAME,ar.SOURCE_REF,ar.SOURCE_TYPE,ifnull(ar.AMOUNT_RECEIVED,0) debit,ifnull(ar.AMOUNT_TO_BE_RECEIVED,0) credit,
ar.FROM_APPROVED_DATE,ifnull(ar.AMOUNT_TO_BE_RECEIVED,0) outstanding,ar.TO_APPROVED_DATE from account_receivables_working ar';

SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE);

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `DEBIT_NOTE` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `DEBIT_NOTE`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

SET BASE_SQL='select d.debit_note_no,d.debit_date,d.invoice_id,
sp.SP_NAME,d.amount,d.remarks,e.First_NM as approved_by
from debit_note d inner join supplier sp on d.SUPPLIER_ID=sp.SUPPLIER_ID
inner join employee e on e.Employee_ID = d.Approved_By';

SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE);

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `DUMMY_BILL_LIST` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `DUMMY_BILL_LIST`(
	IN `WHERECLAUSE` VARCHAR(3000)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';


SET BASE_SQL='select p.FIRST_NM, s.BILL_DATE,s.BILL_CODE,st.EXPIRY_DT,s.CUSTOMER_NM,
si.UNIT_SALE_PRICE,i.ITEM_NM,si.SALE_QTY,m.MFR_NAME,si.BATCH_NO,si.DISCOUNT,
((si.UNIT_SALE_PRICE*si.SALE_QTY)-si.DISCOUNT) AS TOTAL_AMOUNT from sales s 
INNER JOIN provider p ON s.PROVIDER_ID=p.PROVIDER_ID 
 INNER JOIN sales_items si ON s.BILL_ID=si.BILL_ID
 inner join items i on si.ITEM_ID=i.ITEM_ID
 INNER JOIN manufacturer m ON i.MANUFACTURER_ID=m.MANUFACTURER_ID
INNER JOIN stock st ON si.STOCK_ID=st.STOCK_ID ';
SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE,'GROUP BY m.MFR_NAME ORDER BY m.MFR_NAME');
PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `EXCESS_STOCK_REPORT` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `EXCESS_STOCK_REPORT`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

SET BASE_SQL='select distinct i.ITEM_CD,i.ITEM_NM,st.QUANTITY,s.SALE_QTY from stock st 
INNER JOIN sales_items s ON st.STOCK_ID=s.STOCK_ID
INNER JOIN items i ON st.ITEM_ID=i.ITEM_ID';
SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE,'group by i.ITEM_NM,i.ITEM_CD');

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `FAST_MOVING_PRODUCT_DETAILS` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `FAST_MOVING_PRODUCT_DETAILS`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

SET BASE_SQL='select DISTINCT st.QUANTITY,i.ITEM_CD,i.ITEM_NM from stock st
INNER JOIN items i ON st.ITEM_ID=i.ITEM_ID GROUP BY i.ITEM_NM';
SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE);

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `GENERAL_LEDGER_ENTRIES_REPORT` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GENERAL_LEDGER_ENTRIES_REPORT`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';
TRUNCATE TABLE general_ledger_working;
insert into general_ledger_working select gl.Journal_ID,gl.Journal_Ref,gl.Entry_No,gl.Entry_Type,
gl.Party,gl.Counter_Party,gl.Entry_Date,gl.Entry_Date,gl.Debit,gl.Credit,gl.Balance from general_ledger gl;

SET BASE_SQL=  'select gl.Journal_ID,gl.Journal_Ref,gl.Entry_No,gl.Entry_Type,
gl.Party,gl.Counter_Party,gl.From_Entry_Date,gl.To_Entry_Date,gl.Debit,gl.Credit,gl.Balance from general_ledger_working gl';

SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE);

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `MANUFACTURER_WISE_SALES` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `MANUFACTURER_WISE_SALES`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

SET BASE_SQL='select distinct sv.BILL_ID,i.ITEM_CD,sv.FromDate,sv.ToDate,i.ITEM_NM,
si.SALE_QTY,si.UNIT_SALE_PRICE,(si.UNIT_SALE_PRICE*si.SALE_QTY) AS SUB_TOTAL,si.DISCOUNT,
((si.UNIT_SALE_PRICE*si.SALE_QTY)-si.DISCOUNT) AS TOTAL_AMOUNT,si.BATCH_NO  from sales_view sv
INNER JOIN sales_items si ON sv.BILL_ID=si.BILL_ID
INNER JOIN items i ON si.ITEM_ID=i.ITEM_ID';
SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE,'ORDER BY sv.FromDate');

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `MINIMUM_STOCK_DETAILS` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `MINIMUM_STOCK_DETAILS`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';
SET BASE_SQL= 'select i.ITEM_CD,i.ITEM_NM,sum(st.QUANTITY) as QUANTITY from stock st 
INNER JOIN items i ON st.ITEM_ID=i.ITEM_ID
 group by i.Item_cd, i.Item_nm 
 having sum(st.quantity) < ( select a.reorder_level from items a
 where a.Item_cd = i.item_cd and a.item_nm = i.item_nm)';
 
SET @FINAL_SQL= CONCAT(BASE_SQL);

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `OUT_OF_STOCK_LIST` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`ihealthpharm`@`%` PROCEDURE `OUT_OF_STOCK_LIST`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

SET BASE_SQL='select DISTINCT i.ITEM_CD,i.ITEM_NM,sum(st.QUANTITY) AS QUANTITY from stock st 
INNER JOIN items i ON st.ITEM_ID=i.ITEM_ID group by i.ITEM_CD,i.ITEM_NM';
SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE);

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `PETTY_CASH_EXPENDITURE` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `PETTY_CASH_EXPENDITURE`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

truncate table petty_cash_expenditure;

insert into petty_cash_expenditure(
select g.ENTRY_TYPE,g.PARTY,
e.EXPENSES_REF,g.COUNTER_PARTY,g.DEBIT,g.CREDIT,
g.BALANCE,g.ENTRY_DATE,g.ENTRY_DATE,g.CREATION_TS from general_ledger g  
left outer join expenses e on g.ENTRY_NO=e.EXPENSE_NO
where g.ENTRY_TYPE="Petty Cash" or g.ENTRY_TYPE="Exp PettyCash" order by g.CREATION_TS ASC);

SET BASE_SQL='select pe.ENTRY_TYPE,pe.PARTY,pe.CREATION_TS,
pe.EXPENSES_REF,pe.COUNTER_PARTY,pe.DEBIT,pe.CREDIT,
pe.BALANCE,pe.FROM_DATE,pe.TO_DATE from  petty_cash_expenditure pe';


SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE);

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `PRODUCT_OFFER_LIST` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `PRODUCT_OFFER_LIST`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

SET BASE_SQL=  'select DISTINCT inv.INVOICE_NO,inv.INVOICE_DT,i.ITEM_NM,
m.MFR_NAME,st.BATCH_NO,sp.SP_NAME,init.PACK,init.BONUS from stock st 
inner join invoice_items init on init.ITEM_ID=st.ITEM_ID
inner join invoice inv on inv.INVOICE_ID=init.INVOICE_ID
inner join items i on st.ITEM_ID=i.ITEM_ID
 inner join manufacturer m on i.MANUFACTURER_ID=m.MANUFACTURER_ID
inner join supplier sp on inv.SUPPLIER_ID=sp.SUPPLIER_ID';

SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE,'GROUP BY inv.INVOICE_NO');

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `PURCHASE_BY_PRODUCT_DETAILS` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `PURCHASE_BY_PRODUCT_DETAILS`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

SET BASE_SQL=  'select  distinct sp.SP_NAME,inv.INVOICE_NO,inv.INVOICE_DT,i.ITEM_NM,
m.MFR_NAME,st.BATCH_NO,poi.QUANTITY,st.EXPIRY_DT,po.PO_AMOUNT
from purchase_order_items poi
inner join purchase_order po on poi.PURCHASE_ORDER_ID=po.PURCHASE_ORDER_ID
inner join supplier sp on po.SUPPLIER_ID=sp.SUPPLIER_ID
inner join invoice inv on sp.SUPPLIER_ID=inv.SUPPLIER_ID
inner join stock st on inv.SUPPLIER_ID=st.SUPPLIER_ID
inner join items i on poi.ITEM_ID=i.ITEM_ID
inner join manufacturer m on i.MANUFACTURER_ID=m.MANUFACTURER_ID';

SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE,'order by inv.INVOICE_DT');

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `PURCHASE_DETAILS_BATCHNO` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `PURCHASE_DETAILS_BATCHNO`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

SET BASE_SQL=  'select DISTINCT si.BATCH_NO,po.PURCHASE_ORDER_ID,
sp.SP_NAME,inv.INVOICE_DT,INVOICE_NO,i.ITEM_NM,poi.QUANTITY,inv.INVOICE_AMOUNT from purchase_order po 
inner join purchase_order_items poi on poi.PURCHASE_ORDER_ID=po.PURCHASE_ORDER_ID
inner join items i on i.ITEM_ID=poi.ITEM_ID
inner join sales_items si on si.ITEM_ID=i.ITEM_ID
inner join invoice inv on inv.SUPPLIER_ID=si.SPPLIER_ID
inner join supplier sp on si.SPPLIER_ID=sp.SUPPLIER_ID';


SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE,'order by inv.INVOICE_DT');

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `PURCHASE_DETAILS_ITEM` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `PURCHASE_DETAILS_ITEM`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

SET BASE_SQL=  'select DISTINCT si.BATCH_NO,po.PURCHASE_ORDER_ID,sp.SP_NAME,inv.INVOICE_DT,INVOICE_NO,i.ITEM_NM,poi.QUANTITY,inv.INVOICE_AMOUNT from purchase_order po 
inner join purchase_order_items poi on poi.PURCHASE_ORDER_ID=po.PURCHASE_ORDER_ID
inner join items i on i.ITEM_ID=poi.ITEM_ID
inner join sales_items si on si.ITEM_ID=i.ITEM_ID
inner join invoice inv on inv.SUPPLIER_ID=si.SPPLIER_ID
inner join supplier sp on si.SPPLIER_ID=sp.SUPPLIER_ID';

SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE,'order by inv.INVOICE_DT');

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `PURCHASE_INVOICE_DETAILS` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `PURCHASE_INVOICE_DETAILS`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';


SET BASE_SQL=  'select distinct sp.SP_NAME,ifnull(inv.INVOICE_NO," ") INVOICE_NO,ifnull(sp.CITY_NM," ") CITY_NM,ifnull(inv.GRN_NO," ") GRN_NO,
inv.INVOICE_DT,i.ITEM_NM,
init.BATCH_NO,init.QUANTITY_APPROVED,init.EXPIRY_DT,
ifnull(init.BONUS,0) BONUS,ifnull(ROUND(init.PACK_PRICE,2),0) UNIT_RATE,
ifnull(ROUND(init.DISCOUNT_PERCENTAGE,2),0) DISCOUNT,
ifnull(ROUND((init.QUANTITY_APPROVED*init.PACK_PRICE) - (init.QUANTITY_APPROVED*init.PACK_PRICE*init.DISCOUNT_PERCENTAGE/100),2),0)
AS TOTAL_VALUE,
ifnull(Round((tc.CATEGORY_VALUE/100)*(init.QUANTITY_APPROVED*init.PACK_PRICE * ( 1 -(init.DISCOUNT_PERCENTAGE/100))),2),0)  AS VAT_AMT,
ifnull(ROUND(inv.HANDLING_CHARGES,2),0) HANDLING_CHARGES
from invoice_items init
inner join invoice inv on init.INVOICE_ID=inv.INVOICE_ID
inner join items i on init.ITEM_ID=i.ITEM_ID
inner join supplier sp on inv.SUPPLIER_ID=sp.SUPPLIER_ID
left join taxcategory tc on tc.TAXCATEGORY_ID=init.TAX';


SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE,'ORDER BY inv.INVOICE_DT');

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `PURCHASE_INVOICE_REPORT` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `PURCHASE_INVOICE_REPORT`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

SET BASE_SQL=  'select sp.SP_NAME,inv.INVOICE_DT,inv.INVOICE_NO,init.SALE_TAX_PERCENTAGE,init.SALE_TAX_AMOUNT,
sum(init.SALE_TAX_AMOUNT) as TOTAL_VAT,
inv.DISCOUNT,inv.ROUND_OFF,inv.INVOICE_AMOUNT,init.QUANTITY_APPROVED,init.TOTAL_QUANTITY 
from invoice inv 
inner join invoice_items init on init.INVOICE_ID=inv.INVOICE_ID 
inner join supplier sp on inv.SUPPLIER_ID=sp.SUPPLIER_ID';

SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE);

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `PURCHASE_MARGIN` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `PURCHASE_MARGIN`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN

DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

SET BASE_SQL=  'select DISTINCT i.ITEM_NM,init.BATCH_NO,init.QUANTITY_APPROVED,
init.UNIT_RATE,init.MRP,init.DISCOUNT,
ROUND(((init.QUANTITY_APPROVED*init.MRP)-(init.QUANTITY_APPROVED*init.UNIT_RATE)),2) AS MARGIN_AMT,
ROUND(ROUND(((init.QUANTITY_APPROVED*init.MRP)-(init.QUANTITY_APPROVED*init.UNIT_RATE)),2)*100/(init.QUANTITY_APPROVED*init.UNIT_RATE),2) AS MARGIN,
ROUND((init.MRP-init.UNIT_RATE),2) AS MARGIN_AMT_ITEM
from invoice_items init 
inner join stock st on init.ITEM_ID=st.ITEM_ID
inner join items i on init.ITEM_ID=i.ITEM_ID
inner join supplier sp on st.SUPPLIER_ID=sp.SUPPLIER_ID';

SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE);

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `PURCHASE_MARGIN_COMPARISON` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `PURCHASE_MARGIN_COMPARISON`(
	IN `WHERECLAUSE` VARCHAR(5000)
)
BEGIN

DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

SET BASE_SQL='select DISTINCT i.ITEM_NM,sp.SP_NAME,init.BATCH_NO,init.QUANTITY_APPROVED,init.BONUS,
init.UNIT_RATE,init.MRP,init.DISCOUNT,FORMAT((init.UNIT_RATE*init.QUANTITY_APPROVED),2) AS NET_AMOUNT,
ROUND(((init.QUANTITY_APPROVED*init.MRP)-(init.QUANTITY_APPROVED*init.UNIT_RATE)),2) AS MARGIN_AMT,
ROUND((init.MRP-init.UNIT_RATE),2) AS MARGIN_AMT_ITEM
from invoice_items init 
inner join stock st on st.ITEM_ID=init.ITEM_ID
inner join items i on init.ITEM_ID=i.ITEM_ID
inner join supplier sp on st.SUPPLIER_ID=sp.SUPPLIER_ID';

SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE,'group by i.ITEM_NM');

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `PURCHASE_ORDER_DETAILS` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `PURCHASE_ORDER_DETAILS`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

SET BASE_SQL=  'select DISTINCT ifnull(po.PURCHASE_ORDER_NO," ") PURCHASE_ORDER_NO,
po.PURCHASE_ORDER_DT,sp.SP_NAME,ifnull(sp.CITY_NM," ") CITY_NM,i.ITEM_NM,poi.QUANTITY,ifnull(poi.BONUS,0) BONUS,
ifnull(ROUND(poi.PACK_PURCHASE_PRICE,2),0) UNIT_RATE,
ifnull(ROUND(poi.DISCOUNT_PERCENTAGE,2),0) DISCOUNT,
ifnull(ROUND((poi.QUANTITY*poi.PACK_PURCHASE_PRICE) - (poi.QUANTITY*poi.PACK_PURCHASE_PRICE*poi.DISCOUNT_PERCENTAGE/100),2),0)
AS TOTAL_VALUE,ifnull(poi.NET_AMT,0) NET_AMT,
ifnull(ROUND(po.OTHER_CHARGES,2),0) OTHER_CHARGES,
ifnull(ROUND(round(tc.CATEGORY_VALUE/100,2)*((poi.QUANTITY*poi.PACK_PURCHASE_PRICE * ( 1 -(poi.DISCOUNT_PERCENTAGE/100)))),2),0) AS VAT_AMT,
poi.PACK from purchase_order po 
inner join purchase_order_items poi on poi.PURCHASE_ORDER_ID=po.PURCHASE_ORDER_ID
inner join items i on poi.ITEM_Id=i.ITEM_ID 
inner join supplier sp on po.SUPPLIER_ID=sp.SUPPLIER_ID
left join taxcategory tc on tc.TAXCATEGORY_ID=poi.TAX';


SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE,'GROUP BY i.ITEM_NM ORDER BY po.PURCHASE_ORDER_DT');

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `PURCHASE_ORDER_SUMMARY` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `PURCHASE_ORDER_SUMMARY`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

SET BASE_SQL=  'select DISTINCT po.PURCHASE_ORDER_ID,po.PURCHASE_ORDER_NO,
po.PURCHASE_ORDER_DT,sp.SP_NAME,sp.CITY_NM,dt.TYPE,i.ITEM_NM,poi.QUANTITY,poi.BONUS,
poi.PACK,sp.PAYMENT_TERMS from purchase_order po 
inner join purchase_order_items poi on poi.PURCHASE_ORDER_ID=po.PURCHASE_ORDER_ID
inner join items i on poi.ITEM_Id=i.ITEM_ID 
inner join supplier sp on po.SUPPLIER_ID=sp.SUPPLIER_ID
inner join delivery_types dt on po.DELIVERY_TYPE_ID=dt.DELIVERY_TYPE_ID';

SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE);

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `PURCHASE_PRICE_VALUE_FOR_CURRENT_STOCK` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `PURCHASE_PRICE_VALUE_FOR_CURRENT_STOCK`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

truncate table purchaseprice_value_for_current_stock_working;

insert into purchaseprice_value_for_current_stock_working (
select distinct i.ITEM_NM, st.BATCH_NO, st.EXPIRY_DT, st.QUANTITY,ifnull(st.PURCHASE_DISCOUNT_PERCENTAGE,0)
as PURCHASE_DISCOUNT_PERCENTAGE, ifnull(st.UNIT_PURCHASE_RATE,0) AS UNIT_PURCHASE_RATE,
tc.CATEGORY_CODE,
ifnull(Round((tc.CATEGORY_VALUE/100)*(st.QUANTITY*st.UNIT_PURCHASE_RATE),2),0) AS VAT_AMT,
ifnull(round(ifnull(st.QUANTITY,0) * st.UNIT_PURCHASE_RATE * ( 1 - ifnull(st.PURCHASE_DISCOUNT_PERCENTAGE,0)/100) * 
1+(tc.CATEGORY_VALUE/100),2),0) as PURCHASE_VALUE,
date_format(st.LAST_UPDATE_TS,'%y-%m-%d'),date_format(st.LAST_UPDATE_TS,'%y-%m-%d'),
st.LAST_UPDATE_TS
from stock_history st left join stock s on s.STOCK_ID=st.STOCK_ID
left join taxcategory tc on tc.TAXCATEGORY_ID=s.TAX_CATEGORY_ID
inner join items i on i.ITEM_ID = st.ITEM_ID and i.ITEM_NM is not null,
(select max(sh.LAST_UPDATE_TS) LAST_UPDATED_TS,it.ITEM_NM AS productname FROM stock_history sh
inner join items it on it.ITEM_ID = sh.ITEM_ID and it.ITEM_NM is not null group by it.ITEM_NM) maxdates 
WHERE maxdates.productname=i.ITEM_NM and maxdates.LAST_UPDATED_TS=st.LAST_UPDATE_TS
ORDER BY i.ITEM_NM DESC);


SET BASE_SQL=  'select st.ITEM_NM,st.BATCH_NO,st.EXPIRY_DT,
ifnull(st.QUANTITY,0) AS QUANTITY,st.PURCHASE_DISCOUNT_PERCENTAGE,st.UNIT_PURCHASE_RATE,
st.CATEGORY_CODE,ifnull(st.VAT_AMT,0) AS VAT_AMT,
ifnull(st.PURCHASE_VALUE,0) AS PURCHASE_VALUE,
date_format(st.LAST_UPDATE_TS,"%d-%m-%y %h:%i %p") AS LAST_UPDATE_TS 
from purchaseprice_value_for_current_stock_working st';

SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE,'order by st.LAST_UPDATE_TS DESC');

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `PURCHASE_REGISTER_LIST` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `PURCHASE_REGISTER_LIST`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

SET BASE_SQL=  'select DISTINCT inv.INVOICE_NO,inv.INVOICE_DT,
sp.SP_NAME,pt.TYPE,po.PO_AMOUNT from purchase_order po 
inner join invoice inv on inv.SUPPLIER_ID=po.SUPPLIER_ID
inner join supplier sp on inv.SUPPLIER_ID=sp.SUPPLIER_ID
inner join payment_types pt';


SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE);

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `PURCHASE_RETURNS` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `PURCHASE_RETURNS`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

SET BASE_SQL='select distinct sp.SP_NAME,
ifnull(inv.INVOICE_NO," ") INVOICE_NO,
ifnull(pr.PURCHASE_RETURN_NO," ") PURCHASE_RETURN_NO,
ifnull(sp.CITY_NM," ") CITY_NM,
ifnull(inv.GRN_NO," ") GRN_NO,
pr.PURCHASE_RETURN_DATE,
inv.INVOICE_DT,
i.ITEM_NM,
prit.BATCH_NO,
prit.RETURN_QUANTITY AS QUANTITY ,init.EXPIRY_DT,
ifnull(init.BONUS,0) BONUS,
ifnull(ROUND(init.UNIT_RATE,2),0) UNIT_RATE,
ifnull(ROUND(init.DISCOUNT_PERCENTAGE,2),0) DISCOUNT,
ifnull(ROUND((prit.RETURN_QUANTITY*init.UNIT_RATE) - (prit.RETURN_QUANTITY*init.UNIT_RATE*init.DISCOUNT_PERCENTAGE/100),2),0)
AS TOTAL_VALUE,
ifnull(ROUND(tc.CATEGORY_VALUE/100*((prit.RETURN_QUANTITY*init.UNIT_RATE *( 1 -(init.DISCOUNT_PERCENTAGE/100)))),2),0) AS VAT_AMT,
ifnull(ROUND(pr.CHARGES,2),0) HANDLING_CHARGES
from purchase_return_item prit
inner join purchase_return pr on pr.PURCHASE_RETURN_ID=prit.PURCHASE_RETURN_ID
inner join invoice_items init on init.INVOICE_ID=pr.INVOICE_ID
inner join invoice inv on inv.INVOICE_ID=pr.INVOICE_ID
inner join items i on prit.ITEM_ID=i.ITEM_ID
inner join stock st on st.ITEM_ID=i.ITEM_ID
inner join supplier sp on pr.SUPPLIER_ID=sp.SUPPLIER_ID 
left join taxcategory tc on tc.TAXCATEGORY_ID=init.tax'; 

SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE);

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SALES_BY_PRODUCT_DETAILS` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SALES_BY_PRODUCT_DETAILS`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN

DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';
SET BASE_SQL=  'select DISTINCT s.CUSTOMER_NM,s.BILL_CODE,s.BILL_DATE,
p.FIRST_NM,i.ITEM_NM,m.MFR_NAME,si.BATCH_NO,st.EXPIRY_DT,si.SALE_QTY,
si.UNIT_SALE_PRICE,si.DISCOUNT,((si.UNIT_SALE_PRICE*si.SALE_QTY)-si.DISCOUNT) AS TOTAL_AMOUNT
from sales_items si 
inner join sales s on si.BILL_ID=s.BILL_ID
inner join items i on si.ITEM_ID=i.ITEM_ID
inner join stock st on st.STOCK_ID=si.STOCK_ID
inner join manufacturer m on i.MANUFACTURER_ID=m.MANUFACTURER_ID
inner join provider p on s.PROVIDER_ID=p.PROVIDER_ID';

SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE,'order by s.BILL_DATE');


PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SALES_BY_PRODUCT_DETAILS_DATE` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SALES_BY_PRODUCT_DETAILS_DATE`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN

DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

SET BASE_SQL=  'select DISTINCT s.BILL_DATE,

from sales_items si 
inner join sales s on si.BILL_ID=s.BILL_ID
inner join items i on si.ITEM_ID=i.ITEM_ID
inner join stock st on st.STOCK_ID=si.STOCK_ID
inner join manufacturer m on i.MANUFACTURER_ID=m.MANUFACTURER_ID
inner join provider p on s.PROVIDER_ID=p.PROVIDER_ID';

SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE);

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SALES_BY_PRODUCT_SUMMARY` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SALES_BY_PRODUCT_SUMMARY`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

SET BASE_SQL='select  s.BILL_CODE, s.BILL_DATE, i.ITEM_NM, si.BATCH_NO, ifnull(si.SALE_QTY,0) AS SALE_QTY,
round(ifnull(si.UNIT_SALE_PRICE,0),2) AS UNIT_SALE_PRICE , 
st.EXPIRY_DT,s.CUSTOMER_NM,ifnull(si.QTY_FREE,0) AS QTY_FREE,
round(ifnull(si.DISCOUNT_PERCENTAGE,0),2) AS DISC_PER,
ifnull(round(sum(si.SALE_QTY * (si.SALE_QTY / (ifnull(si.QTY_FREE,0) + si.SALE_QTY)) * ( ( 1 - ifnull(si.DISCOUNT_PERCENTAGE,0) /100) * si.UNIT_SALE_PRICE)),2),0) AS TOTAL_AMOUNT
from SALES_ITEMS si inner join SALES s on si.BILL_ID = s.BILL_ID
inner join ITEMS i on i.ITEM_ID = si.ITEM_ID
inner join STOCK st on  si.STOCK_ID = st.STOCK_ID';

SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE,'group by s.BILL_CODE, s.BILL_DATE,si.BATCH_NO, i.ITEM_NM, si.SALE_QTY,si.UNIT_SALE_PRICE order by s.BILL_DATE');

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SALES_CANCELLATION_LIST` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SALES_CANCELLATION_LIST`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

SET BASE_SQL='select DISTINCT s.BILL_CODE,s.BILL_DATE,s.CUSTOMER_NM,
p.FIRST_NM,i.ITEM_NM,s.TOTAL_QTY,m.MFR_NAME,
si.BATCH_NO,st.EXPIRY_DT,s.TOTAL_AMOUNT  from sales s 
inner join sales_items si on s.BILL_ID=si.BILL_ID
 INNER JOIN provider p ON s.PROVIDER_ID=p.PROVIDER_ID 
 INNER JOIN items i ON si.ITEM_ID=i.ITEM_ID
 inner join stock st on st.ITEM_ID=i.ITEM_ID
 INNER JOIN manufacturer m on i.MANUFACTURER_ID=m.MANUFACTURER_ID ';
SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE,'GROUP BY s.BILL_CODE ORDER BY s.BILL_DATE');

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SALES_PROFIT_ATTRIBUTION` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SALES_PROFIT_ATTRIBUTION`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

TRUNCATE TABLE sales_profit_attribution_working ;

insert into sales_profit_attribution_working(
select  s.BIll_CODE, s.BILL_DATE, s.BILL_DATE, i.ITEM_NM, si.BATCH_NO,
ifnull(si.SALE_QTY,0) AS SALE_QTY, ifnull(si.QTY_FREE,0) as 'QTY FREE',
round(ifnull(si.UNIT_PURCHASE_PRICE,0) ,2) AS 'P Price',
round(ifnull(st.PURCHASE_DISCOUNT_PERCENTAGE,0),2) 
AS 'P Disc%' ,
round(ifnull(si.UNIT_SALE_PRICE,0),2) AS 'S Price' , 
round(ifnull(si.DISCOUNT_PERCENTAGE,0),2) AS 'S Disc%', 
ifnull(si.VAT,0) AS VAT, ifnull(si.SALE_AMOUNT,0) AS SALE_AMOUNT,
round(sum((si.SALE_QTY-ifnull(si.QTY_FREE,0)) * (( ( 1 - ifnull(si.DISCOUNT_PERCENTAGE,0) /100) * si.UNIT_SALE_PRICE)  -
 ( ( 1 - ifnull(st.PURCHASE_DISCOUNT_PERCENTAGE,0) /100) * si.UNIT_PURCHASE_PRICE) ) ),2)  AS
'PROFIT',
round(sum((si.SALE_QTY-ifnull(si.QTY_FREE,0)) * (( ( 1 - ifnull(si.DISCOUNT_PERCENTAGE,0) /100) * si.UNIT_SALE_PRICE)  -
 ( ( 1 - ifnull(st.PURCHASE_DISCOUNT_PERCENTAGE,0) /100) * si.UNIT_PURCHASE_PRICE) )  /
 ROUND((si.SALE_QTY-ifnull(st.BONUS,0))*( ( 1 - ifnull(st.PURCHASE_DISCOUNT_PERCENTAGE,0) /100) * si.UNIT_PURCHASE_PRICE),2))*100,2) AS PROFIT_PER 
 from SALES_ITEMS si, SALES s ,ITEMS  i, STOCK st where st.ITEM_ID = si.ITEM_ID
and i.ITEM_ID = si.ITEM_ID  and si.STOCK_ID = st.STOCK_ID
 and si.BILL_ID = s.BILL_ID 
 group by  s.BILL_CODE, s.BILL_DATE,si.BATCH_NO, i.ITEM_NM, si.SALE_QTY,si.QTY_FREE,st.PURCHASE_DISCOUNT_PERCENTAGE,si.UNIT_SALE_PRICE,  
si.DISCOUNT_PERCENTAGE, si.VAT, si.SALE_AMOUNT
);

SET BASE_SQL='select sa.BILL_CODE,sa.FROM_BILL_DATE,sa.ITEM_NM,
sa.BATCH_NO,sa.SALE_QTY,sa.QTY_FREE,
sa.UNIT_PURCHASE_PRICE,sa.PURCHASE_DISCOUNT_PERCENTAGE,
sa.UNIT_SALE_PRICE,sa.DISCOUNT_PERCENTAGE,sa.VAT,sa.SALE_AMOUNT,
ifnull(sa.PROFIT,0) AS PROFIT,ifnull(sa.PROFIT_PER,0) AS PROFIT_PER from sales_profit_attribution_working sa';

SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE);

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SALES_RECEIPT` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SALES_RECEIPT`(
	IN `WHERECLAUSE` VARCHAR(3000)
)
BEGIN
DECLARE BASE_SQL1  VARCHAR(5000) DEFAULT '';
DECLARE BASE_SQL2  VARCHAR(5000) DEFAULT '';
DECLARE SERVEDBYWHERE  VARCHAR(5000) DEFAULT '';
set SERVEDBYWHERE= WHERECLAUSE;
set SERVEDBYWHERE = REPLACE(SERVEDBYWHERE,"1 = 1","");
set SERVEDBYWHERE = REPLACE(SERVEDBYWHERE,"WHERE","");

SET BASE_SQL1=  'SELECT 
  i.ITEM_NM, 
  ifnull(si.SALE_QTY,0) SALE_QTY,
  s.LAST_UPDATE_TS,
  ifnull(ROUND(si.UNIT_SALE_PRICE,2),0) MRP,
  ifnull(s.TOTAL_QTY,0) TOTAL_QTY,
  ifnull(si.VAT,0) EFFECTIVE_VAT,
  ifnull(s.TOTAL_PRODUCTS,0) TOTAL_PRODUCTS,
  ifnull(ROUND(s.OVERALL_DISCOUNT,2),0) OVERALL_DISCOUNT,
  ifnull(ROUND((s.TOTAL_AMOUNT-ifnull(s.OVERALL_DISCOUNT,0)),2),0) TOTAL_AMOUNT,
  ifnull(s.NET_AMOUNT,0) NET_AMOUNT,
  ifnull(ROUND(s.PAID_AMOUNT,2),0) PAID_AMOUNT,
  ifnull(s.BALANCE_AMOUNT,0) BALANCE_AMOUNT,
  tc.CATEGORY_CODE AS TAX,
  ifnull(si.VAT,0) VAT,
  ifnull(ROUND(s.VAT_AMT,2),0) VAT_AMT,
  s.BILL_CODE,
   ifnull(s.REMARKS,"-") REMARKS,
  substring_index(s.PAYMENT_STATUS," ",2) AS PAYMENT_STATUS,
  e.FIRST_NM,
  ifnull(concat(p.FIRST_NM," ",substring(p.LAST_NM,1,1)),"N/A") AS DOCTOR_NM,
  substring(ifnull(concat(c.FIRST_NM," ",c.LAST_NM),"N/A"),1,50) AS CUSTOMER_NM,
  ifnull(ROUND(si.SALE_AMOUNT/si.SALE_QTY,2),0) AS PRICE,
  si.SALE_AMOUNT,
  ROW_NUMBER() OVER (PARTITION BY s.bill_id ORDER BY sales_items_id) AS S_NO,
    (select em.FIRST_NM FROM sales s,employee em where em.EMPLOYEE_ID=s.LAST_UPDATE_USER_ID ';
 SET BASE_SQL2= ') AS SERVED_BY
  FROM sales s 
INNER JOIN sales_items si ON s.BILL_ID = si.BILL_ID
INNER JOIN items i ON i.ITEM_ID = si.ITEM_ID 
inner join customer c on c.CUSTOMER_ID=s.CUSTOMER_ID 
inner join employee e on e.EMPLOYEE_ID=s.EMPLOYEE_ID
inner join provider p on s.PROVIDER_ID=p.PROVIDER_ID
inner join taxcategory tc on tc.TAXCATEGORY_ID=si.TAX_CATEGORY_ID';


SET @FINAL_SQL= CONCAT(BASE_SQL1,SERVEDBYWHERE,BASE_SQL2,WHERECLAUSE);

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;


END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SALES_REGISTER_AREAWISE_DETAILS` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SALES_REGISTER_AREAWISE_DETAILS`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

SET BASE_SQL=  'select distinct sp.CITY_NM,s.BILL_ID,s.BILL_CODE,
s.BILL_DATE,s.CUSTOMER_NM,pt.TYPE,s.PAID_AMOUNT,s.TOTAL_AMOUNT from sales s 
inner join sales_items si on s.BILL_ID=si.BILL_ID
inner join supplier sp on si.SPPLIER_ID=sp.SUPPLIER_ID
inner join payment_types pt';

SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE,'GROUP BY s.BILL_CODE ORDER BY s.BILL_DATE');

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SALES_REGISTER_DETAILS` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SALES_REGISTER_DETAILS`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

TRUNCATE TABLE SALES_REGISTER_DETAILS_working;

INSERT INTO SALES_REGISTER_DETAILS_working 
select DISTINCT s.BILL_CODE,s.BILL_DATE,s.BILL_DATE,s.CUSTOMER_ID,
pt.TYPE, s.CASH_AMOUNT ,s.PAID_AMOUNT,s.BALANCE_AMOUNT,s.PAYMENT_STATUS,
round((ifnull(s.VAT_AMT,0)*ifnull(s.CASH_AMOUNT,0))/(s.NET_AMOUNT),2) AS VAT_AMT from sales s 
INNER JOIN sales_items si ON s.Bill_ID=si.BILL_ID and s.CASH_AMOUNT != 0.0 and s.PAYMENT_STATUS != "CANCEL" and s.PAYMENT_STATUS != "DUMMY BILL"
INNER JOIN payment_types pt on pt.TYPE = "CASH"

union

select DISTINCT s.BILL_CODE,s.BILL_DATE,s.BILL_DATE,s.CUSTOMER_ID,
pt.TYPE,s.CREDIT_AMOUNT,s.PAID_AMOUNT,s.BALANCE_AMOUNT,s.PAYMENT_STATUS,
round((ifnull(s.VAT_AMT,0)*ifnull(s.CREDIT_AMOUNT,0))/(s.NET_AMOUNT),2) AS VAT_AMT from sales s 
INNER JOIN sales_items si ON s.Bill_ID=si.BILL_ID and s.CREDIT_AMOUNT != 0.0 and s.PAYMENT_STATUS != "CANCEL" and s.PAYMENT_STATUS != "DUMMY BILL"
INNER JOIN payment_types pt on pt.TYPE = "CREDIT"

union

select DISTINCT s.BILL_CODE,s.BILL_DATE,s.BILL_DATE,s.CUSTOMER_ID,
pt.TYPE,s.UPI_AMOUNT,s.PAID_AMOUNT,s.BALANCE_AMOUNT,s.PAYMENT_STATUS,
round((ifnull(s.VAT_AMT,0)*ifnull(s.UPI_AMOUNT,0))/(s.NET_AMOUNT),2) AS VAT_AMT from sales s 
INNER JOIN sales_items si ON s.Bill_ID=si.BILL_ID and UPI_AMOUNT != 0.0 and 
s.PAYMENT_STATUS != "CANCEL" and s.PAYMENT_STATUS != "DUMMY BILL"
INNER JOIN payment_types pt on pt.TYPE = "M-PESA"

union

select DISTINCT s.BILL_CODE,s.BILL_DATE,s.BILL_DATE,s.CUSTOMER_ID,
pt.TYPE,s.CREDIT_CARD_AMOUNT ,s.PAID_AMOUNT,s.BALANCE_AMOUNT,s.PAYMENT_STATUS,
round((ifnull(s.VAT_AMT,0)*ifnull(s.CREDIT_CARD_AMOUNT,0))/(s.NET_AMOUNT),2) AS VAT_AMT from sales s 
INNER JOIN sales_items si ON s.Bill_ID=si.BILL_ID and CREDIT_CARD_AMOUNT != 0.0 and s.PAYMENT_STATUS != "CANCEL" and s.PAYMENT_STATUS != "DUMMY BILL"
INNER JOIN payment_types pt on pt.TYPE = "CARD"

union

select DISTINCT s.BILL_CODE,s.BILL_DATE,s.BILL_DATE,s.CUSTOMER_ID,
pt.TYPE,s.CHEQUE_AMT,s.PAID_AMOUNT,s.BALANCE_AMOUNT,s.PAYMENT_STATUS,
round((ifnull(s.VAT_AMT,0)*ifnull(s.CHEQUE_AMT,0))/(s.NET_AMOUNT),2) AS VAT_AMT from sales s 
INNER JOIN sales_items si ON s.Bill_ID=si.BILL_ID and CHEQUE_AMT != 0.0 and s.PAYMENT_STATUS != "CANCEL" and s.PAYMENT_STATUS != "DUMMY BILL"
INNER JOIN payment_types pt on pt.TYPE = "CHEQUE";

SET BASE_SQL = 'select DISTINCT w.BILL_CODE,w.FROM_BILL_DATE,w.TO_BILL_DATE,CONCAT(c.FIRST_NM," ", c.LAST_NM) as CUSTOMER_NM,
w.TYPE, w.AMOUNT,w.PAID_AMOUNT,round(ifnull(w.VAT_AMT,0),2) AS VAT_AMT,w.BALANCE_AMOUNT,w.PAYMENT_STATUS from
SALES_REGISTER_DETAILS_working w
inner join customer c on c.CUSTOMER_ID = w.CUSTOMER_ID';


SET @FINAL_SQL= concat(BASE_SQL,WHERECLAUSE," ORDER BY w.FROM_BILL_DATE DESC");

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SALES_REPORT_BY_BILLID` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SALES_REPORT_BY_BILLID`(
	IN `WHERECLAUSE` VARCHAR(3000)
)
BEGIN

DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

SET BASE_SQL=  'SELECT 
  i.ITEM_NM, 
  ifnull(si.SALE_QTY,0) TOTAL_QTY, 
  ifnull(si.UNIT_SALE_PRICE,0) MRP,
  ifnull(si.VAT,0) EFFECTIVE_VAT,
  ifnull(s.TOTAL_QTY,0) TOTAL_QTY,
  ifnull(s.OVERALL_DISCOUNT,0) OVERALL_DISCOUNT,
  ifnull(si.DISCOUNT_PERCENTAGE,0) DISCOUNT_PERCENTAGE,
  ifnull(s.TOTAL_AMOUNT,0) TOTAL_AMOUNT,
  ifnull(s.NET_AMOUNT,0) NET_AMOUNT,
  ifnull(si.VAT,0) VAT,
  ifnull(s.VAT_AMT,0) VAT_AMT,
  s.BILL_CODE,
  ifnull(s.CUSTOMER_NM,"N/A") CUSTOMER_NM ,
  si.SALE_AMOUNT,
  ROW_NUMBER() OVER (PARTITION BY s.bill_id ORDER BY sales_items_id) AS S_NO  
  FROM sales s 
INNER JOIN sales_items si ON s.BILL_ID = si.BILL_ID
INNER JOIN items i ON i.ITEM_ID = si.ITEM_ID  ';


SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE);

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;


END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SALES_RETURN` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SALES_RETURN`(
	IN `WHERECLAUSE` VARCHAR(3000)
)
BEGIN


DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';


SET BASE_SQL=  'SELECT distinct
  i.ITEM_NM, 
  ifnull(sri.RETURN_QUANTITY,0) SALE_QTY, 
  ifnull(ROUND(si.UNIT_SALE_PRICE,2),0) MRP,
ifnull(sri.RETURN_QUANTITY,0) TOTAL_QTY,
  ifnull(si.VAT,0) EFFECTIVE_VAT,
  ifnull(s.TOTAL_PRODUCTS,0) TOTAL_PRODUCTS,
  ifnull(ROUND(si.UNIT_SALE_PRICE * sri.RETURN_QUANTITY * si.DISCOUNT_PERCENTAGE/100,2),0) OVERALL_DISCOUNT,
  ifnull(ROUND(sri.RETURN_QUANTITY * si.UNIT_SALE_PRICE,2),0) AMOUNT,
  ifnull(sr.TOTAL_AMOUNT,0) NET_AMOUNT,
  ifnull(ROUND(sr.TOTAL_AMOUNT,2),0) PAID_AMOUNT,
  0.00 AS BALANCE_AMOUNT,
  tc.CATEGORY_CODE AS TAX,
  ifnull(si.VAT,0) VAT,
  sr.SALES_RETURN_DATE,
  sri.LAST_UPDATE_TS,
  ifnull(ROUND((si.VAT/100) * ifnull(ROUND((si.UNIT_SALE_PRICE * sri.RETURN_QUANTITY) *(1- ifnull(si.DISCOUNT_PERCENTAGE,0)/100),2),0),2),0) VAT_AMT,
  sr.SALES_RETURN_NO,
  "PAID" AS PAYMENT_STATUS,
  e.FIRST_NM,s.BILL_CODE,
  ifnull(concat(p.FIRST_NM," ",substring(p.LAST_NM,1,1)),"N/A") AS DOCTOR_NM,
  substring(ifnull(concat(c.FIRST_NM," ",c.LAST_NM),"N/A"),1,50) AS CUSTOMER_NM,
  ifnull(ROUND(si.UNIT_SALE_PRICE,2),0) AS PRICE,
  sr.SALES_RETURN_NO,
  ROW_NUMBER() OVER (PARTITION BY sr.SALES_RETURN_ID ORDER BY SALES_RETURN_ITEM_ID) AS S_NO
  FROM sales_return_item sri
  inner join sales_return sr on sr.SALES_RETURN_ID=sri.SALES_RETURN_ID
INNER JOIN sales_items si ON sr.BILL_ID = si.BILL_ID
inner join sales s on s.BILL_ID=sr.BILL_ID
INNER JOIN items i ON i.ITEM_ID = sri.ITEM_ID and i.ITEM_ID = si.Item_ID
inner join customer c on c.CUSTOMER_ID=s.CUSTOMER_ID 
inner join employee e on e.EMPLOYEE_ID=s.EMPLOYEE_ID
inner join provider p on s.PROVIDER_ID=p.PROVIDER_ID
inner join taxcategory tc on tc.TAXCATEGORY_ID=si.TAX_CATEGORY_ID';

SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE);

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SALE_PRICE_VALUE_FOR_CURRENT_STOCK` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SALE_PRICE_VALUE_FOR_CURRENT_STOCK`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

truncate table saleprice_value_for_current_stock_working;

insert into saleprice_value_for_current_stock_working (
select distinct i.ITEM_NM, st.BATCH_NO, st.EXPIRY_DT, st.QUANTITY, ifnull(st.SALE_DISCOUNT_PERCENTAGE,0)
as SALE_DISCOUNT_PERCENTAGE, ifnull(st.UNIT_SALE_RATE,0) AS UNIT_SALE_RATE,
tc.CATEGORY_CODE,
ifnull(ROUND((tc.CATEGORY_VALUE/100)*((st.QUANTITY*st.UNIT_SALE_RATE)),2),0) AS VAT_AMT,
round(ifnull(st.QUANTITY,0) * st.UNIT_SALE_RATE * ( 1 - ifnull(st.SALE_DISCOUNT_PERCENTAGE,0)/100) * 
(1 + (tc.CATEGORY_VALUE/100)),2) as SALE_VALUE,
date_format(st.LAST_UPDATE_TS,'%y-%m-%d'),date_format(st.LAST_UPDATE_TS,'%y-%m-%d'),
st.LAST_UPDATE_TS
from stock_history st left join stock s on s.STOCK_ID=st.STOCK_ID
left join taxcategory tc on tc.TAXCATEGORY_ID=s.TAX_CATEGORY_ID
inner join items i on i.ITEM_ID = st.ITEM_ID and i.ITEM_NM is not null,
(select max(sh.LAST_UPDATE_TS) LAST_UPDATED_TS,it.ITEM_NM AS productname FROM stock_history sh
inner join items it on it.ITEM_ID = sh.ITEM_ID and it.ITEM_NM is not null group by it.ITEM_NM) maxdates 
WHERE maxdates.productname=i.ITEM_NM and maxdates.LAST_UPDATED_TS=st.LAST_UPDATE_TS
ORDER BY st.LAST_UPDATE_TS DESC);

SET BASE_SQL=  'select st.ITEM_NM,st.BATCH_NO,st.EXPIRY_DT,
ifnull(st.QUANTITY,0) AS QUANTITY,st.SALE_DISCOUNT_PERCENTAGE,st.UNIT_SALE_RATE,
st.CATEGORY_CODE,ifnull(st.VAT_AMT,0) AS VAT_AMT,
ifnull(st.SALE_VALUE,0) AS SALE_VALUE,
date_format(st.LAST_UPDATE_TS,"%d-%m-%y %h:%i %p") AS LAST_UPDATE_TS
 from saleprice_value_for_current_stock_working st';

SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE,'order by st.LAST_UPDATE_TS DESC');

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SLOW_MOVING_PRODUCT_DETAILS` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SLOW_MOVING_PRODUCT_DETAILS`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';
SET BASE_SQL= 'SELECT i.ITEM_CD,i.ITEM_NM,sh.QUANTITY from stock_history sh
 inner join items i on sh.ITEM_ID=i.ITEM_ID group by sh.ITEM_ID';
 
SET @FINAL_SQL= CONCAT(BASE_SQL);

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_QUOTATION_REPORT` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_QUOTATION_REPORT`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN

DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

SET BASE_SQL=  'SELECT s.SP_NAME SupplierName,item.ITEM_NM ItemName,qi.QUANTITY 
FROM quotation q 
INNER JOIN quotation_items qi ON q.QUOTATION_ID = qi.QUOTATION_ID
INNER JOIN supplier s ON qi.SUPPLIER_ID = s.SUPPLIER_ID
INNER JOIN items item ON item.ITEM_ID = qi.ITEM_ID';


SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE);

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;


END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_REPORT_TEST2` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_REPORT_TEST2`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN

DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

SET BASE_SQL=  'SELECT * FROM test_report2 a ';


SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE);

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;


END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_REPORT_TEST4` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
CREATE DEFINER=`ihealthpharm`@`%` PROCEDURE `SP_REPORT_TEST4`(
IN `WHERECLAUSE` VARCHAR(3000)
)
BEGIN

DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

SET BASE_SQL=  'SELECT i.ITEM_NM, s.TOTAL_QTY, si.MRP, s.EFFECTIVE_VAT,s.TOTAL_QTY,s.OVERALL_DISCOUNT,
s.TOTAL_AMOUNT,si.VAT,s.BILL_CODE,s.CUSTOMER_NM,si.SALE_AMOUNT  FROM sales s 
   INNER JOIN sales_items si ON s.BILL_ID = si.BILL_ID
   INNER JOIN items i ON i.ITEM_ID = si.ITEM_ID';


SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE);

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;


END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `STOCK_AUDIT_REPORT` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `STOCK_AUDIT_REPORT`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

TRUNCATE TABLE stock_history_working;


INSERT INTO `stock_history_working` select i.ITEM_NM,ifnull(s.INVOICE_NO,'') INVOICE_NO,ifnull(st.QUANTITY,0) QUANTITY,ifnull(st.UNIT_SALE_RATE,0) UNIT_SALE_RATE,  
round(ifnull(st.QUANTITY,0) * st.UNIT_SALE_RATE * ( 1 - ifnull(st.SALE_DISCOUNT_PERCENTAGE,0)/100) *
ifnull(( 1 + tc.CATEGORY_VALUE/100),0),2) as SALE_PRICE,ifnull(st.UNIT_PURCHASE_RATE,0) UNIT_PURCHASE_RATE,
round(ifnull(st.QUANTITY,0) * st.UNIT_PURCHASE_RATE * ( 1 - ifnull(st.PURCHASE_DISCOUNT_PERCENTAGE,0)/100) * 
ifnull(( 1 + tc.CATEGORY_VALUE/100),0),2) as PURCHASE_PRICE,
date_format(st.LAST_UPDATE_TS,'%y-%m-%d') AS FROM_DATE,date_format(st.LAST_UPDATE_TS,'%y-%m-%d') AS TO_DATE,
st.EXPIRY_DT,e.FIRST_NM,sp.SP_NAME,st.LAST_UPDATE_TS
 from stock_history st left join stock s on s.STOCK_ID=st.STOCK_ID
inner join items i on i.ITEM_ID = st.ITEM_ID 
left join employee e on e.EMPLOYEE_ID=st.LAST_UPDATE_USER_ID
left join supplier sp on sp.SUPPLIER_ID=st.SUPPLIER_ID
left join taxcategory tc on s.TAX_CATEGORY_ID=tc.TAXCATEGORY_ID,
(select max(sh.LAST_UPDATE_TS) LAST_UPDATED_TS,it.ITEM_NM AS productname FROM stock_history sh
inner join items it on it.ITEM_ID = sh.ITEM_ID and it.ITEM_NM is not null group by it.ITEM_NM) maxdates WHERE maxdates.productname=i.ITEM_NM and maxdates.LAST_UPDATED_TS=st.LAST_UPDATE_TS
ORDER BY st.LAST_UPDATE_TS DESC ;

SET BASE_SQL='SELECT sh.ITEM_NM,ifnull(sh.INVOICE_NO,"") INVOICE_NO,ifnull(sh.UNIT_SALE_RATE,0) UNIT_SALE_RATE,ifnull(sh.SALE_PRICE,0) SALE_PRICE,ifnull(sh.UNIT_PURCHASE_RATE,0) UNIT_PURCHASE_RATE,ifnull(sh.PURCHASE_PRICE,0) PURCHASE_PRICE,sh.FROM_DATE,sh.TO_DATE,sh.EXPIRY_DT,ifnull(sh.QUANTITY,0) QUANTITY,
ifnull(sh.LAST_UPDATE_USER,"") LAST_UPDATE_USER,ifnull(sh.SP_NAME,"") SP_NAME,DATE_FORMAT(sh.LAST_UPDATE_TS,"%d-%m-%y %h:%i %p") LAST_UPDATE_TS from stock_history_working sh';


SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE);



PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `STOCK_CONSUMPTION_INVENTORY_REPORT` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `STOCK_CONSUMPTION_INVENTORY_REPORT`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';
Truncate table sales_inventory_working;

insert into sales_inventory_working ( BILL_ID, PAYMENT_STATUS, FROMDATE, TODATE)
select Bill_ID, Payment_Status, Bill_Date, Bill_Date
from sales WHERE
        ((BILL_DATE > (CURDATE() + INTERVAL -(1) YEAR))
            AND (PAYMENT_STATUS NOT IN ('CANCEL' , 'DUMMY BILL')));
            
Truncate table stock_qty_working;

insert into stock_qty_working ( Item_ID, Quantity)
select Item_ID, sum(Quantity) from stock group by Item_ID;

SET BASE_SQL='select i.ITEM_ID,i.ITEM_NM,sum(si.SALE_QTY) AS SALE_QTY, 
QUANTITY from 
sales_items si inner join sales_inventory_working s on si.Bill_ID = s.Bill_ID 
inner join items i on i.ITEM_ID = si.ITEM_ID 
inner join stock_qty_working st on st.ITEM_ID = si.ITEM_ID';

SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE,'GROUP BY i.Item_ID, i.ITEM_NM order by i.Item_Nm');

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `STOCK_TAKE` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `STOCK_TAKE`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

TRUNCATE TABLE stock_adjustment_working;

insert into stock_adjustment_working SELECT i.ITEM_NM,st.INVOICE_NO,st.BATCH_NO,st.EXPIRY_DT,
ifnull(st.RACK,"") RACK,ifnull(st.SHELF,"") SHELF,sa.PHYSICAL_STOCK,sa.ADJUSTEMENT_STOCK,
e.FIRST_NM,DATE_FORMAT(sa.LAST_UPDATE_TS,"%d-%m-%y") LAST_UPDATED_DT,sa.LAST_UPDATE_TS,sa.SYSTEM_DATE,sa.SYSTEM_DATE from stock_adjustment sa  
inner join stock st on st.STOCK_ID=sa.STOCK_ID
inner join items i on i.ITEM_ID=st.ITEM_ID
INNER JOIN employee e on e.EMPLOYEE_ID=sa.LAST_UPDATE_USER_ID;


SET BASE_SQL='SELECT sa.ITEM_NM,ifnull(sa.INVOICE_NO,"") INVOICE_NO,sa.BATCH_NO,sa.EXPIRY_DT,sa.RACK,sa.SHELF,sa.PHYSICAL_STOCK,sa.ADJUSTEMENT_STOCK,
sa.LAST_UPDATE_USER_ID,sa.LAST_UPDATED_DT,DATE_FORMAT(sa.LAST_UPDATE_TS,"%d-%m-%y %h:%i %p") LAST_UPDATE_TS,sa.FROM_SYSTEM_DATE,sa.TO_SYSTEM_DATE from stock_adjustment_working sa';



SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE);


PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SUPPLIER_BY_MFR_LIST` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SUPPLIER_BY_MFR_LIST`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN

DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

SET BASE_SQL='select DISTINCT m.MFR_NAME,sp.SP_NAME from stock st 
 INNER JOIN supplier sp ON st.SUPPLIER_ID=sp.SUPPLIER_ID
INNER JOIN items i ON st.ITEM_ID=i.ITEM_ID
INNER JOIN manufacturer m ON i.MANUFACTURER_ID=m.MANUFACTURER_ID';

SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE);

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SUPPLIER_LIST` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SUPPLIER_LIST`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

SET BASE_SQL='select DISTINCT sp.SP_NAME,sp.PAYMENT_CREDIT_NET_DAYS,
sp.PIN_NO,sp.SL_NO,sp.ADDRESS_LINE1,sp.CITY_NM,
sp.CONTACT_PERSON_PHONE_NBR,sp.CONTACT_PERSON_EMAIL_ID from supplier sp';
SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE);

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SUPPLIER_STATEMENT` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SUPPLIER_STATEMENT`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

truncate table supplier_statement_working;

INSERT INTO supplier_statement_working select s.SP_NAME,ap.SOURCE_REF,ap.SOURCE_TYPE,ap.PAYMENT_DATE,ap.INVOICE_NO,ifnull(ap.TOTAL_AMOUNT_PAID,0) debit,
ifnull(ap.TOTAL_AMOUNT_TO_BE_PAID,0) as credit,ifnull(ap.TOTAL_AMOUNT_TO_BE_PAID,0) balance,ap.APPROVED_DATE,ap.APPROVED_DATE from account_payables ap 
inner join supplier s on s.SUPPLIER_ID=ap.SUPPLIER_ID;


SET BASE_SQL=  'select ap.FROM_APPROVED_DATE,ap.TO_APPROVED_DATE,ifnull(ap.SOURCE_TYPE," ") SOURCE_TYPE,ap.SOURCE_REF,ifnull(ap.debit,0) debit,ap.PAYMENT_DATE,
ifnull(ap.credit,0) credit,ifnull(ap.INVOICE_NO," ") INVOICE_NO,ifnull(ap.balance,0) balance,ap.SP_NAME from supplier_statement_working ap';

SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE);

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SUPPLIER_WISE_SALES` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SUPPLIER_WISE_SALES`(
	IN `WHERECLAUSE` VARCHAR(500)
)
BEGIN
DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

SET BASE_SQL='select distinct sv.FromDate,sv.ToDate,
si.SALE_QTY,si.UNIT_SALE_PRICE,si.DISCOUNT,
si.SALE_AMOUNT,si.BATCH_NO,i.ITEM_CD,i.ITEM_NM,round((si.SALE_QTY*si.UNIT_SALE_PRICE),2) AS SUB_TOTAL,
round(((si.SALE_QTY*si.UNIT_SALE_PRICE)-si.DISCOUNT),2) AS TOTAL_AMOUNT from sales_items si 
INNER JOIN sales_view sv on sv.BILL_ID = si.BILL_ID
INNER JOIN items i ON si.ITEM_ID=i.ITEM_ID'; 

SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE,'ORDER BY sv.FromDate');

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `sales_view`
--

/*!50001 DROP VIEW IF EXISTS `sales_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `sales_view` AS select `sales`.`BILL_ID` AS `Bill_ID`,`sales`.`PAYMENT_STATUS` AS `Payment_Status`,`sales`.`BILL_DATE` AS `FromDate`,`sales`.`BILL_DATE` AS `ToDate` from `sales` where ((`sales`.`BILL_DATE` > (curdate() + interval -(1) year)) and (`sales`.`PAYMENT_STATUS` not in ('CANCEL','DUMMY BILL'))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `stock_view_qty`
--

/*!50001 DROP VIEW IF EXISTS `stock_view_qty`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `stock_view_qty` AS select `stock`.`ITEM_ID` AS `Item_ID`,sum(`stock`.`QUANTITY`) AS `Quantity` from `stock` group by `stock`.`ITEM_ID` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-18 17:28:48
