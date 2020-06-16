package com.game.xchess.playing.algorithm;

import com.game.xchess.exception.GameException;
import com.game.xchess.playing.PlayingUtil;
import com.game.xchess.pojo.bo.Cell;
import com.game.xchess.pojo.bo.Game;

/**
 * 没走棋，即同一个单元格走棋（翻棋）
 * 
 * @author zhangjian01
 *
 */
public class NoStepPlayingNode extends AbstractPlayingNode {
	
	@Override
	public boolean recognize(final int playerColor, final Game game, final int fromX, final int fromY, final int toX, final int toY) {
		return PlayingUtil.isSameCell(fromX, fromY, toX, toY);
	}

	@Override
	public void handle(final int playerColor, final Game game, final Cell fromCell, final Cell toCell) throws GameException {
		PlayingUtil.flipping(fromCell);
	}
}