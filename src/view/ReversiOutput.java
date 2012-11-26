package view;

import model.Board;
import model.Piece;
import model.PieceState;

public class ReversiOutput implements ReversiView {
	
	@Override
	public void showBoard(Board board) {
		System.out.println("  a b c d e f g h");
		int rowNum = 1;
		for (Piece[] ps : board.getBoard()) {
			System.out.print(rowNum + " ");
			for (Piece p : ps) {
				if (p.getState() == PieceState.EMPTY) {
					System.out.print(". ");
				} else if (p.getState() == PieceState.BLACK) {
					System.out.print("B ");
				} else {
					System.out.print("W ");
				}
			}
			System.out.println();
			rowNum++;
		}
		System.out.println();
	}

}
