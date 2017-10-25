package server.logic.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.logic.model.ApplicationUniversityMediator;
import server.logic.model.Course;
import server.logic.model.Student;


public class UniversityTests {
	
	
	ApplicationUniversityMediator universityUnderTest;


	@Before
	public void setUp() throws Exception {
		universityUnderTest = new ApplicationUniversityMediator(10);
		
	}

	@Test
	public void numberOfUniversityCourses_returingTen() {
		int numberOfCourses=universityUnderTest.getNumberOfUniversityCourses();
		int expectedNumberOfCourses=10;
		assertEquals(expectedNumberOfCourses,numberOfCourses);

	}
	
	@Test(expected = IllegalArgumentException.class)
	public void numberOfUniversityCourses_returningExeptionBecauseOfOffLimits() {
		ApplicationUniversityMediator tempUni=new ApplicationUniversityMediator(27);
	}
	
	@Test
	public void maxCoursesFTStudents_returingFour() {
		int numberOfFTStudents=universityUnderTest.getNumberOfMaxFTStudents();
		int expectedNumberOfCourses=4;
		assertEquals(expectedNumberOfCourses,numberOfFTStudents);

	}
	
	@Test
	public void maxCoursesPTStudents_returingTwo() {
		int numberOfFTStudents=universityUnderTest.getNumberOfMaxPTStudents();
		int expectedNumberOfCourses=2;
		assertEquals(expectedNumberOfCourses,numberOfFTStudents);

	}
	
	@Test
	public void passRate_returingSeventey() {
		int numberOfFTStudents=universityUnderTest.getPassRate();
		int expectedPassRate=70;
		assertEquals(expectedPassRate,numberOfFTStudents);

	}
	
	
	// testing create a Course 
	
	@Test
	public void createCourse_returingCourseThatIsCreated() {
		
		int cap = 60;
		universityUnderTest.creatCourse("object oriented Development",cap);
		int actualNumberOfCourses= universityUnderTest.courses().size();
		int expectedNumberOfCourses=1;
		assertEquals(actualNumberOfCourses,expectedNumberOfCourses);
	}
	//creating two course and ensure that their code is unique 
	@Test
	public void createCourse_creatingTwoCourses_returningDifferentCodes() {
		
		int cap = 60;
		
		Course tempCourse_1 = universityUnderTest.creatCourse("object oriented Development",cap);
		Course tempCourse_2= universityUnderTest.creatCourse("networking", cap);
		assertTrue(tempCourse_1.getCode()!= tempCourse_2.getCode());
		assertEquals(100000,tempCourse_1.getCode());
		assertEquals(100001,tempCourse_2.getCode());
		
	
	}
	@Test
	public void createCourse_returingCourseThatIsCreated1() {
		
		int cap = 60;
		universityUnderTest.creatCourse("object oriented Development",cap);
		int actualNumberOfCourses= universityUnderTest.courses().size();
		int expectedNumberOfCourses=1;
		assertEquals(actualNumberOfCourses,expectedNumberOfCourses);
	}
	//creating two course and ensure that their code is unique 
	@Test
	public void createCourse_OneCourseCreated_returingSumOfCourseComponents() {
		
		int cap = 60;
		
		Course tempCourse = universityUnderTest.creatCourse("object oriented Development",cap);
		assertEquals(100,tempCourse.getSumOfCourseElements());
		
	
	}
	
	
	@Test
	public void createStudent_returingStudnetThatIsCreated() {
		int age = 20;
		boolean isFullTime=true;
		String name = "Michelle";
		
		Student newStudent= universityUnderTest.creatStudent(name,age,isFullTime);
		
		int actualNumberOfStudents= universityUnderTest.students().size();
		int expectedNumberOfStudents=1;
		assertEquals(expectedNumberOfStudents,actualNumberOfStudents);
		
		//ensure that isCreated Method is set to true now sinc
		assertTrue(newStudent.isCreated());
	}
	
	
	@Test
	public void registerSutdentForCourse_givenaCourseAndStudent_withinCapSizeLimt() {
		
		//assumption : the student is created through the procedure createCourse 
		//             the course is crreated throguh the procedure of createStudent 
		// new course 
		Course newCourse = universityUnderTest.creatCourse("OOD",50);
		// new student
		Student newStudent= universityUnderTest.creatStudent("Ahmed",18,false);
		//register the new student to the new course
		universityUnderTest.registerStudentForCourse(newStudent,newCourse);
		// expectations : 1)the course will be shown in the sutdent's end in currentCourses List
		//                2)a new student will be added to the studentList of the new course
		// -------------------Case 1--------------------
		String expectedTitle = newStudent.getCurrentCourses().get(0).title();
		//actual : the tile of the new course 
		String actualTitle = newCourse.title();
		// assert : is actual.equals expected
		assertEquals(expectedTitle,actualTitle);
		
		// -------------------Case 2----------------------
		// expected : student name of new course 
		String expectedStudentName= newStudent.getName();
		//actual : the tile of the new course 
		String actualStudentName = newCourse.Students().get(0).getName();
		
		assertEquals(expectedStudentName,actualStudentName);

	}
	
	@Test(expected = IllegalStateException.class)
	public void registerSutdentForCourse_givenaCourseAndStudent_capSizeIsReached() {
		
		//assumption : the student is created through the procedure createCourse 
		//             the course is created throguh the procedure of createStudent 
		// new course : no students registered yet   : 0/26
		Course newCourse = universityUnderTest.creatCourse("OOD",26);
		// new student
		Student newStudent= universityUnderTest.creatStudent("Ahmed",18,false);
		//register new students to the course till the limit is reched
		for (int i=0; i<newCourse.getCapSize();i++){
			Student tempStudent= universityUnderTest.creatStudent("Ahmed",i+1,false);
			universityUnderTest.registerStudentForCourse(tempStudent,newCourse);
		}
		// the number of students in the course should be now 26
	//	assertEquals(26,newCourse.Students().size());
		// new create a student
		Student newStudent_2= universityUnderTest.creatStudent("Mer",20,false);
		// this student should not be accepted since the capsize has been reached
		universityUnderTest.registerStudentForCourse(newStudent,newCourse);
		
	}
	// ensure that no two identical students can register for the same course
	@Test(expected = IllegalArgumentException.class)
	public void registerSutdentForCourse_givenCourseAndStudent_returningException() {
		
		//assumption : the student is created through the procedure createCourse 
				//             the course is created throguh the procedure of createStudent 
				// new course : no students registered yet   : 0/26
				Course newCourse = universityUnderTest.creatCourse("OOD",26);
				// new student
				Student newStudent= universityUnderTest.creatStudent("Ahmed",18,false);
				// this is not allowed!
				for (int i=0; i<newCourse.getCapSize();i++){
					universityUnderTest.registerStudentForCourse(newStudent,newCourse);
				}
	}
	
	@Test
	public void cancelCourse_derigsterStudentsForThatCourse() {
		
		// create new course 
		Course newCourse = universityUnderTest.creatCourse("OOD",50);
		Course newCourse_2 = universityUnderTest.creatCourse("AdHocNetworking",26);
		// create 2 students 
		Student newStudent_1= universityUnderTest.creatStudent("Ahmed",18,false);
		Student newStudent_2= universityUnderTest.creatStudent("Abraham",20,true);
		
		// register both students to the new course
		universityUnderTest.registerStudentForCourse(newStudent_1,newCourse);
		universityUnderTest.registerStudentForCourse(newStudent_2,newCourse);
		universityUnderTest.registerStudentForCourse(newStudent_2,newCourse_2);

		
		// list of students in the newCourse 
		List<Student> studentsOfnewCourse = newCourse.Students();
		// ensure that newCourse is added to the courses list 
		assertEquals(2, studentsOfnewCourse.size());
		// cancel the course 
		universityUnderTest.cancelCourse(newCourse);
		
		// all students should be deregistered
		assertTrue(studentsOfnewCourse.isEmpty());
		
		// the course should be deleted from the current courses of newStudent_1 
		// and newStudent_2
		assertTrue(newStudent_1.getCurrentCourses().isEmpty());
		assertEquals(newCourse_2.getCapSize(), newStudent_2.getCurrentCourses().get(0).getCapSize());
	}
	
	
	@Test
	public void destroyCourse_deleteCourseFromUniveristyCourses_void() {
		
		// create new courses 
		Course newCourse = universityUnderTest.creatCourse("OOD",50);
		Course newCourse_2 = universityUnderTest.creatCourse("AdHocNetworking",26);
		
		// destory course 
		universityUnderTest.destroy(newCourse);
		// course should be eliminated from the univeristyCourses
		assertTrue(universityUnderTest.courses().size()==1);
		
		String expectedTitleOfRemainingCourse = newCourse_2.title();
		String actualTitleOfRemainingCourse = universityUnderTest.courses().get(0).title();
		assertEquals(expectedTitleOfRemainingCourse, actualTitleOfRemainingCourse);
		
	
	}
	
	
	
	@After
	public void tearDown(){
		universityUnderTest.courses().clear();	
		universityUnderTest.students().clear();

		
	}
	
	
	
	
	
	
	

}
