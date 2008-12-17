package example;

/**
 * Interface to represent the constants contained in resource bundle:
 * 	'/usr/local/google/projects/gwt-mvc-example/src/main/java/example/Junk.properties'.
 */
public interface Junk extends com.google.gwt.i18n.client.Constants {
  
  /**
   * Translated "I am an unforunate side effect of the GWT plugin. See <i18nConstantsNames> inside pom.xml for an explanation.".
   * 
   * @return translated "I am an unforunate side effect of the GWT plugin. See <i18nConstantsNames> inside pom.xml for an explanation."
   */
  @DefaultStringValue("I am an unforunate side effect of the GWT plugin. See <i18nConstantsNames> inside pom.xml for an explanation.")
  @Key("junk")
  String junk();
}
