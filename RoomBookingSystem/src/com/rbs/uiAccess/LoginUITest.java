/**
 * 
 */
package com.rbs.uiAccess;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Andrew
 *
 */
public class LoginUITest {

	/**
	 * Test method for {@link com.rbs.uiAccess.LoginUI#login(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testLogin() {
		LoginUI lui = new LoginUI();
		int res = lui.login("123", "app");
		System.out.println(res);
	}

}
