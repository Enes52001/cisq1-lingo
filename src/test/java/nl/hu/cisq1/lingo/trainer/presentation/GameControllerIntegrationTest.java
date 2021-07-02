package nl.hu.cisq1.lingo.trainer.presentation;

import nl.hu.cisq1.lingo.CiTestConfiguration;
import nl.hu.cisq1.lingo.trainer.application.GameService;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@Import(CiTestConfiguration.class)
@AutoConfigureMockMvc
class GameControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private GameService gameService;

    @Test
    @DisplayName("start a new game and see if it's actually started")
    void startNewGameWorks() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/lingo/game");

        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("start a new round and see if it's actually started")
    void startNewRoundWorks() throws Exception {
        Game game = new Game();
        gameService.saveGame(game);
        RequestBuilder request = MockMvcRequestBuilders
                .post("/lingo/game/"+ ((int) game.getId())+"/round");

        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("make a guess")
    void guessWorks() throws Exception {
        Game game = new Game();
        gameService.saveGame(game);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/lingo/game/"+ game.getId()+"/round"));
        //er moet een round gestart zijn om te kunnen guessen

        RequestBuilder request = MockMvcRequestBuilders
                .post("/lingo/game/"+ game.getId()+"/guess")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"guess\": \"your_guess\"\n" +
                        "}");


        mockMvc.perform(request);
        //game en round moeten gestart zijn om guess te testen
    }
}