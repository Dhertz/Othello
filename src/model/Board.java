package model;

public class Board {
	
	private Piece[][] board;
	private static int BOARDSIZE = 8;

	public Board() {
		board = new Piece[BOARDSIZE][BOARDSIZE];
		setupBoard();
	}

	private void setupBoard() {
		for (int i = 0; i < BOARDSIZE; i++) {
			for (int j = 0; j < BOARDSIZE; j++) {
				board[i][j] = new Piece(PieceState.EMPTY);
			}
		}
		
		board[3][3].setState(PieceState.WHITE);
		board[3][4].setState(PieceState.BLACK);
		board[4][3].setState(PieceState.BLACK);
		board[4][4].setState(PieceState.WHITE);
	}

	public Piece[][] getBoard() {
		return board;
	}

}
