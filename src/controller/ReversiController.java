package controller;

import java.util.Scanner;

import model.Board;
import model.PieceState;
import view.ReversiStdoutPrinter;

public class ReversiController {
	
	private Player player1;
	private Player player2;
	private Board board;
	private ReversiStdoutPrinter output;
	private Scanner reader;
	private Player currentPlayer;
	
	public ReversiController(Board board, ReversiStdoutPrinter output) {
		this.board = board;
		this.output = output;
		reader = new Scanner(System.in);
		setupPlayers();
	}

	private void setupPlayers() {
		System.out.println("Player 1, what is your name?");
		player1 = new Player(PieceState.BLACK, reader.nextLine());
		
		System.out.println("Player 2, what is your name?");
		player2 = new Player(PieceState.WHITE, reader.nextLine());
		
		currentPlayer = player1;
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public Board getBoard() {
		return board;
	}
	
	public void readMove() {
		if(!board.hasValidMoves(currentPlayer.getColour())) {
			System.out.println(currentPlayer.getName() + " has no valid move!");
			changePlayer();
		}
		System.out.println(currentPlayer.getName()  + "'s move, please enter your move: letter, number.");
		String input = reader.nextLine();
		String [] tokenised = input.split(" ");
		
		try {
			
			char x  = tokenised[0].charAt(0);
			int xi = x - 96;
			int y = Integer.parseInt(tokenised[1]);
			
			if(board.isValidMove(xi, y, currentPlayer.getColour())) {
				board.setPiece(xi, y, currentPlayer.getColour());
				board.capturePieces(xi, y, currentPlayer.getColour());
				changePlayer();
			} else {
				System.out.println("Invalid move.");
				readMove();
			}
			
		} catch (ArrayIndexOutOfBoundsException e) {
			
			System.out.println("Invalid move." + e);
			readMove();
		}
		
	}
	
	private void changePlayer() {
		currentPlayer = currentPlayer.equals(player1) ? player2 : player1;
	}
	
	public void playGame() {
		while(isPlayable()) {
			output.showBoard(board);
			readMove();
		}
		output.showBoard(board);
		System.out.println("Game over! Winner is: " + getWinnerName() + ".");
	}
	
	private boolean isPlayable() {
		return board.hasValidMoves(player1.getColour()) || 
				board.hasValidMoves(player2.getColour());
	}
	
	private String getWinnerName() {
		if(board.getPieceCount(player1.getColour()) > board.getPieceCount(player2.getColour())) {
			return player1.getName();
		} else {
			return player2.getName();
		}
	}

}


