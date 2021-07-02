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

}