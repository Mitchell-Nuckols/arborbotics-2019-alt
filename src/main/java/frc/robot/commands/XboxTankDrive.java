package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class XboxTankDrive extends Command {

  private double speedModifier = 1.0;

  public XboxTankDrive() {
    super("xbox_tank_drive");

    requires(Robot.driveBase);
  }

  public XboxTankDrive(double speedModifier) {
    this();

    this.speedModifier = speedModifier;
  }

  @Override
  protected void initialize() {
    Robot.driveBase.stop();
    Robot.driveBase.reset();
  }

  @Override
  protected void execute() {
    if(speedModifier != 1.0) Robot.driveBase.tankDrive(speedModifier * Robot.oi.getTankLeft(), speedModifier * Robot.oi.getTankRight());
    else Robot.driveBase.tankDrive(Robot.oi.getTankLeft(), Robot.oi.getTankRight());
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.driveBase.stop();
  }
}
