CREATE TABLE `drug_type` (
  `drug_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `drug_type_name` varchar(100) DEFAULT NULL,
  `active_s` char(1) DEFAULT 'Y',
  `CREATION_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`drug_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `drug_info` (
  `drug_id` int(11) NOT NULL AUTO_INCREMENT,
  `drug_name` varchar(80) NOT NULL,
  `brand_name` varchar(80) DEFAULT NULL,
  `generic_name` varchar(80) DEFAULT NULL,
  `dosage` varchar(40) DEFAULT NULL,
  `drug_type` int(11) NOT NULL,
  `classification` varchar(40) DEFAULT NULL,
  `sub_classification` varchar(40) DEFAULT NULL,
  `strength` varchar(40) DEFAULT NULL,
  `delivery_time` date DEFAULT NULL,
  `allergies` int(11) DEFAULT NULL,
  `bar_code` varchar(40) DEFAULT NULL,
  `formulation` varchar(40) DEFAULT NULL,
  `storage` varchar(40) DEFAULT NULL,
  `storage_temperature` varchar(40) DEFAULT NULL,
  `min_order_level` varchar(40) DEFAULT NULL,
  `max_order_level` varchar(40) DEFAULT NULL,
  `re_order_level` varchar(40) DEFAULT NULL,
  `tab_for_strip` varchar(40) DEFAULT NULL,
  `rack_number` varchar(40) DEFAULT NULL,
  `drug_usage` varchar(40) DEFAULT NULL,
  `side_effects` varchar(40) DEFAULT NULL,
  `warining_and_precautions` varchar(40) DEFAULT NULL,
  `interactions` varchar(40) DEFAULT NULL,
  `therapeutic_users` varchar(40) DEFAULT NULL,
  `storage_mechanism_for_damage` varchar(40) DEFAULT NULL,
  `expiry_return` varchar(40) DEFAULT NULL,
  `damage_return` varchar(40) DEFAULT NULL,
  `CREATION_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATION_USER_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_USER_ID` varchar(50) DEFAULT NULL,
  `audit_id` int(11) DEFAULT NULL,
  `ACTIVE_S` char(1) DEFAULT 'Y',
  PRIMARY KEY (`drug_id`),
  KEY `FK_REFER_DROUG_TYPE_idx` (`drug_type`),
  CONSTRAINT `FK_REFER_DROUG_TYPE` FOREIGN KEY (`drug_type`) REFERENCES `drug_type` (`drug_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `ihealthpharm`.`drug_manufactures` (
  `drug_manufacture_id` INT(11) NOT NULL,
  `drug_id` INT(11) NOT NULL,
  `manufacture_id` INT(11) NOT NULL,
  `active_s` VARCHAR(1) NOT NULL DEFAULT 'Y',
  `CREATION_TS` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATION_USER_ID` INT(11) NULL,
  `LAST_UPDATE_TS` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_USER_ID` INT(11) NULL,
  PRIMARY KEY (`drug_manufacture_id`),
  INDEX `FK_REF_DRUG_INFO_idx` (`drug_id` ASC) VISIBLE,
  INDEX `FK_REF_MANUFACTURE_idx` (`manufacture_id` ASC) VISIBLE,
  CONSTRAINT `FK_REF_DRUG_INFO`
    FOREIGN KEY (`drug_id`)
    REFERENCES `ihealthpharm`.`drug_info` (`drug_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_REF_MANUFACTURE`
    FOREIGN KEY (`manufacture_id`)
    REFERENCES `ihealthpharm`.`manufacturer` (`MANUFACTURER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
