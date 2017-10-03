import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.carleton.mainclasses.ApplicationUniversityMediator;
import com.carleton.mainclasses.Course;
import com.carleton.mainclasses.Student;
import com.carleton.mainclasses.UniversityMediator;

public class UniversityClassTest {
	
	
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
	
	@Test
	public void createStudent_returingStudnetThatIsCreated() {
		int age = 20;
		boolean isFullTime=true;
		String name = "Michelle";
		universityUnderTest.creatStudent(name,age,isFullTime);
		int actualNumberOfStudents= universityUnderTest.students().size();
		int expectedNumberOfStudents=1;
		assertEquals(expectedNumberOfStudents,actualNumberOfStudents);
	}
	
	
	@Test
	public void registerSutdentForCourse_givenaCourseAndStudent() {
		
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
	
	@After
	public void tearDown(){
		universityUnderTest.courses().clear();
		
	}
	
	
	
	
	
	
	

}
