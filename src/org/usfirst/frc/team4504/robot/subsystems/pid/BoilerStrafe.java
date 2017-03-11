package org.usfirst.frc.team4504.robot.subsystems.pid;

import org.usfirst.frc.team4504.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class BoilerStrafe extends PIDSubsystem {

    // Initialize your subsystem here
    public BoilerStrafe() {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	super(.1,.1,.1,.1);
    	setInputRange(-33.5, 33.5);
    	setOutputRange(-.3,.3);
    	setSetpoint(0.0);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        double angle = Robot.visionTable.getNumber("boilerAngle", 0.0);
    	return angle;
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	Robot.driveTrain.autoStrafe(output);
    }
    
    public boolean onTarget()
    {
    	double position = Robot.visionTable.getNumber("boilerAngle", 0.0);
    	double setpoint = getSetpoint();
    	boolean isFound = Robot.visionTable.getBoolean("boilerIsFound", false);
    	return (position >= setpoint -1.0 && position <= setpoint + 1.0) && isFound;
    }
}
