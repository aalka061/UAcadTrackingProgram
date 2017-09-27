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
		studentUnderTestParametrized = new Student("Karev",18);
		
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
		int sNumberExpected= 1;
		assertEquals(sNumberExpected,sNumberActual);

	}
	
	
}
