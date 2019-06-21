/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;

public class DriveTurnPID extends PIDCommand {
  private static final double P = 0.2, I = 0.0, D = 0.0;

  private double angle;

  public DriveTurnPID(double angle) {
    super("drive_turn_pid", P, I, D);

    requires(Robot.driveBase);

    getPIDController().setAbsoluteTolerance(0.1);
    setInputRange(-23040, 23040);
    getPIDController().setOutputRange(-0.5, 0.5);
    getPIDController().setContinuous(true);

    this.angle = angle;
  }

  @Override
  protected void initialize() {
    Robot.driveBase.reset();
    Robot.driveBase.gyroCalibrate();

    setSetpoint(angle + Robot.driveBase.getAngle());
  }

  @Override
  protected double returnPIDInput() {
    return Robot.driveBase.getAngle();
  }

  @Override
  protected void usePIDOutput(double output) {
    Robot.driveBase.tankDrive(-output, output);
  }

  @Override
  protected boolean isFinished() {
    return getPIDController().onTarget();
  }

  @Override
  protected void end() {
    Robot.driveBase.stop();
  }
}
