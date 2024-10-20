package counter;



public class Counter24 extends SettableCounter{
	
	public Counter24() {
		this(Direction.INCREASING, null); // vad anropas här?
	}

	public Counter24(CounterType next) {
		this(Direction.INCREASING, next); // vad anropas här?
	}

	public Counter24(Direction direction) {
		// When I call this, it calls the constructor that exists in
		// this same class, counter24, we call
		// this direction, null
		//which is 2 parameters, we do an overloading of constructors,
		// so it calls Counter24(Direction and CounterType) which has 
		// 2 paramerters
		this(direction, null); // vad anropas här?
	}
	
	// when this is called, we use the super, send it up one step to
	// parent class CircularCounter with the help of Super, we call
	/* the constructor there, instance an object of it, but the reference
	 * is  going to be Counter24.
	 * we acces circular counter (abstract cant instantiate it) we need
	 * a handler class which we use to use that CircularCounter class
	
	Counter24 handles CircularCounter*/
	
	public Counter24(Direction direction, CounterType next) {
		super(24, direction, next);
	}
	
}
