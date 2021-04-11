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
    @DisplayName("kijken of de guess functie het doet")
    void guessWords(String guess, String feedback) {
        Round round = new Round("PAARD");
        assertEquals(round.guess(guess), feedback);

    }

    static Stream<Arguments> provideGuessWords() {
        return Stream.of(
                Arguments.of("PAARD", "PAARD"),
                Arguments.of("KOE", "....."),
                Arguments.of("APPEL", "....."),
                Arguments.of("AARDE", ".A..."),
                Arguments.of("HAARD", ".AARD")

        );
    }



}