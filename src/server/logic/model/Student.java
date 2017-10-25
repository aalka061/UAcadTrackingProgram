package server.logic.model;

import java.util.ArrayList;

public class Student {
	
	private static int studentId =0;
	private int studentNumber;
	private String name;
	private int age;
	private boolean isCreated = false; 
	private boolean isFullTime; 
	private ArrayList<Course> currentCourses;
	
	public Student (){
		//this.number=0;
		this.name="UnKnown";
		this.age=17;
		
	}
	
	public void setFullTime(boolean isFullTime) {
		this.isFullTime = isFullTime;
	}
	
	
	public Student (String name, int  age, boolean isFullTime ){
		studentId++;
		studentNumber = studentId;
		this.name=name;
		this.age=age;
		this.isFullTime=isFullTime;
		this.isCreated = false;
		currentCourses = new ArrayList<Course>();
		}
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public int getAge() {
		// TODO Auto-generated method stub
		return age;
	}
	
	public int studentNumber() {
		// TODO Auto-generated method stub
		return  studentNumber;
	}
	public boolean isFullTime() {
		// TODO Auto-generated method stub
		return isFullTime;
	}

	public boolean registerCourse(Course newCourse) {
		// TODO Auto-generated method stub
		boolean result = false;
		if (newCourse !=null && checkDuplicateCourses(newCourse)){
				currentCourses.add(newCourse);
				result = true; 
		} 
		//System.out.println(result);
		return result;
	}
	public boolean deregisterCourse(Course toBeRemovedCourse) {
		// TODO Auto-generated method stub
		boolean result = false;
		 
		if (toBeRemovedCourse !=null && isCourseExist(toBeRemovedCourse)){
				currentCourses.remove(findCourseIndex(toBeRemovedCourse));
				result = true; 
		} 
		//System.out.println(result);
		return result;
	}
	// return false when there a course to be added is already in the list of current courses 
	private boolean checkDuplicateCourses(Course newCourse) {
		// TODO Auto-generated method stub
		boolean result = true;
		for (int i=0; i<currentCourses.size();i++){
			if(newCourse.getCode()==currentCourses.get(i).getCode()){
				 result=false;
			}
		}
		return result;
	
	}
	
	private boolean isCourseExist(Course newCourse) {
		// TODO Auto-generated method stub
		boolean result = false;
		for (int i=0; i<currentCourses.size();i++){
			if(newCourse.getCode()==currentCourses.get(i).getCode()){
				 result=true;
			}
		}
		return result;
	
	}
	// isCourse Exist must run before this method 
	private int findCourseIndex(Course newCourse) {
		// TODO Auto-generated method stub
		int courseIndex=0;
		for (int i=0; i<currentCourses.size();i++){
			if(newCourse.getCode()==currentCourses.get(i).getCode()){
				 courseIndex=i;
			}
		}
		return courseIndex;
	
	}
	
	

	public boolean isCreated() {
		// TODO Auto-generated method stub
		return isCreated;
	}
	
	public void setIsCreated(boolean isCreatedNew) {
		// TODO Auto-generated method stub
		isCreated = isCreatedNew;
	}


	public ArrayList<Course> getCurrentCourses() {
		return currentCourses;
	}

	public void setCurrentCourses(ArrayList<Course> currentCourses) {
		this.currentCourses = currentCourses;
	}
	public boolean isMaxCouresesReached (){
		if(isFullTime && currentCourses.size()==UniversityMediator.MaxCoursesForFTStudents){
			return true; 
		} else if (!isFullTime &&currentCourses.size()==UniversityMediator.MaxCoursesForPTStudents){
			return true;
		} else {
			return false;
		}
	}
	
	

}
