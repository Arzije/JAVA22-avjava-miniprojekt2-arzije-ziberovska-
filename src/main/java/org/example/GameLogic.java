package org.example;

public class GameLogic {

    private Board board; // The game board
    private GameAI ai; // The AI component for providing hints or AI moves
    private GameSymbol currentPlayer; // Represents the current player's symbol ('X' or 'O')

    public GameLogic() {
        this.board = new Board();
        this.currentPlayer = GameSymbol.X;
        this.ai = new GameAI(board, currentPlayer);

    }

    public Move provideHint() {
        return ai.bestMove();  // Use the AI to provide a hint
    }

    public boolean isValidMove(int row, int col) {
        return board.isValidMove(row, col);
    }

    public void makeMove(int row, int col) {
        if (isValidMove(row, col)) {
            board.placeMark(currentPlayer, row, col);
            switchPlayer();
        }
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == GameSymbol.X) ? GameSymbol.O : GameSymbol.X;
    }

    public boolean isWinner() {
        return board.isWinner();
    }

    public boolean isDraw() {
        return board.isDraw();
    }

    public void restartGame() {
        board = new Board();
        currentPlayer = GameSymbol.X;
    }

    public String getBoardCellSymbol(int row, int col) {
        return board.getBoardCell(row, col).getSymbol();
    }

    public Move aiMove() {
        return ai.bestMove();  // Use the AI to make a move
    }

    public GameSymbol getCurrentPlayer() {
        return currentPlayer;
    }
}
