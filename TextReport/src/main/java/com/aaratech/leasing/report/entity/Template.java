package com.aaratech.leasing.report.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="REPORT_TEMPLATE")
public class Template {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long id;
		
	String name;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="template")
	List<MainHeader> mainHeader;
	
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="template")
	List<TableHeader> tableHeaders;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="template")
	List<Key> tableKeys;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="template")
	List<PageFooter> pageFooter;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="template")
	List<MainFooter> mainFooter;
	
	int pageLines;
	
	int maxWidth;
	
	String dateFormat;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TableHeader> getTableHeaders() {
		return tableHeaders;
	}

	public void setTableHeaders(List<TableHeader> tableHeaders) {
		this.tableHeaders = tableHeaders;
	}

	public List<Key> getTableKeys() {
		return tableKeys;
	}

	public void setTableKeys(List<Key> tableKeys) {
		this.tableKeys = tableKeys;
	}

	public int getPageLines() {
		return pageLines;
	}

	public void setPageLines(int pageLines) {
		this.pageLines = pageLines;
	}

	public int getMaxWidth() {
		return maxWidth;
	}

	public void setMaxWidth(int maxWidth) {
		this.maxWidth = maxWidth;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public List<MainHeader> getMainHeader() {
		return mainHeader;
	}

	public void setMainHeader(List<MainHeader> mainHeader) {
		this.mainHeader = mainHeader;
	}

	public List<MainFooter> getMainFooter() {
		return mainFooter;
	}

	public void setMainFooter(List<MainFooter> mainFooter) {
		this.mainFooter = mainFooter;
	}

	public List<PageFooter> getPageFooter() {
		return pageFooter;
	}

	public void setPageFooter(List<PageFooter> pageFooter) {
		this.pageFooter = pageFooter;
	}

	
	
}