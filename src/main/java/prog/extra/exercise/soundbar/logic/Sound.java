package prog.extra.exercise.soundbar.logic;

import java.io.Serializable;

/**
 * Represents a sound in a soundbar.
 */
public class Sound implements Serializable {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(Sound.class);

  private String title;
  private String filename;

  public Sound(final String title, final String filename) {
    this.title = title;
    this.filename = filename;
  }

  /**
   * So called copy constructor. Is better suited than clone().
   *
   * @param sound sound to be copied from
   */
  public Sound(Sound sound) {
    if (sound == null) {
      throw new IllegalArgumentException("sound is null reference.");
    }
    this.title = sound.getTitle();
    this.filename = sound.getFilename();
  }

  public String getTitle() {
    return title;
  }

  public String getFilename() {
    return filename;
  }

  @Override
  public String toString() {
    return "Sound{title='" + title + '\'' + ", filename='" + filename + '\'' + '}';
  }
}
