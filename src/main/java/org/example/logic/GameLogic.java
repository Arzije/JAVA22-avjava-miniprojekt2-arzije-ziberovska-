package org.example.logic;

import org.example.model.Board;
import org.example.model.Move;
import org.example.model.PlayerMark;

/**
 * GameLogic handles the game's logic, including making moves, checking game status, and providing hints.
 */
public class GameLogic {
    private Board board;
    private DecisionEngine decisionEngine;
    private PlayerMark currentPlayer;

    /**
     * Constructor initializes the game logic with a new board, decision engine, and sets the current player to X.
     */
    public GameLogic() {
        this.board = new Board();
        this.decisionEngine = new DecisionEngine();
        this.currentPlayer = PlayerMark.X;
    }

    /**
     * Checks if a move is valid.
     * @param row The row of the move.
     * @param col The column of the move.
     * @return True if the move is valid, false otherwise.
     */
    public boolean isValidMove(int row, int col) {
        return board.isValidMove(row, col);
    }

    /**
     * Makes a move on the board.
     * @param row The row of the move.
     * @param col The column of the move.
     */
    public void makeMove(int row, int col) {
        if (isValidMove(row, col)) {
            board.placeMark(currentPlayer, row, col);
            switchPlayer();
        }
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == PlayerMark.X) ? PlayerMark.O : PlayerMark.X;
    }

    /**
     * Checks if there's a winner.
     * @return True if there's a winner, false otherwise.
     */
    public boolean isWinner() {
        return board.isWinner();
    }

    /**
     * Checks if the game is a draw.
     * @return True if the game is a draw, false otherwise.
     */
    public boolean isDraw() {
        return board.isDraw();
    }

    public void restartGame() {
        board = new Board();
        currentPlayer = PlayerMark.X;
    }

    /**
     * Gets the mark of a cell on the board.
     * @param row The row of the cell.
     * @param col The column of the cell.
     * @return The mark of the cell.
     */
    public String getBoardCellMark(int row, int col) {
        return board.getBoardCell(row, col).getMark();
    }


    public PlayerMark getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Provides a hint by determining the best move.
     * @return The best move for the current player.
     */
    public Move provideHint() {
        return decisionEngine.determineBestMove(board, currentPlayer);
    }

}
