package examples.lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FirstExamples {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(FirstExamples.class);

  /**
   * Demo some lambda expressions
   */
  public static void main(String[] args) {
    String[] names = {"hugo", "egon", "erna", "paul", "frida", "anna-ida", "karl-friedrich",
            "hilde", "ruth", "sarah", "karl", "ferdinand", "eva", "bo"};
    List<String> nameList = Arrays.asList(names);
    nameList.forEach((name) -> System.out.println("name = " + name));

    System.out.println("Sorted by length");
    Collections.sort(nameList, (str1, str2) -> Integer.compare(str1.length(), str2.length()));
    nameList.forEach((name) -> System.out.println("name = " + name));

    // Arrays.asList does not work due to boxing problem
    int[] values = {1, 4, 6, 5, 8, 22, 54, 12, 23, 42};
    List<Integer> valueList = Arrays.stream(values).boxed().collect(Collectors.toList());
    valueList.forEach((value) -> System.out.println("square = " + value * value));
  }
}
