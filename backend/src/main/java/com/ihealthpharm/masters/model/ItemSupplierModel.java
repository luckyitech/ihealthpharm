package com.ihealthpharm.masters.model;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Entity(name = "items_supplier")
@EqualsAndHashCode(of = "itemSupplierId", callSuper = false)
public class ItemSupplierModel extends AuditModel{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ITEM_SUPPLIER_ID",length=11)
	private Integer itemSupplierId;
	
	// Drug Entity mapped 
	@Column(name="ITEM_ID")
	private Integer itemsId;
	
	// SupplierModel Entity mapped 
    @Column(name="SUPPLIER_ID")
	private Integer suppliersId;
	
	@Column(name="ACTIVE_S",length=1)
	private String activeS;
	
	@Column(name="SUPPLIER_PRIORITY",length=11)
	private Integer supplierPriority;
	
	@Column(name="UNIT_RATE")
	private Double unitRate;
	
	@Column(name="DISCOUNT_PERCENTAGE")
	private Double discountPercentage;
	
	@Column(name="VALIDITY")
	private String validity;

	
	public void setValidity(Date date)throws ParseException {
		SimpleDateFormat simpleDate=new SimpleDateFormat("yyyy-MM-dd");
		String val=simpleDate.format(date);
			this.validity = val;
	}
	
}
