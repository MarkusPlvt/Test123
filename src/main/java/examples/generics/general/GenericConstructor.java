package examples.generics.general;

import java.time.LocalDate;

public class GenericConstructor<X> {
  private X content;

  <T> GenericConstructor(T t) {
    System.out.println("Type of T ist: " + t.getClass().getName());
  }

  public X getContent() {
    return content;
  }

  public static void main(String[] args) {
    GenericConstructor<Integer> gc1 = new GenericConstructor<>(LocalDate.now());
    GenericConstructor<Integer> gc2 = new <String>GenericConstructor<Integer>("Test");
  }
}

