package prog.ex11.solution.saveandload.factory;

import prog.ex11.exercise.saveandload.factory.PersistenceFactory;
import prog.ex11.exercise.saveandload.factory.WrongOrderFormatException;
import prog.ex11.exercise.saveandload.pizzadelivery.Order;
import prog.ex11.exercise.saveandload.pizzadelivery.PizzaSize;
import prog.ex11.exercise.saveandload.pizzadelivery.Topping;
import prog.ex11.solution.saveandload.pizzadelivery.SimpleOrder;
import prog.ex11.solution.saveandload.pizzadelivery.SimplePizza;

import java.io.*;

/** Alternativklasse von Serializing zur Werte Überprüfung der .txt */
public class SerializingPersistenceFactoryAlternative implements PersistenceFactory, Serializable {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SerializingPersistenceFactory.class);

  SimpleOrder simpleOrder;

  @Override
  public void save(final File file, final Order order) throws IOException {
    this.simpleOrder = (SimpleOrder) order;
    if (!file.canRead() || !file.exists()) {
      throw new IOException();
    } else {
      try (ObjectOutputStream objectOutputStream =
          new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file))); ) {
        objectOutputStream.writeUTF(
            order.getOrderId() + ";" + order.getValue() + ";" + order.getPizzaList().size() + "\n");
        int i = 1;
        int j = 0;

        while (order.getPizzaList().size() >= i) {
          if (this.simpleOrder.getPizza(i).getToppings().size() == 0) {
            objectOutputStream.writeUTF(
                this.simpleOrder.getPizza(i).getPizzaId()
                    + ";"
                    + this.simpleOrder.getPizza(i).getPrice()
                    + ";"
                    + this.simpleOrder.getPizza(i).getSize());
          } else {
            objectOutputStream.writeUTF(
                this.simpleOrder.getPizza(i).getPizzaId()
                    + ";"
                    + this.simpleOrder.getPizza(i).getPrice()
                    + ";"
                    + this.simpleOrder.getPizza(i).getSize()
                    + ";");
          }
          while (this.simpleOrder.getPizza(i).getToppings().size() > j) {
            if (this.simpleOrder.getPizza(i).getToppings().size() == j + 1) {
              objectOutputStream.writeUTF(this.simpleOrder.getPizza(i).getToppings().get(j) + "");
            } else {
              objectOutputStream.writeUTF(this.simpleOrder.getPizza(i).getToppings().get(j) + ";");
            }
            j++;
          }
          objectOutputStream.writeUTF("\n");
          i++;
        }
      } catch (IOException e) {
      }
    }
  }

  @Override
  public Order load(final File file) throws IOException, WrongOrderFormatException {
    int orderId;
    int totalValueToken;
    int orderPizzaAmount;
    if (!file.canRead()) {
      throw new IOException();
    }
    try (ObjectInputStream objectInputStream =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(file))); ) {

      String[] orderDetails = objectInputStream.readUTF().split(";");

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
      while ((orderDetailsLine = objectInputStream.readUTF()) != null) {
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
