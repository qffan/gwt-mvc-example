
package example.client.core.model;

import junit.framework.TestCase;

import org.easymock.EasyMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;

/**
 * @author Misha Gridnev
 */
public class DefaultActionModelTest extends TestCase {
  public void testDelegation() {
    ActionModel model = new DefaultActionModel();

    model.execute(); // ensure nothing blows up

    // Install an action
    Action action = EasyMock.createMock(Action.class);
    model.setAction(action);

    action.execute(); // ensure the call is delegated
    replay(action);
    
    model.execute();

    verify(action);

    // Remove an action
    model.setAction(null);

    reset(action);
    replay(action);

    model.execute();

    // Ensure nohting is delegated
    verify(action);
  }
}
