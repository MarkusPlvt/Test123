package prog.ex10.solution.javafx4pizzadelivery.gui;

import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import prog.ex10.exercise.javafx4pizzadelivery.gui.ScreenController;
import prog.ex10.exercise.javafx4pizzadelivery.gui.UnknownTransitionException;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.Pizza;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.PizzaSize;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.Topping;
import prog.ex10.solution.javafx4pizzadelivery.pizzadelivery.SimpleOrder;
import prog.ex10.solution.javafx4pizzadelivery.pizzadelivery.SimplePizzaDeliveryService;

/**
 * ShowOrderScreen Class.
 *
 * @author Markus Kübler 207273
 * @datum 29.11.2020
 * @version 1.0
 */
public class ShowOrderScreen extends VBox {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(ShowOrderScreen.class);

  public static final String SCREEN_NAME = "ShowOrderScreen";
  public ScreenController screenController;
  private ListView listView;
  private Label priceLabel;
  private int order;

  /**
   * Creates a new ShowOrderScreen.
   *
   * @param screenController ScreenController
   */
  public ShowOrderScreen(PizzaDeliveryScreenController screenController) {
    this.screenController = screenController;
    SimplePizzaDeliveryService simplePizzaDeliveryService =
        (SimplePizzaDeliveryService)
            SingletonAttributeStore.getReference().getAttribute("PizzaDeliveryService");

    Label pizzaSizeText = new Label("Choose your PizzaSize:");
    ChoiceBox<PizzaSize> pizzaSizeChoiceBox = new ChoiceBox();
    pizzaSizeChoiceBox.getItems().addAll(FXCollections.observableArrayList(PizzaSize.values()));
    pizzaSizeChoiceBox.setValue(null);
    Button createPizza = new Button("Create Pizza");
    Button sendButton = new Button("Send Order");
    listView = new ListView<Pizza>();
    listView.setCellFactory(list -> new PizzaCell(this));
    priceLabel = new Label();

    createPizza.setOnAction(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
            if (event.getSource() == createPizza) {
              if (pizzaSizeChoiceBox.getValue() != null) {
                int pizzaId =
                    simplePizzaDeliveryService.addPizza(
                        (Integer) SingletonAttributeStore.getReference().getAttribute("Order"),
                        pizzaSizeChoiceBox.getValue());
                SingletonAttributeStore.getReference().setAttribute("Pizza", pizzaId);

                try {
                  screenController.switchTo(SCREEN_NAME, EditPizzaScreen.SCREEN_NAME);
                } catch (UnknownTransitionException e) {
                  e.printStackTrace();
                }
              } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cant add nothing!");
                alert.setHeaderText("No Size chosen.");
                alert.showAndWait();
              }
            }
          }
        });

    sendButton.setOnAction(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
            SimplePizzaDeliveryService simplePizzaDeliveryService =
                (SimplePizzaDeliveryService)
                    SingletonAttributeStore.getReference().getAttribute("PizzaDeliveryService");

            SimpleOrder order =
                (SimpleOrder)
                    simplePizzaDeliveryService.getOrder(
                        (Integer) SingletonAttributeStore.getReference().getAttribute("Order"));

            if (order.getPizzaList().size() != 0) {
              if (event.getSource() == sendButton) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Complete Order?");
                alert.setHeaderText("Do you want to finish your Order?");
                ButtonType buttonYes = new ButtonType("Yes");
                ButtonType buttonNo = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(buttonYes, buttonNo);

                Optional<ButtonType> buttonPressed = alert.showAndWait();
                if (buttonPressed.get() == buttonYes) {
                  Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                  alert2.setTitle("Order successful!!");
                  alert2.setHeaderText("Your order was successfull.");
                  alert2.showAndWait();

                  try {
                    screenController.switchTo(SCREEN_NAME, CreateOrderScreen.SCREEN_NAME);
                  } catch (UnknownTransitionException e) {
                    e.printStackTrace();
                  }
                }
              }
            } else {
              Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
              alert3.setTitle("Cant order nothing!");
              alert3.setHeaderText("No Pizza in cart.");
              alert3.showAndWait();
            }
          }
        });

    this.getChildren()
        .addAll(pizzaSizeText, pizzaSizeChoiceBox, createPizza, listView, priceLabel, sendButton);
  }

  public void refresh() {
    SimplePizzaDeliveryService simplePizzaDeliveryService =
        (SimplePizzaDeliveryService)
            SingletonAttributeStore.getReference().getAttribute("PizzaDeliveryService");

    SimpleOrder order =
        (SimpleOrder)
            simplePizzaDeliveryService.getOrder(
                (Integer) SingletonAttributeStore.getReference().getAttribute("Order"));
    listView.getItems().clear();
    listView.getItems().addAll(FXCollections.observableArrayList(order.getPizzaList()));

    int totalPrice = 0;
    for (Pizza pizza : order.getPizzaList()) {
      totalPrice += pizza.getPrice();
    }
    priceLabel.setText("Price: " + String.format("%10.2f", totalPrice / 100.0) + " €");
  }

  static class PizzaCell extends ListCell<Pizza> {
    private final ShowOrderScreen showOrderScreen;

    public PizzaCell(ShowOrderScreen showOrderScreen) {
      this.showOrderScreen = showOrderScreen;
    }

    public void updateItem(Pizza item, boolean empty) {
      super.updateItem(item, empty);
      if (empty || item == null) {
        setText(null);
        setGraphic(null);
      } else {
        SimplePizzaDeliveryService simplePizzaDeliveryService =
            (SimplePizzaDeliveryService)
                SingletonAttributeStore.getReference().getAttribute("PizzaDeliveryService");
        int orderId = (Integer) SingletonAttributeStore.getReference().getAttribute("Order");
        SimpleOrder order = (SimpleOrder) simplePizzaDeliveryService.getOrder(orderId);

        String pizzaName = item.getSize() + "";
        List<Topping> toppings = item.getToppings();
        if (toppings.size() > 0) {
          pizzaName += " (";
          for (int i = 0; i < toppings.size() - 1; i++) {
            pizzaName += toppings.get(i).name() + ", ";
          }
          pizzaName += toppings.get(toppings.size() - 1).name() + ")";
        }
        pizzaName += String.format(" %10.2f", item.getPrice() / 100.0) + " €";
        setText(pizzaName);

        Button editButton = new Button("Edit");
        Button removeButton = new Button("Remove");
        HBox buttons = new HBox();
        buttons.getChildren().addAll(removeButton, editButton);
        setGraphic(buttons);

        editButton.setOnAction(
            evt -> {
              SingletonAttributeStore.getReference().setAttribute("Pizza", item.getPizzaId());

              try {
                this.showOrderScreen.screenController.switchTo(
                    SCREEN_NAME, EditPizzaScreen.SCREEN_NAME);
              } catch (UnknownTransitionException e) {
                e.printStackTrace();
              }
            });

        removeButton.setOnAction(
            evt -> {
              simplePizzaDeliveryService.removePizza(orderId, item.getPizzaId());
              this.showOrderScreen.refresh();
            });
      }
    }
  }
}
