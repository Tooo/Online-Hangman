package ca.cmpt213.a4.onlinehangman.model;

import java.util.ArrayList;

/**
 * Game holds the game logics of a single Hangman game
 */
public class Game {
    private long gameId;
    private String word;
    private String guessingWord;
    private int guessCount;
    private int incorrectCount;
    private GameState gameState;
    private Guess guess;
    private ArrayList<Character> incorrectLetters = new ArrayList<>();

    public Game( ) {

    }

    public Game (long gameId) {
        this.gameId = gameId;
        gameState = GameState.ACTIVE;
        word = generateWord();
        guessingWord = generateGuessingWord();
        guess = new Guess();
    }

    private String generateWord() {
        RandomWord randomWord = RandomWord.getInstance();
        return randomWord.getWord();
    }

    private String generateGuessingWord() {
        StringBuilder emptyWord = new StringBuilder(word);

        for (int i = 0; i < emptyWord.length(); i++) {
            if (emptyWord.charAt(i) != ' ') {
                emptyWord.setCharAt(i, '_');
            }
        }
        return emptyWord.toString();
    }

    public void makeGuess() {
        Character letter = guess.getLetter();
        boolean foundLetter = false;

        if (letter == null|| letter.equals(' ')) {
            return;
        }

        letter = Character.toLowerCase(letter);
        guessCount++;
        StringBuilder guessWord = new StringBuilder(guessingWord);

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                guessWord.setCharAt(i, letter);
                foundLetter = true;
            }
        }

        if (!foundLetter) {
            incorrectLetters.add(letter);
            incorrectCount++;
        }

        guessingWord = guessWord.toString();
        checkGameState();
    }

    public String getIncorrectString() {
        StringBuilder incorrectStringBuilder = new StringBuilder();
        for (Character character : incorrectLetters) {
            incorrectStringBuilder.append(character);
        }
        return incorrectStringBuilder.toString();
    }

    private void checkGameState() {
        if (word.equals(guessingWord)) {
            gameState = GameState.WON;
            return;
        }

        if (incorrectCount > 7) {
            gameState = GameState.LOST;
        }
    }

    public long getGameId() {
        return gameId;
    }

    public String getWord() {
        return word;
    }

    public String getGuessingWord() {
        return guessingWord;
    }

    public int getGuessCount() {
        return guessCount;
    }

    public int getIncorrectCount() {
        return incorrectCount;
    }

    public GameState getGameState() {
        return gameState;
    }

    public Guess getGuess() {
        return guess;
    }

    public void setGuess(Guess guess) {
        this.guess = guess;
    }
}
