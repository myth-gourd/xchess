package com.game.xchess.pojo.bo;

/**
 * 棋盘里的格子
 * @author zhangjian01
 *
 */
public final class Cell
{
	private final int x;
	
	private final int y;
	
	private Piece piece;
	
	/**
	 * 格子上的棋子是否正面朝上
	 */
	private Boolean isFrontUp;
	
	public Cell(int x, int y){
		this.x = x;
		this.y = y;
		this.piece = null;
		this.isFrontUp = false;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public Boolean getIsFrontUp() {
		return isFrontUp;
	}

	public void setIsFrontUp(Boolean isBottomUp) {
		this.isFrontUp = isBottomUp;
	}

	public boolean hasPiece(){
		return piece != null;
	}

	@Override
	public String toString() {
		return "Cell [x=" + x + ", y=" + y + ", piece=" + piece + ", isFrontUp=" + isFrontUp + "]";
	}
}