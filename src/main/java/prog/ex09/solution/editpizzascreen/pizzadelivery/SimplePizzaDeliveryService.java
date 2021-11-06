package prog.ex09.solution.editpizzascreen.pizzadelivery;

import prog.ex09.exercise.editpizzascreen.pizzadelivery.Order;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.PizzaDeliveryService;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.PizzaSize;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.TooManyToppingsException;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.Topping;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.Pizza;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimplePizzaDeliveryService implements PizzaDeliveryService {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SimplePizzaDeliveryService.class);

  private Map<PizzaSize, Integer> pizzaSizePriceList = new HashMap<>();
  private Map<Topping, Integer> toppingsPriceList = new HashMap<>();
  private List<Order> orders;

  /** Constructor for PizzaDeliveryService. */
  public SimplePizzaDeliveryService() {
    this.orders = new ArrayList<>();
  }

  /**
   * Creates internally an Order object.
   *
   * @return id of the newly created Order object
   */
  @Override
  public int createOrder() {
    Order order = new SimpleOrder();
    this.orders.add(order);
    return order.getOrderId();
  }

  /**
   * Adds a pizza of the given size to the order referenced by the orderId.
   *
   * @param orderId id of the order
   * @param size size of the pizza
   * @return id of the pizza
   * @throws IllegalArgumentException if the orderId is not a valid order id.
   */
  @Override
  public int addPizza(final int orderId, final PizzaSize size) throws IllegalArgumentException {
    if (orderId < 0) {
      SimpleOrder simpleOrder = (SimpleOrder) this.getOrder(orderId);
      Pizza pizza = new SimplePizza(size);
      simpleOrder.getPizzaList().add(pizza);
      return pizza.getPizzaId();
    } else {
      throw new IllegalArgumentException("OrderId not valid.");
    }
  }

  /**
   * Removes a pizza from the order referenced by the orderId.
   *
   * @param orderId id of the order
   * @param pizzaId id of the pizza
   * @throws IllegalArgumentException if either the orderId is not a valid order or the pizzaId is
   *     not a valid pizza
   */
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

  /**
   * Adds a Topping to the pizza referenced by the pizzaId.
   *
   * @param pizzaId id of the pizza
   * @param topping Topping to be added
   * @throws IllegalArgumentException if the pizzaId is not a valid pizza
   * @throws prog.ex06.exercise.pizzadelivery.TooManyToppingsException if there are already
   *     MAX_TOPPINGS_PER_PIZZA Toppings on the pizza
   */
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

  /**
   * Removes a topping from the pizza referenced by the pizzaId.
   *
   * @param pizzaId id of the pizza
   * @param topping Topping to be removed
   * @throws IllegalArgumentException if either the pizzaId is not a valid pizza or the Topping is
   *     not configured for the referenced pizza
   */
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

  /**
   * Returns the order referenced by the orderId.
   *
   * @param orderId id of the order
   * @return Order referenced by the id.
   */
  @Override
  public Order getOrder(final int orderId) throws IllegalArgumentException {
    for (Order order : this.orders) {
      if (order.getOrderId() == orderId) {
        return order;
      }
    }
    throw new IllegalArgumentException("Order not found.");
  }

  /**
   * Returns the price information for different Pizza sizes.
   *
   * @return Map with price per PizzaSize
   */
  @Override
  public Map<PizzaSize, Integer> getPizzaSizePriceList() {
    pizzaSizePriceList.put(PizzaSize.SMALL, 500);
    pizzaSizePriceList.put(PizzaSize.MEDIUM, 700);
    pizzaSizePriceList.put(PizzaSize.LARGE, 900);
    pizzaSizePriceList.put(PizzaSize.EXTRA_LARGE, 1100);
    return pizzaSizePriceList;
  }

  /**
   * Returns the price information for Toppings.
   *
   * @return Map with price per Topping
   */
  @Override
  public Map<Topping, Integer> getToppingsPriceList() {
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
