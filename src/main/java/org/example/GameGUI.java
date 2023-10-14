package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI {

        private GameLogic controller;
        private JFrame frame;
        private JButton[][] buttons;

        public GameGUI() {
            setupMainFrame();
            setupGameController();
            setupUIElements();
            frame.setVisible(true);
        }

        private void setupMainFrame() {
            frame = new JFrame("Tic Tac Toe");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 450);
            frame.setLayout(new BorderLayout());
        }

        private void setupGameController() {
            controller = new GameLogic();
        }

        private void setupUIElements() {
            setupRestartButton();
            setupGameBoard();
            setupHelpButton();
        }

        private void setupGameBoard() {
            buttons = new JButton[3][3];
            JPanel boardPanel = new JPanel(new GridLayout(3, 3, 10, 10));
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buttons[i][j] = generateGameButton(i, j);
                    boardPanel.add(buttons[i][j]);
                }
            }
            frame.add(boardPanel, BorderLayout.CENTER);
        }

        private JButton generateGameButton(int row, int col) {
            JButton button = new JButton(" ");
            button.setFont(new Font("Arial", Font.BOLD, 40));
            button.setPreferredSize(new Dimension(100, 100));
            button.addActionListener(new ButtonListener(row, col));
            return button;
        }

        private void setupRestartButton() {
            JButton restartButton = new JButton("Start Over!");
            restartButton.addActionListener(e -> restartMatch());
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
            Move bestMove = controller.provideHint();
            int bestRow = bestMove.getRow();
            int bestCol = bestMove.getCol();
            buttons[bestRow][bestCol].setBackground(Color.gray);
        }

        private class ButtonListener implements ActionListener {
            private int row, col;

            public ButtonListener(int row, int col) {
                this.row = row;
                this.col = col;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                processPlayerMove();
                if (!controller.isWinner() && !controller.isDraw()) {
                    processAIMove();
                }
            }

            private void processPlayerMove() {
                if (controller.isValidMove(row, col)) {
                    controller.makeMove(row, col);
                    updateGameBoard();
                    if (controller.isWinner()) {
                        displayMessage(PlayerMark.O + " wins!");
                        disableGameButtons();
                    } else if (controller.isDraw()) {
                        displayMessage("It's a draw!");
                    }
                }
            }
            private void processAIMove() {
                Move bestMove = controller.aiMove();
                controller.makeMove(bestMove.getRow(), bestMove.getCol());
                updateGameBoard();
                if (controller.isWinner()) {
                    JOptionPane.showMessageDialog(frame,
                            (controller.getCurrentPlayer() == PlayerMark.X ? PlayerMark.O : PlayerMark.X) + " wins!");
                    disableGameButtons();
                } else if (controller.isDraw()) {
                    JOptionPane.showMessageDialog(frame, "It's a draw!");
                }
            }
        }

        private void updateGameBoard() {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buttons[i][j].setBackground(null);
                    buttons[i][j].setText(controller.getBoardCellMark(i, j));
                }
            }
        }

        private void disableGameButtons() {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buttons[i][j].setEnabled(false);
                }
            }
        }

        private void restartMatch() {
            controller.restartGame();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buttons[i][j].setEnabled(true);
                    buttons[i][j].setText(" ");
                    buttons[i][j].setBackground(null);
                }
            }
        }

    private void displayMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }
    }
