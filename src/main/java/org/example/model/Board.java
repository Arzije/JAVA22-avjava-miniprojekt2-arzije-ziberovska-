package org.example.model;

import org.example.operations.BoardOperations;

import java.util.ArrayList;
import java.util.List;

public class Board implements BoardOperations {

    private PlayerMark[][] board = new PlayerMark[3][3];
    private int lastPlacedRow = -1;
    private int lastPlacedCol = -1;

    public Board() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = PlayerMark.EMPTY;
            }
        }
    }

//    @Override
//    public void setEmpty(int row, int col) {
//        if (row >= 0 && row < 3 && col >= 0 && col < 3) {
//            board[row][col] = PlayerMark.EMPTY;
//        }
//    }

    @Override
    public boolean isValidMove(int row, int col) {
        // Validate indices and cell emptiness
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

    private boolean checkWinner(PlayerMark mark) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == mark && board[i][1] == mark && board[i][2] == mark)
                return true;
            if (board[0][i] == mark && board[1][i] == mark && board[2][i] == mark)
                return true;
        }
        if (board[0][0] == mark && board[1][1] == mark && board[2][2] == mark)
            return true;
        if (board[0][2] == mark && board[1][1] == mark && board[2][0] == mark)
            return true;
        return false;
    }

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

//    @Override
//    public void printBoard() {
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                System.out.print(board[i][j] == PlayerMark.EMPTY ? "-" : board[i][j]);
//                if (j < 2)
//                    System.out.print("|");
//            }
//            System.out.println();
//        }
//    }

    @Override
    public PlayerMark getBoardCell(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3) {
            return board[row][col];
        } else {
            throw new IllegalArgumentException("Row and column indices must be between 0 and 2, inclusive.");
        }
    }

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
