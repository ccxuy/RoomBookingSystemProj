package com.rbs.dbAccess;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class hbmOperation {
	public final int FAIL = 0;
	public final int SUCCESS = 1;
	
	private SessionFactory sessionFactory;
	
	public hbmOperation() {
		super();
		// A SessionFactory is set up once for an application
        sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();
	}
	
	public Session getSession(){
		return sessionFactory.openSession();
	}
	
	protected void close() throws Exception {
		if ( sessionFactory != null ) {
			sessionFactory.close();
		}
	}

	public int save(Object o){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
			session.save(o);
		session.getTransaction().commit();
		session.close();
		return this.SUCCESS;
	}
	
	public int save(List l){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		for(Object o:l){
			session.save(o);
		}
		session.getTransaction().commit();
		session.close();
		return this.SUCCESS;
	}
	
	public int update(List l){
		return this.FAIL;
	}
	public int update(Object o){
		return this.FAIL;
	}
	
	public int delete(Object o){
		return this.FAIL;
	}

	public List<Object> query(Object o){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.getTransaction().commit();
		session.close();
		return null;
	}
	
	public List<Object> query(String hql){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.getTransaction().commit();
		session.close();
		return null;
	}
}
