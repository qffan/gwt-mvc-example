
package example.client.core.model;

/**
 * @author Misha Gridnev
 */
public interface TextModel extends Model {

  /**
   * Returns text value.
   *
   * @return text
   */
  String getText();

  /**
   * Sets text value.
   *
   * @param text text value
   */
  void setText(String text);
}

