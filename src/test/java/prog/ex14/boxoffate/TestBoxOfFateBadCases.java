package prog.ex14.boxoffate;

import org.junit.Ignore;
import org.junit.Test;
import prog.ex14.exercise.boxoffate.BoxEmptyException;
import prog.ex14.solution.boxoffate.BoxOfFate;

import static org.junit.Assert.fail;

public class TestBoxOfFateBadCases {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(TestBoxOfFateBadCases.class);

  @Test(expected = BoxEmptyException.class)
  public void drawWithoutReplacementFromAnEmptyBox() throws BoxEmptyException {
    BoxOfFate<Shot> shotBoxOfFate = new BoxOfFate<>();
    shotBoxOfFate.drawWithoutReplacement();
    fail("drawing from an empty box should result in a BoxEmptyException.");
  }

  @Test(expected = BoxEmptyException.class)
  public void drawWithReplacementFromAnEmptyBox() throws BoxEmptyException {
    BoxOfFate<Shot> shotBoxOfFate = new BoxOfFate<>();
    shotBoxOfFate.drawWithReplacement();
    fail("drawing from an empty box should result in a BoxEmptyException.");
  }
}
