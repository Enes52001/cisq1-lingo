package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

import static nl.hu.cisq1.lingo.trainer.domain.Mark.CORRECT;
import static nl.hu.cisq1.lingo.trainer.domain.Mark.INVALID;

public class Feedback {
    private final String attempt;
    private final List<Mark> marks;

    public Feedback(String attempt, List<Mark> mark) {
        this.attempt = attempt;
        this.marks = mark;
    }

    public String giveHint(){
        String hint = "";
        for(int i = 0; i<marks.size() ; i++ ){
            if(marks.get(i) == CORRECT){
                hint = hint+attempt.charAt(i);
            }else{
                hint=hint+".";
            }
        }
        return hint;
    }



    public boolean isWordGuessed(){
         return marks.stream().allMatch(mark -> mark == CORRECT);
//        for(Mark mark: marks){
//            if(mark != CORRECT){
//                return false;
//            }
//        }
//        return true;
    }

    public String getAttempt() {
        return attempt;
    }

    public boolean isGuessvalid(){
        return marks.stream().noneMatch(mark -> mark == INVALID);
    }

    public boolean isGuessInvalid(){
        return marks.stream().allMatch(mark -> mark == INVALID);
    }
}

