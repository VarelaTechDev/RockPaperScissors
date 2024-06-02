package com.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The GameTest class contains unit tests for the Game class.
 */
public class GameTest {

    private Game game;
    private HashMap<Choice, Choice> victoryMap;

    @BeforeEach
    void setUp() {
        Scanner scanner = new Scanner(System.in);
        victoryMap = new HashMap<>();
        victoryMap.put(Choice.ROCK, Choice.SCISSORS);
        victoryMap.put(Choice.PAPER, Choice.ROCK);
        victoryMap.put(Choice.SCISSORS, Choice.PAPER);

        game = new Game(scanner, victoryMap);
    }

    @Test
    void testDetermineWinner_Tie() {
        Choice userChoice = Choice.ROCK;
        Choice computerChoice = Choice.ROCK;

        game.determineWinner(userChoice, computerChoice);
        assertEquals(1, game.getNumberOfTies());
    }

    @Test
    void testDetermineWinner_HumanWins() {
        Choice userChoice = Choice.ROCK;
        Choice computerChoice = Choice.SCISSORS;

        game.determineWinner(userChoice, computerChoice);
        assertEquals(1, game.getHumanWins());
    }

    @Test
    void testDetermineWinner_ComputerWins() {
        Choice userChoice = Choice.SCISSORS;
        Choice computerChoice = Choice.ROCK;

        game.determineWinner(userChoice, computerChoice);
        assertEquals(1, game.getComputerWins());
    }
}
