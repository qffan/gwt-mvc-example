
package example.client.color.view;

/**
 * Color constants specific to the dialog box.
 *
 * @author Misha Gridnev
 */
public interface ColorDialogConstants extends ColorConstants, StyleConstants {
  @Key("title")
  @DefaultStringValue("Color")
  String title();

  @Key("button.ok")
  @DefaultStringValue("OK")
  String okButton();

  @Key("button.cancel")
  @DefaultStringValue("Cancel")
  String cancelButton();
}

