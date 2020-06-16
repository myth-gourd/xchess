package com.game.xchess.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.game.xchess.exception.GameException;
import com.game.xchess.pojo.bo.Game;
import com.game.xchess.pojo.dto.GameResponse;
import com.game.xchess.pojo.dto.PlayingRequest;
import com.game.xchess.service.GameService;

@RestController
@RequestMapping(value = "/game")
public class GameConrtroller {

	@Autowired
	private GameService gameService;
	
	/**
	 * 开始游戏
	 * @param request
	 * @return
	 * @throws GameException
	 */
	@RequestMapping(value = "/start", method = RequestMethod.POST)
	public GameResponse start(@RequestBody PlayingRequest request) throws GameException
	{
		GameResponse rps = new GameResponse();
		Game game = gameService.start(request.getRoomNum());
		rps.setGame(game);
		rps.setGameOver(false);
		return rps;
	}
	
	/**
	 * 走棋
	 * @param request
	 * @return
	 * @throws GameException
	 */
	@RequestMapping(value = "/play", method = RequestMethod.POST)
	public GameResponse play(@RequestBody PlayingRequest request) throws GameException
	{
		GameResponse rps = gameService.play(request.getRoomNum(), request.getPlayerColor(), request.getFromX(), request.getFromY(), request.getToX(), request.getToY());
		return rps;
	}
}