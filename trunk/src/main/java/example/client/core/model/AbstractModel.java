
package example.client.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The default implementation of the core model logic.
 *
 * @author Misha Gridnev
 */
public abstract class AbstractModel implements Model {

  /**
   * Listener list.
   */
  private final List<ModelChangeListener> listeners = new ArrayList<ModelChangeListener>();

  public void addModelChangeListener(ModelChangeListener listener) {
    if (!listeners.contains(listener)) {
      listeners.add(listener);
    }
  }

  public void removeModelChangeListener(ModelChangeListener listener) {
    listeners.remove(listener);
  }

  /**
   * Fires a model change event.
   */
  protected void fireModelChangeEvent() {
    ModelChangeEvent event = null;

    for (ModelChangeListener listener : listeners) {
      if (event == null) {
        event = new ModelChangeEvent(this);
      }

      listener.modelChanged(event);
    }
  }
}
