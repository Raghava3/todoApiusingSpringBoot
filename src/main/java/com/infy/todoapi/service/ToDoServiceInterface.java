package com.infy.todoapi.service;

import java.util.Set;

import com.infy.todoapi.model.ToDo;

public interface ToDoServiceInterface {

	public Set<ToDo> getAllToDO();
	public ToDo saveToDo(ToDo toDo);
	public ToDo updateToDo(Long toDoId, ToDo toDo);
	public boolean deleteToDo(Long toDoId);
}
