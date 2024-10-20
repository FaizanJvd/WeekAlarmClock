package counter;

//Interface can also inherit another interface
/*when implememntimg e'settable countertype in any class, you have to
 * impement the methods of both interfaces, */

public interface SettableCounterType extends CounterType
  {
	
  public void setCount(int value);
  
  }
