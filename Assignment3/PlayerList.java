// Trey Hutson 
// CMPINF 0401 Fall 2023 Tu Thur 1pm
// PlayerList class.
// This class represents a collection of Players -- a very simple database.  The
// collection can be represented in various ways but in Assignment 3 you are required
// to use a Java array of Player, as shown below.  You may add other instance variables
// but you may NOT use an ArrayList (or other collection) for your data.

// Note the import below.  java.io.* is needed for IOException and Files. 
import java.io.*;
import java.util.*;

public class PlayerList
{
	// Keep an array of Player objects to store the players within this PlayerList and
	// an int to maintain the logical size (number of actual players stored).
	// Also keep track of the file name associated with the PlayerList so that it can
	// be saved back.
	private Player [] players;
	private int num;
	private String fileName;
	
	// Initialize the list from a file.  Note that the file name is a parameter.  You
	// must open the file, read the data, making a new Player object for each line and
	// adding them into the array (resizing the array when necessary).  The second
	// argument is the initial capacity of the underlying array (i.e. the length of
	// the array before any adds are done).  
	
	// Note that this method throws IOException. Because of this, any method that CALLS
	// this method must also either catch IOException or throw IOException.  Note that
	// the main() in PlayerListTest.java throws IOException.  Keep this in mind for your 
	// main program (Assig3.java).  Note that your saveList() method will also need
	// "throws IOException" in its header.
	public PlayerList(String fName, int cap) throws IOException
	{
		fileName = fName;
		File inFile = new File(fileName); //Create File object from argument fname
		Scanner scan = new Scanner(inFile); //Create Scanner with input from the file

		players = new Player[cap]; //initialize array to the size of argument cap
		num = 0;

		String line;
		String [] args;
		Player play;
		int index = 0;

		while(scan.hasNextLine()) //Read whole file
		{
			line = scan.nextLine();
			args = line.split(","); //Split each line by commas
			play = new Player(args[0], args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4])); //Create Player object with arguments from file

			addPlayer(play); //Add Player object to the players array
		}

		scan.close(); //Close file
	}

	private void resize()
	{
		Player [] temp = new Player[players.length * 2]; //Create new array with double the capacity

		//Copy existing values into new array
		for (int i = 0; i < players.length; i++) 
		{
			temp[i] = players[i];
		}
		players = temp; //Set players to new array
	}

	//Returns logical size of the array
	public int size()
	{
		return num;
	}

	//Returns the physical size of the array
	public int capacity()
	{
		return players.length;
	}

	//Returns total number of rounds played by all players
	public int totalRounds()
	{
		int rounds = 0;
		for (int i = 0; i < num; i++)
		{
			rounds += players[i].getRounds();
		}
		return rounds;
	}

	//Returns total number of words found by all players
	public int totalWords()
	{
		int words = 0;
		for (int i = 0; i < num; i++)
		{
			words += players[i].getWords();
		}
		return words;
	}

	//Returns total number of points earned by all players
	public int totalPoints()
	{
		int points = 0;
		for (int i = 0; i < num; i++)
		{
			points += players[i].getPoints();
		}
		return points;
	}

	//Returns statistics of all players in formatted string
	public String toString()
	{
		StringBuilder S = new StringBuilder();
		S.append("Total Players: " + num + "\n");

		//Add stats for each player
		for (int i = 0; i < num; i++)
		{
			S.append(players[i].toString());
			S.append("	Ave Words Found: " + players[i].aveWords() + "\n");
			S.append("	Ave Points Earned: " + players[i].avePoints() + "\n\n");
		}

		S.append("Overall statistics:\n");
		S.append("	Total Rounds: " + totalRounds() + "\n");
		S.append("	Total Words: " + totalWords() + "\n");
		S.append("	Total Points: " + totalPoints() + "\n");
		S.append("	Average Words: " + (double)totalWords()/totalRounds() + "\n");
		S.append("	Average Points: " + (double)totalPoints()/totalRounds() + "\n");

		return S.toString();
	}

	//Returns true if Player with id is found in the array players, false otherwise
	public boolean containsId(String id)
	{
		for (int i = 0; i < num; i++)
		{
			if (players[i].getId().equals(id))
			{
				return true;
			}
		}
		return false;
	}

	//If space is available, Player p is added to end of players array, otherwise the array is resized and then Player p is added
	public boolean addPlayer(Player p)
	{
		if (num < players.length)
		{
			players[num] = p;
			num++;
			return true;
		}
		else 
		{
			resize();
			players[num] = p;
			num++;
			return true;
		}
	}

	//Returns a Player if said Player matches id and password of Player p argument, null otherwise
	public Player authenticate(Player p)
	{
		for (int i = 0; i < num; i++)
		{
			if (p.getId().equals(players[i].getId()))
			{
				if (p.getPass().equals(players[i].getPass()))
				{
					return players[i];
				}
			}
		}
		return null;
	}

	//Writes updates player stats back to original file
	public void saveList() throws IOException
	{
		PrintWriter myWriter = new PrintWriter(fileName);

		for (int i = 0; i < num; i++)
		{
			myWriter.println(players[i].getId()+","+players[i].getPass()+","+players[i].getRounds()+","+players[i].getWords()+","+players[i].getPoints());
		}

		myWriter.close();
	}

	// See program PlayerListTest.java to see the other methods that you will need for
	// your PlayerList class.  There are a lot of comments in that program explaining
	// the required effects of the methods.  Read that program very carefully before
	// completing the PlayerList class.  You will also need to complete the Player class
	// before the PlayerList class will work, since your array is an array of
	// Player objects.
	
	// You may also need some methods that are not tested in PlayerListTest.java.  Think
	// about what you need to do to a PlayerList in your Assig3 program and write the methods
	// to achieve those tasks.
}