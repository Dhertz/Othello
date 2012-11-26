package game;

import controller.ReversiController;
import view.ReversiOutput;
import model.Board;
import model.PieceState;

public class Reversi {

	public static void main(String[] args) {
		Board gameBoard = new Board();
		ReversiOutput printer = new ReversiOutput();
		ReversiController controller = new ReversiController(gameBoard, printer);
		controller.playGame();
	}

}
