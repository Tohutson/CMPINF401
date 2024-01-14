// Trey Hutson
// CMPINF 0401 Fall 2023 Tu Thur 1pm
// Player class
// You must complete this class for Assignment 3.  It must run in your Assig3.java
// main program and it must also run correctly with the test program
// PlayerListTest.java.

// Some instance variables and constructors are shown.  You may add some additional 
// instance variables if you wish.  You will also need to add the methods that are 
// called in the program PlayerListTest.java.  Read over that program carefully so 
// that you know the method that you need in this class.

// You may also need some methods that are not tested in PlayerListTest.java.  Think
// about what you need to do to a Player in your Assig3 program and write the methods
// to achieve those tasks.

// Note that all of the methods in class are fairly simple since they all deal with
// a SINGLE Player.  The methods that deal with multiple players will be in the
// PlayerList class.

public class Player
{
	private String id, password;  // must store id and password for each player
	private int roundsPlayed, wordsFound, points;  // must also store these
						// values for the player stats

	// Default constructor 
	public Player()
	{
		id = null;
		password = null;
		roundsPlayed = 0;
		wordsFound = 0;
		points = 0;
	}
	
	// Construct new Player with just ID
	public Player(String newId)
	{
		id = new String(newId);
		password = null;
		roundsPlayed = 0;
		wordsFound = 0;
		points = 0;
	}
	
	// Construct new Player with all fields passed in
	public Player(String newid, String pass, int rds, int wds, int pts)
	{
		id = new String(newid);
		password = new String(pass);
		roundsPlayed = rds;
		wordsFound = wds;
		points = pts;
	}

	//Set password to pass
	public void setPass(String pass)
	{
		password = new String(pass);
	}

	//Appends player statistics into formatted string
	public String toString()
	{
		StringBuilder S = new StringBuilder();
		S.append("	Id: " + id + "\n");
		S.append("	Total Rounds Played: " + roundsPlayed + "\n");
		S.append("	Total Words Found: " + wordsFound + "\n");
		S.append("	Total Points Earned: " + points + "\n");

		return S.toString();
	}

	//Add r rounds to player stats
	public void addRounds(int r)
	{
		roundsPlayed += r;
	}

	//Add w words to player stats
	public void addWords(int w)
	{
		wordsFound += w;
	}

	//Add p points to player stats
	public void addPoints(int p)
	{
		points += p;
	}

	//Returns ID
	public String getId()
	{
		return new String(id);
	}

	//Returns average words per round for the player
	public double aveWords()
	{
		return ((double)wordsFound)/roundsPlayed;
	}

	//Returns average points per round for the player
	public double avePoints()
	{
		return ((double)points)/roundsPlayed;
	}

	//Returns password
	public String getPass()
	{
		return password;
	}

	//Returns number of rounds played
	public int getRounds()
	{
		return roundsPlayed;
	}

	//Returns number of words found
	public int getWords()
	{
		return wordsFound;
	}

	//Returns number of points earned
	public int getPoints()
	{
		return points;
	}
	// You must fill in the remaining methods.  See PlayerListTest.java for the
	// methods that are minimally required.  However, you may certainly add some
	// methods that you will find useful, even if they are not used in the
	// PlayerListTest.java program.
}