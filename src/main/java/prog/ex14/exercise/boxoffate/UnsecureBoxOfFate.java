package prog.ex14.exercise.boxoffate;

public interface UnsecureBoxOfFate {

  /**
   * Adds an object into the BoxOfFate.
   *
   * @param object Object to be added
   * @throws IllegalArgumentException if the object is a null reference
   */
  void add(Object object) throws IllegalArgumentException;

  /**
   * Draws randomly one of the objects in the BoxOfFate without removing it.
   * The probabilities are a uniform distribution among the objects in the box.
   *
   * @return object randomly selected
   * @throws BoxEmptyException if there are no objects in the BoxOfFate
   */
  Object drawWithReplacement() throws BoxEmptyException;

  /**
   * Draws randomly one of the objects in the BoxOfFate and removes it from the BoxOfFate.
   * The probabilities are a uniform distribution among the objects in the box.
   *
   * @return object randomly selected
   * @throws BoxEmptyException if there are no objects in the BoxOfFate
   */
  Object drawWithoutReplacement() throws BoxEmptyException;

  /**
   * Returns the number of objects in the BoxOfFate.
   *
   * @return number of object in the BoxOfFate
   */
  int getSize();
}
