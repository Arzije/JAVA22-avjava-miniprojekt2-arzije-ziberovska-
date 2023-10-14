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



//    @Override
//    public Move evaluateBestMove(Board board, PlayerMark currentPlayer) {
//
//        if (board.isXWinner()) {
//            return new Move(1);
//        }
//        if (board.isOWinner()) {
//            return new Move(-1);
//        }
//        if (!board.hasEmptyCells()) {
//            return new Move(0);
//        }
//
//        List<Move> moves = evaluateMoves(board, currentPlayer);
//
//        int bestMoveIndex = currentPlayer == PlayerMark.X ? findBestMoveForMax(moves) : findBestMoveForMin(moves);
//
//        return moves.get(bestMoveIndex);
//
//    }
//
//    private List<Move> evaluateMoves(Board board, PlayerMark currentPlayer) {
//
//        List<Move> moves = new ArrayList<>();
//
//        for (int row = 0; row < 3; row++) {
//            for (int col = 0; col < 3; col++) {
//                if (board.isValidMove(row, col)) {
//                    moves.add(evaluateMove(board, row, col, currentPlayer));
//                }
//            }
//        }
//
//        return moves;
//    }
//
//
//    private Move evaluateMove(Board board, int row, int col, PlayerMark currentPlayer) {
//
//        Move move = new Move(row, col);
//        Board boardCopy = board.copyBoard();
//
//        boardCopy.placeMark(currentPlayer, row, col);
//        move.setScore(evaluateBestMove(boardCopy, switchPlayer(currentPlayer)).getScore());
//
//        board.setEmpty(row, col);
//        return move;
//    }
//
//
//    private PlayerMark switchPlayer(PlayerMark currentPlayer) {
//        return (currentPlayer == PlayerMark.X) ? PlayerMark.O : PlayerMark.X;
//    }
//
//
//    private int findBestMoveForMax(List<Move> moves) {
//        return findBestMove(moves, Integer.MIN_VALUE, true);
//    }
//
//    private int findBestMoveForMin(List<Move> moves) {
//        return findBestMove(moves, Integer.MAX_VALUE, false);
//    }
//
//    private int findBestMove(List<Move> moves, int initialBestScore, boolean isMax) {
//        int bestMove = -1;
//        int bestScore = initialBestScore;
//
//        for (int i = 0; i < moves.size(); i++) {
//            int currentScore = moves.get(i).getScore();
//
//            if (isMax ? currentScore > bestScore : currentScore < bestScore) {
//                bestScore = currentScore;
//                bestMove = i;
//            }
//        }
//
//        return bestMove;
//    }

