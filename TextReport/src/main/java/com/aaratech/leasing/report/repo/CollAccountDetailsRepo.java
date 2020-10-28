package com.aaratech.leasing.report.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.aaratech.leasing.projections.BrokenPromisesSummary;
import com.aaratech.leasing.projections.ClassWiseAccountSummary;
import com.aaratech.leasing.report.CollAccountDetails;

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
	//Class Wise Account Summary
    @Query(nativeQuery=true,
    		value="Select COMPANY_CODE,CLASS_ID,Count(CLASS_ID) as NUMBER_OF_ACCOUNTS,SUM(TOTAL_AMOUNT_DUE) as TOTAL_DUE_AMOUNT,SUM(CURRENT_BALANCE) as CURRENT_DUE_AMOUNT,SUM(PTP_AMOUNT) as PTP_AMOUNT from dbo.COLL_ACCOUNT_DETAILS Group By CLASS_ID,COMPANY_CODE")
    List<ClassWiseAccountSummary> fetchAccountSummary();
    
//AH.COMPANY_CODE=DM.COMPANY_CODE AND AH.ACTION_DATE_AND_TIME=DM.BUSINESS_DATE and																																																																//here
    @Query(nativeQuery=true,value="Select COALESCE(AH.COMPANY_CODE, '') as COMPANY_CODE, COALESCE(ACTION_COLLECTOR_ID, '') as ACTION_COLLECTOR_ID , COALESCE(count(ACTION_COLLECTOR_ID),0) as NO_OF_ACCOUNTS, COALESCE(SUM(cad.PTP_TOTAL_AMOUNT),0) as PTP_AMOUNT from COLL_ACTIONS_HISTORY AH, COLL_ACCOUNT_DETAILS cad, DATE_MASTER as DM where   AH.ACTION_STATUS='A' and ACTION_CODE='BRKN' GROUP BY ACTION_COLLECTOR_ID,AH.COMPANY_CODE")
    List<BrokenPromisesSummary> fetchBrokenPromisesSummary();
 







}
