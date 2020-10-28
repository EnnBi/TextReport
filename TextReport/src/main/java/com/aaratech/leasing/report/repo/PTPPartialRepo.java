package com.aaratech.leasing.report.repo;

import com.aaratech.leasing.report.dataentity.PTPPartial;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface PTPPartialRepo extends CrudRepository<PTPPartial, String> {
    //where cad.COMPANY_CODE=dm.COMPANY_CODE and cad.NEXT_PTP_DATE > dm.PREV_BUSINESS_DATE and cad.NEXT_PTP_DATE < dm.PREV_BUSINESS_DATE
    @Query(nativeQuery = true,
            value ="select COALESCE(cad.CONTRACT_CARD_ID,'') as CONTRACT_CARD_ID,COALESCE(CUSTOMER_NAME, '') as CUSTOMER_NAME,COALESCE(PTP_TOTAL_AMOUNT, 0) as PTP_TOTAL_AMOUNT, COALESCE(NEXT_PTP_DATE, '') as NEXT_PTP_DATE, COALESCE(PTP_AMOUNT_PAID, 0) as PTP_AMOUNT_PAID, COALESCE(PTP_TOTAL_AMOUNT -  PTP_AMOUNT_PAID, 0) as REMAINING_AMOUNT, COALESCE(cad.COMPANY_CODE,'') as COMPANY_CODE from COLL_ACCOUNT_DETAILS cad, DATE_MASTER dm")
    List<PTPPartial> getPTPPartialData();

    static interface PTPPartial {

        String getCONTRACT_CARD_ID();

        String getCUSTOMER_NAME();

        Double getPTP_TOTAL_AMOUNT();

        Date getNEXT_PTP_DATE();

        Double getPTP_AMOUNT_PAID();

        Double getREMAINING_AMOUNT();

        String getCOMPANY_CODE();

    }

}
