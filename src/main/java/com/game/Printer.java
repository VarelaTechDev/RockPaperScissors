package com.game;

/**
 * The Printer class is responsible for printing various outputs to the console.
 */
public class Printer {

    /**
     * Prints the choices available for the player to select.
     */
    public void printChoices() {
        System.out.print("Type a number corresponding with your choice: ");
        for (int i = 0; i < Choice.values().length; i++) {
            System.out.printf("%d = %s\t", i, Choice.values()[i].getName());
        }
        System.out.printf(": ");
    }

    /**
     * Prints the result of the game, including the number of ties and wins for both the human and computer.
     *
     * @param humanName   the name of the human player
     * @param computerName the name of the computer player
     * @param numberOfTies the number of ties in the game
     * @param humanWins   the number of wins for the human player
     * @param computerWins the number of wins for the computer player
     */
    public void printResult(String humanName, String computerName, int numberOfTies, int humanWins, int computerWins) {
        System.out.printf("\n=========================\n");
        System.out.printf("Number of ties: %d\nNumber of %s wins: %d\nNumber of %s wins: %d\n", numberOfTies, humanName, humanWins, computerWins);

        if (humanWins > computerWins) System.out.printf("Winner: %s\n", humanName);
        else if (computerWins > humanWins) System.out.printf("Winner: %s\n", computerName);
        else System.out.println("Tie");

        System.out.printf("=========================\n\n");
    }
}
