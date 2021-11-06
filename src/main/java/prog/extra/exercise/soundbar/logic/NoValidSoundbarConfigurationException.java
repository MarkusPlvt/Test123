package prog.extra.exercise.soundbar.logic;

public class NoValidSoundbarConfigurationException extends Exception {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(NoValidSoundbarConfigurationException.class);

  public NoValidSoundbarConfigurationException(final String message) {
    super(message);
  }

  public NoValidSoundbarConfigurationException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
