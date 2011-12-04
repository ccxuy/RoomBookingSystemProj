package com.rbs.ctrl;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rbs.model.User;

public class UserCtrlTest {

	@Test
	public void testFindUserByName() {
		UserCtrl uc = new UserCtrl();
		User u = uc.findUserByName("name");
		System.out.println(u.getUid());
	}

	@Test
	public void testUserAll(){
		UserCtrl uc = new UserCtrl();
		uc.findUserAll();
	}
}
