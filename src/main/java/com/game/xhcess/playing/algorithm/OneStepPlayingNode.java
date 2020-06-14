package com.game.xhcess.playing.algorithm;

import com.game.xchess.exception.GameException;
import com.game.xchess.pojo.bo.Cell;
import com.game.xchess.pojo.bo.Game;
import com.game.xhcess.playing.PlayingChecker;
import com.game.xhcess.playing.PlayingUtil;
import com.game.xhcess.playing.algorithm.command.AbstractPlayingCommandNode;
import com.game.xhcess.playing.algorithm.command.ExchangingCmd;
import com.game.xhcess.playing.algorithm.command.MovingCmd;
import com.game.xhcess.playing.algorithm.command.OtherEatingCmd;
import com.game.xhcess.playing.algorithm.command.ShuaiEatingCmd;
import com.game.xhcess.playing.algorithm.command.ZuEatingCmd;

/**
 * 走一步棋
 * 可能是移动
 * 可能是兑子
 * 可能是吃子（对卒、帅、其他除了炮之外的吃子，一一分析）
 * 
 * @author zhangjian01
 *
 */
public class OneStepPlayingNode extends AbstractPlayingNode
{
	private static AbstractPlayingCommandNode CHAIN;
	
	static{
		MovingCmd moving = new MovingCmd();
		ExchangingCmd exchanging = new ExchangingCmd();
		ZuEatingCmd zueating = new ZuEatingCmd();
		ShuaiEatingCmd shuaieating = new ShuaiEatingCmd();
		OtherEatingCmd otherEating = new OtherEatingCmd();
		moving.setNextNode(exchanging);
		exchanging.setNextNode(zueating);
		zueating.setNextNode(shuaieating);
		shuaieating.setNextNode(otherEating);
		CHAIN = moving;
	}

	@Override
	public boolean recognize(final int playerColor, final Game game, final int fromX, final int fromY, final int toX, final int toY) {
		return PlayingUtil.isNearCell(fromX, fromY, toX, toY);
	}

	@Override
	public void handle(final int playerColor, final Game game, final Cell fromCell, final Cell toCell) throws GameException {
		PlayingChecker.checkCellIsUp(fromCell);
		PlayingChecker.checkCellIsUp(toCell);
		PlayingChecker.checkToCellPieceIsOfOtherPlayer(playerColor, toCell);
		CHAIN.exce(fromCell, toCell);
	}
}