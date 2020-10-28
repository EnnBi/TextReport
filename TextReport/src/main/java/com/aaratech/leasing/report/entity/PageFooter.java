package com.aaratech.leasing.report.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="REPORT_TEMPLATE_PAGE_FOOTER")
public class PageFooter {

	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	long id;
	
	String name;
	
	String type;
	
	int width;
	
	String alignment;
	
	int row;
	
	boolean isDashed;
	
	boolean isNewLine;

	boolean isPage;
	
	@ManyToOne
	@JoinColumn(name="Temp_ID")
	Template template;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public boolean isDashed() {
		return isDashed;
	}

	public void setDashed(boolean isDashed) {
		this.isDashed = isDashed;
	}

	public boolean isNewLine() {
		return isNewLine;
	}

	public void setNewLine(boolean isNewLine) {
		this.isNewLine = isNewLine;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public boolean isPage() {
		return isPage;
	}

	public void setPage(boolean isPage) {
		this.isPage = isPage;
	}

}
