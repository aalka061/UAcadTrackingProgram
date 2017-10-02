import static org.junit.Assert.*;

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
	
	
	

}
