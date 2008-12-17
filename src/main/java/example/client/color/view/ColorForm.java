
package example.client.color.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

import example.client.core.view.WidgetFactory;
import example.client.color.model.ColorFormModel;

/**
 * A color form display a text field where a user can enter the RGB triplet value in HEX. The form
 * also has a button, which user can click to display a dialog box.
 *
 * @author Misha Gridnev
 */
public class ColorForm extends Composite {

  private static final ColorFormConstants constants = GWT.create(ColorFormConstants.class);

  /**
   * Creates a color form
   *
   * @param factory widget factory
   * @param model color model
   */
  public ColorForm(WidgetFactory factory, ColorFormModel model) {
    Label label = factory.createLabel(constants.colorLabel());
    TextBox text = factory.createTextBox(model.getColor());

    Button change = factory.createButton(constants.changeButton(), model.getChangeAction());

    HorizontalPanel panel = new HorizontalPanel();
    panel.setStyleName(constants.panelStyle());
    panel.setVerticalAlignment(HorizontalPanel.ALIGN_MIDDLE);
    panel.add(label);
    panel.add(text);
    panel.add(change);

    initWidget(panel);
  }
}
