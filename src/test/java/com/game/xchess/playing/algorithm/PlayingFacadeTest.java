package com.game.xchess.playing.algorithm;

import org.junit.Assert;
import org.junit.Test;

import com.game.xchess.consts.PieceColorConsts;
import com.game.xchess.errors.GameErrors;
import com.game.xchess.exception.GameException;
import com.game.xchess.playing.PlayingFacade;
import com.game.xchess.pojo.bo.Game;
import com.game.xchess.pojo.bo.Piece;
import com.game.xchess.pojo.bo.StandardPieces;

/**
 * 走棋接口测试
 * @author zhangjian01
 *
 */
public class PlayingFacadeTest {
	
	
	private Game getGame() {
		Game game = GameReady.getGame();
		game.getBoard().shuffle();
		return game;
	}

	/**
	 * 正常翻棋
	 */
	@Test
	public void FlippingTest() {
		try {
			Game game = getGame();
			PlayingFacade.play(PieceColorConsts.RED, game, 0, 0, 0, 0);
			Assert.assertTrue(game.getBoard().getCells()[0][0].getIsFrontUp());
		} catch (GameException e) {
			Assert.assertFalse(true);
		}
	}

	/**
	 * 已经翻过的棋，再翻报错
	 */
	@Test
	public void CanNotFlippingTest() {
		try {
			Game game = getGame();
			PlayingFacade.play(PieceColorConsts.RED, game, 0, 0, 0, 0);
			PlayingFacade.play(PieceColorConsts.RED, game, 0, 0, 0, 0);
			Assert.assertFalse(true);
		} catch (GameException e) {
			Assert.assertTrue(e.getGameError().equals(GameErrors.PieceIsAlreadyUp));
		}
	}

	/**
	 * 翻棋的格子没有棋子
	 */
	@Test
	public void FlippingNullTest() {
		try {
			Game game = getGame();
			game.getBoard().getCells()[0][0].setPiece(null);
			PlayingFacade.play(PieceColorConsts.RED, game, 0, 0, 0, 0);
			Assert.assertFalse(true);
		} catch (GameException e) {
			Assert.assertTrue(e.getGameError().equals(GameErrors.CellHasNoPiece));
		}
	}

	@Test
	public void MovingTest() {
		try {
			Game game = getGame();
			Piece testPiece = StandardPieces.getPieceArray()[0];
			game.getBoard().getCells()[0][0].setPiece(testPiece);
			game.getBoard().getCells()[0][0].setIsFrontUp(true);
			;
			game.getBoard().getCells()[0][1].setPiece(null);
			PlayingFacade.play(PieceColorConsts.RED, game, 0, 0, 0, 1);
			if (!game.getBoard().getCells()[0][0].hasPiece()
					&& testPiece.equals(game.getBoard().getCells()[0][1].getPiece())) {
				Assert.assertTrue(true);
			} else {
				Assert.assertFalse(true);
			}
		} catch (GameException e) {
			System.out.print(e.toString());
			Assert.assertFalse(true);
		}
	}

	/**
	 * 正常红士兑绿士
	 */
	@Test
	public void ExchangingTest() {
		try {
			Game game = getGame();
			Piece piece1 = StandardPieces.getPieceArray()[1];
			Piece piece2 = StandardPieces.getPieceArray()[17];

			game.getBoard().getCells()[0][0].setPiece(piece1);
			game.getBoard().getCells()[0][0].setIsFrontUp(true);
			game.getBoard().getCells()[0][1].setPiece(piece2);
			game.getBoard().getCells()[0][1].setIsFrontUp(true);
			;
			PlayingFacade.play(PieceColorConsts.RED, game, 0, 0, 0, 1);
			if (!game.getBoard().getCells()[0][0].hasPiece() && !game.getBoard().getCells()[0][1].hasPiece()) {
				Assert.assertTrue(true);
			} else {
				Assert.assertFalse(true);
			}
		} catch (GameException e) {
			System.out.print(e.toString());
			Assert.assertFalse(true);
		}
	}

	/**
	 * 红士兑绿士，绿士没有翻过来
	 */
	@Test
	public void ExchangingNotUpTest() {
		try {
			Game game = getGame();
			Piece piece1 = StandardPieces.getPieceArray()[1];
			Piece piece2 = StandardPieces.getPieceArray()[17];
			game.getBoard().getCells()[0][0].setPiece(piece1);
			game.getBoard().getCells()[0][0].setIsFrontUp(true);
			game.getBoard().getCells()[0][1].setPiece(piece2);
			PlayingFacade.play(PieceColorConsts.RED, game, 0, 0, 0, 1);
			Assert.assertFalse(true);
		} catch (GameException e) {
			if (e.getGameError().equals(GameErrors.CellMustBeUp)) {
				Assert.assertTrue(true);
			}
		}
	}

	// 红士兑红士
	@Test
	public void ExchangingSelfTest() {
		try {
			Game game = getGame();
			Piece piece1 = StandardPieces.getPieceArray()[1];
			Piece piece2 = StandardPieces.getPieceArray()[2];
			game.getBoard().getCells()[0][0].setPiece(piece1);
			game.getBoard().getCells()[0][0].setIsFrontUp(true);
			game.getBoard().getCells()[0][1].setPiece(piece2);
			game.getBoard().getCells()[0][1].setIsFrontUp(true);
			PlayingFacade.play(PieceColorConsts.RED, game, 0, 0, 0, 1);
			Assert.assertFalse(true);
		} catch (GameException e) {
			if (e.getGameError().equals(GameErrors.OnlyExchangeingOrEatingOtherPiece)) {
				Assert.assertTrue(true);
			}
		}
	}

	// 红卒吃绿帅
	@Test
	public void ZuEatShuai() {
		try {
			Game game = getGame();
			Piece piece1 = StandardPieces.getPieceArray()[11];
			Piece piece2 = StandardPieces.getPieceArray()[16];
			game.getBoard().getCells()[0][0].setPiece(piece1);
			game.getBoard().getCells()[0][0].setIsFrontUp(true);
			game.getBoard().getCells()[1][0].setPiece(piece2);
			game.getBoard().getCells()[1][0].setIsFrontUp(true);
			int result = PlayingFacade.play(PieceColorConsts.RED, game, 0, 0, 1, 0);
			if (result == PieceColorConsts.RED && !game.getBoard().getCells()[0][0].hasPiece()
					&& piece1.equals(game.getBoard().getCells()[1][0].getPiece())) {
				Assert.assertTrue(true);
			} else {
				Assert.assertFalse(true);
			}
		} catch (GameException e) {
			System.out.print(e.toString());
			Assert.assertFalse(true);
		}
	}

	// 红卒吃绿马
	@Test
	public void ZuEatMa() {
		try {
			Game game = getGame();
			Piece piece1 = StandardPieces.getPieceArray()[11];
			Piece piece2 = StandardPieces.getPieceArray()[21];
			game.getBoard().getCells()[0][0].setPiece(piece1);
			game.getBoard().getCells()[0][0].setIsFrontUp(true);
			game.getBoard().getCells()[1][0].setPiece(piece2);
			game.getBoard().getCells()[1][0].setIsFrontUp(true);
			PlayingFacade.play(PieceColorConsts.RED, game, 0, 0, 1, 0);
			Assert.assertFalse(true);
		} catch (GameException e) {
			if (e.getGameError().equals(GameErrors.ZuOnlyEatShuai)) {
				Assert.assertTrue(true);
			}
		}
	}

	//红帅吃绿卒
	@Test
	public void ShuaiEatZu() {
		try {
			Game game = getGame();
			Piece piece1 = StandardPieces.getPieceArray()[0];
			Piece piece2 = StandardPieces.getPieceArray()[27];
			game.getBoard().getCells()[0][0].setPiece(piece1);
			game.getBoard().getCells()[0][0].setIsFrontUp(true);
			game.getBoard().getCells()[1][0].setPiece(piece2);
			game.getBoard().getCells()[1][0].setIsFrontUp(true);
			PlayingFacade.play(PieceColorConsts.RED, game, 0, 0, 1, 0);
			Assert.assertFalse(true);
		} catch (GameException e) {
			if (e.getGameError().equals(GameErrors.ShuaiCanNotEatZu)) {
				Assert.assertTrue(true);
			}
		}
	}
	
	//红帅吃绿车
	@Test
	public void ShuaiEatChe() {
		try {
			Game game = getGame();
			Piece piece1 = StandardPieces.getPieceArray()[0];
			Piece piece2 = StandardPieces.getPieceArray()[23];
			game.getBoard().getCells()[0][0].setPiece(piece1);
			game.getBoard().getCells()[0][0].setIsFrontUp(true);
			game.getBoard().getCells()[1][0].setPiece(piece2);
			game.getBoard().getCells()[1][0].setIsFrontUp(true);
			int result = PlayingFacade.play(PieceColorConsts.RED, game, 0, 0, 1, 0);
			if (result == -1 && !game.getBoard().getCells()[0][0].hasPiece()
					&& piece1.equals(game.getBoard().getCells()[1][0].getPiece())) {
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		} catch (GameException e) {
			System.out.print(e.toString());
			Assert.assertFalse(true);
		}
	}
	
	//红象吃绿卒
	@Test
	public void XiangEatZu() {
		try {
			Game game = getGame();
			Piece piece1 = StandardPieces.getPieceArray()[3];
			Piece piece2 = StandardPieces.getPieceArray()[27];
			game.getBoard().getCells()[2][2].setPiece(piece1);
			game.getBoard().getCells()[2][2].setIsFrontUp(true);
			game.getBoard().getCells()[2][1].setPiece(piece2);
			game.getBoard().getCells()[2][1].setIsFrontUp(true);
			int result = PlayingFacade.play(PieceColorConsts.RED, game, 2, 2, 2, 1);
			if (result == -1 && !game.getBoard().getCells()[2][2].hasPiece()
					&& piece1.equals(game.getBoard().getCells()[2][1].getPiece())) {
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		} catch (GameException e) {
			System.out.print(e.toString());
			Assert.assertFalse(true);
		}
	}
	
	/**
	 * 车吃象
	 */
	@Test
	public void CheEatXiang() {
		try {
			Game game = getGame();
			Piece piece1 = StandardPieces.getPieceArray()[7];
			Piece piece2 = StandardPieces.getPieceArray()[19];
			game.getBoard().getCells()[2][2].setPiece(piece1);
			game.getBoard().getCells()[2][2].setIsFrontUp(true);
			game.getBoard().getCells()[2][1].setPiece(piece2);
			game.getBoard().getCells()[2][1].setIsFrontUp(true);
			PlayingFacade.play(PieceColorConsts.RED, game, 2, 2, 2, 1);
			Assert.assertTrue(false);
		} catch (GameException e) {
			if(e.equals(GameErrors.SmallPieceCanNotEatBigPiece))
			{
				Assert.assertTrue(true);
						
			}
		}
	}
	
	/**
	 * 炮挨着吃子
	 */
	@Test
	public void PaoOneStep() {
		try {
			Game game = getGame();
			Piece piece1 = StandardPieces.getPieceArray()[9];
			Piece piece2 = StandardPieces.getPieceArray()[19];
			game.getBoard().getCells()[2][2].setPiece(piece1);
			game.getBoard().getCells()[2][2].setIsFrontUp(true);
			game.getBoard().getCells()[2][1].setPiece(piece2);
			game.getBoard().getCells()[2][1].setIsFrontUp(true);
			PlayingFacade.play(PieceColorConsts.RED, game, 2, 2, 2, 1);
			Assert.assertTrue(false);
		} catch (GameException e) {
			if (e.equals(GameErrors.PaoMustThroughOneToEat))
			{
				Assert.assertFalse(true);
			}
		}
	}
	
	/**
	 * 炮没有隔着子吃子 
	 */
	@Test
	public void PaoAcrossEmptyEating() {
		try {
			Game game = getGame();
			Piece piece1 = StandardPieces.getPieceArray()[9];
			Piece piece2 = StandardPieces.getPieceArray()[19];
			game.getBoard().getCells()[2][2].setPiece(piece1);
			game.getBoard().getCells()[2][2].setIsFrontUp(true);
			game.getBoard().getCells()[2][3].setPiece(null);
			game.getBoard().getCells()[2][4].setPiece(null);
			game.getBoard().getCells()[2][5].setPiece(null);
			game.getBoard().getCells()[2][6].setPiece(piece2);
			game.getBoard().getCells()[2][6].setIsFrontUp(true);
			PlayingFacade.play(PieceColorConsts.RED, game, 2, 2, 2, 6);
			Assert.assertTrue(false);
		} catch (GameException e) {
			if (e.equals(GameErrors.PaoMustThroughOneToEat))
			{
				Assert.assertFalse(true);
			}
		}
	}
	
	/**
	 * 炮隔着2个子吃子 
	 */
	@Test
	public void PaoAcrossTwoPieceEating() {
		try {
			Game game = getGame();
			Piece piece1 = StandardPieces.getPieceArray()[9];
			Piece piece2 = StandardPieces.getPieceArray()[19];
			game.getBoard().getCells()[2][2].setPiece(piece1);
			game.getBoard().getCells()[2][2].setIsFrontUp(true);
			game.getBoard().getCells()[2][3].setPiece(StandardPieces.getPieceArray()[10]);
			game.getBoard().getCells()[2][4].setPiece(StandardPieces.getPieceArray()[11]);
			game.getBoard().getCells()[2][5].setPiece(null);
			game.getBoard().getCells()[2][6].setPiece(piece2);
			game.getBoard().getCells()[2][6].setIsFrontUp(true);
			PlayingFacade.play(PieceColorConsts.RED, game, 2, 2, 2, 6);
			Assert.assertTrue(false);
		} catch (GameException e) {
			if (e.equals(GameErrors.PaoMustThroughOneToEat))
			{
				Assert.assertFalse(true);
			}
		}
	}
	
	
	/**
	 * 红炮吃未翻过来的红帅 
	 */
	@Test
	public void PaoEatShuai() {
		try {
			Game game = getGame();
			Piece piece1 = StandardPieces.getPieceArray()[9];
			Piece piece2 = StandardPieces.getPieceArray()[0];
			game.getBoard().getCells()[2][2].setPiece(piece1);
			game.getBoard().getCells()[2][2].setIsFrontUp(true);
			game.getBoard().getCells()[2][3].setPiece(StandardPieces.getPieceArray()[1]);
			game.getBoard().getCells()[2][4].setPiece(null);
			game.getBoard().getCells()[2][5].setPiece(null);
			game.getBoard().getCells()[2][6].setPiece(piece2);
			game.getBoard().getCells()[2][6].setIsFrontUp(false);
			int result = PlayingFacade.play(PieceColorConsts.RED, game, 2, 2, 2, 6);
			if (result == PieceColorConsts.GREEN && !game.getBoard().getCells()[2][2].hasPiece()
					&& piece1.equals(game.getBoard().getCells()[2][6].getPiece())) {
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		} catch (GameException e) {
			System.out.print(e.toString());
			Assert.assertFalse(true);
		}
	}
	
	@Test
	public void PaoEatShi() {
		try {
			Game game = getGame();
			Piece piece1 = StandardPieces.getPieceArray()[9];
			Piece piece2 = StandardPieces.getPieceArray()[17];
			game.getBoard().getCells()[2][2].setPiece(piece1);
			game.getBoard().getCells()[2][2].setIsFrontUp(true);
			game.getBoard().getCells()[2][3].setPiece(StandardPieces.getPieceArray()[1]);
			game.getBoard().getCells()[2][4].setPiece(null);
			game.getBoard().getCells()[2][5].setPiece(null);
			game.getBoard().getCells()[2][6].setPiece(piece2);
			game.getBoard().getCells()[2][6].setIsFrontUp(true);
			int result = PlayingFacade.play(PieceColorConsts.RED, game, 2, 2, 2, 6);
			if (result == -1 && !game.getBoard().getCells()[2][2].hasPiece()
					&& piece1.equals(game.getBoard().getCells()[2][6].getPiece())) {
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		} catch (GameException e) {
			System.out.print(e.toString());
			Assert.assertFalse(true);
		}
	}
}