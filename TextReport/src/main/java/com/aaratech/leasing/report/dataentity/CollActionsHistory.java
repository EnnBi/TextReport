package com.aaratech.leasing.report.dataentity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="COLL_ACTIONS_HISTORY")
public class CollActionsHistory implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="COMPANY_CODE")
    private String companyCode;
    
    @Id
    @Column(name="CONTRACT_CARD_ID")
    private String contractCardId;

    @Column(name="ACTION_CODE")
    private String actionCode;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="ACTION_DATE_AND_TIME")
    private Date actionDateAndTime;
    
    @Temporal(TemporalType.DATE)
    @Column(name="ACTION_DATE")
    private Date actionDate;
    
    @Temporal(TemporalType.TIME)
    @Column(name="ACTION_TIME", length=1)
    private Date actionTime;
    
    @Column(name="ACTION_COLLECTOR_ID")
    private String actionCollectorId;
    

    
    @Column(name="ACTION_STATUS")
    private String actionStatus;
    
    
    public String getCompanyCode() {
        return companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    
    public String getContractCardId() {
        return contractCardId;
    }
    
    public void setContractCardId(String contractCardId) {
        this.contractCardId = contractCardId;
    }
    
    public String getActionCode() {
        return actionCode;
    }
    
    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }
    
    public Date getActionDate() {
        return actionDate;
    }
    
    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }
    
    public Date getActionTime() {
        return actionTime;
    }
    
    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }
    
    public String getActionCollectorId() {
        return actionCollectorId;
    }
    
    public void setActionCollectorId(String actionCollectorId) {
        this.actionCollectorId = actionCollectorId;
    }
    
    public Date getActionDateAndTime() {
        return actionDateAndTime;
    }
    
    public void setActionDateAndTime(Date actionDateAndTime) {
        this.actionDateAndTime = actionDateAndTime;
    }
    
    public String getActionStatus() {
        return actionStatus;
    }
    
    public void setActionStatus(String actionStatus) {
        this.actionStatus = actionStatus;
    }
    
    
}
