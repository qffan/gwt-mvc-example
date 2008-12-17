
package example.client.color.model;

import example.client.core.model.ModelFactory;
import example.client.core.model.ActionModel;

/**
 * A UI level model that builds on top of actionable model by adding a cancel action.
 *
 * @author Misha Gridnev
 */
public class ColorDialogModel extends ColorFormModel {

  /**
   * Cancel action.
   */
  private final ActionModel cancel;

  /**
   * Creates a color model.
   *
   * @param factory factory to create sub-models
   */
  public ColorDialogModel(ModelFactory factory) {
    super(factory);

    cancel = factory.createActionModel();
  }

  public ActionModel getCancelAction() {
    return cancel;
  }
}
