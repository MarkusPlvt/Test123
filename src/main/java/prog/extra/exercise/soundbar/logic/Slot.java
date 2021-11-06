package prog.extra.exercise.soundbar.logic;

import java.io.Serializable;

/**
 * Represents an entry in a soundbar.
 */
public class Slot implements Serializable {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(Slot.class);

  private int id;
  private Sound sound;

  public Slot(final int id, final Sound sound) {
    this.id = id;
    this.sound = sound;
  }

  /**
   * So called copy constructor. Is better suited than clone(). Makes a deep copy.
   *
   * @param slot slot to be copied from
   */
  public Slot(Slot slot) {
    if (slot == null) {
      throw new IllegalArgumentException("slot is null reference.");
    }
    this.id = slot.getId();
    this.sound = new Sound(slot.getSound());
  }

  public int getId() {
    return id;
  }

  public void setId(final int id) {
    this.id = id;
  }

  public Sound getSound() {
    return sound;
  }

  public void setSound(final Sound sound) {
    this.sound = sound;
  }

  @Override
  public String toString() {
    return "Slot{id=" + id + ", sound=" + sound + '}';
  }
}
