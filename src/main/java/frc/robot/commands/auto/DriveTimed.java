package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

public class DriveTimed extends TimedCommand {

   private double speed;

  public DriveTimed(double timeout, double speed) {
    super(timeout);
    requires(Robot.driveBase);

    this.speed = speed;
  }

  @Override
  protected void initialize() {
    Robot.driveBase.reset();
  }

  @Override
  protected void execute() {
    Robot.driveBase.tankDrive(speed, speed);
  }

  @Override
  protected void end() {
    Robot.driveBase.stop();
  }

  @Override
  protected void interrupted() {}
}
