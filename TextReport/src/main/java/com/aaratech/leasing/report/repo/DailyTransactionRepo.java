package com.aaratech.leasing.report.repo;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.aaratech.leasing.report.dataentity.DailyTransaction;

public interface DailyTransactionRepo extends CrudRepository<DailyTransaction, String> {

    //cph.POSTING_DATE=dateMaster.BUSINESS_DATE
    @Query(nativeQuery = true,
            value ="select COALESCE(CONTRACT_ID, '') as CONTRACT_ID, COALESCE(cad.CUSTOMER_NAME, '') as CUSTOMER_NAME, COALESCE(POSTING_DATE, '') as POSTING_DATE, COALESCE(TXN_DESCRIPTION, '') as TXN_DESCRIPTION, COALESCE(TXN_TYPE, '') as TXN_TYPE,COALESCE(TXN_AMOUNT, 0) as TXN_AMOUNT, COALESCE(cad.COMPANY_CODE, '') as COMPANY_CODE from COLL_PAYMENT_HISTORY cph, DATE_MASTER dateMaster, COLL_ACCOUNT_DETAILS cad where  cad.CONTRACT_CARD_ID = cph.CONTRACT_ID")
    List<DailyTransactionData> getDailyCollAction();

    static interface DailyTransactionData {

         String getCONTRACT_ID();

         String getCUSTOMER_NAME();

         Date getPOSTING_DATE();

         String getTXN_DESCRIPTION();

         String getTXN_TYPE();

         Double getTXN_AMOUNT();

         //String getTXN_AMOUNT_FLAG();

         String getCOMPANY_CODE();

    }
}
