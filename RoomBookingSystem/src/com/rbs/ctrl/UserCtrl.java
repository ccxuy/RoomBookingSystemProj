package com.rbs.ctrl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.SessionFactory;

import com.rbs.model.User;
import org.hibernate.*;
import org.hibernate.cfg.*;

import com.rbs.model.*;
public class UserCtrl extends RoomInfoCtrl {
	
	public final String R_REGISTER = "NOT REGISTERED"; 
	public final String R_APPLICANT = "APPLICANT"; 
	public final String R_MANAGER = "MANAGER"; 
	public final String R_ADMIN = "ADMIN"; 
	
	private SessionFactory sessionFactory;
	/**
	 * 
	 * @return 
	 */
	public void logout() {
		
	}

	/**
	 * 
	 * @param name
	 * @param password  OK
	 * @return 
	 */
	public int login(String name, String password) {

		Session session = sessionFactory.openSession();
		Transaction tran=session.beginTransaction();
		String hql = "select u.* from User u where u.name='"+name+"' and u.password='"+password+"'";
		Query query=session.createSQLQuery(hql);
		List<User> list=query.list();
		
		if(list.size()==1){
			tran.commit();
        	session.close();
			return 1; //successful to login
		}
		else{
			tran.commit();
        	session.close();
        	return 0; // fail to login
		}
	}
	public int login(User u){
		return login(u.getName(),u.getPassword());
	}
	
	/**
	 * 
	 * @param newUser   OK
	 * @return 
	 */
	public int register(User newUser) {
		Session session = sessionFactory.openSession();
		Transaction tran=session.beginTransaction();
		String hql = "select u.* from User u where u.name='"+newUser.getName()+"'";
		Query query=session.createSQLQuery(hql);
		List<User> list=query.list();
		if(list.size()!=0){
			//System.out.print("User is already existed. ");
			tran.commit();
	        session.close();
	        return 0;
		}
		else{
		    session = sessionFactory.openSession();
			tran=session.beginTransaction();
	        //System.out.print("Register Successful!");
			session.save(newUser);
			tran.commit();
	        session.close();
	        return 1;
		}
	}

	/**
	 * 
	 * @param OK
	 * @return 
	 */
	public int setManager(User u) {
		
		
		
		//Query query=session.createSQLQuery(queryState);
	    //List<User> list=query.list();
		//Iterator it =list.iterator();
		//while(it.hasNext()){
		//Object[] result=(Object[])it.next();
		//Sytem.out.println(result[3]);	
		
		Session session = sessionFactory.openSession();
		Transaction tran=session.beginTransaction();
		u.setPermission(R_MANAGER);
		session.save(u);
		//String hql = "update User as u1 set permission='"+R_MANAGER+"' where u1.name='"+u.getName()+"'";		
		//Query query=session.createQuery(hql);
		//int ret=query.executeUpdate();
		//System.out.println("Upload Records->"+ret);
		tran.commit();
        session.close();
        return 1;
	}
	
	/**
	 * 
	 * @param OK
	 * @return 
	 */
	
     public int setApplicant(User u) {
		
		Session session = sessionFactory.openSession();
		Transaction tran=session.beginTransaction();
		//String hql = "update User as u1 set permission='0' where u1.name='"+u.getName()+"'";		
		//Query query=session.createQuery(hql);
		//int ret=query.executeUpdate();
		//System.out.println("Upload Records->"+ret);
		u.setPermission(R_APPLICANT);
		session.save(u);
		tran.commit();
        session.close();
        return 1;
	}

	

	/**
	 * 
	 * @param u  OK
	 * @return 
	 */
	public int deleteUser(User u) {
		Session session = sessionFactory.openSession();
		Transaction tran=session.beginTransaction();
		String hql = "delete from User u1 where u1.name='"+u.getName()+"'";
		int number=session.createQuery(hql).executeUpdate();
		//session.delete(u);
		// number=1, successful 0,fail
		tran.commit();
		session.close();
		return number;		
	}

}