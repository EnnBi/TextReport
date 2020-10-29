package com.aaratech.leasing.report.repo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.aaratech.leasing.projections.BrokenPromisesSummary;
import com.aaratech.leasing.projections.BucketWise;
import com.aaratech.leasing.projections.ClassWiseAccountSummary;
import com.aaratech.leasing.projections.SatisfiedAccounts;
import com.aaratech.leasing.projections.UnworkedAccountDays;
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
    	
    //Broken Promise Summary
    @Query(nativeQuery=true,value="Select COALESCE(AH.COMPANY_CODE, '') as COMPANY_CODE, COALESCE(ACTION_COLLECTOR_ID, '') as ACTION_COLLECTOR_ID , COALESCE(count(ACTION_COLLECTOR_ID),0) as NO_OF_ACCOUNTS, COALESCE(SUM(cad.PTP_TOTAL_AMOUNT),0) as PTP_AMOUNT from COLL_ACTIONS_HISTORY AH, COLL_ACCOUNT_DETAILS cad, DATE_MASTER as DM where AH.COMPANY_CODE=DM.COMPANY_CODE AND AH.ACTION_DATE_AND_TIME=DM.BUSINESS_DATE and   AH.ACTION_STATUS='A' and ACTION_CODE='BRKN' GROUP BY ACTION_COLLECTOR_ID,AH.COMPANY_CODE")
    List<BrokenPromisesSummary> fetchBrokenPromisesSummary();
    
    @Query(nativeQuery=true,value="Select Distinct COALESCE(AD.COMPANY_CODE, '') as COMPANY_CODE,COALESCE(AD.CONTRACT_CARD_ID, '') as CONTRACT_CARD_ID,COALESCE(CUSTOMER_NAME, '') as CUSTOMER_NAME,COALESCE(AD.CLASS_ID, '') as CLASS_ID,COALESCE(UNWORKED_ACCOUNT_DAYS, 0) as UNWORKED_ACCOUNT_DAYS,COALESCE(AD.PRIORITY, 0)  as PRIORITY,COALESCE(COLLECTION_START_DATE,'') as COLLECTION_START_DATE,COALESCE(DELINQUENT_DUE, 0) as DELINQUENT_DUE,COALESCE(TOTAL_AMOUNT_DUE, 0) as TOTAL_AMOUNT_DUE,COALESCE(BUSINESS_DATE, '') as BUSINESS_DATE from COLL_ACCOUNT_DETAILS AD,COLL_PARAM_CLASS_MASTER CM,DATE_MASTER DM where AD.CLASS_ID=CM.CLASS_ID AND AD.COMPANY_CODE=DM.COMPANY_CODE AND CONTRACT_STATUS='A' AND UNWORKED_ACCOUNT_DAYS != 0 ")
    List<UnworkedAccountDays> fetchUnworkedAccountDays();
    
    @Query(nativeQuery=true,value="Select * from COLL_ACTIONS_HISTORY where ACTION_DATE_AND_TIME BETWEEN ?1 AND ?2")
    Integer checkCountInActionHistory(LocalDate startDate,LocalDate endDate);

    @Query(nativeQuery=true,value="Select COALESCE(cad.COMPANY_CODE, '') as COMPANY_CODE,COALESCE(cad.CLASS_ID, '') as CLASS_ID, COALESCE(cad.COLLECTOR_ID, '') as COLLECTOR_ID, COALESCE(COUNT(CLASS_ID), 0) as NO_OF_ACCOUNTS from COLL_ACCOUNT_DETAILS cad , DATE_MASTER dm where cad.COMPANY_CODE=DM.COMPANY_CODE GROUP By COLLECTOR_ID,CLASS_ID,cad.COMPANY_CODE order by CLASS_ID, COLLECTOR_ID")
    List<SatisfiedAccounts> fetchSatisfiedAccounts();
    
    @Query(nativeQuery=true,value="select COALESCE(COMPANY_CODE,'') as COMPANY_CODE, COALESCE(SUM(TOTAL_AMOUNT_DUE), 0) as TOTAL_AMOUNT_DUE, count(TOTAL_AMOUNT_DUE) as COUNT_TOTAL_AMOUNT_DUE,COALESCE(SUM(CURRENT_BALANCE),0) as CURRENT_BALANCE, count(CURRENT_BALANCE) as TOTAL_CURRENT_BALANCE, COALESCE(SUM(X_DAYS_AMOUNT),0) as X_DAYS_AMOUNT,count(X_DAYS_AMOUNT) as TOTAL_X_DAYS_AMOUNT, COALESCE(SUM(DAYS_30_AMOUNT), 0) as DAYS_30_AMOUNT, COALESCE(count(DAYS_30_AMOUNT) , 0) as TOTAL_COUNT_DAYS_30_AMOUNT,COALESCE(SUM(DAYS_60_AMOUNT), 0) as DAYS_60_AMOUNT,COALESCE(count(DAYS_60_AMOUNT), 0) as TOTAL_COUNT_DAYS_60_AMOUNT,COALESCE(SUM(DAYS_90_AMOUNT),0) as DAYS_90_AMOUNT,COALESCE(count(DAYS_90_AMOUNT), 0) as TOTAL_COUNT_DAYS_90_AMOUNT,COALESCE(SUM(DAYS_120_AMOUNT),0) as DAYS_120_AMOUNT,COALESCE(count(DAYS_120_AMOUNT),0) as TOTAL_DAYS_120_AMOUNT,COALESCE(SUM(DAYS_150_AMOUNT),0) as DAYS_150_AMOUNT, COALESCE(count(DAYS_150_AMOUNT),0) as TOTAL_DAYS_150_AMOUNT,COALESCE(SUM(DAYS_180_AMOUNT),0) as DAYS_180_AMOUNT,COALESCE(COUNT(DAYS_180_AMOUNT),0) as TOTAL_DAYS_180_AMOUNT,COALESCE(SUM(DAYS_210_AMOUNT),0) as DAYS_210_AMOUNT,COALESCE(COUNT(DAYS_210_AMOUNT),0) as TOTAL_DAYS_210_AMOUNT,COALESCE(SUM(DAYS_240_AMOUNT),0) as DAYS_240_AMOUNT,COALESCE(COUNT(DAYS_240_AMOUNT),0) as TOTAL_DAYS_240_AMOUNT,COALESCE(SUM(DAYS_270_AMOUNT),0) as DAYS_270_AMOUNT,COALESCE(COUNT(DAYS_270_AMOUNT),0) as TOTAL_DAYS_270_AMOUNT,COALESCE(SUM(DAYS_300_AMOUNT),0) as DAYS_300_AMOUNT,COALESCE(COUNT(DAYS_300_AMOUNT),0) as TOTAL_DAYS_300_AMOUNT,COALESCE(SUM(DAYS_330_AMOUNT),0) as DAYS_330_AMOUNT,COALESCE(COUNT(DAYS_330_AMOUNT),0) as TOTAL_DAYS_330_AMOUNT,COALESCE(SUM(DAYS_360_AMOUNT),0) as DAYS_360_AMOUNT, COALESCE(COUNT(DAYS_360_AMOUNT),0) as TOTAL_DAYS_360_AMOUNT from COLL_ACCOUNT_DETAILS where CONTRACT_STATUS='A' GROUP BY COMPANY_CODE")
    List<BucketWise> fetchBucketWise();
} 
