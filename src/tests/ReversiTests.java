package tests;

import static org.junit.Assert.*;

import model.Board;
import model.PieceState;

import org.junit.Test;

import view.ReversiOutput;

public class ReversiTests {

	@Test
	public void testSetPiece() {
		Board gameBoard = new Board();
		
		gameBoard.setPiece(3, 4, PieceState.BLACK);
		
		assertEquals(PieceState.BLACK, gameBoard.getPiece(3, 4).getState());
	}
	
	@Test
	public void testValidMoves() {
		Board gameBoard = new Board();
		
		assertTrue(gameBoard.isValidMove(3, 4, PieceState.BLACK));
		assertTrue(gameBoard.isValidMove(4, 3, PieceState.BLACK));
		assertTrue(gameBoard.isValidMove(5, 6, PieceState.BLACK));
		assertFalse(gameBoard.isValidMove(3, 5, PieceState.BLACK));
		assertFalse(gameBoard.isValidMove(10, 5, PieceState.BLACK));
		assertFalse(gameBoard.isValidMove(4, 4, PieceState.BLACK));
	}
	
	@Test
	public void testCapture() {
		Board gameBoard = new Board();
		
		gameBoard.setPiece(3, 4, PieceState.BLACK);
		gameBoard.capturePieces(3, 4, PieceState.BLACK);
		
		assertEquals(PieceState.BLACK, gameBoard.getPiece(4, 4).getState());
	}
	
	@Test
	public void testCaptureSetSequence() {
		Board gameBoard = new Board();
		ReversiOutput printer = new ReversiOutput();
		
		gameBoard.setPiece(4, 3, PieceState.BLACK);
		gameBoard.capturePieces(4, 3, PieceState.BLACK);
		
		printer.printBoard(gameBoard);
		
		gameBoard.setPiece(3, 5, PieceState.WHITE);
		gameBoard.capturePieces(3, 5, PieceState.WHITE);
		
		printer.printBoard(gameBoard);
	}
	
}
