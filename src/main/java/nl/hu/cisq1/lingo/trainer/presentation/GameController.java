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

    @PostMapping("/startgame")
    public void startNewGame(){
        gameService.startNewGame();
    }
    @PostMapping("/startround")
    public void startNewRound(@RequestParam int lengte){
        gameService.startNewRound(lengte);

    }

    @PostMapping("/guess")
    public String startNewRound(@RequestParam String guess){
        return gameService.guess( guess);

    }


}
