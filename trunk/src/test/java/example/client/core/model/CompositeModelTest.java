
package example.client.core.model;

import junit.framework.TestCase;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;

/**
 * @author Misha Gridnev
 */
public class CompositeModelTest extends TestCase {

  public void testAddingDuplicateChild() {
    CompositeModel model = new CompositeModel() {
    };

    String id = "child";

    model.add(id, EasyMock.<Model>createMock(Model.class));

    try {
      model.add(id, EasyMock.<Model>createMock(Model.class));
      fail("Must fail with duplicate ID exception");
    } catch (IllegalArgumentException e) {
    }
  }

  public void testReset() {
    IMocksControl control = EasyMock.createControl();

    Model child = control.createMock(Model.class);
    Model child2 = control.createMock(Model.class);

    // Create complex model
    TwoChildModel model = new TwoChildModel(child, child2);

    // Record the behavior
    child.reset();
    child2.reset();

    control.replay();

    // Make the call and verify
    model.reset();
    control.verify();
  }

  public void testCopyFrom() {
    IMocksControl control = EasyMock.createControl();

    Model sourceChild = control.createMock(Model.class);
    SingleChildModel source = new SingleChildModel(sourceChild);

    Model targetChild = control.createMock(Model.class);
    Model targetChild2 = control.createMock(Model.class);

    TwoChildModel target = new TwoChildModel(targetChild, targetChild2);

    // Record the behavior
    targetChild.copyFrom(sourceChild);

    control.replay();

    // Make the call and verify
    target.copyFrom(source);
    control.verify();
  }

  /**
   * A complex model with one child
   */
  class SingleChildModel extends CompositeModel {

    SingleChildModel(Model child) {
      add("child", child);
    }
  }

  /**
   * A complex model with two children.
   */
  class TwoChildModel extends SingleChildModel {

    TwoChildModel(Model child, Model child2) {
      super(child);

      add("child2", child2);
    }
  }
}
