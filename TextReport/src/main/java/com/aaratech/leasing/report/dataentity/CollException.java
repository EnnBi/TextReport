package com.aaratech.leasing.report.dataentity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="COLL_EXCEPTION")
public class CollException {
    @Id
    @Column(name = "CONTRACT_CARD_ID")
    private String getCONTRACT_CARD_ID;
    @Column(name = "CUSTOMER_ID")
    private String getCUSTOMER_ID;
    @Column(name = "REASON_CODE")
    private String getREASON_CODE;
    @Column(name = "REJECT_REASON")
    private String getREJECT_REASON;
    @Column(name = "PROCESS_DATE")

    private Date getPROCESS_DATE;
    @Column(name = "COMPANY_CODE")
    private String getCOMPANY_CODE;

    public String getGetCONTRACT_CARD_ID() {
        return getCONTRACT_CARD_ID;
    }

    public void setGetCONTRACT_CARD_ID(String getCONTRACT_CARD_ID) {
        this.getCONTRACT_CARD_ID = getCONTRACT_CARD_ID;
    }

    public String getGetCUSTOMER_ID() {
        return getCUSTOMER_ID;
    }

    public void setGetCUSTOMER_ID(String getCUSTOMER_ID) {
        this.getCUSTOMER_ID = getCUSTOMER_ID;
    }

    public String getGetREASON_CODE() {
        return getREASON_CODE;
    }

    public void setGetREASON_CODE(String getREASON_CODE) {
        this.getREASON_CODE = getREASON_CODE;
    }

    public String getGetREJECT_REASON() {
        return getREJECT_REASON;
    }

    public void setGetREJECT_REASON(String getREJECT_REASON) {
        this.getREJECT_REASON = getREJECT_REASON;
    }

    public Date getGetPROCESS_DATE() {
        return getPROCESS_DATE;
    }

    public void setGetPROCESS_DATE(Date getPROCESS_DATE) {
        this.getPROCESS_DATE = getPROCESS_DATE;
    }

    public String getGetCOMPANY_CODE() {
        return getCOMPANY_CODE;
    }

    public void setGetCOMPANY_CODE(String getCOMPANY_CODE) {
        this.getCOMPANY_CODE = getCOMPANY_CODE;
    }
}
