package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class XboxArcadeDrive extends Command {

  private double speedModifier = 1.0, rotationModifier = 1.0;
  
  public XboxArcadeDrive(double speedModifier, double rotationModifier) {
    super("xbox_tank_drive");

    requires(Robot.driveBase);

    this.speedModifier = speedModifier;
    this.rotationModifier = rotationModifier;
  }

  @Override
  protected void initialize() {
    Robot.driveBase.stop();
    Robot.driveBase.reset();
  }

  @Override
  protected void execute() {
    Robot.driveBase.arcadeDrive(Robot.oi.getXboxY(Hand.kLeft) * speedModifier, -Robot.oi.getXboxX(Hand.kRight) * rotationModifier, false);
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
