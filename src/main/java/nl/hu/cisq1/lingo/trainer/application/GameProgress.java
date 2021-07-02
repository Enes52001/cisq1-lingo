package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.trainer.domain.Feedback;
import nl.hu.cisq1.lingo.trainer.domain.Game;

public class GameProgress {
    private final Long id;
    private final Game.GameState gameState;
    private final int score;
    private final Feedback feedback;
    private final String hint;

    public GameProgress(Long id, Game.GameState gameState, int score, Feedback feedback, String hint) {
        this.id = id;
        this.gameState = gameState;
        this.score = score;
        this.feedback = feedback;
        this.hint = hint;
    }

    public Game.GameState getGameState() {
        return gameState;
    }

    public int getScore() {
        return score;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public String getHint() {
        return hint;
    }
}
