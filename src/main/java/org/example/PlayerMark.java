package org.example;

public enum PlayerMark {
    X("X"), O("O"), EMPTY(" ");

    private final String symbol;

    PlayerMark(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
