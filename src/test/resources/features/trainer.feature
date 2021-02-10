  Feature: lingo trainer
    As a participant,
    I want to be able to guess words
    so that i can win



  Scenario: Start a new game
     Given I want to play a game of lingo
     When I start the game
     Then I can start guessing words


    Scenario Outline: Guessing a word
      Given I am playing a game
      And the word is "<word>"
      When i take a guess; "<guess>"
      Then the game gives me feedback; "<feedback>"
      Examples:
        | word | guess | feedback              |
        | cow  | cat   | the letter C is right |

