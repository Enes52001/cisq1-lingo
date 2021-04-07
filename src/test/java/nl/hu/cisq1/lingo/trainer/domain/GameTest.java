package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void startgameStatusPlaying() {
        Game game = new Game();
        game.startgame();
        game.startNewRound("test");
        assertEquals(game.getLastRound().getWordToGuess(), "test");
        assertEquals(game.getGameState(), Game.GameState.PLAYING);

    }

    @Test
    void startNewRoundWhileNoGame() {
        Game game = new Game();
        game.setGameState(Game.GameState.ELIMINATED);
        assertEquals(game.startNewRound("test"), null);
    }

    @Test
    void newRoundExists() {
        Game game = new Game();
        game.startgame();
        game.startNewRound("test");
        assertNotNull(game.getLastRound());
    }

    @Test
    void roundCount(){
        Game game = new Game();
        game.startgame();
        game.startNewRound("test");
        game.startNewRound("test");
        game.startNewRound("test");
        assertEquals(3, game.getRoundNumber());
    }


}