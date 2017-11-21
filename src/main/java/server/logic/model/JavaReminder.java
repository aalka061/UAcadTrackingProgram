package main.java.server.logic.model;

import java.util.Timer;
import java.util.TimerTask;

import main.java.server.logic.tables.CourseTabel;
import main.java.server.logic.tables.UniversityTable;


	public class JavaReminder {

	    Timer timer;
	    public static int day=0;
	   
	    public static void setDay(int day) {
			JavaReminder.day = day;
		}
	    
		public JavaReminder(int seconds) {
	        timer = new Timer();  //At this line a new Thread will be created
	        timer.schedule(new RemindTask(), seconds*1000); //delay in milliseconds
	    }
		  class RemindTask extends TimerTask {

		        @Override
		        public void run() {
		            day++;
		            System.out.println("day: "+day);
		            if(day>34 && day<120){
		            	if (day==35) {		  		      
		            		System.out.println();
		            		System.out.println("---------------------------------------------------------------------");
		            		System.out.println("Term Starts");
		            		System.out.println("---------------------------------------------------------------------");

		            	}
			            UniversityTable.getInstance().termStarts();

		            } else if (day==120){
		            	System.out.println();
	            		System.out.println("---------------------------------------------------------------------");
	            		System.out.println("Term Ends");
	            		System.out.println("---------------------------------------------------------------------");
	            		System.out.println("Students Marks");

		            	CourseTabel.getInstance().termEnded();
		            }
		            timer.cancel(); //Not necessary because we call System.exit
		            //System.exit(0); //Stops the AWT thread (and everything else)
		        }
		    }
	}
