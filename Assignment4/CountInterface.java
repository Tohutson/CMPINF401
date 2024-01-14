// CMPINF 0401 Fall 2023
// CountInterface for Assignment 4
// Note the methods below.  You must implement all of these methods
// in your ArrayCounter and LinkedCounter classes.  See more details
// in the Assignment 4 document.

public interface CountInterface
{
	public void increment();
	// Add 1 to the counter value, increasing the values of all affected
	// digits. This method may cause the number of digits to increase, if
	// the new value cannot be represented with the previous digits.
	
	public void decrement();
	// Remove one from the counter value, decreasing the values of all
	// affected digits.  This method may cause the number of digits to
	// decrease, if the leftmost digit would change from 1 to 0 (since
	// we will not show leading 0s).  If the value of the counter is 0
	// prior to this call, this method should output an error and not
	// change the counter value.
	
	public void reset();
	// Reset the counter back to its initial state (and value of 0). After
	// this call the counter should have only a single digit.

	public int digits();
	// Return the number of digits being represented by the CountInterface
	// This should NOT include any leading 0s.
	
	public int radix();
	// Return the radix value for this CountInterface.  For a given radix,
	// r, the digits in the CountInterface will have values from 0 to (r-1), 
	// inclusive.

	public String toString();
	// Return a String representation of the counter with the digits shown 
	// correctly.  It should contain no extra characters -- only the digits
	// in order.
}