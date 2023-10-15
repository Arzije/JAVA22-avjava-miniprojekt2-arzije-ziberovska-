package org.example.model;

import org.example.operations.BoardOperations;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Tic Tac Toe game board and its operations.
 */
public class Board implements BoardOperations {

    // 3x3 matrix to represent the game board.
    private PlayerMark[][] board = new PlayerMark[3][3];
    private int lastPlacedRow = -1;
    private int lastPlacedCol = -1;

    /**
     * Initializes an empty game board.
     */
    public Board() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = PlayerMark.EMPTY;
            }
        }
    }

    /**
     * Checks if a move is valid based on the given row and column.
     */
    @Override
    public boolean isValidMove(int row, int col) {
        return (row >= 0 && row < 3 && col >= 0 && col < 3) && (board[row][col] == PlayerMark.EMPTY);
    }

    @Override
    public void placeMark(PlayerMark mark, int row, int col) {
        if (isValidMove(row, col)) {
            board[row][col] = mark;
            lastPlacedRow = row;
            lastPlacedCol = col;
        }
    }

    public int getLastPlacedRow() {
        return lastPlacedRow;
    }

    public int getLastPlacedCol() {
        return lastPlacedCol;
    }

    @Override
    public boolean isWinner() {
        return isXWinner() || isOWinner();
    }


    @Override
    public boolean isDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == PlayerMark.EMPTY) {
                    return false;
                }
            }
        }
        return !isWinner();
    }

    @Override
    public boolean isXWinner() {
        return checkWinner(PlayerMark.X);
    }

    @Override
    public boolean isOWinner() {
        return checkWinner(PlayerMark.O);
    }

    /**
     * Checks if the given player mark has a winning combination on the board.
     *
     * @param mark The player mark to check (X or O).
     * @return true if the given player mark has a winning combination, otherwise false.
     */
    private boolean checkWinner(PlayerMark mark) {
        // Check all rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == mark && board[i][1] == mark && board[i][2] == mark)
                return true;
        }

        // Check all columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == mark && board[1][i] == mark && board[2][i] == mark)
                return true;
        }

        // Check main diagonal
        if (board[0][0] == mark && board[1][1] == mark && board[2][2] == mark)
            return true;

        // Check secondary diagonal
        if (board[0][2] == mark && board[1][1] == mark && board[2][0] == mark)
            return true;

        // No winning combination found
        return false;
    }

    /**
     * Creates a copy of the current board.
     */
    @Override
    public Board copyBoard() {
        Board newBoard = new Board();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                newBoard.board[i][j] = this.board[i][j];
            }
        }
        return newBoard;
    }

    /**
     * Checks if there are any empty cells left on the board.
     */
    @Override
    public boolean hasEmptyCells() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == PlayerMark.EMPTY) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns the mark at the specified row and column.
     */
    @Override
    public PlayerMark getBoardCell(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3) {
            return board[row][col];
        } else {
            throw new IllegalArgumentException("Row and column indices must be between 0 and 2, inclusive.");
        }
    }

    /**
     * Generates all possible child boards based on the current board state.
     */
    public List<Board> generateChildBoards(PlayerMark currentPlayer) {
        List<Board> childBoards = new ArrayList<>();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (isValidMove(row, col)) {
                    Board childBoard = this.copyBoard();
                    childBoard.placeMark(currentPlayer, row, col);
                    childBoards.add(childBoard);
                }
            }
        }

        return childBoards;
    }
}
