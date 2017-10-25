package server.logic.model;

import java.util.Timer;
import java.util.TimerTask;

import server.logic.tables.UniversityTable;


	public class JavaReminder {

	    Timer timer;
	    public static int day=0;
	   
	    
	    
		public JavaReminder(int seconds) {
	        timer = new Timer();  //At this line a new Thread will be created
	        timer.schedule(new RemindTask(), seconds*1000); //delay in milliseconds
	    }
		  class RemindTask extends TimerTask {

		        @Override
		        public void run() {
		            day++;
		            System.out.println("day: "+day);
		            if(day>34){
		            	System.out.println("Term Starts");
			            UniversityTable.getInstance().termStarts();

		            }
		            timer.cancel(); //Not necessary because we call System.exit
		            //System.exit(0); //Stops the AWT thread (and everything else)
		        }
		    }
	}
