package example.client.color.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * {@link example.client.color.service.ColorRemoteService} twin for use by GWT classes.
 */
public interface ColorRemoteServiceAsync {

  /**
   * Returns color RGB value in hex from its canonical value.
   *
   * @param canonical   canonical color, e.g., {@code "red"}
   * @param rgbCallback a callback to receive canonical name RGB equivalent, e.g., {@code "FF0000"}
   *                    or if canonical name is invalid, an empty string
   */
  void getRGB(String canonical, AsyncCallback<String> rgbCallback);


  /**
   * Returns canonical name corresponding to an RGB color.
   *
   * @param rgb               RGB triplet, e.g., {@code "FF0000"}
   * @param canonicalCallback a callback to receive canonical name, e.g., {@code "red"} or if RGB
   *                          triplet if there is no such name
   */
  void getCanonicalName(String rgb, AsyncCallback<String> canonicalCallback);
}
