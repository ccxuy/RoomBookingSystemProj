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
	 * Test method for {@link com.rbs.uiAccess.UIAccess#login(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testLogin() {
		UIAccess lui = new UIAccess();
		int res = lui.login("123", "app");
		System.out.println(res);
	}

}
