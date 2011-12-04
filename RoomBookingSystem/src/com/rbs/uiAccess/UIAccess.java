package com.rbs.uiAccess;

import java.util.List;

import com.rbs.ctrl.*;
import com.rbs.model.*;

public class UIAccess {
	UserCtrl uc = new UserCtrl();
	RoomInfoCtrl ric = new RoomInfoCtrl();
	/**
	 * @param userId
	 * @param pwd
	 * @return 1 for SUCCESS, 0 for FAIL cause password, -1 for FAIL cause no such User
	 */
	public int login(String userId,String pwd) {
        
        try {
        	int res = uc.login(userId, pwd);
        	System.out.println("Login : "+res);
        	return res;
        } catch(Exception e) {
            e.printStackTrace();
        }
                
        return 0;
    }
	public int register(String uname, String password, String role, String email, String phone){
		User newUser = new User();
		newUser.setName(uname);
		newUser.setPassword(password);
		newUser.setPermission(role);
		newUser.setEmail(email);
		newUser.setPhone(phone);
		return uc.register(newUser);
	}
	public List<RoomInfo> query(){
		List<RoomInfo> res = ric.queryRoom();
		return res;
	}
}
