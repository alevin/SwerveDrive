/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5507.robot.subsystems;

import org.usfirst.frc.team5507.robot.Robot;
import org.usfirst.frc.team5507.robot.commands.ShowLimelight;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class Limelight extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

  public static NetworkTableEntry tx = table.getEntry("tx");
  public static NetworkTableEntry ty = table.getEntry("ty");
  public static NetworkTableEntry ta = table.getEntry("ta");

  // read values periodically
  public static double limelightx = tx.getDouble(0.0);
  public static double limelighty = ty.getDouble(0.0);
  public static double limelightarea = ta.getDouble(0.0);

  public static final double WIDTH = 320;
  public static final double HEIGHT = 240;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new ShowLimelight());
  }

  public void printInfo() {
    // post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", limelightx);
    SmartDashboard.putNumber("LimelightY", limelighty);
    SmartDashboard.putNumber("LimelightArea", limelightarea);

  }

  public void align() // method to line us up in the middle of the tape
  {
    while (limelightx > .5 && limelightx < -.5) {
      if (limelightx > .5) {
        System.out.println("turning");

        Robot.swerveDriveSubsystem.holonomicDrive(0, 0, .2);
      }
      else if (limelightx < -.5) {
        System.out.println("turning");
        Robot.swerveDriveSubsystem.holonomicDrive(0, 0, -.2);
      }
    }
    while(limelightarea < 10)
    System.out.println("driving straight");
    Robot.swerveDriveSubsystem.holonomicDrive(.2, 0, 0);
  }
}
