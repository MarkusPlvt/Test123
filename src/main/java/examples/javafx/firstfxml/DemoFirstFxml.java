package examples.javafx.firstfxml;

import examples.javafx.helper.MockDataGenerator;
import examples.javafx.model.Contact;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DemoFirstFxml extends Application {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(DemoFirstFxml.class);

  @Override
  public void start(final Stage primaryStage) throws Exception {
    StackPane rootStackPane = new StackPane();
    FirstScreen firstScreen = new FirstScreen();
    rootStackPane.getChildren().add(firstScreen);

    primaryStage.setScene(new Scene(rootStackPane, 800, 800));
    primaryStage.setTitle("First simple Screen");
    primaryStage.show();
  }
}
