package com.aaratech.leasing.report.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.aaratech.leasing.report.dataentity.CollException;

public interface CollExceptionRepo extends CrudRepository<CollException, String> {

    @Query(nativeQuery = true,
            value ="select COALESCE(CONTRACT_CARD_ID, '') as CONTRACT_CARD_ID,COALESCE(CUSTOMER_ID, '') as CUSTOMER_ID,COALESCE(REASON_CODE, '') as REASON_CODE,COALESCE(PROCESS_DATE, '') as PROCESS_DATE, COALESCE(REJECT_REASON, '') as REJECT_REASON, COALESCE(COMPANY_CODE, '') as COMPANY_CODE from COLL_EXCEPTION")
    List<RejectedRecords> getRejectedRecordsList();

    static interface RejectedRecords {

        String getCONTRACT_CARD_ID();

        String getCUSTOMER_ID();

        String getREASON_CODE();

        String getREJECT_REASON();

        Date getPROCESS_DATE();

        String getCOMPANY_CODE();
    }

}
