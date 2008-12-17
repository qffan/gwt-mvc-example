
package example.client.core.model;

import junit.framework.TestCase;

import static org.easymock.EasyMock.createControl;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import org.easymock.IMocksControl;

/**
 * Tests adding and removing listeners and firing of events implemented by {@link
 * example.client.core.model.AbstractModel}.
 *
 * @author Misha Gridnev
 */
public class AbstractModelEventFiringTest extends TestCase {

  /**
   * Model being exercised.
   */
  private AbstractModel model;

  protected void setUp() throws Exception {
    super.setUp();
    model = new AbstractModel() {
      public void reset() {
      }

      public void copyFrom(Model source) {
      }
    };
  }

  public void testSingleListenerAdded() {
    verifyListenerAdded(1, 1);
  }

  public void testSingleListenerAddedTwice() {
    verifyListenerAdded(1, 2);
  }

  public void testSeveralListenersAddedSeveralTimes() {
    verifyListenerAdded(3, 2);
  }

  private void verifyListenerAdded(int listenerCount, int timesToAdd) {
    IMocksControl control = createControl();

    // Add one or more listener
    while (listenerCount-- > 0) {
      ModelChangeListener listener = control.createMock(ModelChangeListener.class);

      // Add each listener one or more times
      for (int added = 0; added < timesToAdd; added++) {
        model.addModelChangeListener(listener);
      }

      // Ensure each added listener got called exactly once
      listener.modelChanged(ModelChangeEventEquals.sourceEq(model));
    }

    // Freeze
    control.replay();

    // Fire a single event
    model.fireModelChangeEvent();

    // Check
    control.verify();
  }

  public void testListenerRemoved() {
    verifyListenerRemoved(1);
  }

  public void testListenerRemovedTwice() {
    verifyListenerRemoved(2);
  }

  private void verifyListenerRemoved(int timesToRemove) {
    ModelChangeListener listener = createMock(ModelChangeListener.class);
    model.addModelChangeListener(listener);

    while (timesToRemove-- > 0) {
      model.removeModelChangeListener(listener);
    }

    replay(listener);

    model.fireModelChangeEvent();

    verify(listener);
  }

  /**
   * Runs the following test scenario:<ul> <li>Add two listeners <li>Remove the first one <li>Fire
   * an event <li>Ensure only the second listener got the event </ul>
   */
  public void testMultipleListeners() {
    IMocksControl control = createControl();

    ModelChangeListener listener = control.createMock(ModelChangeListener.class);
    model.addModelChangeListener(listener);

    ModelChangeListener listener2 = control.createMock(ModelChangeListener.class);
    model.addModelChangeListener(listener2);

    model.removeModelChangeListener(listener);

    listener2.modelChanged(ModelChangeEventEquals.sourceEq(model));

    control.replay();

    model.fireModelChangeEvent();

    control.verify();
  }
}
