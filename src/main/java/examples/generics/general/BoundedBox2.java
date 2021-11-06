package examples.generics.general;

public class BoundedBox2<T extends Number> {

  private T elem;

  public void add(T t) {
    this.elem = t;
  }

  public T get() {
    return elem;
  }

  public static void main(String[] args) {

    BoundedBox2<Integer> integerBox = new BoundedBox2<>();
    integerBox.add(10);
    System.out.println(integerBox.get());

    BoundedBox2<Number> numberBox = new BoundedBox2<>();
    numberBox.add(10.2f);
    System.out.println(numberBox.get());
  }
}
