
package example.client.core.model;

import java.util.EventListener;

/**
 * A listener that is notified whenever a model fires a model change event.
 *
 * @author Misha Gridnev
 */
public interface ModelChangeListener extends EventListener {

  /**
   * Called to notify about the model change.
   *
   * @param event change event
   */
  void modelChanged(ModelChangeEvent event);
}

