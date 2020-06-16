package com.game.xchess.service.impl;

import org.springframework.stereotype.Service;

import com.game.xchess.consts.PieceColorConsts;
import com.game.xchess.exception.GameException;
import com.game.xchess.playing.PlayingFacade;
import com.game.xchess.pojo.bo.Area;
import com.game.xchess.pojo.bo.Game;
import com.game.xchess.pojo.bo.Room;
import com.game.xchess.pojo.dto.GameResponse;
import com.game.xchess.service.GameService;

@Service
public class GameServiceImpl implements GameService {

	private static Area area;

	static {
		area = new Area();
	}

	@Override
	public Game start(int roomNum) {
		Room room = area.getRoom(roomNum);
		Game game = room.getGame();
		game.getBoard().shuffle();
		game.setCurrentColorTurn(PieceColorConsts.RED);
		return game;
	}

	@Override
	public GameResponse play(int roomNum, int playerColor, int fromX, int fromY, int toX, int toY)
			throws GameException {
		GameResponse rps = new GameResponse();
		Room room = area.getRoom(roomNum);
		Game game = room.getGame();
		int result = PlayingFacade.play(playerColor, game, fromX, fromY, toX, toY);
		if (result < 0) {
			rps.setGameOver(false);
		} else {
			rps.setGameOver(true);
			rps.setWinnedPlayerColor(result);
		}
		rps.setGame(game);
		return rps;
	}
}