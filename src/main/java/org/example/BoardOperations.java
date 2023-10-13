package org.example;

public interface BoardOperations {

    void setEmpty(int row, int col);

    boolean isValidMove(int row, int col);

    void placeMark(PlayerMark mark, int row, int col);

    Board copyBoard();

    boolean hasEmptyCells();

//    void printBoard();

    PlayerMark getBoardCell(int row, int col);

    boolean isWinner();

    boolean isDraw();

    boolean isXWinner();

    boolean isOWinner();
}
