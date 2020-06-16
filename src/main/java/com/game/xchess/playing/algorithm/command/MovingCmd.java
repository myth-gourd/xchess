package com.game.xchess.playing.algorithm.command;

import com.game.xchess.exception.GameException;
import com.game.xchess.playing.PlayingUtil;
import com.game.xchess.pojo.bo.Cell;


/**
 * 移动棋子
 * @author zhangjian01
 *
 */
public class MovingCmd extends AbstractPlayingCommandNode
{
	@Override
	protected boolean recognize(final Cell fromCell, final Cell toCell) throws GameException {
		return !toCell.hasPiece();
	}

	@Override
	protected void handle(final Cell fromCell, final Cell toCell) throws GameException {
		PlayingUtil.move(fromCell, toCell);
	}
}