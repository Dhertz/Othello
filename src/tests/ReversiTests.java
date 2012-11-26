package tests;

import static org.junit.Assert.*;

import model.Board;
import model.PieceState;

import org.junit.Test;

import view.ReversiStdoutPrinter;
import view.ReversiView;

public class ReversiTests {

	@Test
	public void testSetPiece() {
		Board gameBoard = Board.withDefaultSize();
		
		gameBoard.setPiece(3, 4, PieceState.BLACK);
		
		assertEquals(PieceState.BLACK, gameBoard.getPiece(3, 4).getState());
	}
	
	@Test
	public void testValidMoves() {
		Board gameBoard = Board.withDefaultSize();
		
		assertTrue(gameBoard.isValidMove(3, 4, PieceState.BLACK));
		assertTrue(gameBoard.isValidMove(4, 3, PieceState.BLACK));
		assertTrue(gameBoard.isValidMove(5, 6, PieceState.BLACK));
		assertFalse(gameBoard.isValidMove(3, 5, PieceState.BLACK));
		assertFalse(gameBoard.isValidMove(10, 5, PieceState.BLACK));
		assertFalse(gameBoard.isValidMove(4, 4, PieceState.BLACK));
	}
	
	@Test
	public void testCapture() {
		Board gameBoard = Board.withDefaultSize();
		
		gameBoard.setPiece(3, 4, PieceState.BLACK);
		gameBoard.capturePieces(3, 4, PieceState.BLACK);
		
		assertEquals(PieceState.BLACK, gameBoard.getPiece(4, 4).getState());
	}
	
	@Test
	public void testCaptureSetSequence() {
		Board gameBoard = Board.withDefaultSize();
		
		gameBoard.setPiece(4, 3, PieceState.BLACK);
		gameBoard.capturePieces(4, 3, PieceState.BLACK);
		
		assertEquals(PieceState.BLACK, gameBoard.getPiece(4, 4).getState());
		
		gameBoard.setPiece(3, 5, PieceState.WHITE);
		gameBoard.capturePieces(3, 5, PieceState.WHITE);

		assertEquals(PieceState.WHITE, gameBoard.getPiece(4, 5).getState());
	}
	
	@Test
	public void testNoValidMove() {
		Board gameBoard = Board.withDefaultSize();
		gameBoard.setPiece(4, 4, PieceState.BLACK);
		gameBoard.setPiece(5, 5, PieceState.BLACK);
		assertFalse(gameBoard.hasValidMoves(PieceState.WHITE));
	}
	
	@Test
	public void testCustomSize() {
		Board gameBoard = Board.withCustomSize(4);
		
		assertEquals(4, gameBoard.getSize());
	}
	
	@Test
	public void testHasValidMoves() {
		Board gameBoard = Board.withCustomSize(4);
		
		assertTrue(gameBoard.hasValidMoves(PieceState.BLACK));
		assertTrue(gameBoard.hasValidMoves(PieceState.WHITE));
		
		gameBoard.setPiece(2, 1, PieceState.BLACK);
		gameBoard.capturePieces(2, 1, PieceState.BLACK);
		gameBoard.setPiece(4, 3, PieceState.BLACK);
		gameBoard.capturePieces(4, 3, PieceState.BLACK);
		
		assertFalse(gameBoard.hasValidMoves(PieceState.WHITE));
	}
	
}
