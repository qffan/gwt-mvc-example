
package example.client.core.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Composite model serves as a base for custom models that are assembled from primitive models.
 *
 * @author Misha Gridnev
 */
public abstract class CompositeModel extends AbstractModel {

  private final Map<String, Model> children = new HashMap<String, Model>();

  /** Adds a child to the composite model.
   *
   * @param id child ID
   * @param child child to add
   * @return child model just added
   */
  protected <T extends Model> T add(String id, T child) {
    if (children.containsKey(id)) {
      throw new IllegalArgumentException("Duplicate child: " + id);
    }
    children.put(id, child);

    return child;
  }

  public void reset() {
    for (Model child : children.values()) {
      child.reset();
    }
  }

  public void copyFrom(Model source) {
    Map<String, Model> sourceChildren = ((CompositeModel)source).children;

    for (String id : children.keySet()) {
      Model targetChild = children.get(id);
      Model sourceChild = sourceChildren.get(id);

      if (sourceChild != null) {
        targetChild.copyFrom(sourceChild);
      }
    }
  }
}
