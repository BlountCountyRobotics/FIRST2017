package org.usfirst.frc.team4504.robot.commands;

import org.usfirst.frc.team4504.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToBoiler extends Command {

	
	boolean auto;
	boolean leftSide;
	double distance;
	boolean isFinished = false;
	boolean trigger = false;
    public TurnToBoiler(boolean auto, boolean leftSide, double distance) {
        
    	this.auto = auto;
    	this.leftSide = leftSide;
    	this.distance = distance;
    	// Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gyroAngle);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(leftSide)
    	{
    		Robot.gyroAngle.setSetpoint(-150.0);
    	}else
    	{
    		Robot.gyroAngle.setSetpoint(150.0);
    	}
    	Robot.gyroAngle.enable();
    	if(Robot.visionTable.getBoolean("boilerIsFound", false) && distance == -1.0)
    	{
    		distance = Robot.boilerDistance.getClosestDistance();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	boolean isButtonPressed = Robot.oi.autoRightShooter.get();
    	if(!trigger && !isButtonPressed)
    	{
    		trigger = true;
    	}
    	
    	if(isButtonPressed && trigger)
    	{
    		this.isFinished = true;
    	}
    	if(leftSide)
    	{
    		Robot.gyroAngle.setSetpoint(-150.0);
    		//Robot.gyroAngle.setSetpoint(0.0);
    	}else
    	{
    		Robot.gyroAngle.setSetpoint(150.0);
    		//Robot.gyroAngle.setSetpoint(0.0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.gyroAngle.onTarget() || this.isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
		Robot.gyroAngle.disable();
		if(isFinished)
		{
			return;
		}
		if(auto)
		{
			if(!Robot.boilerStrafe.onTarget())
			{
				Robot.scheduler.add(new StrafeToBoiler(auto, leftSide, distance));
				return;
			}
			if(!Robot.boilerDistance.onTarget())
			{
				Robot.scheduler.add(new ApproachBoiler(auto, leftSide, distance));
				return;
			}
			Robot.scheduler.add(new AutoShoot(distance));
		}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.gyroAngle.disable();
    }
}
