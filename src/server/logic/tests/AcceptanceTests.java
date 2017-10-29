package server.logic.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.logic.model.Course;
import server.logic.tables.CourseTabel;
import server.logic.tables.StudentTabel;
import server.logic.tables.UniversityTable;

public class AcceptanceTests {
	UniversityTable universityTable= null;
	CourseTabel courseTable = null;
	StudentTabel studentTable = null;

	
	@Before
	public void setUp() throws Exception {
		
		universityTable = UniversityTable.getInstance();
		courseTable = CourseTabel.getInstance();
		studentTable = StudentTabel.getInstance();
		
	//	universityTable.clearUniversityTable();
	}

	@After
	public void tearDown() throws Exception {
		//universityTable.clearUniversityTable();
	}

	@Test
	public void clerkCreatesCourseTest_success() {
		
		universityTable.createcourse("MobileCommerce", 30);
		// 4/5 is automatically generated +1 for the new one
		int expectedNumberOfCourses = 5;  
		int actualNumberOfCourses = courseTable.size();
		assertEquals(expectedNumberOfCourses, actualNumberOfCourses);
		
	}
	
	@Test
	public void clerkCreatesCourseTest_fail_sameCourseName() {
		UniversityTable TempTabel= UniversityTable.getInstance();
		TempTabel.createcourse("Machine Learning", 30);
		
		// 4/5 is automatically generated +1 for the new one
		int expectedNumberOfCourses = 5;  
		int actualNumberOfCourses = courseTable.size();
		assertEquals(expectedNumberOfCourses, actualNumberOfCourses);
		
	}
	
	@Test
	public void clerkCreatesStudent_success() {
		
		universityTable.createstudent("Simon", 17, false);
		// 4/5 is automatically generated +1 for the new one
		int expectedNumberOfStudents = 5;  
		int actualNumberOfStudents = studentTable.size();
		assertEquals(expectedNumberOfStudents, actualNumberOfStudents);
		
	}
	
	@Test
	public void clerkCreatesStudent_fail_sameStudentName() {
		
		universityTable.createstudent("Emley", 17, false);
		// 4/5 is automatically generated +1 for the new one
		int expectedNumberOfStudents = 5;  
		int actualNumberOfStudents = studentTable.size();
		assertEquals(expectedNumberOfStudents, actualNumberOfStudents);
		
	}
	
	@Test
	public void clerkCancelsCOourse_success() {
		
		int existedCourseCode = 100001;
		int existedStudentNum = 1;
		// register a student to a course 
		universityTable.registerStudentForCourse(existedStudentNum, existedCourseCode);
		// find that course 
		Course course = courseTable.findCourse(existedCourseCode);
		int numberOfStudents =course.Students().size();
		int expectedNumberOfStudent= 1;
		assertEquals(expectedNumberOfStudent, numberOfStudents);
		// cancel the course which means the course should not have any more students
		universityTable.cancelCourse(existedCourseCode);
		numberOfStudents =course.Students().size();
		expectedNumberOfStudent= 0;
		
		assertEquals(expectedNumberOfStudent, numberOfStudents);
		
	}

}
