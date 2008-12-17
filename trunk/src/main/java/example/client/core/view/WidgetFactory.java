
package example.client.core.view;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Button;

import example.client.core.model.TextModel;
import example.client.core.model.ActionModel;

/**
 * @author Misha Gridnev
 */
public interface WidgetFactory {

  /**
   * Creates a dynamic label.
   *
   * @param text label text
   * @return label
   */
  Label createLabel(TextModel text);

  /**
   * Creates a static label.
   *
   * @param text label text
   * @return label
   */
  Label createLabel(String text);

  /**
   * Creates a text box backed by the given text model.
   *
   * @param model text model
   * @return text box
   */
  TextBox createTextBox(TextModel model);

  /**
   * Creates a button with a dynamic label that fires the given action when it is clicked.
   *
   * @param text   button label
   * @param action action model to receive click notifications
   * @return button
   */
  Button createButton(TextModel text, ActionModel action);

  /**
   * Creates a button with a static label.
   *
   * @param name   button label
   * @param action action model to receive click notification
   * @return a button
   */
  Button createButton(String name, ActionModel action);
}

