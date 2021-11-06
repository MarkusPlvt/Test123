package prog.ex05.solution.masterworker;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import prog.ex05.exercise.masterworker.Task;
import prog.ex05.exercise.masterworker.Worker;

/**
 * A Worker receives tasks to be executed using a queue. Since it is a non-blocking queue a waiting
 * time is defined to be used when the queue is empty.
 */
public class SimpleWorker implements Worker {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SimpleWorker.class);

  private String name;
  // Queue<Task> taskQueue;

  public SimpleWorker(String name) {
    this.name = name;
  }

  /**
   * Assigns a queue to the worker. This queue might be shared between master and all workers.
   *
   * @param queue queue containing task objects
   */
  @Override
  public void setQueue(final ConcurrentLinkedQueue<Task> queue) {
    Queue<Task> taskQueue = new ConcurrentLinkedQueue<>();
  }

  /**
   * Returns the name of the worker. If the worker is a thread, this should be the name of the
   * thread.
   *
   * @return name of the worker
   */
  @Override
  public String getName() {
    return name;
  }

  public void run() {}

  /**
   * Triggers the worker to terminate. If the worker is a thread, no brutal termination method
   * should be used. If the worker is executing a runnable, this execution should not be aborted.
   */
  @Override
  public void terminate() {}
}
