update stock a
inner join
stock_tmp b on a.Item_ID = b.Item_ID and a.Stock_ID = b.Stock_ID
 set
a.Pack = b.Pack, a.UNIT_PURCHASE_RATE = ROUND(b.UNIT_PURCHASE_RATE,2),
a.PURCHASE_DISCOUNT_PERCENTAGE = ROUND(b.PURCHASE_DISCOUNT_PERCENTAGE,2),
a.UNIT_SALE_RATE = ROUND(b.UNIT_SALE_RATE,2), a.SALE_DISCOUNT_PERCENTAGE = ROUND(b.SALE_DISCOUNT_PERCENTAGE,2),
a.SP_VAT = ROUND((b.Unit_Sale_Rate * ( 1 + VAT / 100)),2),
a.MRP = ROUND(b.UNIT_SALE_RATE,2) ,
a.Last_Update_TS = now();