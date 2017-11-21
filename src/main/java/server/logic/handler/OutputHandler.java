package main.java.server.logic.handler;

import org.apache.log4j.Logger;

import main.java.server.logic.handler.model.Output;
import main.java.server.logic.model.JavaReminder;
import main.java.server.logic.tables.StudentTabel;
import main.java.server.logic.tables.UniversityTable;
import main.java.utilities.Config;
import main.java.utilities.Trace;

public class OutputHandler {
	public static final int WAITING = 0;
	public static final int FINISHWAITING=1;
    public static final int CLERK = 2;
    public static final int STUDENT = 3;
    public static final int CREATEUSER=4;
    public static final int CREATECOURSE=5;
    public static final int CREATESTUDENT=6;
    public static final int REGISTERSTUDENT=7;
    public static final int CANCELCOURSE=8;
    public static final int DESTROYCOURSE=9;
    public static final int REGISTERTOCOURSE=10;
    public static final int DROPCOURSE=11;
    public static final int DEREGISTERCOURSE=12;
    public static final int PAYFINE=13;
    public static final int CLERKLOGIN=14;
    public static final int STUDENTLOGIN=15;
    public static final int OVERDUECOURSECREATION=16;
	private static final int STUDENTSTARTREGISTRATION = 17;
    public static final int TERMSTART=18;
    public static final int TERMEND = 19;
	private static final int DEREGISTERORDROPCOURSE =20;
	private static final int DROPCOURSESTATE = 21;
	
	private Logger logger = Trace.getInstance().getLogger("opreation_file");

	public Output clerkLogin(String input) {
		Output output=new Output("",0);
		   
			output.setOutput("What can I do for you? Menu:Create Student/Course"
					+ ", Cancel/Destroy Course");
        	output.setState(CLERK);
		
		
		return output;
	}
	
	public Output userLogin(String input) {
		Output output=new Output("",0);
		//What can I do for you? Menu: Register for course / Deregiser from course/ Drop Course.
		output.setOutput("What can I do for you? Menu: Register for course / Deregiser from course/ Drop Course.");
		output.setState(STUDENT);
		return output;
	}
	public Output studentLogin(String input) {
		// TODO Auto-generated method stub	
		Output output=new Output("",0);
		System.out.println(input);
        // strArray = input.split(",");
 		boolean isStudentIdNumber = numberOrNot(input);

		int studentNumber = Integer.parseInt(input);
        //boolean email=strArray[0].contains("@");
      //  boolean isFulltime=Boolean.valueOf(strArray[2]);
        Object result="";
        
        if(!isStudentIdNumber){
        	output.setOutput("Your input should be a number'");
        	output.setState(STUDENTLOGIN);

        }else{
        	result=StudentTabel.getInstance().lookupStudentById(studentNumber);
        	if(result.equals(true)){
        		output.setOutput("What can I do for you?"
        				+ "Menu: Register Course / Drop Course.");
            	output.setState(STUDENT);
        	}else{
        		output.setOutput("Wrong studnent number");
            	output.setState(STUDENTLOGIN);

        	}
        }
		return output;
	
	}

	public Output createCourse(String input) {

		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        //boolean email=strArray[0].contains("@");
        int capSize = Integer.parseInt(strArray[1]);
        Object result="";
        if(JavaReminder.day>UniversityTable.COURSECREACTIONDEADLINE){
        	output.setOutput("Sorry, Deadline is Passed!");
        	output.setState(OVERDUECOURSECREATION);
        	logger.info(String.format("Operation:Create New Course;Course Info:[%s,%d];State:Fail;"
					+ "Reason:The deadline is passed.", strArray[0],capSize));
        }
        else if(strArray.length!=2 ){
        	output.setOutput("Your input should in this format:'course title,cap size'");
        	output.setState(CREATECOURSE);

        }else{
        	result=UniversityTable.getInstance().createcourse(strArray[0], capSize);
        	if(result.equals(true)){
        		output.setOutput("Success!");
        	}else{
        		output.setOutput("The Course Already Exists!");
        	}
        	output.setState(CLERK);
        }
		return output;
	}
	
	public Output createStudent(String input) {
		
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        //boolean email=strArray[0].contains("@");
        int sAge = Integer.parseInt(strArray[1]);
        boolean isFulltime=Boolean.valueOf(strArray[2]);
        Object result="";
        if(JavaReminder.day>UniversityTable.COURSECREACTIONDEADLINE){
        	output.setOutput("Sorry, Deadline is Passed!");
        	output.setState(OVERDUECOURSECREATION);
        	logger.info(String.format("Operation:Create New Student;Student Info:[%s];State:Fail;"
					+ "Reason:The deadline is passed.", strArray[0]));

        }
        else  if(strArray.length!=3 ){
        	output.setOutput("Your input should in this format:'name,age (int),true (full time)'");
        	output.setState(CREATESTUDENT);

        }else{
        	result=UniversityTable.getInstance().createstudent(strArray[0], sAge, isFulltime);
        	if(result.equals(true)){
        		output.setOutput("Success!");
        	}else{
        		output.setOutput("The Student Already Exists!");
        	}
        	output.setState(CLERK);
        }
		return output;
	}
	

	public Output registerStudentoCourse(String input) {
		
		Output output=new Output("",0);
		String[] strArray = null;   
         strArray = input.split(",");
 		boolean isIdNumber         =numberOrNot(strArray[0]);
 		boolean isCourseCodeNumber = numberOrNot(strArray[1]);
        //boolean email=strArray[0].contains("@");
        int studentNumber = Integer.parseInt(strArray[0]);
        int courseCode = Integer.parseInt(strArray[1]);
      //  boolean isFulltime=Boolean.valueOf(strArray[2]);
        Object result="";
        if(!isIdNumber || strArray.length!=2 || !isCourseCodeNumber){
        	output.setOutput("Your input should be in the format :'student number, course code'");
        	output.setState(REGISTERSTUDENT);

        }else{
        	result=UniversityTable.getInstance().registerStudentForCourse(studentNumber,courseCode);
        	if(result.equals(true)){
        		output.setOutput("Success!");
        	}else{
        		output.setOutput("The Student Already Exists!");
        	}
        	output.setState(CLERK);
        }
		return output;
	}
	
public Output cancelCourse(String input) {
		
		Output output=new Output("",0);
        // strArray = input.split(",");
 		boolean isCourseCodeNumber = numberOrNot(input);
        //boolean email=strArray[0].contains("@");
        int courseCode = Integer.parseInt(input);
      //  boolean isFulltime=Boolean.valueOf(strArray[2]);
        Object result="";
        if(JavaReminder.day>UniversityTable.COURSECREACTIONDEADLINE){
        	output.setOutput("Sorry, Deadline is Passed!");
        	output.setState(OVERDUECOURSECREATION);

        }else if(!isCourseCodeNumber){
        	output.setOutput("Your input should be a a course code'");
        	output.setState(CANCELCOURSE);

        }else{
        	result=UniversityTable.getInstance().cancelCourse(courseCode);
        	if(result.equals(true)){
        		output.setOutput("Success!");
        	}else{
        		output.setOutput("The Course has no Students!");
        	}
        	output.setState(CLERK);
        }
		return output;
	}
	

	public Output destroyCourse(String input) {
		
		Output output=new Output("",0);
	    // strArray = input.split(",");
		boolean isCourseCodeNumber = numberOrNot(input);
	    //boolean email=strArray[0].contains("@");
	    int courseCode = Integer.parseInt(input);
	  //  boolean isFulltime=Boolean.valueOf(strArray[2]);
	    Object result="";
	    if(!isCourseCodeNumber){
	    	output.setOutput("Your input should be a a course code'");
	    	output.setState(DESTROYCOURSE);
	
	    }else{
	    	result=UniversityTable.getInstance().destroyCourse(courseCode);
	    	if(result.equals(true)){
	    		output.setOutput("Success!");
	    	}else{
	    		output.setOutput("The Course does not Exist!");
	    	}
	    	output.setState(CLERK);
	    }
		return output;
	}
// to be run by student
	public Output registerCourse(String input) {
	// TODO Auto-generated method stub
		Output output=new Output("",0);
	    // strArray = input.split(",");
		boolean isCourseCodeNumber = numberOrNot(input);
	    //boolean email=strArray[0].contains("@");
	    int courseCode = Integer.parseInt(input);
	  //  boolean isFulltime=Boolean.valueOf(strArray[2]);
	    Object result="";
	    if(!isCourseCodeNumber){
	    	output.setOutput("Your input should be a a course code'");
	    	output.setState(REGISTERTOCOURSE);
	
	    } else if (JavaReminder.day >UniversityTable.TERMSTARTDAY){
	    	output.setOutput("Registration Deadline is Passed!");
	    	output.setState(STUDENT);
	    }else{
	    	result=StudentTabel.getInstance().registerCourse(courseCode);
	    	if(result.equals(true)){
	    		output.setOutput("Success!");
	    	}else{
	    		output.setOutput("Course is not existant or already registred");
	    	}
	    	output.setState(STUDENT);
	    }
		return output;	
	
	}
	
	public Output deregisterOrDropCourse(String input) {
		Output output=new Output("",0);
	    // strArray = input.split(",");
		boolean isCourseCodeNumber = numberOrNot(input);
	    //boolean email=strArray[0].contains("@");
	    int courseCode = Integer.parseInt(input);
	  //  boolean isFulltime=Boolean.valueOf(strArray[2]);
	    Object result="";
	    if(!isCourseCodeNumber){
	    	output.setOutput("Your input should be a a course code'");
	    	output.setState(DEREGISTERORDROPCOURSE);
	
	    }else{
	    	result=StudentTabel.getInstance().deregisterOrDrop(courseCode);
	    	if(result.equals(true)){
	    		output.setOutput("Success!");
	    	}else{
	    		output.setOutput("Course cannot be Dropped");
	    	}
	    	output.setState(STUDENT);
	    }
		return output;	
	
		 
	}

	
	public Output deregisterCourse(String input) {
		
			Output output=new Output("",0);
		    // strArray = input.split(",");
			boolean isCourseCodeNumber = numberOrNot(input);
		    //boolean email=strArray[0].contains("@");
		    int courseCode = Integer.parseInt(input);
		  //  boolean isFulltime=Boolean.valueOf(strArray[2]);
		    Object result="";
		    if(!isCourseCodeNumber){
		    	output.setOutput("Your input should be a a course code'");
		    	output.setState(DEREGISTERCOURSE);
		
		    }else{
		    	result=StudentTabel.getInstance().deregisterCourse(courseCode);
		    	if(result.equals(true)){
		    		output.setOutput("Success!");
		    	}else{
		    		output.setOutput("Course is not existant");
		    	}
		    	output.setState(STUDENT);
		    }
			return output;	
		
		
		
	
	}
	
	public Output dropCourse(String input) {
		// TODO Auto-generated method stub
		Output output=new Output("",0);
	    // strArray = input.split(",");
		boolean isCourseCodeNumber = numberOrNot(input);
	    //boolean email=strArray[0].contains("@");
	    int courseCode = Integer.parseInt(input);
	  //  boolean isFulltime=Boolean.valueOf(strArray[2]);
	    Object result="";
	    if(!isCourseCodeNumber){
	    	output.setOutput("Your input should be a a course code'");
	    	output.setState(DROPCOURSE);
	
	    }else{
	    	result=StudentTabel.getInstance().dropCourse(courseCode);
	    	if(result.equals(true)){
	    		output.setOutput("Success!");
	    	}else{
	    		output.setOutput("Course is not existant");
	    	}
	    	output.setState(STUDENT);
	    }
		return output;	
	
	}
	
	
	
	boolean numberOrNot(String input)
	{
	    try
	    {
	        Integer.parseInt(input);
	    }
	    catch(NumberFormatException ex)
	    {
	        return false;
	    }
	    return true;
	}

	public Output checkDueCreateCourse() {
		// TODO Auto-generated method stub
		Output output=new Output("",0);
	    // strArray = input.split(",");
		
	  //  boolean isFulltime=Boolean.valueOf(strArray[2]);
	    Object result="";
	    
	    if (JavaReminder.day>UniversityTable.COURSECREACTIONDEADLINE){
    		output.setOutput("Failed, Deadline is Passed!");
	    	output.setState(OVERDUECOURSECREATION);
		}

		return output;	
	}

	public Output startStudentRegistration() {

				Output output=new Output("",0);
			    // strArray = input.split(",");
				
			  //  boolean isFulltime=Boolean.valueOf(strArray[2]);
			    Object result="";
			    
			    if (JavaReminder.day>=UniversityTable.COURSEREGISTRATIONSTART &&JavaReminder.day<UniversityTable.LASTDAYDROP){
		    		output.setOutput("Now you can register!");
			    	output.setState(STUDENTSTARTREGISTRATION);
				} 

				return output;	
	}
	

	
	
	public Output checkTermStart() {

		Output output=new Output("",0);
	    // strArray = input.split(",");
		
	  //  boolean isFulltime=Boolean.valueOf(strArray[2]);
	    Object result="";
	    
	    if (JavaReminder.day>=UniversityTable.TERMSTARTDAY){
    		output.setOutput("Term starts!");
	    	output.setState(TERMSTART);
		}

		return output;	
}
	
	public Output startTheTerm(){

		Output output=new Output("",0);
	    // strArray = input.split(",");
		
	  //  boolean isFulltime=Boolean.valueOf(strArray[2]);
	    Object result="";
	   
	  while (JavaReminder.day!=UniversityTable.TERMENDDAY) {
		  
		   UniversityTable.getInstance().termStarts();
		   output.setOutput("Term is still running!");
	   		output.setState(TERMSTART);

	  }  
	   
	   if(JavaReminder.day>=UniversityTable.TERMENDDAY) {
		   output.setOutput("Term is now Over!");
	   		output.setState(TERMEND);
	   } else {
		   output.setOutput("Term is still running!");
	   		output.setState(TERMSTART);
	   }
	 
		return output;	
}


}

	
	

		
		
	
	
	
	
	


