package prog.ex02.solution.printer;

import java.util.ArrayList;
import java.util.List;
import prog.ex02.exercise.printer.Printer;
import prog.ex02.exercise.printer.PrinterManager;

/**
 * This is the SimplePrinterManager class. It coordinates prints for printers.
 *
 * @author Markus Kübler 207273
 * @datum 12.10.2020
 * @version 1.0
 */
public class SimplePrinterManager implements PrinterManager {

  public static List<Printer> allPrinters = new ArrayList<>();

  /**
   * returns a printer with the given name.
   *
   * @param name name of the requested printer.
   * @return Printer with the given name, otherwise null.
   */
  @Override
  public  Printer getPrinter(final String name) {
    if (name == null || name.contains(" ")) {
      return null;
    }
    for (Printer allPrinter : allPrinters) {
      if (allPrinter.getName().equals(name)) {
        return allPrinter;
      }
    }
    return null;
  }

  /**
   * Returns all printers known by the PrinterManager.
   *
   * @return List of Printer objects. If no printer is known by the PrinterManager, it return an
   *     empty list.
   */
  @Override
  public List<Printer> getAllPrinters() {
    return allPrinters;
  }

  /**
   * Adds a printer to the PrinterManager. The name of the printer must contain printable characters
   * and must be unique within the PrinterManager.
   *
   * @param printer printer to be added to the PrinterManager
   * @return false if
   *     <ul>
   *       <li>the parameter is a null reference
   *       <li>the name of the printer is already known within the PrinterManager
   *       <li>the name of the printer does not contain printable characters
   *     </ul>
   *     otherwise true.
   */
  @Override
  public boolean addPrinter(final Printer printer) {
    if (printer == null || printer.getName() == "" || printer.getName().contains(" ")) {
      return false;
    } else if (allPrinters.contains(printer)) {
      return false;
    } else if (printer.getName().contains("!")) {
      return false;
    } else {
      allPrinters.add(printer);
      return true;
    }
  }

  /**
   * Removes the printer with the given name from the PrinterManager.
   *
   * @param name name of the Printer to be removed.
   * @return false if
   *     <ul>
   *       <li>the parameter is a null reference
   *       <li>the printer with the given name does not exist within the PrinterManager
   *     </ul>
   *     otherwise true.
   */
  @Override
  public boolean removePrinter(final String name) {
    if (name == null) {
      return false;
    } else {
      for (int i = 0; i <= allPrinters.size(); i++) {
        if (allPrinters.get(i).getName().equals(name)) {
          allPrinters.remove(i);
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Returns the number of ColorPrinters within the PrinterManager.
   *
   * @return number of ColorPrinters within the PrinterManager.
   */
  @Override
  public int getNumberOfColorPrinters() {
    int colorPrinters = 0;
    for (Printer allPrinter : allPrinters) {
      if (allPrinter.hasColor()) {
        colorPrinters++;
      }
    }
    return colorPrinters;
  }

  /**
   * Returns the number of Black-and-White-Printers within the PrinterManager.
   *
   * @return number of BwPrinters within the PrinterManager.
   */
  @Override
  public int getNumberOfBwPrinters() {
    int bwPrinters = 0;
    for (Printer allPrinter : allPrinters) {
      if (!allPrinter.hasColor()) {
        bwPrinters++;
      }
    }
    return bwPrinters;
  }
}
