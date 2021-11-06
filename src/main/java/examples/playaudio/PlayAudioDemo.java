package examples.playaudio;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class PlayAudioDemo extends Application {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(PlayAudioDemo.class);

  @Override
  public void start(final Stage primaryStage) throws Exception {
    primaryStage.setScene(new Scene(new SimpleAudioPlayer(), 300, 300));
    primaryStage.show();
  }
}
