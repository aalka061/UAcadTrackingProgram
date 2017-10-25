package server.logic.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import server.logic.tables.UniversityTable;

public class Course {
	
	private String title;
	private int myCode; 
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
	private boolean hasProject;
	private int finalGrade;
	private int dueDayAssignements[];
	private int dueDayMidterms[];

	public final static int FULL_GRADE = 100;

	
	
	public Course (String title,boolean enforcePrereqs, int numberOfMidterms, int numberOfAssignments, 
		boolean hasAFinal, int capSize, int code ){
		
		this.title = title;
		this.enforcePrereqs= enforcePrereqs;
		if(numberOfMidterms<0 || numberOfMidterms>2){
			  throw new IllegalArgumentException("Number of midterms must be btw 0-2");
		} else {
			this.numberOfMidterms = numberOfMidterms;
			midterms = new int[numberOfMidterms];
			dueDayMidterms = new int[numberOfMidterms];
		}
		
		if(numberOfAssignments<0 || numberOfAssignments>5){
			  throw new IllegalArgumentException("Number of assignements must be btw 0-5");
		} else {
			this.numberOfAssignments = numberOfAssignments;
			assignments = new int[numberOfAssignments];
			dueDayAssignements = new int[numberOfAssignments];

		}
		
		this.hasFinal=hasAFinal;
		
		if(capSize<25){
			  throw new IllegalArgumentException("cap size must be more then 25");
		} else {
			this.capSize=capSize;
		}
		if(!checkCode(code)){
			throw new IllegalArgumentException("Code is invalid");
			
		} else {
			this.myCode = code; 
		}
		studentList = new ArrayList<Student>();
		isFull = false;
		
	}
	
	
	
	public void setCode(int code) {
		this.myCode = code;
	}
	public int getCode() {
		return myCode;
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
public void setNumberOfAssignments(int numberOfAssignments) {
	this.numberOfAssignments = numberOfAssignments;
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
		if(assignmentNumber==0){
			return 0;
		}
		return assignments[assignmentNumber-1];	
		
	}
	
	public void setAssignmentWeight(int assignmentNumber, int grade){
		assignments[assignmentNumber-1] = grade;
	}
	
	public void setMidtermWeight(int midtermNumber, int grade){
		midterms[midtermNumber-1] = grade;
	}
	public int weightOfMidterm(int midtermNum) {
		// TODO Auto-generated method stub
		if(midtermNum==0){
			return 0;
		} else 
			return midterms[midtermNum-1];
	}
	
	public int weightOfFinal() {
		// TODO Auto-generated method stub
		if (hasFinal==false)
			return 0;
		else 
			return finalGrade;
		
	}
	public void setFinalGrade(int finalGrade) {
		this.finalGrade = finalGrade;
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

	public boolean isHasFinal() {
		return hasFinal;
	}
	public int getNumberOfAssignments() {
		return numberOfAssignments;
	}


	public int getSumOfCourseElements() {
		// TODO Auto-generated method stub
		int assignementsGrade=0;
		int midtermsGrade = 0; 
		//System.out.println(assignementsGrade);
		for (int i=1; i<=numberOfAssignments;i++){
			assignementsGrade=assignementsGrade+weightOfAssignment(i);
		}
		
		for (int i=1; i<=numberOfMidterms;i++){
			midtermsGrade=midtermsGrade+weightOfMidterm(i);
		}
		return weightOfFinal()+midtermsGrade+assignementsGrade ;
	}
	public int[] getMidterms() {
		return midterms;
	}
	public int[] getAssignments() {
		return assignments;
	}
	
public void setWeightsOfaCourse(){
		
	// case 1 : final + midterms+ assignements 
		if (isHasFinal() && getNumberOfMidterms()>0 && 
				getNumberOfAssignments()>0){
			int sum=0;
			int sum2=0;
			int finalWeight = (randInt(0,100)*2)%100; // get only even numbers
			finalGrade =finalWeight;
			int midAndAsWeight = (Course.FULL_GRADE-finalWeight);
			int midWeight =0;
			if ((midAndAsWeight/2)%2==0) {
				midWeight = (midAndAsWeight/2);
			} else {
				midWeight = (midAndAsWeight/2)+1;
			}
		
			
			int assignWeight=Course.FULL_GRADE-finalWeight-midWeight;
			for (int i=0; i<getMidterms().length;i++){
				int grade = midWeight/getMidterms().length;
				setMidtermWeight(i+1, grade);
				
			}
			for (int i=0; i<getAssignments().length;i++){
				int grade = assignWeight/getAssignments().length;
				setAssignmentWeight(i+1, grade);
			}
			
			
			// case 2 : no final + midterms+ assignements 

		} else if (!isHasFinal() && getNumberOfMidterms()>0 && 
				getNumberOfAssignments()>0){
		
			
			if (getNumberOfMidterms()==2){
				setMidtermWeight(1,20);
				setMidtermWeight(2,20);
				int gradeA = (FULL_GRADE-40)/getNumberOfAssignments();
				for (int i=0; i<getAssignments().length;i++){
					setAssignmentWeight(i+1, gradeA);
				}
			} else if(getNumberOfMidterms()==1){
				setMidtermWeight(1,40);
				int gradeA = (FULL_GRADE-40)/getNumberOfAssignments();
				for (int i=0; i<getAssignments().length;i++){
					setAssignmentWeight(i+1, gradeA);
				}
			}
			
			// no final and no mids and some assignements 
		} else if (!isHasFinal() && getNumberOfMidterms()==0 && getNumberOfAssignments()>0){
			
			if (getNumberOfAssignments()==3){
				setAssignmentWeight(1, 33);
				setAssignmentWeight(2, 33);
				setAssignmentWeight(3, 34);
			} else {
				int grade =FULL_GRADE/getNumberOfAssignments();
				for (int i=0; i<getAssignments().length;i++){
					setAssignmentWeight(i+1, grade);
				}
			}
			
		} else if (isHasFinal() && getNumberOfMidterms()==0 && getNumberOfAssignments()==0){
			setFinalGrade(100);
		}
		System.out.println(getNumberOfMidterms() +" "+getNumberOfAssignments() + " "+ getCode());

		
		
		
	}
	public int[] getDueDayMidterms() {
		return dueDayMidterms;
	}
	public int[] getDueDayAssignements() {
		return dueDayAssignements;
	}
	
	public void setDueDayAssignements(int[] dueDayAssignements) {
		this.dueDayAssignements = dueDayAssignements;
	}
	
	public void setDueDayMidterms(int[] dueDayMidterms) {
		this.dueDayMidterms = dueDayMidterms;
	}
	
	public int getDueDateForAssignement (int index){
		if(dueDayAssignements.length>0){
			return dueDayAssignements[index];
		} else {
			return 0;
		}
	}
	public int getDueDateForMidterm (int index){
		if(dueDayMidterms.length>0){
			return dueDayMidterms[index];
		} else {
			return 0;
		}
	}

	public void setDueDates() {
		
		if (numberOfAssignments>1){
			int timeLimitAs= UniversityTable.TERMDURATION/numberOfAssignments;
			int accTime = timeLimitAs; 
			for (int i=0 ; i<numberOfAssignments; i++) {
				dueDayAssignements[i]= UniversityTable.TERMSTARTDAY+accTime;
				accTime = accTime+timeLimitAs;
				System.out.println(dueDayAssignements[i]);

			}
		} else if (numberOfAssignments==1) {
			dueDayAssignements[0] = UniversityTable.TERMSTARTDAY+35;
		} 
		
		if (numberOfMidterms>1){
			int timeLimitMs= UniversityTable.TERMDURATION/numberOfMidterms;
			int m1 = randInt(20,30); // first midterm 
			int m2 = randInt(45,70); 
			
			dueDayMidterms[0]= UniversityTable.TERMSTARTDAY+m1;
			dueDayMidterms[1]= UniversityTable.TERMSTARTDAY+m2;
			
			System.out.println(dueDayMidterms[0]);
			System.out.println(dueDayMidterms[1]);



			
		} else if(numberOfMidterms==1) {
			dueDayMidterms[0] = UniversityTable.TERMSTARTDAY+40;
		} 
		
	}
//used to generate a number between min and max 
	public int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	
	


}
