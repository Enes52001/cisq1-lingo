package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.AssertTrue;
import java.util.List;

import static nl.hu.cisq1.lingo.trainer.domain.Mark.*;
import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {

    @Test
    @DisplayName("word is guessed if all letters are correct")
    void wordIsGuessed(){


        String poging = "PAARD";
        List<Mark> marks = List.of(CORRECT, CORRECT, CORRECT, CORRECT, CORRECT);
        Feedback feedback = new Feedback(poging, marks);


        assertTrue(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("word is not guessed if all letters are correct")
    void wordIsNotGuessed(){


        String poging = "PAARD";
        List<Mark> marks = List.of(CORRECT, CORRECT, CORRECT, CORRECT, ABSENT);
        Feedback feedback = new Feedback(poging, marks);


        assertFalse((feedback.isWordGuessed()));
    }

    @Test
    @DisplayName("guess is invalid if word is not exactly 5 letters long")
    void guessIsInvalid(){


        String poging = "PAARD";
        List<Mark> marks = List.of(INVALID, INVALID, INVALID, INVALID);
        Feedback feedback = new Feedback(poging, marks);


        assertTrue((feedback.isGuessInvalid()));
    }
    @Test
    @DisplayName("guess is valid if there are 5 letters")
    void guessIsValid(){


        String poging = "PAARD";
        List<Mark> marks = List.of(CORRECT, CORRECT, CORRECT, CORRECT, ABSENT);
        Feedback feedback = new Feedback(poging, marks);


        assertTrue((feedback.isGuessvalid()));
    }
    @Test
    @DisplayName("als er ")
    void geeftHint(){


        String poging = "PAARD";
        List<Mark> marks = List.of(CORRECT, CORRECT, CORRECT, CORRECT, ABSENT);
        Feedback feedback = new Feedback(poging, marks);


        assertTrue((feedback.isGuessvalid()));
    }
}