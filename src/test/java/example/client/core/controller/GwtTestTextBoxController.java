
package example.client.core.controller;

import com.google.gwt.user.client.ui.TextBox;

import example.client.core.model.TextModel;

/**
 * @author Misha Gridnev
 */
public class GwtTestTextBoxController extends GwtControllerTestBase {

  private TextBox textBox;
  private TextModel model;

  protected void onControllerSetUp() throws Exception {
    textBox = new TextBox();
    model = modelFactory.createTextModel();
  }

  public void testInit() {
    String text = "Some text";
    model.setText(text);

    new TextBoxController(textBox, model);

    assertEquals(text, textBox.getText());
  }

  public void testWidgetUpdated() {
    new TextBoxController(textBox, model);

    String text = "Some text";
    model.setText(text);

    assertEquals(text, textBox.getText());
  }
}
