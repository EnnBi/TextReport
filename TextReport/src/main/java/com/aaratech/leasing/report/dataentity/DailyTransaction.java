package com.aaratech.leasing.report.dataentity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="COLL_PAYMENT_HISTORY")
public class DailyTransaction {

    @Id
    @Column(name = "CONTRACT_ID")
    private String contractId;

    @Column(name = "CUSTOMER_NAME")
    private String customer_name;

    @Column(name = "POSTING_DATE")
    private String posting_date;

    @Column(name = "TXN_DESCRIPTION")
    private String txn_description;

    @Column(name = "TXN_TYPE")
    private String txn_type;

    @Column(name = "TXN_AMOUNT")
    private Double txn_amount;

    @Column(name = "TXN_AMOUNT_FLAG")
    private String txn_amount_Flag;

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getPosting_date() {
        return posting_date;
    }

    public void setPosting_date(String posting_date) {
        this.posting_date = posting_date;
    }

    public String getTxn_description() {
        return txn_description;
    }

    public void setTxn_description(String txn_description) {
        this.txn_description = txn_description;
    }

    public String getTxn_type() {
        return txn_type;
    }

    public void setTxn_type(String txn_type) {
        this.txn_type = txn_type;
    }

    public Double getTxn_amount() {
        return txn_amount;
    }

    public void setTxn_amount(Double txn_amount) {
        this.txn_amount = txn_amount;
    }

    public String getTxn_amount_Flag() {
        return txn_amount_Flag;
    }

    public void setTxn_amount_Flag(String txn_amount_Flag) {
        this.txn_amount_Flag = txn_amount_Flag;
    }



}
