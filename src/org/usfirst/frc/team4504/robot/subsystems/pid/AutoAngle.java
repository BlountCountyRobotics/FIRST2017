package org.usfirst.frc.team4504.robot.subsystems.pid;


import org.usfirst.frc.team4504.robot.Robot;

import org.usfirst.frc.team4504.robot.objects.BCRGyro;

//import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class AutoAngle extends PIDSubsystem {
	BCRGyro gyro = Robot.driveTrain.gyro;
	

	
	public AutoAngle()
	{
		//super("PIDGearVision", Robot.TestingKp.getSelected(), 
		//		Robot.TestingKi.getSelected(), Robot.TestingKd.getSelected(), 
		//		0.0, Robot.TestingPeriod.getSelected());
		super(0.1,0.1,0.1,1.0); //P,I,D,Period

		this.setInputRange(-360.0, 360.0);
		this.setOutputRange(-.5, .5);
		this.setPercentTolerance(5.0);
		//gyro.reset();
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }

	@Override
	protected double returnPIDInput() {
		return gyro.getRealAngle();
		//return 0.0;
	}

	@Override
	protected void usePIDOutput(double output) {

		Robot.driveTrain.turn(output);
	}
	// when creating a class using this subsystem, remember 
	// to require DriveTrain and init w/ stopping drive train. 
	// initialize it by setting a setpoint
	
}

