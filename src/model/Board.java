package model;

import java.util.ArrayList;

public class Board {

	private Piece[][] board;
	private static final int BOARDSIZE = 8;

	// These arrays allow us to loop through in all possible directions
	private static final int[] DIRECT_X = { -1, 0, 1, -1, 1, -1, 0, 1 };
	private static final int[] DIRECT_Y = { -1, -1, -1, 0, 0, 1, 1, 1 };

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

		// Using Othello rules for initial setup, not Reversi
		setPiece(4, 4, PieceState.WHITE);
		setPiece(5, 4, PieceState.BLACK);
		setPiece(4, 5, PieceState.BLACK);
		setPiece(5, 5, PieceState.WHITE);
	}

	public void setPiece(int column, int row, PieceState st) {
		//System.out.println("setting (" + column + ", " + row + ") to " + st);
		board[row - 1][column - 1].setState(st);
	}

	// Capture surrounding pieces - to be called after setPiece
	public void capturePieces(int column, int row, PieceState st) {
		ArrayList<Piece> captureList = new ArrayList<Piece>();
		
		for (int i = 0; i < DIRECT_X.length; i++) {
			int x = column, y = row;

			for (int j = 0; j < BOARDSIZE; j++) {
				x += DIRECT_X[i];
				y += DIRECT_Y[i];

				if (!inBounds(x, y)) {
					break;
				}

				PieceState foundState = getColour(x, y);
				if (foundState == PieceState.EMPTY) {
					break;
				} else if (foundState != st) {
					captureList.add(getPiece(x, y));
				} else {
					for (Piece p : captureList) {
						p.setState(st);
					}
					break;
				}
			}
			captureList.clear();
		}
	}

	public boolean isValidMove(int column, int row, PieceState st) {
		// Bounds check and PieceState check
		if (!inBounds(column, row)
				|| getColour(column, row) != PieceState.EMPTY) {
			return false;
		}

		// Loop through in all directions, checking for a piece of the opposite
		// colour, break on other two cases
		for (int i = 0; i < DIRECT_X.length; i++) {
			boolean sawOther = false, sawSelf = false;
			int x = column, y = row;

			for (int j = 0; j < BOARDSIZE; j++) {
				x += DIRECT_X[i];
				y += DIRECT_Y[i];

				if (!inBounds(x, y)) {
					break;
				}

				PieceState foundState = getColour(x, y);
				if (foundState == PieceState.EMPTY) {
					break;
				} else if (foundState != st) {
					sawOther = true;
				} else {
					sawSelf = true;
					break;
				}
			}

			if (sawOther && sawSelf) {
				return true;
			}
		}

		return false;
	}

	private PieceState getColour(int column, int row) {
		return board[row - 1][column - 1].getState();
	}

	private boolean inBounds(int column, int row) {
		int zeroIndexedColumn = column - 1;
		int zeroIndexedRow = row - 1;
		return zeroIndexedColumn >= 0 && zeroIndexedRow >= 0
				&& zeroIndexedColumn < BOARDSIZE && zeroIndexedRow < BOARDSIZE;
	}

	public Piece[][] getBoard() {
		return board;
	}

	public Piece getPiece(int column, int row) {
		return board[row - 1][column - 1];
	}

}
