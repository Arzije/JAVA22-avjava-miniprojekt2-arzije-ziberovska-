package org.example;

public interface BoardOperations {

        void setEmpty(int row, int col);

        boolean isValidMove(int row, int col);

        void placeMark(GameSymbol mark, int row, int col);

    Board copyBoard();

    boolean hasEmptyCells();

    void printBoard();

    GameSymbol getBoardCell(int row, int col);

    boolean isWinner();

    boolean isDraw();

    boolean isXWinner();

    boolean isOWinner();
}
