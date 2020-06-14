package com.game.xchess.pojo.bo;

import java.util.Random;

/**
 * 棋盘
 * 
 * @author zhangjian01
 *
 */
public class Board {
	public static Random RANDOM = new Random();

	private final int[] array = new int[32];

	private final Cell[][] cells = new Cell[4][8];

	public Cell[][] getCells() {
		return cells;
	}

	public Board() {
		initCells();
	}

	/**
	 * 初始化棋盘的格子
	 */
	private void initCells() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 8; j++) {
				cells[i][j] = new Cell(i, j);
			}
		}
	}

	/**
	 * 乱序棋子
	 */
	public void shuffle() {
		initArray();
		int length = 32;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 8; j++) {
				int ranInt = RANDOM.nextInt(length);
				cells[i][j].setIsFrontUp(false);
				cells[i][j].setPiece(StandardPieces.getPieceArray()[array[ranInt]]);
				array[ranInt] = array[length - 1];
				length--;
			}
		}
	}

	private void initArray() {
		for (int i = 0; i < 32; i++) {
			array[i] = i;
		}
	}

	@Override
	public String toString() {
		return "cells=[" + cellsToString() + "]";
	}

	private String cellsToString() {
		String v = "";
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 8; j++) {
				if (i != 0 || j != 0) {
					v += ",";
				}
				v += cells[i][j].getPiece().getId();
			}
		}
		return v;
	}
}