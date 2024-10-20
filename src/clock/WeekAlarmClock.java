package clock;

import counter.*;

import java.util.Collection;

import alarm.*;
import counter.CircularCounter.Direction;
import time.*;

public class WeekAlarmClock implements AlarmClockType 
{
	private Counter7 days;
    private Counter24 hours;
    private Counter60 minutes;
    private Counter60 seconds;
    
    AlarmManager alarmmanager;
    
    public WeekAlarmClock()
    {
    	// using instances of other classes -> to build functionality
    	
    	days = new Counter7(Direction.INCREASING);
    	hours = new Counter24(Direction.INCREASING, days);
    	minutes = new Counter60(Direction.INCREASING, hours);
    	seconds = new Counter60(Direction.INCREASING, minutes);
    	
    	alarmmanager = new AlarmManager();
    }
    
    public void tickTack()
    {
        seconds.count();
        System.out.println(getTime().toString());
        alarmmanager.checkForAlarm(getTime());
    }
    
    public void setTime(TimeType time)
    {
    	// convert TimeType to int, set it into SettableCounter class
    	
    	seconds.setCount(time.getSecond());
    	minutes.setCount(time.getMinute());
    	hours.setCount(time.getHour());
    	days.setCount(time.getDay());
    }
    
    public void addAlarm(AlarmType larm)
    {
    	//AlarmManagers method takes of the object AlarmType
    	alarmmanager.addAlarm(larm);
    }
    
    public void removeAlarm(AlarmType alarm)
    {
    	alarmmanager.removeAlarm(alarm);
    }
    
    public void removeAllAlarms()
    {
    	alarmmanager.removeAllAlarms();
    }
    
    public Collection<AlarmType> getAlarms()
    {
    	return alarmmanager.getAlarms();
    }
    
    public TimeType getTime()
    {
    	return new Time(days.getCount(), hours.getCount(), minutes.getCount(), seconds.getCount());
    	
    }
    
    public String toString()
    {
    	return String.format("%03d %02d:%02d:%02d" ,days.getCount(), hours, minutes, seconds);
    }
    
    
}
