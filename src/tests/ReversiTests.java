package tests;

import static org.junit.Assert.*;

import model.Board;

import org.junit.Test;

import view.ReversiOutput;
import controller.ReversiController;

public class ReversiTests {

	@Test
	public void testPrint() {
		Board gameBoard = new Board();
		ReversiOutput printer = new ReversiOutput();
		ReversiController controller = new ReversiController(gameBoard, printer);
		
		printer.printBoard(gameBoard);
	}

}
