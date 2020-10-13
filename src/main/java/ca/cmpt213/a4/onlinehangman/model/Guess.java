package ca.cmpt213.a4.onlinehangman.model;

/**
 * Guess is used to hold the guessing letter
 * It the object being taken and recieve by Thymeleaf to get the letter
 */
public class Guess {
    private Character letter = ' ';

    public Character getLetter() {
        return letter;
    }

    public void setLetter(Character letter) {
        this.letter = letter;
    }
}
