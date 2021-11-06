package prog.ex11.solution.saveandload.pizzadelivery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import prog.ex11.exercise.saveandload.pizzadelivery.Order;
import prog.ex11.exercise.saveandload.pizzadelivery.Pizza;

/**
 * SimpleOrder Class.
 *
 * @author Markus Kübler 207273
 * @datum 09.12.2020
 * @version 1.0
 */
public class SimpleOrder implements Order, Serializable {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SimpleOrder.class);

  private int idCounter = 1;
  private int orderId;
  private List<Pizza> pizzaList;
  int totalValueToken = 0;

  /** creates a new order. */
  public SimpleOrder() {
    this.orderId = idCounter;
    idCounter++;
    this.pizzaList = new ArrayList<Pizza>();
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }

  @Override
  public int getOrderId() {
    return this.orderId;
  }

  @Override
  public List<Pizza> getPizzaList() {
    return this.pizzaList;
  }

  public void setTotalValue(int totalValueToken) {
    this.totalValueToken = totalValueToken;
  }

  @Override
  public int getValue() {
    int totalValue = 0;
    if (totalValueToken == 0) {
      for (Pizza pizza : this.pizzaList) {
        totalValue += pizza.getPrice();
      }
      return totalValue;
    } else {
      return totalValueToken;
    }
  }

  public Pizza getPizza(int pizzaId) throws IllegalArgumentException {
    for (Pizza pizza : this.pizzaList) {
      if (pizza.getPizzaId() == pizzaId) {
        return pizza;
      }
    }
    throw new IllegalArgumentException("Pizza not found.");
  }
}
