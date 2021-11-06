package prog.ex07.solution.javafx4palindrome;

import prog.ex07.exercise.javafx4palindrome.PalindromeChecker;

/**
 * SimplePizzaDeliveryService Class.
 *
 * @author Markus Kübler 207273
 * @datum 11.11.2020
 * @version 1.0
 */
public class SimplePalindromeChecker implements PalindromeChecker {

  /**
   * Checks if the given line is a palindrome.
   *
   * @param line line to be checked
   * @return true, if the line contains a palindrome, false otherwise.
   */
  @Override
  public boolean isPalindrome(final String line) {
    char [] normal = normalizeLine(line);

    if (normal == null) {
      return false;
    }
    int backward = normal.length - 1;
    int forward = 0;
    while (forward < backward) {
      char forwardChar = normal[forward];
      forward++;
      char backwardChar = normal[backward];
      backward--;
      if (forwardChar != backwardChar) {
        return false;
      }
    }
    return true;
  }

  /**
   * Transforms a given String into a char array while ignoring all chars which are neither a letter
   * nor a digit.
   *
   * @param line line to be normalized
   * @return char array only containing chars which are letters or digits
   */
  @Override
  public char[] normalizeLine(final String line) {
    String lineToNormalize = line;
    lineToNormalize = lineToNormalize.toLowerCase().replaceAll("[^a-z0-9]", "");
    return lineToNormalize.toCharArray();
  }
}
