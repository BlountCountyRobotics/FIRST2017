package org.usfirst.frc.team4504.robot;

public class RobotMap {
	
	//Motors
	public static final int frontLeftMotor = 4;
	public static final int rearLeftMotor = 3; 
	public static final int frontRightMotor = 2;  
	public static final int rearRightMotor = 0;  
	public static final int shooter = 7;
	public static final int agitator = 5;
	public static final int climber = 8;
	public static final int intake = 1;
	
	
	//Sensors
	public static final int gyro = 0;
	
	
	//Xbox controller
	public static final int backButton = 7;
	public static final int startButton = 8;
	public static final int leftBumper = 5;
	public static final int rightBumper =  6;
	public static final int leftClick = 9;
	public static final int rightClick = 10;
	public static final int aButton = 1;
	public static final int bButton = 2;
	public static final int xButton = 3;
	public static final int yButton = 4;
	
	
	//Driver station
	public static final int bottomLeft = 8;
	public static final int bottomMiddle = 11;
	public static final int middleLeft = 10;
	public static final int middleMiddle = 1;
	public static final int middleRight = 5;
	public static final int topLeft = 2;
	public static final int topRight = 4;
	public static final int leverForward = 6;
	public static final int leverBack = 7;
	

	//Constants
	public static final double approachTimeout = 1.5;
	public static final double inchesToGearLeft = 8.0;
	public static final double inchesToGearRight = 8.0;
	public static final double shooterDistance = 5.0;
	public static final double shooterPower = .625;
	public static final double agitatorPower = .4;
	public static final double intakePower = 1.0;
	
	//Changing variables
	public static double addedShootPower = 0.0; 
	public static boolean agitatorDirection = true;

}
