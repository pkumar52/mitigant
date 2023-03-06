package com.itemservice.java.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Item
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 50)
	private String description;	
	
	@Column(nullable = false, length = 10)
	private String status;
	
	@JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = Shape.STRING)
	//@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	@Column(nullable = false, length = 20)
	private String creationdate;
	
	@JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = Shape.STRING)
	@Column(nullable = false, length = 20)
	private String duedate;
	
	@JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = Shape.STRING)
	@Column(nullable = false, length = 20)	
	private String donedate;

	public Item(int id, String description, String status, String creationdate, String duedate, String donedate) {
		super();
		this.id = id;
		this.description = description;
		this.status = status;
		this.creationdate = creationdate;
		this.duedate = duedate;
		this.donedate = donedate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}

	public String getDuedate() {
		return duedate;
	}

	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}

	public String getDonedate() {
		return donedate;
	}

	public void setDonedate(String donedate) {
		this.donedate = donedate;
	}
	
};

