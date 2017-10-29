package server.logic.tables;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import server.logic.model.Course;
import server.logic.model.JavaReminder;
import server.logic.model.Student;
import utilities.Trace;

public class CourseTabel {
	
	private Logger logger = Trace.getInstance().getLogger("opreation_file");
	List<Course> courseList=new ArrayList<Course>();
    private static class CourseListHolder {
        private static final CourseTabel INSTANCE = new CourseTabel();
    }
    private CourseTabel(){
    	//set up the default list with some instances
    	
    
    	logger.info(String.format("Operation:Initialize CourseTable;CourseTable: %s", 
    			courseList));
    };
    public static final CourseTabel getInstance() {
        return CourseListHolder.INSTANCE;
    }
    
    public List<Course> getCourseList() {
		return courseList;
	}
	public int size() {
		// TODO Auto-generated method stub
		return courseList.size();
	}
	public Course getCourse(int i) {
		// TODO Auto-generated method stub
		return courseList.get(i);
	}
	public void add(Course newCourse) {
		// TODO Auto-generated method stub
		if(newCourse !=null){
			courseList.add(newCourse);
		}
	}
	public List<Course> courses() {
		// TODO Auto-generated method stub
		return courseList;
	}
	
	public void removeStudent(int courseCode, int studentNumber){
		Course course = findCourse(courseCode);
		Student student = StudentTabel.getInstance().getSutdent(studentNumber-1);
		course.removeStudent(student);
	}
	
	public Course findCourse (int courseCode){
		
		Course resultedCourse = null;
		for (int i=0; i<courseList.size();i++){
			if (courseList.get(i).getCode()==courseCode){
				resultedCourse = courseList.get(i);
			}
		}
		return resultedCourse;
	}
	
	public void termEnded(){
		// currentCourses for every student 
		// student 1 -> course 1 - > getSumOfElements 
		//           -> course 2  -> getSumOfElements 
		 List<Student> students = StudentTabel.getInstance().getStudentList(); 
		for (int i=0; i<students.size();i++){
			 Student student = students.get(i);
			 System.out.println("Student"+student.getName());
			 List<Course> studentCourses = students.get(i).getCurrentCourses();  // course 1 
		//	 System.out.println("number of courses are "+studentCourses.size());
		
			 for (int j=0; j<studentCourses.size();j++){
				
				 Course course = studentCourses .get(j);
			    // System.out.println("course is "+ course.getCode());
				 System.out.println("   "+ course.title()+ " "+course.getCode()+ " : "+ course.getSumOfCourseElements());
		/*
		for(int i=0 ; i<courseList.size();i++){
			System.out.println("Course "+courseList.get(i).getCode());
			List<Student>studentList=courseList.get(i).Students();
			System.out.println(studentList.size());

			for (int j=0; j<studentList.size();j++){
				System.out.println(studentList.get(j).getName());
				for (int k=0; k<studentList.get(j).getCurrentCourses().size();k++){
					if(courseList.get(i).getCode()==studentList.get(j).getCurrentCourses().get(k).getCode()){
						System.out.println(studentList.get(j).getCurrentCourses().get(k).getSumOfCourseElements());
					}
				}
			
			}
		}
		*/
	}
	


}
	}
}
