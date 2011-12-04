package com.rbs.uiAccess;

import com.rbs.ctrl.UserCtrl;
import com.rbs.model.User;

public class LoginUI {
	/**
	 * @param userId
	 * @param pwd
	 * @return 1 for SUCCESS, 0 for FAIL cause password, -1 for FAIL cause no such User
	 */
	public int login(String userId,String pwd) {
        
        UserCtrl uc = new UserCtrl();
        try {
        	int res = uc.login(userId, pwd);
        	System.out.println("Login : "+res);
        	return res;
        } catch(Exception e) {
            e.printStackTrace();
        }
                
        return 0;
    }
}
