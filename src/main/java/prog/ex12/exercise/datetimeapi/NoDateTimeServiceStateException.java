package prog.ex12.exercise.datetimeapi;

public class NoDateTimeServiceStateException extends Exception {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(NoDateTimeServiceStateException.class);

  public NoDateTimeServiceStateException(final String message) {
    super(message);
  }

  public NoDateTimeServiceStateException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
