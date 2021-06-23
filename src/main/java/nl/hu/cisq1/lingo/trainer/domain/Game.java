package nl.hu.cisq1.lingo.trainer.domain;

import javax.persistence.*;
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

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private transient List<Round> rounds = new ArrayList<>();


    public enum GameState{
        WAITING_FOR_ROUND,
        PLAYING,
        ELIMINATED,
        WIN
    }


    public void startgame(){
        setGameState(GameState.WAITING_FOR_ROUND);
    }

    public Round startNewRound(String wordToGuess) {
        if (gameState == GameState.ELIMINATED | gameState == GameState.WIN) {
            return null;
        }
        setGameState(GameState.PLAYING);
        Round ronde = new Round(wordToGuess);
        ronde.setWordToGuess(wordToGuess);
        rounds.add(ronde);
        roundNumber = (rounds.size());
        return ronde;
    }

    public String makeGuess(String attempt){
        Round ronde = rounds.get(rounds.size()-1);
        if(ronde.getAttempts()>5){
            return "you are out of attempts, start a new round";
        }
        else if(ronde.guess(attempt).contains(".")) {
            return ronde.guess(attempt);
        }else{
            score = score +( 5 * (( 5 - ronde.getAttempts()) + 5 ));
            setGameState(GameState.WIN);
            return ronde.guess(attempt);
        }

    }

    public String getRoundStatus(){
        Round ronde = rounds.get(rounds.size()-1);
        return ronde.getFeedback().getFeedbackStatus();
    }

    public Round getLastRound() {
        return rounds.get(rounds.size()-1);
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
