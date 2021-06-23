package nl.hu.cisq1.lingo.trainer.presentation;

import nl.hu.cisq1.lingo.trainer.application.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lingo")
public class GameController {
    @Autowired
    private GameService gameService;
    private int lengte = 5;

    @PostMapping("/startgame")
    public void startNewGame(){
        gameService.startNewGame();
    }
    @PostMapping("/startround")
    public void startNewRound(){
        gameService.startNewRound(lengte);
        if (lengte == 5){
            lengte = 6;
        }
        else if(lengte == 6){
            lengte = 7;
        }else if(lengte == 7){
            lengte = 5;
        }
        // na een 5-letterwoord hoort een 6-letterwoord te komen.
        // Als dat woord goed wordt geraden komt een 7-letterwoord en daarna weer een 5-letterwoord.

    }

    @PostMapping("/guess")
    public String Guess(@RequestParam String guess){
        return gameService.guess(guess);

    }


}
