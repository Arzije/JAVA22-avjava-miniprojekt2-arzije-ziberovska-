package org.example;

public class GameLogic {

    private Board board; // The game board
    private MinMaxAlgorithm ai; // The AI component for providing hints or AI moves
    private GameSymbol currentPlayer; // Represents the current player's symbol ('X' or 'O')

    public GameLogic() {
        this.board = new Board();
        this.ai = new MinMaxAlgorithm();
        this.currentPlayer = GameSymbol.X;
    }

    public MinMaxAlgorithm.Move provideHint() {
        return ai.minMax(board, currentPlayer);
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

    public MinMaxAlgorithm.Move aiMove() {
        return ai.minMax(board, currentPlayer);
    }

    public GameSymbol getCurrentPlayer() {
        return currentPlayer;
    }
}
