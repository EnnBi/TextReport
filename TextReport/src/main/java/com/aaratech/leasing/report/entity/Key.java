package com.aaratech.leasing.report.entity;

import com.aaratech.leasing.report.entity.Template;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="REPORT_TEMPLATE_ROW_KEY")
public class Key {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long id; 
	
	String value;
		
	int width;
	
	String alignment;
	
	String format;
	
	boolean doSum;
	
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

	public String getAlignment() {
		return alignment;
	}

	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public boolean isDoSum() {
		return doSum;
	}

	public void setDoSum(boolean doSum) {
		this.doSum = doSum;
	}	
}
