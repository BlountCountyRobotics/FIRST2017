package org.usfirst.frc.team4504.robot.commands;

import org.usfirst.frc.team4504.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ApproachBoiler extends Command {

	
	boolean auto = false;
	boolean leftSide = false;
	double distance = 0.0;
	boolean isFinished = false;
	boolean trigger = false;
    public ApproachBoiler(boolean auto, boolean leftSide, double distance) {
    	this.auto = auto;
    	this.leftSide = leftSide;
    	this.distance = distance;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	requires(Robot.boilerDistance);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.visionTable.getBoolean("boilerIsFound", false) && distance == -1.0)
    	{
    		distance = Robot.boilerDistance.getClosestDistance();
    	}
    	Robot.boilerDistance.setSetpoint(distance);
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
    	Robot.boilerDistance.enable(); 
    	Robot.boilerDistance.setSetpoint(distance);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(distance == -1.0)
    	{
    		return true;
    	}
        return Robot.boilerDistance.onTarget() || this.isFinished;
    }


    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.boilerDistance.disable();
    }
    
    protected void end()
    {
		
		Robot.boilerDistance.disable();
		if(isFinished)
		{
			return;
		}
		if(auto)
		{
			double angle;
			if(leftSide)
			{
				angle = -150.0;
			}else
			{	
				angle = 150.0;
			}
			if(!Robot.gyroAngle.onTarget(angle))
			{
				Robot.scheduler.add(new TurnToBoiler(auto, leftSide, distance));
				return;
			}
			if(!Robot.boilerStrafe.onTarget())
			{
				Robot.scheduler.add(new StrafeToBoiler(auto, leftSide, distance));
				return;
			}
			Robot.scheduler.add(new AutoShoot(distance));
		}
    }
}
