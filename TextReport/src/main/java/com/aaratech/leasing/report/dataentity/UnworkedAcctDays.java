package com.aaratech.leasing.report.dataentity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class UnworkedAcctDays {
	
	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	long id;
	
    String companyCode;

    String contractCardId;

    String customerName;

    String classId;

    Long unworkedAccountDays;

    Integer priority;

    LocalDate collectionStartDate;

    Integer delinquentDue;

    Integer totalAmountDue;

    public String getContractCardId() {
        return contractCardId;
    }

    public void setContractCardId(String contractCardId) {
        this.contractCardId = contractCardId;
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

    public Long getUnworkedAccountDays() {
        return unworkedAccountDays;
    }

    public void setUnworkedAccountDays(Long unworkedAccountDays) {
        this.unworkedAccountDays = unworkedAccountDays;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public LocalDate getCollectionStartDate() {
        return collectionStartDate;
    }

    public void setCollectionStartDate(LocalDate collectionStartDate) {
        this.collectionStartDate = collectionStartDate;
    }

    public Integer getDelinquentDue() {
        return delinquentDue;
    }

    public void setDelinquentDue(Integer delinquentDue) {
        this.delinquentDue = delinquentDue;
    }

    public Integer getTotalAmountDue() {
        return totalAmountDue;
    }

    public void setTotalAmountDue(Integer totalAmountDue) {
        this.totalAmountDue = totalAmountDue;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
}
