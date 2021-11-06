package prog.ex10.solution.javafx4pizzadelivery.gui;

import java.io.IOException;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import prog.ex10.exercise.javafx4pizzadelivery.gui.UnknownTransitionException;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.TooManyToppingsException;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.Topping;
import prog.ex10.solution.javafx4pizzadelivery.pizzadelivery.SimpleOrder;
import prog.ex10.solution.javafx4pizzadelivery.pizzadelivery.SimplePizza;
import prog.ex10.solution.javafx4pizzadelivery.pizzadelivery.SimplePizzaDeliveryService;

/**
 * EditPizzaScreen Class.
 *
 * @author Markus Kübler 207273
 * @datum 29.11.2020
 * @version 1.0
 */
public class EditPizzaScreen extends VBox {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(EditPizzaScreen.class);
  public static final String SCREEN_NAME = "EditPizzaScreen";

  private PizzaDeliveryScreenController screenController;
  private SimplePizzaDeliveryService simplePizzaDeliveryService;
  private SimpleOrder order;
  private SimplePizza pizza;
  @FXML private Label sizeLabel;
  @FXML private Label priceLabel;
  @FXML private Label chooseToppingText;
  @FXML private ChoiceBox<Topping> toppingChoiceBox;
  @FXML private ListView listView;
  @FXML private Button addTopping;
  @FXML private Button addPizza;

  /**
   * Creates a new EditPizzaScreen object.
   *
   * @param screenController ScreenController
   */
  public EditPizzaScreen(PizzaDeliveryScreenController screenController) {

    FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/EditPizza.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);
    try {
      fxmlLoader.load();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    this.screenController = screenController;
    chooseToppingText.setText("Choose your Topping:");
    toppingChoiceBox.getItems().addAll(FXCollections.observableArrayList(Topping.values()));
    toppingChoiceBox.setValue(null);
    listView.setCellFactory(list -> new ToppingCell(this));

    addTopping.setOnAction(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
            if (event.getSource() == addTopping) {
              if (toppingChoiceBox.getValue() != null) {
                try {
                  simplePizzaDeliveryService.addTopping(
                      (int) SingletonAttributeStore.getReference().getAttribute("Pizza"),
                      toppingChoiceBox.getValue());
                  listView.getItems().clear();
                  listView.getItems().addAll(pizza.getToppings());
                  setPrice(priceLabel, pizza);
                } catch (TooManyToppingsException e) {
                  Alert alert = new Alert(Alert.AlertType.WARNING);
                  alert.setTitle("Too many Toppings!");
                  alert.setHeaderText("You are only allowed to choose up to 6 Toppings.");
                  alert.showAndWait();
                }
              } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cant add nothing!");
                alert.setHeaderText("No Topping chosen.");
                alert.showAndWait();
              }
            }
          }
        });

    addPizza.setOnAction(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
            if (event.getSource() == addPizza) {
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
              alert.setTitle("Complete Creation?");
              alert.setHeaderText("Add this pizza to cart?");
              ButtonType buttonYes = new ButtonType("Yes");
              ButtonType buttonNo = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
              alert.getButtonTypes().setAll(buttonYes, buttonNo);

              Optional<ButtonType> buttonPressed = alert.showAndWait();
              if (buttonPressed.get() == buttonYes) {
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Pizza created!");
                alert2.setHeaderText("Your Pizza has been created successfully.");
                alert2.setContentText(priceLabel.getText());

                alert2.showAndWait();

                try {
                  screenController.switchTo(SCREEN_NAME, ShowOrderScreen.SCREEN_NAME);
                } catch (UnknownTransitionException e) {
                  e.printStackTrace();
                }
              }
            }
          }
        });
  }

  private static void setPrice(Label priceLabel, SimplePizza pizza) {
    priceLabel.setText("Price: " + String.format("%10.2f", pizza.getPrice() / 100.0) + " €");
  }

  public void refresh() {
    this.simplePizzaDeliveryService =
        (SimplePizzaDeliveryService)
            SingletonAttributeStore.getReference().getAttribute("PizzaDeliveryService");
    int orderId = (Integer) SingletonAttributeStore.getReference().getAttribute("Order");
    int pizzaId = (Integer) SingletonAttributeStore.getReference().getAttribute("Pizza");
    order = (SimpleOrder) simplePizzaDeliveryService.getOrder(orderId);
    pizza = (SimplePizza) order.getPizza(pizzaId);

    sizeLabel.setText("Size: " + pizza.getSize());
    setPrice(priceLabel, pizza);
    listView.getItems().clear();
    listView.getItems().addAll(pizza.getToppings());
  }

  static class ToppingCell extends ListCell<Topping> {
    private final EditPizzaScreen editPizzaScreen;

    public ToppingCell(EditPizzaScreen editPizzaScreen) {
      this.editPizzaScreen = editPizzaScreen;
    }

    public void updateItem(Topping item, boolean empty) {
      super.updateItem(item, empty);
      if (empty || item == null) {
        textProperty().setValue(null);
        setGraphic(null);
      } else {
        SimplePizzaDeliveryService simplePizzaDeliveryService =
            (SimplePizzaDeliveryService)
                SingletonAttributeStore.getReference().getAttribute("PizzaDeliveryService");
        int orderId = (Integer) SingletonAttributeStore.getReference().getAttribute("Order");
        SimpleOrder order = (SimpleOrder) simplePizzaDeliveryService.getOrder(orderId);
        int pizzaId = (Integer) SingletonAttributeStore.getReference().getAttribute("Pizza");
        SimplePizza pizza = (SimplePizza) order.getPizza(pizzaId);

        Button removeButton = new Button("Remove");
        textProperty().setValue("" + item);
        setGraphic(removeButton);

        removeButton.setOnAction(
            new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent event) {
                simplePizzaDeliveryService.removeTopping(pizza.getPizzaId(), item);
                editPizzaScreen.listView.getItems().clear();
                editPizzaScreen.listView.getItems().addAll(pizza.getToppings());
                EditPizzaScreen.setPrice(editPizzaScreen.priceLabel, pizza);
              }
            });
      }
    }
  }
}
