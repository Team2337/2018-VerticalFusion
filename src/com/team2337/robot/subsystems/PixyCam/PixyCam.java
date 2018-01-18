package com.team2337.robot.subsystems.PixyCam;

import java.util.LinkedList;
import java.util.List;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.I2C;

import com.team2337.robot.subsystems.PixyCam.PixyCmu5;
import com.team2337.robot.subsystems.PixyCam.PixyCmu5.PixyBlock;

/**
 *
 */
public class PixyCam extends Subsystem {

    public static final int PIXY_I2C_ADDR = 0xa8;
    //public static final int PIXY_I2C_SHOOTER_ADDR = 0xa9;
	
	PixyCmu5 pixyCamera;
	List<PixyCmu5.PixyBlock> pixyBlocks;
	boolean cameraPresent = false;
	int m_i2c_address;
	//String m_targetName = "";

	
	public PixyCam(int i2c_address) {
    	try
    	{
    		// Pixy Port Assignment
    		
    		/*
    		 * Instantiate a new Pixy object at the given address and schedule it to read data
    		 * at a 1 second period. This data will be accessible by calling pixyCamera.getCurrentframes()
    		 */
    		m_i2c_address = i2c_address;
            
    		pixyBlocks = new LinkedList<PixyCmu5.PixyBlock>();
    	    pixyCamera = new PixyCmu5(i2c_address, I2C.Port.kMXP, 0.25);
    	    cameraPresent = true;
	
    	    SmartDashboard.putString("PIXYCAM:", "Running normally");
        	
    	} catch (RuntimeException ex ) {
	        DriverStation.reportError("Error instantiating Pixy:  " + ex.getMessage(), true);
	        SmartDashboard.putString("PIXYCAM:", "NOT DETECTED");
	    	}	
		}

    public void initDefaultCommand() {
            setDefaultCommand(new displayPixyData());
        }
    
    public void detectTarget() {

    	double degFromCenterX = 0;
    	double degFromCenterY = 0;

    	// If an object is detected in the frame
		if(cameraPresent && !pixyCamera.getCurrentBlocks().isEmpty())
		{
			SmartDashboard.putBoolean("Pixy Target Detected", true);
			
			for (int i = 0; i < 2; i++) {
				try
				{
					// Calculate the number of degrees from the center the current frame 
					degFromCenterX = PixyCmu5.degreesXFromCenter(pixyCamera.getCurrentBlocks().get(i));
					degFromCenterY = PixyCmu5.degreesYFromCenter(pixyCamera.getCurrentBlocks().get(i));
					SmartDashboard.putString("Pixy AimX[" + Integer.toString(i) + "]", Double.toString(degFromCenterX) + " degrees from target");
					SmartDashboard.putString("Pixy AimY[" + Integer.toString(i) + "]", Double.toString(degFromCenterY) + " degrees from target");
				
				} catch  (RuntimeException ex ){
	
				}
			}

		} else {
			SmartDashboard.putBoolean("Pixy Target Detected", false);
			SmartDashboard.putString("Pixy AimX[0]", "No visible target");
			SmartDashboard.putString("Pixy AimY[0]", "No visible target");
			SmartDashboard.putString("Pixy AimX[1]", "No visible target");
			SmartDashboard.putString("Pixy AimY[1]", "No visible target");
		}

    }
 
    /******************************
     * Basic Pixy Tracking
     * centerX, centerY
     ******************************/
    
    
    /******************************
     * Power Up Tracking
     ******************************/    
    
    // Array of data calculated from values returned from Pixy
    // deltaCenterX, deltaCenterY, centerX, centerY, width, height
    double[] basicPixyData = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0};  

    
    /**
     * Calculate the current centerX, centerY, width & height
     * and store the values in a local array for pickup by helper routines below.
     * 
     * @throws NoTargetException
     */
    protected void calculateBasicPixyData() throws NoTargetException {

    	double centerXDelta = 0;
    	double centerYDelta = 0;
    	double centerX= 0;
    	double centerY = 0;
    	double width = 0;
    	double height = 0;
    	
    	// If an object is detected in the frame
		if(cameraPresent && !pixyCamera.getCurrentBlocks().isEmpty())
		{
			SmartDashboard.putBoolean("Target Detected", true);
			
			try
			{
	    		centerXDelta = PixyCmu5.xCenterDelta(pixyCamera.getCurrentBlocks().get(0));
	    		centerYDelta = PixyCmu5.yCenterDelta(pixyCamera.getCurrentBlocks().get(0));
	    		centerX 	 = pixyCamera.getCurrentBlocks().get(0).xCenter;
	    		centerY 	 = pixyCamera.getCurrentBlocks().get(0).yCenter;
	        	width        = pixyCamera.getCurrentBlocks().get(0).width;
	        	height       = pixyCamera.getCurrentBlocks().get(0).height;

	    		
	    		SmartDashboard.putString("PIXY:", Double.toString(centerXDelta) + " delta X");
				SmartDashboard.putString("PIXY:", Double.toString(centerYDelta) + " delta Y");
				SmartDashboard.putString("PIXY:", Double.toString(centerX) + " centerX");
				SmartDashboard.putString("PIXY:", Double.toString(centerY) + " centerY");
				SmartDashboard.putString("PIXY:", Double.toString(width) + " width");
				SmartDashboard.putString("PIXY:", Double.toString(height) + " height");
				
			} catch (RuntimeException ex ) { }

		} else {
			SmartDashboard.putBoolean("Target Detected", false);
			throw new NoTargetException("No Boiler Target Found");
		}

		basicPixyData[0] = centerXDelta;
		basicPixyData[1] = centerYDelta;
		basicPixyData[2] = centerX;
		basicPixyData[3] = centerY;
		basicPixyData[4] = height;
		basicPixyData[5] = width;
	
    }
    
    /**
     * Helper routine to return current data
     * 
     * @throws NoTargetException
     */
    public double[] getBasicPixyData() throws NoTargetException {
    	calculateBasicPixyData();
    	return basicPixyData;
    }
    
    ///********************************************************************************************************old*****
    
    // Height of the goal above the Pixy camera mount point on the robot
    static final double GOAL_HEIGHT = .5;
	
    // Angle of Pixy Cam mounting
    static final double PIXY_ANGLE = 0.174;
    static final double PIXY_ANGLE_DEGREES = 20.;
    
    
    // Array of data calculated from values returned from Pixy
    double[] distanceAndAngles = {0.0, 0.0, 0.0};  // distance, AngleX, AngleY
    
    /**
     * Calculate the current X & Y Angles and the distance from boiler
     * and store the values in a local array for pickup by helper routines below.
     * 
     * @throws NoTargetException
     */
    protected void calculateBoilerDistance() throws NoTargetException {

    	double tapePosx = 0;
    	double tapePosy = 0;

    	// If an object is detected in the frame
		if(cameraPresent && !pixyCamera.getCurrentBlocks().isEmpty())
		{
			SmartDashboard.putBoolean("Target Detected", true);
			
			try
			{
	    		tapePosy = PixyCmu5.degreesYFromCenter(pixyCamera.getCurrentBlocks().get(0));
	    		tapePosx = PixyCmu5.degreesXFromCenter(pixyCamera.getCurrentBlocks().get(0));
				SmartDashboard.putString("Pixy AimX[0]", Double.toString(tapePosx) + " degrees from target");
				SmartDashboard.putString("Pixy AimY[0]", Double.toString(tapePosy) + " degrees from target");
			} catch (RuntimeException ex ) { }

		} else {
			SmartDashboard.putBoolean("Target Detected", false);
			throw new NoTargetException("No Boiler Target Found");
		}

/*
 		distanceAndAngles[1] = tapePosx * (2 * 3.14159/360);
    	distanceAndAngles[2] = -tapePosy * (2 * 3.14159/360) + PIXY_ANGLE;
    	distanceAndAngles[0] = GOAL_HEIGHT/Math.tan(distanceAndAngles[2]);
 */
		distanceAndAngles[1] = tapePosx;
    	distanceAndAngles[2] = -tapePosy + PIXY_ANGLE_DEGREES;
    	distanceAndAngles[0] = GOAL_HEIGHT/Math.tan(distanceAndAngles[2] * (2 * 3.14159/360));
    	
    }
    
    /**
     * Helper routine to return current distance and angles
     * 
     * @throws NoTargetException
     */
    public double[] getBoilerLocationData() throws NoTargetException {
    	calculateBoilerDistance();
    	return distanceAndAngles;
    }
    
    /**
     * Helper routine to return current distance
     * 
     * @throws NoTargetException
     */
    public double getBoilerDistance() throws NoTargetException {
    	calculateBoilerDistance();
    	return distanceAndAngles[0];
    }

    
    double[] gearData = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}; //tapePosx, tapePosx2, height1, height2, width1, width2, area1, area2

    protected void calculateGearStuff() throws NoTargetException {
    	double tapePosx = 0;
    	double tapePosx2 = 0;
    	
    	double height1 = 0;
    	double height2 = 0;
    	double width1 = 0;
    	double width2 = 0;
    	double area1 = 0;
    	double area2 = 0;
    	
    	// If an object is detected in the frame
		if(cameraPresent && !pixyCamera.getCurrentBlocks().isEmpty())
		{
			SmartDashboard.putBoolean("Target Detected", true);
			
			try
			{
	    		tapePosx = PixyCmu5.degreesXFromCenter(pixyCamera.getCurrentBlocks().get(0));
	    		tapePosx2 = PixyCmu5.degreesYFromCenter(pixyCamera.getCurrentBlocks().get(1));
	    		height1 = pixyCamera.getCurrentBlocks().get(0).height;
	        	height2 = pixyCamera.getCurrentBlocks().get(1).height;
	        	width1 = pixyCamera.getCurrentBlocks().get(0).width;
	        	width2 = pixyCamera.getCurrentBlocks().get(1).width;
	        	area1 = height1*width1;
	        	area2 = height2*width2;
	    		
			} catch (RuntimeException ex ) { }

		} else {
			SmartDashboard.putBoolean("Target Detected", false);
			throw new NoTargetException("No Gear Target Found");
		}
		gearData[0] = tapePosx;
		gearData[1] = tapePosx2;
		gearData[2] = height1;
		gearData[3] = height2;
		gearData[4] = width1;
		gearData[5] = width2;
		gearData[6] = area1;
		gearData[7] = area2;
		
    }
    
    public double[] getPegLocationData() throws NoTargetException {
		calculateGearStuff();
    	return gearData;
    }
    
}

