package prog.extra.soundbar.logic;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import prog.extra.exercise.soundbar.logic.Slot;
import prog.extra.exercise.soundbar.logic.Sound;
import prog.extra.exercise.soundbar.logic.Soundbar;
import prog.extra.solution.soundbar.logic.SimpleSoundbar;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;


public class TestDeepCopy {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(TestDeepCopy.class);
  private Soundbar soundbar;

  @Before
  public void setup() {
    soundbar = new SimpleSoundbar();
    soundbar.setSlot(1, new Sound("sound-1","testfiles/sound-one.mp3"));
    soundbar.setSlot(3, new Sound("sound-3","testfiles/sound-three.mp3"));
    soundbar.setSlot(2, new Sound("sound-2","testfiles/sound-two.mp3"));
  }

  @Test @Ignore
  public void testIfGetSlotsProduceCopy() {
    logger.info("Try to change the slot array within the soundbar. This should not be possible.");
    Slot[] slots = soundbar.getSlots();
    assertNotNull(slots);
    Slot slot = slots[3];
    assertNotNull(slot);
    slots[3] = null;
    Slot[] slotsAgain = soundbar.getSlots();
    assertNotNull(slotsAgain);
    Slot slotAgain = slotsAgain[3];
    assertNotNull(slotAgain);
  }

  @Test @Ignore
  public void testIfGetSlotsProduceDeepCopy() {
    logger.info("Try to change the sound of a slot within the soundbar. This should not " +
            "be possible.");
    Slot[] slots = soundbar.getSlots();
    Slot slot = slots[3];
    Sound sound = slot.getSound();
    slot.setSound(new Sound("Trallala", "Trallala"));
    Slot[] slotsAgain = soundbar.getSlots();
    Slot slotAgain = slotsAgain[3];
    assertNotEquals("Trallala", slotAgain.getSound().getTitle());
  }
}
