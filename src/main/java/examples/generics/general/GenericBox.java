package examples.generics.general;

public class GenericBox<T> {
  private T elem;

  public void add(T t) {
    this.elem = t;
  }

  public T get() {
    return elem;
  }

  public <U> void inspect(U param) {
    System.out.println("T: " + elem.getClass().getName());
    System.out.println("U: " + param.getClass().getName());
  }

  /**
   * Demo for generic typing of methods.
   *
   * @param args not used.
   */
  public static void main(String[] args) {
    GenericBox<Integer> integerBox = new GenericBox<Integer>();
    integerBox.add(10);
    integerBox.inspect("some text");
  }
}

