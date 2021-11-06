package prog.ex06.solution.pizzadelivery;

import java.util.ArrayList;
import java.util.List;

import prog.ex06.exercise.pizzadelivery.Order;
import prog.ex06.exercise.pizzadelivery.Pizza;
/**
 * SimpleOrder Class.
 *
 * @author Markus Kübler 207273
 * @datum 10.11.2020
 * @version 1.0
 */

public class SimpleOrder implements Order {
  private static int idCounter = 0;
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SimpleOrder.class);
  private int orderId;
  private List<Pizza> pizzaList;

  /**
   * Constructor for Order.
   */
  public SimpleOrder() {
    this.orderId = idCounter;
    idCounter++;
    this.pizzaList = new ArrayList<>();
  }

  /**
   * Returns the id of the order.
   *
   * @return id of the order.
   */
  @Override
  public int getOrderId() {
    return this.orderId;
  }

  /**
   * Returns a list of pizza objects the order contains.
   *
   * @return List of Pizza objects.
   */
  @Override
  public List<Pizza> getPizzaList() {
    return this.pizzaList;
  }

  /**
   * Returns the cumulated price of the order.
   *
   * @return price of the order
   */
  @Override
  public int getValue() {
    int totalValue = 0;
    for (Pizza pizza : this.pizzaList) {
      totalValue += pizza.getPrice();
    }
    return totalValue;
  }
}
