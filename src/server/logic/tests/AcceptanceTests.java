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
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void clerkCreatesCourseTest() {
		universityTable.createcourse("MobileCommerce", 30);
		int expectedNumberOfCourses = 5;
		int actualNumberOfCourses = courseTable.size();
		assertEquals(expectedNumberOfCourses, actualNumberOfCourses);
		
	}

}
