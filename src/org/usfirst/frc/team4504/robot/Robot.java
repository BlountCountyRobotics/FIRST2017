
package org.usfirst.frc.team4504.robot;

import org.usfirst.frc.team4504.robot.subsystems.Agitator;
import org.usfirst.frc.team4504.robot.subsystems.Climber;
import org.usfirst.frc.team4504.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4504.robot.subsystems.Intake;
import org.usfirst.frc.team4504.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
// import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4504.robot.commands.autonomous.AutoLeft;
import org.usfirst.frc.team4504.robot.commands.autonomous.AutoMiddle;
import org.usfirst.frc.team4504.robot.commands.autonomous.AutoRight;
// import org.usfirst.frc.team4504.robot.commands.ExampleCommand;
//import org.usfirst.frc.team4504.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4504.robot.subsystems.pid.AutoAngle;
import org.usfirst.frc.team4504.robot.subsystems.pid.BoilerDistance;
import org.usfirst.frc.team4504.robot.subsystems.pid.BoilerStrafe;
import org.usfirst.frc.team4504.robot.subsystems.pid.GearStrafe;
import org.usfirst.frc.team4504.robot.subsystems.pid.GearDistance;
import org.usfirst.frc.team4504.robot.subsystems.pid.GyroAngle;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final DriveTrain driveTrain = new DriveTrain();
	public static OI oi;
	
	public static final GyroAngle gyroAngle = new GyroAngle();
	public static final GearStrafe gearStrafe = new GearStrafe();
	public static final AutoAngle autoAngle = new AutoAngle();
	public static final Shooter shooter = new Shooter();
	public static final GearDistance gearDistance = new GearDistance();
	public static final Agitator agitator = new Agitator();
	public static final BoilerDistance boilerDistance = new BoilerDistance();
	public static final BoilerStrafe boilerStrafe = new BoilerStrafe();
	public static final Climber climber = new Climber();
	public static final Intake intake = new Intake();
	public static Scheduler scheduler;
	
	//public static final AutoApproach autoApproach = new AutoApproach();
	
	public static NetworkTable visionTable;
	
	
	// Command autonomousCommand;
	
	public static SendableChooser<Command> autoCommand = new SendableChooser<Command>();

	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		NetworkTable.setServerMode();
		NetworkTable.initialize();
		visionTable = NetworkTable.getTable("visionTable");
		oi = new OI();
		autoCommand.addObject("Middle", new AutoMiddle());
		autoCommand.addObject("Right", new AutoRight());
		autoCommand.addObject("Left", new AutoLeft());
		autoCommand.addDefault("None", null);

		SmartDashboard.putData(driveTrain);
		SmartDashboard.putData(shooter);
		SmartDashboard.putData(intake);
		SmartDashboard.putData(agitator);

		
		SmartDashboard.putData("Autonomous mode", autoCommand);
		driveTrain.gyro.calibrate();
	}

	
	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 *  the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	
	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		Command auto = autoCommand.getSelected();
		if(auto != null)
		{
			auto.start();
		}
		driveTrain.gyro.reset();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		scheduler = Scheduler.getInstance();
		scheduler.run();
	}

	@Override
	public void teleopInit() {
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		SmartDashboard.putNumber("gyroAngle", driveTrain.gyro.getRealAngle());
		SmartDashboard.putBoolean("gearIsFound", visionTable.getBoolean("gearIsFound", false));
		SmartDashboard.putBoolean("boilerIsFound", visionTable.getBoolean("boilerIsFound", false));
		SmartDashboard.putNumber("addedShootPower", RobotMap.addedShootPower);
		SmartDashboard.putNumber("boilerDistance", visionTable.getNumber("boilerDistance", 0.0));
		SmartDashboard.putNumber("boilerAngle", visionTable.getNumber("boilerAngle", 0.0));
		
		SmartDashboard.putNumber("gearDistance", visionTable.getNumber("gearDistance", 0.0));
		double gearTranslational = visionTable.getNumber("gearTranslational", 0.0);
		SmartDashboard.putNumber("gearTranslational", gearTranslational);
		SmartDashboard.putBoolean("gearOnTarget", gearTranslational >= 6.0 && gearTranslational <= 10.0);
		SmartDashboard.putBoolean("switcher", visionTable.getBoolean("switcher", false));
		SmartDashboard.putNumber("recommendedShootingPower", boilerDistance.getPower(boilerDistance.getClosestDistance()));
		SmartDashboard.putNumber("totalShootPower", RobotMap.shooterPower + RobotMap.addedShootPower);
		SmartDashboard.putBoolean("usingGyro", driveTrain.usingGyro);
		scheduler =  Scheduler.getInstance();
		scheduler.run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
