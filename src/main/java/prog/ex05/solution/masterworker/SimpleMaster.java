package prog.ex05.solution.masterworker;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import prog.ex05.exercise.masterworker.Master;
import prog.ex05.exercise.masterworker.Task;
import prog.ex05.exercise.masterworker.TaskState;
import prog.ex05.exercise.masterworker.Worker;

/**
 * A Master is the coordinator in the master-worker design pattern. The master receives runnables to
 * be worked on by the worker threads. Depending on the executing machine, the number of worker
 * threads can be parameterized in the constructor of an implementation of the Master interface.
 */
public class SimpleMaster implements Master {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SimpleMaster.class);

  public List<Worker> allWorkers = new ArrayList<>();
  private Queue<Task> taskQueue = new ConcurrentLinkedQueue<>();

  /**
   * Constructor SimpleMaster.
   * @param numberOfWorkers Number of Workers.
   */
  public SimpleMaster(int numberOfWorkers) {
    if (numberOfWorkers < 1) {
      throw new IllegalArgumentException("NumberOfWorkers is under 1.");
    } else {
      for (int i = 0; i < numberOfWorkers; i++) {
        Worker worker = new SimpleWorker("Worker" + i);
        allWorkers.add(worker);
      }
    }
  }

  /**
   * Adds a task to the master-worker team.
   *
   * @param runnable Runnable to be executed by one of the workers
   * @return A Task object with a unique id and the state of the task
   * @throws IllegalArgumentException if the runnable is a null reference
   */
  @Override
  public Task addTask(final Runnable runnable) throws IllegalArgumentException {
    Task currentTask;
    if (runnable == null) {
      throw new IllegalArgumentException("runnable is null.");
    } else {
      currentTask = new Task(runnable);
      taskQueue.add(currentTask);
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
    for (int i = 0; i < allWorkers.size(); i++) {}
    return numberOfWorkers;
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
    for (Worker worker : allWorkers) {
      worker.terminate();
    }
  }
}
