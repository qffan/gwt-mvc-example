
package example.client.core.controller;

import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;

import example.client.core.model.ActionModel;
import example.client.core.model.ModelChangeEvent;
import example.client.core.model.ModelChangeListener;
import example.client.core.model.TextModel;

/**
 * A controller that binds a button to a text and action models. Changes in the text model are
 * automatically reflected in the button. Button clicks are propagated to the action model.
 *
 * @author Misha Gridnev
 */
public class ButtonController {

  /**
   * Creates a controller.
   *
   * @param button the view
   * @param text   button text
   * @param action action fired whenever button is clicked
   */
  public ButtonController(final Button button, TextModel text, final ActionModel action) {
    // Update button text from model
    text.addModelChangeListener(new ModelChangeListener() {
      public void modelChanged(ModelChangeEvent event) {
        button.setText(action.toString());
      }
    });

    // Set initial text
    button.setText(text.getText());

    // Notify model when button is clicked
    button.addClickListener(new ClickListener() {
      public void onClick(Widget widget) {
        action.execute();
      }
    });
    button.sinkEvents(Event.ONCLICK);
  }
}
