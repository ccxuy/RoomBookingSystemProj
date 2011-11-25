package com.rbs.test;

import java.util.Date;
import java.util.List;

import junit.framework.TestCase;
import org.hibernate.*;
import org.hibernate.cfg.*;

import com.rbs.model.User;

public class TestHibernate extends TestCase {
	private SessionFactory sessionFactory;
	
	@Override
	protected void setUp() throws Exception {
		System.out.println("START");
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
	public void testBasicUsage() {
        System.out.println("SET UP");
		System.out.println("BEGIN");
		// create a couple of events...
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		//Begin
		User u = new User("abc","name","123","applicant","123","1@1.1");
		
		session.save(u);
		
		session.getTransaction().commit();
		session.close();

	}
}
