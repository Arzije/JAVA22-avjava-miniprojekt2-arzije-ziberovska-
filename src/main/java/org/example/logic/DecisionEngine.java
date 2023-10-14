package org.example.logic;

import org.example.strategy.DecisionStrategy;
import org.example.model.Board;
import org.example.model.Move;
import org.example.model.PlayerMark;

public class DecisionEngine {

        private DecisionStrategy strategy;

        public DecisionEngine() {
            this.strategy = new MinMaxStrategy();
        }

        public Move determineBestMove(Board board, PlayerMark currentMark) {
            return strategy.evaluateBestMove(board, currentMark);
        }

        public void setStrategy(DecisionStrategy strategy) {
            this.strategy = strategy;
        }


}
