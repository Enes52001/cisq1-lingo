//package nl.hu.cisq1.lingo.trainer.application;
//
//import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
//import nl.hu.cisq1.lingo.trainer.domain.Game;
//import nl.hu.cisq1.lingo.words.application.WordService;
//import org.junit.jupiter.api.Test;
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//class GameServiceTest {
//
//    @Test
//    void startNewGameWorks() {
//        WordService wordService = mock(WordService.class);
//        SpringGameRepository gameRepository = mock(SpringGameRepository.class);
//        GameService service = new GameService(wordService, gameRepository);
//        service.startNewGame();
//        assertEquals(Game.GameState.WAITING_FOR_ROUND, service.getGame().getGameState());
//    }
//
//    @Test
//    void startNewRound() {
//        WordService wordService = mock(WordService.class);
//        SpringGameRepository gameRepository = mock(SpringGameRepository.class);
//        GameService service = new GameService(wordService, gameRepository);
//        service.startNewGame();
//        service.startNewRound(5);
//        assertEquals(Game.GameState.PLAYING, service.getGame().getGameState());
//    }
//
//    @Test
//    void guessIsRight() {
//        WordService wordService = mock(WordService.class);
//        SpringGameRepository gameRepository = mock(SpringGameRepository.class);
//        GameService service = new GameService(wordService, gameRepository);
//        service.startNewGame();
//        service.startNewRound(5);
//        when(wordService.provideRandomWord(5)).thenReturn("APPEL");
//        service.getGame().getLastRound().setWordToGuess("APPEL");
//        assertEquals(service.guess("APPEL"), "APPEL" );
//    }
//}