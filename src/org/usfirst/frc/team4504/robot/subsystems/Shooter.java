package org.usfirst.frc.team4504.robot.subsystems;

import org.usfirst.frc.team4504.robot.RobotMap;
import org.usfirst.frc.team4504.robot.commands.ShooterStop;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {

	public Shooter()
	{
	}
	
	private CANTalon shooter = new CANTalon(RobotMap.shooter);
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	
	public void shoot(double output)
	{
		shooter.set(-output - RobotMap.addedShootPower);
	}

	public void shoot()
	{
		shooter.set(-RobotMap.shooterPower - RobotMap.addedShootPower);
	}
	
	public void stop()
	{
		shooter.set(0.0);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ShooterStop());
    }
}

