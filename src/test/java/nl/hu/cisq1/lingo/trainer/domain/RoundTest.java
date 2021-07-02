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

class RoundTest {

    @ParameterizedTest
    @MethodSource("provideGuessWords")
    @DisplayName("give feedback based on guess")
    void guessWords(String wordToGuess, String attempt, List<Mark> expectedFeedback) {
        Round round = new Round(wordToGuess);
        Feedback actualFeedback = round.guess(attempt);
        assertEquals(expectedFeedback, actualFeedback.getMarks());
    }

    static Stream<Arguments> provideGuessWords() {
        return Stream.of(
                Arguments.of("niets", "niets", List.of(CORRECT, CORRECT, CORRECT, CORRECT, CORRECT)),
                Arguments.of("niets", "polos", List.of(ABSENT, ABSENT, ABSENT, ABSENT, CORRECT)),
                Arguments.of("niets", "nette", List.of(CORRECT, PRESENT, ABSENT, CORRECT, ABSENT))

        );
    }



}