package nl.hu.cisq1.lingo.trainer.presentation;

import nl.hu.cisq1.lingo.trainer.application.GameProgress;
import nl.hu.cisq1.lingo.trainer.application.GameService;
import nl.hu.cisq1.lingo.trainer.presentation.dto.Guess;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.net.http.HttpConnectTimeoutException;
import java.net.http.HttpRequest;

@RestController
@RequestMapping("/lingo")
public class GameController {
    private final GameService gameService;


    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/game")
    public GameProgress startNewGame() {
        return gameService.startNewGame();


    }

    @PostMapping("/game/{id}/round")
    public GameProgress startNewRound(@PathVariable Long id) throws HttpClientErrorException {
        // als ID niet bestaat in database, 404 error returnen
        return gameService.startNewRound(id);
    }

    @PostMapping("/game/{id}/guess")
    public GameProgress Guess(@PathVariable Long id, @RequestBody Guess guess) throws HttpClientErrorException {
        return gameService.guess(id, guess.getGuess());
    }
}
