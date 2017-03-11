package org.usfirst.frc.team4504.robot.commands.autonomous;

import org.usfirst.frc.team4504.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ApproachGears extends Command {

	
	boolean isFinal;
	boolean kill = false;
	static boolean pastKill = false;
	double curve;
    public ApproachGears(boolean isFinal) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//Wrequires(Robot.visionAngle);
    	//requires(Robot.gyroAngle);
    	requires(Robot.driveTrain);
    	//requires(Robot.gearDistance)
    	this.curve = Robot.driveTrain.gyro.getRealAngle();
    	this.isFinal = isFinal;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.stop();
    	
    	
    	/*Robot.visionAngle.setSetpoint(0.0);
    	Robot.gearDistance.setSetpoint(0.0);
    	Robot.visionAngle.enable();
    	Robot.gearDistance.enable();*/
    	//t.visionAngle.getFinalGyro());
    	//Robot.gyroAngle.isCurveOnly(true);
    	//Robot.gyroAngle.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.goForward(.3);
    	if(!Robot.visionTable.getBoolean("gearIsFound", false))
    	{
    		kill = true;
    	}
    	//Robot.driveTrain.setPIDOutputMagnitude(.2);
    	//Robot.driveTrain.pidExecute();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(!isFinal)
    	{
    		return Robot.gearDistance.onInitialTarget() || this.timeSinceInitialized() >= 1.5 || kill;
    	}else
    	{
    		if(pastKill)
    		{
    			pastKill = false;
    			return true;
    		}
    		return this.timeSinceInitialized() >= 2.0;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.gyroAngle.disable();
    	if(!isFinal && kill)
    	{
    		pastKill = true;
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
