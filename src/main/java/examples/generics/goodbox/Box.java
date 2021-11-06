package examples.generics.goodbox;

/**
 * Generic version of the Box class.
 *
 * @param <T> the type of value being boxed
 */
public class Box<T> {

  private T elem;

  public void add(T t) {
    this.elem = t;
  }

  public T get() {
    return elem;
  }
}

