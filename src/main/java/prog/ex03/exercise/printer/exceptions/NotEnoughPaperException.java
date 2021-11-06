package prog.ex03.exercise.printer.exceptions;

public class NotEnoughPaperException extends Exception {
  private int numberMissingPages;

  public NotEnoughPaperException(final String message, final int numberMissingPages) {
    super(message);
    this.numberMissingPages = numberMissingPages;
  }

  public int getNumberMissingPages() {
    return numberMissingPages;
  }
}
