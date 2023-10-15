package org.example.model;

/**
 * Enum representing the possible marks on the Tic Tac Toe board.
 */
public enum PlayerMark {
    X("X"), O("O"), EMPTY(" ");

    private final String mark;

    /**
     * Constructor to initialize the mark.
     */
    PlayerMark(String mark) {
        this.mark = mark;
    }

    /**
     * Returns the string representation of the mark.
     */
    public String getMark() {
        return mark;
    }
}
