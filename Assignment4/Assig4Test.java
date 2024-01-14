// CMPINF 0401 Fall 2023
// Main program for Assignment 4
// This program should compile and execute using your ArrayCounter and
// LinkedCounter classes and the output should match that shown in file
// a4out.txt.
// Required files for this program are:
// 	 CountInterface.java (given to you)
// 	 ArrayCounter.java (you must write)
// 	 LinkedCounter.java (you must write)

public class Assig4Test
{
	public static void main(String [] args)
	{
		// ArrayCounter constructor contains 2 arguments.  The first is the
		// base, or radix of the count.  The second is the initial maximum
		// number of digits.  LinkedCounter always is sized precisely, so it
		// has only the radix in the constructor.  For both classes, you may
		// assume that the value for radix will be <= 10.
		CountInterface C1 = new ArrayCounter(8, 3);
		CountInterface C2 = new LinkedCounter(10);
		
		// See comments in method showInfo() below.
		showInfo(C1);
		showInfo(C2);
		System.out.println();
		
		// As the count increases more digits will be needed.  For ArrayCounter
		// this may require resizing of the array.  See the output.
		for (int i = 1; i <= 499; i++)
		{
			C1.increment();
			C2.increment();
		}
		
		// Note that the array in the ArrayCounter may be larger than the
		// actual number of digits being used.  However, only the necessary
		// digits should be shown in the toString() method.
		showInfo(C1);
		showInfo(C2);
		System.out.println();
		
		C1.increment();
		C2.increment();
		
		showInfo(C1);
		showInfo(C2);
		System.out.println();
		
		// The decrement() method may decrease the number of digits, since
		// there should be no leading zeros in the output.  Make sure you
		// handle this correctly for both of the classes.
		C1.decrement();
		C2.decrement();
		
		showInfo(C1);
		showInfo(C2);
		System.out.println();
		
		for (int i = 1; i <= 511; i++)
		{
			C1.decrement();
			C2.decrement();
		}
		
		showInfo(C1);
		showInfo(C2);
		System.out.println();
		
		// If the counter value is 0 the decrement() method should return
		// an error.  Make sure you handle this case also.
		C1.decrement();
		C2.decrement();
		System.out.println();
		
		// Trying some other instances, using an array of CounterInterface
		// to store the objects.  Note that this is using polymorphism!
		CountInterface [] A = new CountInterface[4];
		A[0] = new ArrayCounter(4, 3);
		A[1] = new LinkedCounter(4);
		A[2] = new ArrayCounter(2, 8);
		A[3] = new LinkedCounter(2);
		for (int i = 1; i <= 255; i++)
		{
			for (int j = 0; j < A.length; j++)
				A[j].increment();	
		}
		for (int j = 0; j < A.length; j++)
		{
			showInfo(A[j]);
		}
		System.out.println();
		for (int j = 0; j < A.length; j++)
		{
			A[j].increment();
			A[j].increment();
			A[j].increment();	
		}		
		for (int j = 0; j < A.length; j++)
		{
			showInfo(A[j]);
		}
		System.out.println();
		
		CountInterface [] B = new CountInterface[4];
		// Testing copy constructors.  First we test ArrayCounter then we test
		// LinkedCounter.
	
		for (int j = 0; j < A.length; j++)
		{
			B[j] = new ArrayCounter(A[j]);
		}
		for (int j = 0; j < A.length; j++)
		{
			showInfo(B[j]);
		}
		System.out.println();
		
		for (int j = 0; j < A.length; j++)
		{
			B[j] = new LinkedCounter(A[j]);
		}
		for (int j = 0; j < A.length; j++)
		{
			showInfo(B[j]);
		}	
		System.out.println();
		
		// One more test to show arbitrary number of digits
		C1 = new ArrayCounter(2, 1);
		C2 = new LinkedCounter(2);
		for (int i = 0; i < 1073741829; i++)
		{
			C1.increment();
			C2.increment();
		}
		showInfo(C1);
		showInfo(C2);
	}
	
	// Print out some information for the argument counter.  Note that the
	// toString() method should return only the digits in the CountInterface --
	// nothing else.  The remaining information shown is generated via other
	// method calls.  Note the getClass() method.  This is a method defined in
	// class Object and inherited by all other classes.  It simply returns the
	// class of the object from which it is called.
	public static void showInfo(CountInterface counter)
	{
		System.out.print(counter.toString() + " R:" + counter.radix());
		System.out.println(" D:" + counter.digits() + " " + counter.getClass());
	}
}
