package com.infy.todoapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author raghava
 * This is REST API to todo
 * omitted the service layer as this is smaller application.
 * For bigger apis will add service layer and separate request model
 * 
 *
 */

@Entity
@Table(name = "todo")
public class ToDo {
	
	/**
	 * @author raghava
	 * For larger apis we can create separate request model
	 * 
	 */
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Title cant be blank")
	@Size(max = 100 , message = "Maximum length is 100")
	@Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "Must not contain special character")
	private String title;
	private boolean completed;
	
	public ToDo() {
		super();
	}

	public ToDo( String title, boolean completed) {
		super();
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
