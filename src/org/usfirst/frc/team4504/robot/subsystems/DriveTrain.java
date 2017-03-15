package org.usfirst.frc.team4504.robot.subsystems;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team4504.robot.RobotMap;
import org.usfirst.frc.team4504.robot.commands.DriveWithMecanum;
import org.usfirst.frc.team4504.robot.objects.BCRGyro;

import com.ctre.CANTalon;


/**
 *
 */
public class DriveTrain extends Subsystem {
	private CANTalon frontLeftMotor = new CANTalon(RobotMap.frontLeftMotor);
	private CANTalon rearLeftMotor = new CANTalon(RobotMap.rearLeftMotor);
	private CANTalon frontRightMotor = new CANTalon(RobotMap.frontRightMotor);
	private CANTalon rearRightMotor = new CANTalon(RobotMap.rearRightMotor);
	public BCRGyro gyro = new BCRGyro(SPI.Port.kOnboardCS0);
	private RobotDrive robotDrive;
	int x = 0;
	private double curve = 0.0;
	private double outputMagnitude = 0.0;
	public boolean usingGyro = true;
	
	
	public DriveTrain()
	{
		robotDrive = new RobotDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
		robotDrive.setSafetyEnabled(false);
		this.invert(true);
	}
	public void inputMecanum(XboxController joystick, double factor, boolean usingGyro)
	{
		double x = factor * Math.pow(joystick.getRawAxis(0),3);
		double y = factor * Math.pow(joystick.getRawAxis(1),3);
		double z = factor * Math.pow(joystick.getRawAxis(4),3);
		if(usingGyro)
		{
			robotDrive.mecanumDrive_Cartesian(x, y, z, gyro.getAngle());
			//robotDrive.mecanumDrive_Cartesian(x, y, z, 0.0);

		}else
		{
			robotDrive.mecanumDrive_Cartesian(x, y, z, 0.0);
		}
		this.usingGyro = usingGyro;
	}
	public void stop()
	{
		robotDrive.drive(0, 0);
	}
	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithMecanum(true));
	}
	public void turn(double input)
	{
		robotDrive.mecanumDrive_Cartesian(0.0, 0.0, input, 0.0);
	}
	public void goForward(double input)
	{
		robotDrive.mecanumDrive_Cartesian(0.0, -input, 0.0, 0.0);
	}
	public void goForward(double input, double curve)
	{
		robotDrive.mecanumDrive_Cartesian(0.0, input, 0.0, gyro.getRealAngle());
	}
	public void pidExecute()
	{
		robotDrive.drive(outputMagnitude, curve);
	}
	public void setPIDCurve(double curve)
	{
		this.curve = curve;
	}
	public void setPIDOutputMagnitude(double outputMagnitude)
	{
		this.outputMagnitude = outputMagnitude;
	}
	public void invert(boolean invert)
	{
		frontRightMotor.setInverted(invert);
		rearRightMotor.setInverted(invert);
		rearLeftMotor.setInverted(invert);
	}
	public void autoStrafe(double outputMagnitude)
	{
		rearLeftMotor.set(-outputMagnitude * .9);
		frontLeftMotor.set(outputMagnitude);
		rearRightMotor.set(outputMagnitude * .8);
		frontRightMotor.set(-outputMagnitude);
	}
	public void autoStrafe(double outputMagnitude, double curve)
	{
		robotDrive.mecanumDrive_Cartesian(outputMagnitude, 0.0, 0.0, gyro.getRealAngle());
	}
}
