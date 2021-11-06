package prog.ex10.solution.javafx4pizzadelivery.pizzadelivery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.Order;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.Pizza;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.PizzaDeliveryService;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.PizzaSize;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.TooManyToppingsException;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.Topping;

public class SimplePizzaDeliveryService implements PizzaDeliveryService {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SimplePizzaDeliveryService.class);
  private List<Order> orders;

  /** creates a new delivery service. */
  public SimplePizzaDeliveryService() {
    this.orders = new ArrayList<Order>();
  }

  @Override
  public int createOrder() {
    Order order = new SimpleOrder();
    this.orders.add(order);
    return order.getOrderId();
  }

  @Override
  public int addPizza(final int orderId, final PizzaSize size) throws IllegalArgumentException {
    SimpleOrder simpleOrder = (SimpleOrder) this.getOrder(orderId);
    Pizza pizza = new SimplePizza(size);
    simpleOrder.getPizzaList().add(pizza);
    return pizza.getPizzaId();
  }

  @Override
  public void removePizza(final int orderId, final int pizzaId) throws IllegalArgumentException {
    SimpleOrder simpleOrder = (SimpleOrder) this.getOrder(orderId);
    for (int i = 0; i < simpleOrder.getPizzaList().size(); i++) {
      Pizza pizza = simpleOrder.getPizzaList().get(i);
      if (pizza.getPizzaId() == pizzaId) {
        simpleOrder.getPizzaList().remove(i);
        return;
      }
    }
    throw new IllegalArgumentException("Pizza not found.");
  }

  @Override
  public void addTopping(final int pizzaId, final Topping topping)
      throws IllegalArgumentException, TooManyToppingsException {
    for (Order order : this.orders) {
      for (Pizza pizza : order.getPizzaList()) {
        if (pizza.getPizzaId() == pizzaId) {
          if (pizza.getToppings().size() >= MAX_TOPPINGS_PER_PIZZA) {
            throw new TooManyToppingsException("Too many toppings on pizza.");
          } else {
            pizza.getToppings().add(topping);
            return;
          }
        }
      }
    }
    throw new IllegalArgumentException("Pizza not found.");
  }

  @Override
  public void removeTopping(final int pizzaId, final Topping topping)
      throws IllegalArgumentException {
    for (Order order : this.orders) {
      for (Pizza pizza : order.getPizzaList()) {
        if (pizza.getPizzaId() == pizzaId) {
          for (int i = 0; i < pizza.getToppings().size(); i++) {
            Topping topping1 = pizza.getToppings().get(i);
            if (topping1 == topping) {
              pizza.getToppings().remove(i);
              return;
            }
          }
          throw new IllegalArgumentException("Topping is not on pizza.");
        }
      }
    }
    throw new IllegalArgumentException("Pizza not found.");
  }

  @Override
  public Order getOrder(final int orderId) throws IllegalArgumentException {
    for (Order order : this.orders) {
      if (order.getOrderId() == orderId) {
        return order;
      }
    }
    throw new IllegalArgumentException("Order not found.");
  }

  @Override
  public Map<PizzaSize, Integer> getPizzaSizePriceList() {
    Map<PizzaSize, Integer> pizzaSizePriceList = new HashMap<PizzaSize, Integer>();
    pizzaSizePriceList.put(PizzaSize.SMALL, 500);
    pizzaSizePriceList.put(PizzaSize.MEDIUM, 700);
    pizzaSizePriceList.put(PizzaSize.LARGE, 900);
    pizzaSizePriceList.put(PizzaSize.EXTRA_LARGE, 1100);
    return pizzaSizePriceList;
  }

  @Override
  public Map<Topping, Integer> getToppingsPriceList() {
    Map<Topping, Integer> toppingsPriceList = new HashMap<Topping, Integer>();
    toppingsPriceList.put(Topping.TOMATO, 30);
    toppingsPriceList.put(Topping.CHEESE, 60);
    toppingsPriceList.put(Topping.SALAMI, 50);
    toppingsPriceList.put(Topping.HAM, 70);
    toppingsPriceList.put(Topping.ANANAS, 90);
    toppingsPriceList.put(Topping.VEGETABLES, 20);
    toppingsPriceList.put(Topping.SEAFOOD, 150);
    return toppingsPriceList;
  }
}
