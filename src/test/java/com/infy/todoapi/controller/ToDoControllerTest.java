package com.infy.todoapi.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.junit.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.todoapi.TodoapiApplication;
import com.infy.todoapi.model.ToDo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TodoapiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ToDoControllerTest {

	ObjectMapper om = new ObjectMapper();

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port + "/api/v1";
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void testGetAllToDo() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/todo", HttpMethod.GET, entity,
				String.class);
		Assert.assertEquals(200, response.getStatusCodeValue());
	}

	@Test
	public void testSaveToDo() throws JsonProcessingException {

		ToDo do1 = new ToDo();
		do1.setTitle("testing");
		do1.setcompleted(false);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(om.writeValueAsString(do1), headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/todo", HttpMethod.POST, entity,
				String.class);
		Assert.assertEquals(200, response.getStatusCodeValue());
	}

	@Test
	public void testUpdateToDo() throws JsonProcessingException {

		ToDo do1 = new ToDo();
		do1.setTitle("testing");
		do1.setcompleted(false);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(om.writeValueAsString(do1), headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/todo/1", HttpMethod.PUT, entity,
				String.class);
		Assert.assertEquals(202, response.getStatusCodeValue());
	}

	@Test
	public void testDeleteToDo() throws JsonProcessingException {

		ToDo do1 = new ToDo();
		do1.setTitle("testing");
		do1.setcompleted(false);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(om.writeValueAsString(do1), headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/todo/1", HttpMethod.DELETE, entity,
				String.class);
		Assert.assertEquals(202, response.getStatusCodeValue());
	}

}
