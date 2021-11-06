package examples.generics.badbox;

public class BoxDemo1 {

  public static void main(String[] args) {

    // ONLY place Integer objects into this box!
    Box integerBox = new Box();

    integerBox.add(10);
    Integer someInteger = (Integer) integerBox.get();
    System.out.println(someInteger);
  }
}
