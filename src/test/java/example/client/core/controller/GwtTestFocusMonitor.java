
package example.client.core.controller;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.TextBox;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * @author Misha Gridnev
 */
public class GwtTestFocusMonitor extends GwtControllerTestBase {

  public void testTimerControl() {
    // Override the timer to record the calls
    int interval = 500;
    final List<Command> actualHistory = new ArrayList<Command>();
    final FocusMonitor monitor = new FocusMonitor(interval, null) {
      protected Timer createTimer(Runnable runnable) {
        return new Timer() {
          public void run() {
          }

          public void scheduleRepeating(int interval) {
            actualHistory.add(new Command(Command.Code.SCHEDULE, interval));
          }

          public void cancel() {
            actualHistory.add(new Command(Command.Code.CANCEL));
          }
        };
      }
    };

    // Fake focus gain followed by focus loss
    final TextBox textBox = new TextBox();

    monitor.onFocus(textBox);
    monitor.onLostFocus(textBox);

    // Make sure the timer was started and stopped
    final List<Command> expectedHistory = Arrays.asList(
        new Command(Command.Code.SCHEDULE, interval),
        new Command(Command.Code.CANCEL));

    assertEquals("Unexpected command history", expectedHistory, actualHistory);
  }

  public void testRunnableCalled() {
    final boolean[] called = {false};
    Runnable runnable = new Runnable() {
      public void run() {
        called[0] = true;
      }
    };

    FocusMonitor monitor = new FocusMonitor(0, runnable);
    monitor.timer.run();

    assertTrue("Runnable not called", called[0]);
  }

  static class Command {

    final Code code;
    final Object[] args;

    Command(Code code, Object... args) {
      this.code = code;
      this.args = args;
    }

    public boolean equals(Object obj) {
      return obj instanceof Command && code == ((Command) obj).code &&
             Arrays.equals(args, ((Command) obj).args);
    }

    public String toString() {
      StringBuffer sb = new StringBuffer();
      sb.append(code);

      for (Object arg : args) {
        sb.append(" ,");
        sb.append(arg);
      }

      return sb.toString();
    }

    enum Code {
      SCHEDULE, CANCEL
    }
  }
}
