package com.carleton.mainclasses;

public class Student {
	
	public static int number =0;
	private String name;
	private int age;
	
	public Student (){
		this.number=0;
		this.name="UnKnown";
		this.age=17;
		
	}
	public Student (String name, int  age ){
		number++;
		this.name=name;
		this.age=age;
		
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
	

}
