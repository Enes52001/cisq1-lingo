//package nl.hu.cisq1.lingo.trainer.application;
//
//import nl.hu.cisq1.lingo.CiTestConfiguration;
//import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
//import nl.hu.cisq1.lingo.trainer.domain.Game;
//import nl.hu.cisq1.lingo.trainer.domain.Round;
//import nl.hu.cisq1.lingo.words.application.WordService;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.Arguments;
//import org.junit.jupiter.params.provider.MethodSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Import;
//
//import java.io.IOException;
//import java.util.stream.Stream;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//@Import(CiTestConfiguration.class)
//@SpringBootTest
////@Import(CiTestConfiguration.class)
////@AutoConfigureMockMvc
//class GameServiceIntegrationTest {
//    @Autowired
//    private GameService service;
//
//    @BeforeEach
//    void beforeEachTest() throws IOException {
//        service.startNewGame((long) 1);
//    }
//
//
//    @Test
//    void startNewGameWorks() {
//        assertEquals(Game.GameState.WAITING_FOR_ROUND, service.getGameById(1).getGameState());
//    }
//
//    @Test
//    void startNewRound() {
//        service.startNewRound(5, (long) 1);
//        assertEquals(Game.GameState.PLAYING, service.getGameById(1).getGameState());
//    }
//
//    @MethodSource("provideGuessWords")
//    @ParameterizedTest
//    void guessFeedbackIsRight(String guess, String feedback) {
//        service.startNewRound(5, (long) 1);
//        service.getGameById(1).getLastRound().setWordToGuess("PAARD");
//        assertEquals(service.guess(1, guess), feedback );
//    }
//
//    static Stream<Arguments> provideGuessWords() {
//        return Stream.of(
//                Arguments.of("PAARD", "PAARD"),
//                Arguments.of("KOE", "....."),
//                Arguments.of("APPEL", "....."),
//                Arguments.of("AARDE", ".A..."),
//                Arguments.of("HAARD", ".AARD")
//
//        );
//    }
//
//}