/* Trey Hutson
CMPINF 0401 Fall 2023 Tu Thu 1pm
Assignment 2 User class
Store file of statistics for user
*/

import java.util.*;
import java.io.*;
import java.lang.*;

class User
{
	private Scanner userFile;
	private FileWriter myWriter;

	private String name;
	private int rounds;
	private int words;
	private int points;

	//Create User object taking a username as arguement
	public User(String username)
	{
		name = username;
		try
		{
			File inFile = new File(username + ".txt");

			if (inFile.createNewFile())	//If file does not already exist, create new file, initialize instance variable to 0
			{
				rounds = 0;
				words = 0;
				points = 0;
				userFile = new Scanner(inFile);
			}
			else //If file already exists, read in statistics from file
			{
				userFile = new Scanner(inFile);
				rounds = userFile.nextInt();
				words = userFile.nextInt();
				points = userFile.nextInt();
			}

		}
		catch (IOException e)
		{
			System.out.println("Problem reading user file " + username);
		}
	}

	//Write updated statistics to file
	public void update()
	{
		try
		{
			myWriter = new FileWriter(name + ".txt");
			myWriter.write(rounds + " ");
			myWriter.write(words + " ");
			myWriter.write(points + " ");
			myWriter.close();
		}
		catch (IOException e)
		{
			System.out.println("Trouble updating file");
		}
	}

	//Increment round variable
	public void addRound()
	{
		rounds++;
	}

	//Add p points to points variable
	public void addPoints(int p)
	{
		points += p;
	}

	//Add w words to words variable
	public void addWords(int w)
	{
		words += w;
	}

	//Returns user's all-time statistics as a string
	public String toString()
	{
		StringBuilder S = new StringBuilder("Your all-time stats are: \n");
		S.append("Rounds played: " + rounds + "\n");
		S.append("Words found: " + words + "\n");
		S.append("Points: " + points + "\n");

		return S.toString();
	}


}