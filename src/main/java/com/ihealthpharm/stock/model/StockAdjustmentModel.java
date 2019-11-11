package com.ihealthpharm.stock.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ihealthpharm.masters.model.AuditModel;
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.masters.model.PharmacyModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name="stock_adjustment")
@EqualsAndHashCode(of="stockAdjustmentId",callSuper=false)
public class StockAdjustmentModel extends AuditModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1612235109086681872L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="STOCK_ADJUSTMENT_ID",length=11)
	private Integer stockAdjustmentId ;
	
	@OneToOne
	@JoinColumn(name="ITEM_ID")
	private ItemsModel item;
	
	@Column(name="BATCH_NO",length=20)
	private String batchNo;
	
	@Temporal(TemporalType.DATE)
	@Column(name="EXPIRY_DT")
	private Date expiryDt;
	
	@Column(name="ON_HAND_STOCK",length=11)
	private Integer onHandStock;
	
	@Column(name="ON_HAND_STOCK_VALUE")
	private Float onHandStockValue;
	
	@Column(name="ADJUSTMENT_STOCK",length=11)
	private Integer adjustedStock;
	
	@Column(name="ADJUSTED_STOCK_VALUE")
	private Float adjustmentStockValue;
	
	@Column(name="PHYSICAL_STOCK",length=11)
	private Integer physicalStock;
	
	@Column(name="PHYSICAL_STOCK_VALUE")
	private Float physicalStockValue;
	
	@Column(name="REMARKS",length=100)
	private String remarks;
	
	@Column(name = "ACTIVE_S",length=1)
	private String activeS;

	@Column(name = "AUDIT_ID",length=11)
	private Integer auditId;
	
	@OneToOne
	@JoinColumn(name="PHARMACY_ID")
	private PharmacyModel pharmacy;
	
	
	@OneToOne
	@JoinColumn(name="STOCK_ID")
	private StockModel stock; 
	
	
	@Column(name="SYSTEM_DATE")
	private Date date;

   /* public void setExpiryDt(Date date) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String expiryDate=simpleDateFormat.format(date);  
		this.expiryDt = expiryDate;
    }
    */
}
