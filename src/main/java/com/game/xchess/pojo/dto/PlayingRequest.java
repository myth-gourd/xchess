package com.game.xchess.pojo.dto;

/**
 * 走棋请求内容
 * @author zhangjian01
 *
 */
public class PlayingRequest {
	int playerColor;
	int roomNum;
	int fromX;
	int fromY;
	int toX;
	int toY;
	public int getPlayerColor() {
		return playerColor;
	}
	public void setPlayerColor(int playerColor) {
		this.playerColor = playerColor;
	}
	public int getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}
	public int getFromX() {
		return fromX;
	}
	public void setFromX(int fromX) {
		this.fromX = fromX;
	}
	public int getFromY() {
		return fromY;
	}
	public void setFromY(int fromY) {
		this.fromY = fromY;
	}
	public int getToX() {
		return toX;
	}
	public void setToX(int toX) {
		this.toX = toX;
	}
	public int getToY() {
		return toY;
	}
	public void setToY(int toY) {
		this.toY = toY;
	}
}