package com.aaratech.leasing.report;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="COLL_ACCOUNT_DETAILS")
public class CollAccountDetails implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="COMPANY_CODE")
    private String companyCode;
    
    @Id
    @Column(name="CONTRACT_CARD_ID")
    private String contractCardId;
    
    @Column(name="ACCOUNT_NUMBER")
    private String accountNumber;
    
    @Column(name="CUSTOMER_ID")
    private String customerId;
    
    @Column(name="REASON_CODE")
    private String reasonCode;
    
    @Column(name="PRIORITY")
    private Integer priority;
    
    @Column(name="CUSTOMER_NAME")
    private String customerName;
    
    @Column(name="CLASS_ID")
    private String classId;
    
    @Column(name="COLLECTOR_ID")
    private String collectorId;
    
    @Column(name="PAYMENT_FREQUENCY")
    private String paymentFrequency;
    
    @Column(name="LEASE_TYPE")
    private String leaseType;
    
    @Column(name="CURRENCY")
    private String currency;
    
    @Column(name="DELINQUENT_DUE")
    private Integer delinquentDue;
    
    @Column(name="BILLING_CYCLE_STMT_DAY")
    private Integer billingCycleStmtDay;
    
    @Column(name="AMOUNT_LAST_REQUESTED")
    private Double amountLastRequested;
    
    @Column(name="LAST_PAYMENT_AMOUNT")
    private Double lastPaymentAmount;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="NEXT_PAYMENT_DUE_DATE")
    private Date nextPaymentDueDate;
    
    @Column(name="NO_OF_DAYS_DELINQUENT")
    private Integer noOfDaysDelinquent;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="NEXT_SCHEDULED_FOR_REVIEW")
    private Date nextScheduledForReview;
    
    @Column(name="AMT_DUE_CURR_MONTH_STMT")
    private Double amtDueCurrMonthStmt;
    
    @Column(name="PTP_AMOUNT")
    private Double ptpAmount;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="PTP_AMOUNT_DUE_DATE")
    private Date ptpAmountDueDate;
    
    @Column(name="TOTAL_AMOUNT_DUE")
    private Double totalAmountDue;
    
    @Column(name="CURRENT_BALANCE")
    private Double currentBalance;
    
    @Column(name="X_DAYS_AMOUNT")
    private Double xDaysAmount;
    
    @Column(name="DAYS_30_AMOUNT")
    private Double days30Amount;
    
    @Column(name="DAYS_60_AMOUNT")
    private Double days60Amount;
    
    @Column(name="DAYS_90_AMOUNT")
    private Double days90Amount;
    
    @Column(name="AR_BALANCE")
    private Double arBalance;
    
    @Column(name="PRINCIPAL_OVERDUE")
    private Double principalOverdue;
    
    @Column(name="INTEREST_OVERDUE")
    private Double interestOverdue;
    
    @Column(name="FEES_AND_CHARGES_OVERDUE")
    private Double feesAndChargesOverdue;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DATE_OPEN")
    private Date dateOpen;
    
    @Column(name="BLOCK_CODE")
    private String blockCode;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="BLOCK_CODE_DATE")
    private Date blockCodeDate;
    
    @Column(name="DAYS_120_AMOUNT")
    private Double days120Amount;
    
    @Column(name="DAYS_150_AMOUNT")
    private Double days150Amount;
    
    @Column(name="DAYS_180_AMOUNT")
    private Double days180Amount;
    
    @Column(name="DAYS_210_AMOUNT")
    private Double days210Amount;
    
    @Column(name="DAYS_240_AMOUNT")
    private Double days240Amount;
    
    @Column(name="DAYS_270_AMOUNT")
    private Double days270Amount;
    
    @Column(name="DAYS_300_AMOUNT")
    private Double days300Amount;
    
    @Column(name="DAYS_330_AMOUNT")
    private Double days330Amount;
    
    @Column(name="DAYS_360_AMOUNT")
    private Double days360Amount;
    
    @Column(name="MONTHS_24_PROFILE")
    private String months24Profile;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="LAST_DELQ_DATE")
    private Date lastDelqDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="PREV_DELQ_DATE")
    private Date prevDelqDate;
    
    @Column(name="TIMES_IN_COLLECTIONS")
    private Integer timesInCollections;
    
    @Column(name="NUMBER_OF_BROKEN_PROMISES")
    private Integer numberOfBrokenPromises;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="COLLECTION_START_DATE")
    private Date collectionStartDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="COLLECTION_END_DATE")
    private Date collectionEndDate;
    
    @Column(name="CONTRACT_STATUS")
    private String contractStatus;
    
    @Column(name="MOBILE_PHONE")
    private String mobilePhone;
    
    @Column(name="PTP_COLLECTOR_ID")
    private String ptpCollectorId;
    
    @Temporal(TemporalType.DATE)
    @Column(name="LAST_STMT_DATE")
    private Date lastStmtDate;
    
    @Temporal(TemporalType.DATE)
    @Column(name="CLASS_MOVE_DATE")
    private Date classMoveDate;
    
    @Temporal(TemporalType.DATE)
    @Column(name="PREV_PAYMENT_DATE")
    private Date prevPaymentDate;
    
    @Column(name="PTP_AMOUNT_PAID")
    private Double ptpAmountPaid;
    
    @Column(name="CREDIT_LIMIT_LOAN_AMOUNT")
    private Double creditLimitLoanAmount;
    
    @Temporal(TemporalType.DATE)
    @Column(name="PREV_PAYMENT_DUE_DATE")
    private Date prevPaymentDueDate;
    
    @Column(name="FIELD_ASSIGN_FLAG")
    private String fieldAssignFlag;
    
    @Column(name="OVER_LIMIT_PERCENTAGE")
    private Double overLimitPercentage;
    
    @Temporal(TemporalType.DATE)
    @Column(name="PTP_LAST_PAYMENT_DATE")
    private Date ptpLastPaymentDate;
    
    @Column(name="PTP_REMAINING_AMOUNT")
    private Double ptpRemainingAmount;
    
    @Column(name="PTP_TYPE")
    private String ptpType;
    
    @Temporal(TemporalType.DATE)
    @Column(name="BROKEN_PROMISE_DATE")
    private Date brokenPromiseDate;
    
    @Column(name="ACCOUNT_ACTION_FLAG")
    private String accountActionFlag;

    @Column(name="KEPT_PROMISES_COUNTER")
    private Integer keptPromisesCounter;

    @Temporal(TemporalType.DATE)
    @Column(name="LAST_PROCESSED_DATE")
    private Date lastProcessedDate;
    
    @Column(name="OLD_COLLECTOR_ID")
    private String oldCollectorId;
    
    @Column(name="ROUTED_TO_MANAGER")
    private String routedToManager;
    
    @Column(name="COLLECTION_RETENTION_FLAG")
    private String collectionRetentionFlag;
    
    @Column(name="REASSIGN_DAYS")
    private Integer reassignDays;
    
    @Column(name="REASSIGN_REASON")
    private String reassignReason;
    
    @Temporal(TemporalType.DATE)
    @Column(name="REASSIGN_DATE")
    private Date reassignDate;
    
    @Column(name="REASSIGN_OLD_CLASS")
    private String reassignOldClass;
    
    @Column(name="AUTO_REASSIGNMENT")
    private String autoReassignment;
    
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

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getCollectorId() {
		return collectorId;
	}

	public void setCollectorId(String collectorId) {
		this.collectorId = collectorId;
	}

	public String getPaymentFrequency() {
		return paymentFrequency;
	}

	public void setPaymentFrequency(String paymentFrequency) {
		this.paymentFrequency = paymentFrequency;
	}

	public String getLeaseType() {
		return leaseType;
	}

	public void setLeaseType(String leaseType) {
		this.leaseType = leaseType;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getDelinquentDue() {
		return delinquentDue;
	}

	public void setDelinquentDue(Integer delinquentDue) {
		this.delinquentDue = delinquentDue;
	}

	public Integer getBillingCycleStmtDay() {
		return billingCycleStmtDay;
	}

	public void setBillingCycleStmtDay(Integer billingCycleStmtDay) {
		this.billingCycleStmtDay = billingCycleStmtDay;
	}

	public Double getAmountLastRequested() {
		return amountLastRequested;
	}

	public void setAmountLastRequested(Double amountLastRequested) {
		this.amountLastRequested = amountLastRequested;
	}

	public Double getLastPaymentAmount() {
		return lastPaymentAmount;
	}

	public void setLastPaymentAmount(Double lastPaymentAmount) {
		this.lastPaymentAmount = lastPaymentAmount;
	}

	public Date getNextPaymentDueDate() {
		return nextPaymentDueDate;
	}

	public void setNextPaymentDueDate(Date nextPaymentDueDate) {
		this.nextPaymentDueDate = nextPaymentDueDate;
	}

	public Integer getNoOfDaysDelinquent() {
		return noOfDaysDelinquent;
	}

	public void setNoOfDaysDelinquent(Integer noOfDaysDelinquent) {
		this.noOfDaysDelinquent = noOfDaysDelinquent;
	}

	public Date getNextScheduledForReview() {
		return nextScheduledForReview;
	}

	public void setNextScheduledForReview(Date nextScheduledForReview) {
		this.nextScheduledForReview = nextScheduledForReview;
	}

	public Double getAmtDueCurrMonthStmt() {
		return amtDueCurrMonthStmt;
	}

	public void setAmtDueCurrMonthStmt(Double amtDueCurrMonthStmt) {
		this.amtDueCurrMonthStmt = amtDueCurrMonthStmt;
	}

	public Double getPtpAmount() {
		return ptpAmount;
	}

	public void setPtpAmount(Double ptpAmount) {
		this.ptpAmount = ptpAmount;
	}

	public Date getPtpAmountDueDate() {
		return ptpAmountDueDate;
	}

	public void setPtpAmountDueDate(Date ptpAmountDueDate) {
		this.ptpAmountDueDate = ptpAmountDueDate;
	}

	public Double getTotalAmountDue() {
		return totalAmountDue;
	}

	public void setTotalAmountDue(Double totalAmountDue) {
		this.totalAmountDue = totalAmountDue;
	}

	public Double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public Double getxDaysAmount() {
		return xDaysAmount;
	}

	public void setxDaysAmount(Double xDaysAmount) {
		this.xDaysAmount = xDaysAmount;
	}

	public Double getDays30Amount() {
		return days30Amount;
	}

	public void setDays30Amount(Double days30Amount) {
		this.days30Amount = days30Amount;
	}

	public Double getDays60Amount() {
		return days60Amount;
	}

	public void setDays60Amount(Double days60Amount) {
		this.days60Amount = days60Amount;
	}

	public Double getDays90Amount() {
		return days90Amount;
	}

	public void setDays90Amount(Double days90Amount) {
		this.days90Amount = days90Amount;
	}

	public Double getArBalance() {
		return arBalance;
	}

	public void setArBalance(Double arBalance) {
		this.arBalance = arBalance;
	}

	public Double getPrincipalOverdue() {
		return principalOverdue;
	}

	public void setPrincipalOverdue(Double principalOverdue) {
		this.principalOverdue = principalOverdue;
	}

	public Double getInterestOverdue() {
		return interestOverdue;
	}

	public void setInterestOverdue(Double interestOverdue) {
		this.interestOverdue = interestOverdue;
	}

	public Double getFeesAndChargesOverdue() {
		return feesAndChargesOverdue;
	}

	public void setFeesAndChargesOverdue(Double feesAndChargesOverdue) {
		this.feesAndChargesOverdue = feesAndChargesOverdue;
	}

	public Date getDateOpen() {
		return dateOpen;
	}

	public void setDateOpen(Date dateOpen) {
		this.dateOpen = dateOpen;
	}

	public String getBlockCode() {
		return blockCode;
	}

	public void setBlockCode(String blockCode) {
		this.blockCode = blockCode;
	}

	public Date getBlockCodeDate() {
		return blockCodeDate;
	}

	public void setBlockCodeDate(Date blockCodeDate) {
		this.blockCodeDate = blockCodeDate;
	}

	public Double getDays120Amount() {
		return days120Amount;
	}

	public void setDays120Amount(Double days120Amount) {
		this.days120Amount = days120Amount;
	}

	public Double getDays150Amount() {
		return days150Amount;
	}

	public void setDays150Amount(Double days150Amount) {
		this.days150Amount = days150Amount;
	}

	public Double getDays180Amount() {
		return days180Amount;
	}

	public void setDays180Amount(Double days180Amount) {
		this.days180Amount = days180Amount;
	}

	public Double getDays210Amount() {
		return days210Amount;
	}

	public void setDays210Amount(Double days210Amount) {
		this.days210Amount = days210Amount;
	}

	public Double getDays240Amount() {
		return days240Amount;
	}

	public void setDays240Amount(Double days240Amount) {
		this.days240Amount = days240Amount;
	}

	public Double getDays270Amount() {
		return days270Amount;
	}

	public void setDays270Amount(Double days270Amount) {
		this.days270Amount = days270Amount;
	}

	public Double getDays300Amount() {
		return days300Amount;
	}

	public void setDays300Amount(Double days300Amount) {
		this.days300Amount = days300Amount;
	}

	public Double getDays330Amount() {
		return days330Amount;
	}

	public void setDays330Amount(Double days330Amount) {
		this.days330Amount = days330Amount;
	}

	public Double getDays360Amount() {
		return days360Amount;
	}

	public void setDays360Amount(Double days360Amount) {
		this.days360Amount = days360Amount;
	}

	public String getMonths24Profile() {
		return months24Profile;
	}

	public void setMonths24Profile(String months24Profile) {
		this.months24Profile = months24Profile;
	}

	public Date getLastDelqDate() {
		return lastDelqDate;
	}

	public void setLastDelqDate(Date lastDelqDate) {
		this.lastDelqDate = lastDelqDate;
	}

	public Date getPrevDelqDate() {
		return prevDelqDate;
	}

	public void setPrevDelqDate(Date prevDelqDate) {
		this.prevDelqDate = prevDelqDate;
	}

	public Integer getTimesInCollections() {
		return timesInCollections;
	}

	public void setTimesInCollections(Integer timesInCollections) {
		this.timesInCollections = timesInCollections;
	}

	public Integer getNumberOfBrokenPromises() {
		return numberOfBrokenPromises;
	}

	public void setNumberOfBrokenPromises(Integer numberOfBrokenPromises) {
		this.numberOfBrokenPromises = numberOfBrokenPromises;
	}

	public Date getCollectionStartDate() {
		return collectionStartDate;
	}

	public void setCollectionStartDate(Date collectionStartDate) {
		this.collectionStartDate = collectionStartDate;
	}

	public Date getCollectionEndDate() {
		return collectionEndDate;
	}

	public void setCollectionEndDate(Date collectionEndDate) {
		this.collectionEndDate = collectionEndDate;
	}

	public String getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getPtpCollectorId() {
		return ptpCollectorId;
	}

	public void setPtpCollectorId(String ptpCollectorId) {
		this.ptpCollectorId = ptpCollectorId;
	}

	public Date getLastStmtDate() {
		return lastStmtDate;
	}

	public void setLastStmtDate(Date lastStmtDate) {
		this.lastStmtDate = lastStmtDate;
	}

	public Date getClassMoveDate() {
		return classMoveDate;
	}

	public void setClassMoveDate(Date classMoveDate) {
		this.classMoveDate = classMoveDate;
	}

	public Date getPrevPaymentDate() {
		return prevPaymentDate;
	}

	public void setPrevPaymentDate(Date prevPaymentDate) {
		this.prevPaymentDate = prevPaymentDate;
	}

	public Double getPtpAmountPaid() {
		return ptpAmountPaid;
	}

	public void setPtpAmountPaid(Double ptpAmountPaid) {
		this.ptpAmountPaid = ptpAmountPaid;
	}

	public Double getCreditLimitLoanAmount() {
		return creditLimitLoanAmount;
	}

	public void setCreditLimitLoanAmount(Double creditLimitLoanAmount) {
		this.creditLimitLoanAmount = creditLimitLoanAmount;
	}

	public Date getPrevPaymentDueDate() {
		return prevPaymentDueDate;
	}

	public void setPrevPaymentDueDate(Date prevPaymentDueDate) {
		this.prevPaymentDueDate = prevPaymentDueDate;
	}

	public String getFieldAssignFlag() {
		return fieldAssignFlag;
	}

	public void setFieldAssignFlag(String fieldAssignFlag) {
		this.fieldAssignFlag = fieldAssignFlag;
	}

	public Double getOverLimitPercentage() {
		return overLimitPercentage;
	}

	public void setOverLimitPercentage(Double overLimitPercentage) {
		this.overLimitPercentage = overLimitPercentage;
	}

	public Date getPtpLastPaymentDate() {
		return ptpLastPaymentDate;
	}

	public void setPtpLastPaymentDate(Date ptpLastPaymentDate) {
		this.ptpLastPaymentDate = ptpLastPaymentDate;
	}

	public Double getPtpRemainingAmount() {
		return ptpRemainingAmount;
	}

	public void setPtpRemainingAmount(Double ptpRemainingAmount) {
		this.ptpRemainingAmount = ptpRemainingAmount;
	}

	public String getPtpType() {
		return ptpType;
	}

	public void setPtpType(String ptpType) {
		this.ptpType = ptpType;
	}

	public Date getBrokenPromiseDate() {
		return brokenPromiseDate;
	}

	public void setBrokenPromiseDate(Date brokenPromiseDate) {
		this.brokenPromiseDate = brokenPromiseDate;
	}

	public String getAccountActionFlag() {
		return accountActionFlag;
	}

	public void setAccountActionFlag(String accountActionFlag) {
		this.accountActionFlag = accountActionFlag;
	}

	public Integer getKeptPromisesCounter() {
		return keptPromisesCounter;
	}

	public void setKeptPromisesCounter(Integer keptPromisesCounter) {
		this.keptPromisesCounter = keptPromisesCounter;
	}
	
	public Date getLastProcessedDate() {
		return lastProcessedDate;
	}

	public void setLastProcessedDate(Date lastProcessedDate) {
		this.lastProcessedDate = lastProcessedDate;
	}

	public String getOldCollectorId() {
		return oldCollectorId;
	}

	public void setOldCollectorId(String oldCollectorId) {
		this.oldCollectorId = oldCollectorId;
	}

	public String getRoutedToManager() {
		return routedToManager;
	}

	public void setRoutedToManager(String routedToManager) {
		this.routedToManager = routedToManager;
	}

	public String getCollectionRetentionFlag() {
		return collectionRetentionFlag;
	}

	public void setCollectionRetentionFlag(String collectionRetentionFlag) {
		this.collectionRetentionFlag = collectionRetentionFlag;
	}

	public Integer getReassignDays() {
		return reassignDays;
	}

	public void setReassignDays(Integer reassignDays) {
		this.reassignDays = reassignDays;
	}

	public String getReassignReason() {
		return reassignReason;
	}

	public void setReassignReason(String reassignReason) {
		this.reassignReason = reassignReason;
	}

	public Date getReassignDate() {
		return reassignDate;
	}

	public void setReassignDate(Date reassignDate) {
		this.reassignDate = reassignDate;
	}

	public String getReassignOldClass() {
		return reassignOldClass;
	}

	public void setReassignOldClass(String reassignOldClass) {
		this.reassignOldClass = reassignOldClass;
	}

	public String getAutoReassignment() {
		return autoReassignment;
	}

	public void setAutoReassignment(String autoReassignment) {
		this.autoReassignment = autoReassignment;
	}

	@Override
	public String toString() {
		return "CollAccountDetails [companyCode=" + companyCode + ", contractCardId=" + contractCardId
				+ ", accountNumber=" + accountNumber + ", customerId=" + customerId + ", reasonCode=" + reasonCode
				+ ", priority=" + priority + ", customerName=" + customerName + ", classId=" + classId
				+ ", collectorId=" + collectorId + ", paymentFrequency=" + paymentFrequency + ", leaseType=" + leaseType
				+ ", currency=" + currency + ", delinquentDue=" + delinquentDue + ", billingCycleStmtDay="
				+ billingCycleStmtDay + ", amountLastRequested=" + amountLastRequested + ", lastPaymentAmount="
				+ lastPaymentAmount + ", nextPaymentDueDate=" + nextPaymentDueDate + ", noOfDaysDelinquent="
				+ noOfDaysDelinquent + ", nextScheduledForReview=" + nextScheduledForReview + ", amtDueCurrMonthStmt="
				+ amtDueCurrMonthStmt + ", ptpAmount=" + ptpAmount + ", ptpAmountDueDate=" + ptpAmountDueDate
				+ ", totalAmountDue=" + totalAmountDue + ", currentBalance=" + currentBalance + ", xDaysAmount="
				+ xDaysAmount + ", days30Amount=" + days30Amount + ", days60Amount=" + days60Amount + ", days90Amount="
				+ days90Amount + ", arBalance=" + arBalance + ", principalOverdue=" + principalOverdue
				+ ", interestOverdue=" + interestOverdue + ", feesAndChargesOverdue=" + feesAndChargesOverdue
				+ ", dateOpen=" + dateOpen + ", blockCode=" + blockCode + ", blockCodeDate=" + blockCodeDate
				+ ", days120Amount=" + days120Amount + ", days150Amount=" + days150Amount + ", days180Amount="
				+ days180Amount + ", days210Amount=" + days210Amount + ", days240Amount=" + days240Amount
				+ ", days270Amount=" + days270Amount + ", days300Amount=" + days300Amount + ", days330Amount="
				+ days330Amount + ", days360Amount=" + days360Amount + ", months24Profile=" + months24Profile
				+ ", lastDelqDate=" + lastDelqDate + ", prevDelqDate=" + prevDelqDate + ", timesInCollections="
				+ timesInCollections + ", numberOfBrokenPromises=" + numberOfBrokenPromises + ", collectionStartDate="
				+ collectionStartDate + ", collectionEndDate=" + collectionEndDate + ", contractStatus="
				+ contractStatus + ", mobilePhone=" + mobilePhone + ", ptpCollectorId=" + ptpCollectorId
				+ ", lastStmtDate=" + lastStmtDate + ", classMoveDate=" + classMoveDate + ", prevPaymentDate="
				+ prevPaymentDate + ", ptpAmountPaid=" + ptpAmountPaid + ", creditLimitLoanAmount="
				+ creditLimitLoanAmount + ", prevPaymentDueDate=" + prevPaymentDueDate + ", fieldAssignFlag="
				+ fieldAssignFlag + ", overLimitPercentage=" + overLimitPercentage + ", ptpLastPaymentDate="
				+ ptpLastPaymentDate + ", ptpRemainingAmount=" + ptpRemainingAmount + ", ptpType=" + ptpType
				+ ", brokenPromiseDate=" + brokenPromiseDate + ", accountActionFlag=" + accountActionFlag
				+ ", keptPromisesCounter=" + keptPromisesCounter + ", lastProcessedDate=" + lastProcessedDate
				+ ", oldCollectorId=" + oldCollectorId + ", routedToManager=" + routedToManager
				+ ", collectionRetentionFlag=" + collectionRetentionFlag + ", reassignDays=" + reassignDays
				+ ", reassignReason=" + reassignReason + ", reassignDate=" + reassignDate + ", reassignOldClass="
				+ reassignOldClass + ", autoReassignment=" + autoReassignment + "]";
	}
	
	
	
    
    
    
}


