package prog.ex10.solution.javafx4pizzadelivery.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import prog.ex10.exercise.javafx4pizzadelivery.gui.UnknownTransitionException;
import prog.ex10.solution.javafx4pizzadelivery.pizzadelivery.SimplePizzaDeliveryService;

/**
 * CreateOrderScreen Class.
 *
 * @author Markus Kübler 207273
 * @datum 29.11.2020
 * @version 1.0
 */
public class CreateOrderScreen extends VBox {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(CreateOrderScreen.class);

  public static final String SCREEN_NAME = "CreateOrderScreen";

  public CreateOrderScreen(PizzaDeliveryScreenController screenController) {
    Button createButton = new Button("Create Order");

    createButton.setOnAction(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
            SimplePizzaDeliveryService simplePizzaDeliveryService =
                (SimplePizzaDeliveryService)
                    SingletonAttributeStore.getReference().getAttribute("PizzaDeliveryService");
            SingletonAttributeStore.getReference()
                .setAttribute("Order", simplePizzaDeliveryService.createOrder());

            try {
              screenController.switchTo(SCREEN_NAME, ShowOrderScreen.SCREEN_NAME);
            } catch (UnknownTransitionException e) {
              e.printStackTrace();
            }
          }
        });

    this.getChildren().addAll(createButton);
  }

  public void refresh() {}
}
