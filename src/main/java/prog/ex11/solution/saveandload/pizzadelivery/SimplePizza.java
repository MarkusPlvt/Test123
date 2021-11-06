package prog.ex11.solution.saveandload.pizzadelivery;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import prog.ex11.exercise.saveandload.pizzadelivery.Pizza;
import prog.ex11.exercise.saveandload.pizzadelivery.PizzaSize;
import prog.ex11.exercise.saveandload.pizzadelivery.Topping;

/**
 * SimplePizza Class.
 *
 * @author Markus Kübler 207273
 * @datum 09.12.2020
 * @version 1.0
 */
public class SimplePizza implements Pizza, Serializable {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SimplePizza.class);

  private static int idCounter = 1;
  int pizzaPriceToken = 0;
  private int pizzaId;
  private List<Topping> toppings;
  private PizzaSize pizzaSize;

  /**
   * creates a new pizza.
   *
   * @param pizzaSize size of the pizza
   */
  public SimplePizza(PizzaSize pizzaSize) {
    this.pizzaId = idCounter;
    idCounter++;
    this.toppings = new ArrayList<Topping>();
    this.pizzaSize = pizzaSize;
  }

  public void setPizzaId(int pizzaId) {
    this.pizzaId = pizzaId;
  }

  @Override
  public int getPizzaId() {
    return this.pizzaId;
  }

  @Override
  public List<Topping> getToppings() {
    return this.toppings;
  }

  @Override
  public PizzaSize getSize() {
    return this.pizzaSize;
  }

  public void setTotalPrice(int pizzaPriceToken) {
    this.pizzaPriceToken = pizzaPriceToken;
  }

  @Override
  public int getPrice() {
    int totalPrice = 0;
    if (pizzaPriceToken == 0) {
      SimplePizzaDeliveryService simplePizzaDeliveryService = new SimplePizzaDeliveryService();
      HashMap<Topping, Integer> toppingsPriceList =
          (HashMap<Topping, Integer>) simplePizzaDeliveryService.getToppingsPriceList();
      HashMap<PizzaSize, Integer> pizzaSizePriceList =
          (HashMap<PizzaSize, Integer>) simplePizzaDeliveryService.getPizzaSizePriceList();
      totalPrice += pizzaSizePriceList.get(this.pizzaSize);
      for (Topping topping : this.toppings) {
        totalPrice += toppingsPriceList.get(topping);
      }
      return totalPrice;
    } else {
      return pizzaPriceToken;
    }
  }
}
