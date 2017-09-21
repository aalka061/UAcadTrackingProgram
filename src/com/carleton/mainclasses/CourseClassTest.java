package com.carleton.mainclasses;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourseClassTest {
	Course courseUnderTest;

	@Before
	public void setUp() throws Exception {
		
		courseUnderTest= new Course ("software developement",100000); 
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTitle() {
		String courseTitle = courseUnderTest.title();
		assertNotNull(courseTitle);
	}
	

	@Test
	public void testCode() {
		int courseCode = courseUnderTest.getCode();
		assertNotNull(courseCode);
		
	}
  // test if code start with 0 will throw exception 
	@Test(expected = IllegalArgumentException.class)
	public void constructorShouldThrowllegalArgumentException() {
	    Course newCourse = new Course("SD",011111);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void constructorShouldThrowIllegalArgumentExceptionTest2() {
	    Course newCourse = new Course("SD",1111);
	}
	
	

}
