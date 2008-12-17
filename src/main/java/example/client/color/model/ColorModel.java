
package example.client.color.model;

import example.client.core.model.CompositeModel;
import example.client.core.model.ModelFactory;
import example.client.core.model.TextModel;

/**
 * A model abstracting the representation of color. This particular model stores the color as an RGB
 * triplet in HEX form, e.g., red is {@code FF0000}. You may consider this model to be a
 * <strong>business</strong> level model.
 *
 * @author Misha Gridnev
 */
public class ColorModel extends CompositeModel {

  /**
   * RGB color in Hex notation.
   */
  private final TextModel color;

  /**
   * Creates a color model.
   *
   * @param factory factory to create sub-models
   */
  public ColorModel(ModelFactory factory) {
    color = add("color", factory.createTextModel());
  }

  /**
   * Returns color RGB triplet.
   *
   * @return color
   */
  public TextModel getColor() {
    return color;
  }
}
