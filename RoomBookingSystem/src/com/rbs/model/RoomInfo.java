package com.rbs.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class RoomInfo extends Room {

	private String roomInfoID;
	private String roomID;
	private Date dateBegin;
	private Date dateEnd;
	private int daysOfWeek;
	private Date timeBegin;
	private Date timeEnd;

	public RoomInfo() {
		super();
	}

	public RoomInfo(String roomID, Date dateBegin, Date dateEnd,
			int daysOfWeek, Date timeBegin, Date timeEnd) {
		super();
		this.roomInfoID = UUID.randomUUID().toString();
		this.roomID = roomID;
		this.dateBegin = dateBegin;
		this.dateEnd = dateEnd;
		this.daysOfWeek = daysOfWeek;
		this.timeBegin = timeBegin;
		this.timeEnd = timeEnd;
	}

	public String getRoomInfoID() {
		return roomInfoID;
	}

	public void setRoomInfoID(String roomInfoID) {
		this.roomInfoID = roomInfoID;
	}

	public String getRoomID() {
		return roomID;
	}

	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}

	public Date getDateBegin() {
		return dateBegin;
	}

	public void setDateBegin(Date dateBegin) {
		this.dateBegin = dateBegin;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public int getDaysOfWeek() {
		return daysOfWeek;
	}

	public void setDaysOfWeek(int daysOfWeek) {
		this.daysOfWeek = daysOfWeek;
	}

	public Date getTimeBegin() {
		return timeBegin;
	}

	public void setTimeBegin(Date timeBegin) {
		this.timeBegin = timeBegin;
	}

	public Date getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}

	

}