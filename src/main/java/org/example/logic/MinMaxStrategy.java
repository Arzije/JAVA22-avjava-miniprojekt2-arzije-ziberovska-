package org.example.logic;

import org.example.strategy.DecisionStrategy;
import org.example.model.Board;
import org.example.model.Move;
import org.example.model.PlayerMark;

import java.util.ArrayList;
import java.util.List;

public class MinMaxStrategy implements DecisionStrategy {

    @Override
    public Move evaluateBestMove(Board board, PlayerMark currentPlayer) {
        Move bestMove = minMax(board, 0, currentPlayer);

        // Loggar bästa möjliga nästa drag
        System.out.println("Best move for player " + currentPlayer + ": Row = " + bestMove.getRow() + ", Col = " + bestMove.getCol() + ", Score = " + bestMove.getScore());

        return bestMove;
    }

    private Move minMax(Board board, int depth, PlayerMark currentPlayer) {
        if (board.isXWinner()) {
            return new Move(board.getLastPlacedRow(), board.getLastPlacedCol(), 10 - depth);
        }
        if (board.isOWinner()) {
            return new Move(board.getLastPlacedRow(), board.getLastPlacedCol(), depth - 10);
        }
        if (!board.hasEmptyCells() || depth == 9) {
            return new Move(board.getLastPlacedRow(), board.getLastPlacedCol(), 0);
        }

        Move bestMove = new Move(-1, -1, (currentPlayer == PlayerMark.X) ? Integer.MIN_VALUE : Integer.MAX_VALUE);
        List<Board> childBoards = board.generateChildBoards(currentPlayer);

        for (Board childBoard : childBoards) {
            Move currentMove = minMax(childBoard, depth + 1, switchPlayer(currentPlayer));

            if (currentPlayer == PlayerMark.X && currentMove.getScore() > bestMove.getScore()) {
                bestMove = new Move(childBoard.getLastPlacedRow(), childBoard.getLastPlacedCol(), currentMove.getScore());
            } else if (currentPlayer == PlayerMark.O && currentMove.getScore() < bestMove.getScore()) {
                bestMove = new Move(childBoard.getLastPlacedRow(), childBoard.getLastPlacedCol(), currentMove.getScore());
            }
        }

        return bestMove;
    }

    private PlayerMark switchPlayer(PlayerMark currentPlayer) {
        return (currentPlayer == PlayerMark.X) ? PlayerMark.O : PlayerMark.X;
    }
}
