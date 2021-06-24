package nl.hu.cisq1.lingo.trainer.domain;

import java.util.List;

import static nl.hu.cisq1.lingo.trainer.domain.Mark.*;

public class Feedback {
    private final String attempt;
    private final List<Mark> marks;



    public Feedback(String attempt, List<Mark> mark) {
        this.attempt = attempt;
        this.marks = mark;
    }

    public String giveHint(){
//        boolean hintGiven = false;
                 String hint = "";
        for(int i = 0; i<marks.size() ; i++ ){
            if(marks.get(i) == PRESENT){
                hint = hint+attempt.charAt(i);

            }
//            else if(hintGiven == false){
//                hintGiven = true;
//                hint = hint+attempt.charAt(i);
//            }// hier wordt 1 letter dat nog niet is geraden meegegeven
            else{
                hint=hint+".";
            }
        }
        return hint;
    }


    public boolean isWordGuessed(){
         return marks.stream().allMatch(mark -> mark == PRESENT);
//        for(Mark mark: marks){
//            if(mark != CORRECT){
//                return false;
//            }
//        }
//        return true;
    }


    public boolean isGuessvalid(){
        return marks.stream().noneMatch(mark -> mark == INVALID);
    }

    public boolean isGuessInvalid(){
        return marks.stream().allMatch(mark -> mark == INVALID);
    }
}


