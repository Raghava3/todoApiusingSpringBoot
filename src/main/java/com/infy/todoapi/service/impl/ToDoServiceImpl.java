package com.infy.todoapi.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.todoapi.exceptions.ToDoNotFoundException;
import com.infy.todoapi.model.ToDo;
import com.infy.todoapi.repository.ToDoRepository;
import com.infy.todoapi.service.ToDoServiceInterface;

@Service
public class ToDoServiceImpl implements ToDoServiceInterface {

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ToDoRepository toDoRepository;
	
	@Override
	public Set<ToDo> getAllToDO() {
		log.debug("entering getAllToDOservice");
		// TODO Auto-generated method stub
		Set<ToDo> toDos = new HashSet<>();
		toDoRepository.findAll().forEach(toDos::add);
		log.debug("exiting getAllToDOservice");
		return toDos;
		
	}

	@Override
	public ToDo saveToDo(ToDo toDo) {
		log.debug("entering saveToDoservice");
		log.debug("exiting saveToDoservice");
		return  toDoRepository.save(toDo);
	}

	@Override
	public ToDo updateToDo(Long toDoId, ToDo toDo) {
		// TODO Auto-generated method stub
		log.debug("entering updateToDoservice");
		Optional<ToDo> toDo1 = toDoRepository.findById(toDoId);
		if (!toDo1.isPresent()) {
			log.info("todo is not presnt or deleted");
			log.error("caught ToDoNotFoundException");
			throw new ToDoNotFoundException();
		}
		toDo.setId(toDoId);
		log.debug("exiting updateToDoservice");
		return toDoRepository.save(toDo);
	}

	@Override
	public boolean deleteToDo(Long toDoId) {
		// TODO Auto-generated method stub
		log.debug("entering deleteToDoservice");
		Optional<ToDo> toDo1 = toDoRepository.findById(toDoId);
		if (!toDo1.isPresent()) {
			log.info("todo is not presnt or deleted");
			log.error("caught ToDoNotFoundException");
			throw new ToDoNotFoundException();
		}
		toDoRepository.deleteById(toDoId);
		log.debug("exiting deleteToDoservice");
		return true;
	}

}
