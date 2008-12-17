
package example.client.core.model;

/**
 * The default implementation of the model factory.
 *
 * @author Misha Gridnev
 */
public class DefaultModelFactory implements ModelFactory {

  public TextModel createTextModel() {
    return new DefaultTextModel();
  }

  public ActionModel createActionModel() {
    return new DefaultActionModel();
  }
}
