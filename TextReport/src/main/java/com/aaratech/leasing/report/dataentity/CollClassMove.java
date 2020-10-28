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
@Table(name="COLL_CLASS_MOVE")
public class CollClassMove implements Serializable{


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Column(name="SEQ_NO")
    private Long seqNo;
	

    @Column(name="COMPANY_CODE")
    private String companyCode;
    
    @Id
    @Column(name="CONTRACT_CARD_ID")
    private String contractCardId;
    
    @Column(name="OLD_CLASS")
    private String oldClass;

    @Column(name="NEW_CLASS")
    private String newClass;
    
    @Temporal(TemporalType.DATE)
    @Column(name="DATE_MOVED")
    private Date dateMoved;
    
    @Column(name="OLD_COLLECTOR_ID")
    private String oldCollectorId;
    
    @Column(name="MOVED_BY")
    private String movedBy;
    
    @Column(name="STATUS")
    private String status;

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

	public String getOldClass() {
		return oldClass;
	}

	public void setOldClass(String oldClass) {
		this.oldClass = oldClass;
	}

	public String getNewClass() {
		return newClass;
	}

	public void setNewClass(String newClass) {
		this.newClass = newClass;
	}

	public Date getDateMoved() {
		return dateMoved;
	}

	public void setDateMoved(Date dateMoved) {
		this.dateMoved = dateMoved;
	}

	public String getOldCollectorId() {
		return oldCollectorId;
	}

	public void setOldCollectorId(String oldCollectorId) {
		this.oldCollectorId = oldCollectorId;
	}

	public String getMovedBy() {
		return movedBy;
	}

	public void setMovedBy(String movedBy) {
		this.movedBy = movedBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Long seqNo) {
		this.seqNo = seqNo;
	}
    
    
}
