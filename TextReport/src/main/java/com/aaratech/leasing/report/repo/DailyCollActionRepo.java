package com.aaratech.leasing.report.repo;

import com.aaratech.leasing.report.dataentity.CollActionsHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface DailyCollActionRepo  extends CrudRepository<CollActionsHistory, String> {
//where colact.ACTION_DATE=dateMaster.BUSINESS_DATE
    @Query(nativeQuery = true,
            value ="select COALESCE(ACTION_COLLECTOR_ID,'') as ACTION_COLLECTOR_ID,COALESCE(ACTION_CODE,'') as ACTION_CODE,COALESCE( ACTION_DATE_AND_TIME,'') as ACTION_DATE_AND_TIME ,COALESCE( CONTRACT_CARD_ID,'') as CONTRACT_CARD_ID ,COALESCE( ACTION_DATE,'') as ACTION_DATE,COALESCE( ACTION_TIME,'') as ACTION_TIME ,COALESCE( colact.COMPANY_CODE,'') as COMPANY_CODE from COLL_ACTIONS_HISTORY as colact,DATE_MASTER dateMaster order by ACTION_COLLECTOR_ID asc")
    List<DailyCollActions> getDailyCollAction();

    static interface DailyCollActions {


        String getACTION_COLLECTOR_ID();

        String getACTION_CODE();

        Date getACTION_DATE_AND_TIME();

        String getCONTRACT_CARD_ID();

        Date getACTION_DATE();

        Date getACTION_TIME();

        String getCOMPANY_CODE();
    }
// and colact.ACTION_DATE=dateMaster.BUSINESS_DATE
    @Query(nativeQuery = true,
            value ="select COALESCE(colact.CONTRACT_CARD_ID,'') as CONTRACT_CARD_ID,COALESCE(cad.CUSTOMER_NAME,'') as CUSTOMER_NAME,ISNULL(cad.PTP_AMOUNT,0) as PTP_AMOUNT,COALESCE(ACTION_CODE,'') as ACTION_CODE,COALESCE(ACTION_COLLECTOR_ID,'') as ACTION_COLLECTOR_ID,COALESCE(ACTION_DATE_AND_TIME,'')as ACTION_DATE_AND_TIME,COALESCE( colact.COMPANY_CODE,'') as COMPANY_CODE from COLL_ACTIONS_HISTORY as colact, COLL_ACCOUNT_DETAILS cad,  DATE_MASTER dateMaster where colact.ACTION_CODE='PTPO'")
    List<OverridePTPReport> getOverridePTPReport();

    static interface OverridePTPReport {

        String getCONTRACT_CARD_ID();

        String getCUSTOMER_NAME();

        Double getPTP_AMOUNT();

        String getACTION_CODE();

        String getACTION_COLLECTOR_ID();

        Date getACTION_DATE_AND_TIME();

        String getCOMPANY_CODE();
    }

    // and ACTION_DATE = dateMaster.BUSINESS_DATE
    @Query(nativeQuery = true,
            value ="select COALESCE(colact.CONTRACT_CARD_ID,'') as CONTRACT_CARD_ID,COALESCE(cad.CUSTOMER_NAME,'') as CUSTOMER_NAME,COALESCE(colact.ACTION_COLLECTOR_ID,'') as ACTION_COLLECTOR_ID,COALESCE(cad.COLLECTOR_ID,'') as COLLECTOR_ID,COALESCE(ACTION_DATE_AND_TIME,'') as ACTION_DATE_AND_TIME,COALESCE( colact.COMPANY_CODE,'') as COMPANY_CODE from COLL_ACTIONS_HISTORY as colact, COLL_ACCOUNT_DETAILS cad,  DATE_MASTER dateMaster where colact.ACTION_CODE='MREA'")
    List<ManualCollectorChanged> getManualCollectorChanged();

    static interface ManualCollectorChanged {

        String getCONTRACT_CARD_ID();

        String getCUSTOMER_NAME();

        String getACTION_COLLECTOR_ID();

        String getCOLLECTOR_ID();

        Timestamp getACTION_DATE_AND_TIME();

        String getCOMPANY_CODE();
    }
}
