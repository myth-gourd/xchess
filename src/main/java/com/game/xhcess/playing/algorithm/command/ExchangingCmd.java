package com.game.xhcess.playing.algorithm.command;

import com.game.xchess.exception.GameException;
import com.game.xchess.pojo.bo.Cell;
import com.game.xchess.pojo.bo.Piece;
import com.game.xhcess.playing.PlayingUtil;

/**
 * 兑子
 * @author zhangjian01
 *
 */
public class ExchangingCmd extends AbstractPlayingCommandNode 
{
	/**
	 * 检查两个棋子的值是否相等
	 * to位置肯定是有子的，否则Moving 就执行了
	 */
	@Override
	protected boolean recognize(final Cell fromCell, final Cell toCell) throws GameException 
	{
		Piece fromPiece = fromCell.getPiece();
		Piece toPiece = toCell.getPiece();
		return fromPiece.getValue() == toPiece.getValue();
	}

	@Override
	protected void handle(final Cell fromCell, final Cell toCell) throws GameException {
		PlayingUtil.exchange(fromCell, toCell);
	}
}