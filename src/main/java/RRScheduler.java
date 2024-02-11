import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;

/**
 * Round Robin Scheduler
 * 
 * @version 2017
 */
public class RRScheduler extends AbstractScheduler {

  // TODO
  private int timeQuantum;
  private Queue<Process> readyQueue;


  @Override
  public void initialize(Properties parameters) {
    timeQuantum = Integer.parseInt(parameters.getProperty("timeQuantum"));
    //System.out.println(timeQuantum +" timeQuantum");                              // needs removing
    readyQueue = new LinkedList<>();
  }


  /**
   * Adds a process to the ready queue.
   * usedFullTimeQuantum is true if process is being moved to ready
   * after having fully used its time quantum.
   */
  @Override     //needs checking
  public void ready(Process process, boolean usedFullTimeQuantum) {
   readyQueue.offer(process);

   // System.out.println(readyQueue+" readyQueue");                                // needs removing
    // TODO

  }

  /**
   * Removes the next process to be run from the ready queue 
   * and returns it. 
   * Returns null if there is no process to run.
   */
  public Process schedule() {

    if (!readyQueue.isEmpty()){
     // System.out.println("Scheduler selects process "+readyQueue.peek());
      return readyQueue.poll();

    }
    // TODO
    return null;

  }

  @Override
  public boolean isPreemptive() {                 // needs checking
    return false;
  }


  /**
   * Returns the time quantum of this scheduler                                     // may not be be needed
   */
  @Override
  public int getTimeQuantum() {
    return timeQuantum;
  }                                 //timeQuantum

}
