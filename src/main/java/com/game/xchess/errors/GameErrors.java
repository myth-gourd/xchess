package com.game.xchess.errors;

/**
 * 游戏错误
 * @author zhangjian01
 *
 */
public class GameErrors {

	public static final GameError ArgumentError = new GameError(100, "参数错误");
	public static final GameError NotRecognizeCommand = new GameError(101, "不识别的游戏命令");
	public static final GameError UserNotGamePriviledge = new GameError(200, "没有此游戏的操作权限");
	public static final GameError IsNotYourTurn = new GameError(201, "没有轮到您走棋");
	public static final GameError CellHasNoPiece = new GameError(300, "格子上没有棋子");
	public static final GameError CanNotPlayingSideways = new GameError(301, "不能斜着走棋");
	public static final GameError PieceIsAlreadyUp = new GameError(301, "棋子已经是正面朝上");
	public static final GameError CellMustBeUp = new GameError(311, "棋子必须是翻过来的");
	public static final GameError OnlyCanPlayingSelfPiece = new GameError(302, "只能走自己的棋子");
	public static final GameError OnlyExchangeingOrEatingOtherPiece = new GameError(306, "只能吃或兑对方的子");
	public static final GameError ZuOnlyEatShuai = new GameError(307, "卒只能吃帅");
	public static final GameError ShuaiCanNotEatZu = new GameError(308, "帅不能吃足");
	public static final GameError SmallPieceCanNotEatBigPiece = new GameError(309, "小子不能吃大子");
	public static final GameError PaoOnlyThroughEating = new GameError(310, "炮只能隔着吃子");
	public static final GameError PaoMustThroughOneToEat = new GameError(311, "炮必须隔着一个子吃子");
}