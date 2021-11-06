package prog.extra.exercise.soundbar.logic;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Represents the soundbar logic. A Soundbar has slots. Theses slots can be set with Sound
 * objects. When a certain slot is selected to play the sound, the soundbar uses callbacks to
 * trigger the UI to play the sound.
 */
public interface Soundbar {

  /**
   * Default number of slots.
   */
  int DEFAULT_SIZE = 10;

  /**
   * Returns the title of the soundbar.
   *
   * @return title of the soundbar
   */
  String getTitle();

  /**
   * Sets the title of the soundbar.
   *
   * @param title title of the soundbar. The string must contain at minimum two readable characters.
   * @throws IllegalArgumentException if the title is a null reference or does not meet the
   *                                  specification
   */
  void setTitle(String title) throws IllegalArgumentException;

  /**
   * Returns the size (number of slots) of the soundbar.
   *
   * @return size of the soundbar.
   */
  public int getSize();

  /**
   * Sets the soundbar to a different size. This may enlarge or reduce the number of slots. Slots
   * which are already set, get copied into the enlarged or reduced soundbar if the index of the
   * slot is still valid.
   *
   * @param size new size of the soundbar
   * @throws IllegalArgumentException if size is lower 1
   */
  public void setSize(int size) throws IllegalArgumentException;

  /**
   * Returns an array of Slot objects. The returned array is a deep copy of the internal state of
   * the soundbar.
   *
   * @return Array of Slots as a deep copy of the internal state.
   */
  Slot[] getSlots();

  /**
   * Places a Sound into a slot. If the slot is occupied, the content gets overwritten.
   *
   * @param index index of the slot
   * @param sound sound to be placed into the slot
   * @throws IndexOutOfBoundsException if the index is not a valid index
   * @throws IllegalArgumentException  if the Sound object is not a valid Sound object
   */
  void setSlot(int index, Sound sound) throws IndexOutOfBoundsException, IllegalArgumentException;

  /**
   * Clears a slot.
   *
   * @param index slot to be cleared
   * @throws IndexOutOfBoundsException if the index is not a valid index
   */
  void clearSlot(int index) throws IndexOutOfBoundsException;

  /**
   * Triggers the SoundbarListeners to play the sound.
   *
   * @param index slot which sound shall be played
   * @throws IndexOutOfBoundsException if the index is not a valid index
   * @throws SlotNotSetException       if the slot is not occupied
   */
  void playSlot(int index) throws IndexOutOfBoundsException, SlotNotSetException;

  /**
   * Adds a SoundbarListener to the Soundbar. The listener gets triggered when a sound shall be
   * played.
   *
   * @param listener SoundbarListener
   * @throws IllegalArgumentException      if the SoundbarListener is a null reference
   * @throws ListenerAlreadyAddedException if the listener has already been added to the Soundbar
   */
  void addSoundbarListener(SoundbarListener listener) throws IllegalArgumentException,
          ListenerAlreadyAddedException;

  /**
   * Removes a SoundbarListener from the Soundbar.
   *
   * @param listener listener to be removed.
   * @throws IllegalArgumentException  if the listener is a null reference
   * @throws ListenerNotKnownException if the listener is not known within the Soundbar.
   */
  void removeSoundbarListener(SoundbarListener listener) throws IllegalArgumentException,
          ListenerNotKnownException;

  /**
   * Saves the soundbar configuration into a file identified by the given path.
   *
   * @param path path of the file
   * @param format TEXT, if the configuration should be saved in a text format to be inspected
   *               with a usual text editor or BINARY, if the format should only be inspected by
   *               soundbar software
   * @throws IOException if the file cannot be written or the path is not a valid path
   */
  void save(Path path, Format format) throws IOException;

  /**
   * Loads the soundbar configuration from a file identified by the given path.
   *
   * @param path path of the file
   * @throws IOException                           if the file does not exist or cannot be read
   * @throws NoValidSoundbarConfigurationException if the content of the file is no valid
   *                                               soundbar configuration
   */
  void load(Path path) throws IOException, NoValidSoundbarConfigurationException;

  enum Format {
    TEXT, BINARY
  }
}
