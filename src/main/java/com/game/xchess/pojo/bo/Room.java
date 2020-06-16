package com.game.xchess.pojo.bo;

public class Room {

	private final Board board;

	private final Game game;
	
	public Room() {
		this.board = new Board();
		this.game = new Game(this.board);
	}

	public Board getBoard() {
		return board;
	}
	
	public Game getGame(){
		return this.game;
	}
}
