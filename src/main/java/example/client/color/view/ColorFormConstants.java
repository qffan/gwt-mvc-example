
package example.client.color.view;

/**
 * Color constants specific to the main color form.
 *
 * @author Misha Gridnev
 */
public interface ColorFormConstants extends ColorConstants, StyleConstants {
  @Key("button.change")
  @DefaultStringValue("Change...")
  String changeButton();
}

