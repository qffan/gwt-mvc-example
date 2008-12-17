
package example.client.core.model;

/**
 * Default implementation of the text model.
 *
 * @author Misha Gridnev
 */
public class DefaultTextModel extends AbstractModel implements TextModel {

  /**
   * The text.
   */
  private String text = "";

  public DefaultTextModel() {
    reset();
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    if (!this.text.equals(text)) {
      this.text = text;
      fireModelChangeEvent();
    }
  }

  public void reset() {
    setText("");
  }

  public void copyFrom(Model source) {
    setText(((TextModel)source).getText());
  }
}
