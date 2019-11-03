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

-- Dumping structure for procedure ihealthpharm.SP_REPORT_TEST2
DROP PROCEDURE IF EXISTS `SP_REPORT_TEST2`;
DELIMITER //
CREATE DEFINER=`ihealthpharm`@`%` PROCEDURE `SP_REPORT_TEST2`(
	IN `WHERECLAUSE` VARCHAR(3000)
)
BEGIN

DECLARE BASE_SQL  VARCHAR(5000) DEFAULT '';

SET BASE_SQL=  'SELECT * FROM TEST_REPORT2 a ';


SET @FINAL_SQL= CONCAT(BASE_SQL,WHERECLAUSE);

PREPARE stmt FROM @FINAL_SQL;
EXECUTE  stmt;
deallocate PREPARE stmt;


END//
DELIMITER ;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
