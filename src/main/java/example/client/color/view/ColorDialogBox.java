
package example.client.color.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import example.client.core.view.WidgetFactory;
import example.client.color.model.ColorDialogModel;

/**
 * A dialog box that is similar to the color form. The dialog has two buttons: OK and Cancel.
 *
 * @author Misha Gridnev
 */
public class ColorDialogBox extends DialogBox {

  private static final ColorDialogConstants constants = GWT.create(ColorDialogConstants.class);

  /**
   * Creates a dialog box.
   *
   * @param factory widget factory
   * @param model   color model
   */
  public ColorDialogBox(WidgetFactory factory, ColorDialogModel model) {
    // Label and color text box
    Label label = factory.createLabel(constants.colorLabel());
    TextBox text = factory.createTextBox(model.getColor());

    HorizontalPanel colorPanel = new HorizontalPanel();
    colorPanel.setVerticalAlignment(HorizontalPanel.ALIGN_MIDDLE);
    colorPanel.setStyleName(constants.panelStyle());
    colorPanel.add(label);
    colorPanel.add(text);

    // Buttons
    Button accept = factory.createButton(constants.okButton(), model.getChangeAction());
    Button cancel = factory.createButton(constants.cancelButton(), model.getCancelAction());

    HorizontalPanel buttonPanel = new HorizontalPanel();
    buttonPanel.setStyleName(constants.panelStyle());
    buttonPanel.add(accept);
    buttonPanel.add(cancel);

    // The whole enchilada
    VerticalPanel mainPanel = new VerticalPanel();
    mainPanel.setStyleName(constants.panelStyle());
    mainPanel.add(colorPanel);
    mainPanel.add(buttonPanel);

    setText(constants.title());
    setWidget(mainPanel);
  }
}
