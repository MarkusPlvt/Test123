package examples.designpattern.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DemoIteratorSaveRemove {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(DemoIteratorSaveRemove.class);

  /** Demonstrates save remove while iterating. */
  public static void main(String[] args) {
    List<String> nameList = new ArrayList<>();
    nameList.add("hugo");
    nameList.add("egon");
    nameList.add("carla");
    nameList.add("victor");
    nameList.add("jeanne");


    Iterator<String> iterator = nameList.listIterator();

    while (iterator.hasNext()) {
      String tmpString = iterator.next();
      if ("carla".equals(tmpString)) {
        iterator.remove();
      } else {
        System.out.println(tmpString);
      }
    }
  }
}


