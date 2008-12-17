
package example.client.core.model;

/**
 * A factory to create all kinds of models.
 *
 * @author Misha Gridnev
 */
public interface ModelFactory {

  /**
   * Creates a text model.
   *
   * @return text model
   */
  TextModel createTextModel();

  /**
   * Creates an action model.
   *
   * @return action model
   */
  ActionModel createActionModel();
}

