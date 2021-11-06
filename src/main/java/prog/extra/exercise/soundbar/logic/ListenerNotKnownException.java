package prog.extra.exercise.soundbar.logic;

public class ListenerNotKnownException extends RuntimeException {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ListenerNotKnownException.class);

  public ListenerNotKnownException(final String message) {
    super(message);
  }
}
