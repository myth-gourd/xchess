package com.game.xchess.playing.algorithm.command;

import com.game.xchess.consts.PieceValueConsts;
import com.game.xchess.exception.GameException;
import com.game.xchess.playing.PlayingUtil;
import com.game.xchess.pojo.bo.Cell;

/**
 * 足吃子
 * 
 * @author zhangjian01
 *
 */
public class ZuEatingCmd extends AbstractPlayingCommandNode {

	@Override
	protected boolean recognize(Cell fromCell, Cell toCell) throws GameException {
		return fromCell.getPiece().getValue() == PieceValueConsts.ZU;
	}

	@Override
	protected void handle(Cell fromCell, Cell toCell) throws GameException {
		PlayingUtil.zuEat(fromCell, toCell);
	}
}