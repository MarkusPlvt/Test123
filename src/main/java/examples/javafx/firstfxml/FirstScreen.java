package examples.javafx.firstfxml;

import examples.javafx.helper.MockDataGenerator;
import examples.javafx.model.Contact;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;


public class FirstScreen extends VBox implements Initializable {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(FirstScreen.class);

  @FXML
  private ListView<Contact> contactListView;
  private ObservableList<Contact> contactObservableList;

  /**
   * Constructor of FirstScreen.
   */
  public FirstScreen() {
    contactObservableList = FXCollections.observableList(MockDataGenerator.getContactList());

    FXMLLoader loader = new FXMLLoader(getClass().getResource("/FirstScreen.fxml"));
    loader.setRoot(this);
    loader.setController(this);

    try {
      loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void initialize(final URL location, final ResourceBundle resources) {
    contactListView.setItems(contactObservableList);

  }
}
