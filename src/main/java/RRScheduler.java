import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;

/**
 * Round Robin Scheduling – Each process gets a fixed time quantum.
 * Preemptive Scheduling – If a process does not complete within the time quantum, it is moved to the back of the queue.
 * FIFO Queue (readyQueue) – Stores processes in arrival order.
 * Fair CPU Allocation – Ensures each process gets an equal share of CPU time.
 * @version 2017
 */
public class RRScheduler extends AbstractScheduler {

  // TODO
  private int timeQuantum; //timeQuantum: The fixed time slice for each process.
  private Queue<Process> readyQueue; //readyQueue: A FIFO queue (LinkedList) to store processes in arrival order.


  @Override
  public void initialize(Properties parameters) {
    timeQuantum = Integer.parseInt(parameters.getProperty("timeQuantum"));    //
    //System.out.println(timeQuantum +" timeQuantum");                              // needs removing
    readyQueue = new LinkedList<>();
  } //Retrieves time quantum value from the parameters.
    //Initialises readyQueue as a LinkedList.


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
      return readyQueue.poll();  // Retrieves and removes the next process

    }
    // TODO
    return null;  //// No process available

  }

  @Override
  public boolean isPreemptive() {                 // needs checking 
    return false;     // Returns true since Round Robin is a preemptive scheduler.
  }


  /**
   * Returns the time quantum of this scheduler                                     // may not be be needed
   */
  @Override
  public int getTimeQuantum() {
    return timeQuantum;
  }                                 //Returns the time quantum

}
