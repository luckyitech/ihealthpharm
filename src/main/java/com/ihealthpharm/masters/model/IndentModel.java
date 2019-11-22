package com.ihealthpharm.masters.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name="indent")
@EqualsAndHashCode(of="indentId",callSuper=false)
public class IndentModel extends AuditModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3279497905076289996L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="INDENT_ID",length=11, columnDefinition = "AUTO_INCREMENT")
    private Integer indentId;

    @Column(name="APPROVED_BY",length=11)
    private Integer approvedBy;

    @Column(name="APPROVED_DT",length=20)
    private LocalDate approvedDate;

    @Column(name="AUDIT_ID",length=11)
    private Integer auditId;

    @Column(name="CANCELLED_BY",length=11)
    private Integer cancelledBy;

    @Column(name="CANCELLED_DT",length=20)
    private LocalDate cancelledDate;

    @Column(name="CREATED_BY",length=11)
    private Integer createdBy;


    @Column(name="EMERGENCY",length=1)
    private Character emergency;

    @Column(name="INDENT_DT",length=20)
    private LocalDate indentDate;


    @Column(name="INDENT_NO",length=20)
    private String indentNumber;

    @Column(name="INDENT_STATUS_ID",length=11)
    private Integer indentStatusId;


    @Column(name="modified_by",length=11)
    private Integer modifiedBy;

    @Column(name="MODIFIED_DT")
    private LocalDate modifiedDate;

    @Column(name="REJECTED_REASON",length=200)
    private String rejectedReason;

    @Column(name="REMARKS",length=200)
    private String remarks;

    @Column(name="REQ",length=20)
    private String req;

    @Column(name="REQUEST_DT",length=20)
    private LocalDate requestDate;

    @Column(name="RFQ",length=20)
    private String rfq;

    @Column(name="STATUS",length=1)
    private Character status;

    @Column(name="SYSTEM_NM",length=50)
    private String systemName;
    
    @OneToOne
    private IndentStatusModel indentStatusModel;
}
