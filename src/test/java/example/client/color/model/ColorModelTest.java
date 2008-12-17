
package example.client.color.model;

import example.client.core.model.ModelTestCase;

/**
 * @author Misha Gridnev
 */
public class ColorModelTest extends ModelTestCase<ColorModel> {

  protected ColorModel createModel() {
    return new ColorModel(modelFactory);
  }

  public void testCopyFrom() {
    ColorModel source = new ColorModel(modelFactory);

    String color = "AABBCC";
    source.getColor().setText(color);

    model.copyFrom(source);

    assertEquals(color, model.getColor().getText());
  }
}
