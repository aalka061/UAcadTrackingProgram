package com.carleton.mainclasses;

public class ProjectCourse extends Course {

	private int weightOfProject;
	
	public ProjectCourse(String title,boolean enforcePrereqs, int numberOfMidterms, int numberOfAssignments, 
			boolean hasAFinal, int capSize) {
		
		super(title,enforcePrereqs,numberOfMidterms,numberOfAssignments,hasAFinal,capSize);
		
	} 
	
	public boolean hasProject(){
		return true; 
	}
	public int getWeightOfProject() {
		return weightOfProject;
	}
	public void setWeightOfProject(int weightOfProject) {
		this.weightOfProject = weightOfProject;
	}
}
