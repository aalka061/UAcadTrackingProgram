package server.logic.tables;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import server.logic.model.Course;
import server.logic.model.JavaReminder;
import server.logic.model.Student;
import utilities.Trace;

public class StudentTabel {
	
	private Logger logger = Trace.getInstance().getLogger("opreation_file");
	List<Student> studentList=new ArrayList<Student>();
	Student taregetedStudent = null;
	private int targetedStudentNumber=0;
    private static class StudentListHolder {
        private static final StudentTabel INSTANCE = new StudentTabel();
    }
    private StudentTabel(){
    	//set up the default list with some instances
    	
    
    	logger.info(String.format("Operation:Initialize Student Table;Student Tabel: %s", 
    			studentList));
    };
    public static final StudentTabel getInstance() {
        return StudentListHolder.INSTANCE;
    }
    
   public List<Student> getStudentList() {
	return studentList;
}
   public void add(Student newStudent) {
	// TODO Auto-generated method stub
	if(newStudent!=null){
		studentList.add(newStudent);
	}
	
   }
   
   public int size() {
		// TODO Auto-generated method stub
	   return studentList.size();
		
	}
   
   public Student getSutdent(int index) {
		// TODO Auto-generated method stub
	   return studentList.get(index);
		
	}
   public Object deregisterOrDrop (int courseCode){
	   if(JavaReminder.day >=UniversityTable.COURSEREGISTRATIONSTART && JavaReminder.day <=UniversityTable.LASTDAYDEREGISTRATION){
		   return deregisterCourse(courseCode);
   		} else {
   			return dropCourse(courseCode);
   		}
}
	public Object registerCourse(int courseCode) {
		// TODO Auto-generated method stub
		boolean result=true;
		int flag=0;
		Course  toBeRegisteredInCourse = CourseTabel.getInstance().findCourse(courseCode);
			
		if (toBeRegisteredInCourse!=null && taregetedStudent!=null && 
				 !taregetedStudent.isMaxCouresesReached() &&taregetedStudent.registerCourse(toBeRegisteredInCourse)){
			
				result=true;
				logger.info(String.format("Operation:Course Registeration by Student;Course[%d], Student[%s];State:Success;"
							 ,toBeRegisteredInCourse.getCode(),taregetedStudent.getName()));		
			
		}else{

			result=false;
			logger.info(String.format("Operation:Course Registeration by Student;Course[%d];State:Fail;"
					+ "Reason:The Course is not existant or student already registerd .", courseCode));
		}
	
		return result;	
	}
	public Object dropCourse(int courseCode) {
		// TODO Auto-generated method stub
		
		boolean result=true;
		int flag=0;
		Course  toBeDroppedcourse = CourseTabel.getInstance().findCourse(courseCode);
		Student studentInTarget= getSutdent(targetedStudentNumber-1);

		if (toBeDroppedcourse!=null && taregetedStudent!=null && 
				 !taregetedStudent.isMaxCouresesReached() &&studentInTarget!=null){
			
				studentInTarget.dropCourse(toBeDroppedcourse);
				result=true;
				logger.info(String.format("Operation:Course Drop by Student;Course[%d], Student[%s];State:Success;"
							 ,toBeDroppedcourse.getCode(),taregetedStudent.getName()));		
			
		}else{

			result=false;
			logger.info(String.format("Operation:Course Drop by Student;Course[%d];State:Fail;"
					+ "Reason:The Course is not existant or student already registerd .", courseCode));
		}
	
		return result;	
	}
	
	public Object deregisterCourse(int courseCode) {
		// TODO Auto-generated method stub
		boolean result=true;
		int flag=0;
		Course  toBeRegisteredInCourse = CourseTabel.getInstance().findCourse(courseCode);
		Student toBeDeregistredStudent= getSutdent(targetedStudentNumber-1);
		
		if (toBeRegisteredInCourse!=null && taregetedStudent!=null && 
				 !taregetedStudent.isMaxCouresesReached() && toBeDeregistredStudent!=null) {
				toBeDeregistredStudent.deregisterCourse(toBeRegisteredInCourse);
				result=true;
				logger.info(String.format("Operation:Course Deregisteration by Student;Course[%d], Student[%s];State:Success;"
							 ,toBeRegisteredInCourse.getCode(),taregetedStudent.getName()));		
			
		}else{

			result=false;
			logger.info(String.format("Operation:Course deregisteration by Student;Course[%d];State:Fail;"
					+ "Reason:The Course is not existant or student already registerd .", courseCode));
		}
	
		return result;	
	}
	
	
	private Course lookupCourseByCode(int courseCode) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Object lookupStudentById(int studentNumber) {
		// TODO Auto-generated method stub
		boolean result=false;
		int flag=0;
		Student searhedForStudent=null;
		for (int i=0; i<studentList.size(); i++){
			if (studentList.get(i).studentNumber()==studentNumber){
				searhedForStudent = studentList.get(i);
				taregetedStudent = searhedForStudent;
				targetedStudentNumber=searhedForStudent.studentNumber();
				result=true;
				logger.info(String.format("Operation:Student Lookup;Student[%d];State:Success;"
							 ,studentNumber));	
			} 
		} 
		if(!result){
			logger.info(String.format("Operation:Student Lookup;Student[%d];State:Fail;"
					+ "Reason:The student does not exist.", studentNumber));
		}
		
	
		return result;	
	}
	
	

}


