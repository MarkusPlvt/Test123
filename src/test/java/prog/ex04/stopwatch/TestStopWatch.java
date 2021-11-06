package prog.ex04.stopwatch;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import prog.ex04.exercise.stopwatch.StopWatch;
import prog.ex04.solution.stopwatch.SimpleStopWatch;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.*;

import static org.junit.Assert.*;

public class TestStopWatch {

  private final int toleranceInMs = 50;
  private StopWatch stopWatch;

  @Before
  public void setup() {
    stopWatch = new SimpleStopWatch();
  }

  @Test @Ignore
  public void testDoubleReset() {
    stopWatch.reset();

    stopWatch.startNow();
    stopWatch.stopNow();
    stopWatch.reset();
    stopWatch.reset();
    assertTrue(true);
  }

  @Test @Ignore
  public void testResetAfterStop() {
    stopWatch.reset();

    stopWatch.startNow();
    stopWatch.stopNow();
    stopWatch.reset();
    try {
      stopWatch.getStartTime();
      fail(
          "If the stopwatch is reseted, an IllegalStateException must be thrown when calling getStartTime()");
    } catch (IllegalStateException e) {
      try {
        stopWatch.getStopTime();
        fail(
            "If the stopwatch is reseted, an IllegalStateException must be thrown when calling getStopTime()");
      } catch (IllegalStateException e2) {
        assertTrue(true);
      }
    }
  }

  @Test @Ignore
  public void testResetWhileRunning() {
    stopWatch.reset();

    stopWatch.startNow();
    stopWatch.reset();
    try {
      stopWatch.getStartTime();
      fail(
          "If the stopwatch is reseted while running, an IllegalStateException must be thrown when calling getStartTime()");
    } catch (IllegalStateException e) {
      try {
        stopWatch.getStopTime();
        fail(
            "If the stopwatch is reseted while running, an IllegalStateException must be thrown when calling getStopTime()");
      } catch (IllegalStateException e2) {
        assertTrue(true);
      }
    }
  }

  @Test @Ignore
  public void testNormalUse() throws InterruptedException {
    for (int i = 0; i < 2; ++i) {
      stopWatch.reset();

      stopWatch.startNow();
      Thread.sleep(250);
      Duration result = stopWatch.stopNow();

      assertNotNull("The return value of stopNow() is null.", result);
      int resultInMs = result.getNano() / 1000000;
      assertTrue("The expected time would be 250ms, returned were " + resultInMs + "ms",
          250 <= resultInMs && resultInMs <= 250 + toleranceInMs);
    }
  }

  @Test @Ignore
  public void testRobustness() throws InterruptedException {
    for (int i = 0; i < 2; ++i) {
      stopWatch.reset();

      stopWatch.startNow();
      // Check whether a exception is thrown
      try {
        stopWatch.startNow();
        fail("startNow() must throw an IllegalStateException if it's called multiple times.");
      } catch (IllegalStateException e) {
        try {
          stopWatch.getStopTime();
          fail(
              "If the method getStopTime() is called while the Stopwatch is running, an IllegalStateException must occur. ");
        } catch (IllegalStateException e2) {
          // This method, unlike the previous ones, should be executable without problems
          stopWatch.getStartTime();
        }

      }
      Thread.sleep(250);
      Duration result = stopWatch.stopNow();

      assertNotNull("The return value of stopNow() is null.", result);
      int resultInMs = result.getNano() / 1000000;
      assertTrue("The expected time would be 255ms, returned were " + resultInMs + "ms",
          250 <= resultInMs && resultInMs <= 255 + toleranceInMs);
      // Check whether a exception is thrown
      try {
        stopWatch.stopNow();
        fail("stopNow() must throw an IllegalStateException if it's called multiple times.");
      } catch (IllegalStateException e) {
        assertTrue(true);
      }
    }
  }

  @Test @Ignore
  public void testStartTime() {
    stopWatch.reset();

    Instant methodeStartTime;
    methodeStartTime = Instant.now();
    stopWatch.startNow();
    stopWatch.stopNow();

    int difference = stopWatch.getStartTime().getNano() - methodeStartTime.getNano();
    assertTrue(
        "The actual start time differs from the value from getStartTime() by " + difference
            + " ns.",
        -100 <= difference && difference <= 100);
  }

  @Test @Ignore
  public void testStopTime() {
    stopWatch.reset();

    Instant methodeStartTime;
    stopWatch.startNow();
    methodeStartTime = Instant.now();
    stopWatch.stopNow();

    int difference = stopWatch.getStopTime().getNano() - methodeStartTime.getNano();
    assertTrue(
        "The actual start time differs from the value from getStopTime() by " + difference
            + " ns.",
        -100 <= difference && difference <= 100);
  }

  // Required for the following StopWatch test with multiple threads
  private class StopWatchCallable implements Callable<Integer> {
    private final int timeToSleep;

    public StopWatchCallable(int timeToSleep){
      this.timeToSleep = timeToSleep;
    }

    @Override
    public Integer call() throws Exception {
      SimpleStopWatch stopwatch = new SimpleStopWatch();
      stopwatch.startNow();
      Thread.sleep(timeToSleep);
      return stopwatch.stopNow().getNano() / 1000000;
    }
  }


  @Test @Ignore
  public void testMultiThreading() throws InterruptedException, ExecutionException {
    ExecutorService executor = Executors.newFixedThreadPool(3);
    var stopWatchCallable1 = new StopWatchCallable(150);
    var stopWatchCallable2 = new StopWatchCallable(100);
    var stopWatchCallable3 = new StopWatchCallable(50);

    Future<Integer> futureStopwatch1 = executor.submit(stopWatchCallable1);
    Thread.sleep(25);
    Future<Integer> futureStopwatch2 = executor.submit(stopWatchCallable2);
    Thread.sleep(25);
    Future<Integer> futureStopwatch3 = executor.submit(stopWatchCallable3);

    Integer resultStopwatch1 = futureStopwatch1.get();
    Integer resultStopwatch2 = futureStopwatch2.get();
    Integer resultStopwatch3 = futureStopwatch3.get();

    assertTrue("The expected time would be 100ms, returned were " + resultStopwatch1 + "ms",
            150 <= resultStopwatch1 && resultStopwatch1 <= 150 + toleranceInMs);
    assertTrue("The expected time would be 100ms, returned were " + resultStopwatch2 + "ms",
            100 <= resultStopwatch2 && resultStopwatch2 <= 100 + toleranceInMs);
    assertTrue("The expected time would be 100ms, returned were " + resultStopwatch3 + "ms",
            50 <= resultStopwatch3 && resultStopwatch3 <= 50 + toleranceInMs);

    executor.shutdown();
  }
}
