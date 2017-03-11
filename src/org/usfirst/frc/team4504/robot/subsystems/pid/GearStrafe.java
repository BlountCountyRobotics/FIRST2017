package org.usfirst.frc.team4504.robot.subsystems.pid;


import org.usfirst.frc.team4504.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class GearStrafe extends PIDSubsystem {
	
	
	public void setPID()
	{
		getPIDController().setPID(0.2,0.2,0.2); //P,I,D,Period
	}
	
	public GearStrafe()
	{
		
		//super("PIDGearVision", Robot.TestingKp.getSelected(), 
		//		Robot.TestingKi.getSelected(), Robot.TestingKd.getSelected(), 
		//		0.0, Robot.TestingPeriod.getSelected());
		//super(SmartDashboard.getNumber("kp",0.2),SmartDashboard.getNumber("ki",0.2),SmartDashboard.getNumber("ki",0.2),0.1); //P,I,D,Period
		super(0.1,0.1,0.1,0.1); //P,I,D,Period
		this.setInputRange(-90.0, 90.0);
		this.setOutputRange(-.4, .4);
		//this.setPercentTolerance(3.0);
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new StopStrafing());
    }

    public void setInchSetpoint(double inches)
    {
    	this.setSetpoint(inches/12.0);
    }
    
	@Override
	protected double returnPIDInput() {
		double input = (Robot.visionTable.getNumber("gearTranslational", 0.0));
		if(input > 5.0)
		{
			input = 5.0;
		}else if(input < -5.0)
		{
			input = -5.0;
		}
		//SmartDashboard.putNumber("gearTranslational", input);
		return input;
	}

	@Override
	protected void usePIDOutput(double outputMagnitude) {
		Robot.driveTrain.autoStrafe(-outputMagnitude);	
	}
	
	public boolean onTarget()
	{
    	double distance = Robot.visionTable.getNumber("gearTranslational", 0.0);
    	double setpoint = getSetpoint();
    	return distance < (setpoint + (1.0/12.0))  && distance > (setpoint - (1.0/12.0));
	}


	
	
}

