
package example.client.core.controller;

import com.google.gwt.junit.client.GWTTestCase;

import example.client.core.model.DefaultModelFactory;
import example.client.core.model.ModelFactory;

/**
 * The base for all GWT tests in the application. Please note, in order for your tests to be run by
 * GWT Maven plugin, the test name must begin with {@code GwtTest} prefix. The prefix can be
 * override in GWT Maven plugin configuration but it has to be a prefix, i.e., the plugin does not
 * support a standard wildcard notation. Also be aware that GWT tests are not run in a normal
 * fashion, i.e., by Maven SureFire plugin. They are run by the GWT test runner. An additional
 * problem is {@link #getModuleName()}. It has to refer to an existing module name.
 *
 * @author Misha Gridnev
 */
public abstract class GwtControllerTestBase extends GWTTestCase {

  protected ModelFactory modelFactory;

  protected final void gwtSetUp() throws Exception {
    modelFactory = new DefaultModelFactory();

    onControllerSetUp();
  }

  protected void onControllerSetUp() throws Exception {

  }

  protected final void gwtTearDown() throws Exception {
    onControllerTearDown();
  }

  protected void onControllerTearDown() throws Exception {

  }

  public String getModuleName() {
    return "example.Application"; // todo misha can we provide some fake module?
  }
}
