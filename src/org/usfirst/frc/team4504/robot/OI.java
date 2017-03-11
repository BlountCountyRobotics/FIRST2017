package org.usfirst.frc.team4504.robot;

import edu.wpi.first.wpilibj.buttons.JoystickButton;


import org.usfirst.frc.team4504.robot.commands.Agitate;
import org.usfirst.frc.team4504.robot.commands.AutoRightShoot;
import org.usfirst.frc.team4504.robot.commands.ClimberUp;
import org.usfirst.frc.team4504.robot.commands.Shoot;
import org.usfirst.frc.team4504.robot.commands.ShootPowerAdd;
import org.usfirst.frc.team4504.robot.commands.ShootPowerReset;
import org.usfirst.frc.team4504.robot.commands.ShootPowerSubtract;
import org.usfirst.frc.team4504.robot.commands.StopEverything;
//import org.usfirst.frc.team4504.robot.commands.autonomous.test;
import org.usfirst.frc.team4504.robot.commands.DriveTrainStop;
import org.usfirst.frc.team4504.robot.commands.DriveWithMecanum;
import org.usfirst.frc.team4504.robot.commands.IntakeForward;
import org.usfirst.frc.team4504.robot.commands.IntakeReverse;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	private XboxController joystick = new XboxController(0);
	private Joystick driverStation = new Joystick(1);

	
	private JoystickButton driveNoGyro = new JoystickButton(joystick, RobotMap.aButton);
	private JoystickButton stopDriveTrain = new JoystickButton(joystick,RobotMap.backButton);
	
	private JoystickButton ballPickup = new JoystickButton(driverStation, RobotMap.leverForward);
	private JoystickButton ballReverse = new JoystickButton(driverStation, RobotMap.leverBack);
	
	
	private JoystickButton stopEVERYTHING = new JoystickButton(joystick, RobotMap.bButton);
	
	
	
	private JoystickButton manualAgitator = new JoystickButton(driverStation, RobotMap.topRight);
	
	//public JoystickButton autoLeftShooter = new JoystickButton(driverStation, RobotMap.middleLeft);
	private JoystickButton manualShooter = new JoystickButton(driverStation, RobotMap.middleMiddle);
	public JoystickButton autoRightShooter = new JoystickButton(driverStation, RobotMap.middleRight);
	
	private JoystickButton shootPowerAdd = new JoystickButton(driverStation, RobotMap.bottomLeft);
	private JoystickButton shootPowerSubtract = new JoystickButton(driverStation, RobotMap.bottomMiddle);
	private JoystickButton shootPowerReset = new JoystickButton(driverStation, RobotMap.middleLeft);
	
	private JoystickButton climberUp = new JoystickButton(driverStation, RobotMap.topLeft);
		
	public OI()
	{
		stopDriveTrain.toggleWhenPressed(new DriveTrainStop());
		driveNoGyro.toggleWhenPressed(new DriveWithMecanum(false));
		
		ballPickup.whileHeld(new IntakeForward());
		ballReverse.whileHeld(new IntakeReverse());
		
		manualAgitator.toggleWhenPressed(new Agitate());
		//pidtest.toggleWhenPressed(new test());
		stopEVERYTHING.whenPressed(new StopEverything());
		
		shootPowerAdd.whenPressed(new ShootPowerAdd(2.5));
		shootPowerSubtract.whenPressed(new ShootPowerSubtract(2.5));
		shootPowerReset.whenPressed(new ShootPowerReset());
		
		manualShooter.toggleWhenPressed(new Shoot());
		autoRightShooter.toggleWhenPressed(new AutoRightShoot());
		
		climberUp.toggleWhenPressed(new ClimberUp());
	}
	
	public XboxController getJoystick()
	{
		return joystick;
	}
	
	public Joystick getDriverStation()
	{
		return driverStation;
	}
	
}
