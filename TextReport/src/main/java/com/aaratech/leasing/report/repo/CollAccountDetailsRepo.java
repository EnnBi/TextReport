package com.aaratech.leasing.report.repo;

import java.util.Date;
import java.util.List;

import com.aaratech.leasing.projections.ClassWiseAccountSummary;
import com.aaratech.leasing.report.CollAccountDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CollAccountDetailsRepo extends CrudRepository<CollAccountDetails, String> {
	
	
	//@Query("Select c from CollAccountDetails c where c.contractStatus = :status order by c.classId ASC")
	List<CollAccountDetails> findByContractStatusOrderByClassIdAsc( String status);


	@Query(nativeQuery = true,
			value ="select COALESCE(classmove.COMPANY_CODE,'')as COMPANY_CODE, COALESCE(classmove.CONTRACT_CARD_ID,'') as CONTRACT_CARD_ID, COALESCE(accdetails.CUSTOMER_NAME, '') as CUSTOMER_NAME, COALESCE(classmove.DATE_MOVED, '') as DATE_MOVED, COALESCE(classmove.MOVED_BY,'') as MOVED_BY, COALESCE(classmove.OLD_CLASS, '') as OLD_CLASS, COALESCE(classmove.NEW_CLASS,'') as NEW_CLASS from COLL_CLASS_MOVE as classmove, COLL_ACCOUNT_DETAILS accdetails,DATE_MASTER dateMaster where classmove.COMPANY_CODE = accdetails.COMPANY_CODE  and classmove.COMPANY_CODE = dateMaster.COMPANY_CODE and classmove.CONTRACT_CARD_ID = accdetails.CONTRACT_CARD_ID")
	List<ClassMoveRepo.ClassMove> getCollClassMoves();

	static interface ClassMove {


		 String getCONTRACT_CARD_ID();

		 String getCOLLECTOR_ID();
		 String getCUSTOMER_NAME();

		 String getCLASS_ID();

		 String getPRIORITY();

		 Double getPTP_TOTAL_AMOUNT();

		 Double getPTP_AMOUNT_PAID();

		 Date getNEXT_PTP_DATE();

		 String getCOMPANY_CODE();
	}
	
    @Query(nativeQuery=true,
    		value="Select COMPANY_CODE,CLASS_ID,Count(CLASS_ID) as NUMBER_OF_ACCOUNTS,SUM(TOTAL_AMOUNT_DUE) as TOTAL_DUE_AMOUNT,SUM(CURRENT_BALANCE) as CURRENT_DUE_AMOUNT,SUM(PTP_AMOUNT) as PTP_AMOUNT from dbo.COLL_ACCOUNT_DETAILS Group By CLASS_ID,COMPANY_CODE")
    List<ClassWiseAccountSummary> fetchAccountSummary();









}
