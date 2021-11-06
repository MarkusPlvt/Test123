package examples.playaudio;

import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SimpleAudioPlayer extends Button {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SimpleAudioPlayer.class);

  private MediaPlayer player;

  public SimpleAudioPlayer() {
    setText("Play");
    setOnAction((event) -> playAudio());
  }

  private void playAudio() {
    Media media = new Media(PlayAudioDemo.class.getResource("/audio/sound-one.mp3").toString());
    player = new MediaPlayer(media);
    player.play();
  }
}
