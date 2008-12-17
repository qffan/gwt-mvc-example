
package example.client.color.controller;

import example.client.core.model.Action;
import example.client.color.model.ColorFormModel;

/**
 * A controller associated with the main color form. The controller's only job is to show the dialog
 * box.
 *
 * @author Misha Gridnev
 */
public class ColorFormController {

  /**
   * Creates a controller.
   *
   * @param model            color form model
   * @param dialogController dialog controller that oversees dialog box interactions
   */
  public ColorFormController(final ColorFormModel model,
                             final ColorDialogBoxController dialogController) {
    // Wire the action
    model.getChangeAction().setAction(new Action() {
      public void execute() {
        dialogController.showDialog();
      }
    });
  }
}
