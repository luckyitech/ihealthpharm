-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.18 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             10.2.0.5701
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table ihealthpharm.reports_mapping
DROP TABLE IF EXISTS `reports_mapping`;
CREATE TABLE IF NOT EXISTS `reports_mapping` (
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
  PRIMARY KEY (`REPORT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table ihealthpharm.reports_mapping: ~1 rows (approximately)
DELETE FROM `reports_mapping`;
/*!40000 ALTER TABLE `reports_mapping` DISABLE KEYS */;
INSERT INTO `reports_mapping` (`REPORT_ID`, `REPORT_CODE`, `REPORT_NAME`, `STORED_PROCEDURE_NAME`, `INPUT_PARAMETERS`, `REPORT_HEADER`, `REPORT_TITLE`, `HEADER_CONTENT`, `FOOTER_CONTENT`, `SHOW_VERTICAL_LINES`, `SHOW_BAR_CHARTS`) VALUES
	(2, 'REPORT2', 'Report Details2', 'SP_REPORT_TEST2(?)', '[\r\n  {\r\n    "fieldName": "name",\r\n    "columnName": "name",\r\n    "type": "String",\r\n    "operator": "EQ",\r\n    "alias": "a."\r\n  },\r\n  {\r\n    "fieldName": "email",\r\n    "columnName": "email",\r\n    "type": "String",\r\n    "operator": "EQ",\r\n    "alias": "a."\r\n  },\r\n  {\r\n    "fieldName": "phone",\r\n    "columnName": "phone",\r\n    "type": "String",\r\n    "operator": "EQ",\r\n    "alias": "a."\r\n  },\r\n  {\r\n    "fieldName": "dob",\r\n    "columnName": "dob",\r\n    "type": "DATE",\r\n    "operator": "GE",\r\n    "alias": "a."\r\n  }\r\n]', '[\r\n  {\r\n    "columnName": "name",\r\n    "displayName": "Employee Name",\r\n    "position": 1\r\n  },\r\n  {\r\n    "displayName": "Email",\r\n    "columnName": "email",\r\n    "position": 2\r\n  },\r\n  {\r\n    "displayName": "Date of Birth",\r\n    "columnName": "dob",\r\n    "position": 3\r\n  },\r\n  {\r\n    "displayName": "Phone Number",\r\n    "columnName": "phone",\r\n    "position": 4\r\n  }\r\n]', 'REPORT TITLE GOES HERE', '{\n  "centerContent": [\n    {\n      "text": "DOCPHARMA LIMITED",\n      "fontName": "Helvetica",\n      "size": 12\n    },\n    {\n      "text": "Doctors\' Park, 3rd Parklands Ave., Nairobi",\n      "fontName": "Helvetica",\n      "size": 8\n    },\n    {\n      "text": "Nairobi-62934-619",\n      "fontName": "Helvetica",\n      "size": 8\n    }\n  ],\n  "leftContent": [\n    {\n      "text": " ",\n      "fontName": "Helvetica",\n      "size": 12\n    },\n    {\n      "text": " ",\n      "fontName": "Helvetica",\n      "size": 8\n    },\n    {\n      "text": " ",\n      "fontName": "Helvetica",\n      "size": 8\n    }\n  ],\n  "rightContent": [\n    {\n      "text": " ",\n      "fontName": "Helvetica",\n      "size": 12\n    },\n    {\n      "text": " ",\n      "fontName": "Helvetica",\n      "size": 8\n    },\n    {\n      "text": "Phone No : +254 735929000",\n      "fontName": "Helvetica",\n      "size": 8\n    }\n  ]\n}', '{\r\n  "centerContent": [\r\n    {\r\n      "text": "™Medeil",\r\n      "fontName": "Helvetica",\r\n      "size": 8\r\n    }\r\n  ],\r\n  "leftContent": [ \r\n	  {\r\n      "text": "",\r\n      "fontName": "Helvetica",\r\n      "size": 8\r\n    }\r\n ],\r\n  "rightContent": [ \r\n      {\r\n      "text": "",\r\n      "fontName": "Helvetica",\r\n      "size": 8\r\n    }\r\n\r\n  ]\r\n}', b'1', b'0'),
	(3, 'REPORT3', 'Report Details 3', 'SP_REPORT_TEST3(?)', '[\r\n  {\r\n    "fieldName": "name",\r\n    "columnName": "CUSTOMER_NAME",\r\n    "type": "String",\r\n    "operator": "EQ",\r\n    "alias": "a."\r\n  }\r\n]', '[\r\n  {\r\n    "columnName": "CUSTOMER_NAME",\r\n    "displayName": "Customer Name/ID No.",\r\n    "position": 1\r\n	,"width":15\r\n  },\r\n  {\r\n    "displayName": "BILL NO.",\r\n    "columnName": "BILL_NO",\r\n    "position": 2\r\n	,"width":8\r\n  },\r\n  {\r\n    "displayName": "Date",\r\n    "columnName": "DATE",\r\n    "position": 3\r\n	,"width":8\r\n	,"format":"DD/MM/YYYY"\r\n  },\r\n  {\r\n    "displayName": "Doctor Name",\r\n    "columnName": "DOCTOR",\r\n    "position": 4\r\n	,"width":12\r\n  },\r\n  {\r\n    "displayName": "Product Name",\r\n    "columnName": "PRODUCT_NAME",\r\n    "position": 4\r\n	,"width":10\r\n  },\r\n  {\r\n    "displayName": "Mfr Name",\r\n    "columnName": "MFR_NAME",\r\n    "position": 4\r\n	,"width":10\r\n  },\r\n  {\r\n    "displayName": "Batch",\r\n    "columnName": "BATCH",\r\n    "position": 4\r\n	,"width":8\r\n  },\r\n  {\r\n    "displayName": "Expiry",\r\n    "columnName": "Expiry",\r\n    "position": 4\r\n	,"width":5\r\n	,"format":"MM/YYYY"\r\n  },\r\n  {\r\n    "displayName": "Quantity",\r\n    "columnName": "QTY",\r\n    "position": 4\r\n	,"width":5\r\n  },\r\n  {\r\n    "displayName": "Unit Price",\r\n    "columnName": "UNIT_PRICE",\r\n    "position": 4\r\n	,"width":8\r\n  },\r\n  {\r\n    "displayName": "Sub Total",\r\n    "columnName": "SUB_TOTAL",\r\n    "position": 4\r\n	,"width":8\r\n  },\r\n  {\r\n    "displayName": "Amount",\r\n    "columnName": "AMOUNT",\r\n    "position": 4\r\n	,"width":8\r\n  }\r\n]', 'SALES BY PRODUCT DETAILS', '{\r\n  "leftContent": [\r\n    {\r\n      "text": "DOCPHARMA LIMITED",\r\n      "fontName": "Helvetica",\r\n      "size": 12\r\n    },\r\n    {\r\n      "text": "Doctors\' Park, 3rd Parklands Ave., Nairobi",\r\n      "fontName": "Helvetica",\r\n      "size": 8\r\n    },\r\n    {\r\n      "text": "Nairobi-62934-619",\r\n      "fontName": "Helvetica",\r\n      "size": 8\r\n    }\r\n  ],\r\n  "centerContent": [\r\n    {\r\n      "text": " ",\r\n      "fontName": "Helvetica",\r\n      "size": 12\r\n    },\r\n    {\r\n      "text": " ",\r\n      "fontName": "Helvetica",\r\n      "size": 8\r\n    },\r\n    {\r\n      "text": " ",\r\n      "fontName": "Helvetica",\r\n      "size": 8\r\n    }\r\n  ],\r\n  "rightContent": [\r\n    {\r\n      "text": " ",\r\n      "fontName": "Helvetica",\r\n      "size": 12\r\n    },\r\n    {\r\n      "text": " ",\r\n      "fontName": "Helvetica",\r\n      "size": 8\r\n    },\r\n    {\r\n      "text": "Phone No : +254 735929000",\r\n      "fontName": "Helvetica",\r\n      "size": 8\r\n    }\r\n  ]\r\n}', '{\r\n  "centerContent": [\r\n    {\r\n      "text": "™Medeil",\r\n      "fontName": "Helvetica",\r\n      "size": 8\r\n    }\r\n  ],\r\n  "leftContent": [ \r\n	  {\r\n      "text": "",\r\n      "fontName": "Helvetica",\r\n      "size": 8\r\n    }\r\n ],\r\n  "rightContent": [ \r\n      {\r\n      "text": "",\r\n      "fontName": "Helvetica",\r\n      "size": 8\r\n    }\r\n\r\n  ]\r\n}', b'0', b'0');
/*!40000 ALTER TABLE `reports_mapping` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
