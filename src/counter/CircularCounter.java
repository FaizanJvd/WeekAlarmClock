package counter;

/* Abstract means we can not create instance of the class, 
it is just a drawing with the varibles it should have*/

public abstract class CircularCounter implements CounterType {

	/*
	 * protected can only be reached from the instance of the class it self and the
	 * subclass that extends this class
	 */
	public enum Direction {
		INCREASING, DECREASING
	};
	
	/* Modifier protected will make it only accescible by the package (meaning class itself
	and the subclasses of the class*/
	protected int currentCount;
	protected boolean isPaused;
	protected final int MAX_NR_OF_COUNTS;
	protected Direction direction;

	// Another counter connected to this one
	// Second, minute and hour counter,
	protected CounterType nextCounter;

	// Constructor to instantiate object of class
	public CircularCounter(int maxNrOfCounts, Direction direction, CounterType nextCounter) {
		this.direction = direction;
		this.nextCounter = nextCounter;
		// Fixa ej rimliga inputvärden
		// Max number of counts you want to do, Max 100 counts, you can not set
		// max of counts < 2, if you did, it will automatcaly set the max to 0.
		if (maxNrOfCounts < 2)
			this.MAX_NR_OF_COUNTS = 0;
		else
			this.MAX_NR_OF_COUNTS = maxNrOfCounts;

		// Om det är en nedåträknare, börja räkna från högsta värde istället för från
		// noll
		// If direction is decreasing, count down 10 - 9 - 8 - 3- 0...
		if (this.direction == Direction.DECREASING && this.MAX_NR_OF_COUNTS > 0)
			currentCount = MAX_NR_OF_COUNTS - 1;
	}

// Override means that there are multiple methods with the same name in classes 
	// in the same package or extends each other, but each method depending on the
	// class
	// Can have a behavior different to the method with the exact same name in the
	// other classm
	// It depends which method you are calling, and @Override is polymorphism, which
	// means
	// that the program can have different behaviors,
	// @Override is directly for the compiler, we are telling the compiler that we
	// are

	/*
	 * implementing the method of superclass or the interface which it is below,
	 * when we write Override, Compilator helps us to find bugsm means we are
	 * overwriting the methds in superclass or interface.
	 */

	@Override
	public int getCount() {
		return currentCount;
	}

//Starta om räknare från början
	@Override
	public void reset() {
		currentCount = 0;
	}

	@Override
	public void pause() {
		isPaused = true;
	}

	// Används för att häva paus, Used to stop the pause
	@Override
	public void resume() {
		isPaused = false;
	}

	@Override
	public void count() {
		if (!isPaused && this.MAX_NR_OF_COUNTS > 0) 
		{
			if (direction == Direction.INCREASING) {
				currentCount++;
				// Kolla om räknaren har räknat ett helt varv
				if (currentCount >= MAX_NR_OF_COUNTS) {

					// Se till att räknaren börjar om från noll
					// Om kopplad till en annan räknare (nextCounter)…
					currentCount = 0;
					System.out.println("CircCounter Lab2");
					System.out.println(nextCounter);
					if (nextCounter != null)
						// …räkna upp den andra räknaren ett steg.
						// Count the other counter up one unit
						System.out.println(currentCount);
						nextCounter.count();
				}
			}
			else if (direction == Direction.DECREASING) {
				// Flera kod-rader saknas här
				// Many rows of code missing here
				if(currentCount >= 0) {
					currentCount--;
				} else {
					currentCount = MAX_NR_OF_COUNTS-1;
					
					if(nextCounter!=null) {
						nextCounter.count();
					}
				}
			}
		} 
		
	}

    @Override
    public String toString()
    {
        return "Clock is: "+ direction+", " + "Time: "+currentCount+" " + "is paused? "+isPaused;
    }
}
