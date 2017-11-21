package main.java.server.logic.tables;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.management.remote.SubjectDelegationPermission;

import org.apache.log4j.Logger;

import main.java.server.logic.model.ApplicationUniversityMediator;
import main.java.server.logic.model.Course;
import main.java.server.logic.model.JavaReminder;
import main.java.server.logic.model.Student;
import main.java.server.logic.model.UniversityMediator;
import main.java.utilities.Trace;

public class UniversityTable {
	
	private Logger logger = Trace.getInstance().getLogger("opreation_file");
	List <ApplicationUniversityMediator> universitiesList = new ArrayList<ApplicationUniversityMediator> ();
	JavaReminder newTimer = null;
	private int dayInSecond = 20;
	private final int time =20;
	public static final int COURSECREACTIONDEADLINE= 20;
	public static final int COURSEREGISTRATIONSTART= 21;
	public static final int REGISTRATIONDURATION   = 34;
	public static final int TERMDURATION           = 84;
	public static final int TERMSTARTDAY           = 35;
	public static final int LASTDAYDEREGISTRATION  = 49;
	public static final int LASTDAYDROP            = 75;
	public static final int TERMENDDAY             = 120;
	public static final int FINALDUEDAY            = 119;



	
	 private static class UniversityHolder {
	        private static final UniversityTable INSTANCE = new UniversityTable();
	    }
	 
	 private UniversityTable(){
		 ApplicationUniversityMediator uOttawa = new ApplicationUniversityMediator(25);
		 universitiesList.add(uOttawa);
		 startTiming(time);
		 coursesCreated();	
		
	    	
	    };
	    public static final UniversityTable getInstance() {
	        return UniversityHolder.INSTANCE;
	    }
	    private void startTiming (int time){
	    	for (int i=0 ; i<TERMENDDAY; i++){
	   		 	newTimer= new JavaReminder(dayInSecond);
	   		 dayInSecond =dayInSecond + time;

	    	}
	    }
	 
	 private void coursesCreated() {
		// TODO Auto-generated method stub
		 List<Course> coursesList = CourseTabel.getInstance().getCourseList();
	    String[] titleList=new String[]{"Obejct Oriented Dev.","Machine Learning","Wireless AdHock",
	    		"Mutimedia Commuincation"};
	    String[] studentsNames=new String[]{"Emley","Michelle","Linda","Richard"};
	    Boolean[] isFulltime=new Boolean[]{true,false,false,true};
	     final int initCourses = 4;

	    Integer[] studentsAge=new Integer[]{18,20,28,21};
		 // create 3 courses 
		 for(int i=0; i<initCourses;i++){
			 universitiesList.get(0).creatCourse(titleList[i], 27+i);
			 universitiesList.get(0).creatStudent(studentsNames[i], studentsAge[i], 
					 isFulltime[i]);
		 }	
		 logger.info(String.format("Operation:Initialize University uOttawa with %d courses and %d students",
				 initCourses, studentsNames.length));
		 
		
	}
	 public void clearUniversityTable(){
		 universitiesList.get(0).students().clear();
		 universitiesList.get(0).courses().clear();
	 }
	 
	 public void cleanNewAddedCoursesAndStudents(){
		 
		 for (int i=4; i<universitiesList.get(0).students().size(); i++){
			 universitiesList.get(0).students().remove(i);
		 }
		 for (int i=4; i<universitiesList.get(0).courses().size(); i++){
			 universitiesList.get(0).courses().remove(i);
		 }
		 for (int i=0; i<universitiesList.get(0).courses().size(); i++){
			 Course course = universitiesList.get(0).courses().get(i);
			 for(int j=0; j<course.Students().size();j++){
				 course.Students().remove(j);
			 }
		 }
		 for (int i=0; i<universitiesList.get(0).students().size(); i++){
			 Student student= universitiesList.get(0).students().get(i);
			 student.getCurrentCourses().clear();
		 }
		 
		 
		 
		
	 }
	 public void termStarts() {
			// TODO Auto-generated method stub
		
		 List<Student> students =  universitiesList.get(0).students();
		 List<Course> courses = universitiesList.get(0).courses();

		// System.out.println("term start :)");
		
		 boolean termEnds = false; 
		 int dayOfAs=0;
		 int dayOfMid=0;
		 int today_1 = 0;
		 int today_2 = 1;
		
			 for (int i=0; i<students.size();i++){
				 Student student = students.get(i);
				 System.out.println("Student: "+student.getName());
				 List<Course> studentCourses = students.get(i).getCurrentCourses();  // course 1 
			//	 System.out.println("number of courses are "+studentCourses.size());
			
				 for (int j=0; j<studentCourses.size();j++){
					
					 Course course = studentCourses .get(j);
				    // System.out.println("course is "+ course.getCode());
					 	if(isDueAs(course)){
					 		//System.out.println("numeber of Assignements: "+course.getNumberOfAssignments());
					 		for(int k=0; k<course.getNumberOfAssignments(); k++){ 
								 if(course.getDueDateForAssignement(k)==JavaReminder.day){
								//	 System.out.println("Due Date : "+ course.getDueDateForAssignement(k));
									 int aGrade = course.weightOfAssignment(k+1);
									 int receivedGrad = randInt(0,aGrade);
									 course.setAssignmentWeight(k+1,receivedGrad);
									 System.out.println("    Course : "+course.getCode()+" Assignment# :"+(k+1)+" Grade: "+
											 receivedGrad +"/"+aGrade);
								 }
							 }
					 	}
						if(isDueMs(course)) {
							 for(int k=0; k<course.getNumberOfMidterms(); k++){ 
								 if(course.getDueDateForMidterm(k)==JavaReminder.day){
									// System.out.println("number of midterms are"+ course.getNumberOfAssignments());
									 int midGrade = course.weightOfMidterm(k+1);
									 int receivedGrade=randInt(0,randInt(0,midGrade));
									 course.setMidtermWeight(k+1,receivedGrade);
									 System.out.println("    Course : "+course.getCode()+" Midterm# :"+(k+1)+"Grade: "+
											 receivedGrade+"/"+midGrade);

								 }
							
							 }
						}
						if(JavaReminder.day==FINALDUEDAY){
							int finalWeight = course.weightOfFinal();
							
							int finalGradeRandom= randInt(0,finalWeight);
							 System.out.println("     Course : "+course.getCode()+" Final :" + finalGradeRandom +"/"+finalWeight);
							course.setFinalGrade(finalGradeRandom);
						}
						 
					
					 

					
				 }
			 }
					 
 			 logger.info(String.format("Operation:Initialize Univeristy uOttawa with courses"));
			 
			
		}
	 public void termEnds() {
		 
	 }
	 private boolean isDueAs(Course course){
		
		 
		 
		   for (int j=0; j<course.getNumberOfAssignments();j++){
					if (course.getDueDateForAssignement(j)==JavaReminder.day){
						return true;
					}
				}
		   return false;
				
		 
	 }
	 
	 private boolean isDueMs(Course course){
		   for (int j=0; j<course.getNumberOfMidterms();j++){
					if (course.getDueDateForMidterm(j)==JavaReminder.day){
						return true;
					}
				}
		   return false;
				
		 
	 }
	 private int getWhichAsIsDue(List<Course> courses){
		
		int dueDay=150;        //never will happen 
		for(int i=0; i<courses.size();i++){
			for (int j=0; j<courses.get(i).getNumberOfAssignments();j++){
				if (courses.get(i).getDueDateForAssignement(j)==JavaReminder.day){
					dueDay = j;
				}
			}
				
		}
		return dueDay;
		 
		 
	 }
	 
	 private int getWhichMsIsDue(List<Course> courses){
			
			int dueDay=150;        //never will happen 
			for(int i=0; i<courses.size();i++){
				for (int j=0; j<courses.get(i).getNumberOfMidterms();j++){
					if (courses.get(i).getDueDateForMidterm(j)==JavaReminder.day){
						dueDay = j;
					}
				}
					
			}
			return dueDay;
			 
			 
		 }
	 
	 

	 public Object createcourse(String string, int capSize) {		
			boolean result=true;
			int flag=0;
			int coursesNumber = universitiesList.get(0).courses().size();
			for(int i=0;i<coursesNumber;i++){
				String title=universitiesList.get(0).courses().get(i).title();
				if(title.equalsIgnoreCase(string)){
					flag=flag+1;
				}else{
					flag=flag+0;	
				}
			}
			if(flag==0){
				 universitiesList.get(0).creatCourse(string,capSize);
				 result=true;
				logger.info(String.format("Operation:Create New Course;Course Info:[%s,%d];State:Success", string,capSize));
			}else{
				result=false;
				logger.info(String.format("Operation:Create New Course;Course Info:[%s,%d];State:Fail;"
						+ "Reason:The User already existed.", string,capSize));
			}
			return result;	
		}
	 
	 

	 public Object createstudent(String name, int age, boolean isFullTime) {		
			boolean result=true;
			int flag=0;
			int sizeOfStudentList = universitiesList.get(0).students().size();
			
			for(int i=0;i<sizeOfStudentList;i++){
				String sName=universitiesList.get(0).students().get(i).getName();
				if(sName.equalsIgnoreCase(name)){
					flag=flag+1;
				}else{
					flag=flag+0;	
				}
			}
			if(flag==0){
				 universitiesList.get(0).creatStudent(name, age, isFullTime);
				 result=true;
				logger.info(String.format("Operation:Create New Student;Student Info:[%s,%d,%s];State:Success",
						name,age,isFullTime));
			}else{
				result=false;
				
				logger.info(String.format("Operation:Create New Student;Student Info:[%s,%d];State:Fail;"
						+ "Reason:The Student already existed.", name,age));
			}
			return result;	
		}
	public Object registerStudentForCourse(int studentNumber, int courseCode) {
		// TODO Auto-generated method stub
		boolean result=true;
		int flag=0;
		int sizeOfStudentList = universitiesList.get(0).students().size();
		int sizeOfCoursesList = universitiesList.get(0).courses().size();
		
		Student toBeRegisteredSt = universitiesList.get(0).lookupStudentByStNumber(studentNumber);
		Course  toBeRegisteredCo = universitiesList.get(0).lookupCourseByCode(courseCode);
		if (toBeRegisteredSt!=null && toBeRegisteredCo!=null && !toBeRegisteredSt.isMaxCouresesReached()){
			 universitiesList.get(0).registerStudentForCourse(toBeRegisteredSt, toBeRegisteredCo);
			 result=true;
			 logger.info(String.format("Operation:Student Registration;Student [%s], Course[%s];State:Success;"
						+  toBeRegisteredSt.getName(),toBeRegisteredCo.title()));

		}else{
			result=false;
			logger.info(String.format("Operation:Student Registration;Student [%s], Course[%s];State:Fail;"
					+ "Reason:The Student or Course does not exist.", studentNumber,courseCode));
		}
	
		return result;	
	}
	
	public Object cancelCourse(int courseCode) {
		
		boolean result=true;
		int flag=0;
		int sizeOfCoursesList = universitiesList.get(0).courses().size();		
		Course  toBeCancelledCourse = universitiesList.get(0).lookupCourseByCode(courseCode);
	
		if (toBeCancelledCourse!=null){
			int sizeOfStudents = toBeCancelledCourse.Students().size();
			if (sizeOfStudents>0){
				universitiesList.get(0).cancelCourse(toBeCancelledCourse);
				 result=true;
				 logger.info(String.format("Operation:Course Cancellation;Course[%s,%d];State:Success;"
						 ,toBeCancelledCourse.title(),toBeCancelledCourse.getCode() ));				
			} else {
				result = false;
			}
			 
		}else{
			result=false;
			logger.info(String.format("Operation:Course Cancellation;Course[%d];State:Fail;"
					+ "Reason:The Course has no student.", courseCode));
		}
	
		return result;	

	}
	public Object destroyCourse(int courseCode) {
		// TODO Auto-generated method stub
		boolean result=true;
		int flag=0;
		Course  toBeDestroyedCourse = universitiesList.get(0).lookupCourseByCode(courseCode);
	
		if (toBeDestroyedCourse!=null){
			universitiesList.get(0).destroy(toBeDestroyedCourse);
			result=true;
			logger.info(String.format("Operation:Course Eliminatioin;Course[%s,%d];State:Success;"
						 ,toBeDestroyedCourse.title(),toBeDestroyedCourse.getCode() ));				
			
		}else{
			result=false;
			logger.info(String.format("Operation:Course Elimination;Course[%d];State:Fail;"
					+ "Reason:The Course has no student.", courseCode));
		}
	
		return result;	
	}
	
	 public Object deleteStudent(int studentNumber) {		
			boolean result=true;
			int flag=0;
			Student student= StudentTabel.getInstance().getSutdent(studentNumber-1);
			
			if (student!=null){
				StudentTabel.getInstance().deleteSutdent(studentNumber-1);
				result = true; 
				logger.info(String.format("Operation:Remove Student;Student:[%d];State:Success", studentNumber));
				
			} else {
				result = false;
				logger.info(String.format("Operation:Remove Student;Student:[%d];State; Fail + Rea"
						+ "Reason: Student %d is not in the System", studentNumber));

			}
			return result;	
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
}
