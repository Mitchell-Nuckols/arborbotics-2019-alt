package frc.robot.control;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class ButtonCombo extends Trigger {
  public static enum ComboType {
    OR,
    AND
  }

  private Button b1, b2;
  private ComboType operation;

  public ButtonCombo(Button b1, Button b2, ComboType operation) {
    this.b1 = b1;
    this.b2 = b2;
    this.operation = operation;
  }

  @Override
  public boolean get() {
    if(operation == ComboType.AND) return b1.get() && b2.get();
    else if(operation == ComboType.OR) return b1.get() || b2.get();
    return false;
  }
}
