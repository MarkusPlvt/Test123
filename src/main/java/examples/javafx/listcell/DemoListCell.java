package examples.javafx.listcell;

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


public class DemoListCell extends Application {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(DemoListCell.class);

  @Override
  public void start(final Stage primaryStage) throws Exception {
    StackPane rootStackPane = new StackPane();
    ListView<Contact> contactListView = new ListView<>();
    ObservableList<Contact> contactObservableList =
            FXCollections.observableList(MockDataGenerator.getContactList());
    contactListView.setItems(contactObservableList);
    contactListView.setCellFactory(list -> new ContactListCell());

    rootStackPane.getChildren().add(contactListView);
    primaryStage.setScene(new Scene(rootStackPane, 300, 300));
    primaryStage.show();

  }

  static class ContactListCell extends ListCell<Contact> {
    @Override
    protected void updateItem(final Contact item, final boolean empty) {
      super.updateItem(item, empty);
      if (empty || item == null) {
        textProperty().setValue(null);
        setGraphic(null);
      } else {
        VBox vBox = new VBox();
        Label nameLabel = new Label(item.getName());
        Label phoneNumberLabel = new Label(item.getPhoneNumber());
        vBox.getChildren().addAll(nameLabel, phoneNumberLabel);
        setGraphic(vBox);
      }



    }
  }

}
