-- --------------------------------------------------------
-- Host:                         182.50.133.88
-- Server version:               5.5.51-38.1-log - Percona Server (GPL), Release rel30.2, Revision 39.1
-- Server OS:                    Linux
-- HeidiSQL Version:             10.2.0.5701
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for procedure ihealthpharm.SP_QUOTATION_REPORT
DROP PROCEDURE IF EXISTS `SP_QUOTATION_REPORT`;
DELIMITER //
CREATE DEFINER=`ihealthpharm`@`%` PROCEDURE `SP_QUOTATION_REPORT`(
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


END//
DELIMITER ;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
