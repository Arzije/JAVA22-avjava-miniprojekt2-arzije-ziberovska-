package org.example.strategy;

import org.example.model.Board;
import org.example.model.Move;
import org.example.model.PlayerMark;

/**
 * Interface defining the strategy to evaluate the best move in Tic Tac Toe.
 */
public interface DecisionStrategy {
    Move evaluateBestMove(Board board, PlayerMark currentPlayer);

}
