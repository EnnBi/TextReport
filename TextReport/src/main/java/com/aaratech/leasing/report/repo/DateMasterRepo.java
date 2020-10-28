package com.aaratech.leasing.report.repo;

import com.aaratech.leasing.report.entity.DateMaster;
import org.springframework.data.repository.CrudRepository;

public interface DateMasterRepo  extends CrudRepository<DateMaster, String> {
        DateMaster findByCompanyCode(String companyCode);
}
