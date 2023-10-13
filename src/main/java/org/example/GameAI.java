package org.example;

public class GameAI {
    private Board board;
    private GameSymbol currentPlayer;

    public GameAI(Board board, GameSymbol currentPlayer) {
        this.board = board;
        this.currentPlayer = currentPlayer;
    }

    public Move bestMove() {
        return MinMaxAlgorithm.minMax(board, currentPlayer);
    }

}
