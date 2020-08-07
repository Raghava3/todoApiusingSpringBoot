package com.infy.todoapi.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.todoapi.exceptions.ToDoNotFoundException;
import com.infy.todoapi.model.ToDo;
import com.infy.todoapi.repository.ToDoRepository;

@Service
public class ToDoServiceImplementation implements ToDoServiceInterface {

	@Autowired
	private ToDoRepository toDoRepository;
	
	@Override
	public Set<ToDo> getAllToDO() {
		// TODO Auto-generated method stub
		Set<ToDo> toDos = new HashSet<>();
		toDoRepository.findAll().forEach(toDos::add);
		return toDos;
		
	}

	@Override
	public ToDo saveToDo(ToDo toDo) {
		
		return  toDoRepository.save(toDo);
	}

	@Override
	public ToDo updateToDo(Long toDoId, ToDo toDo) {
		// TODO Auto-generated method stub
		Optional<ToDo> toDo1 = toDoRepository.findById(toDoId);
		if (!toDo1.isPresent()) {
			throw new ToDoNotFoundException();
		}
		toDo.setId(toDoId);
		return toDoRepository.save(toDo);
	}

	@Override
	public boolean deleteToDo(Long toDoId) {
		// TODO Auto-generated method stub
		Optional<ToDo> toDo1 = toDoRepository.findById(toDoId);
		if (!toDo1.isPresent()) {
			throw new ToDoNotFoundException();
		}
		toDoRepository.deleteById(toDoId);
		return true;
	}

}
