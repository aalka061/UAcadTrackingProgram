package com.carleton.mainclasses;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
	
	
	@Test
	public void testStudents() {
		// test 1 - size of student list after adding a student to the course
		courseUnderTest.addStudent(new Student());
		List<Student>studentList= courseUnderTest.Students();
		List <Student> expcectedList = new ArrayList<Student>();
		expcectedList.add(new Student());
		assertEquals(expcectedList.size(),studentList.size());
		
		// test 2 - check if specific student is added using general constructor 
		courseUnderTest.addStudent(new Student());
		String expectedName="UnKnown";
		int expectedNumber=0;
		int expectedAge=17;
		assertEquals(expectedName,courseUnderTest.Students().get(0).getName());
		assertEquals(expectedNumber,courseUnderTest.Students().get(0).getNumber());
		assertEquals(expectedAge,courseUnderTest.Students().get(0).getAge());
		
		// test 3 - check if specific student is added using parameterized constructor 
		courseUnderTest.addStudent(new Student("Ahmed",19, true));
		expectedName="Ahmed";
		expectedNumber=1;
		expectedAge=19;
		assertEquals(expectedName,courseUnderTest.Students().get(2).getName());
		assertEquals(expectedNumber,courseUnderTest.Students().get(2).getNumber());
		assertEquals(expectedAge,courseUnderTest.Students().get(2).getAge());		
	}
	
	@Test
	public void removeStudent_removingStudentFromCourse(){
		// test 1 - remove a student from the list
		courseUnderTest.addStudent(new Student());
		Student newStudent = new Student ();
		courseUnderTest.addStudent(newStudent);
		List<Student>studentList= courseUnderTest.Students();
		List <Student> expcectedList = new ArrayList<Student>();
		expcectedList.add(new Student());
		boolean isRemovedActual= courseUnderTest.removeStudent(newStudent);
		assertEquals(expcectedList.size(),studentList.size());
		// test if student is removed
		assertTrue(isRemovedActual);
		
		
	}
	 
	@Test
	public void weightOfAssignment_NoAssignement_returingZero(){
		//assignemnt number is from 0 to 5
		// test 1 if it the parameter is 0 then the function should return 0
		int expectedWeight = 0;
		int actualWeight = courseUnderTest.weightOfAssignment(0);
		assertEquals(expectedWeight,actualWeight);
	}
	
	@Test
	public void weightOfAssignment_OneAssignementNoPorjectNoFinal(){
		//assignemnt number is from 0 to 5
		Course tempCourse = new Course("Object Oriented",true,0,1,false,27);
		// test 1 if it the parameter is 0 then the function should return 0
		int expectedWeight = 100;
		int actualWeight = tempCourse.weightOfAssignment(1);
		assertEquals(expectedWeight,actualWeight);
	} 
	@Test
	public void weightOfMidterm_NoMidterm_returingZero(){
		//midtermNum number is from 0 to 2
		//if the parameter is 0 then the function should return 0
		int expectedWeight = 0;
		int actualWeight = courseUnderTest.WeightOfMidterm(0);
		assertEquals(expectedWeight,actualWeight);
	}
	
	/*
	@Test
	public void weightOfMidterm_OneMidterm_returingZero(){
		//midtermNum number is from 0 to 2
		//if the parameter is 0 then the function should return 0
		int expectedWeight = 0;
		int actualWeight = courseUnderTest.WeightOfMidterm(0);
		assertEquals(expectedWeight,actualWeight);
	}*/
	/*
	@Test
	public void weightOfMidterm_twoMidterm_returingZero(){
		//midtermNum number is from 0 to 2
		//if the parameter is 0 then the function should return 0
		int expectedWeight = 0;
		int actualWeight = courseUnderTest.WeightOfMidterm(0);
		assertEquals(expectedWeight,actualWeight);
	}*/
	
	@Test
	public void weightOfFinal_hasFinalIsFalse_returingZero(){
		//midtermNum number is from 0 to 2
		//if the parameter is 0 then the function should return 0
		int expectedWeight = 0;
		int actualWeight = courseUnderTest.WeightOfFinal();
		assertEquals(expectedWeight,actualWeight);
	}
	
	@Test
	public void weightOfFinal_hasFinalIsTrue_returingFiftyForNow(){
		//midtermNum number is from 0 to 2
		//if the parameter is 0 then the function should return 0
		int expectedWeight = 50;
		courseUnderTest.setHasAFinal(true);
		int actualWeight = courseUnderTest.WeightOfFinal();
		assertEquals(expectedWeight,actualWeight);
	}
	
	@Test
	public void isFull_isNumberOfStudentsAtTheCourseReachedTheLimt_returningTrue(){
		//midtermNum number is from 0 to 2
		//if the parameter is 0 then the function should return 0
		Course newCourseUnderTest = new Course("OOD",false,0,1,true,27);
		for (int i=0; i<newCourseUnderTest.getCapSize();i++){
			newCourseUnderTest.addStudent(new Student("Ahmed",i+5, false));
		}
		assertTrue(newCourseUnderTest.isFull());
	}
	
	
	
	
	
	 
	
	
	
	

}
