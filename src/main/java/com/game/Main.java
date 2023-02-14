package com.game;


import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private int numberOfTies, computerWins, humanWins, numberOfRounds;
    private Scanner scanner;
    private HashMap<Integer, Integer> victoryMap;
    private Random random;

    public static void main(String[] args) {
        new Main().driver();
    }

    private void driver(){
        scanner = new Scanner(System.in);
        random = new Random();

        // We should empty out any values we have for ties, wins, and lose
        setVariablesToZero();

        // Next get the amount of rounds we want
        setNumberOfRounds();

        System.out.printf("Cool, we are going to play %d rounds", numberOfRounds);

        // ToDo: Need to implement the loop
    }

    private void setNumberOfRounds(){
        System.out.println("Between 1 - 10, how many rounds would you like to play?");
        try {
            numberOfRounds = scanner.nextInt();

            if(numberOfRounds < 1 || numberOfRounds > 10) throw new IndexOutOfBoundsException();

        }
        // Prevent OutOfBounds and if the user types a non-integer value WE EXIT THE PROGRAM
        catch (Exception e){
            // Go here if we enter something invalid
            System.out.println("Invalid input");
            System.exit(0);
        }
    }


    // If playerOne maps to the value, we know playerOne choice beat playerTwo
    private boolean doesPlayerOneWin(int playerOne, int playerTwo){
        return victoryMap.get(playerOne) == playerTwo;
    }

    // By using a HashMap, we can easily change the code logic
    // Rock(0) BEATS Scissors(2)
    // Paper(1) BEATS Rock(0)
    // Scissor(2) BEATS Paper(1)
    private void setVictoryConditions(){
        victoryMap = new HashMap();
        victoryMap.put(0, 2);
        victoryMap.put(1, 0);
        victoryMap.put(2, 1);
    }

    private void setVariablesToZero(){
        numberOfTies = 0;
        computerWins = 0;
        humanWins = 0;
    }
}
