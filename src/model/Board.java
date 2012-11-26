package model;

public class Board {
	
	private Piece[][] board;
	private static final int BOARDSIZE = 8;
	
	// These arrays allow us to loop through in all possible directions
	private static final int[] DIRECT_X = { -1,  0,  1, -1, 1, -1, 0, 1 };
    private static final int[] DIRECT_Y = { -1, -1, -1,  0, 0,  1, 1, 1 };

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
	
	public void setPiece(int column, int row, PieceState st) {
		board[column - 1][row - 1].setState(st);
	}
	
	public boolean isValidMove(int column, int row, PieceState st) {
		int zeroIndexedColumn = column - 1;
		int zeroIndexedRow = row - 1;
		
		// Bounds check and PieceState check
		if (!inBounds(column, row) || getColour(zeroIndexedColumn, zeroIndexedRow) != PieceState.EMPTY) {
			return false;
		}
		
		for (int i = 0; i < DIRECT_X.length; i++) {
            boolean sawOther = false, sawSelf = false;
            int x = zeroIndexedColumn, y = zeroIndexedRow;
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
		return board[column][row].getState();
	}

	private boolean inBounds(int column, int row) {
		return column >= 0 && row >= 0 && column < BOARDSIZE && row < BOARDSIZE;
	}

	public Piece[][] getBoard() {
		return board;
	}

}
