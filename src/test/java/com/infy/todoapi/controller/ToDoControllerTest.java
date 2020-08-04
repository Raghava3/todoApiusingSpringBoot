package com.infy.todoapi.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.todoapi.exceptions.ToDoNotFoundException;
import com.infy.todoapi.model.ToDo;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ToDoControllerTest {

	protected MockMvc mockMvc;
	   
	@Autowired
	WebApplicationContext webApplicationContext;
	
	ObjectMapper om = new ObjectMapper();

	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testAllToDO() throws Exception {
	   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/todo")
	      .accept(MediaType.APPLICATION_JSON_VALUE))
		  .andReturn();
	   assertEquals(200, mvcResult.getResponse().getStatus());
	}

	@Test
	public void testSaveToDO_SuccessfullySaved() throws Exception {

		ToDo do1 = new ToDo();
		do1.setcompleted(false);
		do1.setTitle("testing");

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/api/v1/todo")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(om.writeValueAsString(do1)))
				.andReturn();
		assertEquals(200, result.getResponse().getStatus());

	}
	
	
	
	@Test
	public void testupdateToDo_Successfully() throws Exception {

		ToDo do1 = new ToDo();
		do1.setcompleted(true);
		do1.setTitle("testing update");
		
	   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/todo/2")
	      .contentType(MediaType.APPLICATION_JSON_VALUE)
	      .content(om.writeValueAsString(do1)))
		  .andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(202, status);
	}

	@Test(expected = ToDoNotFoundException.class)
	public void testupdateToDo_ToDoNotFoundException() throws Exception {

		ToDo do1 = new ToDo();
		do1.setcompleted(true);
		do1.setTitle("testing update");
		
	   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/todo/10")
	      .contentType(MediaType.APPLICATION_JSON_VALUE)
	      .content(om.writeValueAsString(do1)))
		  .andReturn();
	   
	}


	@Test
	public void testdeleteToDo_Successfully() throws Exception {

		ToDo do1 = new ToDo();
		do1.setcompleted(true);
		do1.setTitle("testing update");
		
	   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/todo/2")
	      .contentType(MediaType.APPLICATION_JSON_VALUE)
	      .content(om.writeValueAsString(do1)))
		  .andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(202, status);
	}

	@Test(expected = ToDoNotFoundException.class)
	public void testdeleteToDo_ToDoNotFoundException() throws Exception {

		ToDo do1 = new ToDo();
		do1.setcompleted(true);
		do1.setTitle("testing update");
		
	   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/todo/10")
	      .contentType(MediaType.APPLICATION_JSON_VALUE)
	      .content(om.writeValueAsString(do1)))
		  .andReturn();
	   
	}

	
}
