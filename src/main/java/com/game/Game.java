package com.game;

import lombok.Getter;
import java.util.HashMap;
import java.util.Scanner;

/**
 * The Game class contains the main game logic and manages the flow of the Rock-Paper-Scissors game.
 */
@Getter
public class Game {
    private Scanner scanner;
    private HashMap<Choice, Choice> victoryMap;
    private final String HUMAN_NAME = "Human";
    private final String COMPUTER_NAME = "CPU";
    private int numberOfRounds, numberOfTies, humanWins, computerWins;
    private final int MIN_ROUND = 1;
    private final int MAX_ROUND = 10;
    private int[] choiceCounts = new int[3]; // 0 -> Rock, 1 -> Paper, 2 -> Scissors

    /**
     * Constructs a Game with the specified Scanner, Random, and victory conditions map.
     *
     * @param scanner the Scanner to use for user input
     * @param victoryMap a map of victory conditions
     */
    public Game(Scanner scanner, HashMap<Choice, Choice> victoryMap) {
        this.scanner = scanner;
        this.victoryMap = victoryMap;
        setVariablesToZero();
    }

    /**
     * Starts the game loop.
     */
    public void start() {
        Printer printer = new Printer();
        Helper helper = new Helper(scanner);

        while (true) {
            setVariablesToZero();
            setNumberOfRounds();
            playGame(numberOfRounds, helper, printer);
            printer.printResult(HUMAN_NAME, COMPUTER_NAME, numberOfTies, humanWins, computerWins);
            System.out.print("Would you like to play again?\nPlease type 'yes' if you want to play again, else the program will end: ");
            String userResponse = scanner.nextLine().toLowerCase().trim();

            if (!userResponse.equals("yes")) break;
        }
        System.out.println("Thanks for playing!");
    }

    /**
     * Resets the game variables to zero.
     */
    private void setVariablesToZero() {
        numberOfTies = 0;
        humanWins = 0;
        computerWins = 0;
        choiceCounts[0] = 0;
        choiceCounts[1] = 0;
        choiceCounts[2] = 0;
    }

    /**
     * Prompts the user to set the number of rounds for the game.
     */
    private void setNumberOfRounds() {
        System.out.printf("Between %d - %d, how many rounds would you like to play? ", MIN_ROUND, MAX_ROUND);

        try {
            numberOfRounds = scanner.nextInt();

            if (numberOfRounds < MIN_ROUND || numberOfRounds > MAX_ROUND) throw new IndexOutOfBoundsException();

        } catch (Exception e) {
            System.out.printf("Error: Invalid input! Only NATURAL NUMBERS from %d - %d. Closing the program!\n", MIN_ROUND, MAX_ROUND);
            System.exit(0);
        }

        scanner.nextLine(); // Consume the newline!
    }

    /**
     * Plays the game for the specified number of rounds.
     *
     * @param numberOfRounds the number of rounds to play
     * @param helper the Helper instance for verifying user input and tracking choices
     * @param printer the Printer instance for printing output
     */
    private void playGame(int numberOfRounds, Helper helper, Printer printer) {
        Choice userChoice, computerChoice;

        for (int i = 1; i <= numberOfRounds; i++) {
            printer.printChoices();

            userChoice = helper.verifyUserChoice();
            computerChoice = getAdvancedComputerChoice();

            System.out.printf("Round: %d\t", i);
            System.out.printf("%s: %s\t\t%s: %s\n", HUMAN_NAME, userChoice.getName(), COMPUTER_NAME, computerChoice.getName());

            determineWinner(userChoice, computerChoice);
            helper.trackUserChoice(userChoice, choiceCounts);
        }
    }

    /**
     * Determines the winner of a round and updates the game statistics accordingly.
     *
     * @param userChoice the user's choice
     * @param computerChoice the computer's choice
     */
    void determineWinner(Choice userChoice, Choice computerChoice) {
        if (userChoice == computerChoice) {
            System.out.println("Tie");
            numberOfTies++;
        } else if (victoryMap.get(userChoice) == computerChoice) {
            System.out.printf("%s won\n", HUMAN_NAME);
            humanWins++;
        } else {
            System.out.printf("%s won\n", COMPUTER_NAME);
            computerWins++;
        }
        System.out.println("==========");
    }

    /**
     * Determines the computer's choice using an advanced strategy based on the player's previous choices.
     *
     * @return the computer's choice
     */
    private Choice getAdvancedComputerChoice() {
        if (choiceCounts[0] > choiceCounts[1] && choiceCounts[0] > choiceCounts[2]) {
            return Choice.PAPER; // Paper beats Rock
        } else if (choiceCounts[1] > choiceCounts[0] && choiceCounts[1] > choiceCounts[2]) {
            return Choice.SCISSORS; // Scissors beat Paper
        } else if (choiceCounts[2] > choiceCounts[0] && choiceCounts[2] > choiceCounts[1]) {
            return Choice.ROCK; // Rock beats Scissors
        } else {
            return Choice.getRandomChoice(); // If no clear pattern, pick randomly
        }
    }
}
