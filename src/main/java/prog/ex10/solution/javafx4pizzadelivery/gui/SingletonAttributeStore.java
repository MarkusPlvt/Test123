package prog.ex10.solution.javafx4pizzadelivery.gui;

import java.util.HashMap;
import java.util.Map;
import prog.ex10.exercise.javafx4pizzadelivery.gui.AttributeStore;

/**
 * SingeltonAttributeStore Class.
 *
 * @author Markus Kübler 207273
 * @datum 29.11.2020
 * @version 1.0
 */
public class SingletonAttributeStore implements AttributeStore {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SingletonAttributeStore.class);
  private final Map<String, Object> attributeStore;

  private static final SingletonAttributeStore self = new SingletonAttributeStore();

  /**
   * Store for attributes.
   */
  public static SingletonAttributeStore getReference() {
    return self;
  }

  private SingletonAttributeStore() {
    attributeStore = new HashMap<>();
  }

  /**
   * Sets or overwrites an attribute.
   *
   * @param name   name / key of the attribute. Consists of at minimum one readable character
   * @param object object / value registered in the store using the key
   * @throws IllegalArgumentException if a parameter is a null reference or the name is invalid
   */
  @Override
  public void setAttribute(final String name, final Object object) throws IllegalArgumentException {
    if (name == null || name.length() == 0) {
      throw new IllegalArgumentException("Attribute is not valid.");
    } else {
      this.attributeStore.put(name, object);
    }
  }

  /**
   * returns the object registered under the given name.
   *
   * @param name name / key of the object
   * @return object / value stored under the given name
   * @throws IllegalArgumentException if no object is registered under the given name or the
   *                                  given name is invalid or a null reference
   */
  @Override
  public Object getAttribute(final String name) throws IllegalArgumentException {
    if (this.attributeStore.containsKey(name)) {
      return this.attributeStore.get(name);
    } else {
      throw new IllegalArgumentException("Attribute isn't set.");
    }
  }

  /**
   * removes the object registered under the given name.
   *
   * @param name name / key of the object
   * @throws IllegalArgumentException if no object is registered under the given name or the
   *                                  given name is invalid or a null reference
   */
  @Override
  public void removeAttribute(final String name) {
    this.attributeStore.remove(name);
  }
}
