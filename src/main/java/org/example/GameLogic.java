package org.example;

public class GameLogic {

    private Board board;
    private MinMaxAlgorithm aiPlayer;
    private PlayerMark currentPlayer;

    public GameLogic() {
        this.board = new Board();
        this.aiPlayer = new MinMaxAlgorithm();
        this.currentPlayer = PlayerMark.X;
    }

    public MinMaxAlgorithm.Move provideHint() {
        return aiPlayer.minMax(board, currentPlayer);
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
        currentPlayer = (currentPlayer == PlayerMark.X) ? PlayerMark.O : PlayerMark.X;
    }

    public boolean isWinner() {
        return board.isWinner();
    }

    public boolean isDraw() {
        return board.isDraw();
    }

    public void restartGame() {
        board = new Board();
        currentPlayer = PlayerMark.X;
    }

    public String getBoardCellMark(int row, int col) {
        return board.getBoardCell(row, col).getSymbol();
    }

    public MinMaxAlgorithm.Move aiMove() {
        return aiPlayer.minMax(board, currentPlayer);
    }

    public PlayerMark getCurrentPlayer() {
        return currentPlayer;
    }
}
