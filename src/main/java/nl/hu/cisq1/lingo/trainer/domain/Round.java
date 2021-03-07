package nl.hu.cisq1.lingo.trainer.domain;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private int attempts = 0;
    private Feedback feedback;
    private String wordToGuess;

    public Round(String wordToGuess){
        this.wordToGuess = wordToGuess;

    }

    public List<Mark> guess(String attempt){
        attempts = attempts + 1;
        List<Mark> lijst = new ArrayList<Mark>();
        if(attempt.length() == wordToGuess.length()){
            for(int i = 0; i<attempt.length() ; i++ ){
                if(attempt.charAt(i) == wordToGuess.charAt(i)){
                    lijst.add(Mark.CORRECT);
                }else{
                    lijst.add(Mark.ABSENT);
                }
            }
            Feedback feedback = new Feedback(attempt, lijst);
            feedback.giveHint();
            return lijst;
        }else{
            for(int i = 0; i<wordToGuess.length() ; i++ ){
                lijst.add(Mark.INVALID);
            }
            return lijst;
        }

    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

}
