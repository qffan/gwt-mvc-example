package example.client.color.service;

import com.google.gwt.user.client.rpc.RemoteService;

/**
 * Color service converts between a canonical color name and its RGB representation.
 */
public interface ColorRemoteService extends RemoteService {

  /**
   * Returns color RGB value in hex from its canonical value.
   *
   * @param canonical canonical color, e.g., {@code "red"}
   * @return its RGB equivalent, e.g., {@code "FF0000"} or if canonical name is invalid, an empty
   *         string
   */
  String getRGB(String canonical);

  /**
   * Returns canonical name corresponding to an RGB color.
   *
   * @param rgb RGB triplet, e.g., {@code "FF0000"}
   * @return canonical name, e.g., {@code "red"} or if RGB triplet if there is no such name
   */
  String getCanonicalName(String rgb);
}
