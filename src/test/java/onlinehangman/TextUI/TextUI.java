package onlinehangman.TextUI;

import ca.cmpt213.a4.onlinehangman.model.Game;
import ca.cmpt213.a4.onlinehangman.model.GameState;

import java.util.Scanner;

// Name: Travis Chan, ID: 301358957, Email: tsc7@sfu.ca

/**
 * TextUI is used to print out Hangman Game info in Text form
 */
public class TextUI {

    public void printGameInfo(Game game) {
        GameState gameState = game.getGameState();
        System.out.println("Status: " + convertStatus(gameState));
        System.out.println("Progress: " + game.getGuessingWord());
        System.out.print("You have made " + game.getGuessCount());
        System.out.println(", out of which " + game.getIncorrectCount() + " are incorrect.");
    }

    private String convertStatus(GameState gameState) {
        switch(gameState) {
            case ACTIVE:
                return "Active";
            case WON:
                return "Won";
            case LOST:
                return "Lost";
            default:
                return " ";
        }
    }

    public char getInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Your guess: ");

        String line = scanner.nextLine();
        return line.charAt(0);
    }

    public void printFinalMessage(Game game) {
        GameState gameState = game.getGameState();

        if (gameState == GameState.WON) {
            System.out.println("Congrats! you won");
        } else if (gameState == GameState.LOST) {
            System.out.println("Sorry you lost!");
            System.out.println("The word was " + game.getWord());
        }
    }
}
