package prog.extra.soundbar.logic;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import prog.extra.exercise.soundbar.logic.ListenerAlreadyAddedException;
import prog.extra.exercise.soundbar.logic.ListenerNotKnownException;
import prog.extra.exercise.soundbar.logic.Soundbar;
import prog.extra.exercise.soundbar.logic.SoundbarListener;
import prog.extra.solution.soundbar.logic.SimpleSoundbar;

import static org.junit.Assert.fail;


public class TestSoundbarBadCases {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(TestSoundbarBadCases.class);
  private Soundbar soundbar;

  @Before
  public void setup() {
    soundbar = new SimpleSoundbar();
  }

  @Test(expected = IllegalArgumentException.class) @Ignore
  public void testAddANullSound() {
    soundbar.setSlot(3, null);
    fail("It should not be possible to set a null reference as a sound.");
  }

  @Test(expected = IllegalArgumentException.class) @Ignore
  public void testAddingNullListener(){
    soundbar.addSoundbarListener(null);
    fail("It should not be possible to set a null reference as a listener.");
  }

  @Test(expected = IllegalArgumentException.class) @Ignore
  public void testRemovingNullListener(){
    soundbar.removeSoundbarListener(null);
    fail("It should not be possible to remove a null reference as a listener.");
  }

  @Test(expected = ListenerAlreadyAddedException.class) @Ignore
  public void testAddSameListenerTwice() {
    SoundbarListener listener = new SimpleSoundbarListener();
    soundbar.addSoundbarListener(listener);
    soundbar.addSoundbarListener(listener);
    fail("It should not be possible to add the same listener twice.");
  }

  @Test(expected = ListenerNotKnownException.class) @Ignore
  public void testRemovingANotRegisteredListener() {
    SoundbarListener listener = new SimpleSoundbarListener();
    soundbar.removeSoundbarListener(listener);
    fail("It should not be possible to remove a listener which is not known.");
  }
}
