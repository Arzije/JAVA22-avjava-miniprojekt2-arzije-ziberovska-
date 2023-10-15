package org.example.model;

public enum PlayerMark {
    X("X"), O("O"), EMPTY(" ");

    private final String mark;

    PlayerMark(String mark) {
        this.mark = mark;
    }

    public String getMark() {
        return mark;
    }
}
