package org.usfirst.frc.team4504.robot.subsystems.pid;

import org.usfirst.frc.team4504.robot.Robot;
import org.usfirst.frc.team4504.robot.objects.BCRGyro;

/*
import org.usfirst.frc.team4504.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogGyro;
*/
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class GyroAngle extends PIDSubsystem {

	BCRGyro gyro = Robot.driveTrain.gyro;
    double finalGyro = 0.0;
	// Initialize your subsystem here
	
    public void setFinalGyro(double finalGyro)
    {
    	this.finalGyro = finalGyro;
    }

    public void setFinalGyroSetpoint()
    {
    	setSetpoint(finalGyro);
    }
    
	private boolean isCurveOnly = false;
	public void isCurveOnly(boolean isCurveOnly)
	{
		this.isCurveOnly = isCurveOnly;
		if(isCurveOnly)
		{
			setOutputRange(-1.0, 1.0);
			this.getPIDController().setPID(.1, .1, .1); // empirically set
		}else
		{
			setOutputRange(-.3,.3);
			this.getPIDController().setPID(0.1,0.1,0.1,0.1); //P,I,D,Period
		}
	}
	
	public GyroAngle() {
    	
		super(0.1,0.1,0.1,0.1); //P,I,D,Period
		setInputRange(-180.0, 180.0);
		setOutputRange(-0.2, 0.2);		
		isCurveOnly = false;
		//gyro.setSensitivity(RobotMap.gyroSensitivity);
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new StopGyroPID());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return gyro.getRealAngle();
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	if(isCurveOnly)
    	{
    		Robot.driveTrain.setPIDCurve(output);
    	}else
    	{
    		Robot.driveTrain.turn(output);
    	}
    }
    
    public boolean onTarget()
    {
    	double angle = getPosition();
    	double setpoint = getSetpoint();
    	return angle > setpoint - 3.0 && angle < setpoint + 3.0;
    }
    
    public boolean onTarget(double setpoint)
    {
    	double angle = getPosition();
    	return angle > setpoint - 3.0 && angle < setpoint + 3.0;
    }
}
