package environment.testprograms;

import java.io.InputStream;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HelloWorldWithJavaFx extends Application {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(HelloWorldWithJavaFx.class);

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(final Stage primaryStage) throws Exception {
    StackPane root = new StackPane();
    InputStream is = getClass().getClassLoader()
            .getResourceAsStream("images/javaFX-Handwritten.png");
    Image image = new Image(is);
    ImageView imageView = new ImageView(image);
    root.getChildren().add(imageView);
    primaryStage.setTitle("Hello JavaFX World");
    primaryStage.setScene(new Scene(root));
    primaryStage.show();

  }
}
