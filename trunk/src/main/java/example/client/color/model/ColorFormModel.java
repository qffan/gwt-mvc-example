
package example.client.color.model;

import example.client.core.model.ModelFactory;
import example.client.core.model.ActionModel;

/**
 * A UI level model that adds a concept of a change action to the basic color model.
 *
 * @author Misha Gridnev
 */
public class ColorFormModel extends ColorModel {

  /**
   * An action to change collor.
   */
  private final ActionModel change;

  /**
   * Creates a color model.
   *
   * @param factory factory to create sub-models
   */
  public ColorFormModel(ModelFactory factory) {
    super(factory);

    change = factory.createActionModel();
  }

  public ActionModel getChangeAction() {
    return change;
  }
}
