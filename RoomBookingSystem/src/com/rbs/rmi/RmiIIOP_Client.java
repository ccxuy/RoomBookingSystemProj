package com.rbs.rmi;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.rmi.RMISecurityManager;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import javax.rmi.PortableRemoteObject;
import javax.naming.*;

public class RmiIIOP_Client {
	public static void main(String[] args) { 
        try { 
                //��ȡһ������������ 
                Context ic = new InitialContext(); 

                //�õ�һ��Զ�̶�������� 
                Object objRef = ic.lookup("RmiIIOP_Server"); 

                //ǿ��ת��Ϊһ���������Hello�ӿڶ��� 
                RmiIIOP hello = (RmiIIOP) PortableRemoteObject.narrow(objRef, RmiIIOP.class); 
                
                while(true){
                System.out.println("Please input calculate numbers:"); 
                String in1 = "";
                String in2 = "";
                String output = "";
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                in1 = br.readLine();
                System.out.println(">Input 1: ");
                in2 = br.readLine();
                System.out.println(">Input 2: ");
                output = hello.sayHello(in1,in2);
                System.out.println(">Response: "+output);
                }
                
        } catch (Exception e) { 
                e.printStackTrace(); 
        } 
} 
}