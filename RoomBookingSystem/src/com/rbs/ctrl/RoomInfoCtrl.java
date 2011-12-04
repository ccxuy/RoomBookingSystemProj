package com.rbs.ctrl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.rbs.model.Application;
import com.rbs.model.RoomInfo;
import com.rbs.model.User;

public class RoomInfoCtrl {
	private SessionFactory sessionFactory;
	
	public RoomInfoCtrl() {
		super();
		sessionFactory = new Configuration()
        .configure() // configures settings from hibernate.cfg.xml
        .buildSessionFactory();
	}

	/**
	 * 
	 * @param roomNum
	 * @return List<RoomInfo>
	 */
	public List<RoomInfo> queryRoom(String roomNum) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "select R from RoomInfo R where R.roomID='"+roomNum+"'";
		List result = session.createQuery(hql).list();
		/*
		for ( RoomInfo u : (List<RoomInfo>) result ) {
			System.out.println( "Roominfo (" + u.getDateBegin() + ") : " + u.getDateEnd() );
		}
		*/
		session.getTransaction().commit();
		session.close();
		return result;
	}

	/**
	 * @return all room information
	 */
	public List<RoomInfo> queryRoom() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "from RoomInfo";
		List result = session.createQuery(hql).list();
		/*
		for ( RoomInfo u : (List<RoomInfo>) result ) {
			System.out.println( "Roominfo (" + u.getDateBegin() + ") : " + u.getDateEnd() );
		}
		*/
		session.getTransaction().commit();
		session.close();
		return result;
	}

	/**
	 * 
	 * @param oldRoom
	 * @return 
	 */
	public int maintainRoomInfo(RoomInfo newRoom) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(newRoom);
		
		session.getTransaction().commit();
		session.close();
		return 1;
	}

	/**
	 * 
	 * @param roomNumber
	 * @return 
	 */
	public String viewRatio(String roomNumber) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String hql="select a.* from Application a where a.roomID='"+roomNumber+"'"; // Find Applications by roomNumber
		List result = session.createQuery(hql).list();
		session.getTransaction().commit();
		session.close();	
		long hoursOfApp;
		long sumOfAppHours=0;
		for(Application a:(List<Application>)result){ //add all used hours
			    hoursOfApp=getHoursOfApp(a); 
			    sumOfAppHours=sumOfAppHours+hoursOfApp;
		}
		
		session = sessionFactory.openSession();
		session.beginTransaction();
		hql="select ri.* from RoomInfo ri where ri.roomID='"+roomNumber+"'"; // Find RoomInfo by roomNumber
		result = session.createQuery(hql).list();
		session.getTransaction().commit();
		session.close();
		long hoursOfRoomInfo;
		long sumOfRoomInfo=0;
		for(RoomInfo ri:(List<RoomInfo>) result){
				hoursOfRoomInfo=getHoursOfRoomInfo(ri);
				sumOfRoomInfo=sumOfRoomInfo+hoursOfRoomInfo;
		}
		
		long ratio=sumOfAppHours/(sumOfRoomInfo+sumOfAppHours);
		String resultOfRatio=Long.toString(ratio);
		return resultOfRatio;
		
	}
	
	public long getHoursOfApp(Application app){
		//SimpleDateFormat df = new SimpleDateFormat("hh-mm-ss");
		Calendar appBegin = Calendar.getInstance();
		Calendar appEnd = Calendar.getInstance(); 
		appBegin.setTime(app.getTimeBegin());
		appEnd.setTime(app.getTimeEnd());
		//String appBeginStr=df.format(appEnd.getTime()); 
		//String appEndStr=df.format(appEnd.getTime());
		long hoursOfApp=(long)(appEnd.getTimeInMillis()-appBegin.getTimeInMillis())/(60*60*1000); 
		
		return hoursOfApp;
	}
	
	public long getHoursOfRoomInfo(RoomInfo ri){
		Calendar riBegin = Calendar.getInstance();
		Calendar riEnd = Calendar.getInstance();
		// date
		riBegin.setTime(ri.getDateBegin());
		riEnd.setTime(ri.getDateEnd());
		long daysBetween=(long)(riBegin.getTimeInMillis()-riEnd.getTimeInMillis())/(24*60*60*1000);
		// time
		riBegin.setTime(ri.getTimeBegin());
		riEnd.setTime(ri.getTimeEnd());
		long hoursBetween=(long)(riBegin.getTimeInMillis()-riEnd.getTimeInMillis())/(60*60*1000);
		
		long hoursOfRoomInfo=daysBetween*hoursBetween;
		return hoursOfRoomInfo;
	}


	

}