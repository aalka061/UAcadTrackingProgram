package server.logic.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.logic.tables.CourseTabel;
import server.logic.tables.UniversityTable;

public class AcceptanceTests {
	UniversityTable universityTable= null;
	CourseTabel courseTable = null;
	
	@Before
	public void setUp() throws Exception {
		
		universityTable = UniversityTable.getInstance();
		courseTable = CourseTabel.getInstance();
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

}
