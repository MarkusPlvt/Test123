package prog.ex15.exercise.welcomecountry.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** Main to launch the WelcomeToMyCountry content in a separate application. */
public class WelcomeLauncher extends Application {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(WelcomeLauncher.class);

  @Override
  public void start(final Stage stage) {
    FxWelcomeMain welcomeMain = new FxWelcomeMain();
    stage.setScene(new Scene(welcomeMain, 400, 400));
    stage.show();
  }
}
