package com.aaratech.leasing.report.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "DATE_MASTER")
public class DateMaster implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "SYSTEM_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date systemDate;

	@Column(name = "BUSINESS_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date businessDate;

	// For Leasing_dev DB
	/*@Column(name = "DATE_LAST_PROCESSED")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateLastProcessed;

	@Column(name = "DATE_NEXT_PROCESS")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateNextProcess;*/

	//For Leasing_DEV DB
	@Column(name = "PREV_BUSINESS_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateLastProcessed;
	//For Leasing_DEV DB
	@Column(name = "NEXT_BUSINESS_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateNextProcess;

	
	@Column(name = "COMPANY_CODE")
	private String companyCode;
	
	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	@Id
	@Column(name = "Manage_Date_Master_id")
	private Long manageDateMasterid;
	
	

	/**
	 * @return the systemDate
	 */
	public Date getSystemDate() {
		//return systemDate;
		return (systemDate);
	}

	/**
	 * @param systemDate
	 *            the systemDate to set
	 */
	public void setSystemDate(Date systemDate) {
		this.systemDate = systemDate;
	}

	/**
	 * @return the businessDate
	 */
	public Date getBusinessDate() {
		//return businessDate;
		return (businessDate);
	}

	/**
	 * @param businessDate
	 *            the businessDate to set
	 */
	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	/**
	 * @return the dateLastProcessed
	 */
	public Date getDateLastProcessed() {
	//	return dateLastProcessed;
		return (dateLastProcessed);
	}

	/**
	 * @param dateLastProcessed
	 *            the dateLastProcessed to set
	 */
	public void setDateLastProcessed(Date dateLastProcessed) {
		this.dateLastProcessed = dateLastProcessed;
	}

	/**
	 * @return the dateNextProcess
	 */
	public Date getDateNextProcess() {
		//return dateNextProcess;
		return (dateNextProcess);
	}

	/**
	 * @param dateNextProcess
	 *            the dateNextProcess to set
	 */
	public void setDateNextProcess(Date dateNextProcess) {
		this.dateNextProcess = dateNextProcess;
	}

	/**
	 * @return the manageDateMasterid
	 */
	public Long getManageDateMasterid() {
		return manageDateMasterid;
	}

	/**
	 * @param manageDateMasterid
	 *            the manageDateMasterid to set
	 */
	public void setManageDateMasterid(Long manageDateMasterid) {
		this.manageDateMasterid = manageDateMasterid;
	}

	@Override
	public String toString() {
		return "ManageDateMaster [systemDate=" + systemDate + ", businessDate=" + businessDate + ", dateLastProcessed="
				+ dateLastProcessed + ", dateNextProcess=" + dateNextProcess + "]";
	}

}
