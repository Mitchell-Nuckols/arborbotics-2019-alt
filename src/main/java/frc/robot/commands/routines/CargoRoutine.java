package frc.robot.commands.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.SleepDriveBase;
import frc.robot.commands.auto.DriveStraightPID;
import frc.robot.commands.auto.DriveTurnEncoderPID;

public class CargoRoutine extends CommandGroup {

  public CargoRoutine() {
    addSequential(new DriveStraightPID(230));
    addSequential(new SleepDriveBase(0.5));
    addSequential(new DriveTurnEncoderPID(90));
    addSequential(new SleepDriveBase(0.5));
    addSequential(new DriveStraightPID(65));
    addSequential(new SleepDriveBase(0.5));
    addSequential(new DriveTurnEncoderPID(-90));
    addSequential(new SleepDriveBase(0.5));
    addSequential(new DriveStraightPID(200));
    addSequential(new SleepDriveBase(0.5));
    addSequential(new DriveTurnEncoderPID(-90));
  }
}
