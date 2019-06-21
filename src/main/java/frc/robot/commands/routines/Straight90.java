package frc.robot.commands.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.auto.DriveStraightPID;
import frc.robot.commands.auto.DriveTurnEncoderPID;

public class Straight90 extends CommandGroup {

  public Straight90() {
    addSequential(new DriveStraightPID(100));
    addSequential(new DriveTurnEncoderPID(-90));
  }
}
