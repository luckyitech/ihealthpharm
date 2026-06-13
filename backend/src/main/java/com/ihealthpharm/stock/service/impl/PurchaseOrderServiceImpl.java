package com.ihealthpharm.stock.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.masters.model.SupplierModel;
import com.ihealthpharm.masters.dto.ItemSupplierDTO;
import com.ihealthpharm.masters.model.EmployeeModel;
import com.ihealthpharm.masters.model.ItemsModel;
import com.ihealthpharm.stock.dao.PurchaseOrderItemsRepository;
import com.ihealthpharm.stock.dao.PurchaseOrderRepository;
import com.ihealthpharm.stock.dao.PurchaseOrderStatusRepository;
import com.ihealthpharm.stock.dao.QuotationRepository;
import com.ihealthpharm.stock.helper.PurchaseOrderHelper;
import com.ihealthpharm.stock.model.PurchaseOrderItemsModel;
import com.ihealthpharm.stock.model.PurchaseOrderModel;
import com.ihealthpharm.stock.model.PurchaseOrderStatusModel;
import com.ihealthpharm.stock.service.PurchaseOrderService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

	@Autowired
	PurchaseOrderRepository purchaseorderRepository;
	
	@Autowired
	PurchaseOrderItemsRepository purchaseOrderItemsRepository;
	
	@Autowired
	QuotationRepository  quotationRepository;
	
	@Autowired
	PurchaseOrderStatusRepository  purchaseOrderStatusRepository;

	@Autowired
	PurchaseOrderHelper purchaseorderHelper;

	@Override
	public PurchaseOrderModel savePurchaseOrderData(PurchaseOrderModel purchaseorder) {
		
		List<PurchaseOrderItemsModel> itemsModels = purchaseorder.getPurchaseorderitems();
		
		PurchaseOrderModel purchaseorderRes = purchaseorderRepository.save(purchaseorder);
		
		for(PurchaseOrderItemsModel p : itemsModels) {
			purchaseOrderItemsRepository.save(p);
		}
		
		log.info("PurchaseOrder data with ID : " + purchaseorderRes.getPurchaseOrderId() + " Saved succesfully");
		return purchaseorderRes;
	}
	
	@Override
	public PurchaseOrderModel savePurchaseOrderData(PurchaseOrderModel purchaseorder, String status) {
		
		List<PurchaseOrderItemsModel> itemsModels = purchaseorder.getPurchaseorderitems();
		
		PurchaseOrderStatusModel orderStatusModel  = purchaseOrderStatusRepository.findByStatus(status);
		purchaseorder.setPurchaseOrderStatusModel(orderStatusModel);
		
		EmployeeModel createdBy = quotationRepository.findByEmployeeId(purchaseorder.getCreatedId());
		purchaseorder.setCreatedBy(createdBy);
		
		EmployeeModel modifiedBy = quotationRepository.findByEmployeeId(purchaseorder.getModifiedId());
		purchaseorder.setModifiedBy(modifiedBy);
		
		EmployeeModel approvedBy = quotationRepository.findByEmployeeId(purchaseorder.getApprovedId());
		purchaseorder.setApprovedBy(approvedBy);
		
		EmployeeModel rejectedBy = quotationRepository.findByEmployeeId(purchaseorder.getRejectedId());
		purchaseorder.setRejectedBy(rejectedBy);
		
		EmployeeModel sentBy = quotationRepository.findByEmployeeId(purchaseorder.getSentId());
		purchaseorder.setSentBy(sentBy);
		
		PurchaseOrderModel purchaseorderRes = purchaseorderRepository.save(purchaseorder);
		if(itemsModels != null)
		{
			for(PurchaseOrderItemsModel p : itemsModels) {
				p.setPurchaseOrderModel(purchaseorderRes);
				purchaseOrderItemsRepository.save(p);
			}
		}
		
		
		log.info("PurchaseOrder data with ID : " + purchaseorderRes.getPurchaseOrderId() + " Saved succesfully");
		return purchaseorderRes;
	}

	@Override
	public PurchaseOrderModel updatePurchaseOrderData(PurchaseOrderModel purchaseorder) {
		List<PurchaseOrderItemsModel> itemsModels = purchaseorder.getPurchaseorderitems();
		
		PurchaseOrderModel purchaseorderRes = getValidPurchaseOrders(purchaseorder.getPurchaseOrderId());
		if (!Objects.nonNull(purchaseorderRes)) {
			throw new IHealthPharmException(purchaseorderHelper.getNotFoundPurchaseOrderMessage(),
					HttpStatus.NOT_FOUND);
		}

		purchaseorderRes = purchaseorderRepository.save(purchaseorder);
		
		for(PurchaseOrderItemsModel p : itemsModels) {
			purchaseOrderItemsRepository.save(p);
		}
		
		log.info("PurchaseOrder data with ID : " + purchaseorderRes.getPurchaseOrderId() + " updated succesfully");
		return purchaseorderRes;
	}
	
	@Override
	public PurchaseOrderModel updatePurchaseOrderData(PurchaseOrderModel purchaseorder, String status) {
		List<PurchaseOrderItemsModel> itemsModels = purchaseorder.getPurchaseorderitems();
		
		PurchaseOrderModel purchaseorderRes = getValidPurchaseOrders(purchaseorder.getPurchaseOrderId());
		if (!Objects.nonNull(purchaseorderRes)) {
			throw new IHealthPharmException(purchaseorderHelper.getNotFoundPurchaseOrderMessage(),
					HttpStatus.NOT_FOUND);
		}

		PurchaseOrderStatusModel orderStatusModel  = purchaseOrderStatusRepository.findByStatus(status);
		purchaseorder.setPurchaseOrderStatusModel(orderStatusModel);
		
		purchaseorderRes = purchaseorderRepository.save(purchaseorder);
		
		for(PurchaseOrderItemsModel p : itemsModels) {
			purchaseOrderItemsRepository.save(p);
		}
		
		log.info("PurchaseOrder data with ID : " + purchaseorderRes.getPurchaseOrderId() + " updated succesfully");
		return purchaseorderRes;
	}

	@Override
	public List<PurchaseOrderModel> updatePurchaseOrdersData(List<PurchaseOrderModel> purchaseorders) {
		for (PurchaseOrderModel purchaseorder : purchaseorders) {
			PurchaseOrderModel purchaseorderRes = getValidPurchaseOrders(purchaseorder.getPurchaseOrderId());
			if (!Objects.nonNull(purchaseorderRes)) {
				throw new IHealthPharmException(purchaseorderHelper.getNotFoundPurchaseOrderMessage(),
						HttpStatus.NOT_FOUND);
			}

			purchaseorderRes = purchaseorderRepository.save(purchaseorder);
			log.info("PurchaseOrder data with ID : " + purchaseorderRes.getPurchaseOrderId() + " updated succesfully");
		}
		return purchaseorders;
	}

	@Override
	public List<PurchaseOrderModel> findAllPurchaseOrders() {

		return purchaseorderRepository.findAll();
	}

	@Override
	public PurchaseOrderModel findPurchaseOrderById(Integer purchaseorderId) {
		PurchaseOrderModel purchaseorderRes = getValidPurchaseOrders(purchaseorderId);
		if (!Objects.nonNull(purchaseorderRes)) {
			throw new IHealthPharmException(purchaseorderHelper.getNotFoundPurchaseOrderMessage(),
					HttpStatus.NOT_FOUND);
		}

		log.info("PurchaseOrder data with ID : " + purchaseorderRes.getPurchaseOrderId() + " retrieved succesfully");
		return purchaseorderRes;
	}

	@Override
	public void deletePurchaseOrderById(Integer purchaseorderId) {
		PurchaseOrderModel purchaseorderRes = purchaseorderRepository.getOne(purchaseorderId);
		if (!Objects.nonNull(purchaseorderRes)) {
			throw new IHealthPharmException(purchaseorderHelper.getNotFoundPurchaseOrderMessage(),
					HttpStatus.NOT_FOUND);
		}

		log.info("PurchaseOrder data with ID : " + purchaseorderRes.getPurchaseOrderId() + " Deleted succesfully");
		purchaseorderRepository.delete(purchaseorderRes);

	}

	@Override
	public void deletePurchaseOrdersById(Integer[] purchaseorderIds) {
		PurchaseOrderModel purchaseorderRes;
		for (int purchaseorder : purchaseorderIds) {
			purchaseorderRes = getValidPurchaseOrders(purchaseorder);
			if (!Objects.nonNull(purchaseorderRes)) {
				throw new IHealthPharmException(purchaseorderHelper.getNotFoundPurchaseOrderMessage(),
						HttpStatus.NOT_FOUND);
			}
			purchaseorderRepository.delete(purchaseorderRes);
			log.info("PurchaseOrder data with ID: " + purchaseorderRes.getPurchaseOrderId() + " deleted succesfully");
		}
	}

	public PurchaseOrderModel getValidPurchaseOrders(Integer purchaseorderId) {
		PurchaseOrderModel purchaseorderRes = null;

		try {
			purchaseorderRes = purchaseorderRepository.findById(purchaseorderId).get();
			purchaseorderRes.getSupplierModel();
			purchaseorderRes.getDeliveryTypesModel();
			purchaseorderRes.getPurchaseOrderStatusModel();
			purchaseorderRes.getQuotationModel();
			purchaseorderRes.getCreatedBy();
			purchaseorderRes.getApprovedBy();
			purchaseorderRes.getSentBy();
			purchaseorderRes.getRejectedBy();
			purchaseorderRes.getModifiedBy();
			for(PurchaseOrderItemsModel p : purchaseorderRes.getPurchaseorderitems()) {
				p.getItemsModel();
			}
			
			return purchaseorderRes;
		} catch (NoSuchElementException noSuchElementException) {
			throw new IHealthPharmException(purchaseorderHelper.getNotFoundPurchaseOrderMessage(),
					HttpStatus.NOT_FOUND);
		}
	}
	
	@Override
	public Long getPurchaseOrderCount(Integer pharmacyId) {
		log.info("Pharmacy Id: "+ pharmacyId+" ");
		return purchaseorderRepository.getPurchaseOrderCount(pharmacyId);
	}

	@Override
	public List<SupplierModel> getSuppliersByQuotationId(Integer quotationId) {
		log.info("QuotationId Id: "+ quotationId+" ");
		return purchaseorderRepository.getSuppliersByQuotationId(quotationId);
	}

	@Override
	public List<ItemSupplierDTO> getItemsBySupplierAndQuotation(Integer quotationId, Integer supplierId) {
		log.info("QuotationId Id: "+ quotationId+" Supplier Id "+supplierId);
		return purchaseorderRepository.getItemsBySupplierAndQuotation(quotationId, supplierId);
	}
	
	@Override
	public List<ItemSupplierDTO> getItemsBySupplierAndQuotation(Integer quotationId, Integer supplierId, String itemCode, String itemName) {
		log.info("QuotationId Id: "+ quotationId+" Supplier Id "+supplierId);
		return purchaseorderRepository.getItemsBySupplierAndQuotation(quotationId, supplierId, itemCode, itemName);
	}

	@Override
	public List<ItemsModel> getItemsByPurchaseOrder(Integer purchaseOrderId) {
		log.info("PurchaseOrder Id: "+ purchaseOrderId);
		return purchaseorderRepository.getItemsByPurchaseOrder(purchaseOrderId);
	}
	
	@Override
	public SupplierModel getSupplierByPurchaseOrder(Integer purchaseOrderId) {
		log.info("PurchaseOrder Id: "+ purchaseOrderId);
		return purchaseorderRepository.getSupplierByPurchaseOrder(purchaseOrderId);
	}
	
	@Override
	public List<PurchaseOrderModel> getPurchaseOrderByPharmacy(Integer pharmacyId) {
		log.info("Pharmacy Id: "+ pharmacyId);
//		List<PurchaseOrderModel> purchaseOrderModels = purchaseorderRepository.getPurchaseOrderByPharmacy(pharmacyId);
//		for(PurchaseOrderModel q : purchaseOrderModels) {
//			q.setCreatedName(purchaseorderRepository.createdPurchaseOrderUser(q.getPurchaseOrderId()));
//			q.setModifiedName(purchaseorderRepository.modifiedPurchaseOrderUser(q.getPurchaseOrderId()));
//			q.setRejectedName(purchaseorderRepository.rejectedPurchaseOrderUser(q.getPurchaseOrderId()));
//			q.setApprovedName(purchaseorderRepository.approvedPurchaseOrderUser(q.getPurchaseOrderId()));
//			q.setSentName(purchaseorderRepository.sentPurchaseOrderUser(q.getPurchaseOrderId()));
//		}
		Pageable limit = new PageRequest(0,20);
		return purchaseorderRepository.getPurchaseOrderByPharmacyFirst20(pharmacyId,limit);
	}

	@Override
	public List<PurchaseOrderModel> getPurchaseOrderByPharmacyAndStatus(Integer pharmacyId, String status) {
		log.info("Pharmacy Id: "+ pharmacyId+" Status "+status);
		List<PurchaseOrderModel> purchaseOrderModels = purchaseorderRepository.getPurchaseOrderByPharmacyAndStatus(pharmacyId, status);
		
		/*for(PurchaseOrderModel q : purchaseOrderModels) {
			q.setCreatedName(purchaseorderRepository.createdPurchaseOrderUser(q.getPurchaseOrderId()));
			q.setModifiedName(purchaseorderRepository.modifiedPurchaseOrderUser(q.getPurchaseOrderId()));
			q.setRejectedName(purchaseorderRepository.rejectedPurchaseOrderUser(q.getPurchaseOrderId()));
			q.setApprovedName(purchaseorderRepository.approvedPurchaseOrderUser(q.getPurchaseOrderId()));
			q.setSentName(purchaseorderRepository.sentPurchaseOrderUser(q.getPurchaseOrderId()));
		}*/
		
		return purchaseOrderModels;
	}
	
	@Override
	public List<PurchaseOrderModel> getPurchaseOrderByPharmacyAndStatusLimitedRecords(Integer pharmacyId, Integer start,
			Integer end, String status) {
		
		log.info("Pharmacy Id: "+ pharmacyId+" Status "+status);
		Pageable limit=PageRequest.of(start, end);
		List<PurchaseOrderModel> purchaseOrderModels = purchaseorderRepository.getPurchaseOrderByPharmacyAndStatusWithLimit(pharmacyId, status,limit);
		for(PurchaseOrderModel q : purchaseOrderModels) {
			q.setApprovedName(purchaseorderRepository.approverNameofPurchaseOrder(q.getPurchaseOrderId()));
		}
		return purchaseOrderModels;
	}
	
	@Override
	public List<PurchaseOrderModel> getPurchaseOrderByPharmacyAndStatus(Integer pharmacyId, String status,
			String purchaseOrderNo) {
		log.info("Pharmacy Id: "+ pharmacyId+" Status "+status);
		List<PurchaseOrderModel> purchaseOrderModels = purchaseorderRepository.getPurchaseOrderByPharmacyAndStatus(pharmacyId, status, purchaseOrderNo);
		for(PurchaseOrderModel q : purchaseOrderModels) {
			q.setCreatedName(purchaseorderRepository.createdPurchaseOrderUser(q.getPurchaseOrderId()));
			q.setModifiedName(purchaseorderRepository.modifiedPurchaseOrderUser(q.getPurchaseOrderId()));
			q.setRejectedName(purchaseorderRepository.rejectedPurchaseOrderUser(q.getPurchaseOrderId()));
			q.setApprovedName(purchaseorderRepository.approvedPurchaseOrderUser(q.getPurchaseOrderId()));
			q.setSentName(purchaseorderRepository.sentPurchaseOrderUser(q.getPurchaseOrderId()));
		}
		return purchaseorderRepository.getPurchaseOrderByPharmacy(pharmacyId);
	}

	@Override
	public List<PurchaseOrderModel> getSentPurchaseOrderByPharmacy(Integer pharmacyId) {
		List<PurchaseOrderModel> purchaseOrderModels = purchaseorderRepository.getSentPurchaseOrderByPharmacy(pharmacyId);
		for(PurchaseOrderModel q : purchaseOrderModels) {
			q.setCreatedName(purchaseorderRepository.createdPurchaseOrderUser(q.getPurchaseOrderId()));
			q.setModifiedName(purchaseorderRepository.modifiedPurchaseOrderUser(q.getPurchaseOrderId()));
			q.setRejectedName(purchaseorderRepository.rejectedPurchaseOrderUser(q.getPurchaseOrderId()));
			q.setApprovedName(purchaseorderRepository.approvedPurchaseOrderUser(q.getPurchaseOrderId()));
			q.setSentName(purchaseorderRepository.sentPurchaseOrderUser(q.getPurchaseOrderId()));
		}
		return purchaseorderRepository.getPurchaseOrderByPharmacy(pharmacyId);
	}

	@Override
	public List<PurchaseOrderModel> getSentPurchaseOrderByPharmacy(Integer pharmacyId, String purchaseOrderNo) {
		List<PurchaseOrderModel> purchaseOrderModels = purchaseorderRepository.getSentPurchaseOrderByPharmacy(pharmacyId, purchaseOrderNo);
		for(PurchaseOrderModel q : purchaseOrderModels) {
			q.setCreatedName(purchaseorderRepository.createdPurchaseOrderUser(q.getPurchaseOrderId()));
			q.setModifiedName(purchaseorderRepository.modifiedPurchaseOrderUser(q.getPurchaseOrderId()));
			q.setRejectedName(purchaseorderRepository.rejectedPurchaseOrderUser(q.getPurchaseOrderId()));
			q.setApprovedName(purchaseorderRepository.approvedPurchaseOrderUser(q.getPurchaseOrderId()));
			q.setSentName(purchaseorderRepository.sentPurchaseOrderUser(q.getPurchaseOrderId()));
		}
		return purchaseorderRepository.getPurchaseOrderByPharmacy(pharmacyId);
	}

	//PDBB
	
	@Override
	public List<String> findbatchNoInpurchaseorderPDBB(String searchTerm) {
	return purchaseorderRepository.findbatchNoInpurchaseorderPDBB(searchTerm);
	}

	@Override
	public List<String> findallPDBB() {
		return purchaseorderRepository.findallPDBB();
	}

	@Override
	public List<String> findSuppliersInpurchaseorderPDBB(String searchTerm) {
		return purchaseorderRepository.findSuppliersInpurchaseorderPDBB(searchTerm);
	}

	@Override
	public List<String> findAllSuppliersPDBB() {
		return purchaseorderRepository.findAllSuppliersPDBB();
	}

	//PDBP
	
	@Override
	public List<String> finditemNameInpurchaseorderPDBP(String searchTerm) {
		return purchaseorderRepository.finditemNameInpurchaseorderPDBP(searchTerm);
	}
	

	@Override
	public List<String> findallPDBP() {
		return purchaseorderRepository.findallPDBP();
	}

	//PRLT
	@Override
	public List<String> findpaymenttypebysearchPRLT(String searchTerm) {
		return purchaseorderRepository.findpaymenttypebysearchPRLT(searchTerm);
	}

	@Override
	public List<String> findallpaymenttypesPRLT() {
		return purchaseorderRepository.findallpaymenttypePRLT();
	}
	
//PRLS
	@Override
	public List<String> findsuppliersbysearchPRLS(String searchTerm) {
		return purchaseorderRepository.findsupplierbysearchPRLS(searchTerm);
	}

	@Override
	public List<String> findallsuppliersPRLS() {
		return purchaseorderRepository.findallsuppliersPRLS();
	}

	//Purchase Order Details By PO NO
	@Override
	public List<String> findPurNobysearchPDPO(String searchTerm) {
		return purchaseorderRepository.findPurNoBySearch(searchTerm);
	}

	@Override
	public List<String> findallPurNoPDPO() {
		return purchaseorderRepository.findAllPurNoINPDPO();
	}
	@Override
	public List<String> findSupplierbysearchPDPO(String searchTerm) {
		return purchaseorderRepository.findsupplierNameBySearchPOD(searchTerm);
	}

	@Override
	public List<String> findallSuppliersPDPO() {
		return purchaseorderRepository.findsupplierNameByPOD();
	}

	@Override
	public List<PurchaseOrderModel> getPurchaseOrderByPharmacyAndPONumber(Integer pharmacyId,String PONumber) {
		Pageable limit = new PageRequest(0,50);
		return purchaseorderRepository.getPurchaseOrderByPharmacyAndPurchaseOrderNumber(pharmacyId,PONumber,limit);
	}

	@Override
	public List<PurchaseOrderModel> getPurchaseOrderByPharmacyAndStatusForRejected(Integer pharmacyId, Integer start,
			Integer end, String status) {
		Pageable limit=PageRequest.of(start, end);
		List<PurchaseOrderModel> purchaseOrderModels = purchaseorderRepository.getPOByPharmacyAndStatusForRejected(pharmacyId, status,limit);
		return purchaseOrderModels;
	}

	@Override
	public List<PurchaseOrderModel> getSentPurchaseOrderByPharmacyForSent(Integer pharmacyId,Integer start,Integer end) {
		
		Pageable limit=PageRequest.of(start, end);
		List<PurchaseOrderModel> purchaseOrderModels = purchaseorderRepository.getSentPurchaseOrderByPharmacyWithLimit(pharmacyId,limit);
		for(PurchaseOrderModel q : purchaseOrderModels) {
			q.setCreatedName(purchaseorderRepository.createdPurchaseOrderUser(q.getPurchaseOrderId()));
			q.setModifiedName(purchaseorderRepository.modifiedPurchaseOrderUser(q.getPurchaseOrderId()));
			q.setRejectedName(purchaseorderRepository.rejectedPurchaseOrderUser(q.getPurchaseOrderId()));
			q.setApprovedName(purchaseorderRepository.approvedPurchaseOrderUser(q.getPurchaseOrderId()));
			q.setSentName(purchaseorderRepository.sentPurchaseOrderUser(q.getPurchaseOrderId()));
		}
		return purchaseorderRepository.getPurchaseOrderByPharmacyWithLimit(pharmacyId,limit);
	}
	
	@Override
	public List<PurchaseOrderModel> getPurchaseOrderByPharmacyAndStatusForPending(Integer pharmacyId, Integer start,
			Integer end, String status) {
		Pageable limit=PageRequest.of(start, end);
		List<PurchaseOrderModel> purchaseOrderModels = purchaseorderRepository.getPurchaseOrderByPharmacyAndStatusRepo(pharmacyId, status, limit);
		return purchaseOrderModels;
	}
		
	
}
