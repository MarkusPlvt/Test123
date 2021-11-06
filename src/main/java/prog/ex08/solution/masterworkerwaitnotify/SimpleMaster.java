package prog.ex08.solution.masterworkerwaitnotify;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import prog.ex08.exercise.masterworkerwaitnotify.Master;
import prog.ex08.exercise.masterworkerwaitnotify.Task;
import prog.ex08.exercise.masterworkerwaitnotify.TaskState;
import prog.ex08.exercise.masterworkerwaitnotify.Worker;

/**
 * SimpleMaster Class.
 *
 * @author Markus Kübler 207273
 * @datum 16.11.2020
 * @version 1.0
 */
public class SimpleMaster implements Master {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SimpleMaster.class);

  private SimpleWorker[] allWorkers;
  private ConcurrentLinkedQueue<Task> taskQueue;
  private List<String> workerNames = new ArrayList<>();
  private int numberOfWorkers;

  /**
   * Constructor SimpleMaster.
   *
   * @param numberOfWorkers Number of Workers.
   */
  public SimpleMaster(final int numberOfWorkers) {
    if (numberOfWorkers < 1) {
      throw new IllegalArgumentException("NumberOfWorkers is under 1.");
    } else {
      allWorkers = new SimpleWorker[numberOfWorkers];
      taskQueue = new ConcurrentLinkedQueue<>();
      for (int i = 0; i < numberOfWorkers; i++) {
        allWorkers[i] = new SimpleWorker("Worker " + i, taskQueue);
        allWorkers[i].setQueue(taskQueue);
        new Thread(allWorkers[i], "Worker " + i).start();
      }
    }
  }

  /**
   * Adds a task to the master-worker team.
   *
   * @param runnable Runnable to be executed by one of the workers
   * @return A Task object with an unique id and the state of the task
   * @throws IllegalArgumentException if the runnable is a null reference
   */
  @Override
  public Task addTask(final Runnable runnable) throws IllegalArgumentException {
    Task currentTask = new Task(runnable);
    if (runnable == null) {
      throw new IllegalArgumentException("runnable is null.");
    } else {
      synchronized (taskQueue) {
        this.taskQueue.add(currentTask);
        this.taskQueue.notify();
      }
    }
    return currentTask;
  }

  /**
   * Returns the actual TaskState of the task.
   *
   * @param taskId id of the task
   * @return actual TaskState
   * @throws IllegalArgumentException if the taskId is not known within the Master
   */
  @Override
  public TaskState getTaskState(final int taskId) throws IllegalArgumentException {
    for (Task task : taskQueue) {
      if (task.getId() == taskId) {
        return task.getState();
      }
    }
    throw new IllegalArgumentException("taskId is not known within the Master.");
  }

  /**
   * Returns the task object belonging to the taskId.
   *
   * @param taskId id of the requested task
   * @return Task object belonging to the taskId
   * @throws IllegalArgumentException if the taskId is not known within the Master
   */
  @Override
  public Task getTask(final int taskId) throws IllegalArgumentException {
    for (Task task : taskQueue) {
      if (task.getId() == taskId) {
        return task;
      }
    }
    throw new IllegalArgumentException("taskId is not known within the Master.");
  }

  /**
   * Returns the number of worker threads configured.
   *
   * @return number of worker threads
   */
  @Override
  public int getNumberOfWorkers() {
    int numberOfWorkers = 0;
    for (int i = 0; i < allWorkers.length; i++) {}
    return numberOfWorkers;
  }

  /**
   * Returns the names of the worker threads.
   *
   * @return List of names of the worker threads.
   */
  @Override
  public List<String> getWorkerNames() {
    for (Worker worker : allWorkers) {
      workerNames.add(worker.getName());
    }
    return workerNames;
  }

  /**
   * Returns the number of queued tasks.
   *
   * @return number of tasks which are not yet taken by the workers.
   */
  @Override
  public int getNumberOfQueuedTasks() {
    int queuedTask = 0;
    for (Task task : taskQueue) {
      if (task.getState() == TaskState.QUEUED) {
        queuedTask++;
      }
    }
    return queuedTask;
  }

  /** Shuts the whole master-worker team down. It triggers the termination of the worker threads. */
  @Override
  public void shutdown() {
    synchronized (this.taskQueue) {
      for (Worker worker : allWorkers) {
        worker.terminate();
      }
      this.taskQueue.notifyAll();
    }
  }
}
