package com.infy.todoapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class ToDo {
	
	private Long id;
	@NotBlank
	private String title;
	private boolean completed;
	
	public ToDo() {
		super();
	}

	public ToDo(Long id, String title, boolean completed) {
		super();
		this.id = id;
		this.title = title;
		this.completed = completed;
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean iscompleted() {
		return completed;
	}

	public void setcompleted(boolean completed) {
		this.completed = completed;
	}

	
	

}
