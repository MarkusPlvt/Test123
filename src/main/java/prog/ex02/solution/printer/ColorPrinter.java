package prog.ex02.solution.printer;

public class ColorPrinter extends BasePrinter {

  /**
   * Constructor of ColorPrinter.
   *
   * @param string printer definition
   * @param b true or false for duplex
   */
  public ColorPrinter(String string, boolean b) {
    super();
    currentPrinter = string;
    currentColor = true;
    currentDuplex = b;
  }

  /**
   * hasColor says if the printer got color or not.
   *
   * @return true for color
   */
  @Override
  public boolean hasColor() {
    return true;
  }
}
