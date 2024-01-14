/* Trey Hutson
CMPINF 0401 Fall 2023 Tu Thu 1pm
Assignment 4 ArrayCounter class
Counter with array based data
*/

import java.lang.*;

public class ArrayCounter implements CountInterface
{
	int radix;
	int [] nums;
	int digits; 

	//Create Array Counter Object with rad as the radix and asize for the initial size of the array
	public ArrayCounter(int rad, int asize)
	{
		radix = rad;
		nums = new int[asize];
	}

	//Copy constructor
	//Works with any CountInterface object
	public ArrayCounter(CountInterface orig)
	{
		radix = orig.radix(); //Copy radix
		nums = new int[orig.digits()]; //Length of nums array equal to the number of digits from orig

		String numbers = orig.toString(); //Acquire numbers as string from orig

		//Copy in numbers from string into nums array
		for (int i = 0; i < nums.length; i++)
		{
			nums[i] = Integer.parseInt(numbers.substring(numbers.length() - (i+1), numbers.length() - i));
		}
	}

	//Increase counter by 1
	public void increment()
	{	
		//Check nums array is at least length 1
		if (nums.length > 0)
		{
			int place = 0; //Track place

			nums[place]++; //Increase 1's place by 1

			//While current place is past its maximum value (equals radix)
			while (nums[place] == radix)
			{
				nums[place] = 0; //Set current place back to 0

				if (place + 1 >= nums.length) //Check if next place is past the indexes of the current array
				{
					resize(); //Double array length
				}
				place++; //Move current place by 1
				nums[place]++; //Increase current place by 1
			}
		}
		else 
		{
			resize(); //Resize array to length of 1
			increment(); //Increment resized array
		}
	}

	//Decrease counter by 1
	public void decrement()
	{
		//Check if counter is equal to zero or nums array is length 0
		if ((digits() == 1 && nums[0] == 0) || nums.length < 1)
		{
			System.out.println("Underflow Error: Cannot decrement 0"); //Error message
		}
		else 
		{
			int place = 0; //Track place

			nums[place]--; //Decrease current place by 1

			//While current place is now less than zero
			while (nums[place] < 0)
			{
				nums[place] = radix - 1; //Set current place to maximum value (radix-1)
				place++; //Move current place by 1
				nums[place]--; //Decrement current place by 1
			}
		}
	}

	//Reset counter to zero
	public void reset()
	{
		nums = new int[nums.length];
	}

	//Returns number of digits represented by counter
	public int digits()
	{
		//Find first digit
		for (int i = nums.length - 1; i >= 0; i--)
		{
			if (nums[i] > 0) //First digit will be first number > 0
			{
				return i + 1; //Return index + 1 for number of digits represented
			}
		}

		return 1; //If no numbers are > 0, the counter must only be representing 0
	}

	//Returns radix
	public int radix()
	{
		return radix;
	}

	//Provides current count as formatted string
	public String toString()
	{
		StringBuilder S = new StringBuilder();

		//Check that nums array is at least length 1
		if (nums.length > 0)
		{
			//Append numbers from array, back to front
			for (int i = digits() - 1; i >= 0; i--)
			{
				S.append(nums[i]);
			}
		}
		else 
		{
			S.append("0");	//Append 0 if nums is length 0
		}

		return S.toString(); //Return string
	}

	//Double the size of the array
	protected void resize()
	{
		int [] temp;

		//Check that nums array is at least length 1
		if (nums.length > 0)
		{
			temp = new int[nums.length * 2]; //Set temp as double the length of nums

			//Copy numbers from nums to temp
			for (int i = 0; i < nums.length; i++)
			{
				temp[i] = nums[i];
			}
		}
		else 
		{
			temp = new int[1]; //Set temp equal to array of length 1
		}
		nums = temp; //Set nums equal to temp
	}
}