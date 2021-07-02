package nl.hu.cisq1.lingo.trainer.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

import static nl.hu.cisq1.lingo.trainer.domain.Mark.INVALID;
import static nl.hu.cisq1.lingo.trainer.domain.Mark.PRESENT;

@Entity
public class Feedback {
    @Id
    @GeneratedValue
    private Long id;
    private String attempt;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Mark> marks;

    public Feedback() {
    }

    public Feedback(String attempt, List<Mark> mark) {
        this.attempt = attempt;
        this.marks = mark;
    }

    public String giveHint() {
//        boolean hintGiven = false;
        String hint = "";
        for (int i = 0; i < marks.size(); i++) {
            if (marks.get(i) == PRESENT) {
                hint = hint + attempt.charAt(i);

            }
//            else if(hintGiven == false){
//                hintGiven = true;
//                hint = hint+attempt.charAt(i);
//            }// hier wordt 1 letter dat nog niet is geraden meegegeven
            else {
                hint = hint + ".";
            }
        }
        return hint;
    }


    public boolean isWordGuessed() {
        return marks.stream().allMatch(mark -> mark == PRESENT);
//        for(Mark mark: marks){
//            if(mark != CORRECT){
//                return false;
//            }
//        }
//        return true;
    }


    public boolean isGuessValid() {
        return marks.stream().noneMatch(mark -> mark == INVALID);
    }

    public boolean isGuessInvalid() {
        return marks.stream().allMatch(mark -> mark == INVALID);
    }

    public String getAttempt() {
        return attempt;
    }

    public List<Mark> getMarks() {
        return marks;
    }
}


