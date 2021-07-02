package nl.hu.cisq1.lingo.trainer.domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Round {
    private static final int MAX_ATTEMPTS = 5;

    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection
    private final List<String> hint = new ArrayList<>();

    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Feedback feedback;

    private int attempts = 0;
    private String wordToGuess;

    public Round() {
    }

    public Round(String wordToGuess) {
        this.wordToGuess = wordToGuess;

        this.hint.add(String.valueOf(wordToGuess.charAt(0)));
        for (int i = 1; i < wordToGuess.length(); i++) {
            hint.add(".");
        }
    }

    public Feedback guess(String attempt) {
        attempts = attempts + 1;
        List<Mark> lijst = new ArrayList<Mark>();

        attempt = attempt.toLowerCase();

        // present -> letter zit in het woord, maar op een andere plek
        // voorkom dat letters twee keer worden gemarkt
        // N I E T S
        // N E T T E
        // C P A C A
        if (attempt.length() == wordToGuess.length()) {
            for (int i = 0; i < attempt.length(); i++) {
                if (attempt.charAt(i) == wordToGuess.charAt(i)) {
                    lijst.add(Mark.CORRECT);
                } else {
                    lijst.add(Mark.ABSENT);
                }
            }
        } else {
            for (int i = 0; i < wordToGuess.length(); i++) {
                lijst.add(Mark.INVALID);
            }
        }
        String copyOfWordToGuess = wordToGuess;
        String copyOfAttempt = attempt;
        for (int i = 0; i < copyOfAttempt.length(); i++) {
            if (lijst.get(i) == Mark.ABSENT) {
                for (int a = 0; a < copyOfWordToGuess.length(); a++) {
                    if (lijst.get(a) == Mark.ABSENT){
                        if (copyOfWordToGuess.charAt(a) == copyOfAttempt.charAt(i) & copyOfAttempt.charAt(i) != '.'){
                            lijst.set(i, Mark.PRESENT);
                            copyOfAttempt = replaceCharUsingCharArray(copyOfAttempt, '.', i);
                            copyOfWordToGuess = replaceCharUsingCharArray(copyOfWordToGuess, '.', a);

                        }
                    }           // oplossing op PRESENT probleem
                }
            }
        }

        feedback = new Feedback(attempt, lijst);
        updateHint();

        return feedback;
    }

    public void updateHint() {
        String nextHint = feedback.giveHint();

        for (int i = 0; i < hint.size(); i++) {
            if (nextHint.charAt(i) != '.') {
                hint.set(i, String.valueOf(nextHint.charAt(i)));
            }
        }
    }

    public String getHint() {
        return String.join("", this.hint);
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setWordToGuess(String wordToGuess) {
        this.wordToGuess = wordToGuess;
    }

    public boolean hasAttemptsLeft() {
        return MAX_ATTEMPTS - attempts > 0;
    }

    public int getAttempts() {
        return attempts;
    }

    public int getWordLength() {
        return this.wordToGuess.length();
    }

    public String replaceCharUsingCharArray(String str, char ch, int index) {
        char[] chars = str.toCharArray();
        chars[index] = ch;
        return String.valueOf(chars);
    }
}
