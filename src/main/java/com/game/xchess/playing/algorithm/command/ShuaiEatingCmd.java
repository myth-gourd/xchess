package com.game.xchess.playing.algorithm.command;

import com.game.xchess.consts.PieceValueConsts;
import com.game.xchess.exception.GameException;
import com.game.xchess.playing.PlayingUtil;
import com.game.xchess.pojo.bo.Cell;

public class ShuaiEatingCmd extends AbstractPlayingCommandNode{

	@Override
	protected boolean recognize(Cell fromCell, Cell toCell) throws GameException {
		return fromCell.getPiece().getValue() == PieceValueConsts.SHUAI;
	}

	@Override
	protected void handle(Cell fromCell, Cell toCell) throws GameException 
	{
		PlayingUtil.shuaiEat(fromCell, toCell);
	}
}
