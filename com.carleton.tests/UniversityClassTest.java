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

}