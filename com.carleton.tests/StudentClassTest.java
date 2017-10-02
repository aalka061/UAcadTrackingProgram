import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.carleton.mainclasses.Course;
import com.carleton.mainclasses.Student;

public class StudentClassTest {

	
	Student studentUnderTestDefault;	
	Student studentUnderTestParametrized;


	@Before
	public void setUp() throws Exception {
		studentUnderTestParametrized = new Student("Karev",18,true);
		
	}

	@Test
	public void studentNumber_defConstructor_returingZero(){
		
		// defalut construcotr 
		studentUnderTestDefault= new Student();
		int sNumberActual = studentUnderTestDefault.studentNumber();
		int sNumberExpected= 0;
		assertEquals(sNumberExpected,sNumberActual);
		
	}
	

	@Test
	public void studentNumber_paraConst_returingOne(){
		// Parameterized constructor 
		int sNumberActual = studentUnderTestParametrized.studentNumber();
		int sNumberExpected= 2;
		assertEquals(sNumberExpected,sNumberActual);

	}
	
	@Test
	public void isFullTime_isStudentFullTime_returingTrue(){
		// Parameterized constructor 
		boolean isFullTime = studentUnderTestParametrized.isFullTime();
		assertTrue(isFullTime);

	}
	
	@Test
	public void registerCourse_registerStudentToCourse_returningTrue(){
		// Parameterized constructor 
		Course newCourse = new Course();
		boolean isReigsitered= studentUnderTestParametrized.registerCourse(newCourse);
		assertTrue(isReigsitered);

	}
	
	@Test
	public void isCreated_testingIfstudentIsCreatedByUniversity_returningTrue(){
		// Parameterized constructor 
		
		boolean isCreated= studentUnderTestParametrized.isCreated();
		assertTrue(isCreated);

	}
	
	
	
	
}
