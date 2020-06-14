package com.game.xchess.errors;

import com.game.xchess.exception.GameException;

public class GameError 
{
	private final int errCode;
	
	private final String errMessage;
	
	public GameError(int errCode, String errMessage)
	{
		this.errCode = errCode;
		this.errMessage = errMessage;
	}
	
	public int getErrCode()
	{
		return errCode;
	}
	
	public String getErrMessage()
	{
		return errMessage;
	}
	
	public void throwGameException() throws GameException
	{
		throw new GameException(this);
	}

	@Override
	public String toString() {
		return "GameError [errCode=" + errCode + ", errMessage=" + errMessage + "]";
	}
}