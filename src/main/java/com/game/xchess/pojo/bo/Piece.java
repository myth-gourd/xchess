package com.game.xchess.pojo.bo;

/**
 * 棋子
 * @author zhangjian01
 *
 */
public final class Piece 
{
	private final int id;
	
	private final String name;
	
	private final int color;
	
	private final int value;

	public int getId(){
		return id;
	}
	
	public String getName() {
		return name;
	}

	public int getColor() {
		return color;
	}

	public int getValue() {
		return value;
	}
	
	public Piece(final int id, final String name, int color, int value){
		this.id = id;
		this.name = name;
		this.color = color;
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Piece other = (Piece) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Piece [id=" + id + ", name=" + name + ", color=" + color + ", value=" + value + "]";
	}
}