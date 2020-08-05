package com.infy.todoapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class ToDo {
	
	private Long id;
	@NotBlank(message = "Title cant be blank")
	@Size(max = 100 , message = "Maximum length is 100")
	@Pattern(regexp = "^[A-Za-z0-9]+$", message = "Must not contain special character")
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
