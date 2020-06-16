package com.game.xchess.playing;

import com.game.xchess.consts.PieceColorConsts;
import com.game.xchess.errors.GameErrors;
import com.game.xchess.exception.GameException;
import com.game.xchess.pojo.bo.Cell;
import com.game.xchess.pojo.bo.Game;
import com.game.xchess.pojo.bo.Piece;

/**
 * 走棋检查
 * @author zhangjian01
 *
 */
public class PlayingChecker {

	/**
	 * 检查玩家走棋的颜色
	 * @param playerColor
	 * @return
	 */
	private static boolean isPieceColor(final int playerColor) {
		return playerColor == PieceColorConsts.GREEN || playerColor == PieceColorConsts.RED;
	}

	/**
	 * 检查棋盘X坐标
	 * @param x
	 * @return
	 */
	private static boolean isXValid(final int x) {
		return x > -1 && x < 4;
	}

	/**
	 * 检查棋盘Y坐标
	 * @param y
	 * @return
	 */
	private static boolean isYValid(final int y) {
		return y > -1 && y < 8;
	}

	/**
	 * 检查输入参数
	 * @param playerColor
	 * @param game
	 * @param fromX
	 * @param fromY
	 * @param toX
	 * @param toY
	 * @throws GameException
	 */
	public static void checkParamters(final int playerColor, final Game game, final int fromX, final int fromY, final int toX, final int toY) throws GameException {
		if (!isPieceColor(playerColor) || game == null || !isXValid(fromX) || !isXValid(toX) || !isYValid(fromY)
				|| !isYValid(toY)) {
			GameErrors.ArgumentError.throwGameException();
		}
	}

	/**
	 * 检查是否轮到该颜色的用户走棋
	 * 
	 * @param playerColor
	 * @param game
	 * @throws GameException
	 */
	public static void checkYourTurn(final int playerColor, final Game game) throws GameException {
		if (playerColor != game.getCurrentColorTurn()) {
			GameErrors.IsNotYourTurn.throwGameException();
		}
	}

	/**
	 * 检查是否横竖走棋,不能斜着走
	 * 
	 * @param fromX
	 * @param fromY
	 * @param toX
	 * @param toY
	 * @throws GameException
	 */
	public static void checkDirection(final int fromX, final int fromY, final int toX, final int toY) throws GameException {
		if (fromX != toX && fromY != toY) {
			GameErrors.CanNotPlayingSideways.throwGameException();
		}
	}

	/**
	 * 检查格子里必须有棋子
	 * 
	 * @param cell
	 * @throws GameException
	 */
	public static void checkCellHasPiece(final Cell cell) throws GameException {
		if (!cell.hasPiece()) {
			GameErrors.CellHasNoPiece.throwGameException();
		}
	}

	/**
	 * 检查格子里棋子是否正面朝上。 若格子里没有棋子，则不检查。
	 * 
	 * @param cell
	 * @throws GameException
	 */
	public static void checkCellIsUp(final Cell cell) throws GameException {
		if (cell.hasPiece() && !cell.getIsFrontUp()) {
			GameErrors.CellMustBeUp.throwGameException();
		}
	}

	/**
	 * 检查单元格的妻子是否是自己的棋子
	 * @param playerColor
	 * @param cell
	 * @throws GameException 
	 */
	public static void checkFromCellIsSelf(final int playerColor, final Cell cell) throws GameException
	{
		Piece piece = cell.getPiece();
		if (piece != null && cell.getIsFrontUp() && piece.getColor() != playerColor) {
			GameErrors.OnlyCanPlayingSelfPiece.throwGameException();
		}
	}
	
	/**
	 * 检查单元格的棋子是否是对方的棋子
	 * 
	 * @param playerColor 玩家颜色
	 * @throws GameException
	 */
	public static void checkToCellPieceIsOfOtherPlayer(final int playerColor, final Cell cell) throws GameException {
		Piece piece = cell.getPiece();
		if (piece != null && cell.getIsFrontUp() && piece.getColor() == playerColor) {
			GameErrors.OnlyExchangeingOrEatingOtherPiece.throwGameException();
		}
	}
}