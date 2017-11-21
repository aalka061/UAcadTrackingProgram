package main.java.server.logic.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.server.logic.model.Course;
import main.java.server.logic.tables.CourseTabel;
import main.java.server.logic.tables.StudentTabel;
import main.java.server.logic.tables.UniversityTable;

public class AcceptanceTests {
	UniversityTable universityTable= null;
	CourseTabel courseTable = null;
	StudentTabel studentTable = null;

	
	@Before
	public void setUp() throws Exception {
		
		universityTable = UniversityTable.getInstance();
		courseTable = CourseTabel.getInstance();
		studentTable = StudentTabel.getInstance();
		
		//universityTable.cleanNewAddedCoursesAndStudents();
	}

	@After
	public void tearDown() throws Exception {
		universityTable.cleanNewAddedCoursesAndStudents();
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
		
		universityTable.createcourse("Machine Learning", 30);
		
		// 4/5 is automatically generated 
		int expectedNumberOfCourses = 4;  
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
		int expectedNumberOfStudents = 4 ; 
		int actualNumberOfStudents = studentTable.size();
		assertEquals(expectedNumberOfStudents, actualNumberOfStudents);
		
	}
	
	@Test
	public void clerkCancelsCourse_success() {
		
		int existedCourseCode = 100001;
		int existedStudentNum = 2;
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
	
	
	@Test
	public void ClerkDeletesStudent_success() {
		
		int existedStudentNum = 1;
		
		int expectedNumberOfStudents=4;
		int actualNumberOfStudent= studentTable.size();		
		assertEquals(expectedNumberOfStudents, actualNumberOfStudent);

		universityTable.deleteStudent(existedStudentNum);
		expectedNumberOfStudents=3;
		actualNumberOfStudent= studentTable.size();	
		assertEquals(expectedNumberOfStudents, actualNumberOfStudent);
	
	}
	
	

	@Test
	public void studentRegistersForCourse_success() {
		
		int existedCourseCode = 100003;
		int existedStudentNum = 2;

		studentTable.lookupStudentById(existedStudentNum);
		studentTable.registerCourse(existedCourseCode);
		Course course = courseTable.findCourse(existedCourseCode);
		
		int numberOfregisteredStudents =course.Students().size();
		int expectedNumberOfStudent= 1;
		assertEquals(expectedNumberOfStudent, numberOfregisteredStudents);
		
	}
	
	
	@Test
	public void studentDropCourse_success() {
		
		int existedCourseCode = 100000;
		int existedStudentNum = 4;

		studentTable.lookupStudentById(existedStudentNum);
		studentTable.registerCourse(existedCourseCode);
		Course course = courseTable.findCourse(existedCourseCode);

		int numberOfregisteredStudents =course.Students().size();
		int expectedNumberOfStudent= 1;
		assertEquals(expectedNumberOfStudent, numberOfregisteredStudents);
		
		studentTable.dropCourse(existedCourseCode);
		course = courseTable.findCourse(existedCourseCode);
		numberOfregisteredStudents = course.Students().size();
		expectedNumberOfStudent = 0;
		assertEquals(expectedNumberOfStudent, numberOfregisteredStudents);

		
	}
	
	@Test
	public void clerkCreatesCourseAndStudentRegisterersThenDrops_success() {
		
		
		
		universityTable.createcourse("software", 30);

		int existedCourseCode = 100003;
		int existedStudentNum = 1;
		Course course = courseTable.findCourse(existedCourseCode);

		studentTable.lookupStudentById(existedStudentNum);
		studentTable.registerCourse(existedCourseCode);
		
		int numberOfregisteredStudents =course.Students().size();
		int expectedNumberOfStudent= 1;
		assertEquals(expectedNumberOfStudent, numberOfregisteredStudents);
		
		studentTable.dropCourse(existedCourseCode);
		numberOfregisteredStudents = course.Students().size();
		expectedNumberOfStudent = 0;
		assertEquals(expectedNumberOfStudent, numberOfregisteredStudents);

		
	}
	
	
	@Test
	public void clerkCreatesTwoCoursesAndaStudentThenStudentRegistersInBoth_success() {
		
		
		
		universityTable.createcourse("software usability II", 30);
		universityTable.createcourse("Calculs I", 100);


		int existedCourseCode_1 = 100004;
		int existedCourseCode_2= 100005;
		
		int existedStudentNum = 1;
		Course course_1 = courseTable.findCourse(existedCourseCode_1);
		Course course_2 = courseTable.findCourse(existedCourseCode_2);


		studentTable.lookupStudentById(existedStudentNum);
		studentTable.registerCourse(existedCourseCode_1);
		studentTable.registerCourse(existedCourseCode_2);
		
		int numberOfregisteredStudents =course_1.Students().size();
		int expectedNumberOfStudent= 1;
		assertEquals(expectedNumberOfStudent, numberOfregisteredStudents);
		
		numberOfregisteredStudents = course_2.Students().size();
		expectedNumberOfStudent = 1;
		assertEquals(expectedNumberOfStudent, numberOfregisteredStudents);

		
	}
	
	@Test
	public void fullTimeStudentRegistersInMoreThan4courses_fail() {
				
		universityTable.createcourse("Calculs I", 100);


		int course1=100000;
		int course2=100001;
		int course3=100002;
		int course4=100003;
		int course5=100004;

		
		int existedStudentNum = 1;


		studentTable.lookupStudentById(existedStudentNum);
		studentTable.registerCourse(course1);
		studentTable.registerCourse(course2);
		studentTable.lookupStudentById(existedStudentNum);

		studentTable.registerCourse(course3);
		studentTable.lookupStudentById(existedStudentNum);

		studentTable.registerCourse(course4);
		studentTable.lookupStudentById(existedStudentNum);

		Boolean actualBoolean  = (Boolean) studentTable.registerCourse(course5);
		assertFalse("this will not pass", actualBoolean);
		
	}
	

	@Test
	public void partTimeStudentRegistersInMoreThan2courses_fail() {
			


		int course1=100000;
		int course2=100001;
		int course3=100002;
		
		int existedStudentNum = 3;


		studentTable.lookupStudentById(existedStudentNum);
		studentTable.registerCourse(course1);
		studentTable.registerCourse(course2);
		Boolean actualBoolean  = (Boolean)studentTable.registerCourse(course3);
	
		assertFalse("this will not pass", actualBoolean);
		
	}
	

	
	
	
	

}
