package br.UFSC.GRIMA.thread;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import main.java.us.sosia.video.stream.agent.StreamServer;

public class MyIp
{  
	public static String getMyIp()
	{
		String myIp;
		InetAddress ia = null;  
    	try {  
    	    ia = InetAddress.getLocalHost();  
    	} catch (UnknownHostException e) 
    	{  
    	    e.printStackTrace();  
    	}  
    	myIp = ia.getHostAddress();
    	return myIp;
	}
    public static void main(String [] args) 
    {  
//    	Enumeration nis = null;  
//    	try {  
//    	    nis = NetworkInterface.getNetworkInterfaces();  
//    	} catch (SocketException e) {  
//    	    e.printStackTrace();  
//    	}    
//    	
//    	while (nis.hasMoreElements()) {    
//    	    NetworkInterface ni = (NetworkInterface) nis.nextElement();    
//    	    Enumeration ias = ni.getInetAddresses();    
//    	    while (ias.hasMoreElements()) {    
//    	        InetAddress ia = (InetAddress) ias.nextElement();    
//    	        System.out.println(ni.getName() + " -> ip: " + ia.getHostAddress() + " - hostname: " + ia.getHostName());     
//    	    }    
//    	}    
    	
    	          
//    	System.out.println("IP: " + ia.getHostAddress());  
//    	System.out.println("Nome: " + ia.getHostName());  
    }  
}  