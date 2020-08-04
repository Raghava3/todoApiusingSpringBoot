package com.infy.todoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.todoapi.model.ToDo;

public interface ToDoRepository extends JpaRepository<ToDo, Long>  {

}
	