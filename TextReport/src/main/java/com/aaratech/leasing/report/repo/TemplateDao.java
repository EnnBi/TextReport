package com.aaratech.leasing.report.repo;

import java.util.List;

import com.aaratech.leasing.report.entity.Key;
import com.aaratech.leasing.report.entity.TableHeader;
import com.aaratech.leasing.report.entity.Template;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TemplateDao extends CrudRepository<Template, Long> {

	Template findByName(String name);

	@Query("Select h from TableHeader h where h.template.id = :id Order By h.id ASC ")
	List<TableHeader> findHeadersOnTemplateId(@Param("id") long id);

	@Query("Select k from Key k where k.template.id = :id Order By k.id ASC ")
	List<Key> findKeysOnTemplateId(@Param("id") long id);

	@Query("Select Max(m.row) from MainHeader m where m.template.id = :id")
	int findMaxRowOfMainHeaderOnTemplateId(@Param("id") long id);

	@Query("Select Max(m.row) from MainFooter m where m.template.id = :id")
	Integer findMaxRowOfMainFooterOnTemplateId(@Param("id") long id);

	@Query("Select Max(m.row) from PageFooter m where m.template.id = :id")
	Integer findMaxRowOfPageFooterOnTemplateId(@Param("id") long id);

	@Query("Select Max(m.row) from PageFooter m where m.template.id = :id and m.isPage = true")
	Integer findPageTotalRowsOfPageFooterOnTemplateId(@Param("id") long id);

	@Query(value="Select k.value from Key k where k.template.id = :id and k.doSum=true Order By k.id DESC")
	List<String> findNameOfRowkey(@Param("id") long id);
}