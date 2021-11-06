package examples.generics.goodbox;

public class BoxDemoWithGenericType {

  public static void main(String[] args) {

    Box<Integer> integerBox = new Box<Integer>();
    integerBox.add(Integer.valueOf(10));
    // no cast!
    Integer someInteger = integerBox.get();
    System.out.println(someInteger);

//	The following code snipped does not compile:
//        integerBox.add("10");
//        Integer otherInteger = integerBox.get();
//        System.out.println(otherInteger);

  }
}
