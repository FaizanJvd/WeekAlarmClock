package counter;

public class SettableCounter extends CircularCounter implements SettableCounterType {
	
	public SettableCounter(int value, Direction direction, CounterType next) {
		super(value, direction, next);
	}
	
	//Make sure only correct input values (setting the time is correct), 
	//we do not set the clock to - 1000, what kind of clock time is that? UNALLOWED!
	public void setCount(int value) {
		if(value <= MAX_NR_OF_COUNTS && value >= 0) {
			currentCount = value;
		}
		
	}
}
