package com.game;

import java.util.Scanner;

/**
 * The Helper class provides helper methods for the game, such as verifying user input and tracking choices.
 */
public class Helper {
    private Scanner scanner;

    /**
     * Constructs a Helper with the specified Scanner.
     *
     * @param scanner the Scanner to use for user input
     */
    public Helper(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Verifies the user's choice and returns the corresponding Choice enum.
     *
     * @return the user's choice as a Choice enum
     */
    public Choice verifyUserChoice() {
        boolean isValidInput = false;
        Choice userChoice = null;

        while (!isValidInput) {
            String userInput = scanner.nextLine();
            try {
                int choiceIndex = Integer.parseInt(userInput);

                if (choiceIndex < 0 || choiceIndex >= Choice.values().length) {
                    new Printer().printChoices();
                } else {
                    userChoice = Choice.values()[choiceIndex];
                    isValidInput = true;
                }
            } catch (NumberFormatException e) {
                new Printer().printChoices();
            }
        }

        return userChoice;
    }

    /**
     * Tracks the user's choice by incrementing the corresponding count in the choiceCounts array.
     *
     * @param userChoice the user's choice as a Choice enum
     * @param choiceCounts an array that tracks the count of each choice
     */
    public void trackUserChoice(Choice userChoice, int[] choiceCounts) {
        switch (userChoice) {
            case ROCK -> choiceCounts[0]++;
            case PAPER -> choiceCounts[1]++;
            case SCISSORS -> choiceCounts[2]++;
        }
    }
}
