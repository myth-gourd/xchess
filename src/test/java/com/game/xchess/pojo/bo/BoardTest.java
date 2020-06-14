package com.game.xchess.pojo.bo;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * 棋盘测试
 * @author zhangjian01
 *
 */
public class BoardTest {

	/**
	 * 测试乱序
	 */
	@Test
	public void shuffleTest()
	{
		Board board = new Board();
		board.shuffle();
		Set<Integer> pieceids = new HashSet<Integer>();
		for(int x=0;x<4;x++)
		{
			for(int y=0;y<8;y++)
			{
				int id = board.getCells()[x][y].getPiece().getId();
				if(!pieceids.contains(id))
				{
					pieceids.add(id);
				}
			}	
		}		
		if (pieceids.size() != 32)
		{
			Assert.assertFalse(true);
		}
		String v1 = board.toString();
		System.out.print(v1);
		board.shuffle();
		String v2 = board.toString();
		System.out.print("\n");
		System.out.print(v2);
		if (v1.equals(v2))
		{
			Assert.assertFalse(true);
		}
	}
}
