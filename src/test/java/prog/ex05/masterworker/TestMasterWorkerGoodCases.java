package prog.ex05.masterworker;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import prog.ex05.exercise.masterworker.Master;
import prog.ex05.exercise.masterworker.Task;
import prog.ex05.exercise.masterworker.TaskState;
import prog.ex05.solution.masterworker.SimpleMaster;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

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

    resultQueue.stream().forEach((result) -> System.out.println(result));
    Duration duration = stopWatch.stopNow();
    System.out.println("Calculated in millisecs: " + duration.toMillis());
    assertTrue(duration.toMillis() < 2000);
  }
}
