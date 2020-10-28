package com.aaratech.leasing.report.repo;

import com.aaratech.leasing.report.dataentity.UnworkedAcctDays;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface UnworkedAccountDaysRepo extends CrudRepository<UnworkedAcctDays,Long> {

}
