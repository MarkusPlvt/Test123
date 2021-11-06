package prog.ex10.solution.javafx4pizzadelivery.gui;

import javafx.scene.control.Tab;
import prog.ex10.exercise.javafx4pizzadelivery.gui.ScreenController;
import prog.ex10.exercise.javafx4pizzadelivery.gui.UnknownTransitionException;

/**
 * PizzaDeliveryScreenController Class.
 *
 * @author Markus Kübler 207273
 * @datum 29.11.2020
 * @version 1.0
 */
public class PizzaDeliveryScreenController implements ScreenController {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(PizzaDeliveryScreenController.class);
  private CreateOrderScreen createOrderScreen;
  private EditPizzaScreen editPizzaScreen;
  private ShowOrderScreen showOrderScreen;
  private Tab tab;

  /**
   * Creates a new PizzaDeliveryScreenController.
   *
   * @param tab Tab
   */
  public PizzaDeliveryScreenController(final Tab tab) {
    this.tab = tab;
    this.createOrderScreen = new CreateOrderScreen(this);
    this.showOrderScreen = new ShowOrderScreen(this);
    this.editPizzaScreen = new EditPizzaScreen(this);
  }

  /**
   * Triggers the exchange of Screens.
   *
   * @param fromScreen The screen which triggers the switch. For the first screen this may be null.
   * @param toScreen   The screen to switch to. This must always be readable string.
   * @throws UnknownTransitionException if the transition between the given screens is not specified
   */
  @Override
  public void switchTo(final String fromScreen, final String toScreen)
      throws UnknownTransitionException {
    switch (toScreen) {
      case CreateOrderScreen.SCREEN_NAME:
        this.createOrderScreen.refresh();
        this.tab.setContent(this.createOrderScreen);
        break;
      case ShowOrderScreen.SCREEN_NAME:
        this.showOrderScreen.refresh();
        this.tab.setContent(this.showOrderScreen);
        break;
      case EditPizzaScreen.SCREEN_NAME:
        this.editPizzaScreen.refresh();
        this.tab.setContent(this.editPizzaScreen);
        break;
      default:
        throw new UnknownTransitionException("Unknown transition between: ", fromScreen, toScreen);
    }
  }
}
