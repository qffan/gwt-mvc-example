
package example.client.color.logic;

import example.client.color.model.ColorModel;

/**
 * The interface abstract the initialization and update logic. This is done to divorce the business
 * logic (however primitive it may seem) from the controller.
 *
 * @author Misha Gridnev
 */
public interface ColorUpdateLogic {

  /**
   * Initializes the given model from some data store, e.g., this call may pull the color values
   * from a database
   *
   * @param model model to be initialized
   */
  void initColor(ColorModel model);

  /**
   * Saves model changes to some data store, e.g., this call my update a database.
   *
   * @param model model to be saved
   */
  void saveColor(ColorModel model);
}

