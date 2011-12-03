package com.rbs.ctrl;

import java.util.Calendar;
import java.util.List;

import com.rbs.model.Application;
import com.rbs.model.RoomInfo;

/**
 * @author Andrew
 *
 */
public class ApplicationCtrl {

	/**
	 * 
	 * @param aid
	 * @return 
	 */
	public List<Application> checkHistoryOfApp(String aid) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param app
	 * @return 
	 */
	public int applyRoom(Application app) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param roomInfoID
	 * @return 
	 */
	public int acceptApp(String roomInfoID) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param roomInfoID
	 * @return 
	 */
	public int rejectApp(String roomInfoID) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public List<Application> queryApplication() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * 判断application所占用的时间十分在roominfo所提供的时间里面
	 * @param app
	 * @param ri
	 * @return 1 -> 成功, 0 -> 失败, -1 input err
	 */
	public int isAvailable(Application app, RoomInfo ri){
		int flag = 1;
		Calendar appBegin = Calendar.getInstance();
		Calendar appEnd = Calendar.getInstance();
		Calendar riBegin = Calendar.getInstance();
		Calendar riEnd = Calendar.getInstance();
		

		appBegin.setTime(app.getDateBegin());
		appEnd.setTime(app.getDateEnd());

		riBegin.setTime(ri.getDateBegin());
		riEnd.setTime(ri.getDateEnd());
		
		
		//判断输入是否合法: begin时间先于end时间
		//Application
		System.out.println(app.toTimeString());
		System.out.println("dateEnd.after(dateBegin):");
		if(appEnd.after(appBegin)){
			System.out.println("after");
		}else{
			System.err.println(" NOT after");
			flag = -1;
		}
		//RoomInfo
		System.out.println(ri.toTimeString());
		System.out.println("dateEnd.after(dateBegin):");
		if(riEnd.after(riBegin)){
			System.out.println("after");
		}else{
			System.err.println(" NOT after");
			flag = -1;
		}
		
		//判断date区间
		if(appBegin.after(riBegin)
				&&appEnd.before(riEnd)){
			System.out.println("applicion's date is in room info's date");
		}else{
			System.err.println("applicion's date is NOT in room info's date");
			flag = 0;
		}
		//判断星期
		String appWeek = app.getDaysOfWeek();
		String riWeek = ri.getDaysOfWeek();
		int weekInerval = Math.max(appWeek.length(), riWeek.length());//防止溢出
		for(int i=0;i<=weekInerval&&i<=7;i++){
			if(appWeek.charAt(i)=='1'){
				if(riWeek.charAt(i)!='1'){
					System.out.println("applicaion week not compatibale in week(index+1) "+i+1);
					flag = 0;
				}
			}
		}
		//判断time区间
		appBegin.setTime(app.getTimeBegin());
		appEnd.setTime(app.getTimeEnd());
		riBegin.setTime(ri.getTimeBegin());
		riEnd.setTime(ri.getTimeEnd());
		
		if(appBegin.after(riBegin)
				&&appEnd.before(riEnd)){
			System.out.println("applicion's date is in room info's date");
		}else{
			System.err.println("applicion's date is NOT in room info's date");
			flag = 0;
		}
		
		return flag;
	}
	
	/**
	 * @warn 注意! compareTo 精确到million sec, 但是由于没有保留秒以及后面的参数, 应该可以使用.
	 * @param app
	 * @param ri
	 * @return 被分割时间后的application们...如果不能分割, 返回null
	 */
	public List<RoomInfo> splitApplicationTimeFromRoomInfo(Application app,RoomInfo ri){
		List<RoomInfo> res = null;
		//check err
		if(isAvailable(app, ri)!=1){
			return null;
		}
		
		Calendar appBegin = Calendar.getInstance();
		Calendar appEnd = Calendar.getInstance();
		Calendar riBegin = Calendar.getInstance();
		Calendar riEnd = Calendar.getInstance();
		
		appBegin.setTime(app.getDateBegin());
		appEnd.setTime(app.getDateEnd());
		riBegin.setTime(ri.getDateBegin());
		riEnd.setTime(ri.getDateEnd());
		//检查date
		//检查begin边界
		if(appBegin.compareTo(riBegin)!=0){
			RoomInfo riA = ri.clone();
			riA.setDateEnd(app.getDateBegin());
			res.add(riA);
		}
		//检查end边界
		if(appEnd.compareTo(riEnd)!=0){
			RoomInfo riC = ri.clone();
			riC.setDateEnd(app.getDateBegin());
			res.add(riC);
		}
		
		//检查time
		//检查begin边界
		//检查end边界
		return null;
	}
}