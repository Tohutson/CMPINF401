/* Trey Hutson
CMPINF 0401 Fall 2023 Tu Thu 1pm
Assignment 4 LinkedCounter class
Counter with linked list based data
*/

import java.lang.*;

public class LinkedCounter implements CountInterface
{	
	//Inner Node class for linked list
	private class Node 
	{
		private int data;
		private Node next; //Link to next node
		
		// Create a new Node with the specified data and null next field.
		public Node(int val)
		{
			data = val;
			next = null;
		}
		
		// Create a new Node with the specified data and the specified next field
		public Node(int val, Node nextNode)
		{
			data = val;
			next = nextNode;
		}

	}

	int radix;
	Node front;

	//Create LinkedCounter object with rad as radix, front to track front of the list
	public LinkedCounter(int rad)
	{
		radix = rad;
		front = null;
	}

	//Copy constructor
	//Works with any CountInterface object
	public LinkedCounter(CountInterface orig)
	{
		radix = orig.radix(); //Copy radix from orig
		String numbers = orig.toString(); //Get numbers as string from orig object

		front = new Node(Integer.parseInt(numbers.substring(numbers.length() - 1, numbers.length()))); //Set front to last digit from numbers string
		Node current = front; //Track current node

		//Link a new node for each number in the numbers string
		for (int i = numbers.length() - 2; i >= 0; i--)
		{
			current.next = new Node(Integer.parseInt(numbers.substring(i,i+1))); 
			current = current.next;
		}
	}

	//Increase counter by 1
	public void increment()
	{
		Node place; //Track current place

		//If front is null, create new node equal to 0
		if (front == null) 
		{
			front = new Node(0);
		}

		place = front; //Current place equal to front

		place.data++; //Increment current place
		
		//While current place is past maximum value (equals radix)
		while (place.data == radix)
		{
			place.data = 0; //Set current place equal to 0
			if (place.next == null) //Check if there is a node for next place
			{
				place.next = new Node(0);
			}
			place = place.next; //Move current place to next place
			place.data++; //Increment current place
		}
	}

	//Decrease counter by 1
	public void decrement()
	{
		//If front is null or counter represents 0
		if (front == null || (digits() == 1 && front.data == 0))
		{
			System.out.println("Underflow Error: Cannot decrement 0"); //Error message
		}
		else 
		{
			Node place = front; //Set current place to front
			place.data--; //Decrement current place by 1

			//While current place is less than 0
			while (place.data < 0)
			{
				place.data = radix - 1; //Set current place to maximum value (radix-1)
				if (place.next.next == null && place.next.data - 1 == 0) //If next place will become a leading zero
				{
					place.next = null; //Remove next place
				}
				else
				{
					place = place.next; //Set current place to next place
					place.data--; //Decrement current place by 1		
				}
			}
		}
	}

	//Reset counter to 0
	public void reset()
	{
		front = null;
	}

	//Returns number of digits represented by counter
	public int digits()
	{
		//If front is null (empty)
		if (front == null)
		{
			return 1; //Counter represents 0, 1 digit
		}
		else 
		{
			Node place = front; //Track current place
			int count = 0; //Count

			//Count number of nodes
			while (place != null)
			{
				count++; //Add one node to count
				place = place.next; //Move current place by 1
			}
			return count; //Return number of digits
		}
	}

	//Return radix
	public int radix()
	{
		return radix; 
	}

	//Return current count as formatted string
	public String toString()
	{
		Node place = front; //Track current place
		StringBuilder S = new StringBuilder();

		//If list is empty, 0 is represented
		if (place == null)
		{
			S.append(0);
		}

		//Append numbers from each node in reverse order
		while (place != null)
		{
			S.append(place.data);
			place = place.next;
		}

		//Reverse string
		S.reverse();

		return S.toString(); //Return string
	}
}