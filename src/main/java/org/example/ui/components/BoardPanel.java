package org.example.ui.components;

import org.example.logic.GameLogic;
import org.example.ui.GameGUI;
import org.example.ui.listeners.ButtonListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BoardPanel extends JPanel {
    private JButton[][] buttons;
    private GameLogic gameLogic;
    private GameGUI gameGUI;

    public BoardPanel(GameLogic gameLogic, GameGUI gameGUI) {
        this.gameLogic = gameLogic;
        this.gameGUI = gameGUI;
        setLayout(new GridLayout(3, 3, 10, 10));
        buttons = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = ButtonFactory.generateGameButton(i, j, new ButtonListener(i, j, gameLogic, gameGUI));
                add(buttons[i][j]);
            }
        }
    }

    public void updateButton(int row, int col, String text) {
        buttons[row][col].setText(text);
        buttons[row][col].setBackground(null);
    }

    public void highlightButton(int row, int col) {
        buttons[row][col].setBackground(Color.gray);
    }

    public void resetButton(int row, int col) {
        buttons[row][col].setEnabled(true);
        buttons[row][col].setText(" ");
        buttons[row][col].setBackground(null);
    }

}
