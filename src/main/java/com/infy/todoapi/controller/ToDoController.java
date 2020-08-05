package com.infy.todoapi.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.todoapi.exceptions.ToDoNotFoundException;
import com.infy.todoapi.model.ToDo;
import com.infy.todoapi.repository.ToDoRepository;
import com.infy.todoapi.exceptions.ErrorDetails;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

/**
 * @author raghava
 * This is REST API to controller class
 * omitted the service layer as this is smaller application.
 * For bigger apis will add service layer.
 * 
 *
 */ 

@RestController
@RequestMapping(value = "/api/v1")
public class ToDoController {

	@Autowired
	private ToDoRepository toDoRepository;

	@GetMapping(value = "/todo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<ToDo>> getAllToDo() {

		Set<ToDo> toDos = new HashSet<>();
		toDoRepository.findAll().forEach(toDos::add);
		return ResponseEntity.ok(toDos);

	}

	@PostMapping(value = "/todo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveToDo(@Valid @RequestBody ToDo toDo, Errors errors) {

		if (errors.hasErrors()) {
			return new ResponseEntity<>(
					new ErrorDetails("validation error", errors.getFieldError().getDefaultMessage()),
					HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(toDoRepository.save(toDo));
	}

	@PutMapping("/todo/{id}")
	public ResponseEntity<ToDo> updateToDo(@PathVariable(value = "id") Long toDoId, @Valid @RequestBody ToDo toDo)throws ToDoNotFoundException {

		Optional<ToDo> toDo1 = toDoRepository.findById(toDoId);
		if (!toDo1.isPresent()) {
			System.out.print("inside eceetpip");
			throw new ToDoNotFoundException();
		}

		toDo.setId(toDoId);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(toDoRepository.save(toDo));

	}

	@DeleteMapping("/todo/{id}")
	public ResponseEntity<String> deleteToDo(@PathVariable(value = "id") long toDoId) {
		Optional<ToDo> toDo1 = toDoRepository.findById(toDoId);
		if (!toDo1.isPresent()) {
			throw new ToDoNotFoundException();
		}
		toDoRepository.deleteById(toDoId);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();

	}

	
}
