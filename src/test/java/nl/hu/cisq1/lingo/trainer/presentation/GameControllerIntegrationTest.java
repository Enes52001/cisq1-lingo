package nl.hu.cisq1.lingo.trainer.presentation;

import nl.hu.cisq1.lingo.CiTestConfiguration;
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

    @Test
    @DisplayName("start a new game")
    void startNewGameWorks() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/lingo/startgame");

        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("start a new round")
    void startNewRoundWorks() throws Exception {
        RequestBuilder request1 = MockMvcRequestBuilders
                .post("/lingo/startgame");

        mockMvc.perform(request1);
        //er moet een game gestart zijn om round te kunnen starten

        RequestBuilder request = MockMvcRequestBuilders
                .post("/lingo/startround")
                .param("lengte", "5");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                ;
    }

    @Test
    @DisplayName("make a guess")
    void guessWorks() throws Exception {

        RequestBuilder request1 = MockMvcRequestBuilders
                .post("/lingo/startgame");

        mockMvc.perform(request1);
        //er moet een game gestart zijn om round te kunnen starten

        RequestBuilder request2 = MockMvcRequestBuilders
                .post("/lingo/startround")
                .param("lengte", "5");

        mockMvc.perform(request2);
        //game en round moeten gestart zijn om guess te testen




        RequestBuilder request = MockMvcRequestBuilders
                .post("/lingo/guess")
                .param("guess", "PAARD");

        mockMvc.perform(request)
                .andExpect(status().isOk());
    }
}