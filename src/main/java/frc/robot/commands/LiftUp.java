package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LiftUp extends Command {
  public LiftUp() {
    super("lift_up");
    requires(Robot.lift);
  }

  @Override
  protected void execute() {
    Robot.lift.up();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.lift.stop();
  }
}
