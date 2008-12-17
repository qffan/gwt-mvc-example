
package example.client.core.controller;

import com.google.gwt.user.client.ui.Label;

import example.client.core.model.TextModel;
import example.client.core.model.ModelChangeListener;
import example.client.core.model.ModelChangeEvent;

/**
 * A controller that binds a text model to a label. Changes in the model are automatically
 * reflected in the label.
 *
 * @author Misha Gridnev
 */
public class LabelController {

  public LabelController(final Label label, final TextModel model) {
    // Model to view
    model.addModelChangeListener(new ModelChangeListener() {
      public void modelChanged(ModelChangeEvent event) {
        label.setText(model.getText());
      }
    });

    // Initial text
    label.setText(model.getText());
  }
}
