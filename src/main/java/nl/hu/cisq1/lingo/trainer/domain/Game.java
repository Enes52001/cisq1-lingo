package nl.hu.cisq1.lingo.trainer.domain;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private int score;
    private GameState gameState;
    private List<Round> round = new ArrayList<>();
    private Progress progress;

    public enum GameState{
        WAITING_FOR_ROUND,
        PLAYING,
        ELIMINATED
    }


    public void startgame(){
        setGameState(GameState.WAITING_FOR_ROUND);

    }

    public Round startNewRound(String wordToGuess) {
        if (gameState == GameState.ELIMINATED) {
            return null;
        }
        setGameState(GameState.PLAYING);
        Round ronde = new Round(wordToGuess);
        round.add(ronde);
        return ronde;
    }

    public List<Mark> makeGuess(String attempt){
        Round ronde = round.get(round.size()-1);
        return ronde.guess(attempt);
    }

    public Round getLastRound() {
        return round.get(round.size()-1);
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }
}
