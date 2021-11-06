package prog.ex08.masterworkerwaitnotify;

import org.junit.Ignore;
import org.junit.Test;
import prog.ex08.solution.masterworkerwaitnotify.SimpleMaster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class TestWorkerThreads {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(TestWorkerThreads.class);

  private List<Thread> getAllThreadsOfGroup() {
    int activeThreads = Thread.currentThread().getThreadGroup().activeCount();
    Thread[] threadField = new Thread[2 * activeThreads];
    int storedThreads = Thread.currentThread().getThreadGroup().enumerate(threadField);
    if (storedThreads == threadField.length) {
      logger.warn("Thread field completely filled. Maybe some threads are missing.");
    }
    List<Thread> threadList = new ArrayList<>();
    threadList.addAll(Arrays.asList(threadField));
    return threadList;
  }

  private Map<String, Thread> getThreadMap(List<Thread> threadList) {
    Map<String, Thread> threadMap = new HashMap<>();
    for (Thread thread : threadList) {
      if (thread == null) {
        continue;
      }
      threadMap.put(thread.getName(), thread);
    }
    return threadMap;
  }

  @Test @Ignore
  public void checkWorkerNames() throws InterruptedException {
    Map<String, Thread> threadMap = getThreadMap(getAllThreadsOfGroup());
    logger.info("activeThreads = " + threadMap.size());
    SimpleMaster master = new SimpleMaster(4);
    Thread.sleep(200);
    List<String> names = master.getWorkerNames();
    threadMap = getThreadMap(getAllThreadsOfGroup());
    logger.info("activeThreads = " + threadMap.size());
    assertTrue(allThreadNamesAreValid(threadMap, names));
    master.shutdown();
    Thread.sleep(800);
    threadMap = getThreadMap(getAllThreadsOfGroup());
    logger.info("activeThreads = " + threadMap.size());
    assertTrue(noThreadNameIsValid(threadMap, names));
  }

  @Test @Ignore
  public void checkWorkerNamesAreUnique() throws InterruptedException {
    SimpleMaster master = new SimpleMaster(4);
    Thread.sleep(200);
    List<String> names = master.getWorkerNames();
    assertTrue(allThreadNamesAreUnique(names));
  }

  private boolean allThreadNamesAreValid(final Map<String, Thread> threadMap,
                                         final List<String> names) {
    for (String name : names) {
      if (!threadMap.containsKey(name)) {
        return false;
      }
    }
    return true;
  }

  private boolean allThreadNamesAreUnique(final List<String> names) {
    Set<String> nameSet = new HashSet<>();
    for (String name : names) {
      nameSet.add(name);
    }

    if (names.size() == nameSet.size()) {
      return true;
    }
    return false;
  }

  private boolean noThreadNameIsValid(final Map<String, Thread> threadMap,
                                         final List<String> names) {
    for (String name : names) {
      if (threadMap.containsKey(name)) {
        return false;
      }
    }
    return true;
  }
}

