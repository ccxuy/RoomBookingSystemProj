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
		// create a couple of events...
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		User u1 = new User("xxxxxx","123","applicant","123","1@1.1");
		User u2 = new User("gdf1","123","applicant","123","1@1.1");
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

	}
}
