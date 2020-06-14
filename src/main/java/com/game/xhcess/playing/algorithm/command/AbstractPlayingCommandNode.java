package com.game.xhcess.playing.algorithm.command;

import com.game.xchess.errors.GameErrors;
import com.game.xchess.exception.GameException;
import com.game.xchess.pojo.bo.Cell;

/**
 * 走棋命令
 * @author zhangjian01
 *
 */
public abstract class AbstractPlayingCommandNode {

	private AbstractPlayingCommandNode nextNode;

	public AbstractPlayingCommandNode getNextNode() {
		return nextNode;
	}

	public void setNextNode(AbstractPlayingCommandNode nextNode) {
		this.nextNode = nextNode;
	}

	/**
	 * 命令执行
	 * 
	 * @param playerColor
	 * @param game
	 * @param fromX
	 * @param fromY
	 * @param toX
	 * @param toY
	 * @throws GameException
	 */
	public void exce(final Cell fromCell, final Cell toCell) throws GameException 
	{
		if (this.recognize(fromCell, toCell)) {
			this.handle(fromCell, toCell);
		} else {
			if (this.nextNode != null) {
				this.nextNode.exce(fromCell, toCell);
			} else {
				GameErrors.NotRecognizeCommand.throwGameException();
			}
		}
	}

	protected abstract boolean recognize(final Cell fromCell, final Cell toCell) throws GameException;

	protected abstract void handle(final Cell fromCell, final Cell toCell) throws GameException;
}