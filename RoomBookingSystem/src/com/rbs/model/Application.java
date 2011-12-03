package com.rbs.model;

import java.util.UUID;

public class Application {

	private String aid;
	private int roomID;
	private int roomNum;
	private String appName;
	private TimeInterval time;

	public Application() {
		super();
	}

	public Application(int roomID, int roomNum, String appName,
			TimeInterval time) {
		super();
		this.roomID = roomID;
		this.roomNum = roomNum;
		this.appName = appName;
		this.time = time;
	}

	public String getAid() {
		return aid;
	}

	public void setAid() {
		this.aid = UUID.randomUUID().toString();
	}

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public TimeInterval getTime() {
		return time;
	}

	public void setTime(TimeInterval time) {
		this.time = time;
	}

	/**
	 * 
	 * @return
	 */
	public int checkState() {
		throw new UnsupportedOperationException();
	}

}