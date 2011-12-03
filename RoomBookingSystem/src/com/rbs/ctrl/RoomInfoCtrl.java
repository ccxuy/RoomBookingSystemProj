package com.rbs.ctrl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.rbs.model.RoomInfo;
import com.rbs.model.User;

public class RoomInfoCtrl {
	private SessionFactory sessionFactory;
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
		throw new UnsupportedOperationException();
	}

}