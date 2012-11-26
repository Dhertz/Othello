package controller;

import java.util.Scanner;

import model.Board;
import model.PieceState;
import view.ReversiOutput;

public class ReversiController {
	
	private Player player1;
	private Player player2;
	private Board board;
	private ReversiOutput output;
	private Scanner reader;
	
	public ReversiController(Board board, ReversiOutput output) {
		super();
		this.board = board;
		this.output = output;
		reader = new Scanner(System.in);
		setupPlayers();
	}

	private void setupPlayers() {
		//System.out.println("Player 1, what is your name?");
		player1 = new Player(PieceState.BLACK, "Owen");//reader.nextLine());
		
		//System.out.println("Player 2, what is your name?");
		player2 = new Player(PieceState.WHITE, "Daniel");// reader.nextLine());
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

}
