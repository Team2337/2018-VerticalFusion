package com.team2337.robot;
/**
 * Constants of the Team2337 Code
 * @category CONSTANTS
 * @author Team2337 - EngiNERDs
 */
public class Constants {
	public final static double TargetingCamera_UpdaterTimeout = 3;
	public final static double TargetingCamera_Deadband = 10;
	public final static double TargetingCamera_TurnSpeed = 0.4;

	public final static double TargetingCamera_p = .003;
	public final static double TargetingCamera_DegreeConversion = 0.04;

	public final static double TargetingCamera_GyroConversion = 3.5;

	public final static double TargetingCamera_Exposure = 1.0;
	public final static double TargetingCamera_Brightness = 0.0;

	public final static double TargetingCamera_VerticalOffset = 23;
	public final static double TargetingCamera_HorizontalOffset = 28;
	public final static double TargetingCamera_CameraWidth = 640;
	public final static double TargetingCamera_ObjectHeight = 82;
	public final static double TargetingCamera_WidthBetweenTarget = 8.4;
	public final static double TargetingCamera_AngleConstant = 3.4;

	public final static double TargetingCamera_CenterConstant = 83;

	public final static double TargetingCamera_DistanceInchesMax = 86;
	public final static double TargetingCamera_DistanceInchesMin = 131;

	public final static double TargetingCamera_AreaMax = 24;
	public final static double TargetingCamera_AreaMin = 7;

	public final static double TargetingCamera_PixelDegree = 0.375; // 0.375 degree(s) per 1 pixel //0.2875
	public final static double TargetingCamera_RevDegree = 0.0169; // 0.017845 rev(s) per 1 degree
	
	public final static double EncoderTicksPerInch = 698.57;
	
}