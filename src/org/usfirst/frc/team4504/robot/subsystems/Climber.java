package org.usfirst.frc.team4504.robot.subsystems;

import org.usfirst.frc.team4504.robot.RobotMap;
import org.usfirst.frc.team4504.robot.commands.ClimberStop;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {


	CANTalon climber = new CANTalon(RobotMap.climber);
	
	// Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public void up()
	{
		climber.set(1.0);
	}
	public void stop()
	{
		climber.set(0.0);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ClimberStop());
    }
}

