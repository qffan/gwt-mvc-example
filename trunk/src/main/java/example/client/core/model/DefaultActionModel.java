
package example.client.core.model;

/**
 * Default implementation of an action model. The implementation is pretty dump, it simply delegates
 * to another action.
 *
 * @author Misha Gridnev
 */
public class DefaultActionModel extends AbstractModel implements ActionModel {

  /**
   * Action delegate.
   */
  private Action action;

  public void setAction(Action action) {
    this.action = action;
  }

  public void execute() {
    if (action != null) {
      action.execute();
    }
  }

  public void reset() {
  }

  public void copyFrom(Model source) {
  }
}
