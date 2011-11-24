package com.rbc.model;
public class Room {

	private String roomNum;
	private int capacity;
	private String facilities;
	
	public Room() {
		super();
	}

	public Room(String roomNum, int capacity, String facilities) {
		super();
		this.roomNum = roomNum;
		this.capacity = capacity;
		this.facilities = facilities;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getFacilities() {
		return facilities;
	}

	public void setFacilities(String facilities) {
		this.facilities = facilities;
	}

}