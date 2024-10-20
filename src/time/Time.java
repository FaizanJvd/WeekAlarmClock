package time;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Time implements TimeType
  {
	//variables that hold second, minute, hour and the day
  private int second, minute, hour, day;
  // for day we have an array of strings. static is when we do not need to instantiate an
  // object. we want to use that object outside of the class without having to instantiate it.
  // we also use static for memory efficiency, say we created 100 instances of time class, 
  // only one, dayName String is created by that instance in the memory
  private static final String [] dayNames = {"Mon","Tue","Wed","Thu","Fri","Sat","Sun"};
  // to see if we have an alarm for a specific day
  private boolean hasDay = false;
  
  // constructor to instantiate variables
  public Time(int hour, int minute, int second)
    {
    setHour(hour);
    setMinute(minute);
    setSecond(second);    
    }
  //overloading a constructor that takes time and also day.
  public Time(int day, int hour, int minute, int second)
    {
    this(hour,minute,second);
    setDay(day);
    }
  
  //Constructor to set only time
  public Time(String time)
    {
	  
    // kolla format Hh:Mm:Ss (H: 0-2, h: 0-9, M: 0-5, m: 0-9, S: 0-5, s: 0-9) 
    Pattern plainPattern = Pattern.compile("^(2[0-3]|[01]?[0-9]):([0-5]?[0-9]):([0-5]?[0-9])$");
   
    // Does user input match required regular expression
    Matcher plainMatcher = plainPattern.matcher(time);
    // kolla format Day Hh:Mm:Ss (Day: Mon, Tue, ..., H: 0-2, h: 0-9, M: 0-5, m: 0-9, S: 0-5, s: 0-9) 
    
    // pattern for when you provide day.
    Pattern weekDayPattern = Pattern.compile("^(Mon|Tue|Wed|Thu|Fri|Sat|Sun) (2[0-3]|[01]?[0-9]):([0-5]?[0-9]):([0-5]?[0-9])$");
    //Does day and time created correspond to the regex requirements
    Matcher weekDayMatcher = weekDayPattern.matcher(time);
    
    if(weekDayMatcher.find())
      {
    	// sort day, hours, minutes and seconds to a String array.
      String [] parts = time.split(" |:");
      
      //Verifying the day inputted exists in the final String array dayNames
      for(int day=0; day < dayNames.length; day++)
        if(dayNames[day].equals(parts[0]))
          setDay(day);
      setHour(Integer.parseInt(parts[1]));
      setMinute(Integer.parseInt(parts[2]));
      setSecond(Integer.parseInt(parts[3]));
      }
    //Time without day
    else if(plainMatcher.find())
      {
      String [] parts = time.split(":");
      setHour(Integer.parseInt(parts[0]));
      setMinute(Integer.parseInt(parts[1]));
      setSecond(Integer.parseInt(parts[2]));
      }
    else
      throw new RuntimeException("Illegal time format: " + time);
    }

  public int getSecond()
    {
    return second;
    }

  public void setSecond(int second)
    {
    this.second = second%60;
    }

  public int getMinute()
    {
    return minute;
    }

  public void setMinute(int minute)
    {
    this.minute = minute%60;
    }

  public int getHour()
    {
    return hour;
    }

  public void setHour(int hour)
    {
    this.hour = hour%24;
    }
  
  public boolean hasDay()
    {
    return hasDay;
    }

  public int getDay()
    {
    return day;
    }

  public void setDay(int day)
    {
	  // this is remainder %, take the left over value,
    this.day = day%7;
    hasDay = true;
    }
  
  public String toString()
    {
    char [] array = new char[12];
    // Dividing the String to chars, Mon consists of M, O and N, etc
    array[0] = dayNames[day].charAt(0);
    array[1] = dayNames[day].charAt(1);
    array[2] = dayNames[day].charAt(2);
    array[3] = ' ';
    //adding time, / divide by 10, for example 10:35:42 seconds, when alarm is set, it is
    // setting the value according to 24 hours, if it is 10 pm, in 24 hours it is 22:00
    array[4] = (char)('0' + hour/10);
    array[5] = (char)('0' + hour%10);
    array[6] = ':';
    array[7] = (char)('0' + minute/10);
    array[8] = (char)('0' + minute%10);
    array[9] = ':';
    array[10]= (char)('0' + second/10);
    array[11]= (char)('0' + second%10);
    return new String(array);

//    StringBuffer buffer = new StringBuffer("            ");
//    buffer.setCharAt(0, dayNames[day].charAt(0));
//    buffer.setCharAt(1, dayNames[day].charAt(1));
//    buffer.setCharAt(2, dayNames[day].charAt(2));
//    buffer.setCharAt(3, ' ');
//    buffer.setCharAt(4, (char)('0' + hour/10));
//    buffer.setCharAt(5, (char)('0' + hour%10));
//    buffer.setCharAt(6, ':');
//    buffer.setCharAt(7, (char)('0' + minute/10));
//    buffer.setCharAt(8, (char)('0' + minute%10));
//    buffer.setCharAt(9, ':');
//    buffer.setCharAt(10, (char)('0' + second/10));
//    buffer.setCharAt(11, (char)('0' + second%10));
//    return buffer.toString();
    //return String.format("%s %02d:%02d:%02d", dayNames[day], hour, minute, second);
    }
  }
