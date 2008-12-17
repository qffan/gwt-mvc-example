
package example.client.core.model;

/**
 * A model representing a user action, e.g., user clicking on a button.
 *
 * @author Misha Gridnev
 */
public interface ActionModel extends Model, Action {
  /**
   * Sets an action delegate.
   *
   * @param action action delegate or null for NOP
   */
  void setAction(Action action);
}

