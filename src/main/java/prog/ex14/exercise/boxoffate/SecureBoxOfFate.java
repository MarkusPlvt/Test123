package prog.ex14.exercise.boxoffate;

import java.util.Iterator;

/**
 * The BoxOfFate realizes a lottery drum.
 *
 * @param <T> Type of objects the box will accept and return
 */
public interface SecureBoxOfFate<T> {

  /**
   * Adds an object into the BoxOfFate.
   *
   * @param object Object to be added
   * @throws IllegalArgumentException if the object is a null reference
   */
  void add(T object) throws IllegalArgumentException;

  /**
   * Draws randomly one of the objects in the BoxOfFate without removing it.
   * The probabilities are a uniform distribution among the objects in the box.
   *
   * @return object randomly selected
   * @throws BoxEmptyException if there are no objects in the BoxOfFate
   */
  T drawWithReplacement() throws BoxEmptyException;

  /**
   * Draws randomly one of the objects in the BoxOfFate and removes it from the BoxOfFate.
   * The probabilities are a uniform distribution among the objects in the box.
   *
   * @return object randomly selected
   * @throws BoxEmptyException if there are no objects in the BoxOfFate
   */
  T drawWithoutReplacement() throws BoxEmptyException;

  /**
   * Returns the number of objects in the BoxOfFate.
   *
   * @return number of object in the BoxOfFate
   */
  int getSize();

  /**
   * Returns an iterator.
   *
   * @return iterator
   */
  Iterator<T> getIterator();
}
