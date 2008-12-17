
package example.client.core.model;

import junit.framework.TestCase;

/**
 * @author Misha Gridnev
 */
public abstract class ModelTestCase<T extends Model> extends TestCase {
  protected T model;

  protected ModelFactory modelFactory;

  protected final void setUp() throws Exception {

    modelFactory = new DefaultModelFactory();
    model = createModel();

    onModelSetUp();
  }

  protected abstract T createModel();

  protected void onModelSetUp() throws Exception {
  }

  protected final void tearDown() throws Exception {
    onModelTearDown();
  }

  protected void onModelTearDown() throws Exception {
  }
}
