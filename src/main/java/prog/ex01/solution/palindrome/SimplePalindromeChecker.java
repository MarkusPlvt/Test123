package prog.ex01.solution.palindrome;

import prog.ex01.exercise.palindrome.PalindromeChecker;
/**
 * This is the SimplePalindromeChecker class. It checks if the given line is a palindrome or not.
 *
 * @author Markus Kübler 207273
 * @datum 08.10.2020
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

  public char[] normalizeLine(String line) {
    line = line.toLowerCase().replaceAll("[^a-z0-9]", "");
    return line.toCharArray();
  }
}