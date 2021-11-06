package examples.generics.general;

import java.util.ArrayList;
import java.util.Collection;

public class GenericsLimitedUsage<E> {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(GenericsLimitedUsage.class);

  /**
   * THESE EXAMPLES DO NOT WORK! Uncomment the lines to see the compiler messages
   */
  public void myMethod(Object item) {
    // error: illegal generic type for instanceof
//    if (item instanceof E) {
//      logger.info("Object is of generic Type");
//    }

    // error: unexpected type
//    E localItem = new E();

    // error: generic array creation
//    E[] eArray = new E[10];

    // warning: unsafe cast
//    E anotherItem = (E) new Object();

//    Collection collecion = new ArrayList<String>();

    // error: illegal generic type for instanceof
//    if (collecion instanceof Collection<String>) {
//      logger.info("Object is of generic Type");
//    }
  }
}
