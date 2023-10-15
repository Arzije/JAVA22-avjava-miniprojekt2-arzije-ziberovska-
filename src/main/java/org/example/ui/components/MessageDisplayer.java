package org.example.ui.components;

import javax.swing.*;
public class MessageDisplayer {

    /**
     * Utility class for displaying messages in a dialog.
     */
    public static void displayMessage(JFrame frame, String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

}