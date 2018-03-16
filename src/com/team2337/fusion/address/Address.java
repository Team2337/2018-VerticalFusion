package com.team2337.fusion.address;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


public class Address {
	
	private static Address instance;
	private String macAddress;
	private NetworkTable table;
	public static synchronized Address getInstance() {
		if (instance == null) {
			instance = new Address();
		}
		return instance;
	}

	public String getMAC() {
		this.table = NetworkTableInstance.getDefault().getTable("NerdyDash");
		try {
	        Process pid = Runtime.getRuntime().exec("/home/admin/getAddr");
	        BufferedReader in = new BufferedReader(new InputStreamReader(pid.getInputStream()));
	        String s;
	        while ((s = in.readLine()) != null) {
	        	macAddress = s;
	        	System.out.println(s + " -");
	        	break;
	        }
	        System.out.println(macAddress);
	        in.close();
	        NetworkTableEntry data = table.getEntry("mac");		
			data.forceSetString(macAddress);
	        return macAddress;
		} catch (IOException e){
        	return null;
        }
    }
	public String getStoredMAC() {
		return macAddress;
	}
	
}