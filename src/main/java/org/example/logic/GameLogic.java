package org.example.logic;

import org.example.model.Board;
import org.example.model.Move;
import org.example.model.PlayerMark;

public class GameLogic {

    private Board board;
    private DecisionEngine decisionEngine;
    private PlayerMark currentPlayer;

    public GameLogic() {
        this.board = new Board();
        this.decisionEngine = new DecisionEngine();
        this.currentPlayer = PlayerMark.X;
    }

    public Move provideHint() {
        return decisionEngine.determineBestMove(board, currentPlayer);
    }

    public boolean isValidMove(int row, int col) {
        return board.isValidMove(row, col);
    }

    public void makeMove(int row, int col) {
        if (isValidMove(row, col)) {
            board.placeMark(currentPlayer, row, col);
            switchPlayer();
        }
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == PlayerMark.X) ? PlayerMark.O : PlayerMark.X;
    }

    public boolean isWinner() {
        return board.isWinner();
    }

    public boolean isDraw() {
        return board.isDraw();
    }

    public void restartGame() {
        board = new Board();
        currentPlayer = PlayerMark.X;
    }

    public String getBoardCellMark(int row, int col) {
        return board.getBoardCell(row, col).getSymbol();
    }

    public Move aiMove() {
        return decisionEngine.determineBestMove(board, currentPlayer);
    }

    public PlayerMark getCurrentPlayer() {
        return currentPlayer;
    }
}
