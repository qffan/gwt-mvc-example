
package example.client.color.logic;

import example.client.color.model.ColorModel;
import example.client.core.model.ModelTestCase;

/**
 * @author Misha Gridnev
 */
public class ColorUpdateLogicTest extends ModelTestCase<ColorModel> {

  private static final String GREEN = "00FF00";
  private static final String WHITE = "FFFFFF";
  private ColorUpdateLogic logic;

  protected ColorModel createModel() {
    return new ColorModel(modelFactory);
  }

  protected void onModelSetUp() throws Exception {
    model.getColor().setText(GREEN);
    logic = new ColorUpdateLogicImpl(model);
  }

  public void testInit() {
    ColorModel initModel = createModel();
    assertEquals("Unexpected initial state", "", initModel.getColor().getText());

    logic.initColor(initModel);
    assertEquals("Model was not initialized", GREEN, initModel.getColor().getText());
  }

  public void testSave() {
    ColorModel updatedModel = createModel();
    updatedModel.getColor().setText(WHITE);

    logic.saveColor(updatedModel);

    assertEquals("Master model was not updated", WHITE, model.getColor().getText());
  }
}
