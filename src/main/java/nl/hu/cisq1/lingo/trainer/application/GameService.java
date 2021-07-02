package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.words.application.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class GameService {
    private WordService wordService;
    private final SpringGameRepository gameRepository;

    public GameService(WordService wordService, SpringGameRepository gameRepository) {
        this.wordService = wordService;
        this.gameRepository = gameRepository;
    }

    public GameProgress startNewGame() {
        Game game = new Game();

        game.startGame();
        this.gameRepository.save(game);

        return game.showProgress();
    }

    public GameProgress startNewRound(Long id) {
        Game game = this.getGame(id);

        String wordToGuess = wordService.provideRandomWord(game.getNextWordLength());
        game.startNewRound(wordToGuess);
        this.gameRepository.save(game);

        return game.showProgress();
    }

    public GameProgress guess(long id, String attempt) {
        Game game = this.getGame(id);

        game.makeGuess(attempt);
        this.gameRepository.save(game);

        return game.showProgress();
    }

    private Game getGame(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("game bestaat niet"));
    }
}
