/* Trey Hutson
CMPINF 0401 Fall 2023 Tu Thu 1pm
Assignment 3 Assig3 main program
Run WordFinder game
*/

import java.util.*;
import java.io.*;

class Assig3
{
	public static void main(String[] args) throws IOException
	{
		//Initialize overall session stats
		int num_users = 0, num_rounds = 0, num_words = 0, num_points = 0;

		Scanner scan = new Scanner(System.in);
		PlayerList players = new PlayerList("players.txt", 6); //Intitialize PlayerList from players.txt

		//Initialize WordFinder object with dictionary
		WordFinder F = new WordFinder("dictionary.txt");

		int choices;

		do
		{
			//Print out rules
			System.out.println();
			System.out.println("Word Finder Game Rules:");
			System.out.println("	Each round there will be a randomly selected word. In 1 minute, you must");
			System.out.println("	enter as many words that can be made from the letters in the random word.");
			System.out.println("	Your words do not have to use all of the letters, but they can only use ");
			System.out.println("	each letter from the random word once. A point is scored for each letter ");
			System.out.println("	in each word, but each word must be a valid dictionary word.");
			System.out.println();

			Player user = null; //Initialize Player object

			choices = 0;

			while (user == null && choices != 3) //Loop until user is not null or the user selects quit
			{
				choices = 0;
				while (choices < 1 || choices > 3)
				{
					//Print choices
					System.out.println("Please enter your choice as a number");
					System.out.println("	1) Login");
					System.out.println("	2) Create new player");
					System.out.println("	3) Quit");

					try
					{
						choices = scan.nextInt(); //Retrieve user choice
					}
					catch(InputMismatchException e)
					{
						System.out.println("Please enter choice as an integer.");
					}
					scan.nextLine();
				}

				if (choices == 1) //User selects Login option
				{
					user = login(players);
				}
				else if (choices == 2) //User selects Create new player option
				{
					user = createPlayer(players);
				}
			}

			//Check if user selected quit, if not run game
			if (choices != 3)
			{
				//Initialize user's stats for current session
				int user_rounds = 0, user_words = 0, user_points = 0;

				num_users++; //Increment number of users

				boolean playing = true;

				String choice = "";

				//Confirm that user is ready to play
				while (!choice.equals("y") && !choice.equals("n"))
				{
					System.out.println("Ready to play? (y/n)");
					choice = scan.nextLine();
				}
				if (choice.equals("n"))
				{
					playing = false;
				}

				//Start round
				while (playing)
				{
					//Call nextWord to retrieve random word
					F.nextWord(7);
					String guess;

					//Initialize MyTimer object, set it to 60 seconds, start timer
					MyTimer timer = new MyTimer();
					timer.set(60000);
					timer.start();

					//Dictionary object to track valid answers
					Dictionary answers = new Dictionary();

					//Initialize stats for current round
					int points = 0;
					int words = 0;

					//While time is left in round, loop will run
					while (timer.check())
					{
						System.out.print("Word: " + F.showWord() + " Guess? ");

						//Retrieve user guess
						guess = scan.nextLine();

						//Confirm that there is time left in round after guess was entered
						if (timer.check())
						{
							//Store result of goodWord method
							int test = F.goodWord(guess);

							//Check if guess is a duplicate
							if (answers.contains(guess))
							{
								System.out.println("You have already guessed " + guess);
							}
							else 
							{
								if (test == 0)	//User guess was a valid entry. Added to answer Dictionary. Score counted
								{
									answers.addWord(guess);
									words++;
									points += guess.length();
									System.out.println("Answer " + words + " is " + guess);
								}
								else if (test == 1) //User guess could not be formed from the word
								{
									System.out.println(guess + " cannot be formed from " + F.showWord());
								}
								else //User guess could be formed, but isn't a valid word in the dictionary
								{
									System.out.println(guess + " is not a valid word in the dictionary");
								}
							}
							System.out.println();
						}
						else //Output to user that they ran out of time while entering guess
						{
							System.out.println("Sorry, you ran out of time.");
						}
					}
					//update user stats
					user_points += points;
					user_words += words;
					user_rounds++;

					//update user all-time stats
					user.addPoints(points);
					user.addWords(words);
					user.addRounds(1);

					//Output stats for round to user
					System.out.println();
					System.out.println("You found " + words + " words.");
					System.out.println("You earned " + points + " points.");
					System.out.println();
					System.out.println("Here are the words you found:");
					System.out.println(answers);

					choice = "";

					//Check if user wants to play again
					while (!choice.equals("y") && !choice.equals("n"))
					{
						System.out.println("Would you like to play another round? (y/n)");
						choice = scan.nextLine();
					}
					if (choice.equals("n"))
					{
						playing = false;
					}
				}
				//Update session stats
				num_rounds += user_rounds;
				num_points += user_points;
				num_words += user_words;

				//Output user's session stats
				if (user_rounds == 0)
				{
					System.out.println("You did not play any rounds");
				}
				else 
				{
					System.out.println("This session:");
					System.out.println("	You played " + user_rounds + " rounds.");
					System.out.println("	You found " + user_words + " words for a total of " + user_points + " points!");
					System.out.println("	Your average words per round were: " + user_words/(double)user_rounds);
					System.out.println("	Your average points per round were: " + user_points/(double)user_rounds);
				}
				//Output user's all-time stats
				System.out.println("Your all-time stats:");
				System.out.println(user.toString());
				System.out.println();
			}
		}
		while (choices != 3);

		//Output overall all-time stats for all players
		System.out.println("All-time stats:");
		System.out.println(players.toString());
		System.out.println("Session stats:");

		//Output overall session stats
		if (num_rounds == 0)
		{
			System.out.println("No rounds were played");
		}
		else 
		{
			System.out.println("	Total rounds: " + num_rounds);
			System.out.println("	Total words: " + num_words);
			System.out.println("	Total points: " + num_points);
			System.out.println("	Average words per round: " + num_words/(double)num_rounds);
			System.out.println("	Average points per round: " + num_points/(double)num_rounds);
		}
		System.out.println();

		//Save PlayerList back to file
		players.saveList();
		System.out.println("Players saved to file.");
		System.out.println("Game Quit");
	
	}

	//Returns a new created Player, adds them to PlayerList
	public static Player createPlayer(PlayerList plist)
	{
		Scanner scan = new Scanner(System.in);

		//Retrieve username
		System.out.print("Enter a username: ");
		String id = scan.nextLine();
		System.out.println();

		//Check if username is taken
		while (plist.containsId(id))
		{
			System.out.println("An account was found that already uses that username.");

			//Asks if user wants to login to existing account
			System.out.println("Would you like to login? (y/n)");

			//Retrieve choice
			String choice = scan.nextLine();
			while (!choice.equals("y") && !choice.equals("n"))
			{
				System.out.println("(y/n)");
			}
			if (choice.equals("y"))
			{
				return login(plist);
			}
			else //Retrieve new username
			{
				System.out.print("Enter a new username: ");
				id = scan.nextLine();
				System.out.println();
			}
		}
		//Creates newPlayer
		Player newPlayer = new Player(id);
		String pass = null;
		boolean confirmed = false;

		//User set and confirm password
		while(!confirmed)
		{
			String temp;
			System.out.print("Enter a password: ");
			pass = scan.nextLine();
			System.out.println();
			System.out.print("Confirm password: ");
			temp = scan.nextLine();
			System.out.println();

			if (pass.equals(temp))
			{
				confirmed = true;
			}
			else 
			{
				System.out.println("Passwords did not match.");
				System.out.println();
			}
		}

		//newPlayer's password set, added to PlayerList, returned
		newPlayer.setPass(pass);
		System.out.println("Password confirmed.");
		System.out.println("Player created!");
		System.out.println();
		plist.addPlayer(newPlayer);
		return newPlayer;
	}

	//Returns existing player after user logs in, null if login fails
	public static Player login(PlayerList plist)
	{
		Scanner scan = new Scanner(System.in);

		//Retrieve username
		System.out.print("Enter your username: ");
		String id = scan.nextLine();
		System.out.println();

		//Check if username exists
		while (!plist.containsId(id))
		{
			//Asks if user wants to create a player if username is not found
			System.out.println("Account not found.");
			System.out.println("Would you like to create a player? (y/n)");

			//Retrieve choice
			String choice = scan.nextLine();
			while (!choice.equals("y") && !choice.equals("n"))
			{
				System.out.println("(y/n)");
			}
			if (choice.equals("y"))
			{
				return createPlayer(plist);
			}
			else //Ask for different username
			{
				System.out.print("Enter your username: ");
				id = scan.nextLine();
				System.out.println();
			}
		}

		Player temp = new Player(id); //Create temporary player with input username

		//Ask user for password, set temporary player's password
		System.out.print("Enter your password: ");
		temp.setPass(scan.nextLine());
		System.out.println();

		//Give 3 attemps for user to enter correct password
		int attempts = 0;
		while (plist.authenticate(temp) == null && attempts < 2)
		{
			attempts++;
			System.out.println("Incorrect password");
			System.out.print("Enter your password: ");
			temp.setPass(scan.nextLine());
			System.out.println();
		}
		if (attempts == 2) //Login failed
		{
			System.out.println("Too many attempts. Login failed.");
			return null;
		}
		else //Successful login, return player from PlayerList
		{
			System.out.println("You are logged in as " + id);
			System.out.println();
			return plist.authenticate(temp);
		}
	}
}