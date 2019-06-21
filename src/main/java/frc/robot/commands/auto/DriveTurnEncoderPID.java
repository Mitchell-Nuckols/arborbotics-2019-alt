package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;

public class DriveTurnEncoderPID extends PIDCommand {

  private static final double P = 0.03, I = 0.0, D = 0.05, F = 0.15;
  private double target;

  public DriveTurnEncoderPID(double angle) {
    super("drive_turn_encoder_pid", P, I, D);
    requires(Robot.driveBase);

    if(DriverStation.getInstance().isTest()) getPIDController().setPID(0.0, 0.0, 0.0);

    getPIDController().reset();
    getPIDController().setPID(P, I, D, F);
    getPIDController().setAbsoluteTolerance(0.30);
    getPIDController().setOutputRange(-0.75, 0.75);
    getPIDController().setContinuous(false);

    this.target = angle;
  }

  @Override
  protected void initialize() {
    Robot.driveBase.reset();
    setSetpoint(target);
    getPIDController().enable();
  }

  @Override
  protected boolean isFinished() {
    return getPIDController().onTarget();
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
    Robot.driveBase.arcadeDrive(0, output, false);
  }
}
