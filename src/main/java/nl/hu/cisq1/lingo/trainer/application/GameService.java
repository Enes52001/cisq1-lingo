package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.words.application.WordService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class GameService {
    private WordService wordService;
    private SpringGameRepository gameRepository;

    private Game game;

    public GameService(WordService wordService, SpringGameRepository gameRepository) {
        this.wordService = wordService;
        this.gameRepository = gameRepository;
    }

    public void startNewGame(){
        Game game = new Game();
        setGame(game);
        game.startgame();
    }

    public void startNewRound(int letters){
        game.startNewRound(wordService.provideRandomWord(letters));
    }

    public String guess(String guess){
        return game.makeGuess(guess);
    }
    public String roundStatus(){
        return "\ncurrent: "+game.getRoundStatus();
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

//    private Game getGame(Long id){
//        return gameRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("game bestaat niet"));
//    }
}
