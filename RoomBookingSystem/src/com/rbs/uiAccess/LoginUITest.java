/**
 * 
 */
package com.rbs.uiAccess;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rbs.model.Application;

/**
 * @author Andrew
 *
 */
public class LoginUITest {

	/**
	 * Test method for {@link com.rbs.uiAccess.UIAccess#login(java.lang.String, java.lang.String)}.
	 *//*
	@Test
	public void testLogin() {
		UIAccess lui = new UIAccess();
		int res = lui.login("123", "app");
		System.out.println(res);
	}*/
	/*@Test
	public void testApply() {
		UIAccess lua = new UIAccess();
		int res = lua.apply("C2O7", "12/1/2011", "12/2/2011", "12:00", "13:00", "admin");
		System.out.println(res);
	}*/
	@Test
	public void testAccept() {
		UIAccess lua = new UIAccess();
		int res = lua.acceptApplication("fe2b1c7b-f247-4e61-b833-6d7ebc9d8494");
		System.out.println(res);
	}

}
