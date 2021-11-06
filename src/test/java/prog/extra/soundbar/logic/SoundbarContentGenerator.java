package prog.extra.soundbar.logic;

import prog.extra.exercise.soundbar.logic.Sound;
import prog.extra.exercise.soundbar.logic.Soundbar;

public class SoundbarContentGenerator {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SoundbarContentGenerator.class);


  public static void fillSoundbar(Soundbar soundbar) {
    Sound sound1 = new Sound("first Title", "testfiles/sound-one.mp3");
    Sound sound3 = new Sound("third Title", "testfiles/sound-three.mp3");
    soundbar.setSlot(1, sound1);
    soundbar.setSlot(3, sound3);
  }
}
