import java.util.*;
import java.util.LinkedList;

/**
 * Feedback Round Robin Scheduler
 * 
 * @version 2017
 */
public class FeedbackRRScheduler extends AbstractScheduler {

  // TODO
  private Queue<Process> readyQueue; // priority queues for each level
  private int timeQuantum;

  public static final int HIGHEST_PRIORITY = 0;
  public static final int LOWEST_PRIORITY = 1000;


  @Override
  public void initialize(Properties parameters) {
    // Initialize the priority queues for each level
    readyQueue = new LinkedList<>();
    timeQuantum = Integer.parseInt(parameters.getProperty("timeQuantum"));
  }

  /**
   * Adds a process to the ready queue.
   * usedFullTimeQuantum is true if process is being moved to ready
   * after having fully used its time quantum.
   */
  public void ready(Process process, boolean usedFullTimeQuantum) {

    // TODO
    if (usedFullTimeQuantum) {
      process.setPriority(process.getPriority()+1);
      readyQueue.add(process);
    }else{
      readyQueue.offer(process);

    }




  }

  /**
   * Removes the next process to be run from the ready queue
   * and returns it.
   * Returns null if there is no process to run.
   */
  public Process schedule() {
    // find the process with highest priority level
    for (int priorityLevel = HIGHEST_PRIORITY; priorityLevel <= LOWEST_PRIORITY; priorityLevel++) {
      Iterator<Process> iterator = readyQueue.iterator();
      while (iterator.hasNext()) {
        Process process = iterator.next();
        if (process.getPriority() == priorityLevel) {
          // remove process from ready queue
          iterator.remove();
          // if process has enough time remaining to complete, return it
          if (process != null ) {
            readyQueue.remove(process);

            return process;
          } else {
            // if process does not have enough time remaining to complete, increase its priority and add it back to the ready queue
            return null;
          }
        }
      }
    }
    // if no process can be found in any priority level, return null
    return null;
  }







  public int getTimeQuantum() {
    return timeQuantum;
  }

  /**
   * Returns whether the scheduler is preemptive
   *
   * in our case The scheduler is preemptive. so True
   */
  public boolean isPreemptive() {
    return true;
  }



}