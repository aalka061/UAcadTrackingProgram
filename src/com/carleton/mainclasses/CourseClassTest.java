package com.carleton.mainclasses;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourseClassTest {
	Course course = new Course ("software developement"); 

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTitle() {
		String courseTitle = course.title();
		assertNotNull(courseTitle);
	
	}

}
