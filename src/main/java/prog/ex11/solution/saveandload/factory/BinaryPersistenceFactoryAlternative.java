package prog.ex11.solution.saveandload.factory;

import prog.ex11.exercise.saveandload.factory.PersistenceFactory;
import prog.ex11.exercise.saveandload.factory.WrongOrderFormatException;
import prog.ex11.exercise.saveandload.pizzadelivery.Order;
import prog.ex11.exercise.saveandload.pizzadelivery.PizzaSize;
import prog.ex11.exercise.saveandload.pizzadelivery.Topping;
import prog.ex11.solution.saveandload.pizzadelivery.SimpleOrder;
import prog.ex11.solution.saveandload.pizzadelivery.SimplePizza;

import java.io.*;

/** Alternativklasse von Binary zur Werteüberprüfung der .txt */
public class BinaryPersistenceFactoryAlternative implements PersistenceFactory {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(BinaryPersistenceFactory.class);

  private SimpleOrder simpleOrder = new SimpleOrder();

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
      DataOutputStream dataOutputStream =
          new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));

      simpleOrder = (SimpleOrder) order;
      dataOutputStream.writeUTF(
          simpleOrder.getOrderId()
              + ";"
              + simpleOrder.getValue()
              + ";"
              + simpleOrder.getPizzaList().size()
              + "\n");
      /**
       * dataOutputStream.writeInt(simpleOrder.getOrderId()); // 1 dataOutputStream.writeUTF(";");
       * dataOutputStream.writeInt(simpleOrder.getValue()); // 2 dataOutputStream.writeUTF(";");
       * dataOutputStream.writeByte(simpleOrder.getPizzaList().size()); // 3
       * dataOutputStream.writeUTF("\n");
       */
      int i = 1;
      int j = 0;

      while (order.getPizzaList().size() >= i) {
        if (this.simpleOrder.getPizza(i).getToppings().size() == 0) {
          dataOutputStream.writeUTF(
              this.simpleOrder.getPizza(i).getPizzaId()
                  + ";"
                  + this.simpleOrder.getPizza(i).getPrice()
                  + ";"
                  + this.simpleOrder.getPizza(i).getSize());
        } else {
          dataOutputStream.writeUTF(
              this.simpleOrder.getPizza(i).getPizzaId()
                  + ";"
                  + this.simpleOrder.getPizza(i).getPrice()
                  + ";"
                  + this.simpleOrder.getPizza(i).getSize()
                  + ";");
        }
        while (this.simpleOrder.getPizza(i).getToppings().size() > j) {
          if (this.simpleOrder.getPizza(i).getToppings().size() == j + 1) {
            dataOutputStream.writeUTF(this.simpleOrder.getPizza(i).getToppings().get(j) + "");
          } else {
            dataOutputStream.writeUTF(this.simpleOrder.getPizza(i).getToppings().get(j) + ";");
          }
          j++;
        }
        dataOutputStream.writeUTF("\n");
        i++;
      }
      dataOutputStream.close();
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
    if (!file.canRead()) {
      throw new IOException();
    }
    try (DataInputStream dataInputStream =
        new DataInputStream(new BufferedInputStream(new FileInputStream(file))); ) {

      String[] orderDetails = dataInputStream.readUTF().split(";");

      if (orderDetails.length != 3) {
        throw new WrongOrderFormatException(
            "Tokens in first line are not like they should be. Expected: 3");
      }
      try {
        orderId = Integer.parseInt(orderDetails[0]);
      } catch (Exception e) {
        throw new WrongOrderFormatException("Wrong orderId format. Must be Int!");
      }
      try {
        totalValueToken = Integer.parseInt(orderDetails[1]);
      } catch (Exception e) {
        throw new WrongOrderFormatException("Wrong totalValue format. Must be Int!");
      }
      try {
        orderPizzaAmount = Integer.parseInt(orderDetails[2]);
      } catch (Exception e) {
        throw new WrongOrderFormatException("Wrong pizzaAmount format. Must be Int!");
      }
      simpleOrder.setOrderId(orderId);
      simpleOrder.setTotalValue(totalValueToken);
      String orderDetailsLine;
      while ((orderDetailsLine = dataInputStream.readUTF()) != null) {
        String[] orderDetails2 = orderDetailsLine.split(";");
        if (orderDetails2.length < 3 || orderDetails2.length > 9) {
          throw new WrongOrderFormatException("Wrong order format.");
        }
        for (int i = 0; i < orderPizzaAmount; i++) {
          int pizzaId;
          int currentPizzaPrice;
          SimplePizza pizza;
          String currentTopping;
          Topping topping;
          String pizzaSize;
          try {
            pizzaId = Integer.parseInt(orderDetails2[0]);
          } catch (Exception e) {
            throw new WrongOrderFormatException("Wrong pizzaId format. Must be Int!");
          }
          try {
            currentPizzaPrice = Integer.parseInt(orderDetails2[1]);
          } catch (Exception e) {
            throw new WrongOrderFormatException("Wrong pizzaPrice format. Must be Int!");
          }
          try {
            pizzaSize = orderDetails2[2];
          } catch (Exception e) {
            throw new WrongOrderFormatException("Wrong pizzaSize format. Must be String!");
          }
          switch (pizzaSize) {
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
          for (int j = 3; j < orderDetails2.length; j++) {
            currentTopping = orderDetails2[j];
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
      }
      return simpleOrder;
    }
  }
}
