package counter;
/* Interface is like standardization between
 * classes, all classes that implements interface 
 * ,must have the same methods as those existing in
 * interface class.
 * 
 * Definition/variable inside the claass that immpeklemtns
 * that variable*/

/* in interface we only implement the name of the attribute and signature of the method*/
public interface CounterType {
    // Here we define the signature of the method, void does nothing. By default public, signature is returtype.
	// returtype is 
	
	/*
	 method signature (1st, 2nd, 3rd)
     1st: Access Level (public, private....)
     2nd: Return Type (Void, int ...)
     3rd: Name and Parameters of method 
     */
	public void count();
	public void pause();
	public void resume();
	public int getCount();
	public void reset();
	
	// We have built a bridge, with interface, Circular Counter implements it, methods in interface 
	// also exists in CircularCounter (abstractClass) and the classes that are subtypes of 
	// the abstract class. 
	
	/*Counter60 and 24*/
	
}
