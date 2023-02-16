package com.game;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private int numberOfTies, computerWins, humanWins, numberOfRounds;
    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();
    private HashMap<Integer, Integer> victoryMap = new HashMap();
    private final String[] choices = {"Rock", "Paper", "Scissor"};

    private final String humanName = "Human";
    private final String computerName = "CPU";
    private final int MIN_ROUND = 1;
    private final int MAX_ROUND = 10;

    public Game() {
        setVictoryConditions();
    }

    public void play() {
        while (true) {
            setVariablesToZero();
            setNumberOfRounds();
            playGame(numberOfRounds);
            printResult();
            System.out.println("Would you like to play again?\nPlease type 'yes' if you want to play again, else the program will end: ");
            String userResponse = scanner.nextLine().toLowerCase().trim();

            if (!userResponse.equals("yes")) break;
        }
        System.out.println("Thanks for playing!");
        scanner.close();
    }

    private void setVictoryConditions() {
        victoryMap.put(0, 2);
        victoryMap.put(1, 0);
        victoryMap.put(2, 1);
    }

    private void setVariablesToZero() {
        numberOfTies = 0;
        computerWins = 0;
        humanWins = 0;
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
            computerChoice = random.nextInt(3);

            System.out.printf("Round: %d\t", i);
            System.out.printf("%s: %s\t\t%s: %s\n", humanName, choices[userChoice], computerName, choices[computerChoice]);

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

                if (userChoice < 0 || userChoice >= choices.length) {
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
        System.out.println("=========================");

        System.out.printf("Number of ties: %d\nNumber of %s wins: %d\nNumber of %s wins: %d\n", numberOfTies, humanName, humanWins, computerName, computerWins);

        if (humanWins > computerWins) System.out.printf("Winner: %s\n", humanName);
        else if (computerWins > humanWins) System.out.printf("Winner: %s\n", computerName);
        else System.out.println("Tie");

        System.out.println("=========================");
    }

    private void printChoices() {
        System.out.printf("Type a number corresponding with your choice: ", choices.length);
        for (int i = 0; i < choices.length; i++) {
            System.out.printf("%d = %s\t", i, choices[i]);
        }
        System.out.printf(": ");
    }

    private void determineWinner(int userChoice, int computerChoice) {
        if (userChoice == computerChoice) {
            System.out.println("Tie");
            numberOfTies++;
        } else if (victoryMap.get(userChoice) == computerChoice) {
            System.out.printf("%s won\n", humanName);
            humanWins++;
        } else {
            System.out.printf("%s won\n", computerName);
            computerWins++;
        }
        System.out.println("==========");
    }
}