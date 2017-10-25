package server.logic.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import server.logic.tables.CourseTabel;
import server.logic.tables.StudentTabel;


public class ApplicationUniversityMediator implements UniversityMediator  {
	
	private Integer universtityCourses;  
	private StudentTabel studentList; 
	private CourseTabel coursesList;
	
    
	
	
	public ApplicationUniversityMediator (int universtityCourses) {
		if(universtityCourses<0 || universtityCourses>25){
			  throw new IllegalArgumentException();
		} else {
		this.universtityCourses = universtityCourses;
		}
		studentList=StudentTabel.getInstance();
		coursesList = CourseTabel.getInstance();
		
		
	}
	
	public int getNumberOfUniversityCourses() {
		// TODO Auto-generated method stub
		return universtityCourses;
	}


	public int getNumberOfMaxFTStudents() {
		// TODO Auto-generated method stub
		return MaxCoursesForFTStudents;

	}


	public int getNumberOfMaxPTStudents() {
		// TODO Auto-generated method stub
		return MaxCoursesForPTStudents;
	}


	public int getPassRate() {
		// TODO Auto-generated method stub
		return PassRate;
	}


	public Course creatCourse(String string, int cap) {
		// TODO Auto-generated method stub
		//public Course (String title,boolean enforcePrereqs, int numberOfMidterms, int numberOfAssignments, 
		//boolean hasAFinal, int capSize, int code )
		int firstCourseCode = 100000;
		int currentCourseCode=0;
		if (coursesList != null) {
			currentCourseCode = firstCourseCode+coursesList.size();
		} else {
			currentCourseCode = firstCourseCode;
		}
		
		boolean randomBoolean_prereq;
		boolean randomBoolean_hasFinal=false;
		int r_numOfMids=0;
		boolean isAtleastOneGradeElement= false; 
		int r_numberOfAs=0;
		
		while (!isAtleastOneGradeElement){
			randomBoolean_hasFinal= randomBoolean();
			r_numberOfAs = randInt(0,5);
			r_numOfMids = randInt(0,2);
			isAtleastOneGradeElement=checkOneGradeElement(randomBoolean_hasFinal,
					r_numberOfAs,r_numOfMids);
		}
		
		randomBoolean_prereq= randomBoolean();

		
		//System.out.println(r_numberOfAs+"   "+r_numOfMids);
		//System.out.println(randomBoolean_hasFinal);
		Course newCourse = new Course (string,randomBoolean_prereq,r_numOfMids,
				r_numberOfAs,randomBoolean_hasFinal,cap,currentCourseCode );
		newCourse.setWeightsOfaCourse();
		coursesList.add(newCourse);
		newCourse.setDueDates();
		return newCourse;
	}


	public List<Course> courses() {
		// TODO Auto-generated method stub
		return coursesList.courses();
	}

	public List<Student> students() {
		// TODO Auto-generated method stub
		return studentList.getStudentList();
	}

	public Student creatStudent(String name, int age, boolean isFullTime) {
		// TODO Auto-generated method stub
		Student newStudent= new Student(name,age,isFullTime);
		studentList.add(newStudent);
		newStudent.setIsCreated(true);
		return newStudent;
		
	}

	public void registerStudentForCourse(Student newStudent, Course newCourse) {
		// TODO Auto-generated method stub
		int numberOfSudentsInNewCourse =0;
		if (newCourse!=null){
			numberOfSudentsInNewCourse= newCourse.Students().size();  
		}
		if (newCourse.Students().contains(newStudent)){
	        throw new IllegalArgumentException(newStudent.getName()+
	        		" is Already registred in this course!");
	        
		}
		else if (newStudent!= null && newCourse!=null && 
				numberOfSudentsInNewCourse <newCourse.getCapSize()){
			newStudent.registerCourse(newCourse);
			newCourse.addStudent(newStudent);
		} else {
	        throw new IllegalStateException("Max Number of student is reached!");

		}
	}
	public Student lookupStudentByStNumber(int studentNumber){
		
		Student toBeRegisteredSt = null;
		for(int i=0;i<studentList.size();i++){

			int  stNumber=studentList.getSutdent(i).studentNumber();

			if(stNumber==studentNumber){
				toBeRegisteredSt = studentList.getSutdent(i);
				
			}
		}
		
		return toBeRegisteredSt;
		
	}
	
	public Course lookupCourseByCode(int courseCode){
		
		Course courseToRegisterIn = null;
		
		for(int i=0;i<coursesList.size();i++){
			
			int  myCode=coursesList.getCourse(i).getCode();
			if(myCode==courseCode){

				courseToRegisterIn = coursesList.getCourse(i);
			}
		}
		return courseToRegisterIn;
		
	}

	public void cancelCourse(Course newCourse) {

		// TODO Auto-generated method stub
		if (newCourse != null){
			List<Student> listOfCourseStudent=newCourse.Students(); 
			for (Student st : listOfCourseStudent){
				st.getCurrentCourses().remove(newCourse);
				
			}
			newCourse.Students().clear();
			
		} else {
	        throw new IllegalArgumentException("newCourse was null");
		}
	}

	public void destroy(Course newCourse) {
		// TODO Auto-generated method stub
		if(newCourse!=null){
			if(coursesList.courses().contains(newCourse)){
				coursesList.courses().remove(newCourse);
				cancelCourse(newCourse);
			} else {
		        throw new IllegalArgumentException("the course is not listed");

			}
		} else {
	        throw new IllegalArgumentException("the course was null");

		}
		
	}
	// helper functions
	// used to generate true or false randomly
	public boolean randomBoolean(){
	    return Math.random() < 0.5;
	}
	// used to generate a number between min and max 
	public int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	// used to return false when numberOfMidterms&numberOfAssign=0 & hasFinal=false;
	public boolean checkOneGradeElement(boolean hasFinal, int numAs,
			int numMids){
		if(!hasFinal && numMids==0 && numAs==0){
			return false;
		}
		return true; 
	}
	// used to randomly assign weights of assignments, final and midterms
	
	
	
	
	
}
