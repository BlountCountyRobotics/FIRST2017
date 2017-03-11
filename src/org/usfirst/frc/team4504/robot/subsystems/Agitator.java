package org.usfirst.frc.team4504.robot.subsystems;

import org.usfirst.frc.team4504.robot.RobotMap;
import org.usfirst.frc.team4504.robot.commands.AgitatorStop;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Agitator extends Subsystem {

	// Put methods for controlling this subsystem
    // here. Call these from Commands.

	CANTalon agitator = new CANTalon(RobotMap.agitator);
	
	public void stop()
	{
		agitator.set(0.0);
	}
	
	public void run()
	{
		agitator.set(1.0);
	}
	
	public void reverse()
	{
		agitator.set(-1.0);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new AgitatorStop());
    }
}

