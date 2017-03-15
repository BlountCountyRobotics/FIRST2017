package org.usfirst.frc.team4504.robot.commands.autonomous;

import org.usfirst.frc.team4504.robot.Robot;
//import org.usfirst.frc.team4504.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StrafeToGears extends Command {
	
	boolean withoutVision;
	boolean firstPIDRun;
	double setpoint;
	boolean kill = false;
	
    public StrafeToGears( double setpoint) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	requires(Robot.gearStrafe);
    	this.setpoint = setpoint;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.stop();
		Robot.gearStrafe.enable();
    	Robot.gearStrafe.setInchSetpoint(setpoint); // get inches from center of gear mechanism
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(!Robot.visionTable.getBoolean("gearIsFound", false))
    	{
    		kill = true;
    	}
    	Robot.gearStrafe.setPID();
   		Robot.gearStrafe.setInchSetpoint(setpoint);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        	return Robot.gearStrafe.onTarget() || kill;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.gearStrafe.disable();
    	Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.gearStrafe.disable();
    	Robot.driveTrain.stop();
    }
}
