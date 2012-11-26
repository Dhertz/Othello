package controller;

import model.PieceState;

public class Player {
	
	private PieceState colour;
	private String name;
	
	public Player(PieceState colour, String name) {
		this.colour = colour;
		this.name = name;
	}
	public PieceState getColour() {
		return colour;
	}
	public String getName() {
		return name;
	}

}
