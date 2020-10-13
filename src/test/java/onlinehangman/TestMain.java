package onlinehangman;

import ca.cmpt213.a4.onlinehangman.model.Game;
import ca.cmpt213.a4.onlinehangman.model.GameState;
import onlinehangman.TextUI.TextUI;

// Name: Travis Chan, ID: 301358957, Email: tsc7@sfu.ca

/**
 * TestMain use to test the game in Text
 */
public class TestMain {

    public static void main (String[] args) {
        Game game = new Game(1);
        TextUI textUI = new TextUI();

        while (game.getGameState() == GameState.ACTIVE) {
            textUI.printGameInfo(game);
            char letter = textUI.getInput();
            game.getGuess().setLetter(letter);
            game.makeGuess();
        }
        textUI.printFinalMessage(game);
    }
}
