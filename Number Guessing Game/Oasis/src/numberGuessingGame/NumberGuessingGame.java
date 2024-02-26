/**
 * 
 */
package numberGuessingGame;

/**
 * 
 */

import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

	/**
	 * @param args
	 */
		
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int minValue = 1;
        int maxValue = 100;
        int targetNumber = random.nextInt(maxValue - minValue + 1) + minValue;

        int attemptsLeft = 10;
        double userScore = 0;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("Guess the number between " + minValue + " and " + maxValue);
        
        
        while (attemptsLeft > 0) {
            System.out.print("Enter your guess: ");
            int userGuess = scanner.nextInt();

            if (userGuess == targetNumber) {
                System.out.println("Congratulations! You guessed the correct number.");
                userScore += calculateScore(attemptsLeft);
                break;
                
            } else {
                System.out.println("Incorrect!");

                if (userGuess < targetNumber) {
                    System.out.println("Try a higher number.");
                } else {
                    System.out.println("Try a lower number.");
                }

                attemptsLeft--;
                System.out.println("Attempts left: " + attemptsLeft);
            }
        }

        System.out.println("Your score: " + userScore+"%");
        scanner.close();
    }

    private static double calculateScore(int attemptsLeft) {
    	
        // Award more points for fewer attempts
    	
    	double highScore = (double) (attemptsLeft * 10);
        return highScore;
    }
}
