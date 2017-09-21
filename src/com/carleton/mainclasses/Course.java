package com.carleton.mainclasses;

public class Course {
	
	private String title;
	private int code; 

	public Course(String title,int myCode) {
		this.title=title; 
		// first 2 digits represents dept code and the the last the corse code
		if(!checkCode(myCode)){
			throw new IllegalArgumentException("Code is invalid");
			
		}
		this.code = myCode; 
		// TODO Auto-generated constructor stub
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	public int getCode() {
		return code;
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

}
