
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `bank_transactions_AFTER_INSERT` AFTER INSERT ON `bank_transactions` FOR EACH ROW BEGIN
update chart_of_accounts set CURRENT_BALANCE =new.BALANCE where ACCOUNT_ID=new.PARTY;
update chart_of_accounts set CURRENT_BALANCE =(CURRENT_BALANCE + new.AMOUNT) where ACCOUNT_ID=new.COUNTER_PARTY;
update  chart_of_accounts set AS_OF_DATE=new.TRANSACTION_DATE where ACCOUNT_ID=new.PARTY;
update  chart_of_accounts set AS_OF_DATE=new.TRANSACTION_DATE where ACCOUNT_ID=new.COUNTER_PARTY;
END */;;
DELIMITER ;


DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `expenses_AFTER_INSERT` AFTER INSERT ON `expenses` FOR EACH ROW BEGIN

update chart_of_accounts set CURRENT_BALANCE = new.BALANCE where ACCOUNT_ID=new.PARTY_NO;

update chart_of_accounts set AS_OF_DATE = new.DATE where ACCOUNT_ID=new.PARTY_NO;

update petty_cash p set p.BALANCE = new.Balance  order by p.PETTYCASH_ID desc limit 1;
END */;;
DELIMITER ;


DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `petty_cash_AFTER_INSERT` AFTER INSERT ON `petty_cash` FOR EACH ROW BEGIN

update chart_of_accounts c set c.CURRENT_BALANCE = c.CURRENT_BALANCE+new.AMOUNT where ACCOUNT_ID=new.COUNTER_PARTY_NO; 

update chart_of_accounts c set c.AS_OF_DATE = new.DATE where ACCOUNT_ID=new.COUNTER_PARTY_NO; 

update chart_of_accounts c set c.CURRENT_BALANCE=c.CURRENT_BALANCE-new.AMOUNT where ACCOUNT_ID=new.PARTY_NO;

update chart_of_accounts c set c.AS_OF_DATE = new.DATE where ACCOUNT_ID=new.PARTY_NO; 

END */;;
DELIMITER ;



DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `purchase_return_item_AFTER_INSERT` AFTER INSERT ON `purchase_return_item` FOR EACH ROW BEGIN
update stock s  set s.QUANTITY=s.QUANTITY-new.RETURN_QUANTITY where s.ITEM_ID=new.ITEM_ID ;
END */;;
DELIMITER ;



DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `sales_items_stock_update` AFTER INSERT ON `sales_items` FOR EACH ROW begin
   update stock s inner join sales ss on new.BILL_ID=ss.BILL_ID set s.QUANTITY= (s.QUANTITY - NEW.SALE_QTY)  where s.STOCK_ID=NEW.STOCK_ID and ss.PAYMENT_STATUS != 'Dummy Bill' 
   and ss.PAYMENT_STATUS != 'Cancel';

   update stock s inner join sales ss on new.BILL_ID=ss.BILL_ID set s.QUANTITY= (s.QUANTITY + NEW.SALE_QTY)  where s.STOCK_ID=NEW.STOCK_ID and  ss.PAYMENT_STATUS = 'Cancel'; 
 END */;;
DELIMITER ;

DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `sales_items_AFTER_UPDATE` AFTER UPDATE ON `sales_items` FOR EACH ROW BEGIN
update stock s inner join sales ss on new.BILL_ID=ss.BILL_ID set s.QUANTITY= (s.QUANTITY + NEW.SALE_QTY)  
   where s.STOCK_ID=NEW.STOCK_ID and  ss.PAYMENT_STATUS = 'Cancel'; 
END */;;
DELIMITER ;


DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `sales_return_items_stock_update` AFTER INSERT ON `sales_return_item` FOR EACH ROW BEGIN
update stock s  set s.QUANTITY=s.QUANTITY+new.RETURN_QUANTITY where s.ITEM_ID=new.ITEM_ID ;
END */;;
DELIMITER ;


DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `insertStockHistoryOnStock` AFTER INSERT ON `stock` FOR EACH ROW BEGIN
   INSERT INTO `ihealthpharm`.`stock_history`
(`STOCK_ID`,
`STOCK_NO`,
`ITEM_ID`,
`PHARMACY_ID`,
`INVOICE_ID`,
`QUANTITY`,
`BONUS`,
`UNIT_SALE_RATE`,
`MRP`,
`MARGIN`,
`MARGIN_AMT`,
`SALE_DISCOUNT_PERCENTAGE`,
`SALE_DISCOUNT_AMOUNT`,
`AUDIT_ID`,
`CREATION_TS`,
`CREATION_USER_ID`,
`LAST_UPDATE_TS`,
`LAST_UPDATE_USER_ID`,
`MANUFACTURE_DT`,
`EXPIRY_DT`,
`BATCH_NO`,
`PURCHASE_DISCOUNT_AMOUNT`,
`PURCHASE_DISCOUNT_PERCENTAGE`,
`RACK`,
`UNIT_PURCHASE_RATE`,
`SHELF`,
`BARCODE`,
`SUPPLIER_ID`,
`REMARKS`,
`STATUS`,
`VAT`,
`STOCK_DATE`,
`INVOICE_NO`,
`ENTRY_TYPE`,
`PACK`)
SELECT new.`STOCK_ID`,
new.`STOCK_NO`,
new.`ITEM_ID`,
new.`PHARMACY_ID`,
new.`INVOICE_ID`,
new.`QUANTITY`,
new.`BONUS`,
new.`UNIT_SALE_RATE`,
new.`MRP`,
new.`MARGIN`,
new.`MARGIN_AMT`,
new.`SALE_DISCOUNT_PERCENTAGE`,
new.`SALE_DISCOUNT_AMOUNT`,
new.`AUDIT_ID`,
new.`CREATION_TS`,
new.`CREATION_USER_ID`,
NOW(),
new.`LAST_UPDATE_USER_ID`,
new.`MANUFACTURE_DT`,
new.`EXPIRY_DT`,
new.`BATCH_NO`,
new.`PURCHASE_DISCOUNT_AMOUNT`,
new.`PURCHASE_DISCOUNT_PERCENTAGE`,
new.`RACK`,
new.`UNIT_PURCHASE_RATE`,
new.`SHELF`,
new.`BARCODE`,
new.`SUPPLIER_ID`,
new.`REMARKS`,
new.`STATUS`,
new.`VAT`,
new.`STOCK_DATE`,
new.`INVOICE_NO`,
new.`ENTRY_TYPE`,
new.`PACK`;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `updateStockHistoryOnStock` AFTER UPDATE ON `stock` FOR EACH ROW BEGIN
   INSERT INTO `ihealthpharm`.`stock_history`
(`STOCK_ID`,
`STOCK_NO`,
`ITEM_ID`,
`PHARMACY_ID`,
`INVOICE_ID`,
`QUANTITY`,
`BONUS`,
`UNIT_SALE_RATE`,
`MRP`,
`MARGIN`,
`MARGIN_AMT`,
`SALE_DISCOUNT_PERCENTAGE`,
`SALE_DISCOUNT_AMOUNT`,
`AUDIT_ID`,
`CREATION_TS`,
`CREATION_USER_ID`,
`LAST_UPDATE_TS`,
`LAST_UPDATE_USER_ID`,
`MANUFACTURE_DT`,
`EXPIRY_DT`,
`BATCH_NO`,
`PURCHASE_DISCOUNT_AMOUNT`,
`PURCHASE_DISCOUNT_PERCENTAGE`,
`RACK`,
`UNIT_PURCHASE_RATE`,
`SHELF`,
`BARCODE`,
`SUPPLIER_ID`,
`REMARKS`,
`STATUS`,
`VAT`,
`STOCK_DATE`,
`INVOICE_NO`,
`ENTRY_TYPE`,
`PACK`)
SELECT
new.`STOCK_ID`,
new.`STOCK_NO`,
new.`ITEM_ID`,
new.`PHARMACY_ID`,
new.`INVOICE_ID`,
new.`QUANTITY`,
new.`BONUS`,
new.`UNIT_SALE_RATE`,
new.`MRP`,
new.`MARGIN`,
new.`MARGIN_AMT`,
new.`SALE_DISCOUNT_PERCENTAGE`,
new.`SALE_DISCOUNT_AMOUNT`,
new.`AUDIT_ID`,
new.`CREATION_TS`,
new.`CREATION_USER_ID`,
NOW(),
new.`LAST_UPDATE_USER_ID`,
new.`MANUFACTURE_DT`,
new.`EXPIRY_DT`,
new.`BATCH_NO`,
new.`PURCHASE_DISCOUNT_AMOUNT`,
new.`PURCHASE_DISCOUNT_PERCENTAGE`,
new.`RACK`,
new.`UNIT_PURCHASE_RATE`,
new.`SHELF`,
new.`BARCODE`,
new.`SUPPLIER_ID`,
new.`REMARKS`,
new.`STATUS`,
new.`VAT`,
new.`STOCK_DATE`,
new.`INVOICE_NO`,
new.`ENTRY_TYPE`,
new.`PACK`;

END */;;
DELIMITER ;

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

