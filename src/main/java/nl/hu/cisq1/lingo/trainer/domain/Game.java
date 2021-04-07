package nl.hu.cisq1.lingo.trainer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Game {
    @Id
//    @Column(name="round number")
    private int roundNumber = 0;
//    @Column(name="score")
    private int score = 0;
    private GameState gameState;
//    @OneToMany
    private transient List<Round> round = new ArrayList<>();


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
        ronde.setWordToGuess(wordToGuess);
        round.add(ronde);
        roundNumber = (round.size());
        return ronde;
    }

    public String makeGuess(String attempt){
        Round ronde = round.get(round.size()-1);
        if(ronde.guess(attempt).contains(".")) {
            return ronde.guess(attempt);
        }else{
            score = score +1;
            return ronde.guess(attempt);
        }

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

    public int getRoundNumber() {
        return roundNumber;
    }
}
