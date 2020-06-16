package com.game.xchess.pojo.bo;

import java.util.HashMap;
import java.util.Map;

public class Area 
{
	private Map<Integer, Room> roomMap;
	
	public Area(){
		roomMap = new HashMap<Integer, Room>();
	}
	
	public Room getRoom(final int num)
	{
		if (!roomMap.containsKey(num))
		{
			Room room = new Room();
			roomMap.put(num, room);
		}
		return roomMap.get(num);
	}
}