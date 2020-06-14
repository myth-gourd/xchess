package com.game.xchess.exception;

import com.game.xchess.errors.GameError;

/**
 * 游戏异常
 * @author zhangjian01
 *
 */
public class GameException extends Exception {

	/**
	 * 引发异常的游戏错误
	 */
	private GameError error;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GameException(GameError error){
		this.error = error;
	}
	
	public GameError getGameError(){
		return this.error;
	}

	@Override
	public String toString() {
		return "GameException [error=" + error.toString() + "]";
	}
}
