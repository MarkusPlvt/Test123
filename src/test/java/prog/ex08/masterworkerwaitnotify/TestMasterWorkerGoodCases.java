package prog.ex08.masterworkerwaitnotify;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import prog.ex08.exercise.masterworkerwaitnotify.Master;
import prog.ex08.exercise.masterworkerwaitnotify.Task;
import prog.ex08.exercise.masterworkerwaitnotify.TaskState;
import prog.ex08.solution.masterworkerwaitnotify.SimpleMaster;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestMasterWorkerGoodCases {
  private static Logger logger = LoggerFactory.getLogger(TestMasterWorkerGoodCases.class);

  private Master master;
  private FailingRunnable failingRunnable;
  private SucceedingRunnable succeedingRunnable;

  @Before
  public void setup() {
    master = new SimpleMaster(4);
    failingRunnable = new FailingRunnable(new NullPointerException("This is a fake exception"),
            100);
    succeedingRunnable = new SucceedingRunnable(100);
  }

  @After
  public void teardown() {
    master.shutdown();
    master = null;
  }

  @Test @Ignore
  public void runOneFailingRunnable() {
    Task task = master.addTask(failingRunnable);
    assertNotNull(task);
    assertNotNull(task.getState());
  }

  @Test @Ignore
  public void runOneSucceedingRunnable() {
    Task task = master.addTask(succeedingRunnable);
    assertNotNull(task);
    assertNotNull(task.getState());
  }

  @Test @Ignore
  public void testGetTask() {
    int taskId = master.addTask(succeedingRunnable).getId();
    assertNotNull(master.getTask(taskId));
  }

  @Test @Ignore
  public void runLotsOfRunnables() throws InterruptedException {
    ConcurrentLinkedQueue<PrimeFactorResult> resultQueue = new ConcurrentLinkedQueue<>();
    StopWatch stopWatch = new SimpleStopWatch();
    stopWatch.startNow();
    List<Integer> taskIdList = new ArrayList<>();
    for (int loop = 0; loop < 300; loop++) {
      logger.info("Starting task for loop value {}", loop);
      taskIdList.add(master.addTask(new PrimeFactorRunnable(loop, resultQueue)).getId());
    }

    Thread.sleep(1000);

    AtomicInteger results = new AtomicInteger();
    resultQueue.stream().forEach((result) -> {
      results.getAndIncrement();
      System.out.println(result);
    });

    int numberResults = results.get();
    assertEquals(300, numberResults);
    Duration duration = stopWatch.stopNow();
    System.out.println("Calculated in millisecs: " + duration.toMillis());
    assertTrue(duration.toMillis() < 2000);
  }
}
