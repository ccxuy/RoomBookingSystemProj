package com.rbs.ctrl;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rbs.model.Application;
import com.rbs.model.RoomInfo;

/**
 * @author Andrew
 *
 */
public class ApplicationCtrl {
	private SessionFactory sessionFactory;
	
	public ApplicationCtrl() {
		super();
		sessionFactory = new Configuration()
        .configure() // configures settings from hibernate.cfg.xml
        .buildSessionFactory();
	}
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
		session.getTransaction().commit();
		session.close();
		return result;
	}
	public List<Application> findApplicationByUid(String uid) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "from Application A where A.applyerID='"+uid+"'";
		List<Application> result = (List<Application>)session.createQuery(hql).list();
		session.getTransaction().commit();
		session.close();
		System.out.println("findApplication of "+uid+"\n"+result.size());
		if(result.size()>=0){
		System.out.println("get first status "+result.get(0).getStatus());
		}
		return result;
	}
	public Application findApplicationByAid(String aid) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "from Application A where A.appID='"+aid+"'";
		List<Application> result = (List<Application>)session.createQuery(hql).list();
		session.getTransaction().commit();
		session.close();
		System.out.println("findApplication of "+aid+"\n    >find: "+result.size());
		if(result.size()==1){
			return result.get(0);
		}
		return null;
	}
	public List<Application> findApplicationAll() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "from Application";
		List result = session.createQuery(hql).list();
		session.getTransaction().commit();
		session.close();
		return result;
	}

	/**
	 * 
	 * @param app
	 * @return 1-> apply successfully
	 */
	public int applyRoom(Application app) {
		int flag = 0;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String rid=app.getRoomID();
		String hql="from RoomInfo RI where RI.roomID='"+rid+"'";
		List<RoomInfo> result = (List<RoomInfo>)session.createQuery(hql).list();
		for ( RoomInfo ri : (List<RoomInfo>) result ) {
			if(isAvailable(app, ri)==1){
				session.save(app);
				flag = 1;
			}
		}
		session.getTransaction().commit();
		session.close();
		System.out.println("applyRoom res: "+flag);
		return flag;
	}

	/**
	 * 
	 * @param roomInfoID
	 * @return 1-> accept successfully
	 */
	public int acceptApp(Application app) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		

		String rid=app.getRoomID();
		String hql="from RoomInfo RI where RI.roomID='"+rid+"'"; // find the RoomInfo according to App
		System.out.println("acceptApp  "+hql);
		List<RoomInfo> result = (List<RoomInfo>) session.createQuery(hql).list();
		if(result.size()==0){
			System.out.println("No relevant RoomInfo");
			session.close();
			return 0;
		}
		System.out.println("RoomInfo "+result.size());
		for ( RoomInfo ri : (List<RoomInfo>) result ) {
			if(isAvailable(app, ri)==1){       // find the RoomInfo which needs to be splited
			
				List<RoomInfo> resultOfSplit=splitApplicationTimeFromRoomInfo(app,ri);
				for (RoomInfo split : (List<RoomInfo>) resultOfSplit){     //save each splits
					session.save(split);
				}
				app.setStatus(1); 
				session.update(app);// 1->accept
				session.delete(ri);
				break; 
			}
		}
		session.getTransaction().commit();
		session.close();
		return 0;
	}

	/**
	 * 
	 * @param roomInfoID
	 * @return 
	 */
	public int rejectApp(Application app) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		app.setStatus(2); // 2-> reject
		session.update(app);
		return 1;
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
		if(appEnd.compareTo(appBegin)>=0){
			System.out.println("      >after");
		}else{
			System.err.println("      >NOT after");
			flag = -1;
		}
		//RoomInfo
		System.out.println(ri.toTimeString());
		System.out.println("dateEnd.after(dateBegin):");
		if(riEnd.compareTo(riBegin)>=0){
			System.out.println("       >after");
		}else{
			System.err.println("        >NOT after");
			flag = -1;
		}
		
		//�ж�date����
		if(appBegin.compareTo(riBegin)>=0
				&&appEnd.compareTo(riEnd)<=0){
			System.out.println("applicion's date is in room info's date");
		}else{
			System.err.println("applicion's date is NOT in room info's date");
			flag = 0;
		}
		//�ж�����
		String appWeek = app.getDaysOfWeek();
		String riWeek = ri.getDaysOfWeek();
		int weekInerval = Math.min(appWeek.length(), riWeek.length());//��ֹ���
		for(int i=0;i<weekInerval&&i<7;i++){
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
		
		if(appBegin.compareTo(riBegin)>=0
				&&appEnd.compareTo(riEnd)<=0){
			System.out.println("applicion's date is in room info's date");
		}else{
			System.err.println("applicion's date is NOT in room info's date");
			flag = 0;
		}
		System.out.println("isAvailable : "+flag);
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
		//���date
		//���begin�߽�
		if(appBegin.compareTo(riBegin)!=0){
			RoomInfo riA = ri.clone();
			System.out.println("app.getDateBegin()==null"+app.getDateBegin()==null);
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
		
		System.out.println("=========="+res.size());
		for(RoomInfo rio:res){
			System.out.println(rio.toTimeString());
		}
		return res;
	}
}