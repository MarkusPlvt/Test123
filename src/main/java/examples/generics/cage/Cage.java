package examples.generics.cage;

import java.util.ArrayList;
import java.util.List;

public class Cage<T extends Animal> {

  private List<T> elements;

  public Cage() {
    elements = new ArrayList<>();
  }

  public void add(T animal) {
    elements.add(animal);
  }

}
