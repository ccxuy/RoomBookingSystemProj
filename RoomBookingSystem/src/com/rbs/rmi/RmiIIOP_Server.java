package com.rbs.rmi;


import javax.naming.*;
import java.rmi.RemoteException;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
//import java.rmi.server.UnicastRemoteObject; 
import javax.rmi.PortableRemoteObject;

public class RmiIIOP_Server extends PortableRemoteObject implements RmiIIOP {
	public RmiIIOP_Server() throws RemoteException {
		super();
	}

	public String sayHello(String s1, String s2) {
		System.out.println("receive: "+s1);
		int res = Integer.parseInt(s1) + Integer.parseInt(s2);
		return String.valueOf(res);
	}

	
	public static void main(String args[]) {
		try {
			RmiIIOP_Server hello = new RmiIIOP_Server();

			// 创建一个名称上下文,并绑定远程对象
			Context initialNamingContext = new InitialContext();
			initialNamingContext.rebind("RmiIIOP_Server", hello);

			System.out.println("Hello Server: Ready and Waiting...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}