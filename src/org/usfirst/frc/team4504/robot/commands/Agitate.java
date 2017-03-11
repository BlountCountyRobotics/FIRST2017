package org.usfirst.frc.team4504.robot.commands;

import org.usfirst.frc.team4504.robot.Robot;
import org.usfirst.frc.team4504.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class Agitate extends Command {

	//public static boolean direction = true;
    public Agitate() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.agitator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.agitatorDirection = !RobotMap.agitatorDirection;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(RobotMap.agitatorDirection)
    	{
    		Robot.agitator.run();
    	}else
    	{
    		Robot.agitator.reverse();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.agitator.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.agitator.stop();
    }
}
