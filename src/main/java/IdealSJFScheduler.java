import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;

/**
 * Ideal Shortest Job First Scheduler
 * 
 * @version 2017
 */
public class IdealSJFScheduler extends AbstractScheduler {

  // TODO
  private Queue<Process> readyQueue;

  @Override
  public void initialize(Properties parameters) {
    readyQueue = new LinkedList<>();
  }

  /**
   * Adds a process to the ready queue.
   * usedFullTimeQuantum is true if process is being moved to ready
   * after having fully used its time quantum.
   */
  public void ready(Process process, boolean usedFullTimeQuantum) {

    // TODO
    readyQueue.offer(process);
    //System.out.println(readyQueue +"this readyQueue");

  }

  /**
   * Removes the next process to be run from the ready queue 
   * and returns it. 
   * Returns null if there is no process to run.
   */
  public Process schedule() {


    // TODO

    if (readyQueue == null || readyQueue.isEmpty()) {
      return null;
    }
    Process selectedProcess = readyQueue.peek();
    int shortestNextBurstTime = selectedProcess.getNextBurst();
    for (Process process : readyQueue) {
      int nextBurstTime = process.getNextBurst();
      if (nextBurstTime < shortestNextBurstTime) {
        selectedProcess = process;
        shortestNextBurstTime = nextBurstTime;
      }
      System.out.println("Scheduler selects process "+readyQueue.peek());
    }
    readyQueue.remove(selectedProcess);
    return selectedProcess;


  }

  @Override
  public boolean isPreemptive() {
    return false;
  }
}
