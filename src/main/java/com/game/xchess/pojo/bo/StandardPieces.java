package com.game.xchess.pojo.bo;

import com.game.xchess.consts.PieceValueConsts;
import com.game.xchess.consts.PieceColorConsts;
import com.game.xchess.consts.PieceNameConsts;

/**
 * 一副象棋
 * 
 * @author zhangjian01
 *
 */
public class StandardPieces {
	private static final Piece[] PIECES_ARRAY;

	static {
		PIECES_ARRAY = new Piece[] {
			new Piece(0, PieceNameConsts.SHUAI, PieceColorConsts.RED, PieceValueConsts.SHUAI),
			new Piece(1, PieceNameConsts.SHI, PieceColorConsts.RED,  PieceValueConsts.SHI),
			new Piece(2, PieceNameConsts.SHI, PieceColorConsts.RED,  PieceValueConsts.SHI),
			new Piece(3, PieceNameConsts.XIANG, PieceColorConsts.RED, PieceValueConsts.XIANG),
			new Piece(4, PieceNameConsts.XIANG, PieceColorConsts.RED, PieceValueConsts.XIANG),
			new Piece(5, PieceNameConsts.MA, PieceColorConsts.RED, PieceValueConsts.MA),
			new Piece(6, PieceNameConsts.MA, PieceColorConsts.RED, PieceValueConsts.MA),
			new Piece(7, PieceNameConsts.CHE, PieceColorConsts.RED, PieceValueConsts.CHE),
			new Piece(8, PieceNameConsts.CHE, PieceColorConsts.RED, PieceValueConsts.CHE),
			new Piece(9, PieceNameConsts.PAO, PieceColorConsts.RED, PieceValueConsts.PAO),
			new Piece(10, PieceNameConsts.PAO, PieceColorConsts.RED, PieceValueConsts.PAO),
			new Piece(11, PieceNameConsts.ZU, PieceColorConsts.RED, PieceValueConsts.ZU),
			new Piece(12, PieceNameConsts.ZU, PieceColorConsts.RED, PieceValueConsts.ZU),
			new Piece(13, PieceNameConsts.ZU, PieceColorConsts.RED, PieceValueConsts.ZU),
			new Piece(14, PieceNameConsts.ZU, PieceColorConsts.RED, PieceValueConsts.ZU),
			new Piece(15, PieceNameConsts.ZU, PieceColorConsts.RED, PieceValueConsts.ZU),
			new Piece(16, PieceNameConsts.SHUAI, PieceColorConsts.GREEN, PieceValueConsts.SHUAI),
			new Piece(17, PieceNameConsts.SHI, PieceColorConsts.GREEN,  PieceValueConsts.SHI),
			new Piece(18, PieceNameConsts.SHI, PieceColorConsts.GREEN,  PieceValueConsts.SHI),
			new Piece(19, PieceNameConsts.XIANG, PieceColorConsts.GREEN, PieceValueConsts.XIANG),
			new Piece(20, PieceNameConsts.XIANG, PieceColorConsts.GREEN, PieceValueConsts.XIANG),
			new Piece(21, PieceNameConsts.MA, PieceColorConsts.GREEN, PieceValueConsts.MA),
			new Piece(22, PieceNameConsts.MA, PieceColorConsts.GREEN, PieceValueConsts.MA),
			new Piece(23, PieceNameConsts.CHE, PieceColorConsts.GREEN, PieceValueConsts.CHE),
			new Piece(24, PieceNameConsts.CHE, PieceColorConsts.GREEN, PieceValueConsts.CHE),
			new Piece(25, PieceNameConsts.PAO, PieceColorConsts.GREEN, PieceValueConsts.PAO),
			new Piece(26, PieceNameConsts.PAO, PieceColorConsts.GREEN, PieceValueConsts.PAO),
			new Piece(27, PieceNameConsts.ZU, PieceColorConsts.GREEN, PieceValueConsts.ZU),
			new Piece(28, PieceNameConsts.ZU, PieceColorConsts.GREEN, PieceValueConsts.ZU),
			new Piece(29, PieceNameConsts.ZU, PieceColorConsts.GREEN, PieceValueConsts.ZU),
			new Piece(30, PieceNameConsts.ZU, PieceColorConsts.GREEN, PieceValueConsts.ZU),
			new Piece(31, PieceNameConsts.ZU, PieceColorConsts.GREEN, PieceValueConsts.ZU) };
	}
	
	public static Piece[] getPieceArray(){
		return PIECES_ARRAY;
	}
}