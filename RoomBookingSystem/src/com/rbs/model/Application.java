package com.rbs.model;

import java.util.Date;
import java.util.UUID;

public class Application {

	private String appID;
	private String roomID;
	private Date dateBegin;
	private Date dateEnd;
	private String daysOfWeek;
	private Date timeBegin;
	private Date timeEnd;
	private String applyerID;
	private int status = 0;
	private Date applyTime;

	public Application() {
		super();
	}

	public Application(String roomID, Date dateBegin, Date dateEnd,
			String daysOfWeek, Date timeBegin, Date timeEnd, String applyerID,
			int status, Date applyTime) {
		super();
		this.appID = UUID.randomUUID().toString();
		this.roomID = roomID;
		this.dateBegin = dateBegin;
		this.dateEnd = dateEnd;
		this.daysOfWeek = daysOfWeek;
		this.timeBegin = timeBegin;
		this.timeEnd = timeEnd;
		this.applyerID = applyerID;
		this.status = status;
		this.applyTime = applyTime;
	}

	public String getAppID() {
		return appID;
	}

	public void setAppID(String appID) {
		this.appID = appID;
	}
	
	public void setAppID() {
		this.appID = UUID.randomUUID().toString();
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

	public String getDaysOfWeek() {
		return daysOfWeek;
	}

	public void setDaysOfWeek(String daysOfWeek) {
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

	public String getApplyerID() {
		return applyerID;
	}

	public void setApplyerID(String applyerID) {
		this.applyerID = applyerID;
	}

	public int getStatus() {
		return status;
	}

	//tbd
	public void setStatus(int status) {
		this.status = status;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	
	public String toTimeString() {
		return "Application [dateBegin=" + dateBegin + ", dateEnd=" + dateEnd
				+ ", daysOfWeek=" + daysOfWeek + ", timeBegin=" + timeBegin
				+ ", timeEnd=" + timeEnd + "]";
	}

	@Override
	public Application clone() {
		Application app = new Application(roomID, dateBegin, dateEnd, daysOfWeek, timeBegin, timeEnd, applyerID, status, applyTime);
		return app;
	}


}