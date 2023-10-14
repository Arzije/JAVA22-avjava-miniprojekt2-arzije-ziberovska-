package org.example.ui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonFactory {

    public static JButton generateGameButton(int row, int col, ActionListener listener) {
        JButton button = new JButton(" ");
        button.setFont(new Font("Arial", Font.BOLD, 40));
        button.setPreferredSize(new Dimension(100, 100));
        button.addActionListener(listener);
        return button;
    }
}
