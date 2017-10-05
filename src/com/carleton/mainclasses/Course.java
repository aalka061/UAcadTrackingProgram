package com.carleton.mainclasses;

import java.util.ArrayList;
import java.util.List;

public class Course {
	
	private String title;
	private int code; 
	private boolean enforcePrereqs=false;
	private int numberOfMidterms;     //Between 0-2
	private int numberOfAssignments;  //Between 0-5
	private boolean hasFinal = false;
	private int capSize;
	private boolean isFull;
	public static int instances =0;
	private ArrayList<Student> studentList;
	private int assignments[];
	private int midterms[];
	private final static int FULL_GRADE = 100;

	
	// Default constructor. Populates course name, number of students with defaults
	public Course (){
		instances++;
		this.title = "Not Set";
		this.numberOfMidterms=0;
		this.capSize=0;
		this.code=10000;
		
	}
	public Course (String title,boolean enforcePrereqs, int numberOfMidterms, int numberOfAssignments, 
		boolean hasAFinal, int capSize){
		
		this.title = title;
		this.enforcePrereqs= enforcePrereqs;
		if(numberOfMidterms<0 || numberOfMidterms>2){
			  throw new IllegalArgumentException();
		} else {
			this.numberOfMidterms = numberOfMidterms;
			midterms = new int[numberOfMidterms];
		}
		
		if(numberOfAssignments<0 || numberOfAssignments>5){
			  throw new IllegalArgumentException();
		} else {
			this.numberOfAssignments = numberOfAssignments;
			assignments = new int[numberOfAssignments];

		}
		
		this.hasFinal=hasAFinal;
		
		if(capSize<25){
			  throw new IllegalArgumentException();
		} else {
			this.capSize=capSize;
		}
		studentList = new ArrayList<Student>();
		isFull = false;
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
		this.hasFinal = hasAFinal;
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
		if (student!=null){
			studentList.add(student);
			return true; 
		} 
		return false; 
		// TODO Auto-generated method stub
		
	}
	public Boolean removeStudent(Student toBeDeletedStudent) {
		if (studentList.contains(toBeDeletedStudent) && toBeDeletedStudent!=null){
			studentList.remove(toBeDeletedStudent);
			return true;
		}
		return false; 
		// TODO Auto-generated method stub
		
	}
	public int weightOfAssignment(int assignmentNumber) {
		// TODO Auto-generated method stub
		if(assignmentNumber==0){
			return 0;
		} else if (!hasFinal && numberOfMidterms==0
				&& !hasProject()){
			for(int i=0; i<assignments.length;i++) {
				assignments[i] = FULL_GRADE/assignments.length;
			}
		}
		return assignments[assignmentNumber-1];

		
		
	}
	public int WeightOfMidterm(int midtermNum) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int WeightOfFinal() {
		// TODO Auto-generated method stub
		if (hasFinal==false)
			return 0;
		else 
			return 50;
		
	}
	
	public boolean isFull(){
		if(capSize==studentList.size()){
			return true;
		}
		return false;
	}
	
	public boolean hasProject() {
		return false; 
	}
	
	


}
