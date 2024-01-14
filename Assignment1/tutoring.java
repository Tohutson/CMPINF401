import java.util.*;
import java.lang.Math;

/*
Trey Hutson
CMPINF 401 Tu Thu 1 pm

This program simulates customers purchasing "Defense Against the Dark Arts" tutoring by either
hourly tutoring or individual spells 
*/


class tutoring
{
	private static int hourlyrate = 2465;
	private static int spellprice = 2465;
	static int hours_ordered = 0;
	static int spells_ordered = 0;
	static int total = 0;
	static boolean discount = false;
	static int rate;
	static String name;

	public static void main(String[] args) 
	{
		//Wizard's Chess password
		String pass = "HARRY";

		Scanner scan = new Scanner(System.in);

		//Main Loop iterates until user enters a blank string when prompted for name
		do {
			name = "";
			discount = false;
			hours_ordered = 0;
			spells_ordered = 0;
			total = 0;
			rate = 0;

			System.out.println();
			System.out.println("Welcome to Trey's Defence Against the Dark Arts tutoring!");
			System.out.println("Enter your name [Enter nothing to quit]");
			String input = scan.nextLine();

			if (input == "")
			{
				return;
			}
			else
			{
				name = input;
			}

			System.out.println();
			System.out.println("Ready to serve you, " + name + "!");
			System.out.println();

			//Passes selected password into password() method
			//Will return true if user inputs correct password
			if(password(pass))
			{
				discount = true;
			}

			//Display price list
			displayPrices(discount);

			int choice = 0;

			//Loop iterates until user inputs 4 to checkout
			while(choice != 4)
			{
				choice = getChoice();

				if (choice == 1)
				{
					orderHourly();
				}
				else if (choice == 2)
				{
					orderSpell();
				}
				else if (choice == 3)
				{
					displayPrices(discount);
				}
			}

			checkout();
		} while (!name.equals(""));

	System.out.println();


	}

	//Takes argument answer, returns true if user guesses answer within 2 attempts, false if not
	public static boolean password(String answer)
	{
		String guess;
		int attempts = 0;
		boolean correct = false;

		Scanner stan = new Scanner(System.in);

		System.out.println("What is the secret password");
		guess = (stan.nextLine()).toUpperCase();
		if(guess.equals(answer))
		{
				System.out.println();
				System.out.println("Congratulations you guessed correctly!");

			return true;
		}	
		attempts++;

		while (!(correct) && attempts < 2)
		{
			System.out.println();
			System.out.println("Try again (" + (2-attempts) + " attempt remaining)");
			System.out.println("What is the secret password");
			guess = (stan.nextLine()).toUpperCase();
			if(guess.equals(answer))
			{
				System.out.println();
				System.out.println("Congratulations you guessed correctly! Enjoy your discounts!");
				System.out.println();

				return true;
			}	
			attempts++;
		}
		System.out.println("Sorry. You did not guess correctly. Prices are normal.");
		return false;
	}

	//Returns integer input from user
	public static int getInt()
	{
		int input;
		Scanner scan = new Scanner(System.in);
		while(!scan.hasNextInt()) {
			System.out.println();
			System.out.println("Please enter your choice as an integer");
			scan.nextLine();
		}
		input = scan.nextInt();
		scan.nextLine();
		return input;
	}

	//Takes argument discounted, if discounted is true, displays discounted prices, if not, displays regular prices
	public static void displayPrices(boolean discounted)
	{
		if(discounted)
		{
			System.out.println();

			System.out.println("PRICES:");
			System.out.println("(10% off hourly rates and 4 Sickles off per spell)");
			System.out.println("	Hourly tutoring:");
			System.out.println("		[0 to 4 hours]: 2219 (normal 2465) Knuts per hour");
			System.out.println("		Hourly rates are decreased by 145 Knuts for every 5 hours purchased");
			System.out.println("		Minimum hourly rate: 1566 (normal 1740) Knuts per hour");
			System.out.println("	Spells:");
			System.out.println("		[1 or 2 spells]: 2349 (normal 2465) Knuts per spell");
			System.out.println("		[3 or 4 spells]: 1856 (normal 1972) Knuts per spell");
			System.out.println("		[5 spells]: 	 1363 (normal 1479) Knuts per spell");
		}
		else 
		{
			System.out.println();

			System.out.println("PRICES:");
			System.out.println("	Hourly tutoring:");
			System.out.println("		[0 to 4 hours]: 2465 Knuts per hour");
			System.out.println("		Hourly rates are decreased by 145 Knuts for every 5 hours purchased");
			System.out.println("		Minimum hourly rate: 1740 Knuts per hour");
			System.out.println("	Spells:");
			System.out.println("		[1 or 2 spells]: 2465 Knuts per spell");
			System.out.println("		[3 or 4 spells]: 1972 Knuts per spell");
			System.out.println("		[5 spells]: 	 1479 Knuts per spell");
		}
	}

	//Displays choices, returns choice
	public static int getChoice()
	{
		System.out.println();
		System.out.println("Choose an option below:");
		System.out.println("	1)Order hourly tutoring");
		System.out.println("	2)Order individual spell tutoring");
		System.out.println("	3)Review prices");
		System.out.println("	4)Check out");
		System.out.println();

		int choice = getInt();

		while(choice < 1 || choice > 4)
		{
			System.out.println("(Choice must be between 1 and 4)");
			choice = getInt();
		}
		return choice;
	}

	//Displays current order, prompts user to purchase hourly tutoring, confirms purchase
	public static void orderHourly()
	{
		int hours;
		int price;

		//Display current order
		System.out.println();
		if(hours_ordered > 0)
		{
			System.out.println("Current order: " + hours_ordered + " hours for " + total + " Knuts");
		}
		else if (spells_ordered > 0)
		{
			System.out.println("Current order: " + spells_ordered + " spells for " + total + " Knuts");
		}
		else 
		{
			System.out.println("Current order: None");
		}

		//Prompt user to purchase hourly tutoring
		Scanner scan = new Scanner(System.in);
		System.out.println();
		System.out.println("How many hours of tutoring would you like to purchase?");
		hours = getInt();

		while (hours < 0)
		{
			System.out.println("Please enter a positive number of hours to purchase");
			hours = getInt();
		}

		if(hours == 0)
		{
			hours_ordered = 0;
			return;
		}

		rate = Math.max(hourlyrate - (hours/5 * 145), 1740);

		//Confirm purchase
		if(discount)
		{
			price = hours * (int)Math.round(0.9 * rate);
			System.out.println("Confirm purchase of " + hours + " hours for " + price + " Knuts (y/n)" );
			String choice = scan.nextLine();
			while (!choice.equals("y") && !choice.equals("n"))
			{
				System.out.println("Please confirm with either (y/n)");
				choice = scan.nextLine();
			}

			if(choice.equals("y"))
			{
				hours_ordered = hours;
				total = price;
			}
			else 
			{
				return;
			}
		}
		else 
		{
			price = hours * rate;
			System.out.println("Confirm purchase of " + hours + " hours for " + price + " Knuts (y/n)" );
			String choice = scan.nextLine();
			while (!choice.equals("y") && !choice.equals("n"))
			{
				System.out.println("Please confirm with either (y/n)");
				choice = scan.nextLine();
			}

			if(choice.equals("y"))
			{
				hours_ordered = hours;
				total = price;
				spells_ordered = 0;
			}
			else 
			{
				return;
			}
		}


		return;
	}

	//Displays current order, prompts user to purchase spells, confirms purchase
	public static void orderSpell()
	{
		int num_spells;
		int price;


		Scanner scan = new Scanner(System.in);
		System.out.println();

		//Display current order
		if(hours_ordered > 0)
		{
			System.out.println("Current order: " + hours_ordered + " hours for " + total + " Knuts");
		}
		else if (spells_ordered > 0)
		{
			System.out.println("Current order: " + spells_ordered + " spells for " + total + " Knuts");
		}
		else 
		{
			System.out.println("Current order: none");
		}

		//Prompt user to purchase spells
		System.out.println("How many spells would you like to purchase?");
		num_spells = getInt();

		while (num_spells < 0 || num_spells > 5)
		{
			System.out.println("Please enter a positive number of spells to purchase (Up to 5)");
			num_spells = getInt();
		}

		if (num_spells == 0)
		{
			spells_ordered = 0;
			return;
		}

		//confirm purchase

		rate = spellprice - (((int)Math.round(num_spells/2.0) - 1)* 493);

		price = num_spells * rate;
		if(discount)
		{
			price -= num_spells * 116;
		}
		System.out.println("Confirm purchase of " + num_spells + " spells for " + price + " Knuts (y/n)" );
		String choice = scan.nextLine();
		while (!choice.equals("y") && !choice.equals("n"))
		{
			System.out.println("Please confirm with either (y/n)");
				choice = scan.nextLine();
		}

		if(choice.equals("y"))
		{
			spells_ordered = num_spells;
			total = price;
			hours_ordered = 0;
		}
		else 
		{
			return;
		}
		

		return;
	}

	//Displays subtotal, tax, total, prompts user to input money in Knuts, Sickles, or Galleons until total is paid off.
	//Prints out change
	public static void checkout()
	{
		Scanner scan = new Scanner(System.in);
		int payment;
		String currency = "";
		String input;
		int amount = 0; 
		int tax = 0;
		if(hours_ordered > 0 || spells_ordered > 0)
		{
			if (hours_ordered > 0)
			{
				if(discount)
				{
					System.out.println("Your bill is:");
					System.out.println(String.format("%-70s %d", hours_ordered + " hours of tutoring at " + Math.round(rate * 0.9) + " (normal " + rate + ") Knuts/hour:", total));
					tax = (int)Math.round(0.05 * total);
					System.out.println(String.format("%-70s %d", "Ministry of Magic tax:", tax));
					total += tax;
					for(int i = 0; i < 85; i++)
					{
						System.out.print("-");
					}
					System.out.println();
					System.out.println(String.format("%-70s %d", "Total:", total));

				}
				else 
				{
					System.out.println("Your bill is:");
					System.out.println(String.format("%-70s %d", hours_ordered + " hours of tutoring at " + rate + " Knuts/hour:", total));
					tax = (int)Math.round(0.05 * total);
					System.out.println(String.format("%-70s %d", "Ministry of Magic tax:", tax));
					total += tax;
					for(int i = 0; i < 85; i++)
					{
						System.out.print("-");
					}
					System.out.println();
					System.out.println(String.format("%-70s %d", "Total:", total));
				}
			}
			else if (spells_ordered > 0)
			{
				System.out.println("Your bill is:");
				if(discount)
				{
					System.out.println(String.format("%-70s %d", spells_ordered + " spells at " + (rate - (spells_ordered * 116)) + " (normal " + rate + ") Knuts/spell:", total));
				}
				else 
				{
					System.out.println(String.format("%-70s %d", spells_ordered + " spells at " + rate + " Knuts/spell:", total));

				}
				tax = (int)Math.round(0.05 * total);
				System.out.println(String.format("%-70s %d", "Ministry of Magic tax:", tax));
				total += tax;
				for(int i = 0; i < 85; i++)
				{
					System.out.print("-");
				}
				System.out.println();
				System.out.println(String.format("%-70s %d", "Total:", total));

			}

			while (total > 0)
			{

				System.out.println();
				System.out.println("You still owe " + total + " Knuts");
				System.out.println("Enter a payment amount: <amount><space><currency>");
				System.out.println("	(amount = integer value)");
				System.out.println("	(space = blank space)");
				System.out.println("	(currency = Galleons, Sickles, Knuts)");

				boolean valid = false;

				while(!valid)
				{
					try
					{
							input = scan.nextLine();
							String[] x = input.split(" ");
							amount = Integer.parseInt(x[0]);
							currency = x[1].toUpperCase();
							if(currency.equals("KNUTS") || currency.equals("SICKLES") || currency.equals("GALLEONS"))
							{
								valid = true;
							}
					}
					catch (Exception e)
					{
						System.out.println("Format: <amount><space><currency>");
					}
				}

				if (currency.equals("GALLEONS"))
				{
					total -= amount * 493;
				}
				else if (currency.equals("SICKLES"))
				{
					total -= amount * 29;
				}
				else 
				{
					total -= amount;
				}
			}
			int change = Math.abs(total);
			if(change > 0)
			{
				System.out.println();
				System.out.println("Heres your change:");
				if(change/493 > 0)
				{
					System.out.print("	" + change/493 + " Galleons ");
					change -= change/493 * 493;
				}
				if(change/29 > 0)
				{
					System.out.print(change/29 + " Sickles ");
					change -= change/29 * 29;

				}
				if(change>0)
				{
					System.out.println(change + " Knuts");
				}
			}
			System.out.println();
			System.out.println("Thank you for your purchase, " + name);
		}
		else 
		{
			System.out.println("You did not make any orders");
		}
		System.out.println();
		return;
	}

}