package prog.ex11.solution.saveandload.factory;

import java.io.*;
import prog.ex11.exercise.saveandload.factory.PersistenceFactory;
import prog.ex11.exercise.saveandload.factory.WrongOrderFormatException;
import prog.ex11.exercise.saveandload.pizzadelivery.Order;


/**
 * SerializingPersistenceFactory Class.
 *
 * @author Markus Kübler 207273
 * @datum 16.12.2020
 * @version 1.0
 */
public class SerializingPersistenceFactory implements PersistenceFactory, Serializable {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SerializingPersistenceFactory.class);

  @Override
  public void save(final File file, final Order order) throws IOException {
    if (!file.canRead() || !file.exists() || order == null) {
      throw new IOException();
    } else {
      try (ObjectOutputStream objectOutputStream =
          new ObjectOutputStream(new FileOutputStream(file)); ) {
        objectOutputStream.writeObject(order);
      } catch (EOFException | NotSerializableException e) {
      }
    }
  }

  @Override
  public Order load(final File file) throws IOException, WrongOrderFormatException {
    if (!file.canRead()) {
      throw new IOException();
    }
    try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file)); ) {
      Order order = (Order) objectInputStream.readObject();
      objectInputStream.close();
      return order;
    } catch (Exception e) {
      throw new WrongOrderFormatException(
          "the content of the file cannot successfully be parsed into an Order object.");
    }
  }
}
