
package org.usfirst.frc.team5507.robot;

import com.kauailabs.navx.frc.AHRS;

import org.usfirst.frc.team5507.robot.subsystems.Climber;
import org.usfirst.frc.team5507.robot.subsystems.HatchDelivery;
import org.usfirst.frc.team5507.robot.subsystems.SwerveDriveModule;
//import org.usfirst.frc.team5507.robot.subsystems.SwerveDriveModule;
import org.usfirst.frc.team5507.robot.subsystems.SwerveDriveSubsystem;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
	public static final boolean DEBUG = true;

	private static OI mOI;
	private static SwerveDriveSubsystem swerveDriveSubsystem;
	private Timer timer; 
	public static Climber m_climber;
	public static HatchDelivery m_HatchDelivery;

	public static OI getOI() {
		return mOI;
	}

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		mOI = new OI(this);

		swerveDriveSubsystem = new SwerveDriveSubsystem();

		mOI.registerControls();

		timer = new Timer();
	}

	@Override
	public void robotPeriodic() {
		SmartDashboard.putNumber("Adjusted Drivetrain Angle", swerveDriveSubsystem.getGyroAngle());
		SmartDashboard.putNumber("Raw Drivetrain Angle", swerveDriveSubsystem.getRawGyroAngle());
		SmartDashboard.putNumber("Drivetrain Rate", swerveDriveSubsystem.getGyroRate());
		SmartDashboard.putNumber("Gyro Update Rate", swerveDriveSubsystem.getNavX().getActualUpdateRate());


		for (int i = 0; i < 4; i++) {
			SmartDashboard.putNumber("Drive Current Draw " + i, swerveDriveSubsystem.getSwerveModule(i).getDriveMotor().getOutputCurrent());
			SmartDashboard.putNumber("Angle Current Draw " + i, swerveDriveSubsystem.getSwerveModule(i).getAngleMotor().getOutputCurrent());
			//System.out.println("Module " + i  + ": " + swerveDriveSubsystem.getSwerveModule(i).getCurrentAngle());
		}

		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		for (int i = 0; i < 4; i++) {
			swerveDriveSubsystem.getSwerveModule(i).robotDisabledInit();
		}
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
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {

	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		System.out.println(swerveDriveSubsystem.getSwerveModule(2).getInches());
	}

	@Override
	public void testInit() {
		timer.reset();
		timer.start();
	}
	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
		System.out.println(swerveDriveSubsystem.getSwerveModule(2).getInches());
		if(Math.abs(swerveDriveSubsystem.getSwerveModule(2).getInches()) < 80) swerveDriveSubsystem.holonomicDrive(-0.3, 0, 0);
		else swerveDriveSubsystem.stopDriveMotors();
		/*System.out.println("Module 0 = " + swerveDriveSubsystem.getSwerveModule(0).getAngleMotor().getSelectedSensorPosition(RobotMap.kPIDLoopIdx));
		System.out.println("Module 3 = " + swerveDriveSubsystem.getSwerveModule(3).getAngleMotor().getSelectedSensorPosition(RobotMap.kPIDLoopIdx));*/
	}

	public SwerveDriveSubsystem getDrivetrain() {
		return swerveDriveSubsystem;
	}
}
