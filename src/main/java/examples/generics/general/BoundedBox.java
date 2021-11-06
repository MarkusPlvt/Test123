package examples.generics.general;

public class BoundedBox<T> {

  private T elem;

  public void add(T t) {
    this.elem = t;
  }

  public T get() {
    return elem;
  }

  public <U extends Number> void inspect(U u) {
    System.out.println("T: " + elem.getClass().getName());
    System.out.println("U: " + u.getClass().getName());
  }

  public static void main(String[] args) {
    BoundedBox<Integer> integerBox = new BoundedBox<>();
    integerBox.add(10);
    // error: this is still String!
    // integerBox.inspect("some text");
    integerBox.inspect(3.14);
  }

}
