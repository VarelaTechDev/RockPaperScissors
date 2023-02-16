package com.game;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private Scanner scanner;
    private Random random;
    private HashMap<Integer, Integer> victoryMap;

    private final String HUMAN_NAME = "Human";
    private final String COMPUTER_NAME = "CPU";
    private final String[] CHOICES = {"Rock", "Paper", "Scissor"};

    private int numberOfRounds, numberOfTies, humanWins, computerWins;
    private final int MIN_ROUND = 1;
    private final int MAX_ROUND = 10;

    public Game() {
        scanner = new Scanner(System.in);
        random = new Random();
        victoryMap = new HashMap();

        setVictoryConditions();
    }

    public void start() {
        while (true) {
            setVariablesToZero();
            setNumberOfRounds();
            playGame(numberOfRounds);
            printResult();
            System.out.print("Would you like to play again?\nPlease type 'yes' if you want to play again, else the program will end: ");
            String userResponse = scanner.nextLine().toLowerCase().trim();

            if (!userResponse.equals("yes")) break;
        }
        System.out.println("Thanks for playing!");
        scanner.close();
    }

    // 0 (Rock) beats 2 (Scissors). We are using the mapping 0: Rock, 1: Paper, and 2: Scissor
    private void setVictoryConditions() {
        victoryMap.put(0, 2);
        victoryMap.put(1, 0);
        victoryMap.put(2, 1);
    }

    private void setVariablesToZero() {
        numberOfTies = 0;
        humanWins = 0;
        computerWins = 0;
    }

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

    private void playGame(int numberOfRounds) {
        int userChoice, computerChoice;

        for (int i = 1; i <= numberOfRounds; i++) {
            printChoices();

            userChoice = verifyUserChoice();
            computerChoice = random.nextInt(CHOICES.length);

            System.out.printf("Round: %d\t", i);
            System.out.printf("%s: %s\t\t%s: %s\n", HUMAN_NAME, CHOICES[userChoice], COMPUTER_NAME, CHOICES[computerChoice]);

            determineWinner(userChoice, computerChoice);
        }
    }

    private int verifyUserChoice() {
        boolean isValidInput = false;
        int userChoice = -1;

        while (!isValidInput) {
            String userInput = scanner.nextLine();
            try {
                userChoice = Integer.parseInt(userInput);

                if (userChoice < 0 || userChoice >= CHOICES.length) {
                    printChoices();
                } else {
                    isValidInput = true;
                }
            } catch (NumberFormatException e) {
                printChoices();
            }
        }

        return userChoice;
    }

    private void printResult() {
        System.out.printf("\n=========================\n");

        System.out.printf("Number of ties: %d\nNumber of %s wins: %d\nNumber of %s wins: %d\n", numberOfTies, HUMAN_NAME, humanWins, COMPUTER_NAME, computerWins);

        if (humanWins > computerWins) System.out.printf("Winner: %s\n", HUMAN_NAME);
        else if (computerWins > humanWins) System.out.printf("Winner: %s\n", COMPUTER_NAME);
        else System.out.println("Tie");

        System.out.printf("=========================\n\n");
    }

    private void printChoices() {
        System.out.printf("Type a number corresponding with your choice: ", CHOICES.length);
        for (int i = 0; i < CHOICES.length; i++) {
            System.out.printf("%d = %s\t", i, CHOICES[i]);
        }
        System.out.printf(": ");
    }

    private void determineWinner(int userChoice, int computerChoice) {
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
}
