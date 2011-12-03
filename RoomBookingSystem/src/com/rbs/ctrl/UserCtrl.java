package com.rbs.ctrl;

import org.hibernate.Query;
import org.hibernate.Session;

import com.rbs.dbAccess.hbmOperation;
import com.rbs.model.User;

public class UserCtrl extends RoomInfoCtrl {
	public final String FAIL = "FAIL";
	public final String SUCCESS = "SUCCESS";
	

	public final String R_NOTREG = "not registered";
	public final String ROLE_USER = "ROLE_USER";
	public final String ROLE_MGR = "ROLE_MGR";
	public final String ROLE_ADM = "ROLE_ADM";
	
	private Session session;
	
	public UserCtrl(){
		session = new hbmOperation().getSession();
	}
	/**
	 * 
	 * @return 
	 */
	public void logout() {
		throw new UnsupportedOperationException();
		
	}

	/**
	 * 
	 * @param name
	 * @param password
	 * @return 
	 */
	public String login(String name, String password) {
		User entity = null;
        Query q = session.createQuery("from User u where u.name = ? and u.password = ?");
        q.setString(0, name);
        q.setString(1, password);
        if(q.list().size() > 0)
        {
            return SUCCESS;
        }
        return FAIL;
	}

	/**
	 * 
	 * @param newUser
	 * @return 
	 */
	public int register(User newUser) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param u
	 * @return 
	 */
	public int changePermission(User u) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param u
	 * @return 
	 */
	public int addUser(User u) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param u
	 * @return 
	 */
	public int deleteUser(User u) {
		throw new UnsupportedOperationException();
	}

}