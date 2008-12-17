
package example.client.core.controller;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.Widget;

/**
 * A focus monitor may be attached to a {@link Widget} to monitor for focus changes. When the widget
 * receives focus, the monitor start a timer that executes a {@link Runnable} at the specified
 * intervals.
 *
 * @author Misha Gridnev
 */
public class FocusMonitor implements FocusListener {

  /**
   * Interval in milliseconds the timer will tick.
   */
  private final int interval;

  /**
   * Timer.
   */
  final Timer timer;

  /**
   * Creates a focus monitor. On focus, the monitor will kick off the periodic timer that will tick
   * every {@code interval} milliseconds executing the given {@code runnable}
   *
   * @param interval interval in milliseconds to execute the {@code runnable} when focus is gained
   * @param runnable runnable to execute every {@code interval} milliseconds
   */
  public FocusMonitor(int interval, final Runnable runnable) {
    this.interval = interval;

    this.timer = createTimer(runnable);
  }

  /**
   * Creates a timer. The method is primarily intented to be overriden by unit tests.
   *
   * @param runnable runnable to be executed whenver timer expires
   * @return a timer
   */
  Timer createTimer(final Runnable runnable) {
    return new Timer() {
      public void run() {
        runnable.run();
      }
    };
  }

  public void onFocus(Widget widget) {
    timer.scheduleRepeating(interval);
  }

  public void onLostFocus(Widget widget) {
    timer.cancel();
  }
}
