package org.usfirst.frc.team4504.robot.subsystems.pid;

import org.usfirst.frc.team4504.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class GearDistance extends PIDSubsystem {

    // Initialize your subsystem here
    public GearDistance() {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	super(.2,.2,.2,.1);
    	setInputRange(0.0, 8.0);
    	setOutputRange(-.5, .5);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return Robot.visionTable.getNumber("gearDistance", 5.0);
    }
    
    public boolean onInitialTarget()
    {
    	double setpoint = 4.0;
    	double position = getPosition();
    	boolean isFound = Robot.visionTable.getBoolean("gearIsFound", false);
    	return position <= setpoint && isFound;
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	Robot.driveTrain.setPIDOutputMagnitude(output);
    }
}