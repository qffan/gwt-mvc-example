package example.client.color.view;

/**
 * Generic color constants that can be shared by all views.
 */
public interface ColorConstants extends com.google.gwt.i18n.client.Constants {

  @DefaultStringValue("Color:")
  @Key("label.color")
  String colorLabel();
}
