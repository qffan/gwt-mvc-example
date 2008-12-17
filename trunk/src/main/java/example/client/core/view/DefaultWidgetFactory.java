
package example.client.core.view;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

import example.client.core.controller.ButtonController;
import example.client.core.controller.LabelController;
import example.client.core.controller.TextBoxController;
import example.client.core.model.ActionModel;
import example.client.core.model.ModelFactory;
import example.client.core.model.TextModel;

/**
 * A factory for creating standard widgets.
 *
 * @author Misha Gridnev
 */
public class DefaultWidgetFactory implements WidgetFactory {

  private final ModelFactory factory;

  /**
   * Creates a widget factory.
   *
   * @param factory model factory
   */
  public DefaultWidgetFactory(ModelFactory factory) {
    this.factory = factory;
  }

  public Label createLabel(TextModel text) {
    Label label = new Label();

    new LabelController(label, text); // connect widget to the model

    return label;
  }

  public Label createLabel(String text) {
    return new Label(text);
  }

  public TextBox createTextBox(TextModel model) {
    TextBox text = new TextBox();

    new TextBoxController(text, model); // connect widget to the model

    return text;
  }

  public Button createButton(TextModel text, ActionModel action) {
    Button button = new Button();

    new ButtonController(button, text, action); // connect widget to the model

    return button;
  }

  public Button createButton(String name, ActionModel action) {
    TextModel text = factory.createTextModel();
    text.setText(name);

    return createButton(text, action);
  }
}
