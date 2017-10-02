package com.carleton.mainclasses;

import java.util.ArrayList;

public class Student {
	
	private static int number =0;
	private String name;
	private int age;
	private boolean isCreated = false; 
	private boolean isFullTime; 
	private ArrayList<Course> currentCourses;
	
	public Student (){
		this.number=0;
		this.name="UnKnown";
		this.age=17;
		
	}
	
	public void setFullTime(boolean isFullTime) {
		this.isFullTime = isFullTime;
	}
	
	
	public Student (String name, int  age, boolean isFullTime ){
		number++;
		this.name=name;
		this.age=age;
		this.isFullTime=isFullTime;
		this.isCreated = true;
		currentCourses = new ArrayList<Course>();
	}
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	public int getNumber() {
		// TODO Auto-generated method stub
		return number;
	}
	public int getAge() {
		// TODO Auto-generated method stub
		return age;
	}
	public int studentNumber() {
		// TODO Auto-generated method stub
		return getNumber();
	}
	public boolean isFullTime() {
		// TODO Auto-generated method stub
		return isFullTime;
	}

	public boolean registerCourse(Course newCourse) {
		// TODO Auto-generated method stub
		if (newCourse !=null){
			currentCourses.add(newCourse);
			return true; 
		}
		return false;
	}

	public boolean isCreated() {
		// TODO Auto-generated method stub
		return isCreated;
	}
	

}
