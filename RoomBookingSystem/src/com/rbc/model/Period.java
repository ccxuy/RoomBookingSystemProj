package com.rbc.model;

import java.util.Date;

public class Period {

	private Date dateBegin;
	private Date dateEnd;
	private int week;
	
	public Period(Date dateBegin, Date dateEnd, int week) {
		super();
		this.dateBegin = dateBegin;
		this.dateEnd = dateEnd;
		this.week = week;
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
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}

}