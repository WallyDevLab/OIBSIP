# OIBSIP

Overview

This Java program is a simple Number Guessing Game where the user is prompted to guess a randomly generated number within a specified range. The game provides feedback after each guess, guiding the user to guess higher or lower until the correct number is found or the maximum number of attempts is reached.
Features

    Randomly generates a target number within a specified range.
    Allows the user to input guesses and provides feedback.
    Calculates and displays the user's score based on the number of attempts.

How to Play

    Run the program.
    The game will welcome the player and provide instructions.
    Enter your guess when prompted.
    Receive feedback on whether the guess is correct, too high, or too low.
    Repeat steps 3-4 until the correct number is guessed or the maximum number of attempts is reached.

Program Logic

    The target number is generated randomly between a specified minimum and maximum value.
    The user is given a limited number of attempts to guess the correct number.
    The program provides feedback after each guess, indicating whether the guess is correct or suggesting a higher or lower number.
    The user's score is calculated based on the number of attempts left, with more points awarded for fewer attempts.

Score Calculation

The score is calculated using the calculateScore method, which awards more points for fewer attempts. The formula for calculating the score is: score = attemptsLeft * 10.
Author

This Number Guessing Game was created by [Katlego Barayi].
