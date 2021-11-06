package prog.ex10.solution.javafx4pizzadelivery.pizzadelivery;

import java.util.ArrayList;
import java.util.List;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.Order;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.Pizza;

public class SimpleOrder implements Order {
  private static int idCounter = 0;
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SimpleOrder.class);
  private int orderId;
  private List<Pizza> pizzaList;

  /** creates a new order. */
  public SimpleOrder() {
    this.orderId = idCounter;
    idCounter++;
    this.pizzaList = new ArrayList<Pizza>();
  }

  @Override
  public int getOrderId() {
    return this.orderId;
  }

  @Override
  public List<Pizza> getPizzaList() {
    return this.pizzaList;
  }

  @Override
  public int getValue() {
    int totalValue = 0;
    for (Pizza pizza : this.pizzaList) {
      totalValue += pizza.getPrice();
    }
    return totalValue;
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
