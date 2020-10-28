package com.aaratech.leasing.report.dataentity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="COLL_VARIABLE_SCHEDULE")
public class PTPPartial {

    @Id
    @Column(name = "CONTRACT_CARD_ID")
    private String CONTRACT_CARD_ID;
}
