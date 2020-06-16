package com.game.xchess.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.xchess.exception.GameException;
import com.game.xchess.pojo.dto.Response;

@ControllerAdvice
public class ControllerHandler 
{
	@ExceptionHandler(GameException.class)
	@ResponseBody
	public Response handlerJException(GameException ept)
	{
		Response rps = new Response();
		rps.setError(ept.getGameError());
		return rps;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Response handlerException(Exception ept)
	{
		Response rps = new Response();
		rps.setStatus(500);
		rps.setMessage(ept.getMessage());
		return rps;
	}
}