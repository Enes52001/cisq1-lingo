package nl.hu.cisq1.lingo.trainer.domain;

public class Game {
    private int score;
    private GameState gameState;

    public enum GameState{
        WAITING_FOR_ROUND,
        PLAYING,
        ELIMINATED
    }

    public void startgame(){
        setGameState(GameState.PLAYING);
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }
}
