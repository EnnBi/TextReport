package com.aaratech.leasing.report.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="REPORT_TEMPLATE_TABLE_HEADER")
public class TableHeader {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long id;
	
	String value;
		
	int width;
	
	@ManyToOne
	@JoinColumn(name="Temp_ID")
	Template template;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	
}