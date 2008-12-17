package example.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import example.client.color.service.ColorRemoteService;

/**
 * The implementation of the color service.
 */
public class ColorRemoteServiceImpl extends RemoteServiceServlet implements ColorRemoteService {

  public String getRGB(String canonical) {
    return "";
  }

  public String getCanonicalName(String rgb) {
    return rgb;
  }
}
