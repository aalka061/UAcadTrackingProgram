package server.logic.tables;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import server.logic.model.Course;

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
	public Course findCourse (int courseCode){
		
		Course resultedCourse = null;
		for (int i=0; i<courseList.size();i++){
			if (courseList.get(i).getCode()==courseCode){
				resultedCourse = courseList.get(i);
			}
		}
		return resultedCourse;
	}
	


}
