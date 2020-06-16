package com.game.xchess.pojo.dto;

import com.game.xchess.pojo.bo.Game;

/**
 * 游戏返回结果
 * @author zhangjian01
 *
 */
public class GameResponse extends Response
{
	private Game game;
	
	private boolean isGameOver;
	
	private int winnedPlayerColor;

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}

	public int getWinnedPlayerColor() {
		return winnedPlayerColor;
	}

	public void setWinnedPlayerColor(int winnedPlayerColor) {
		this.winnedPlayerColor = winnedPlayerColor;
	}
}