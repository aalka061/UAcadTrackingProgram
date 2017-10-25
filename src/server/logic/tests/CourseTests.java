package server.logic.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.logic.model.Course;
import server.logic.model.Student;

public class CourseTests {
	Course courseUnderTest;
//	public Course (String title,boolean enforcePrereqs, int numberOfMidterms, int numberOfAssignments, 
	//		boolean hasAFinal, int capSize, int code )
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTitle() {
		courseUnderTest= new Course("OOD",false,0,1,true,26,110111); 
		String courseTitle = courseUnderTest.title();
		assertNotNull(courseTitle);
	}
	

	@Test
	public void testCode() {
		courseUnderTest= new Course("OOD",false,0,1,true,26,110111); 
		int courseCode = courseUnderTest.getCode();
		assertNotNull(courseCode);
		
	}
  // test if code start with 0 will throw exception 
	@Test(expected = IllegalArgumentException.class)
	public void constructorShouldThrowllegalArgumentException() {
		courseUnderTest= new Course("OOD",false,0,1,true,26,011111); 
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void constructorShouldThrowIllegalArgumentExceptionTest_lessThanAnticpatedDigits() {
		courseUnderTest= new Course("OOD",false,0,1,true,26,11011); 
	}
	
	
	@Test
	public void testStudents() {
		// test 1 - size of student list after adding a student to the course
		courseUnderTest= new Course("OOD",false,0,1,true,26,110111); 
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
		assertEquals(expectedNumber,courseUnderTest.Students().get(0).studentNumber());
		assertEquals(expectedAge,courseUnderTest.Students().get(0).getAge());
		
		// test 3 - check if specific student is added using parameterized constructor 
		courseUnderTest.addStudent(new Student("Ahmed",19, true));
		expectedName="Ahmed";
		expectedNumber=1;
		expectedAge=19;
		assertEquals(expectedName,courseUnderTest.Students().get(2).getName());
		assertEquals(expectedNumber,courseUnderTest.Students().get(2).studentNumber());
		assertEquals(expectedAge,courseUnderTest.Students().get(2).getAge());		
	}
	
	@Test
	public void removeStudent_removingStudentFromCourse(){
		// test 1 - remove a student from the list
		courseUnderTest= new Course("OOD",false,0,1,true,26,110111); 
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
		courseUnderTest= new Course("OOD",false,0,1,true,26,110111); 

		int expectedWeight = 0;
		int actualWeight = courseUnderTest.weightOfAssignment(0);
		assertEquals(expectedWeight,actualWeight);
	}
	
	@Test
	public void weightOfAssignment_OneAssignementNoPorjectNoFinal(){
		//assignemnt number is from 0 to 5
		Course tempCourse = new Course("Object Oriented",true,0,1,false,27,110111);
		tempCourse.setNumberOfAssignments(1);
		tempCourse.setNumberOfMidterms(0);
		tempCourse.setWeightsOfaCourse();
		// test 1 if it the parameter is 0 then the function should return 0
		int expectedWeight = 100;
		int actualWeight = tempCourse.weightOfAssignment(1);
		assertEquals(expectedWeight,actualWeight);
	} 
	
	@Test
	public void weightOfAssignment_ThreeAssignementsNoPorjectNoFinalNoMids(){
		//assignemnt number is from 0 to 5
		Course tempCourse = new Course("Object Oriented",true,0,3,false,27,110111);
		tempCourse.setWeightsOfaCourse();
		// test 1 if it the parameter is 0 then the function should return 0
		int expectedWeight_A1 = 33;
		int actualWeight = tempCourse.weightOfAssignment(1);
		assertEquals(expectedWeight_A1,actualWeight);
	} 
	@Test
	public void weightOfMidterm_NoMidterm_returingZero(){
		//midtermNum number is from 0 to 2
		//if the parameter is 0 then the function should return 0
		courseUnderTest= new Course("OOD",false,0,1,true,26,110111); 

		int expectedWeight = 0;
		int actualWeight = courseUnderTest.weightOfMidterm(0);
		assertEquals(expectedWeight,actualWeight);
	}
	
	@Test
	public void sumCourseElements_someMidtermsSomeAsNoFinal_returningOneHundered(){
		//assignemnt number is from 0 to 5
		Course tempCourse = new Course("Object Oriented",true,2,3,false,27,110111);
		tempCourse.setWeightsOfaCourse();
		
		int expectedWeight =100;
		int actualWeight =  tempCourse.getSumOfCourseElements();
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
		courseUnderTest= new Course("OOD",false,0,1,false,26,110111); 

		int expectedWeight = 0;
		int actualWeight = courseUnderTest.weightOfFinal();
		assertEquals(expectedWeight,actualWeight);
	}
	
	@Test
	public void weightOfFinal_hasFinalNoAssignementNoMid_returningOneHundared(){
		//midtermNum number is from 0 to 2
		//if the parameter is 0 then the function should return 0
		courseUnderTest= new Course("OOD",false,0,1,true,26,110111); 
		courseUnderTest.setNumberOfAssignments(0);
		courseUnderTest.setNumberOfMidterms(0);
		courseUnderTest.setWeightsOfaCourse();
		int expectedWeight = 100;
		int actualWeight = courseUnderTest.weightOfFinal();
		assertEquals(expectedWeight,actualWeight);
	}
	
	
	@Test
	public void isFull_isNumberOfStudentsAtTheCourseReachedTheLimt_returningTrue(){
		//midtermNum number is from 0 to 2
		//if the parameter is 0 then the function should return 0
		courseUnderTest= new Course("OOD",false,0,1,true,26,110111); 
		for (int i=0; i<courseUnderTest.getCapSize();i++){
			courseUnderTest.addStudent(new Student("Ahmed",i+5, false));
		}
		assertTrue(courseUnderTest.isFull());
	}
	
	
	
	
	
	 
	
	
	
	

}
