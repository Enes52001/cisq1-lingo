package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;


import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    @DisplayName("kan je inloggen")
    void logInTest(){
        Player speler = new Player("gebruiker", "wachtwoord");

        assertTrue(speler.login("gebruiker", "wachtwoord"));

    }

    @Test
    @DisplayName("verkeerde inloggegevens")
    void verkeerdeLogIn(){
        Player speler = new Player("gebruiker", "wachtwoord");

        assertFalse(speler.login("ja", "nee"));

    }


}