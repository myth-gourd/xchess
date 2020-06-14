package com.game.xhcess.playing;

import com.game.xchess.consts.PieceValueConsts;
import com.game.xchess.errors.GameErrors;
import com.game.xchess.exception.GameException;
import com.game.xchess.pojo.bo.Board;
import com.game.xchess.pojo.bo.Cell;

/**
 * 执行走棋工具
 * 
 * @author zhangjian01
 *
 */
public class PlayingUtil {

	/**
	 * 验证两个格子是否是同一个格子
	 * 
	 * @param fromX
	 * @param fromY
	 * @param toX
	 * @param toY
	 * @return
	 */
	public static boolean isSameCell(final int fromX, final int fromY, final int toX, final int toY) {
		return fromX == toX && fromY == toY;
	}

	/**
	 * 验证 两个格子是否挨着
	 * 
	 * @param fromX
	 * @param fromY
	 * @param toX
	 * @param toY
	 * @return
	 */
	public static boolean isNearCell(final int fromX, final int fromY, final int toX, final int toY) {
		return distance(fromX, fromY, toX, toY) == 1;
	}

	/**
	 * 验证两个格子不挨着（中间隔着几个格子）
	 * 
	 * @param fromX
	 * @param fromY
	 * @param toX
	 * @param toY
	 * @return
	 */
	public static boolean isAcrossCell(final int fromX, final int fromY, final int toX, final int toY) {
		return distance(fromX, fromY, toX, toY) > 1;
	}

	/**
	 * 计算两个格子之间的距离 同一个格子：0 相邻的格子：1 两个格子不在一条线上：-1
	 * 
	 * @param fromX
	 * @param fromY
	 * @param toX
	 * @param toY
	 * @return
	 */
	public static int distance(final int fromX, final int fromY, final int toX, final int toY) {
		if (fromX == toX) {
			return Math.abs(toY - fromY);
		}
		if (fromY == toY) {
			return Math.abs(toX - fromX);
		}
		return -1;
	}

	/**
	 * 计算两个格子之间有几个棋子 两个格子不在一条线上：-1
	 * 
	 * @param board
	 * @param fromX
	 * @param fromY
	 * @param toX
	 * @param toY
	 * @return
	 */
	public static int hasPieceNumBetween(final Board board, final int fromX, final int fromY, final int toX, final int toY) {
		if (fromX == toX) {
			int num = 0;
			for (int y = fromY + 1; y < toY; y++) {
				if (board.getCells()[fromX][y].hasPiece()) {
					num++;
				}
			}
			return num;
		}
		if (fromY == toY) {
			int num = 0;
			for (int x = fromX + 1; x < toX; x++) {
				if (board.getCells()[x][fromY].hasPiece()) {
					num++;
				}
			}
			return num;
		}
		return -1;
	}
	
	/**
	 * 翻子
	 * 
	 * @param cell
	 * @throws GameException
	 */
	public static void flipping(final Cell cell) throws GameException {
		if (cell.getIsFrontUp()) {
			GameErrors.PieceIsAlreadyUp.throwGameException();
		} else {
			cell.setIsFrontUp(true);
		}
	}

	/**
	 * 移动
	 * 
	 * @param fromCell
	 * @param toCell
	 */
	public static void move(final Cell fromCell, final Cell toCell) {
		toCell.setPiece(fromCell.getPiece());
		fromCell.setPiece(null);
	}

	/**
	 * 兑子
	 * 
	 * @param fromCell
	 * @param toCell
	 */
	public static void exchange(final Cell fromCell, final Cell toCell) {
		fromCell.setPiece(null);
		toCell.setPiece(null);
	}

	/**
	 * 吃子 实际效果与移动一致
	 * 
	 * @param fromCell
	 * @param toCell
	 */
	private static void doEating(final Cell fromCell, final Cell toCell) {
		move(fromCell, toCell);
	}
	
	/**
	 * 炮吃子
	 * @param playerColor
	 * @param board
	 * @param fromCell
	 * @param toCell
	 * @throws GameException
	 */
	public static void paoEat(final int playerColor, final Board board, final Cell fromCell,  final Cell toCell) throws GameException
	{
		PlayingChecker.checkCellHasPiece(toCell);
		if (PlayingUtil.hasPieceNumBetween(board, fromCell.getX(), fromCell.getY(), toCell.getX(), toCell.getY()) != 1)
		{
			GameErrors.PaoMustThroughOneToEat.throwGameException();
		}
		if (toCell.getIsFrontUp())
		{
			PlayingChecker.checkToCellPieceIsOfOtherPlayer(playerColor, toCell);
		}
		PlayingUtil.doEating(fromCell, toCell);
	}
	
	/**
	 * 卒吃子
	 * @param fromCell
	 * @param toCell
	 * @throws GameException
	 */
	public static void zuEat(final Cell fromCell,  final Cell toCell) throws GameException{
		if (toCell.getPiece().getValue() != PieceValueConsts.SHUAI) {
			GameErrors.ZuOnlyEatShuai.throwGameException();
		}
		PlayingUtil.doEating(fromCell, toCell);
	}
	
	/*
	 *帅吃子
	 */
	public static void shuaiEat(final Cell fromCell, final Cell toCell) throws GameException
	{
		if (toCell.getPiece().getValue() == PieceValueConsts.ZU)
		{
			GameErrors.ShuaiCanNotEatZu.throwGameException();
		}
		PlayingUtil.eat(fromCell, toCell);
	}
	
	/**
	 * 吃子
	 * 不包括炮吃子
	 * @param fromCell
	 * @param toCell
	 * @throws GameException
	 */
	public static void eat(final Cell fromCell,  final Cell toCell) throws GameException
	{
		if (fromCell.getPiece().getValue() == PieceValueConsts.PAO)
		{
			GameErrors.PaoOnlyThroughEating.throwGameException();
		}
		if (toCell.getPiece().getValue() >= fromCell.getPiece().getValue())
		{
			GameErrors.SmallPieceCanNotEatBigPiece.throwGameException();
		}
		PlayingUtil.doEating(fromCell, toCell);
	}
}