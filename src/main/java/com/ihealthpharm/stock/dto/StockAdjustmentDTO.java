package com.ihealthpharm.stock.dto;

import com.ihealthpharm.masters.model.ItemFormModel;
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.stock.model.StockModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StockAdjustmentDTO {

/*	private Integer itemId;

	private String itemCode;

	private String itemName;

	//private  String form;
	
	private String form;
	
	private ItemsModel item;
	//private ItemFormModel forms;
	//private StockModel stock;	

	private String batchNo;
	
	private Integer stockId;

	private Date expiryDt;

	private Double unitSaleRate;

	private long onHandStock;
*/
	
	
	private ItemsModel item;
	
	private ItemFormModel form;
	
	private StockModel stock;
	
	private long onHandStock;
	
	public StockAdjustmentDTO() {
		
	}

	public StockAdjustmentDTO(ItemsModel item, ItemFormModel form, StockModel stock, long onHandStock) {
		super();
		this.item = item;
		this.form = form;
		this.stock = stock;
		this.onHandStock = onHandStock;
	}
	
	
	
/*	public StockAdjustmentDTO(Integer itemId,String itemCode, String itemName, ItemsModel item, String form,
			String batchNo, Date expiryDt,Integer stockId, Double unitSaleRate, long onHandStock) {
		super();

		this.itemCode = itemCode;
		this.itemName = itemName;
		this.item = item;
		this.form = form;
		this.stockId = stockId;
		this.batchNo = batchNo;
		this.expiryDt = expiryDt;
		this.unitSaleRate = unitSaleRate;
		this.onHandStock = onHandStock;

		ItemsModel items=new ItemsModel();
		items.setItemName(itemName);
		items.setItemName(itemName);
		//items.setItemForm(form);

		StockModel stocks=new StockModel();
		stocks.setBatchNo(batchNo);
		stocks.setExpiryDt(expiryDt);
		//stocks.setQuantity(onHandStock);
		stocks.setUnitSaleRate(unitSaleRate);

	}
*/

	/*	public StockAdjustmentDTO(Integer itemId, String itemCode, String itemName, String form, Integer stockId,
			String batchNo, Date expiryDt, Double unitSaleRate,long onHandStock) {
		super();
		this.itemId = itemId;
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.form = form;
		this.stockId = stockId;
		this.batchNo = batchNo;
		this.expiryDt = expiryDt;
		this.unitSaleRate = unitSaleRate;
		this.onHandStock= onHandStock;
	}*/

	/*	public StockAdjustmentDTO(Integer itemId, String itemCode, String itemName, String form, Integer stockId,
			String batchNo, Date expiryDt, Double unitSaleRate,long onHandStock) {
		super();
		this.itemId = itemId;
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.form = form;
		this.stockId = stockId;
		this.batchNo = batchNo;
		this.expiryDt = expiryDt;
		this.unitSaleRate = unitSaleRate;
		this.onHandStock = onHandStock;

		ItemsModel items=new ItemsModel();
		items.setItemName(itemName);
		items.setItemName(itemName);
		items.setItemForm(form);

		StockModel stocks=new StockModel();
		stocks.setBatchNo(batchNo);
		stocks.setExpiryDt(expiryDt);
		stocks.setQuantity(onHandStock);
		stocks.setUnitSaleRate(unitSaleRate);
	}
	 */

}
