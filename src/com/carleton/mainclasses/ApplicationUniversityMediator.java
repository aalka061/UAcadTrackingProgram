package com.carleton.mainclasses;

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

}
