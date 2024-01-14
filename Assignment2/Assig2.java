/* Trey Hutson
CMPINF 0401 Fall 2023 Tu Thu 1pm
Assignment 2 Assig2 main program
Run WordFinder game
*/

import java.util.*;

class Assig2
{
	public static void main(String[] args) 
	{
		//Initialize overall session stats
		int num_users = 0, num_rounds = 0, num_words = 0, num_points = 0;

		String name = "";
		Scanner scan = new Scanner(System.in);

		//Initialize WordFinder object with dictionary
		WordFinder F = new WordFinder("dictionary.txt");

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

			//Retrieve user's name or empty string to quit
			System.out.println("Please enter your username (Enter nothing to quit)");
			name = scan.nextLine();

			//Check if user entered empty string, if not run game
			if (name != "")
			{
				//Initialize a User object to update all-time stats
				User player = new User(name);

				//Initialize user's stats for current session
				int user_rounds = 0, user_words = 0, user_points = 0;

				num_users++;

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
					player.addRound();
					player.addPoints(points);
					player.addWords(words);

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

				//Update user's all-time file
				player.update();

				//Output user's session stats
				if (user_rounds == 0)
				{
					System.out.println("You did not play any rounds");
				}
				else 
				{
					System.out.println("You played " + user_rounds + " rounds.");
					System.out.println("You found " + user_words + " words for a total of " + user_points + " points!");
					System.out.println("That's an average of " + user_words/(double)user_rounds + " words and " + user_points/(double)user_rounds + " points per round.");
				}
					System.out.println();
					System.out.println(player);
			}
		}
		while (name != "");

		//Output overall session stats
		if (num_rounds == 0)
		{
			System.out.println("Game Quit");
		}
		else 
		{
			System.out.println("All players are finished playing.");
			System.out.println("	Total number of users: " + num_users);
			System.out.println("	Total number of rounds: " + num_rounds);
			System.out.println("	Total number of words found: " + num_words);
			System.out.println("	Total number of points earned: " + num_points);
			System.out.println("	Average words per round: " + num_words/(double)num_rounds);
			System.out.println("	Average points per round: " + num_points/(double)num_rounds);
		}
			System.out.println();
	
	}
}