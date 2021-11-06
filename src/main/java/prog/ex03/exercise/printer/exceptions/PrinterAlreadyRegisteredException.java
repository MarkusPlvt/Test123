package prog.ex03.exercise.printer.exceptions;

public class PrinterAlreadyRegisteredException extends Exception {

  public PrinterAlreadyRegisteredException(final String message) {
    super(message);
  }
}
