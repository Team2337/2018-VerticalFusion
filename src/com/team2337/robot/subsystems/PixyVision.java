package com.team2337.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import java.util.ArrayList;
import java.util.HashMap;

import com.team2337.fusion.PixyCam.PixyException;
import com.team2337.fusion.PixyCam.PixyPacket;
import com.team2337.fusion.PixyCam.PixySPI;
import com.team2337.robot.Robot;
import com.team2337.robot.commands.pixy.pixy_Monitor;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class PixyVision extends Subsystem {
	
	public double centerXX = 190;
	public double pixyPixeltoDegree = 0; ///  NEED TO CALCULATE
	public double xx = 0;
	public double yy = 0;

	// These values are the default if you instantiate a PixySPI without arguments.
	// To create multiple PixySPI objects and thus use multiple Pixy cameras via SPI
	// Copy the items below, change variable names as needed and especially change
	// the SPI port used eg; Port.kOnboardCS[0-3] or Port.kMXP
	public PixySPI pixy1;
	Port port = Port.kOnboardCS0;
	String print;
	public HashMap<Integer, ArrayList<PixyPacket>> packets = new HashMap<Integer, ArrayList<PixyPacket>>();

	public PixyVision(){
		// Open a pipeline to a Pixy camera.
		pixy1 = new PixySPI(new SPI(port), packets, new PixyException(print));
	}


	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
	//	setDefaultCommand(new pixy_Monitor());
	}
	

	

	public void testPixy1(){
		
		
		int ret = -1;
		// Get the packets from the pixy.
		try {
			ret = pixy1.readPackets();
		} catch (PixyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 1; i <= PixySPI.PIXY_SIG_COUNT ; i++) {
			//SmartDashboard.putString("Pixy Vision: Signature: ", Integer.toString(i));

			//SmartDashboard.putNumber("Pixy Vision: packet: " + Integer.toString(i) + ": size: ", packets.get(i).size());
			
			// Loop through the packets for this signature.
			if(packets.get(1).size() > 0) {
			for(int j=0; j < packets.get(i).size(); j++) {
				packets.get(1).size();
				
				
				xx = packets.get(1).get(j).X;
				yy = packets.get(1).get(j).Y;
				/*
				SmartDashboard.putBoolean("in loop", true);
				
				SmartDashboard.putNumber("Pixy Vision: " + Integer.toString(i) + ": X: ", packets.get(i).get(j).X);
				SmartDashboard.putNumber("Pixy Vision: " + Integer.toString(i) + ": Y: ", packets.get(i).get(j).Y);
				SmartDashboard.putNumber("Pixy Vision: " + Integer.toString(i) + ": Width: ", packets.get(i).get(j).Width);
				SmartDashboard.putNumber("Pixy Vision: " + Integer.toString(i) + ": Height: ", packets.get(i).get(j).Height);
				*/
				
			}
		}
	}
	}
	
	public void periodic() {
		testPixy1();
	}

	
}

