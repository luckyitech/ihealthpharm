package com.ihealthpharm.masters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Entity (name="MEMBERSHIP")
@Getter
@Setter
@ToString
public class MembershipModel {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MEMBERSHIP_CARD_ID",length=11, columnDefinition = "AUTO_INCREMENT")
    private int membershipCardId;
	
	 @OneToOne
	    @JoinColumn(name="CUSTOMER_ID")
	   private CustomerModel customerModel;
	
}
