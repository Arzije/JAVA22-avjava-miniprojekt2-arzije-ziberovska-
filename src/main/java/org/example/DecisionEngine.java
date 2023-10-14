package org.example;

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
