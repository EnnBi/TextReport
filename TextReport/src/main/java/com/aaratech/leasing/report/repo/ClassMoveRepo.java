package com.aaratech.leasing.report.repo;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.aaratech.leasing.report.dataentity.CollClassMove;

public interface ClassMoveRepo  extends CrudRepository<CollClassMove, String> {

//and classmove.DATE_MOVED=dateMaster.BUSINESS_DATE
        @Query(nativeQuery = true,
                value ="select COALESCE(classmove.COMPANY_CODE,'')as COMPANY_CODE, COALESCE(classmove.CONTRACT_CARD_ID,'') as CONTRACT_CARD_ID, COALESCE(accdetails.CUSTOMER_NAME, '') as CUSTOMER_NAME, COALESCE(classmove.DATE_MOVED, '') as DATE_MOVED, COALESCE(classmove.MOVED_BY,'') as MOVED_BY, COALESCE(classmove.OLD_CLASS, '') as OLD_CLASS, COALESCE(classmove.NEW_CLASS,'') as NEW_CLASS from COLL_CLASS_MOVE as classmove, COLL_ACCOUNT_DETAILS accdetails,DATE_MASTER dateMaster where classmove.COMPANY_CODE = accdetails.COMPANY_CODE  and classmove.COMPANY_CODE = dateMaster.COMPANY_CODE and classmove.CONTRACT_CARD_ID = accdetails.CONTRACT_CARD_ID"                )
        List<ClassMove> getCollClassMoves();

        static interface ClassMove {


                public String getCOMPANY_CODE();

                public String getCONTRACT_CARD_ID();
                public String getCUSTOMER_NAME();

                public Date getDATE_MOVED();

                public String getMOVED_BY();

                public String getOLD_CLASS();

                public String getNEW_CLASS();
        }

}
