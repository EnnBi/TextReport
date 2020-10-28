package com.aaratech.leasing.report.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.aaratech.leasing.projections.ClassWiseAccountSummary;
import com.aaratech.leasing.report.dataentity.AccountDetailsPurge;

public interface AccountDetailsPurgeRepo extends CrudRepository<AccountDetailsPurge, String> {
    // DM.SYSTEM_DATE=CADP.PURGE_DATE and
    @Query(nativeQuery = true,
            value ="select COALESCE(CONTRACT_CARD_ID,'') as CONTRACT_CARD_ID,COALESCE(CUSTOMER_NAME,'') as CUSTOMER_NAME,COALESCE(COLLECTION_START_DATE,'') as COLLECTION_START_DATE,COALESCE(COLLECTION_END_DATE,'') as COLLECTION_END_DATE,COALESCE(PURGE_DATE,'') as PURGE_DATE,COALESCE(CADP.COMPANY_CODE,'') as COMPANY_CODE from COLL_ACCOUNT_DETAILS_PURGE CADP, DATE_MASTER DM where DM.COMPANY_CODE = CADP.COMPANY_CODE")
    List<AccountDetailsPurgeData> getAccountDetailsPurge();

    static interface AccountDetailsPurgeData {

        String getCOMPANY_CODE();

        String getCONTRACT_CARD_ID();
        String getCUSTOMER_NAME();

        Date getCOLLECTION_START_DATE();

        Date getCOLLECTION_END_DATE();

        Date getPURGE_DATE();

    }
    

    
    
}
