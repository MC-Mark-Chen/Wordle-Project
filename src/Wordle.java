import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Wordle
{

	private static final String RESET = "\033[0m"; // Text Reset
	private static final String CYAN = "\033[0;36m"; // Text color cyan
	private static final String RED_BOLD = "\033[1;31m"; // Text color red bold
	private static final String PURPLE_BOLD = "\033[1;35m"; // Text color purple bold
	private static final String BLACK_BOLD = "\033[1;30m";  // Text color black bold
    private static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m"; // Text background color green
    private static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m"; // Text background color yellow
	private static final File file = new File("/Users/mark/Library/Mobile Documents/com~apple~CloudDocs/Basis/Grade 9/Intro to Programing/800 Five Letter Words.txt"); // Worldle words file
	private static final Random random = new Random();
	private static final Scanner input = new Scanner(System.in); // Player input scanner

	private static Scanner text; // Wordle text scanner
	private static String textStr; // Wordle text
	private static String playerStr; // Player input text
	private static ArrayList<String> outputLetter = new ArrayList<String>(); // Player input text letters holder
	private static ArrayList<String> outputColor = new ArrayList<String>(); // Player input text colors holder
	private static String status = "no"; // Status for continueing the game

	public static void begin() throws FileNotFoundException
    {
		text = new Scanner(file);

		System.out.print("\033[H\033[2J"); // Clear terminal
		System.out.flush();

		System.out.println(CYAN + "Guess the WORDLE in six tries. \nEach guess must be a valid five-letter word. Hit the enter button to submit. \nAfter each guess, the color of the tiles will change to show how close your guess was to the word." + RESET);
		System.out.print(CYAN + "\nType in \"" + RESET); // Game instructions  
		System.out.print(RED_BOLD + "yes" + RESET);
		System.out.println(CYAN + "\" to start." + RESET);
		status = input.nextLine();

		while(status.equals("yes"))
		{

			outputLetter.clear(); // Clear history after each word
			outputColor.clear(); // Clear history after each word

			System.out.print("\033[H\033[2J"); // Clear terminal after each word
    		System.out.flush();

			for(int i = 0; i <= random.nextInt(801); i++) // Randomly choose a String from the file
			{
				textStr = text.nextLine();
			}

			for(int count = 6; count > 0; count--) // The player has 6 chances for each word
			{

				playerStr = input.nextLine();

				for(int i = 0; i < textStr.length(); i++)
				{

					outputLetter.add(playerStr.substring(i, i + 1)); // Add the index of the color in the colors array with its designated letter

					if(playerStr.charAt(i) == textStr.charAt(i))
					{
						outputColor.add(GREEN_BACKGROUND_BRIGHT);
					}

					else if(textStr.contains(playerStr.substring(i, i + 1)))
					{
						outputColor.add(YELLOW_BACKGROUND_BRIGHT);
					}

					else
					{
						outputColor.add(BLACK_BOLD);
					}
				}

				System.out.print("\033[H\033[2J"); // Clear terminal after each round
    			System.out.flush();

				for(int j = 0; j < outputLetter.size() / playerStr.length(); j++)
				{

					for(int i = 0; i < playerStr.length(); i++)
					{

						System.out.print(outputColor.get(i + j * playerStr.length()) + " " + outputLetter.get(i + j * playerStr.length()) + " " + RESET); // Output the letters with colors

					}

					System.out.println();

				}

				if(playerStr.equals(textStr))
				{

					break; // End the loop if the player inputs the correct answer

				}
    		}

			System.out.println();
			System.out.print(CYAN + "The correct answer is: " + RESET); // Correct answer
			System.out.println(PURPLE_BOLD + textStr + RESET);
			System.out.print(CYAN + "Type in \"" + RESET); // Start the next round 
			System.out.print(RED_BOLD + "yes" + RESET);
			System.out.println(CYAN + "\" to continue" + RESET);
			status = input.nextLine();

		}
	}
}