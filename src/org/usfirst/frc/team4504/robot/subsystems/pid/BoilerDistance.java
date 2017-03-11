package org.usfirst.frc.team4504.robot.subsystems.pid;


import org.usfirst.frc.team4504.robot.Robot;
import org.usfirst.frc.team4504.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class BoilerDistance extends PIDSubsystem {

	double[] distance = {5.16, 6.00, 7.00, 8.00, 9.00, 10.0, 11.0, 12.0};
	double[] power    = {.540, .540, .585, .614, .620, .643, .655, .683};
	
    // Initialize your subsystem here
    public BoilerDistance() {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	super(.1,.1,.1,.1);
    	setSetpoint(RobotMap.shooterDistance);
    	setInputRange(0.0, 12.0);
    	setOutputRange(.5,.5);
    }

    public double getPower(double distance)
    {
    	if(distance == -1.0)
    	{
    		return 0.0;
    	}
    	int index = -1;
    	for(int x = 0; x < this.distance.length; x++)
    	{
    		if(this.distance[x] == distance)
    		{
    			index = x;
    		}
    	}
    	if(index == -1)
    	{
    		return 0.0;
    	}
    	return this.power[index];
    }
    
    public double getClosestDistance()
    {
    	double distance = Robot.visionTable.getNumber("boilerDistance", -1.0);
    	if(distance == -1.0)
    	{
    		return distance;
    	}
    	return getClosestDistance(distance);
    }
    
    public double getClosestDistance(double distance)
    {
    	double[] residuals = new double[this.distance.length];
    	for(int x = 0; x < this.distance.length; x++)
    	{
    		double indexDistance = this.distance[x];
    		residuals[x] = distance - indexDistance;
    	}
    	
    	int index = 0;
    	double smallestResidual = Math.abs(residuals[0]);
    	for(int x = 1; x < residuals.length; x++)
    	{
    		double residual = Math.abs(residuals[x]);
    		if(residual < smallestResidual)
    		{
    			smallestResidual = residual;
    			index = x;
    		}
    	}
    	return this.distance[index];
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	double input = Robot.visionTable.getNumber("boilerDistance", 0.0);
        return input;
    }

    public boolean onTarget()
    {
    	double position = Robot.visionTable.getNumber("boilerDistance", 0.0);
    	double setpoint = getSetpoint();
    	boolean isFound = Robot.visionTable.getBoolean("boilerDistance", false);
    	return (position <= setpoint + .2 && position >= setpoint - .2) && isFound;
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	Robot.driveTrain.goForward(output);
    }
}
