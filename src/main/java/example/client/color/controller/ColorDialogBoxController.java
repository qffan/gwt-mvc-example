
package example.client.color.controller;

import example.client.core.model.Action;
import example.client.color.model.ColorDialogModel;
import example.client.color.view.ColorDialogBox;
import example.client.color.logic.ColorUpdateLogic;

/**
 * A controller responsible for displaying and handling the events of the color chooser dialog box.
 * The controller delegates all initialization and update operations to {@link
 * example.client.color.logic.ColorUpdateLogic}
 *
 * @author Misha Gridnev
 */
public class ColorDialogBoxController {

  private final ColorDialogBox dialog;
  private final ColorDialogModel model;
  private final ColorUpdateLogic logic;

  /**
   * Creates a controller.
   *
   * @param dialog dialog box
   * @param model  dialog box model
   * @param logic  update logic
   */
  public ColorDialogBoxController(final ColorDialogBox dialog, final ColorDialogModel model,
                                  final ColorUpdateLogic logic) {
    this.dialog = dialog;
    this.model = model;
    this.logic = logic;

    // Wire OK action
    model.getChangeAction().setAction(new Action() {
      public void execute() {
        dialog.hide();
        logic.saveColor(model);
      }
    });

    // Wire cancel action
    model.getCancelAction().setAction(new Action() {
      public void execute() {
        dialog.hide();
      }
    });
  }

  /**
   * Displays dialog box. Before being displayed, the dialog box model is initialized from logic.
   */
  public void showDialog() {
    // Update dialog model
    logic.initColor(model);

    // Display the dialog
    dialog.center();
    dialog.show();
  }
}
