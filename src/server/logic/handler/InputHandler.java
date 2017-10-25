package server.logic.handler;

import server.logic.handler.model.Output;
import server.logic.handler.model.ServerOutput;


public class InputHandler {
	public static final int WAITING = 0;
	public static final int FINISHWAITING=1;
    public static final int CLERK = 2;
    public static final int STUDENT = 3;
    public static final int CREATEUSER=4;  //createCourse
    public static final int CREATECOURSE=5;
    public static final int CREATESTUDENT=6; // register student
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

    OutputHandler outputHandler=new OutputHandler();


	public ServerOutput processInput(String input, int state) {
	
		
		 String output = "";
		 Output o = new Output("",0);
		 ServerOutput oo = new ServerOutput(output,o.getState());
	     o = outputHandler.checkTermStart();
	     /*
	     	if (o.getState()==TERMSTART){
	     		o=outputHandler.startTheTerm();
	     		output = o.getOutput();
	     		state = o.getState();
	     		oo.setOutput(output);
	     		oo.setState(state);
	     	}*/
	     	if (state == WAITING) {
	        	output = "Who Are you?Clerk or Student?";
	            state = FINISHWAITING;
	            oo.setOutput(output);
	            oo.setState(state);
	         }
	      
	        else if (state == FINISHWAITING) {
	            if (input.equalsIgnoreCase("clerk")) {
	            	o = outputHandler.clerkLogin(input);
	            	output= o.getOutput();
	            	state=o.getState();
	                oo.setOutput(output);
		            oo.setState(state);
	            } else if (input.equalsIgnoreCase("student")) {   
	            	output = "Please Input Your Student Number'";
	            	state=STUDENTLOGIN;
	            	oo.setOutput(output);
		            oo.setState(state);
	            
	            } else {
	            	output = "Who Are you?Clerk or User?";
	            	state = FINISHWAITING;
	            	oo.setOutput(output);
		            oo.setState(state);
	            	}
	            }
	        
	        else if(state == STUDENTLOGIN){
	        	o=outputHandler.studentLogin(input);
        		output=o.getOutput();
        		state=o.getState();
        		oo.setOutput(output);
	            oo.setState(state);
	        	}
	        
	        else if(state == CLERK){
        		o = outputHandler.checkDueCreateCourse();
        		if(o.getState()!=0){
        			output=o.getOutput();
            		state=o.getState();
            		oo.setOutput(output);
    	            oo.setState(state);	
        		}else if (input.equalsIgnoreCase("create course")) {  		
	        		output = "Please Input User Info:'course title,cap size'";
	        		state=CREATECOURSE;
	        		oo.setOutput(output);
	        		oo.setState(state);	
	        	} else if (input.equalsIgnoreCase("create student")){
	        		output = "Please Input Student Info:'name,age,True/False(is full time?)'";
	            	state=CREATESTUDENT;
	            	oo.setOutput(output);
		            oo.setState(state);	
	        	}else if (input.equalsIgnoreCase("register student")){
	        		output = "Please Input Student# and course code Info:"
	        				+ "'student number,course code'";
	            	state=REGISTERSTUDENT;
	            	oo.setOutput(output);
		            oo.setState(state);
	        		
	        	}else if (input.equalsIgnoreCase("cancel course")){
	        		output = "Please Input course code :";
	            	state=CANCELCOURSE;
	            	oo.setOutput(output);
		            oo.setState(state);
	        	}else if (input.equalsIgnoreCase("destroy course")){
	        		output = "Please Input course code :";
	            	state=DESTROYCOURSE;
	            	oo.setOutput(output);
		            oo.setState(state);
	        	}else{
	            	output = "Please select from the menu.Menu:Create Student/Course, Register Student"
					+ ", Cancel/Destroy course";
	                state = CLERK;
	                oo.setOutput(output);
		            oo.setState(state);
	             }
	        	}
	        else if(state ==OVERDUECOURSECREATION) {
	        	output = "Please select from the menu.Menu:Create Student/Course, Register Student"
						+ ", Cancel/Destroy course";
		                state = CLERK;
		                oo.setOutput(output);
			            oo.setState(state);
	        }
	        
	        else if(state==CREATECOURSE){
	        		o=outputHandler.createCourse(input);
	        		output=o.getOutput();
	        		state=o.getState();
	        		oo.setOutput(output);
		            oo.setState(state);
	  
	        } else if(state==CREATESTUDENT){
	    		o=outputHandler.createStudent(input);
	    		output=o.getOutput();
	    		state=o.getState();
	    		oo.setOutput(output);
	            oo.setState(state);
	
	        } else if(state==REGISTERSTUDENT){
	    		o=outputHandler.registerStudentoCourse(input);
	    		output=o.getOutput();
	    		state=o.getState();
	    		oo.setOutput(output);
	            oo.setState(state);
	        }else if(state==CANCELCOURSE){
	    		o=outputHandler.cancelCourse(input);
	    		output=o.getOutput();
	    		state=o.getState();
	    		oo.setOutput(output);
	            oo.setState(state);
	        }else if(state==DESTROYCOURSE){
	    		o=outputHandler.destroyCourse(input);
	    		output=o.getOutput();
	    		state=o.getState();
	    		oo.setOutput(output);
	            oo.setState(state);
	        }
	 
	        
	        else if(state == STUDENT){
        		o = outputHandler.startStudentRegistration();
        		if(o.getState()==STUDENTSTARTREGISTRATION){
        			if (input.equalsIgnoreCase("Register Course")) {
    	            	output = "Please Input course code :";
    	            	state=REGISTERTOCOURSE;
    	            	oo.setOutput(output);
    		            oo.setState(state);
    	        	} else if (input.equalsIgnoreCase("drop course")){
    	        		output = "Please Input course code :";
    	            	state=DROPCOURSE;
    	            	oo.setOutput(output);
    		            oo.setState(state);
    	        		
    	        	}else if (input.equalsIgnoreCase("deregister course")){
    	        		output = "Please Input course code :";
    	            	state=DEREGISTERCOURSE;
    	            	oo.setOutput(output);
    		            oo.setState(state);
    	        		
    	        	}else if (input.equalsIgnoreCase("cancel course")){
    	        		output = "Please Input course code :";
    	            	state=CANCELCOURSE;
    	            	oo.setOutput(output);
    		            oo.setState(state);
    	        	}else{
    	            	output = "Please select from the menu.Menu:register/drop/deregister course"
    	    					+ ", Cancel/Destroy course";
    	    	        state = STUDENT;
    	    	        oo.setOutput(output);
    	    		    oo.setState(state);
    	    	      }
        		}else{
	            	output = "Registration is Not Open YET!";
	                state = STUDENT;
	                oo.setOutput(output);
		            oo.setState(state);
	             }
	        	
	        	}
	        else if(state==REGISTERTOCOURSE){
        		o=outputHandler.registerCourse(input);
        		output=o.getOutput();
        		state=o.getState();
        		oo.setOutput(output);
	            oo.setState(state);
  
	        }  else if(state==DEREGISTERCOURSE){
        		o=outputHandler.deregisterCourse(input);
        		output=o.getOutput();
        		state=o.getState();
        		oo.setOutput(output);
	            oo.setState(state);
  
	        } 
	       
	        
	     
	  
	            
	            
		            /*     
	            } /else if (input.equalsIgnoreCase("user")) {
	            	output="Please Input Username and Password:'username,password'";
	            	state=USERLOGIN;
	                oo.setOutput(output);
		            oo.setState(state);
	            }else{
	            	output = "Who Are you?Clerk or User?";
	            	state = FINISHWAITING;
	            	oo.setOutput(output);
		            oo.setState(state);
	            }
	        }else if(state==CLERKLOGIN){
	        	o=outputHandler.clerkLogin(input);
        		output=o.getOutput();
        		state=o.getState();
        		oo.setOutput(output);
	            oo.setState(state);
	        }else if(state==USERLOGIN){
	        	o=outputHandler.userLogin(input);
        		output=o.getOutput();
        		state=o.getState();
        		oo.setOutput(output);
	            oo.setState(state);
	        }else if (state==CLERK){
	        	if (input.equalsIgnoreCase("create user")) {
	            	output = "Please Input User Info:'username,password'";
	            	state=CREATEUSER;
	            	oo.setOutput(output);
		            oo.setState(state);
	            }else if (input.equalsIgnoreCase("create title")) {
	            	output = "Please Input Title Info:'ISBN,title'";
	            	state=CREATETITLE;
	            	oo.setOutput(output);
		            oo.setState(state);
	            }else if (input.equalsIgnoreCase("create item")) {
		            output = "Please Input Item Info:'ISBN'";
		            state=CREATEITEM;
		            oo.setOutput(output);
		            oo.setState(state);
	            }else if (input.equalsIgnoreCase("delete user")) {
	            	output = "Please Input User Info:'useremail'";
	            	state=DELETEUSER;
	            	oo.setOutput(output);
		            oo.setState(state);
	            }else if (input.equalsIgnoreCase("delete title")) {
		            output = "Please Input Title Info:'ISBN'";
		            state=DELETETITLE;
		            oo.setOutput(output);
		            oo.setState(state);
	            }else if (input.equalsIgnoreCase("delete item")) {
	            	output = "Please Input Item Info:'ISBN,copynumber'";
	            	state=DELETEITEM;
	            	oo.setOutput(output);
		            oo.setState(state);
	            }else if(input.equalsIgnoreCase("log out")){
	            	output = "Successfully Log Out!";
	                state = WAITING;
	                oo.setOutput(output);
		            oo.setState(state);
	            }else if(input.equalsIgnoreCase("main menu")){
	        		output = "What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.";
	                state = CLERK;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else{
	            	output = "Please select from the menu.Menu:Create User/Title/Item,Delete User/Title/Item.";
	                state = CLERK;
	                oo.setOutput(output);
		            oo.setState(state);
	            }
	        }else if (state==USER){
	        	if (input.equalsIgnoreCase("borrow")) {
	            	output = "Please Input User Info:'useremail,ISBN,copynumber'";
	            	state=BORROW;
	            	oo.setOutput(output);
		            oo.setState(state);
	            }else if (input.equalsIgnoreCase("renew")) {
	            	output = "Please Input Title Info:'useremail,ISBN,copynumber'";
	            	state=RENEW;
	            	oo.setOutput(output);
		            oo.setState(state);
	            }else if (input.equalsIgnoreCase("return")) {
		            output = "Please Input Item Info:'useremail,ISBN,copynumber'";
		            state=RETURN;
		            oo.setOutput(output);
		            oo.setState(state);
	            }else if (input.equalsIgnoreCase("pay fine")) {
	            	output = "Please Input User Info:'useremail'";
	            	state=PAYFINE;
	            	oo.setOutput(output);
		            oo.setState(state);
	            }else if(input.equalsIgnoreCase("log out")){
	            	output = "Successfully Log Out!";
	                state = WAITING;
	                oo.setOutput(output);
		            oo.setState(state);
	            }else if(input.equalsIgnoreCase("main menu")){
	        		output = "What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.";
	                state = USER;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else{
	            	output = "Please select from the menu.Menu:Borrow,Renew,Return,Pay Fine.";
	                state = USER;
	                oo.setOutput(output);
		            oo.setState(state);
	            }
	        	
	        }else if(state==CREATEUSER){
	        	if(input.equalsIgnoreCase("log out")){
	            	output = "Successfully Log Out!";
	                state = WAITING;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else if(input.equalsIgnoreCase("main menu")){
	        		output = "What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.";
	                state = CLERK;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else{
	        		o=outputHandler.createUser(input);
	        		output=o.getOutput();
	        		state=o.getState();
	        		oo.setOutput(output);
		            oo.setState(state);
	        	}
	        }else if(state==CREATETITLE){
	        	if(input.equalsIgnoreCase("log out")){
	            	output = "Successfully Log Out!";
	                state = WAITING;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else if(input.equalsIgnoreCase("main menu")){
	        		output = "What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.";
	                state = CLERK;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else{
	        		o=outputHandler.createTitle(input);
	        		output=o.getOutput();
	        		state=o.getState();
	        		oo.setOutput(output);
		            oo.setState(state);
	        	}
	        }else if(state==CREATEITEM){
	        	if(input.equalsIgnoreCase("log out")){
	            	output = "Successfully Log Out!";
	                state = WAITING;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else if(input.equalsIgnoreCase("main menu")){
	        		output = "What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.";
	                state = CLERK;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else{
	        		o=outputHandler.createItem(input);
	        		output=o.getOutput();
	        		state=o.getState();
	        		oo.setOutput(output);
		            oo.setState(state);
	        	}
	        }else if(state==DELETEUSER){
	        	if(input.equalsIgnoreCase("log out")){
	            	output = "Successfully Log Out!";
	                state = WAITING;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else if(input.equalsIgnoreCase("main menu")){
	        		output = "What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.";
	                state = CLERK;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else{
	        		o=outputHandler.deleteUser(input);
	        		output=o.getOutput();
	        		state=o.getState();
	        		oo.setOutput(output);
		            oo.setState(state);
	        	}
	        }else if(state==DELETETITLE){
	        	if(input.equalsIgnoreCase("log out")){
	            	output = "Successfully Log Out!";
	                state = WAITING;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else if(input.equalsIgnoreCase("main menu")){
	        		output = "What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.";
	                state = CLERK;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else{
	        		o=outputHandler.deleteTitle(input);
	        		output=o.getOutput();
	        		state=o.getState();
	        		oo.setOutput(output);
		            oo.setState(state);
	        	}
	        }else if(state==DELETEITEM){
	        	if(input.equalsIgnoreCase("log out")){
	            	output = "Successfully Log Out!";
	                state = WAITING;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else if(input.equalsIgnoreCase("main menu")){
	        		output = "What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.";
	                state = CLERK;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else{
	        		o=outputHandler.deleteItem(input);
	        		output=o.getOutput();
	        		state=o.getState();
	        		oo.setOutput(output);
		            oo.setState(state);
	        	}
	        }else if(state==BORROW){
	        	if(input.equalsIgnoreCase("log out")){
	            	output = "Successfully Log Out!";
	                state = WAITING;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else if(input.equalsIgnoreCase("main menu")){
	        		output = "What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.";
	                state = USER;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else{
	        		o=outputHandler.borrow(input);
	        		output=o.getOutput();
	        		state=o.getState();
	        		oo.setOutput(output);
		            oo.setState(state);
	        	}
	        }else if(state==RENEW){
	        	if(input.equalsIgnoreCase("log out")){
	            	output = "Successfully Log Out!";
	                state = WAITING;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else if(input.equalsIgnoreCase("main menu")){
	        		output = "What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.";
	                state = USER;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else{
	        		o=outputHandler.renew(input);
	        		output=o.getOutput();
	        		state=o.getState();
	        		oo.setOutput(output);
		            oo.setState(state);
	        	}
	        }else if(state==RETURN){
	        	if(input.equalsIgnoreCase("log out")){
	            	output = "Successfully Log Out!";
	                state = WAITING;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else if(input.equalsIgnoreCase("main menu")){
	        		output = "What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.";
	                state = USER;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else{
	        		o=outputHandler.returnBook(input);
	        		output=o.getOutput();
	        		state=o.getState();
	        		oo.setOutput(output);
		            oo.setState(state);
	        	}
	        }else if(state==PAYFINE){
	        	if(input.equalsIgnoreCase("log out")){
	            	output = "Successfully Log Out!";
	                state = WAITING;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else if(input.equalsIgnoreCase("main menu")){
	        		output = "What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.";
	                state = USER;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else{
	        		o=outputHandler.payFine(input);
	        		output=o.getOutput();
	        		state=o.getState();
	        		oo.setOutput(output);
		            oo.setState(state);
	        	}
	        	*/
	        return oo;

	        }
	


}
