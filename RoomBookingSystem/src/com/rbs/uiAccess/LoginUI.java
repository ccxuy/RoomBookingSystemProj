package com.rbs.uiAccess;

import com.rbs.ctrl.UserCtrl;
import com.rbs.model.User;

public class LoginUI {
	public String l = "yes";
	public String testPassVal(String s){
		return "success call: "+s;
	}
	public String login(String userId,String pwd) {
        String res = "";
        
        UserCtrl uc = new UserCtrl();
        User u = new User();
        try {
            res = uc.login(userId, pwd);
            if(res == null) return "用户名不存在";
        } catch(Exception e) {
            e.printStackTrace();
        }
                
        return pwd.equals(u.getPassword()) ? "登陆成功" : "密码错误";
    }
}
