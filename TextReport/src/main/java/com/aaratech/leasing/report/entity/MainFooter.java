package com.aaratech.leasing.report.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="REPORT_TEMPLATE_MAIN_FOOTER")
public class MainFooter {

	enum Type{LABEL,VALUE,DATE,PAGE}
	enum Alignment{RIGHT,LEFT,CENTER}

	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	long id;
	
	String name;
	
	String type;
	
	int width;
	
	String alignment;
	
	int row;
	
	boolean isDashed;
	
	boolean isNewLine;
	
	String format;
	
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
		if(type.equals(Type.LABEL.toString()))
			width=this.name.length();
		
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

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}


	
	
	
}
