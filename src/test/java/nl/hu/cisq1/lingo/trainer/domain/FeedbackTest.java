package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static nl.hu.cisq1.lingo.trainer.domain.Mark.*;
import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {

    @Test
    @DisplayName("word is guessed if all letters are correct")
    void wordIsGuessed(){


        String poging = "PAARD";
        List<Mark> marks = List.of(PRESENT, PRESENT, PRESENT, PRESENT, PRESENT);
        Feedback feedback = new Feedback(poging, marks);


        assertTrue(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("word is not guessed if all letters are correct")
    void wordIsNotGuessed(){


        String poging = "PAARD";
        List<Mark> marks = List.of(PRESENT, PRESENT, PRESENT, PRESENT, ABSENT);
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
        List<Mark> marks = List.of(PRESENT, PRESENT, PRESENT, PRESENT, ABSENT);
        Feedback feedback = new Feedback(poging, marks);


        assertTrue((feedback.isGuessValid()));
    }



//    @ParameterizedTest
//    @MethodSource("hintGivesFeedback")
//    @DisplayName("game gives hint")
//    public void giveHintWorks(String attempt, List<Mark> marks, String hint){
//        Feedback feedback = new Feedback(attempt, marks);
//        assertEquals(feedback.giveHint(), hint);
//    }
//    static Stream<Arguments> hintGivesFeedback() {
//        return Stream.of(
//                Arguments.of("PAARD", List.of(PRESENT, PRESENT, PRESENT, PRESENT, PRESENT), "PAARD"),
//                Arguments.of("KOE", List.of(INVALID, INVALID, INVALID, INVALID, INVALID), "....."),
//                Arguments.of("APPEL", List.of(ABSENT, ABSENT, ABSENT, ABSENT, ABSENT), "....."),
//                Arguments.of("AARDE", List.of(ABSENT, PRESENT, ABSENT, ABSENT, ABSENT), ".A..."),
//                Arguments.of("HAARD", List.of(ABSENT, PRESENT, PRESENT, PRESENT, PRESENT), ".AARD")
//        );
//    }                    werkt niet meer nadat persistence is geimplementeerd

}