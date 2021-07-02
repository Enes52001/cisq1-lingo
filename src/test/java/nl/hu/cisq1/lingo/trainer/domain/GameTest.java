package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.application.GameProgress;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {


    @Test
    @DisplayName("returns the current round")
    void checkRoundNumber(){
        Game game = new Game();
        game.startGame();
        game.startNewRound("motor");
        assertEquals("motor", game.getCurrentRound().getWordToGuess());
    }

    @Test
    @DisplayName("checks if you get ints 5,6,7,5,6,7 and so on ")
    void givesCorrectWordLength(){
        Game game = new Game();
        game.startGame();
        assertEquals(5, game.getNextWordLength());
        game.startNewRound("tests");
        assertEquals(6, game.getNextWordLength());
        game.makeGuess("tests");
        game.startNewRound("testss");
        assertEquals(7, game.getNextWordLength());
        game.makeGuess("testss");
        game.startNewRound("testsss");
        assertEquals(5, game.getNextWordLength());
    }

}