package org.example.ui.listeners;

import org.example.logic.GameLogic;
import org.example.model.Move;
import org.example.model.PlayerMark;
import org.example.ui.GameGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {
    private int row, col;
    private GameLogic gameLogic;
    private GameGUI gameGUI;

    public ButtonListener(int row, int col, GameLogic gameLogic, GameGUI gameGUI) {
        this.row = row;
        this.col = col;
        this.gameLogic = gameLogic;
        this.gameGUI = gameGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        processPlayerMove();
        if (!gameLogic.isWinner() && !gameLogic.isDraw()) {
            processComputerMove();
        }
    }

    private void processPlayerMove() {
        if (gameLogic.isValidMove(row, col)) {
            gameLogic.makeMove(row, col);
            gameGUI.updateGameBoard();
            if (gameLogic.isWinner()) {
                gameGUI.displayMessage(PlayerMark.O + " wins!");
            } else if (gameLogic.isDraw()) {
                gameGUI.displayMessage("It's a draw!");
            }
        }
    }

    private void processComputerMove() {
        Move bestMove = gameLogic.aiMove();
        gameLogic.makeMove(bestMove.getRow(), bestMove.getCol());
        gameGUI.updateGameBoard();
        if (gameLogic.isWinner()) {
            gameGUI.displayMessage((gameLogic.getCurrentPlayer() == PlayerMark.X ? PlayerMark.O : PlayerMark.X) + " wins!");
        } else if (gameLogic.isDraw()) {
            gameGUI.displayMessage("It's a draw!");
        }
    }
}

