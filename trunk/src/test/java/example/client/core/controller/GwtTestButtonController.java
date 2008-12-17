
package example.client.core.controller;

import com.google.gwt.user.client.ui.Button;

import example.client.core.model.TextModel;

/**
 * @author Misha Gridnev
 */
public class GwtTestButtonController extends GwtControllerTestBase {
  public void testInit() {
    Button button = new Button();

    String name = "Button name";
    TextModel text = modelFactory.createTextModel();
    text.setText(name);

    new ButtonController(button, text, modelFactory.createActionModel());

    assertEquals(name, button.getText());
  }
}
