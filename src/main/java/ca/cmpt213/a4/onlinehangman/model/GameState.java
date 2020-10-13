package ca.cmpt213.a4.onlinehangman.model;

/**
 * GameState is use to hold the 3 States of the Game
 */
public enum GameState {
    ACTIVE("Active"),
    WON("Won"),
    LOST("Lost");

    private String stringValue;

    private GameState(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue () {
        return stringValue;
    }
}
