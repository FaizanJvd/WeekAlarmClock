package alarm;

import time.TimeType;

public class Alarm implements AlarmType{
	
	private boolean active;
	  private TimeType time;
	  
	  public Alarm(TimeType time)
	    {
	    this.time = time;
	    active = true;
	    }
	  
	  @Override
	  public void setActive(boolean active)
	    {
	        this.active = active;
	    }

	  @Override
	  public boolean isActive()
	    {
	        return active;
	    }

	  @Override
	  public TimeType getTime()
	    {
	        return time;
	    }

	  @Override
	  public void doAlarm()
	    {
	    if(active)
	        {
	    	    System.out.println("Alarm activated");
	    	    System.out.println("TaTaTaTaTaTaTaTaTaTaTaTaTaTaTaTaTaTaTaTa");
	        }
	      //.... skriv ut notis om att alarm är triggat 
	    }
	  
	  public String toString()
	    {
		  return time.toString();
		  // delegate to the times toString() method
	    //... delegera till times toString()-metod
	    }
}
