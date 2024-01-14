// CMPINF 0401 Fall 2023
// Assignment 2 WordFinder class
// You must complete the implementation of this class.  You will need
// to use an instance of the Dictionary class within this class.  See
// Dictionary.java for details on the Dictionary class.

public class WordFinder
{
	// Think about the instance variables that you will need for this class.
	// Minimally you will need a Dictionary and a String.
	private Dictionary dict;
	private String word; 
	
	// Initialize a WordFinder object.  String fileName is the name of a
	// dictionary file from which the Dictionary instance variable will be
	// initialized.
	public WordFinder(String fileName)
	{
		dict = new Dictionary(fileName);
		word = null;
	}
	
	// Obtain and store (with this WordFinder object) a random word from the
	// Dictionary of "size" or more letters.
	public void nextWord(int size)
	{
		word = dict.randWord(size);
	}
	
	// Return the word that was obtained from the last call to nextWord().
	// This method is necessary since the word itself will be stored in a 
	// private instance variable.
	public String showWord()
	{
		return word;
	}
	
	// This is the most challenging method in this class.  The test argument is
	// a String that will be checked for validity within the current word that was
	// obtained from the Dictionary.  This method should
	//		 
	// return 0 if all of the characters in test are found within the word 
	//		 (such that each letter in the word is used at most one time) and if 
	//		 test is also a valid word in the Dictionary.  
	// return 1 if test cannot be generated in a valid way from the letters in
	//		 the current word
	// return 2 if test can be generated in a valid way from the letters in the
	//		 current word, BUT test is not a real word in the Dictionary
	//
	// Think about how you will do this and consult the Java API for
	// some ideas (in particular look at methods in the StringBuilder class)
	public int goodWord(String test)
	{
		StringBuilder used = new StringBuilder(word);
		for (int i = 0; i < test.length(); i++)
		{
			String test_letter = test.substring(i, i + 1);
			if (used.indexOf(test_letter) == -1)
			{
				return 1;
			}
			used = used.deleteCharAt(used.indexOf(test_letter));
		}

		if(!dict.contains(test))
		{
			return 2;
		}

		return 0;
	}
}
