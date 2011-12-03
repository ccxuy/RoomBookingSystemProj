package com.rbs.ctrl;

import java.util.Calendar;
import java.util.LinkedList;
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
	 * �ж�application��ռ�õ�ʱ��ʮ����roominfo���ṩ��ʱ������
	 * @param app
	 * @param ri
	 * @return 1 -> �ɹ�, 0 -> ʧ��, -1 input err
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
		
		
		//�ж������Ƿ�Ϸ�: beginʱ������endʱ��
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
		
		//�ж�date����
		if(appBegin.after(riBegin)
				&&appEnd.before(riEnd)){
			System.out.println("applicion's date is in room info's date");
		}else{
			System.err.println("applicion's date is NOT in room info's date");
			flag = 0;
		}
		//�ж�����
		String appWeek = app.getDaysOfWeek();
		String riWeek = ri.getDaysOfWeek();
		int weekInerval = Math.max(appWeek.length(), riWeek.length());//��ֹ���
		for(int i=0;i<=weekInerval&&i<=7;i++){
			if(appWeek.charAt(i)=='1'){
				if(riWeek.charAt(i)!='1'){
					System.out.println("applicaion week not compatibale in week(index+1) "+i+1);
					flag = 0;
				}
			}
		}
		//�ж�time����
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
	 * @warn ע��! compareTo ��ȷ��million sec, ��������û�б������Լ�����Ĳ���, Ӧ�ÿ���ʹ��.
	 * @param app
	 * @param ri
	 * @return ���ָ�ʱ����application��...������ָܷ�, ����null
	 */
	public List<RoomInfo> splitApplicationTimeFromRoomInfo(Application app,RoomInfo ri){
		List<RoomInfo> res = new LinkedList<RoomInfo>();
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
		//���date
		//���begin�߽�
		if(appBegin.compareTo(riBegin)!=0){
			RoomInfo riA = ri.clone();
			riA.setDateEnd(app.getDateBegin());
			res.add(riA);
		}
		//���end�߽�
		if(appEnd.compareTo(riEnd)!=0){
			RoomInfo riC = ri.clone();
			riC.setDateBegin(app.getDateEnd());
			res.add(riC);
		}
		RoomInfo riB = ri.clone();
		riB.setDateBegin(app.getDateBegin());
		riB.setDateEnd(app.getDateEnd());
		
		//help:��Ҫ���Calendar��??
		appBegin.setTime(app.getTimeBegin());
		appEnd.setTime(app.getTimeEnd());
		riBegin.setTime(ri.getTimeBegin());
		riEnd.setTime(ri.getTimeEnd());
		//���time
		//���begin�߽�
		if(appBegin.compareTo(riBegin)!=0){
			RoomInfo riA = riB.clone();
			riA.setTimeEnd(app.getTimeBegin());
			res.add(riA);
		}
		//���end�߽�
		if(appEnd.compareTo(riEnd)!=0){
			RoomInfo riC = riB.clone();
			riC.setTimeBegin(app.getTimeEnd());
			res.add(riC);
		}
		return res;
	}
}