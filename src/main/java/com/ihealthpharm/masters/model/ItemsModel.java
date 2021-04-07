package com.ihealthpharm.masters.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ihealthpharm.tax.model.TaxCategoryModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Tarun 
 * 			Item nothing but Drug, Models refers the type of the Drug 
 * 			Setter, getters by default added no need to add manually
 */

@Data
@Entity(name = "items")
@EqualsAndHashCode(of = "itemId", callSuper = false)
public class ItemsModel extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ITEM_ID",length=11)
	private Integer itemId;


	@Column(name="ITEM_CD",length=20)
	private String itemCode;

	@Column(name = "ITEM_NM",length=100)
	private String itemName;

	@Column(name = "ITEM_DESC",columnDefinition="text")
	private String itemDescription;

	@Column(name = "MEDICAL_OR_NON_MEDICAL",length=1)
	private String medicalOrNonMedical;

	@Column(name="RACK_NO",length=20)
	private String rackNumber;

	@Column(name="SHELF_NO",length=20)
	private String shelfNumber;

	@Column(name="REORDER_LEVEL",length=11)
	private Integer reOrderLevel;

	@Column(name="REORDER_QTY",length=11)
	private Integer reOrderQuantity;

	@Column(name="ALERT_MESG",length=50)
	private String alertMessage;

	@Column(name="STORAGE",length=150)
	private String storage;

	@OneToOne
	@JoinColumn(name = "ITEM_GROUP_ID")
	private ItemGroupModel itemGroup;

	@OneToOne
	@JoinColumn(name = "ITEM_GENERIC_NAME_ID")
	private ItemGenericNamesModel itemGenericName;

	@OneToOne
	@JoinColumn(name = "ITEM_FORM_ID")
	private ItemFormModel itemForm;

	@OneToOne
	@JoinColumn(name = "ITEM_CATEGORIE_ID")
	private ItemCategoryModel itemCategory;

	@Column(name = "ACTIVE_S",length=1)
	private String activeS;

	@Column(name = "AUDIT_ID",length=11)
	private Integer auditId;

	@Column(name = "SPECIFICATION",length=100)
	private String specification;

	@Column(name = "DRUG_DOSE",length=20)
	private String drugDose;

	@Column(name = "IS_ASSET",length=1)
	private String isAsset;

	@Column(name = "ITEM_USAGE",columnDefinition="text")
	private String itemUsage;

	@Column(name = "DRUG_SCHEDULE",length=1)
	private String drugSchedule;

	@Column(name = "REORDER_ITEM",length=1)
	private String reOrderItem;

	@OneToOne
	@JoinColumn(name="PURCHASE_UNIT_OF_MEASUREMENT_ID")
	private UnitOfMeasurementModel purchaseUnitMeasurementId;

	@Column(name="IS_FORMULARY",length=1)//defalut 'N'
	private String isFormulary;

	@Column(name="TEMPERATURE",length=20)
	private String  temperature;

	@Column(name="BATCH_NO_REQ",length=1) //default 'Y'
	private String batchNoRequired;

	@Column(name="REUSABLE",length=1) //defalut null
	private String reusable;

	@Column(name="IMPORTED",length=1) //defalut null
	private String imported;

	@Column(name="HAZARDOUS",length=1) //defalut null
	private String hazardous;

	@Column(name="IS_BILLABLE",length=1) //defalut null
	private String isBillable;

	@Column(name="IS_LOOK_ALIKE",length=1) //defalut null
	private String isLookAlike;

	@Column(name="IS_SOUND_ALIKE",length=1) //defalut null
	private String isSoundAlike;

	@Column(name="IS_NARCOTIC",length=1) //defalut null
	private String isNarcotic;

	@Column(name="IS_HIGH_RISK",length=1) //defalut null
	private String isHighRisk;

	@Column(name="IS_DRUG",length=1) //default null
	private String isDrug;

	@Column(name="IS_CRITICAL",length=1) //default null 
	private String isCritical;

	@Column(name="IS_REVERSE_ANTIBIOTIC",length=1) //default null
	private String isReverseAntiBiotic;

	@Column(name="IS_NON_REFUNDABLE_ITEM",length=1) //default null
	private String isNonRefundableItem;

	@OneToOne
	@JoinColumn(name="ISSUE_UNIT_OF_MEASUREMENT_ID")
	private UnitOfMeasurementModel issueUnitOfMeasurementId;

	@Column(name="PACK",length=50)
	private String pack;

	@Column(name="HSN_CODE",length=20)
	private String hsnCode;

	@Column(name="IS_TAX_EXCEMPTED",length=1)
	private String isTaxExcempted;

	@OneToOne
	@JoinColumn(name="TAX_ID")
	private TaxCategoryModel tax;

	@Column(name="DEFAULT_PO_QTY")
	private Integer defaultPOQuantity;

	@Column(name="PO_TERMS",columnDefinition="text")
	private String poTerms;

	@Column(name="SALE_RATE_FOR_BILLING",length=20)
	private String saleRateForBilling;

	@Column(name="BILLING_MESSAGE",length=500)
	private String billingMessage;

	@Column(name="DIRECTIONS",length=500)
	private String directions;

	@Column(name="INSTRUCTIONS",length=500)
	private String instructions;

	@Column(name="EXPIRY_TYPE",length=20)
	private String expiryType;

	@Column(name="STORAGE_TYPE",length=20)
	private String storageType;

	@Column(name="BATCH_NON_BATCH",length=20)
	private String batchNonBatch;

	@Column(name="SEASONAL_NON_SEASONAL",length=20)
	private String seasonalNonSeasonal;

	/*@OneToOne
	@JoinColumn(name="CREATED_BY")
	private UsersModel userCreatedBy;

	@Column(name="CREATED_DT")
	private Date createdDate;

	@OneToOne
	@JoinColumn(name="MODIFIED_BY")
	private UsersModel userModifiedBy;

	@Column(name="MODIFIED_DT")
	private Date modifiedDate;

	@OneToOne
	@JoinColumn(name="APPROVED_BY")
	private UsersModel userApprovedBy;

	@Column(name="APPROVED_DT")
	private Date approvedDate;

	@OneToOne
	@JoinColumn(name="REJECTED_BY")
	private UsersModel userRejectedBy;

	@Column(name="REJECTED_DT")
	private Date rejectedDate;

	@Column(name="REJECTED_REASON",length=200)
	private String rejectedReason;*/

	@Column(name="DRUG_SHORTAGES",length=20)
	private String drugShortages;

	@Column(name="DRUG_VED",length=20)
	private String drugVed;

	@Column(name="DRUG_INTERNAL_EXTERNAL_BOTH",length=20)
	private String drugInternalExternalBoth;

	@ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	@JsonBackReference
	@JoinColumn(name="EXPIRY_PROFILE_ID")
	private ExpiryProfileModel expiryProfile;

	@OneToOne
	@JoinColumn(name = "SPECIALIZATION_ID")
	private SpecializationModel specialization;

	@OneToOne
	@JoinColumn(name = "MANUFACTURER_ID")
	private ManufacturerModel manufacturer;

	@OneToOne
	@JoinColumn(name="LATIN_SHORT_CODE_ID")
	private LatinShortCodesModel latinCode;

	@OneToOne
	@JoinColumn(name="SCHEDULE_CATEGORY_CODE_ID")
	private ScheduleCodeModel ScheduleCode;
	
	@OneToOne
	@JoinColumn(name="PHARMACY_ID")
	private PharmacyModel pharmacy;
	
	@Column(name="BARCODE",length=20)
	private String barcode;

}
