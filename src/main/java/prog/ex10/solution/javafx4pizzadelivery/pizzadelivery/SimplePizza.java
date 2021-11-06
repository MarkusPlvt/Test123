package prog.ex10.solution.javafx4pizzadelivery.pizzadelivery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.Pizza;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.PizzaSize;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.Topping;

public class SimplePizza implements Pizza {
  private static int idCounter = 0;
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SimplePizza.class);
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

  @Override
  public int getPrice() {
    int totalPrice = 0;
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
  }
}
