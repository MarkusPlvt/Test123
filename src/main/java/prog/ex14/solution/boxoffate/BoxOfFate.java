package prog.ex14.solution.boxoffate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import prog.ex14.exercise.boxoffate.BoxEmptyException;
import prog.ex14.exercise.boxoffate.SecureBoxOfFate;

/**
 * This is the (Secure)BoxOfFate class.
 *
 * @author Markus Kübler 207273
 * @datum 13.01.2021
 * @version 1.0
 */
public class BoxOfFate<U> implements SecureBoxOfFate<U> {
  private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BoxOfFate.class);

  private List<U> losTrommel = new ArrayList<>();

  /**
   * Adds an object into the BoxOfFate.
   *
   * @param object Object to be added
   * @throws IllegalArgumentException if the object is a null reference
   */
  @Override
  public void add(final U object) throws IllegalArgumentException {
    if (object == null) {
      throw new IllegalArgumentException();
    } else {
      losTrommel.add(object);
    }
  }

  /**
   * Draws randomly one of the objects in the BoxOfFate without removing it. The probabilities are a
   * uniform distribution among the objects in the box.
   *
   * @return object randomly selected
   * @throws BoxEmptyException if there are no objects in the BoxOfFate
   */
  @Override
  public U drawWithReplacement() throws BoxEmptyException {
    if (losTrommel.isEmpty()) {
      throw new BoxEmptyException("There are no objects in the BoxOfFate");
    } else {
      int random = (int) (Math.random() * losTrommel.size());
      return losTrommel.get(random);
    }
  }

  /**
   * Draws randomly one of the objects in the BoxOfFate and removes it from the BoxOfFate. The
   * probabilities are a uniform distribution among the objects in the box.
   *
   * @return object randomly selected
   * @throws BoxEmptyException if there are no objects in the BoxOfFate
   */
  @Override
  public U drawWithoutReplacement() throws BoxEmptyException {
    if (losTrommel.isEmpty()) {
      throw new BoxEmptyException("There are no objects in the BoxOfFate");
    } else {
      int random = (int) (Math.random() * losTrommel.size());
      U var = losTrommel.get(random);
      losTrommel.remove(losTrommel.get(random));
      return var;
    }
  }

  /**
   * Returns the number of objects in the BoxOfFate.
   *
   * @return number of object in the BoxOfFate
   */
  @Override
  public int getSize() {
    return losTrommel.size();
  }

  /**
   * Returns an iterator.
   *
   * @return iterator
   */
  @Override
  public Iterator<U> getIterator() {
    return losTrommel.iterator();
  }
}
