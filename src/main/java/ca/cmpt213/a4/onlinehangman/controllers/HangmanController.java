package ca.cmpt213.a4.onlinehangman.controllers;

import ca.cmpt213.a4.onlinehangman.model.Game;
import ca.cmpt213.a4.onlinehangman.model.GameState;
import ca.cmpt213.a4.onlinehangman.model.Guess;
import ca.cmpt213.a4.onlinehangman.model.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * HangmanController is SpringBoot controller for this game
 * Handles the Get and Post mappings, as well as a exception handler
 */
@Controller
public class HangmanController {
    private Message promptMessage;
    private AtomicLong gameId = new AtomicLong();
    private ArrayList<Game> games = new ArrayList<>();
    private Game currentGame;


    @PostConstruct
    public void hangmanControllerInit() {
        promptMessage = new Message("Initializing...");
    }

    @GetMapping("/helloworld")
    public String showHelloworldPage(Model model) {

        promptMessage.setMessage("You are at the helloworld page!");
        model.addAttribute("promptMessage", promptMessage);

        return "helloworld";
    }

    @GetMapping("/welcome")
    public String showWelcomePage(Model model) {
        promptMessage.setMessage("Welcome to Online Hangman Game");
        model.addAttribute("promptMessage", promptMessage);

        return "welcome";
    }


    @PostMapping("/game")
    public String showGameStart(Model model) {
        Game game = new Game(gameId.incrementAndGet());
        games.add(game);
        currentGame = game;

        model.addAttribute("game", game);
        model.addAttribute("guess", game.getGuess());

        return "game";
    }

    @PostMapping("/game/{id}")
    public String showGameProgress(@ModelAttribute Guess guess,  Model model) {
        currentGame.setGuess(guess);
        currentGame.makeGuess();

        model.addAttribute("game", currentGame);
        model.addAttribute("guess", currentGame.getGuess());

        return checkGameStatus(model, currentGame);
    }

    @GetMapping("/game/{id}")
    public String showPreviousGame(@PathVariable("id") long gameId, Model model) {
        for (Game game : games) {
            if (game.getGameId() == gameId) {
                currentGame = game;

                model.addAttribute("game", game);
                return checkGameStatus(model, game);
            }
        }
        throw new GameNotFoundException();
    }

    private String checkGameStatus(Model model, Game game) {
        GameState gameState = game.getGameState();

        if (gameState == GameState.ACTIVE) {
            return "game";
        }

        if (gameState == GameState.LOST) {
            promptMessage.setMessage("Sorry you have lost!");
        } else {
            promptMessage.setMessage("Congrats, you have won!");
        }

        model.addAttribute("promptMessage", promptMessage);
        return "gameover";
    }

    @ExceptionHandler(value=GameNotFoundException.class)
    public String gameNotFound() {
        return "gamenotfound";
    }
}