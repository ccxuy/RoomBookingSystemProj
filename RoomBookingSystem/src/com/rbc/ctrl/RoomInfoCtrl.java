package com.rbc.ctrl;

import java.util.Date;

import com.rbc.model.RoomInfo;

public class RoomInfoCtrl {

	/**
	 * 
	 * @param roomNum
	 * @return 
	 */
	public RoomInfo queryRoom(String roomNum) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param roomInfoID
	 * @param dateBegin
	 * @param dateEnd
	 * @param week
	 * @param timeBegin
	 * @param timeEnd
	 * @param capacity
	 * @param facilities
	 * @return 
	 */
	public int uploadRoomInfo(String roomInfoID, Date dateBegin, Date dateEnd, String week, Date timeBegin, Date timeEnd, int capacity, String facilities) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param oldRoom
	 * @param newRoom
	 * @return 
	 */
	public int updateRoomInfo(RoomInfo oldRoom, RoomInfo newRoom) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param roomNumber
	 * @return 
	 */
	public String viewRatio(String roomNumber) {
		throw new UnsupportedOperationException();
	}

}