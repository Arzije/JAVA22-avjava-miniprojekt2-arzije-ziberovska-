package org.example;

public interface DecisionStrategy {
    Move evaluateBestMove(Board board, PlayerMark currentPlayer);

}
