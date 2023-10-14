package org.example.model;

public class Move {
        private int row;
        private int col;
        private int score;

//        public Move(int score) {
//            this.score = score;
//        }

        public Move(int row, int col, int score) {
            this.row = row;
            this.col = col;
            this.score = score;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getCol() {
            return col;
        }

        public void setCol(int col) {
            this.col = col;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

}
