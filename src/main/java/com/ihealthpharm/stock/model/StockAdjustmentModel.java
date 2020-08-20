package com.ihealthpharm.stock.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import org.hibernate.annotations.UpdateTimestamp;
import com.ihealthpharm.masters.model.PharmacyModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name="stock_adjustment")
@EqualsAndHashCode(of="stockAdjustmentId",callSuper=false)
public class StockAdjustmentModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="STOCK_ADJUSTMENT_ID",length=11)
	private Integer stockAdjustmentId ;
	
	@Column(name="ADJUSTEMENT_STOCK",length=11)
	private Integer adjustedStock;
	
	@Column(name="PHYSICAL_STOCK",length=11)
	private Integer physicalStock;
	
	@Column(name = "AUDIT_ID",length=11)
	private Integer auditId;
	
	@OneToOne
	@JoinColumn(name="PHARMACY_ID")
	private PharmacyModel pharmacy;
	
	@Column(name="STOCK_ID")
	private Integer stock; 
	
	@Column(name="SYSTEM_DATE")
	private Date date;
	
	@Column(name = "LAST_UPDATE_TS")
	@UpdateTimestamp
	private Date lastUpdateTimestamp = new Date();
	
	@Column(name = "LAST_UPDATE_USER_ID")
	private Integer lastUpdateUser;
	
	@Column(name="ENTRY_TYPE",length=20)
	private String entryType;
	
	@Column(name="REMARKS")
	private String remarks;
}
