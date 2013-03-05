package com.rbs.rmi;

import java.rmi.Remote; 
import java.rmi.RemoteException; 
public interface RmiIIOP extends Remote { 
	String sayHello(String s1,String s2) throws RemoteException;
} 