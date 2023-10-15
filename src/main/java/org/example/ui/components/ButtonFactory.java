package org.example.ui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Factory class for generating different types of buttons.
 */
public class ButtonFactory {

    /**
     * Generates a game button with specific properties.
     *
     * @param row      The row index for the button.
     * @param col      The column index for the button.
     * @param listener The action listener for the button.
     * @return A new game button.
     */
    public static JButton generateGameButton(int row, int col, ActionListener listener) {
        JButton button = new JButton(" ");
        button.setFont(new Font("Arial", Font.BOLD, 40));
        button.setPreferredSize(new Dimension(100, 100));
        button.addActionListener(listener);
        return button;
    }

    /**
     * Generates a standard button with a specific text and action listener.
     *
     * @param text     The text for the button.
     * @param listener The action listener for the button.
     * @return A new standard button.
     */
    public static JButton generateStandardButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        return button;
    }
}
