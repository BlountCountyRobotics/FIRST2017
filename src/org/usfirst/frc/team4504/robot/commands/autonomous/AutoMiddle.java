package org.usfirst.frc.team4504.robot.commands.autonomous;

import org.usfirst.frc.team4504.robot.RobotMap;
import org.usfirst.frc.team4504.robot.commands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoMiddle extends CommandGroup {

    public AutoMiddle() {
    	
    	//addSequential(new TurnToGears());
    	//addSequential(new StrafeToGears());
    	//addSequential(new ApproachGears());
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addSequential(new ShortApproach(.1));
    	addSequential(new Wait(.1));
    	addSequential(new StrafeToGears(RobotMap.inchesToGearLeft));
    	addSequential(new Wait(.1));
    	addSequential(new ApproachGears(false));
    	addSequential(new Wait(.1));
    	addSequential(new TurnToGears());   
    	addSequential(new Wait(.1));
    	addSequential(new StrafeToGears(RobotMap.inchesToGearLeft));
    	addSequential(new Wait(.1));
    	addSequential(new ApproachGears(true));
    	addSequential(new Wait(.1));
    }
}
