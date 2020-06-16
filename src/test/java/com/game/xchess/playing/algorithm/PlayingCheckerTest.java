package com.game.xchess.playing.algorithm;

import org.junit.Assert;
import org.junit.Test;

import com.game.xchess.consts.PieceColorConsts;
import com.game.xchess.errors.GameErrors;
import com.game.xchess.exception.GameException;
import com.game.xchess.playing.PlayingChecker;
import com.game.xchess.pojo.bo.Game;

public class PlayingCheckerTest 
{
	/**
	 * 初始化的时候第一个走棋的是红色，若绿色走则出错
	 */
	@Test
	public void checkColorTurn(){
		Game game = GameReady.getGame();
		try {
			PlayingChecker.checkYourTurn(PieceColorConsts.GREEN, game);
		} catch (GameException e) {
			Assert.assertTrue(e.getGameError().getErrCode() == GameErrors.IsNotYourTurn.getErrCode());
		}
	}
	
	/*
	 * 检查不能斜着走棋
	 */
	@Test
	public void checkMovingDirection()
	{
		try {
			PlayingChecker.checkDirection(0, 0, 1, 1);
		} catch (GameException e) {
			Assert.assertTrue(e.getGameError().getErrCode() == GameErrors.CanNotPlayingSideways.getErrCode());
		}
	}
}