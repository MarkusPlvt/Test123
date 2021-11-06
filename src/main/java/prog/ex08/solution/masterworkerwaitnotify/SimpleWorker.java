package prog.ex08.solution.masterworkerwaitnotify;

import java.util.concurrent.ConcurrentLinkedQueue;
import prog.ex08.exercise.masterworkerwaitnotify.Task;
import prog.ex08.exercise.masterworkerwaitnotify.TaskState;
import prog.ex08.exercise.masterworkerwaitnotify.Worker;

/**
 * SimpleWorker Class.
 *
 * @author Markus Kübler 207273
 * @datum 16.11.2020
 * @version 1.0
 */
public class SimpleWorker extends Thread implements Worker, Runnable {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SimpleWorker.class);
  private ConcurrentLinkedQueue<Task> queue = new ConcurrentLinkedQueue<>();
  private boolean stop;
  private Task currentTask;

  /**
   * Constructor of SimpleWorker.
   *
   * @param name name of worker
   * @param queue taskQueue
   */
  public SimpleWorker(String name, ConcurrentLinkedQueue<Task> queue) {
    super(name);
    this.setQueue(queue);
    start();
  }

  /**
   * Assigns a queue to the worker. This queue might be shared between master and all workers.
   *
   * @param queue queue containing task objects
   */
  @Override
  public void setQueue(final ConcurrentLinkedQueue<Task> queue) {
    this.queue = queue;
  }

  /**
   * Triggers the worker to terminate. If the worker is a thread, no brutal termination method
   * should be used. If the worker is executing a runnable, this execution should not be aborted.
   */
  @Override
  public void terminate() {
    this.stop = true;
  }

  @Override
  public void run() {
    while (!this.stop) {
      synchronized (this.queue) {
        if (this.queue == null || this.queue.size() == 0) {
          try {
            this.queue.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        } else {
          this.currentTask = this.queue.peek();
          this.currentTask.setState(TaskState.RUNNING);
          this.currentTask.getRunnable().run();
          this.currentTask.setState(TaskState.SUCCEEDED);
          this.currentTask = null;
          this.queue.poll();
        }
      }
    }
  }
}
