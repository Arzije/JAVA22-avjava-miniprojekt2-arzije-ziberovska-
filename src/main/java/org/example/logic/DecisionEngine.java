package org.example.logic;

import org.example.strategy.DecisionStrategy;
import org.example.model.Board;
import org.example.model.Move;
import org.example.model.PlayerMark;

    /**
     * DecisionEngine is responsible for determining the best move using a given strategy.
     */
public class DecisionEngine {
    private DecisionStrategy strategy;

    /**
     * Constructor initializes the decision engine with the MinMaxStrategy.
     */
    public DecisionEngine() {
        this.strategy = new MinMaxStrategy();
    }

    /**
     * Determines the best move for the current player on the given board.
     *
     * @param board       The current game board.
     * @param currentMark The current player's mark.
     * @return The best move for the current player.
     */
    public Move determineBestMove(Board board, PlayerMark currentMark) {
        return strategy.evaluateBestMove(board, currentMark);
    }

}
