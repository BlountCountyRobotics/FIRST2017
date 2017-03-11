package org.usfirst.frc.team4504.robot.commands.autonomous;


import org.usfirst.frc.team4504.robot.RobotMap;
import org.usfirst.frc.team4504.robot.commands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRight extends CommandGroup {

    public AutoRight() {
    	
    	addSequential(new ShortApproach(1.6));
    	addSequential(new Wait(.1));
    	addSequential(new TurnToGears(false));
    	addSequential(new Wait(.1));
    	addSequential(new StrafeToGears(RobotMap.inchesToGearLeft));
    	addSequential(new Wait(.1));
    	addSequential(new ApproachGears(false));
    	addSequential(new Wait(.1));
    	addSequential(new TurnToGears(false));   
    	addSequential(new Wait(.1));
    	addSequential(new StrafeToGears(RobotMap.inchesToGearLeft));
    	addSequential(new Wait(.1));
    	addSequential(new ApproachGears(true));
    	addSequential(new Wait(.1));
    }
}
