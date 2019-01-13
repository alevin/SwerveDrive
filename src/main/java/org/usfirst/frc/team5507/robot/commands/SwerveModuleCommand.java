package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.subsystems.SwerveDriveModule;

import edu.wpi.first.wpilibj.command.Command;

public class SwerveModuleCommand extends Command {

	private SwerveDriveModule mDriveModule;

	public SwerveModuleCommand(SwerveDriveModule driveModule) {
		this.mDriveModule = driveModule;

		requires(driveModule);
	}

	@Override
	protected void execute() {
		// TODO: Handle smart swerve drive angling.
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
