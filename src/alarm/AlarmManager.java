package alarm;
// import hashmap datastructure
import java.util.HashMap;
import time.TimeType;
//import also the collection, utility provided by java to create collection
import java.util.Collection;

// Collection and hashmap are connected together.
/*
  HashMap implements the map interface.
  it is used to store the key value pairs, 
  In hashmap We can't duplicate the keys. 
  Also provide the building methods to implement
  the hash map like:
  get
  size, 
  basic methods, put to insert etc.
  */


public class AlarmManager {
	// Storing time as a key and relevant objects as a value, 
	// relevant are Alarm object, String is key, AralrmType is value
	  private HashMap<String,AlarmType> map = new HashMap<String, AlarmType>();
	  
	  // we can implment all classes that uses that interface AlarmType
	  // becomes more generic, means general class, person is general
	  //class for all teachers, student and visitors. provide more 
	  //classes that implement that interface
	  public void addAlarm(AlarmType alarm)
	    {
		  // We are getting the time of the alarmType, it
		  // is in the form alarmType, we convert it to String.
		  // instance is the value against storing that key.
		  /*created hashmap, want to store, data of city and population
		   * Want to add city of Sweden and its population,
		   * 
		   * City, population Key is City, value is population,
		   * AlarmType is key, referencing that alarmObject is value.
		   * Set 5 alarms, 1 is 1 am, 2 , 5 am etc. each alarm, by creating 5 alamrs,
		   * you created 5 instances, alarm, 1, 5, 6,
		   * 
		   * we want to store these alarms inside hashmap.
		   * you have to store these 5 instance of that alarm inside the
		   * collection the hash map, How do we store 5 alarms inside hashmap,
		   * 
		   * hashmap is a key value pair, to store these 5 alarm instance, 
		   * alarm two, alarm five, you need to store a key value value for each alarm.*/
		  // put adds to the hashman, alarm.getTime().toString is the value, alarm is the key
	    map.put(alarm.getTime().toString(), alarm);
	        
	    }
	  
	  public void removeAlarm(AlarmType alarm)
	    {
		  // map is the refeernce to object hashmap, method,
		  /*we are getting the value, and removing it from inside
		    the hashmap, the key remains, but the value of the key
		    is removed. The key is String.*/
	    map.remove(alarm.getTime().toString());
	    }
	  
	  public void removeAllAlarms()
	   {
	    	// empty hashmap!
	    map.clear();
	   }
	   // returns all the values (only the values) of the hash map
	  public Collection<AlarmType> getAlarms()
	    {
		  return map.values();
	    }
	  
	  
	  // takes time, provided time object, time 1 am, time class object, it is in the form time. TimeType implements time
	  /* Want to convert it from time to String, we want to get that alarm which we set upon that time, once we
	   * do that, call method map.get, inside map.get, we put the time, which has been converted to string,
	   * String is also key of hashmap collection, map.get (take key that and find the value of that key) 
	   * if they find that key value, they return the timeType object, otherwise null.*/
	  public void checkForAlarm(TimeType time)
	    {
		  // map.get takes key, return the value (AlarmType).
		  // we have created alarm 6 am, convert 
		  /*for alarm, we take the inputted TimeType object
		   from package time.
		   
		   *If in our hashmap, we have an alarm, say 1:30, we
		   *convert it from time object, to time string, meaning
		   *String datatype, then we save it in AlarmType Alarm
		   *    if there was an alarm of that type, alarm will not
		   *    be empty, 
		   *    
		   *        if not, alarm will be null, 
		   *        if there existed an alarm at that type, and it was
		   *        active, start the alarm
		   *        
		   *        We ask is there such a key? if so, return the value
		   *        of the key, we save it to alarm, of AlarmType, */
	    AlarmType alarm = map.get(time.toString());
	    System.out.println(alarm);
	    
	    if(alarm != null && alarm.isActive()) 
	    {
	      alarm.doAlarm();
	    System.out.println("The alarmMethod called!");
	    }
	    }
}
