package prog.ex04.solution.stopwatch;


import java.time.Duration;
import java.time.Instant;
import prog.ex04.exercise.stopwatch.StopWatch;

public class SimpleStopWatch implements StopWatch {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SimpleStopWatch.class);

  @Override
  public void startNow() throws IllegalStateException {

  }

  @Override
  public Duration stopNow() throws IllegalStateException {
    return null;
  }

  @Override
  public Instant getStartTime() throws IllegalStateException {
    return null;
  }

  @Override
  public Instant getStopTime() throws IllegalStateException {
    return null;
  }

  @Override
  public void reset() {

  }
}
