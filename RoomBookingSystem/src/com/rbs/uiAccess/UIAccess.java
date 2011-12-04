package com.rbs.uiAccess;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.rbs.ctrl.*;
import com.rbs.model.*;

public class UIAccess {
	UserCtrl uc = new UserCtrl();
	RoomInfoCtrl ric = new RoomInfoCtrl();
	ApplicationCtrl ac = new ApplicationCtrl();
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
	public int apply(String rid, String dateBegin, String dateEnd, String timeBegin, String timeEnd, String applyerID){
		SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat timeFormat = new java.text.SimpleDateFormat("hh:mm");
		try {
			Application app = new Application(rid, dateFormat.parse(dateBegin), dateFormat.parse(dateEnd), "", timeFormat.parse(timeBegin), timeFormat.parse(timeEnd), applyerID, 0, new Date());
			System.out.println(app.toTimeString());
			ac.applyRoom(app);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		return 1;
	}
	public List<User> displayUser(){
		return uc.findUserAll();
	}
	public int setUserManager(String name){
		User u = uc.findUserByName(name);
		return uc.setManager(u);
	}
	public int setUserApplicant(String name){
		User u = uc.findUserByName(name);
		return uc.setApplicant(u);
	}
	public int delUser(String name){
		User u = uc.findUserByName(name);
		return uc.deleteUser(u);
	}
	public List<Application> displayMyApplicaiton(String name){
		User u = uc.findUserByName(name);
		return ac.findApplicationByUid(u.getUid());
	}
	public String getUserPermissionByName(String name){
		return uc.findUserByName(name).getPermission();
	}
}
