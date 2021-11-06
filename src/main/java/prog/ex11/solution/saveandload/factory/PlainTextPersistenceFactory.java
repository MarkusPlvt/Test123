package prog.ex11.solution.saveandload.factory;

import java.io.*;
import java.net.http.WebSocketHandshakeException;
import java.util.*;

import prog.ex11.exercise.saveandload.factory.PersistenceFactory;
import prog.ex11.exercise.saveandload.factory.WrongOrderFormatException;
import prog.ex11.exercise.saveandload.pizzadelivery.Order;
import prog.ex11.exercise.saveandload.pizzadelivery.PizzaSize;
import prog.ex11.exercise.saveandload.pizzadelivery.Topping;
import prog.ex11.solution.saveandload.pizzadelivery.SimpleOrder;
import prog.ex11.solution.saveandload.pizzadelivery.SimplePizza;

/**
 * PlainTextPersistenceFactory Class.
 *
 * @author Markus Kübler 207273
 * @datum 09.12.2020
 * @version 1.0
 */
public class PlainTextPersistenceFactory implements PersistenceFactory {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(PlainTextPersistenceFactory.class);

  private BufferedReader bufferedReader;
  private SimpleOrder order;
  private File file;

  /**
   * Saves an Order object into the given File.
   *
   * @param file file into which the Order object shall be saved
   * @param order Order to be saved
   * @throws IOException if the file cannot be written
   */
  @Override
  public void save(final File file, final Order order) throws IOException {
    if (!file.canWrite()) {
      throw new IOException("File cannot be written.");
    } else {
      FileWriter fileWriter = new FileWriter(file);
      this.order = (SimpleOrder) order;
      this.file = file;
      fileWriter.write(
          order.getOrderId() + ";" + order.getValue() + ";" + order.getPizzaList().size() + "\n");

      int i = 1;
      int j = 0;

      while (order.getPizzaList().size() >= i) {
        if (this.order.getPizza(i).getToppings().size() == 0) {
          fileWriter.write(
              this.order.getPizza(i).getPizzaId()
                  + ";"
                  + this.order.getPizza(i).getPrice()
                  + ";"
                  + this.order.getPizza(i).getSize());
        } else {
          fileWriter.write(
              this.order.getPizza(i).getPizzaId()
                  + ";"
                  + this.order.getPizza(i).getPrice()
                  + ";"
                  + this.order.getPizza(i).getSize()
                  + ";");
        }
        while (this.order.getPizza(i).getToppings().size() > j) {
          if (this.order.getPizza(i).getToppings().size() == j + 1) {
            fileWriter.write(this.order.getPizza(i).getToppings().get(j) + "");
          } else {
            fileWriter.write(this.order.getPizza(i).getToppings().get(j) + ";");
          }
          j++;
        }
        fileWriter.write("\n");
        i++;
      }
      fileWriter.close();
    }
  }

  /**
   * Loads an Order object from the given File.
   *
   * @param file file where the Order object is stored
   * @return Order object which is stored in the given File
   * @throws IOException if the file does not exist or cannot be opened or cannot be read
   * @throws WrongOrderFormatException if the content of the file cannot successfully be parsed into
   *     an Order object
   */
  @Override
  public Order load(final File file) throws IOException, WrongOrderFormatException {
    int orderId;
    int totalValueToken;
    int orderPizzaAmount;
    if (file == null || !file.canRead() || !file.canExecute()) {
      throw new IOException("File does not exist!");
    }
    bufferedReader = new BufferedReader(new FileReader(file));
    StringTokenizer line = new StringTokenizer(bufferedReader.readLine(), ";");
    if (line.countTokens() != 3) {
      throw new WrongOrderFormatException(
          "Tokens in first line are not like they should be. Expected: 3");
    }
    try {
      orderId = Integer.parseInt(line.nextToken());
    } catch (Exception e) {
      throw new WrongOrderFormatException("Wrong orderId format. Must be Int!");
    }
    SimpleOrder order = new SimpleOrder();
    order.setOrderId(orderId);
    try {
      totalValueToken = Integer.parseInt(line.nextToken());
    } catch (Exception e) {
      throw new WrongOrderFormatException("Wrong totalValue format. Must be Int!");
    }
    order.setTotalValue(totalValueToken);
    try {
      orderPizzaAmount = Integer.parseInt(line.nextToken());
    } catch (Exception e) {
      throw new WrongOrderFormatException("Wrong pizzaAmount format. Must be int!");
    }
    for (int i = 0; i < orderPizzaAmount; i++) {
      int pizzaId;
      int currentPizzaPrice;
      SimplePizza pizza;
      Topping topping;
      String pizzaSize;
      StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine(), ";");
      try {
        pizzaId = Integer.parseInt(tokenizer.nextToken());
      } catch (Exception e) {
        throw new WrongOrderFormatException("Wrong pizzaId format. Must be Int!");
      }
      try {
        currentPizzaPrice = Integer.parseInt(tokenizer.nextToken());
      } catch (Exception e) {
        throw new WrongOrderFormatException("Wrong pizzaPrice format. Must be Int!");
      }
      try {
        pizzaSize = tokenizer.nextToken();
      } catch (Exception e) {
        throw new WrongOrderFormatException("Wrong pizzaSize format. Must be String!");
      }
      switch (pizzaSize) {
        case ("SMALL"):
          pizza = new SimplePizza(PizzaSize.SMALL);
          pizza.setPizzaId(pizzaId);
          pizza.setTotalPrice(currentPizzaPrice);
          order.getPizzaList().add(pizza);
          break;
        case ("MEDIUM"):
          pizza = new SimplePizza(PizzaSize.MEDIUM);
          pizza.setPizzaId(pizzaId);
          pizza.setTotalPrice(currentPizzaPrice);
          order.getPizzaList().add(pizza);
          break;
        case ("LARGE"):
          pizza = new SimplePizza(PizzaSize.LARGE);
          pizza.setPizzaId(pizzaId);
          pizza.setTotalPrice(currentPizzaPrice);
          order.getPizzaList().add(pizza);
          break;
        case ("EXTRA_LARGE"):
          pizza = new SimplePizza(PizzaSize.EXTRA_LARGE);
          pizza.setPizzaId(pizzaId);
          pizza.setTotalPrice(currentPizzaPrice);
          order.getPizzaList().add(pizza);
          break;
        default:
          throw new WrongOrderFormatException("Invalid PizzaSize!");
      }
      while (tokenizer.hasMoreTokens()) {
        switch (tokenizer.nextToken()) {
          case ("TOMATO"):
            topping = Topping.TOMATO;
            break;
          case ("CHEESE"):
            topping = Topping.CHEESE;
            break;
          case ("SALAMI"):
            topping = Topping.SALAMI;
            break;
          case ("HAM"):
            topping = Topping.HAM;
            break;
          case ("ANANAS"):
            topping = Topping.ANANAS;
            break;
          case ("VEGETABLES"):
            topping = Topping.VEGETABLES;
            break;
          case ("SEAFOOD"):
            topping = Topping.SEAFOOD;
            break;
          default:
            throw new WrongOrderFormatException("Invalid Topping!");
        }
        pizza.getToppings().add(topping);
      }
    }
    bufferedReader.close();
    return order;
  }
}
