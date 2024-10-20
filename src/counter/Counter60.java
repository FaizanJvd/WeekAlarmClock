package counter;



public class Counter60 extends SettableCounter{
	
	// this keyword is to refer tothe class itself (Counter60), the instance which we are working with
	// it right now, this in this context keyword, is for constructor to Counter60 which
	// inherits some of attributes of CircularCounter
	// This means right here locally
	// This means this class, were we are sitting.
	
	// These are just supportive constructors... when we create a class, we can use these
	// 4 constructors in order to
	
	// Counter 60 is the handler class of the circular Counter,m becauswe Circuklar counter is abstract
	// but I can handle all the functionality of the circular class by instanciating the Counter60.
	
	/*default constructor without any connector*/
	public Counter60() {
		this(Direction.INCREASING, null); // vad anropas här? What is called here?
	}
	// To only connect a clock
	public Counter60(CounterType next) {
		this(Direction.INCREASING, next); // vad anropas här?
	}
	// direction to move, up or down
	public Counter60(Direction direction) {
		this(direction, null); // vad anropas här?
	}

	/*
	 * super is used to call constructor of parent class which we inherit,
	 we can access also variables and constructors of parent class without 
	 instanting the class, we can also write super.getCount, 
	 
	  Circular Counter is abstract, we can not create an instance of it,
	  however counter60 and counter24 is not, we can give 60 and 24 attributes and
	  behaviours of the abstract class, when we call the constructor of parent class,
	  we get a reference to it for Counter60 and Counter24.
	  */
	public Counter60(Direction direction, CounterType next) 
	{
		super(60, direction, next);
	}
}
