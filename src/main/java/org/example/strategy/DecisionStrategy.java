package org.example.strategy;

import org.example.model.Board;
import org.example.model.Move;
import org.example.model.PlayerMark;

public interface DecisionStrategy {
    Move evaluateBestMove(Board board, PlayerMark currentPlayer);

}
