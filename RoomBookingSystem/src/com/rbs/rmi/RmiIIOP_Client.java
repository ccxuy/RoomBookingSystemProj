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
                //获取一个名称上下文 
                Context ic = new InitialContext(); 

                //得到一个远程对象的引用 
                Object objRef = ic.lookup("RmiIIOP_Server"); 

                //强制转换为一个更具体的Hello接口对象 
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