package prog.extra.exercise.soundbar.logic;

public class ListenerAlreadyAddedException extends RuntimeException {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ListenerAlreadyAddedException.class);

  public ListenerAlreadyAddedException(final String message) {
    super(message);
  }
}
