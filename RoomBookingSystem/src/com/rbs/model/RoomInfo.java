package com.rbs.model;

import java.util.List;

public class RoomInfo extends Room {

	private String roomInfoID;
	private List<TimeInterval> time;
	private int status;

	public RoomInfo() {
		super();
	}

	public RoomInfo(String roomInfoID, List<TimeInterval> time, int status) {
		super();
		this.roomInfoID = roomInfoID;
		this.time = time;
		this.status = status;
	}

	public String getRoomInfoID() {
		return roomInfoID;
	}

	public void setRoomInfoID(String roomInfoID) {
		this.roomInfoID = roomInfoID;
	}

	public List<TimeInterval> getTime() {
		return time;
	}

	public void setTime(List<TimeInterval> time) {
		this.time = time;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * 
	 * @return 
	 */
	public String checkAvailability() {
		throw new UnsupportedOperationException();
	}

}