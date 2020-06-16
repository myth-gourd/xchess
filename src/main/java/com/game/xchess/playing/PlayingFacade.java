package com.game.xchess.playing;

import com.game.xchess.consts.PieceValueConsts;
import com.game.xchess.exception.GameException;
import com.game.xchess.playing.algorithm.AbstractPlayingNode;
import com.game.xchess.playing.algorithm.MoreStepsPlayingNode;
import com.game.xchess.playing.algorithm.NoStepPlayingNode;
import com.game.xchess.playing.algorithm.OneStepPlayingNode;
import com.game.xchess.pojo.bo.Game;
import com.game.xchess.pojo.bo.Piece;

/**
 * 走棋门面
 * @author zhangjian01
 *
 */
public class PlayingFacade 
{
	private static final AbstractPlayingNode CHAIN;
	
	static{
		NoStepPlayingNode noStep = new NoStepPlayingNode();
		OneStepPlayingNode oneStep = new OneStepPlayingNode();
		MoreStepsPlayingNode moreStep = new MoreStepsPlayingNode();
		noStep.setNextNode(oneStep);
		oneStep.setNextNode(moreStep);
		CHAIN = noStep;
	}
	
	/**
	 * 走棋
	 * 返回值为 获胜一方的棋子颜色，返回-1则未分出胜负
	 * @param playerColor
	 * @param game
	 * @param fromX
	 * @param fromY
	 * @param toX
	 * @param toY
	 * @return
	 * @throws GameException
	 */
	public static int play(final int playerColor, final Game game, final int fromX, final int fromY, final int toX, final int toY) throws GameException
	{
		Piece toPiece = game.getBoard().getCells()[toX][toY].getPiece();
		//检查参数
		PlayingChecker.checkParamters(playerColor, game, fromX, fromY, toX, toY);
		//是否该玩家走棋
		PlayingChecker.checkYourTurn(playerColor, game);
		//是否走棋是直线
		PlayingChecker.checkDirection(fromX, fromY, toX, toY);
		//第一个格子必须有棋子
		PlayingChecker.checkCellHasPiece(game.getBoard().getCells()[fromX][fromY]);
		
		CHAIN.play(playerColor, game, fromX, fromY, toX, toY);
		//如果吃的或者兑的是帅，则游戏结束,输棋的是toPiece颜色的一方
		if (!PlayingUtil.isSameCell(fromX, fromY, toX, toY))
		{
			if (toPiece != null && toPiece.getValue() == PieceValueConsts.SHUAI)
			{
				return (toPiece.getColor() + 1)%2;
			}
		}
		game.nextColorTurn();
		return -1;
	}
}