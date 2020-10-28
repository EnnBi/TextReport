package com.aaratech.leasing.report.repo;

import com.aaratech.leasing.report.dataentity.UnworkedAcctDays;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface UnworkedAccountDaysRepo extends CrudRepository<UnworkedAcctDays,Long> {

    /*@Query("");
    List<UnworkedAcctDays> fetchUnworkedAccntDays();*/
   /* @Query(value="Select case when exists (Select a from Action_History a where CONTRACT_CARD_ID = ?1 and ACTION_DATE between ?2 and ?3 LIMIT 1) then cast (1 as BIT) Else cast (0 as BIT)")
    boolean checkExistsInActionHistory(String contractCardId, LocalDate now, LocalDate minusDays);*/
}
