
package example.client.core.model;

/**
 * The interface represents a user action such as user clicking a button.
 *
 * @author Misha Gridnev
 */
public interface Action {
  /**
   * Called to notify an action is taking place.
   */
  void execute();
}

