
package example.client.core.model;

import junit.framework.TestCase;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;

/**
 * @author Misha Gridnev
 */
public class DefaultTextModelTest extends TestCase {

  private DefaultTextModel model;

  protected void setUp() throws Exception {
    super.setUp();
    model = new DefaultTextModel();
  }

  public void testValueAccess() {

    // Ensure model starts with an empty string
    assertEquals("", model.getText());

    // And update works properly
    String text = "Some text";
    model.setText(text);

    assertEquals(text, model.getText());
  }

  public void testReset() {

    // Populate the model then immedietly reset it
    model.setText("Some text");
    model.reset();

    // Good as new!
    assertEquals("", model.getText());
  }

  public void testCopy() {
    String text = "Some text";
    model.setText(text);

    TextModel copy = new DefaultTextModel();
    copy.copyFrom(model);

    assertEquals(text, copy.getText());
  }
  
  public void testEventsFired() {
    ModelChangeListener listener = createMock(ModelChangeListener.class);
    model.addModelChangeListener(listener);

    // Initial change fires an event
    listener.modelChanged(ModelChangeEventEquals.sourceEq(model));
    replay(listener);

    final String text = "Some text";
    model.setText(text);

    verify(listener);

    // If value is unchanged, nothing is fired
    reset(listener);
    replay(listener);

    model.setText(text);

    verify(listener);

    // However updating the value fires an event
    reset(listener);
    listener.modelChanged(ModelChangeEventEquals.sourceEq(model));
  }
}
