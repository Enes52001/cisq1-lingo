package nl.hu.cisq1.lingo.trainer.domain;

import java.util.ArrayList;
import java.util.List;


public class Round {
    private int attempts = 0;
    private Feedback feedback;
    private String wordToGuess;
    private List<String> trueMarks = new ArrayList<>();

    public Round(String wordToGuess){
        this.wordToGuess = wordToGuess;

        for (int i = 0; i<wordToGuess.length(); i++){
            trueMarks.add(".");
        }
    }

    public String guess(String attempt){
        attempts = attempts + 1;
        List<Mark> lijst = new ArrayList<Mark>();
        attempt = attempt.toUpperCase();
        if(attempt.length() == wordToGuess.length()){
            for(int i = 0; i<attempt.length() ; i++ ){
                if(attempt.charAt(i) == wordToGuess.charAt(i)){
                    lijst.add(Mark.PRESENT);
                }else{
                    lijst.add(Mark.ABSENT);
                }
            }
            feedback = new Feedback(attempt, lijst);
            updateTrueMarks();
            return feedback.giveHint();
        }else{
            for(int i = 0; i<wordToGuess.length() ; i++ ){
                lijst.add(Mark.INVALID);
            }
            Feedback feedback = new Feedback(attempt, lijst);
            updateTrueMarks();
            return feedback.giveHint();
        }

    }

    public void updateTrueMarks(){
        for(int i = 0; i<wordToGuess.length() ; i++ ) {
            if(String.valueOf(feedback.giveHint().charAt(i)) != "."){
                if (trueMarks.get(i).equals(".")){
                    trueMarks.set(i, String.valueOf(feedback.giveHint().charAt(i)));
                }
            }
        }
    }

    public String getFeedbackStatus(){
        String hint = "";

        for(int i = 0; i<trueMarks.size() ; i++ ) {
            hint = hint+ trueMarks.get(i);
        }
            return "\ncurrent status: "+hint;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setWordToGuess(String wordToGuess) {
        this.wordToGuess = wordToGuess;
    }

    public int getAttempts() {
        return attempts;
    }
}
