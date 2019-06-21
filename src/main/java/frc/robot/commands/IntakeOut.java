package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class IntakeOut extends Command {
  public IntakeOut() {
    super("intake_out");
    requires(Robot.intake);
  }

  @Override
  protected void execute() {
    Robot.intake.out();
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