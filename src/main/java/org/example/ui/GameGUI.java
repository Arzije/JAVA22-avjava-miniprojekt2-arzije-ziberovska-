package org.example.ui;

import org.example.logic.GameLogic;
import org.example.model.Move;
import org.example.ui.components.BoardPanel;
import org.example.ui.components.MessageDisplayer;

import javax.swing.*;
import java.awt.*;

public class GameGUI {
    private GameLogic gameLogic;
    private JFrame frame;
    private BoardPanel boardPanel;

    public GameGUI() {
        setupMainFrame();
        setupGameController();
        setupUIElements();
        frame.setVisible(true);
    }

    private void setupMainFrame() {
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 550);
        frame.setLayout(new BorderLayout());
    }

    private void setupGameController() {
        gameLogic = new GameLogic();
    }

    private void setupUIElements() {
        setupRestartButton();
        setupGameBoard();
        setupHelpButton();
    }

    private void setupGameBoard() {
        boardPanel = new BoardPanel(gameLogic, this); // Pass both gameLogic and this GUI instance
        frame.add(boardPanel, BorderLayout.CENTER);
    }

    private void setupRestartButton() {
        JButton restartButton = new JButton("Start Over!");
        restartButton.addActionListener(e -> {
            gameLogic.restartGame();
            updateGameBoard(); // Update the GUI after restarting the game
        });
        frame.add(restartButton, BorderLayout.NORTH);
    }

    private void setupHelpButton() {
        JButton hintButton = new JButton("I would like a hint!");
        hintButton.addActionListener(e -> showHint());
        JPanel hintPanel = new JPanel(); // Use default FlowLayout for centering
        hintPanel.add(hintButton);
        frame.add(hintPanel, BorderLayout.SOUTH);
    }

    private void showHint() {
        Move bestMove = gameLogic.provideHint();
        boardPanel.highlightButton(bestMove.getRow(), bestMove.getCol());
    }

    public void updateGameBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardPanel.updateButton(i, j, gameLogic.getBoardCellMark(i, j));
            }
        }
    }

    public void displayMessage(String message) {
        MessageDisplayer.displayMessage(frame, message);
    }

}
