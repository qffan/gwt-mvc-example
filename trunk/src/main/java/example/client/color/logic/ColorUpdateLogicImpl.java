
package example.client.color.logic;

import example.client.color.model.ColorModel;

/**
 * An implementation of color update logic that copies the color to and from another color model.
 *
 * @author Misha Gridnev
 */
public class ColorUpdateLogicImpl implements ColorUpdateLogic {

  private final ColorModel master;

  /** Creates an instance of logic.
   *
   * @param master master color model
   */
  public ColorUpdateLogicImpl(ColorModel master) {
    this.master = master;
  }

  public void initColor(ColorModel model) {
    model.copyFrom(master);
  }

  public void saveColor(ColorModel model) {
    master.copyFrom(model);
  }
}
