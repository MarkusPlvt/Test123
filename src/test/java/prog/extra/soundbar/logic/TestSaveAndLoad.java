package prog.extra.soundbar.logic;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import prog.extra.exercise.soundbar.logic.NoValidSoundbarConfigurationException;
import prog.extra.exercise.soundbar.logic.Slot;
import prog.extra.exercise.soundbar.logic.Soundbar;
import prog.extra.solution.soundbar.logic.SimpleSoundbar;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestSaveAndLoad {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(TestSaveAndLoad.class);
  private SimpleSoundbar soundbar;

  @Before
  public void setup() {
    soundbar = new SimpleSoundbar();
  }

  @Test @Ignore
  public void testBinarySaveAndLoad() throws IOException, NoValidSoundbarConfigurationException {
    Path path = Path.of("sf.cfg.bin");
    SoundbarContentGenerator.fillSoundbar(soundbar);
    soundbar.save(path, Soundbar.Format.BINARY);
    soundbar.clearSlot(1);
    assertTrue("Path to file does not exist.", path.toFile().exists());
    soundbar.load(path);
    Slot[] slots = soundbar.getSlots();
    assertNotNull("Loaded file was null.", slots[1]);

  }


  @Test @Ignore
  public void testTextualSaveAndLoad() throws IOException, NoValidSoundbarConfigurationException {
    Path path = Path.of("sf.cfg.txt");
    SoundbarContentGenerator.fillSoundbar(soundbar);
    soundbar.save(path, Soundbar.Format.TEXT);
    soundbar.clearSlot(1);
    assertTrue("Path to file does not exist.", path.toFile().exists());
    soundbar.load(path);
    Slot[] slots = soundbar.getSlots();
    assertNotNull("Loaded file was null.", slots[1]);
  }
}
