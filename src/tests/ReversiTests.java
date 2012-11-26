package tests;

import static org.junit.Assert.*;

import model.Board;
import model.PieceState;

import org.junit.Test;

import view.ReversiOutput;
import controller.ReversiController;

public class ReversiTests {

	@Test
	public void testPrint() {
		Board gameBoard = new Board();
		ReversiOutput printer = new ReversiOutput();
		
		gameBoard.setPiece(5, 6, PieceState.WHITE);
		
		printer.printBoard(gameBoard);
	}
	
	@Test
	public void testValidMoves() {
		Board gameBoard = new Board();
		
		assertTrue(gameBoard.isValidMove(4, 3, PieceState.BLACK));
		assertTrue(gameBoard.isValidMove(5, 6, PieceState.BLACK));
		assertFalse(gameBoard.isValidMove(3, 5, PieceState.BLACK));
		assertFalse(gameBoard.isValidMove(10, 5, PieceState.BLACK));
		assertFalse(gameBoard.isValidMove(4, 4, PieceState.BLACK));
	}

}
