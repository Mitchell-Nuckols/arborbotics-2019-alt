package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;

public class DriveStraightPID extends PIDCommand {
  private final static double P = 0.03, I = 0.0, D = 0.05, F = 0.15;

  private double target;

  public DriveStraightPID(double distance) {
    super("drive_straight_pid", P, I, D);
    requires(Robot.driveBase);

    if(DriverStation.getInstance().isTest()) getPIDController().setPID(0.0, 0.0, 0.0);

    getPIDController().reset();
    getPIDController().setPID(P, I, D, F);
    getPIDController().setAbsoluteTolerance(0.05);
    getPIDController().setOutputRange(-0.5, 0.5);
    getPIDController().setContinuous(false);

    this.target = distance;
  }

  @Override
  protected void initialize() {
    Robot.driveBase.reset();
    setSetpoint(0);
    getPIDController().enable();
  }

  @Override
  protected boolean isFinished() {
    return (Math.abs(Robot.driveBase.getEncoderDistance() - target) < 0.03*target);
  }

  @Override
  protected void end() {
    Robot.driveBase.stop();
    getPIDController().disable();
  }

  @Override
  protected double returnPIDInput() {
    return Robot.driveBase.getEncoderAngle();
  }

  @Override
  protected void usePIDOutput(double output) {
    Robot.driveBase.arcadeDrive(Math.signum(target) * 0.5, output, false);
  }
}
