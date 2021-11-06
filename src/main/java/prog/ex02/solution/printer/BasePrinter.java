package prog.ex02.solution.printer;

import java.awt.print.Paper;
import java.util.ArrayList;
import java.util.List;

import prog.ex02.exercise.printer.Document;
import prog.ex02.exercise.printer.Printer;

public abstract class BasePrinter implements Printer {
  public int paperValue = 0;
  public int numberOfCopies = 0;
  public String currentPrinter;
  public boolean currentDuplex;
  public boolean currentColor;

  /**
   * Prints the given document.
   *
   * @param document document to print
   * @param duplex flag to signal if the document should be printed two-sided
   * @return false if
   *     <ul>
   *       <li>there is not enough paper in the paper tray
   *       <li>if duplex is required but not provided by the printer
   *       <li>if it is a color document but the printer cannot provide color
   *     </ul>
   *     otherwise true
   */
  @Override
  public boolean print(final Document document, final boolean duplex) {
    if (document == null) {
      return false;
    }
    if ((this.hasDuplex() == true && getNumberOfSheetsOfPaper() < (document.getPages() / 2))
        || (this.hasDuplex() == false && getNumberOfSheetsOfPaper() < document.getPages())) {
      return false;
    }
    if (duplex == true && this.hasDuplex() == false) {
      return false;
    } else {
      if (document.isColor() == true && this.hasColor() == false) {
        return false;
      } else {
        if (this.hasDuplex()) {
          if (document.getPages() % 2 != 0) {
            paperValue = paperValue - ((document.getPages() + 1) / 2);
          } else {
            paperValue = paperValue - document.getPages() / 2;
          }
        } else {
          paperValue = paperValue - document.getPages();
        }
        document.hasBeenPrinted();
        return true;
      }
    }
  }

  /**
   * Getter for property duplex.
   *
   * @return true if the printer is able to print two-sided, otherwise false.
   */
  @Override
  public boolean hasDuplex() {
    if (currentDuplex == true) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Getter for property name.
   *
   * @return name of the printer.
   */
  @Override
  public String getName() {
    return currentPrinter;
  }

  /**
   * Adds paper to the paper tray.
   *
   * @param numberOfSheets number of sheets of paper to be added. This number must be greater-equals
   *     zero
   * @return true if the number is greater-equals zero, otherwise false
   */
  @Override
  public boolean addPaper(final int numberOfSheets) {
    if (numberOfSheets >= 0) {
      paperValue = paperValue + numberOfSheets;
      return true;
    }
    return false;
  }

  /**
   * Getter for property numberOfSheetsOfPaper.
   *
   * @return returns the number of sheets of paper in the paper tray.
   */
  @Override
  public int getNumberOfSheetsOfPaper() {
    return paperValue;
  }
}
