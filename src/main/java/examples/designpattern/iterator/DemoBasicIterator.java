package examples.designpattern.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DemoBasicIterator {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(DemoBasicIterator.class);

  /** Demonstrates simple use of an iterator. */
  public static void main(String[] args) {
    String[] nameArray = {"hugo", "egon", "carla", "victor", "jeanne"};
    List<String> nameList = Arrays.asList(nameArray);

    Iterator<String> iterator = nameList.listIterator();

    while (iterator.hasNext()) {
      String tmpString = iterator.next();
      System.out.println(tmpString);
    }
  }
}
