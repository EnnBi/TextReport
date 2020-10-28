package com.aaratech.leasing.report.dataentity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="COLL_ACCOUNT_DETAILS_PURGE")
public class AccountDetailsPurge {

    @Id
    @Column(name="CONTRACT_CARD_ID")
    private String contract_card_id;

    @Column(name="CUSTOMER_NAME")
    private String customerName;

    @Column(name="COLLECTION_START_DATE")
    private Timestamp collectionStartDate;

    @Column(name="COLLECTION_END_DATE")
    private Timestamp CollectionEndDate;

    @Column(name="PURGE_DATE")
    private Timestamp purgeDate;

}
