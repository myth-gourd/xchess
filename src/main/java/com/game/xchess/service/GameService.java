package com.game.xchess.service;

import com.game.xchess.exception.GameException;
import com.game.xchess.pojo.bo.Game;
import com.game.xchess.pojo.dto.GameResponse;

/**
 * 游戏服务
 * @author zhangjian01
 *
 */
public interface GameService {

	Game start(int roomNum);

	GameResponse play(int roomNum, int playerColor, int fromX, int fromY, int toX, int toY) throws GameException;
}