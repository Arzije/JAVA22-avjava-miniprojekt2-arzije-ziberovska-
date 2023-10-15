package org.example.operations;

import org.example.model.Board;
import org.example.model.PlayerMark;

/**
 * Interface defining operations that can be performed on a Tic Tac Toe board.
 */
public interface BoardOperations {

    boolean isValidMove(int row, int col);

    void placeMark(PlayerMark mark, int row, int col);

    Board copyBoard();

    boolean hasEmptyCells();

    PlayerMark getBoardCell(int row, int col);

    boolean isWinner();

    boolean isDraw();

    boolean isXWinner();

    boolean isOWinner();
}
