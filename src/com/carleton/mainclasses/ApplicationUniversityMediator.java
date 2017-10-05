package com.carleton.mainclasses;

import java.util.ArrayList;
import java.util.List;

public class ApplicationUniversityMediator implements UniversityMediator  {
	
	private Integer universtityCourses;  
	
    
	
	
	public ApplicationUniversityMediator (int universtityCourses) {
		if(universtityCourses<0 || universtityCourses>25){
			  throw new IllegalArgumentException();
		} else {
		this.universtityCourses = universtityCourses;
		}
		
		
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
		
		Course newCourse = new Course ("OOD",false,0,0,true,cap);
		coursesOfUniversity.add(newCourse);
		return newCourse;
	}


	public List<Course> courses() {
		
		// TODO Auto-generated method stub
		return coursesOfUniversity;
	}

	public List<Student> students() {
		// TODO Auto-generated method stub
		return studentsOfUniversity;
	}

	public Student creatStudent(String name, int age, boolean isFullTime) {
		// TODO Auto-generated method stub
		Student newStudent= new Student(name,age,isFullTime);
		studentsOfUniversity.add(newStudent);
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
			if(coursesOfUniversity.contains(newCourse)){
				coursesOfUniversity.remove(newCourse);
				cancelCourse(newCourse);
			} else {
		        throw new IllegalArgumentException("the course is not listed");

			}
		} else {
	        throw new IllegalArgumentException("the course was null");

		}
		
	}
	
	
	
}
