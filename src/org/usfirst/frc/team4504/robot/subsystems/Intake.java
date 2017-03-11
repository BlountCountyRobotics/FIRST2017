package org.usfirst.frc.team4504.robot.subsystems;

import org.usfirst.frc.team4504.robot.RobotMap;
import org.usfirst.frc.team4504.robot.commands.IntakeStop;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	CANTalon intake = new CANTalon(RobotMap.intake);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new IntakeStop());
    }
    
    public void stop()
    {
    	intake.set(0.0);
    }
    public void run()
    {
    	intake.set(RobotMap.intakePower);
    }
    public void reverse()
    {
    	intake.set(-RobotMap.intakePower);
    }
}

