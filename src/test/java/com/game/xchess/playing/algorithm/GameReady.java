package com.game.xchess.playing.algorithm;

import com.game.xchess.pojo.bo.Board;
import com.game.xchess.pojo.bo.Game;

/**
 * 测试数据准备
 * @author zhangjian01
 *
 */
public class GameReady {

	public static Game getGame()
	{
		Game game = new Game(new Board());
		game.getBoard().shuffle();
		return game;
	}
}