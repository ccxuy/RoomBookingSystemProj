package com.rbs.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;
import org.hibernate.*;
import org.hibernate.cfg.*;

import com.rbs.model.Application;
import com.rbs.model.RoomInfo;
import com.rbs.model.User;

public class TestHibernate extends TestCase {
	private SessionFactory sessionFactory;
	
	@Override
	protected void setUp() throws Exception {
		// A SessionFactory is set up once for an application
        sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();
	}

	@Override
	protected void tearDown() throws Exception {
		if ( sessionFactory != null ) {
			sessionFactory.close();
		}
	}
	
	/*public void testBasicUsage() {
		// create a couple of events...
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		User u1 = new User("admin","123123","ADMIN","123","1@1.1");
		User u2 = new User("qwe","123","applicant","123","1@1.1");
		System.out.println("SAVE");
		session.save((Object)u1);
		session.update(u1);
		session.getTransaction().commit();
		session.close();
		
		// now lets pull events from the database and list them
		session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery( "from User" ).list();
		for ( User u : (List<User>) result ) {
			System.out.println( "User (" + u.getName() + ") : " + u.getUid() );
		}
        session.getTransaction().commit();
        session.close();

	}*/
	/*public void testApplicationHbm() throws ParseException{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");  
		java.util.Date dateBegin= format.parse("2007-12-24");   
		java.util.Date dateEnd= format.parse("2007-12-25");
		DateFormat df=new SimpleDateFormat("EE hh:mm:ss");
		Calendar c  =  Calendar.getInstance();
		Date d = c.getTime();
		Application a = new Application();
		a.setAppID("c");
		a.setRoomID("C201");
		a.setApplyerID("abc");
		a.setApplyTime(dateBegin);
		a.setDateBegin(dateBegin);
		a.setDateEnd(dateEnd);
		a.setDaysOfWeek("1");
		a.setTimeBegin(d);
		a.setTimeEnd(d);
		a.setStatus(0);
		System.out.println("SAVE");
		session.save(a);
		//session.delete(a);
		session.getTransaction().commit();
		session.close();
	}*/
	public void testRoomInfoHbm() throws ParseException{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		DateFormat df=new SimpleDateFormat("hh:mm");
		RoomInfo a = new RoomInfo();
		a.setRoomInfoID();
		a.setRoomID("C201");
		a.setDateBegin(format.parse("2011-12-1"));
		a.setDateEnd(format.parse("2011-12-31"));
		a.setDaysOfWeek("101");
		a.setTimeBegin(df.parse("10:00"));
		a.setTimeEnd(df.parse("13:00"));
		System.out.println("SAVE");
		session.save(a);
		//session.delete(a);@test@test
		session.getTransaction().commit();
		session.close();
	}
}
