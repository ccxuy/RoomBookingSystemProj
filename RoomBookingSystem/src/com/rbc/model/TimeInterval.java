package com.rbc.model;

import java.util.Date;

public class TimeInterval {

	private Date timeBegin;
	private Date timeEnd;
	private int period;
	public TimeInterval() {
		super();
	}
	public TimeInterval(Date timeBegin, Date timeEnd, int period) {
		super();
		this.timeBegin = timeBegin;
		this.timeEnd = timeEnd;
		this.period = period;
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
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}

}