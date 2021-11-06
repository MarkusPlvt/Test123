package prog.ex14.boxoffate;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import prog.ex14.exercise.boxoffate.BoxEmptyException;
import prog.ex14.solution.boxoffate.BoxOfFate;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestBoxOfFateGoodCases {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(TestBoxOfFateGoodCases.class);

  BoxOfFate<Shot> shotBoxOfFate;
  BoxOfFate<Integer> dice;
  BoxOfFate<RussianEvent> russianRouletteBoxOfFate;

  @Before
  public void setup() {
    shotBoxOfFate = new BoxOfFate<>();
    shotBoxOfFate.add(new Shot("Teachers"));
    shotBoxOfFate.add(new Shot("ÄppleWoi"));
    shotBoxOfFate.add(new Shot("Milch"));
    shotBoxOfFate.add(new Shot("Eierlikör"));
    shotBoxOfFate.add(new Shot("Kölsch"));
    shotBoxOfFate.add(new Shot("Rum"));
    dice = new BoxOfFate<>();
    dice.add(1);
    dice.add(2);
    dice.add(3);
    dice.add(4);
    dice.add(5);
    dice.add(6);
    russianRouletteBoxOfFate = new BoxOfFate<>();
    russianRouletteBoxOfFate.add(RussianEvent.BANG);
    russianRouletteBoxOfFate.add(RussianEvent.CLICK);
    russianRouletteBoxOfFate.add(RussianEvent.CLICK);
    russianRouletteBoxOfFate.add(RussianEvent.CLICK);
    russianRouletteBoxOfFate.add(RussianEvent.CLICK);
    russianRouletteBoxOfFate.add(RussianEvent.CLICK);
  }

  @Test //done
  public void testDistributionWithDice() throws BoxEmptyException {
    int numberOfThrows = 100;
    double epsilonPercentage = 50.0;

    double expectedResult = numberOfThrows / 6.0;
    logger.info("expected Number of draws per side: {}, epsilonPercentage = {}%", expectedResult,
            epsilonPercentage);

    double lowerThreshold = (100-epsilonPercentage) / 100 * expectedResult;
    double upperThreshold = (100+epsilonPercentage) / 100 * expectedResult;
    logger.info("lower / upper threshold = {} / {}", lowerThreshold, upperThreshold);


    int[] resultArray = new int[7];

    for (int loop = 0; loop < numberOfThrows; loop++) {
      int resultOfThrow = dice.drawWithReplacement();
      resultArray[resultOfThrow]++;
    }

    for (int loop = 1; loop < resultArray.length; loop++) {
      logger.info("Index " + loop + " has entries: " + resultArray[loop]);
      assertTrue("Result was outside of treshold values.",lowerThreshold < resultArray[loop] && resultArray[loop] < upperThreshold);
    }
  }

  @Test
  public void testIteratorWithDice() {
    Iterator<Integer> iterator1 = dice.getIterator();
    assertNotNull("Iterator should not be null.", iterator1);
    Iterator<Integer> iterator2 = dice.getIterator();
    assertNotEquals("GetIterator of one dice should return the same Iterator.", iterator1, iterator2);
    int elemCounter = 0;
    Set<Integer> integerSet = new HashSet<>();
    while(iterator1.hasNext()) {
      integerSet.add(iterator1.next());
      elemCounter++;
    }
    assertEquals("Number of elements not correct, should be 6 but was: "+elemCounter+".",6, elemCounter);
    assertEquals("Two or more elements where the same.", 6, integerSet.size());
  }

  @Test
  public void testDrawWithoutReplacementWithShots() throws BoxEmptyException {
    int numberOfShots = shotBoxOfFate.getSize();
    for (int loop = 0; loop < numberOfShots; loop++) {
      assertEquals("getSize should update when elements get removed",
              numberOfShots-loop, shotBoxOfFate.getSize());
      shotBoxOfFate.drawWithoutReplacement();
    }
    // Now the box is empty
    try {
      shotBoxOfFate.drawWithoutReplacement();
      fail("The box should be empty");
    } catch (BoxEmptyException e){
      // success
    }
  }

  @Test
  public void testDrawWithReplacementWithShots() throws BoxEmptyException {
    int numberOfShots = shotBoxOfFate.getSize();
    for (int loop = 0; loop < numberOfShots; loop++) {
      assertEquals("getSize should stay constant when elements get replaced",
              numberOfShots, shotBoxOfFate.getSize());
      shotBoxOfFate.drawWithReplacement();
    }
    assertEquals("getSize should stay constant when elements get replaced",
            numberOfShots, shotBoxOfFate.getSize());
  }

  @Test
  public void testUseIteratorToRemoveElementsFromBox() {
    int numberOfEvents = russianRouletteBoxOfFate.getSize();
    assertEquals(6, numberOfEvents);
    Iterator<RussianEvent> iterator = russianRouletteBoxOfFate.getIterator();
    while(iterator.hasNext()) {
      if (iterator.next().equals(RussianEvent.BANG)) {
        iterator.remove();
      }
      numberOfEvents = russianRouletteBoxOfFate.getSize();
      assertEquals(5, numberOfEvents);
    }

  }
}
