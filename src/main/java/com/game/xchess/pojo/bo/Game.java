package com.game.xchess.pojo.bo;

import com.game.xchess.consts.PieceColorConsts;

/**
 * 游戏
 * @author zhangjian01
 *
 */
public class Game
{
	/**
	 * 当前该走棋的一方（红方、绿方）
	 */
	private int currentColorTurn;
	
	private final Board board;
	
	public Game(Board board)
	{
		this.board = board;
		this.currentColorTurn = 0;
	}
	
	public int getCurrentColorTurn()
	{
		return currentColorTurn;
	}
	
	public Board getBoard()
	{
		return board;
	}
	
	public void nextColorTurn()
	{
		if(currentColorTurn == PieceColorConsts.RED)
		{
			currentColorTurn = PieceColorConsts.GREEN;
		}
		else
		{
			currentColorTurn = PieceColorConsts.RED;
		}
	}
}