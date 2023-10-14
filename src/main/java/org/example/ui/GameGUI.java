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
        frame.setSize(400, 450);
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

//public class GameGUI {
//
//        private GameLogic gameLogic;
//        private JFrame frame;
//        private JButton[][] buttons;
//
//        public GameGUI() {
//            setupMainFrame();
//            setupGameController();
//            setupUIElements();
//            frame.setVisible(true);
//        }
//
//        private void setupMainFrame() {
//            frame = new JFrame("Tic Tac Toe");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setSize(400, 450);
//            frame.setLayout(new BorderLayout());
//        }
//
//        private void setupGameController() {
//            gameLogic = new GameLogic();
//        }
//
//        private void setupUIElements() {
//            setupRestartButton();
//            setupGameBoard();
//            setupHelpButton();
//        }
//
//        private void setupGameBoard() {
//            buttons = new JButton[3][3];
//            JPanel boardPanel = new JPanel(new GridLayout(3, 3, 10, 10));
//            for (int i = 0; i < 3; i++) {
//                for (int j = 0; j < 3; j++) {
//                    buttons[i][j] = generateGameButton(i, j);
//                    boardPanel.add(buttons[i][j]);
//                }
//            }
//            frame.add(boardPanel, BorderLayout.CENTER);
//        }
//
//        private JButton generateGameButton(int row, int col) {
//            JButton button = new JButton(" ");
//            button.setFont(new Font("Arial", Font.BOLD, 40));
//            button.setPreferredSize(new Dimension(100, 100));
//            button.addActionListener(new ButtonListener(row, col));
//            return button;
//        }
//
//        private void setupRestartButton() {
//            JButton restartButton = new JButton("Start Over!");
//            restartButton.addActionListener(e -> restartMatch());
//            frame.add(restartButton, BorderLayout.NORTH);
//        }
//
//        private void setupHelpButton() {
//            JButton hintButton = new JButton("I would like a hint!");
//            hintButton.addActionListener(e -> showHint());
//            JPanel hintPanel = new JPanel(); // Use default FlowLayout for centering
//            hintPanel.add(hintButton);
//            frame.add(hintPanel, BorderLayout.SOUTH);
//        }
//
//        private void showHint() {
//            Move bestMove = gameLogic.provideHint();
//            int bestRow = bestMove.getRow();
//            int bestCol = bestMove.getCol();
//            buttons[bestRow][bestCol].setBackground(Color.gray);
//        }
//
//        private class ButtonListener implements ActionListener {
//            private int row, col;
//
//            public ButtonListener(int row, int col) {
//                this.row = row;
//                this.col = col;
//            }
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                processPlayerMove();
//                if (!gameLogic.isWinner() && !gameLogic.isDraw()) {
//                    processAIMove();
//                }
//            }
//
//            private void processPlayerMove() {
//                if (gameLogic.isValidMove(row, col)) {
//                    gameLogic.makeMove(row, col);
//                    updateGameBoard();
//                    if (gameLogic.isWinner()) {
//                        displayMessage(PlayerMark.O + " wins!");
//                        disableGameButtons();
//                    } else if (gameLogic.isDraw()) {
//                        displayMessage("It's a draw!");
//                    }
//                }
//            }
//            private void processAIMove() {
//                Move bestMove = gameLogic.aiMove();
//                gameLogic.makeMove(bestMove.getRow(), bestMove.getCol());
//                updateGameBoard();
//                if (gameLogic.isWinner()) {
//                    JOptionPane.showMessageDialog(frame,
//                            (gameLogic.getCurrentPlayer() == PlayerMark.X ? PlayerMark.O : PlayerMark.X) + " wins!");
//                    disableGameButtons();
//                } else if (gameLogic.isDraw()) {
//                    JOptionPane.showMessageDialog(frame, "It's a draw!");
//                }
//            }
//        }
//
//        private void updateGameBoard() {
//            for (int i = 0; i < 3; i++) {
//                for (int j = 0; j < 3; j++) {
//                    buttons[i][j].setBackground(null);
//                    buttons[i][j].setText(gameLogic.getBoardCellMark(i, j));
//                }
//            }
//        }
//
//        private void disableGameButtons() {
//            for (int i = 0; i < 3; i++) {
//                for (int j = 0; j < 3; j++) {
//                    buttons[i][j].setEnabled(false);
//                }
//            }
//        }
//
//        private void restartMatch() {
//            gameLogic.restartGame();
//            for (int i = 0; i < 3; i++) {
//                for (int j = 0; j < 3; j++) {
//                    buttons[i][j].setEnabled(true);
//                    buttons[i][j].setText(" ");
//                    buttons[i][j].setBackground(null);
//                }
//            }
//        }
//
//    private void displayMessage(String message) {
//        JOptionPane.showMessageDialog(frame, message);
//    }
    }
