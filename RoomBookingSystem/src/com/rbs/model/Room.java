package com.rbs.model;
public class Room {

	private String roomID;
	private int capacity;
	private String facilities;
	
	public Room() {
		super();
	}

	public Room(String roomID, int capacity, String facilities) {
		super();
		this.roomID = roomID;
		this.capacity = capacity;
		this.facilities = facilities;
	}

	public String getRoomID() {
		return roomID;
	}

	public void setRoomID(String roomID) {
		this.roomID = roomID;
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