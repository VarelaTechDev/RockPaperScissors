package com.game;


import java.util.Scanner;

public class Main {
    private int numberOfTies, computerWins, humanWins, numberOfRounds;
    private Scanner scanner;

    public static void main(String[] args) {
        new Main().driver();
    }

    private void driver(){
        scanner = new Scanner(System.in);

        // We should empty out any values we have for ties, wins, and lose
        setVariablesToZero();

        // Next get the amount of rounds we want
        setNumberOfRounds();
        System.out.printf("Cool, we are going to play %d rounds", numberOfRounds);
    }

    private void setNumberOfRounds(){
        System.out.println("Between 1 - 10, how many rounds would you like to play?");
        try {
            numberOfRounds = scanner.nextInt();

            if(numberOfRounds < 1 || numberOfRounds > 10) throw new IndexOutOfBoundsException();

        }catch (Exception e){
            // Go here if we enter something invalid
            System.out.println("Invalid input");
            System.exit(0);
        }
    }
    private void setVariablesToZero(){
        numberOfTies = 0;
        computerWins = 0;
        humanWins = 0;
    }
}
