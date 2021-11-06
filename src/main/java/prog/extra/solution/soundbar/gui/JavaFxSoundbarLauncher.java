package prog.extra.solution.soundbar.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFxSoundbarLauncher extends Application {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(JavaFxSoundbarLauncher.class);

  @Override
  public void start(final Stage stage) throws Exception {
    FxSoundbar controller = new FxSoundbar();
    stage.setScene(new Scene(controller, 800, 600));
    stage.show();
  }
}
