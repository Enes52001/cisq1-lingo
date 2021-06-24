package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    @DisplayName("when game starts status=playing")
    void startgameStatusPlaying() {
        Game game = new Game();
        game.startgame();
        game.startNewRound("test");
        assertEquals(game.getGameState(), Game.GameState.PLAYING);

    }

    @Test
    @DisplayName("zonder game kan geen round starten")
    void startNewRoundWhileNoGame() {
        Game game = new Game();
        game.setGameState(Game.GameState.ELIMINATED);
        assertEquals(game.startNewRound("test"), null);
    }

    @Test
    @DisplayName("round wordt aangemaakt")
    void newRoundExists() {
        Game game = new Game();
        game.startgame();
        game.startNewRound("test");
        assertNotNull(game.getLastRound());
    }

    @Test
    @DisplayName("rondes worden bijgehouden")
    void roundCount(){
        Game game = new Game();
        game.startgame();
        game.startNewRound("test");
        game.startNewRound("test");
        game.startNewRound("test");
        assertEquals(3, game.getRoundNumber());
    }


    @Test
    @DisplayName("hints worden onthouden")
    void hintGetsSaved(){
        Game game = new Game();
        game.startgame();
        game.startNewRound("GIRAF");
        game.makeGuess("GROOT");
        game.makeGuess("LIRYC");
        assertTrue(game.getRoundStatus().contains("GIR.."));


    }

}