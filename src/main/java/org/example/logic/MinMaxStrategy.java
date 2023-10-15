package org.example.logic;

import org.example.strategy.DecisionStrategy;
import org.example.model.Board;
import org.example.model.Move;
import org.example.model.PlayerMark;

import java.util.List;

/**
 * MinMaxStrategy implements the MinMax algorithm to determine the best move in the Tic Tac Toe game.
 */
public class MinMaxStrategy implements DecisionStrategy {

    @Override
    /**
     * Evaluates and returns the best move for the current player using the MinMax algorithm.
     * @param board The current game board.
     * @param currentPlayer The current player's mark.
     * @return The best move for the current player.
     */
    public Move evaluateBestMove(Board board, PlayerMark currentPlayer) {
        Move bestMove = minMax(board, 0, currentPlayer);

        // Logs best possible move
        System.out.println("Best move for player " + currentPlayer + ": Row = " + bestMove.row() + ", Col = " + bestMove.col() + ", Score = " + bestMove.score());

        return bestMove;
    }

    /**
     * Recursive function to determine the best move using the MinMax algorithm.
     * @param board The current game board.
     * @param depth The current depth of the recursion.
     * @param currentPlayer The current player's mark.
     * @return The best move for the current player.
     */
    private Move minMax(Board board, int depth, PlayerMark currentPlayer) {

        // Base cases: Check for terminal states of the game
        if (board.isXWinner()) {
            return new Move(board.getLastPlacedRow(), board.getLastPlacedCol(), 10 - depth);
        }
        if (board.isOWinner()) {
            return new Move(board.getLastPlacedRow(), board.getLastPlacedCol(), depth - 10);
        }
        if (!board.hasEmptyCells() || depth == 9) {
            return new Move(board.getLastPlacedRow(), board.getLastPlacedCol(), 0);
        }

        // Initialize the best move. For X, we want the maximum score, and for O, we want the minimum score.
        Move bestMove = new Move(-1, -1, (currentPlayer == PlayerMark.X) ? Integer.MIN_VALUE : Integer.MAX_VALUE);

        // Generate all possible moves (child boards) for the current player
        List<Board> childBoards = board.generateChildBoards(currentPlayer);

        // Recursively evaluate each move
        for (Board childBoard : childBoards) {
            Move currentMove = minMax(childBoard, depth + 1, switchPlayer(currentPlayer));

            // If current player is X, we want to maximize the score
            if (currentPlayer == PlayerMark.X && currentMove.score() > bestMove.score()) {
                bestMove = new Move(childBoard.getLastPlacedRow(), childBoard.getLastPlacedCol(), currentMove.score());

                // If current player is O, we want to minimize the score
            } else if (currentPlayer == PlayerMark.O && currentMove.score() < bestMove.score()) {
                bestMove = new Move(childBoard.getLastPlacedRow(), childBoard.getLastPlacedCol(), currentMove.score());
            }
        }

        return bestMove;
    }

    /**
     * Switches the player.
     * @param currentPlayer The current player's mark.
     * @return The opposite player's mark.
     */
    private PlayerMark switchPlayer(PlayerMark currentPlayer) {
        return (currentPlayer == PlayerMark.X) ? PlayerMark.O : PlayerMark.X;
    }
}
