package prog.extra.soundbar.logic;

import prog.extra.exercise.soundbar.logic.Sound;
import prog.extra.exercise.soundbar.logic.SoundbarListener;

public class SimpleSoundbarListener  implements SoundbarListener {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SimpleSoundbarListener.class);

  private int callbackCounter;

  @Override
  public void play(final Sound sound) {
    callbackCounter++;
  }

  public int getCallbackCounter() {
    return callbackCounter;
  }
}
