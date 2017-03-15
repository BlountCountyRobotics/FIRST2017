package org.usfirst.frc.team4504.robot.commands.autonomous;

import org.usfirst.frc.team4504.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToGears extends Command {

	private boolean isLeft = false;
	private boolean isStraight = false;
	
    public TurnToGears(boolean isLeft) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	requires(Robot.gyroAngle);
    	this.isLeft = isLeft;
    }
    public TurnToGears() 
    {
    	requires(Robot.driveTrain);
    	requires(Robot.gyroAngle);
    	this.isStraight = true;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.stop();
    	Robot.gyroAngle.isCurveOnly(false);
    	Robot.gyroAngle.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(isStraight == true)
    	{
    		Robot.gyroAngle.setSetpoint(0.0);
    	}else if(isLeft == false)
    	{
    		Robot.gyroAngle.setSetpoint(-60.0);
    	}else
    	{
    		Robot.gyroAngle.setSetpoint(60.0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.gyroAngle.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.gyroAngle.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.gyroAngle.disable();
    }
}
