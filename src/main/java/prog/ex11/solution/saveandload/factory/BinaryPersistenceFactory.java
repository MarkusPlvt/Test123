package prog.ex11.solution.saveandload.factory;

import prog.ex11.exercise.saveandload.factory.PersistenceFactory;
import prog.ex11.exercise.saveandload.factory.WrongOrderFormatException;
import prog.ex11.exercise.saveandload.pizzadelivery.Order;
import prog.ex11.exercise.saveandload.pizzadelivery.PizzaSize;
import prog.ex11.exercise.saveandload.pizzadelivery.Topping;
import prog.ex11.solution.saveandload.pizzadelivery.SimpleOrder;
import prog.ex11.solution.saveandload.pizzadelivery.SimplePizza;

import java.io.*;

/**
 * BinaryPersistenceFactory Class.
 *
 * @author Markus Kübler 207273
 * @datum 16.12.2020
 * @version 1.0
 */
public class BinaryPersistenceFactory implements PersistenceFactory {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(BinaryPersistenceFactory.class);

  private SimpleOrder simpleOrder = new SimpleOrder();

  public BinaryPersistenceFactory() {}

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
      try (DataOutputStream dataOutputStream =
          new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file))); ) {

        simpleOrder = (SimpleOrder) order;
        dataOutputStream.writeInt(simpleOrder.getOrderId()); // 1
        // dataOutputStream.writeUTF(";");
        dataOutputStream.writeInt(simpleOrder.getValue()); // 2
        // dataOutputStream.writeUTF(";");
        dataOutputStream.writeInt(simpleOrder.getPizzaList().size()); // 3
        dataOutputStream.writeUTF("\n");

        int i = 1;
        int j = 0;

        while (simpleOrder.getPizzaList().size() >= i) {
          dataOutputStream.writeByte(simpleOrder.getPizza(i).getPizzaId()); // 4
          dataOutputStream.writeUTF(";");
          dataOutputStream.writeByte(simpleOrder.getPizza(i).getPrice()); // 5
          dataOutputStream.writeUTF(";");
          while (simpleOrder.getPizza(i).getToppings().size() > j) {
            dataOutputStream.writeByte(simpleOrder.getPizza(i).getToppings().size()); // 7
            dataOutputStream.writeUTF(
                ";" + simpleOrder.getPizza(i).getToppings().get(j).toString()); // 8
            j++;
          }
          dataOutputStream.writeUTF("\n");
          i++;
        }
        dataOutputStream.close();
      } catch (EOFException e) {
      }
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

    if (!file.canRead()) {
      throw new IOException("File does not exist!");
    }
    try (DataInputStream dataInputStream =
        new DataInputStream(new BufferedInputStream(new FileInputStream(file))); ) {

      int orderId;
      orderId = dataInputStream.readInt();
      simpleOrder = new SimpleOrder();
      this.simpleOrder.setOrderId(orderId);

      int totalValue;
      totalValue = dataInputStream.readInt();
      this.simpleOrder.setTotalValue(totalValue);

      int orderPizzaAmount;
      orderPizzaAmount = dataInputStream.readInt();

      for (int i = 0; i < orderPizzaAmount; i++) {
        int pizzaId;
        int currentPizzaPrice;
        SimplePizza pizza;
        String currentTopping;
        Topping topping;
        String pizzaSize;
        pizzaSize = dataInputStream.readUTF();
        pizzaId = dataInputStream.readInt();
        currentPizzaPrice = dataInputStream.readInt();
        int numberOfToppings = dataInputStream.readInt();

        switch (pizzaSize) { // 6
          case ("SMALL"):
            pizza = new SimplePizza(PizzaSize.SMALL);
            pizza.setPizzaId(pizzaId);
            pizza.setTotalPrice(currentPizzaPrice);
            simpleOrder.getPizzaList().add(pizza);
            break;
          case ("MEDIUM"):
            pizza = new SimplePizza(PizzaSize.MEDIUM);
            pizza.setPizzaId(pizzaId);
            pizza.setTotalPrice(currentPizzaPrice);
            simpleOrder.getPizzaList().add(pizza);
            break;
          case ("LARGE"):
            pizza = new SimplePizza(PizzaSize.LARGE);
            pizza.setPizzaId(pizzaId);
            pizza.setTotalPrice(currentPizzaPrice);
            simpleOrder.getPizzaList().add(pizza);
            break;
          case ("EXTRA_LARGE"):
            pizza = new SimplePizza(PizzaSize.EXTRA_LARGE);
            pizza.setPizzaId(pizzaId);
            pizza.setTotalPrice(currentPizzaPrice);
            simpleOrder.getPizzaList().add(pizza);
            break;
          default:
            throw new WrongOrderFormatException("Invalid PizzaSize!");
        }

        for (int j = 0; j < numberOfToppings; j++) {
          currentTopping = dataInputStream.readUTF();
          switch (currentTopping) {
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
    } catch (EOFException e) {
      throw new EOFException();
    }
    return simpleOrder;
  }
}
