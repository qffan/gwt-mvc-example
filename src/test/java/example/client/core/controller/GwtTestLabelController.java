
package example.client.core.controller;

import com.google.gwt.user.client.ui.Label;

import example.client.core.model.TextModel;

/**
 * @author Misha Gridnev
 */
public class GwtTestLabelController extends GwtControllerTestBase {
  public void testInit() {
    Label label = new Label();

    TextModel model = modelFactory.createTextModel();
    String text = "Some text";
    model.setText(text);

    new LabelController(label, model);

    assertEquals(text, label.getText());
  }
}
