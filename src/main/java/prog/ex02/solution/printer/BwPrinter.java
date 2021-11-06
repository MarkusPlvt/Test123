package prog.ex02.solution.printer;

public class BwPrinter extends BasePrinter {

  /**
   * Constructor of BwPrinter.
   *
   * @param string printer definition
   * @param b true or false for duplex
   */
  public BwPrinter(final String string, final boolean b) {
    super();
    currentPrinter = string;
    currentColor = false;
    currentDuplex = b;
  }

  /**
   * hasColor says if the printer got color or not.
   *
   * @return false for color
   */
  @Override
  public boolean hasColor() {
    return false;
  }
}
