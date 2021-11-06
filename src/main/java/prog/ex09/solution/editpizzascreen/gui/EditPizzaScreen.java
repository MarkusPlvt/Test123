package prog.ex09.solution.editpizzascreen.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.Pizza;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.PizzaDeliveryService;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.TooManyToppingsException;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.Topping;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.PizzaSize;

/**
 * EditPizzaScreen Class.
 *
 * @author Markus Kübler 207273
 * @datum 18.11.2020
 * @version 1.0
 */
public class EditPizzaScreen extends VBox {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(EditPizzaScreen.class);

  private PizzaDeliveryService service;
  private Pizza pizza;
  private final IntegerProperty actualPrice = new SimpleIntegerProperty();
  private ListView<Topping> toppingListView;

  /**
   * Constructor of EditPizzaScreen.
   *
   * @param service PizzaDeliveryService
   * @param orderId Id of the order
   * @param pizzaId Id of the pizza
   */
  public EditPizzaScreen(PizzaDeliveryService service, final int orderId, int pizzaId) {
    this.service = service;
    List<Topping> toppings = new ArrayList<>(service.getToppingsPriceList().keySet());
    for (Pizza pizza : service.getOrder(orderId).getPizzaList()) {
      if (pizza.getPizzaId() == pizzaId) {
        this.pizza = pizza;
      }
    }

    Button buttonAdd = new Button("AddTopping");
    Button buttonConfirm = new Button("ConfirmCreation");
    Label pizzaSize = new Label("PizzaSize: " + pizza.getSize().toString());
    Label pizzaPrice = new Label();
    Label pizzaPriceLabel = new Label("PizzaPrice: ");
    Label pizzaPriceCurrency = new Label("EuroCent");
    Label platzhalterLabel1 = new Label("        ");
    Label platzhalterLabel2 = new Label("        ");
    pizzaPrice.textProperty().bind(actualPrice.asString());
    ChoiceBox<Topping> toppingChoiceBox = new ChoiceBox<>();
    toppingListView = new ListView<>();
    toppingListView.setPrefHeight(200);
    actualPrice.setValue(pizza.getPrice());
    ObservableList<Topping> pizzaToppingList = FXCollections.observableList(toppings);
    toppingChoiceBox.setItems(pizzaToppingList);

    buttonAdd.setOnAction(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
            if (event.getSource() == buttonAdd) {
              if (toppingChoiceBox.getValue() != null) {
                try {
                  service.addTopping(pizzaId, toppingChoiceBox.getValue());
                  actualPrice.setValue(pizza.getPrice());
                  ObservableList<Topping> toppingList =
                      FXCollections.observableList(pizza.getToppings());
                  toppingListView.setItems(toppingList);
                  toppingListView.setCellFactory(
                      new Callback<ListView<Topping>, ListCell<Topping>>() {
                        @Override
                        public ListCell<Topping> call(ListView<Topping> param) {
                          return new PizzaToppingCell(EditPizzaScreen.this);
                        }
                      });
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

    buttonConfirm.setOnAction(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
            if (event.getSource() == buttonConfirm) {
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
              alert.setTitle("Complete Creation?");
              alert.setHeaderText(
                  "Your pizza costs: " + pizza.getPrice() + " EuroCent." + "   Create pizza now?");
              ButtonType buttonYes = new ButtonType("Yes");
              ButtonType buttonNo = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
              alert.getButtonTypes().setAll(buttonYes, buttonNo);

              Optional<ButtonType> buttonPressed = alert.showAndWait();
              if (buttonPressed.get() == buttonYes) {
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Pizza Created!");
                alert2.setHeaderText("Your pizza has been successfully created.");
                alert2.show();
                toppingListView.getItems().clear();
                actualPrice.setValue(pizza.getPrice());
              }
            }
          }
        });

    this.getChildren().add(pizzaSize);
    this.getChildren().add(platzhalterLabel1);
    this.getChildren().add(pizzaPriceLabel);
    this.getChildren().add(pizzaPrice);
    this.getChildren().add(pizzaPriceCurrency);
    this.getChildren().add(platzhalterLabel2);
    this.getChildren().add(toppingChoiceBox);
    this.getChildren().add(buttonAdd);
    this.getChildren().add(buttonConfirm);
    this.getChildren().add(toppingListView);
  }

  /**
   * InnerClass PizzaToppingCell.
   *
   * <p>Contains updateItem and removeTopping
   */
  static class PizzaToppingCell extends ListCell<Topping> {
    private final EditPizzaScreen controller;

    public PizzaToppingCell(EditPizzaScreen editPizzaScreen) {
      this.controller = editPizzaScreen;
    }

    @Override
    public void updateItem(Topping item, boolean b) {
      super.updateItem(item, b);
      if (b || item == null) {
        textProperty().setValue(null);
        setGraphic(null);
      } else {
        Button removeButton = new Button("Remove");
        removeButton.setOnAction(
            new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent event) {
                controller.onActionRemoveTopping(item);
              }
            });
        textProperty().setValue("" + item);
        setGraphic(removeButton);
      }
    }
  }





  /**
   * ToppingRemove
   *
   * @param item topping to remove
   */
  private void onActionRemoveTopping(Topping item) {
    service.removeTopping(pizza.getPizzaId(), item);
    actualPrice.setValue(pizza.getPrice());
    ObservableList<Topping> toppingList = FXCollections.observableList(pizza.getToppings());
    toppingListView.setItems(toppingList);
  }
}
