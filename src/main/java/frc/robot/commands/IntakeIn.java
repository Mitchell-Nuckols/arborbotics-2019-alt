package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class IntakeIn extends Command {
  public IntakeIn() {
    super("intake_in");
    requires(Robot.intake);
  }

  @Override
  protected void execute() {
    Robot.intake.in();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.intake.stop();
  }
}
