package prog.extra.soundbar.logic;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import prog.extra.exercise.soundbar.logic.Slot;
import prog.extra.exercise.soundbar.logic.SlotNotSetException;
import prog.extra.exercise.soundbar.logic.Sound;
import prog.extra.exercise.soundbar.logic.Soundbar;
import prog.extra.solution.soundbar.logic.SimpleSoundbar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


public class TestSoundbarGoodCases {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(TestSoundbarGoodCases.class);
  public static final String THIS_IS_MY_SOUNDBAR = "This is my soundbar";
  private Soundbar soundbar;
  private SimpleSoundbarListener listener1;
  private SimpleSoundbarListener listener2;

  @Before
  public void setup() {
    soundbar = new SimpleSoundbar();
    soundbar.setTitle(THIS_IS_MY_SOUNDBAR);
    listener1 = new SimpleSoundbarListener();
    listener2 = new SimpleSoundbarListener();

    soundbar.setSlot(1, new Sound("sound-1","testfiles/sound-one.mp3"));
    soundbar.setSlot(3, new Sound("sound-3","testfiles/sound-three.mp3"));
    soundbar.setSlot(2, new Sound("sound-2","testfiles/sound-two.mp3"));

    soundbar.addSoundbarListener(listener1);
    soundbar.addSoundbarListener(listener2);
  }

  @Test @Ignore
  public void testEmptySoundbar(){
    assertEquals("Size of Soundbar should be 10 but is "+soundbar.getSize()+ " .", 10, soundbar.getSize());
    Slot[] slots = soundbar.getSlots();
    assertNotNull("Slots if Soundbar should not be null.", slots);
    assertEquals("There should be 10 slots but there are "+slots.length+" .", 10, slots.length);

  }

  @Test @Ignore
  public void testSoundBarContentGenerator() {
    Soundbar soundbar1 = new SimpleSoundbar();
    SoundbarContentGenerator.fillSoundbar(soundbar1);
    assertEquals("Size of soundbar should be 10 but is "+soundbar1.getSize()+" .", 10,
            soundbar1.getSize());
    Slot[] slots = soundbar1.getSlots();
    assertNull("This slot should be null", slots[0]);
    assertNull("This slot should be null", slots[2]);
    assertNull("This slot should be null", slots[4]);
    assertNotNull("This slot should not be null", slots[1]);
    assertNotNull("This slot should not be null", slots[3]);
  }


  @Test @Ignore
  public void reduceSizeButKeepContent() {
    logger.info("Try reducing the size of the soundbar. The  slots 1-3 should be still set.");
    soundbar.setSize(5);
    Slot[] slots = soundbar.getSlots();
    assertNull("Slot should be empty", slots[0]);
    assertNotNull("Slot should be set.", slots[1]);
    assertNotNull("Slot should be set.", slots[2]);
    assertNotNull("Slot should be set.", slots[3]);
    assertNull("Slot should be empty", slots[4]);
  }


  @Test @Ignore
  public void reduceSizeButKeepTitle() {
    logger.info("Try reducing the size of the soundbar. The title should be still set.");
    soundbar.setSize(5);
    assertEquals("The title should be the same", THIS_IS_MY_SOUNDBAR, soundbar.getTitle());
  }


  @Test @Ignore
  public void testIfListenersGetCalled() throws SlotNotSetException {
    logger.info("Register two listeners with the soundbar and play one sound. Both listeners " +
            "should get called.");
    soundbar.playSlot(3);
    assertEquals("Listener 1 should have been called.", 1, listener1.getCallbackCounter());
    assertEquals("Listener 2 should have been called.", 1, listener2.getCallbackCounter());
  }
}
