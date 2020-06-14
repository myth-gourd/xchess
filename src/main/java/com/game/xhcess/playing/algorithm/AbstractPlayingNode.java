package com.game.xhcess.playing.algorithm;

import com.game.xchess.errors.GameErrors;
import com.game.xchess.exception.GameException;
import com.game.xchess.pojo.bo.Cell;
import com.game.xchess.pojo.bo.Game;

/**
 * 走棋分析节点
 * 每个节点是一种走棋情况 
 * 1、没走，即原地走棋：翻棋
 * 2、走一步：移动、兑子、吃子
 * 2、隔着格子走棋：炮吃子
 * @author zhangjian01
 *
 */
public abstract class AbstractPlayingNode {
	
	/**
	 * 根据走棋的格子情况，识别是那类走棋
	 * @param playerColor
	 * @param game
	 * @param fromX
	 * @param fromY
	 * @param toX
	 * @param toY
	 * @return
	 */
	public abstract boolean recognize(final int playerColor, final Game game, final int fromX, final int fromY, final int toX, final int toY);

	/**
	 * 处理走棋
	 * @param playerColor
	 * @param game
	 * @param fromCell
	 * @param toCell
	 * @throws GameException
	 */
	public abstract void handle(final int playerColor, final Game game, final Cell fromCell, final Cell toCell) throws GameException;

	/**
	 * 走棋
	 * 返回值为游戏是否结束
	 * @param playerColor
	 * @param game
	 * @param fromX
	 * @param fromY
	 * @param toX
	 * @param toY
	 * @throws GameException
	 */
	public void play(int playerColor, Game game, int fromX, int fromY, int toX, int toY) throws GameException 
	{
		if (recognize(playerColor, game, fromX, fromY, toX, toY))
		{
			Cell fromCell = game.getBoard().getCells()[fromX][fromY];
			Cell toCell = game.getBoard().getCells()[toX][toY];
			this.handle(playerColor, game, fromCell, toCell);
		}
		else
		{
			if(this.getNextNode() != null)
			{
				this.getNextNode().play(playerColor, game, fromX, fromY, toX, toY);
			}
			else
			{
				GameErrors.NotRecognizeCommand.throwGameException();
			}
		}
	}

	private AbstractPlayingNode nextNode;

	public AbstractPlayingNode getNextNode() {
		return nextNode;
	}

	public void setNextNode(AbstractPlayingNode nextNode) {
		this.nextNode = nextNode;
	}
}