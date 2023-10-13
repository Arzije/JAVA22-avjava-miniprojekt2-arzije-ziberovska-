package org.example;

import java.util.ArrayList;
import java.util.List;

public class MinMaxAlgorithm {

    public static Move minMax(Board board, GameSymbol currentPlayer) {
        // Check if X is a winner and return a move with score 1
        if (board.isXWinner()) {
            return new Move(1);
        }
        // Check if O is a winner and return a move with score -1
        if (board.isOWinner()) {
            return new Move(-1);
        }
        // Check if the board is full and return a move with score 0
        if (!board.hasEmptyCells()) {
            return new Move(0);
        }

        // Evaluate all possible moves and their outcomes
        List<Move> moves = evaluateMoves(board, currentPlayer);

        // Find the index of the best move
        int bestMoveIndex = currentPlayer == GameSymbol.X ? findBestMoveForMax(moves) : findBestMoveForMin(moves);

        return moves.get(bestMoveIndex);
    }

    private static List<Move> evaluateMoves(Board board, GameSymbol currentPlayer) {
        List<Move> moves = new ArrayList<>();

        // Loop through each cell in the 3x3 grid
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                // Check if the cell is empty
                if (board.isValidMove(row, col)) {
                    // Evaluate the move and add to list
                    moves.add(evaluateMove(board, row, col, currentPlayer));
                }
            }
        }

        return moves;
    }

    private static Move evaluateMove(Board board, int row, int col, GameSymbol currentPlayer) {
        Move move = new Move(row, col); // Create a new move
        Board boardCopy = board.copyBoard(); // Copy the board to simulate the move

        // Simulate the move
        boardCopy.placeMark(currentPlayer, row, col);
        // Calculate the score of the move
        move.setScore(minMax(boardCopy, switchPlayer(currentPlayer)).getScore());

        // Reset the board cell back to empty
        board.setEmpty(row, col);
        return move;
    }

    private static GameSymbol switchPlayer(GameSymbol currentPlayer) {
        return (currentPlayer == GameSymbol.X) ? GameSymbol.O : GameSymbol.X;
    }

    private static int findBestMoveForMax(List<Move> moves) {
        return findBestMove(moves, Integer.MIN_VALUE, true);
    }

    private static int findBestMoveForMin(List<Move> moves) {
        return findBestMove(moves, Integer.MAX_VALUE, false);
    }

    private static int findBestMove(List<Move> moves, int initialBestScore, boolean isMax) {
        int bestMove = -1; // Initialize to an invalid index
        int bestScore = initialBestScore;

        // Loop through the moves to find the best one
        for (int i = 0; i < moves.size(); i++) {
            int currentScore = moves.get(i).getScore();

            if (isMax ? currentScore > bestScore : currentScore < bestScore) {
                bestScore = currentScore;
                bestMove = i;
            }
        }

        return bestMove;
    }


}
