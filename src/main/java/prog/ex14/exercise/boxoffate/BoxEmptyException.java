package prog.ex14.exercise.boxoffate;

public class BoxEmptyException extends Exception {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(BoxEmptyException.class);

  public BoxEmptyException() {
  }

  public BoxEmptyException(final String message) {
    super(message);
  }
}
