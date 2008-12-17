
package example.client.core.model;

import java.util.EventObject;

/**
 * An event fires by models whenever they change.
 *
 * @author Misha Gridnev
 */
public class ModelChangeEvent extends EventObject {
  /**
   * Creates an event
   *
   * @param source event source
   */
  public ModelChangeEvent(Model source) {
    super(source);
  }
}
