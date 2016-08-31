package com.organization.project.common;

import java.util.Date;

import javax.persistence.Column;

import org.apache.log4j.Logger;

public class CommonDomain {
	final static Logger LOGGER = Logger.getLogger(CommonDomain.class);
	
	@Column
	private String createdBy;
	
	@Column
	private String updatedBy;
	
	@Column
	private Date createdDate;
	
	@Column
	private Date updatedDate;
	
	public CommonDomain() {
	}

	public CommonDomain(String createdBy, String updatedBy, Date createdDate, Date updatedDate) {
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
}
