package com.carleton.mainclasses;

import java.util.ArrayList;
import java.util.List;

public class Course {
	
	private String title;
	private int code; 
	private boolean enforcePrereqs=false;
	private int numberOfMidterms;
	private boolean hasAFinal = false;
	private int capSize;
	public static int instances =0;
	private ArrayList<Student> studentList;
		
	
	// Default constructor. Populates course name, number of students with defaults
	public Course (){
		instances++;
		this.title = "Not Set";
		this.numberOfMidterms=0;
		this.capSize=0;
		this.code=10000;
		
	}
	public Course(String title,int myCode) {
		this.title=title; 
		// first 2 digits represents dept code and the the last the corse code
		if(!checkCode(myCode)){
			throw new IllegalArgumentException("Code is invalid");
			
		}
		this.code = myCode; 
		
		studentList = new ArrayList<Student>();
		
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	public int getCode() {
		return code;
	}
	
	public int getCapSize() {
		return capSize;
	}
	public int getNumberOfMidterms() {
		return numberOfMidterms;
	}
	
	public boolean isEnforcePrereqs() {
		return enforcePrereqs;
	}
	
	
	public void setCapSize(int capSize) {
		this.capSize = capSize;
	}
	public void setEnforcePrereqs(boolean enforcePrereqs) {
		this.enforcePrereqs = enforcePrereqs;
	}
	public void setNumberOfMidterms(int numberOfMidterms) {
		this.numberOfMidterms = numberOfMidterms;
	}
	
	public void setHasAFinal(boolean hasAFinal) {
		this.hasAFinal = hasAFinal;
	}
		
	
	public String title() {
		// TODO Auto-generated method stub
		if (title!=null){
			return title;
		}
		return null;
	}
	public int Code() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private boolean checkCode(int codeOfCourse){
		int firstDigitOfCode=  Integer.parseInt(Integer.toString(codeOfCourse).substring(0, 1));
		if (String.valueOf(codeOfCourse).length()== 6 && firstDigitOfCode!=0){
			return true;
		}
		return false; 
		
	}

	public List<Student> Students() {
		// TODO Auto-generated method stub
		return studentList;
	
	}
	public boolean addStudent(Student student) {
		studentList.add(student);
		return true;
		// TODO Auto-generated method stub
		
	}


}
