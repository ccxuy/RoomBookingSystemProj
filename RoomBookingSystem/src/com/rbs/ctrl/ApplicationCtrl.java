package com.rbs.ctrl;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.rbs.model.Application;
import com.rbs.model.RoomInfo;

/**
 * @author Andrew
 *
 */
public class ApplicationCtrl {
	private SessionFactory sessionFactory;
	/**
	 * 
	 * @param aid
	 * @return 
	 */
	public List<Application> checkHistoryOfApp(String aid) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "select A from Application A where A.appID='"+aid+"'";
		List result = session.createQuery(hql).list();
		return result;
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
		if(appEnd.compareTo(appBegin)>=0){
			System.out.println("->after");
		}else{
			System.err.println(" NOT after");
			flag = -1;
		}
		//RoomInfo
		System.out.println(ri.toTimeString());
		System.out.println("dateEnd.after(dateBegin):");
		if(riEnd.compareTo(riBegin)>=0){
			System.out.println("after");
		}else{
			System.err.println(" NOT after");
			flag = -1;
		}
		
		//判断date区间
		if(appBegin.compareTo(riBegin)>=0
				&&appEnd.compareTo(riEnd)<=0){
			System.out.println("applicion's date is in room info's date");
		}else{
			System.err.println("applicion's date is NOT in room info's date");
			flag = 0;
		}
		//判断星期
		String appWeek = app.getDaysOfWeek();
		String riWeek = ri.getDaysOfWeek();
		int weekInerval = Math.min(appWeek.length(), riWeek.length());//防止溢出
		for(int i=0;i<weekInerval&&i<7;i++){
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
		
		if(appBegin.compareTo(riBegin)>=0
				&&appEnd.compareTo(riEnd)<=0){
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
		List<RoomInfo> res = new LinkedList<RoomInfo>();
		//check err
		if(isAvailable(app, ri)!=1){
			System.err.println("NOT VALID INPUT");
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
			riC.setDateBegin(app.getDateEnd());
			res.add(riC);
		}
		RoomInfo riB = ri.clone();
		riB.setDateBegin(app.getDateBegin());
		riB.setDateEnd(app.getDateEnd());
		
		//help:需要清空Calendar吗??
		appBegin.setTime(app.getTimeBegin());
		appEnd.setTime(app.getTimeEnd());
		riBegin.setTime(ri.getTimeBegin());
		riEnd.setTime(ri.getTimeEnd());
		//检查time
		//检查begin边界
		if(appBegin.compareTo(riBegin)!=0){
			RoomInfo riA = riB.clone();
			riA.setTimeEnd(app.getTimeBegin());
			res.add(riA);
		}
		//检查end边界
		if(appEnd.compareTo(riEnd)!=0){
			RoomInfo riC = riB.clone();
			riC.setTimeBegin(app.getTimeEnd());
			res.add(riC);
		}
		
		System.out.println("=========="+res.size());
		for(RoomInfo rio:res){
			System.out.println(rio.toTimeString());
		}
		return res;
	}
}