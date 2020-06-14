package com.game.xhcess.playing.algorithm;

import com.game.xchess.consts.PieceValueConsts;
import com.game.xchess.errors.GameErrors;
import com.game.xchess.exception.GameException;
import com.game.xchess.pojo.bo.Cell;
import com.game.xchess.pojo.bo.Game;
import com.game.xhcess.playing.PlayingChecker;
import com.game.xhcess.playing.PlayingUtil;

/**
 * 隔着棋子走棋 只有炮吃子才能这么走
 * 
 * @author zhangjian01
 *
 */
public class MoreStepsPlayingNode extends AbstractPlayingNode {
	@Override
	public boolean recognize(final int playerColor, final Game game, final int fromX, final int fromY, final int toX,
			final int toY) {
		return PlayingUtil.distance(fromX, fromY, toX, toY) > 1;
	}

	@Override
	public void handle(final int playerColor, final Game game, final Cell fromCell, final Cell toCell)
			throws GameException {
		PlayingChecker.checkCellIsUp(fromCell);
		PlayingChecker.checkToCellPieceIsOfOtherPlayer(playerColor, toCell);
		if (fromCell.getPiece().getValue() != PieceValueConsts.PAO) {
			GameErrors.PaoOnlyThroughEating.throwGameException();
		}
		PlayingUtil.paoEat(playerColor, game.getBoard(), fromCell, toCell);
	}
}