package com.game;

import java.util.HashMap;
import java.util.Scanner;

/**
 * The Main class is the entry point of the application.
 */
public class Main {
    public static void main(String[] args) {
        // Initialize resources
        Scanner scanner = new Scanner(System.in);
        HashMap<Choice, Choice> victoryMap = new HashMap<>();

        // Set victory conditions
        victoryMap.put(Choice.ROCK, Choice.SCISSORS);
        victoryMap.put(Choice.PAPER, Choice.ROCK);
        victoryMap.put(Choice.SCISSORS, Choice.PAPER);

        // Start the game
        Game game = new Game(scanner, victoryMap);
        game.start();

        // Close scanner after the game ends
        scanner.close();
    }
}
