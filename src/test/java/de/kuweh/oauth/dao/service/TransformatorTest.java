package de.kuweh.oauth.dao.service;

import static org.junit.Assert.*;

import org.junit.Test;

public class TransformatorTest {

	@Test
	public void testSimpleObject() {
		Object input = new Object();
		
		Transformator<Object, Object> transformator = new Transformator<>();
		transformator.setInputInstance(input);
		
		assertEquals(input.getClass(), transformator.getInputInstance().getClass());
	}
	
	@Test
	public void testPostObject() {
		
		de.kuweh.oauth.model.view.User view = new de.kuweh.oauth.model.view.User();
		view.setEmail("test@test.de");
		view.setPassword("123456");
		
		de.kuweh.oauth.model.dao.User dao = new de.kuweh.oauth.model.dao.User();
		
		Transformator<de.kuweh.oauth.model.view.User, de.kuweh.oauth.model.dao.User> transformator = new Transformator<>();
		transformator.setInputInstance(view);
		transformator.setOutputInstance(dao);
		transformator.fillOutputInstance();
		
		assertEquals(view.getClass(), transformator.getInputInstance().getClass());
		assertEquals(dao.getClass(), transformator.getOutputInstance().getClass());
		assertEquals("test@test.de", dao.getEmail());
		assertEquals("123456", dao.getPassword());
	}

}
