package prog.extra.exercise.soundbar.logic;

public class SlotNotSetException extends Exception {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SlotNotSetException.class);

  public SlotNotSetException(final String message) {
    super(message);
  }
}
