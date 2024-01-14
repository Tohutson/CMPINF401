// CMPINF 0401 Fall 2023
// Assignment 2 WordFinderTest
// Your WordFinder class must work with this program and the output
// must be correct.  Since the words are random, the output will not
// exactly match mine, but see a sample in WFTest.txt

import java.util.*;
public class WordFinderTest
{
	public static String [] tests = {"a", "ae", "rea", "so", "um", "sit", "rate", "to", "tot"};
		// Note: "ae" and "rea" are not in the dictionary, so they should never return
		// 0 for goodWord(). They may return 1 if they cannot be formed or 2 if they can 
		// be formed.  The other words may return 1 if they cannot be formed or 0 if they
		// can be formed.
	public static void main(String [] args)
	{
		WordFinder F = new WordFinder("dictionary.txt");  // Create a new WordFinder
		// Note that we are passing the name of a dictionary file into the constructor.
		// You WordFinder constructor should create and initialize a Dictionary
		// object to be used as necessary.
		
		for (int i = 0; i < 20; i++)  // try some same tests
		{
			F.nextWord(7);	// Obtain a new random word from the WordFinder
			System.out.println("Word is " + F.showWord());  // Return the word
			for (int j = 0; j < tests.length; j++)
			{
				int ans = F.goodWord(tests[j]);  // Check next test word
				if (ans == 0)  // Word is valid and in the dictionary
					System.out.println("\t" + tests[j] + " is valid");
				else if (ans == 1) // Word cannot be formed from the letters
					System.out.println("\t" + tests[j] + " cannot be formed from " + F.showWord());
				else	// Word can be formed but is not in the dictionary
					System.out.println("\t" + tests[j] + " is not in the dictionary");
			}
		}
	}
}
