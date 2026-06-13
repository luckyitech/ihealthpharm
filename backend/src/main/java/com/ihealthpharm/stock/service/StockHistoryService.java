package com.ihealthpharm.stock.service;

import java.util.List;

public interface StockHistoryService {
	
	//Slow Moving Product Details
		List<String> findItemCodesInStockSMPD(String searchTerm);
		
		List<String> findAllItemCodesInStockSMPD();

		List<String> findItemNamesInStockSMPD(String searchTerm);
		
		List<String> findAllItemNamesInStockSMPD();

}
