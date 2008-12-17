
package example.client.core.model;

/**
 * A model.
 *
 * @author Misha Gridnev
 */
public interface Model {

  /**
   * Puts the model into its initial state. The initial state is a model immediately after
   * creation.
   */
  void reset();

  /**
   * Initializes this model from another model. Effectively, this is a copy operation.
   *
   * @param source source model
   */
  void copyFrom(Model source);

  /**
   * Registeres a model change listener with the model.
   *
   * @param listener listener
   */
  void addModelChangeListener(ModelChangeListener listener);

  /**
   * Unregistered a model change listener with the model.
   *
   * @param listener listener
   */
  void removeModelChangeListener(ModelChangeListener listener);
}

