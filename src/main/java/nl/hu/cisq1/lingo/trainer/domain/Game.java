package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.application.GameProgress;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Game {
    @Id
    @GeneratedValue
    private long id;
    private int score = 0;

    @Enumerated(value = EnumType.STRING)
    private GameState gameState = GameState.WAITING_FOR_GAME;

    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Round> rounds = new ArrayList<>();

    public int getNextWordLength() {
        Round round = getCurrentRound();
        if (round == null) {
            return 5;
        }

        int wordLength = round.getWordLength();
        if (wordLength == 5) {
            return 6;
        }

        if (wordLength == 6) {
            return 7;
        }

        return 5;
    }

    public GameProgress showProgress() {
        Round currentRound = this.getCurrentRound();

        if (currentRound == null) {
            return new GameProgress(id, gameState, score, null, null);
        }

        return new GameProgress(id, gameState, score, currentRound.getFeedback(), currentRound.getHint());
    }



    public enum GameState {
        WAITING_FOR_GAME,
        WAITING_FOR_ROUND,
        PLAYING,
        ELIMINATED,
    }

    public void startGame() {
        if (gameState != GameState.WAITING_FOR_GAME) {
            // error
            return;
        }

        gameState = GameState.WAITING_FOR_ROUND;
    }

    public void startNewRound(String wordToGuess) {
        if (gameState != GameState.WAITING_FOR_ROUND) {
            // error
            return;
        }
        gameState = GameState.PLAYING;
        Round ronde = new Round(wordToGuess);
        ronde.setWordToGuess(wordToGuess);
        rounds.add(ronde);
    }

    public void makeGuess(String attempt) {
        if (gameState != GameState.PLAYING) {
            // error
            return;
        }

        Round round = this.getCurrentRound();

        Feedback feedback = round.guess(attempt);
        if (feedback.isWordGuessed()) {
            gameState = GameState.WAITING_FOR_ROUND;
            score = score + (5 * ((5 - round.getAttempts()) + 5));
        } else if (!round.hasAttemptsLeft()) {
            gameState = GameState.ELIMINATED;
        }
    }

    private Round getCurrentRound() {
        if (this.rounds.isEmpty()) {
            return null;
        }

        return this.rounds.get(this.rounds.size() - 1);
    }

    public long getId() {
        return id;
    }
}
