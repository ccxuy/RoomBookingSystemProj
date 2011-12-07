package com.rbs.ctrl;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.rbs.model.Application;

public class ApplicationCtrlTest {

	//@Test
	public void testFindApplicationByUid() {
		List<Application> a = new ApplicationCtrl().findApplicationByUid("abc");
		System.out.println(a.size());
	}

	//@Test
	public void testApplyRoom() throws ParseException{
		Application a = new Application();
		a.setAppID();
		a.setApplyerID("admin");
		a.setApplyTime(new Date());
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		DateFormat tf=new SimpleDateFormat("hh:mm");
		a.setRoomID("C201");
		a.setStatus(0);
		a.setDateBegin(df.parse("2007-12-24"));
		a.setDateEnd(df.parse("2007-12-25"));
		a.setDaysOfWeek("101");
		a.setTimeBegin(tf.parse("10:00"));
		a.setTimeEnd(tf.parse("11:00"));
		new ApplicationCtrl().applyRoom(a);
	}
	
	//@Test
	public void testAcceptApp() throws ParseException{
		Application a = new Application();
		a.setAppID();
		a.setApplyerID(new UserCtrl().findUserByName("admin").getUid());
		a.setApplyTime(new Date());
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		DateFormat tf=new SimpleDateFormat("hh:mm");
		a.setRoomID("C201");
		a.setStatus(0);
		a.setDateBegin(df.parse("2011-12-07"));
		a.setDateEnd(df.parse("2011-12-07"));
		a.setDaysOfWeek("101");
		a.setTimeBegin(tf.parse("11:00"));
		a.setTimeEnd(tf.parse("14:00"));
		new ApplicationCtrl().applyRoom(a);
		new ApplicationCtrl().acceptApp(a);
	}
}
