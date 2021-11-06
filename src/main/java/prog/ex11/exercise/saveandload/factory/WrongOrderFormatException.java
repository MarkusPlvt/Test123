package prog.ex11.exercise.saveandload.factory;

public class WrongOrderFormatException extends Exception {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(WrongOrderFormatException.class);

  public WrongOrderFormatException() {
  }

  public WrongOrderFormatException(final String message) {
    super(message);
  }

  public WrongOrderFormatException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public WrongOrderFormatException(final Throwable cause) {
    super(cause);
  }
}
