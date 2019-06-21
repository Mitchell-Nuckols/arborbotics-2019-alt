package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.IntakeDown;
import frc.robot.commands.IntakeIn;
import frc.robot.commands.IntakeOut;
import frc.robot.commands.IntakeUp;
import frc.robot.commands.LiftDown;
import frc.robot.commands.LiftUp;
import frc.robot.commands.XboxArcadeDrive;
import frc.robot.commands.XboxTankDrive;
import frc.robot.control.ButtonCombo;
import frc.robot.control.JoystickAnalogButton;
import frc.robot.control.ButtonCombo.ComboType;
import frc.robot.control.XboxButton;

public class OI {
  private Joystick joystick;
  private XboxController xbox;
  private Button
    intakeUp, intakeDown, intakeIn, intakeOut, liftUp, liftDown, driveB1, driveB2;
  private Trigger driveModifier;

  private Joystick assist;
  private Button
    assistIntakeUp, assistIntakeDown, assistIntakeIn, assistIntakeOut;


  public OI() {
    if(DriverStation.getInstance().getJoystickIsXbox(RobotMap.CONTROL_JOYSTICK)) {
      xbox = new XboxController(RobotMap.CONTROL_JOYSTICK);

      intakeUp = new JoystickButton(xbox, XboxButton.A);
      intakeDown = new JoystickButton(xbox, XboxButton.B);
      liftUp = new JoystickButton(xbox, XboxButton.LBUMPER);
      liftDown = new JoystickButton(xbox, XboxButton.RBUMPER);

      driveB1 = new JoystickButton(xbox, XboxButton.LSTICK);
      driveB2 = new JoystickButton(xbox, XboxButton.RSTICK);

      driveModifier = new ButtonCombo(driveB1, driveB2, ComboType.OR);
      driveModifier.toggleWhenActive(new XboxArcadeDrive(RobotMap.CONTROL_SPEED_DAMP, RobotMap.CONTROL_ROTATION_DAMP));
    }else {
      joystick = new Joystick(RobotMap.CONTROL_JOYSTICK);

      intakeUp = new JoystickButton(joystick, 3);
      intakeDown = new JoystickButton(joystick, 5);
      intakeIn = new JoystickButton(joystick, 1);
      intakeOut = new JoystickButton(joystick, 2);
      liftUp = new JoystickButton(joystick, 6);
      liftDown = new JoystickButton(joystick, 4);
    }

    assist = new Joystick(RobotMap.CONTROL_ASSIST);
    assistIntakeUp = new JoystickButton(assist, XboxButton.RBUMPER);
    assistIntakeDown = new JoystickButton(assist, XboxButton.LBUMPER);
    assistIntakeIn = new JoystickButton(assist, XboxButton.B);
    assistIntakeOut = new JoystickButton(assist, XboxButton.A);

    assistIntakeUp.whileHeld(new IntakeUp());
    assistIntakeDown.whileHeld(new IntakeDown());
    assistIntakeIn.whileHeld(new IntakeIn());
    assistIntakeOut.whileHeld(new IntakeOut());

    intakeUp.whileHeld(new IntakeUp());
    intakeDown.whileHeld(new IntakeDown());
    intakeIn.whileHeld(new IntakeIn());
    intakeOut.whileHeld(new IntakeOut());
    liftUp.whileHeld(new LiftUp());
    liftDown.whileHeld(new LiftDown());
  }

  public double getRotation() {
    return joystick.getTwist();
  }

  public double getSpeed() {
    return -(joystick.getY() * RobotMap.CONTROL_SPEED_DAMP);
  }

  public double getTankLeft() {
    return -xbox.getY(Hand.kLeft);
  }

  public double getTankRight() {
    return -xbox.getY(Hand.kRight);
  }

  public double getXboxY(Hand hand) {
    return -xbox.getY(hand);
  }

  public double getXboxX(Hand hand) {
    return -xbox.getX(hand);
  }

  public boolean getStickButtonLeft() {
    return xbox.getStickButton(Hand.kLeft);
  }

  public boolean getStickButtonRight() {
    return xbox.getStickButton(Hand.kRight);
  }

  public double sqrInput(double input) {
    return Math.signum(input) * (input*input);
  }

  public double scaleBetween(double unscaled, double min, double max, double a, double b) {
    return (b - a) * (unscaled - min) / (max - min) + a;
  }
}
