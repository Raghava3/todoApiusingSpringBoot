package com.infy.todoapi.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.infy.todoapi.service.ToDoServiceInterface;
import com.infy.todoapi.exceptions.ErrorDetails;

import javax.validation.Valid;

/**
 * @author raghava 
 * 		This is REST API controller class 
 *
 */

@RestController
@RequestMapping(value = "/api/v1")
public class ToDoController {

	Logger log = LoggerFactory.getLogger(this.getClass());
	
    	
	@Autowired
	private ToDoServiceInterface todoService;

	@PostMapping(value = "/todo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveToDo(@Valid @RequestBody ToDo toDo, Errors errors) {

		log.debug("entering saveToDo");
		if (errors.hasErrors()) {
			log.info("received request: "+toDo.toString());
			log.error("validation error->"+errors.getFieldError().getDefaultMessage().toString());
			 return new ResponseEntity<>(
					new ErrorDetails("validation error", errors.getFieldError().getDefaultMessage()),
					HttpStatus.BAD_REQUEST);
		}
		log.debug("exiting saveToDo");
		return ResponseEntity.ok(todoService.saveToDo(toDo));
	}

	@GetMapping(value = "/todo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<ToDo>> getAllToDo() {

		log.debug("entering getAllToDo");
		log.debug("exiting getAllToDo");
		return ResponseEntity.ok(todoService.getAllToDO());
		
	}

	@PutMapping("/todo/{id}")
	public ResponseEntity<?> updateToDo(@PathVariable(value = "id") Long toDoId, @Valid @RequestBody ToDo toDo, Errors errors)
			throws ToDoNotFoundException {

		log.debug("entering updateToDo");
		if (errors.hasErrors()) {
			log.info("received request "+toDo.toString());
			log.error("validation error->"+errors.getFieldError().getDefaultMessage().toString());
			return new ResponseEntity<>(
					new ErrorDetails("validation error", errors.getFieldError().getDefaultMessage()),
					HttpStatus.BAD_REQUEST);
		}
		log.debug("exiting updateToDo");
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(todoService.updateToDo(toDoId, toDo));

	}

	@DeleteMapping("/todo/{id}")
	public ResponseEntity<String> deleteToDo(@PathVariable(value = "id") Long toDoId) {

		log.debug("entering deleteToDo");
		todoService.deleteToDo(toDoId);
		log.debug("exiting deleteToDo");
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();

	}

}
