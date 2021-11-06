package prog.ex09.solution.editpizzascreen.pizzadelivery;

import prog.ex09.exercise.editpizzascreen.pizzadelivery.Pizza;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.PizzaSize;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.Topping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * SimplePizza Class.
 *
 * @author Markus Kübler 207273
 * @datum 18.11.2020
 * @version 1.0
 */
public class SimplePizza implements Pizza {
  private static int idCounter = 0;
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SimplePizza.class);
  private int pizzaId;
  private List<Topping> toppings;
  private PizzaSize pizzaSize;

  /**
   * Constructor for Pizza.
   * @param pizzaSize size of the pizza
   */
  public SimplePizza(PizzaSize pizzaSize) {
    this.pizzaId = idCounter;
    idCounter++;
    this.toppings = new ArrayList<>();
    this.pizzaSize = pizzaSize;
  }

  /**
   * Returns the id of the pizza. The id of the pizza must be unique within the
   * PizzaDeliveryService.
   *
   * @return id of the pizza
   */
  @Override
  public int getPizzaId() {
    return this.pizzaId;
  }

  /**
   * Returns a list of Toppings the pizza has. Toppings are allowed to appear multiple times.
   *
   * @return List of Toppings configured for this pizza
   */
  @Override
  public List<Topping> getToppings() {
    return this.toppings;
  }

  /**
   * Returns the size of the pizza.
   *
   * @return Size of the pizza
   */
  @Override
  public PizzaSize getSize() {
    return this.pizzaSize;
  }

  /**
   * Returns the cumulated price (base price for the size plus toppings) of this pizza.
   *
   * @return cumulated price of the pizza
   */
  @Override
  public int getPrice() {
    int totalPrice = 0;
    SimplePizzaDeliveryService simplePizzaDeliveryService = new SimplePizzaDeliveryService();
    HashMap<Topping, Integer> toppingsPriceList = (HashMap<Topping, Integer>)
            simplePizzaDeliveryService.getToppingsPriceList();
    HashMap<PizzaSize, Integer> pizzaSizePriceList = (HashMap<PizzaSize, Integer>)
            simplePizzaDeliveryService.getPizzaSizePriceList();
    totalPrice += pizzaSizePriceList.get(this.pizzaSize);
    for (Topping topping : this.toppings) {
      totalPrice += toppingsPriceList.get(topping);
    }
    return totalPrice;
  }
}


