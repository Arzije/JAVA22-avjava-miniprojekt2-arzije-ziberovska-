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
            initializeFrame();
            initializeGame();
            addUIComponents();
            frame.setVisible(true);
        }

        private void initializeFrame() {
            frame = new JFrame("Tic Tac Toe");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 450);
            frame.setLayout(new BorderLayout());
        }

        private void initializeGame() {
            controller = new GameLogic();
        }

        private void addUIComponents() {
            addStartOverButton();
            addBoardPanel();
            addHintButton();
        }

        private void addBoardPanel() {
            buttons = new JButton[3][3];
            JPanel boardPanel = new JPanel(new GridLayout(3, 3, 10, 10));
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buttons[i][j] = createBoardButton(i, j);
                    boardPanel.add(buttons[i][j]);
                }
            }
            frame.add(boardPanel, BorderLayout.CENTER);
        }

        private JButton createBoardButton(int row, int col) {
            JButton button = new JButton(" ");
            button.setFont(new Font("Arial", Font.BOLD, 40));
            button.setPreferredSize(new Dimension(100, 100));
            button.addActionListener(new ButtonListener(row, col));
            return button;
        }

        private void addStartOverButton() {
            JButton restartButton = new JButton("Start Over!");
            restartButton.addActionListener(e -> restartGame());
            frame.add(restartButton, BorderLayout.NORTH);
        }

        private void addHintButton() {
            JButton hintButton = new JButton("I would like a hint!");
            hintButton.addActionListener(e -> provideHint());
            JPanel hintPanel = new JPanel(); // Use default FlowLayout for centering
            hintPanel.add(hintButton);
            frame.add(hintPanel, BorderLayout.SOUTH);
        }

        private void provideHint() {
            MinMaxAlgorithm.Move bestMove = controller.provideHint();
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
                if (controller.isValidMove(row, col)) {
                    controller.makeMove(row, col);
                    updateBoard();
                    if (controller.isWinner()) {
                        PlayerMark previousPlayer = (controller.getCurrentPlayer() == PlayerMark.X) ? PlayerMark.O
                                : PlayerMark.X;
                        JOptionPane.showMessageDialog(frame, previousPlayer + " wins!");
                        disableButtons();
                        return;
                    }

                    if (controller.isDraw()) {
                        JOptionPane.showMessageDialog(frame, "It's a draw!");
                        return;
                    }

                    aiMove();
                }
            }

            private void aiMove() {
                Timer timer = new Timer(1000, e -> {
                    MinMaxAlgorithm.Move bestMove = controller.aiMove();
                    controller.makeMove(bestMove.getRow(), bestMove.getCol());
                    updateBoard();
                    if (controller.isWinner()) {
                        JOptionPane.showMessageDialog(frame,
                                (controller.getCurrentPlayer() == PlayerMark.X ? PlayerMark.O : PlayerMark.X) + " wins!");
                        disableButtons();
                    } else if (controller.isDraw()) {
                        JOptionPane.showMessageDialog(frame, "It's a draw!");
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        }

        private void updateBoard() {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buttons[i][j].setBackground(null);
                    buttons[i][j].setText(controller.getBoardCellMark(i, j));
                }
            }
        }

        private void disableButtons() {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buttons[i][j].setEnabled(false);
                }
            }
        }

        private void restartGame() {
            controller.restartGame();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buttons[i][j].setEnabled(true);
                    buttons[i][j].setText(" ");
                    buttons[i][j].setBackground(null);
                }
            }
        }
    }
