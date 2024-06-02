package com.game;

import java.util.Random;

/**
 * The Choice enum represents the possible choices in the game of Rock-Paper-Scissors.
 */
enum Choice {
    PAPER("Paper"),
    ROCK("Rock"),
    SCISSORS("Scissors");

    private String name;

    /**
     * Constructs a Choice with the specified name.
     *
     * @param name the name of the choice
     */
    Choice(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the choice.
     *
     * @return the name of the choice
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a random Choice.
     *
     * @return a random Choice
     */
    public static Choice getRandomChoice() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }

    /**
     * Returns the name of a random Choice.
     *
     * @return the name of a random Choice
     */
    public static String getRandomChoiceWithName() {
        return getRandomChoice().getName();
    }
}
