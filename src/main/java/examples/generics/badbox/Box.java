package examples.generics.badbox;

public class Box {

  private Object object;

  public void add(Object object) {
    this.object = object;
  }

  public Object get() {
    return object;
  }
}

