package com.aaratech.leasing.report;

public class UnworkedAccountSummary {

	String classId;
	
	Integer noOfAccounts;
	
	Integer unworkedDays;
	
	Integer totalAmountDue;
	
	String companyCode;
	
	

	public UnworkedAccountSummary() {
		super();
		this.unworkedDays=0;
		this.totalAmountDue=0;
		this.noOfAccounts=0;
	}

	public UnworkedAccountSummary(String classId, Integer noOfAccounts, Integer unworkedDays, Integer totalAmountDue,
			String companyCode) {
		super();
		this.classId = classId;
		this.noOfAccounts = noOfAccounts;
		this.unworkedDays = unworkedDays;
		this.totalAmountDue = totalAmountDue;
		this.companyCode = companyCode;
	}

	public String getClassId() {
		return classId;
	}

	public Integer getNoOfAccounts() {
		return noOfAccounts;
	}

	public Integer getUnworkedDays() {
		return unworkedDays;
	}

	public Integer getTotalAmountDue() {
		return totalAmountDue;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public void setNoOfAccounts(Integer noOfAccounts) {
		this.noOfAccounts = noOfAccounts;
	}

	public void setUnworkedDays(Integer unworkedDays) {
		this.unworkedDays = unworkedDays;
	}

	public void setTotalAmountDue(Integer totalAmountDue) {
		this.totalAmountDue = totalAmountDue;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	
	
}
