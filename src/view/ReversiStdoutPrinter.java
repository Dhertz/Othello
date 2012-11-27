package view;

import model.Board;
import model.Piece;
import model.PieceState;

public class ReversiStdoutPrinter implements ReversiView {

    Board board;

    public ReversiStdoutPrinter(Board board) {
        this.board = board;
    }

    @Override
    public void showBoard() {
        System.out.print("  ");
        for (char l = 'a'; l < 'a' + board.getSize(); l++) {
            System.out.print(l + " ");
        }
        System.out.println();

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
