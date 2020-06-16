package com.game.xchess.pojo.dto;

import com.game.xchess.errors.GameError;
import com.game.xchess.exception.GameException;

public class Response 
{
	/**
	 * 是否成功
	 */
	boolean success;
	
	/**
	 * 编码
	 * 200：成功
	 * 其他：错误
	 */
	int status;
	
	/**
	 * 错误信息，空字符串代表无错误，status=200的情况
	 */
	String message;
	
	public Response(){
		this.success = true;
		this.status  = 200;
		this.message = "";
	}
	
	public void setError(GameError error)
	{
		this.success = false;
		this.status = error.getErrCode();
		this.message = error.getErrMessage();
	}
	
	public void setJException(GameException ept)
	{
		this.success = false;
		this.status = ept.getGameError().getErrCode();
		this.message = ept.getGameError().getErrMessage();
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}