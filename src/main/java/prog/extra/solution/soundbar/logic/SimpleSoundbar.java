package prog.extra.solution.soundbar.logic;

import java.io.IOException;
import java.nio.file.Path;
import prog.extra.exercise.soundbar.logic.ListenerAlreadyAddedException;
import prog.extra.exercise.soundbar.logic.ListenerNotKnownException;
import prog.extra.exercise.soundbar.logic.NoValidSoundbarConfigurationException;
import prog.extra.exercise.soundbar.logic.Slot;
import prog.extra.exercise.soundbar.logic.SlotNotSetException;
import prog.extra.exercise.soundbar.logic.Sound;
import prog.extra.exercise.soundbar.logic.Soundbar;
import prog.extra.exercise.soundbar.logic.SoundbarListener;


public class SimpleSoundbar implements Soundbar {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SimpleSoundbar.class);

  @Override
  public String getTitle() {
    return null;
  }

  @Override
  public void setTitle(final String title) throws IllegalArgumentException {

  }

  @Override
  public int getSize() {
    return 0;
  }

  @Override
  public void setSize(final int size) throws IllegalArgumentException {

  }

  @Override
  public Slot[] getSlots() {
    return new Slot[0];
  }

  @Override
  public void setSlot(final int index, final Sound sound) throws IndexOutOfBoundsException,
          IllegalArgumentException {

  }

  @Override
  public void clearSlot(final int index) throws IndexOutOfBoundsException {

  }

  @Override
  public void playSlot(final int index) throws IndexOutOfBoundsException, SlotNotSetException {

  }

  @Override
  public void addSoundbarListener(final SoundbarListener listener)
          throws IllegalArgumentException, ListenerAlreadyAddedException {

  }

  @Override
  public void removeSoundbarListener(final SoundbarListener listener)
          throws IllegalArgumentException, ListenerNotKnownException {

  }

  @Override
  public void save(final Path path, final Format format) throws IOException {

  }

  @Override
  public void load(final Path path) throws IOException, NoValidSoundbarConfigurationException {

  }
}
