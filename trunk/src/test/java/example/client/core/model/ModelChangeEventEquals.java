
package example.client.core.model;

import org.easymock.IArgumentMatcher;
import org.easymock.EasyMock;
import org.apache.commons.lang.ObjectUtils;

/**
 * EasyMock matcher for {@link ModelChangeEvent}s. Currently, the matcher verifies the event source
 * only.
 *
 * @author Misha Gridnev
 */
class ModelChangeEventEquals implements IArgumentMatcher {

  final ModelChangeEvent event;

  /**
   * Call inside {@link EasyMock#expect} when matching {@link ModelChangeEvent} argument. The method
   * checks only the source of the event
   *
   * @param source event source
   * @return null
   */
  public static ModelChangeEvent sourceEq(Model source) {
    EasyMock.reportMatcher(new ModelChangeEventEquals(new ModelChangeEvent(source)));
    return null;
  }

  private ModelChangeEventEquals(ModelChangeEvent event) {
    this.event = event;
  }

  public boolean matches(Object o) {
    return o instanceof ModelChangeEvent &&
           ObjectUtils.equals(event.getSource(), ((ModelChangeEvent) o).getSource());
  }

  public void appendTo(StringBuffer stringBuffer) {
    stringBuffer.append("Expected ModelChangeEvent source = ");
    stringBuffer.append(event.getSource());
  }
}
