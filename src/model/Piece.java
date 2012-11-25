package model;

public class Piece {

	private PieceState state;

	public Piece(PieceState state) {
		super();
		this.state = state;
	}

	public PieceState getState() {
		return state;
	}

	public void setState(PieceState state) {
		this.state = state;
	}
	
}
