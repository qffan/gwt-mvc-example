
package example.client.core.controller;

import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import example.client.core.model.ModelChangeEvent;
import example.client.core.model.ModelChangeListener;
import example.client.core.model.TextModel;

/**
 * A controller that binds a text box to a text model. Changes in the text model are propagated to
 * the text box and visa versa.
 *
 * @author Misha Gridnev
 */
public class TextBoxController {

  public TextBoxController(final TextBox text, final TextModel model) {
    // Model to view
    model.addModelChangeListener(new ModelChangeListener() {
      public void modelChanged(ModelChangeEvent event) {
        text.setText(model.getText());
      }
    });

    // Initial text
    text.setText(model.getText());

    // View to model
    final Runnable runnable = new Runnable() {
      public void run() {
        model.setText(text.getText());
      }
    };

    text.addChangeListener(new ChangeListener() {
      public void onChange(Widget widget) {
        runnable.run();
      }
    });

    // Since we only get the change events on focus loss, kick off a timer when the text field
    // gains focus and monitor for changes
    // todo misha the update interval should probably be configurable
    text.addFocusListener(new FocusMonitor(500, runnable));
  }
}
