package org.example.ui.components;

import org.example.logic.GameLogic;
import org.example.ui.GameGUI;
import org.example.ui.listeners.ButtonListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Represents the main game board UI component.
 */
public class BoardPanel extends JPanel {
    private JButton[][] buttons;

    /**
     * Constructs the game board with buttons.
     *
     * @param gameLogic The game logic instance.
     * @param gameGUI   The main game GUI instance.
     */
    public BoardPanel(GameLogic gameLogic, GameGUI gameGUI) {
        setBackground(new Color(58, 64, 82));
        setLayout(new GridLayout(3, 3, 20, 20));
        buttons = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = ButtonFactory.generateGameButton(i, j, new ButtonListener(i, j, gameLogic, gameGUI));
                add(buttons[i][j]);
            }
        }
    }

    /**
     * Updates the text of a specific button on the board.
     *
     * @param row   The row index of the button.
     * @param col   The column index of the button.
     * @param text  The text to set on the button.
     */
    public void updateButton(int row, int col, String text) {
        buttons[row][col].setText(text);
        buttons[row][col].setBackground(null);
    }

    /**
     * Highlights a specific button on the board for hints.
     *
     * @param row The row index of the button.
     * @param col The column index of the button.
     */
    public void highlightButton(int row, int col) {
        buttons[row][col].setBackground(Color.gray);
    }


}
