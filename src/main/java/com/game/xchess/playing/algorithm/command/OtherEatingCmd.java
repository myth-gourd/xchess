package com.game.xchess.playing.algorithm.command;

import com.game.xchess.exception.GameException;
import com.game.xchess.playing.PlayingUtil;
import com.game.xchess.pojo.bo.Cell;

public class OtherEatingCmd extends AbstractPlayingCommandNode {

	@Override
	protected boolean recognize(final Cell fromCell, final Cell toCell) throws GameException {
		return true;
	}

	@Override
	protected void handle(final Cell fromCell, final Cell toCell) throws GameException {
		PlayingUtil.eat(fromCell, toCell);
	}
}