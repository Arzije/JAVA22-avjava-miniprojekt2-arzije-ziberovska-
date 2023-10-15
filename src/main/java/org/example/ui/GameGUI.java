package org.example.ui;

import org.example.logic.GameLogic;
import org.example.model.Move;
import org.example.ui.components.BoardPanel;
import org.example.ui.components.ButtonFactory;
import org.example.ui.components.MessageDisplayer;

import javax.swing.*;
import java.awt.*;

/**
 * Main game GUI class.
 */
public class GameGUI {
    private GameLogic gameLogic;
    private JFrame frame;
    private BoardPanel boardPanel;

    /**
     * Constructs the main game GUI.
     */
    public GameGUI() {
        setupMainFrame();
        setupGameController();
        setupUIElements();
        frame.setVisible(true);
    }

    // Private helper methods for setting up the GUI
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
        boardPanel = new BoardPanel(gameLogic, this);
        frame.add(boardPanel, BorderLayout.CENTER);
    }

    private void setupRestartButton() {
        JButton restartButton = ButtonFactory.generateStandardButton("Start Over!", e -> {
            gameLogic.restartGame();
            updateGameBoard();
        });
        frame.add(restartButton, BorderLayout.NORTH);
    }

    private void setupHelpButton() {
        JButton hintButton = ButtonFactory.generateStandardButton("I would like a hint!", e -> showHint());
        JPanel hintPanel = new JPanel();
        hintPanel.add(hintButton);
        frame.add(hintPanel, BorderLayout.SOUTH);
    }

    private void showHint() {
        Move bestMove = gameLogic.provideHint();
        boardPanel.highlightButton(bestMove.row(), bestMove.col());
    }

    /**
     * Updates the game board UI.
     */
    public void updateGameBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardPanel.updateButton(i, j, gameLogic.getBoardCellMark(i, j));
            }
        }
    }

    /**
     * Displays a message in a dialog.
     *
     * @param message The message to display.
     */
    public void displayMessage(String message) {
        MessageDisplayer.displayMessage(frame, message);
    }

}
