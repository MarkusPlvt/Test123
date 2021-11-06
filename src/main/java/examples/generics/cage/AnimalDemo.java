package examples.generics.cage;

public class AnimalDemo {

  /**
   * Simple demo to show subtyping problems in generics.
   *
   * @param args command line args, not used
   */
  public static void main(String[] args) {

    Cage<Animal> animalCage = null;

    Cage<Lion> lionCage = new Cage<>();
    lionCage.add(new Lion());

    Cage<Butterfly> butterflyCage = new Cage<>();
    butterflyCage.add(new Butterfly());

    // The following assignments are not allowed,
    // because Cage<Lion> is not a sub-type of Cage<Animal>
    // and Cage<Butterfly> also is not a sub-type of Cage<Animal>
    // animalCage = lionCage; // NOK
    // animalCage = butterflyCage; // NOK

    // When using the ? wildcard, sub-typing is allowed:
    Cage<? extends Animal> someAnimalCage = null;
    someAnimalCage = lionCage; // OK
    someAnimalCage = butterflyCage; // OK

    // but NOK:
    //someAnimalCage.add(new Lion());
  }
}
