import java.util.Queue;
import java.util.LinkedList;

/**
 * First-Come First-Served Scheduler, which is a basic CPU scheduling algorithm where the process that arrives first gets executed first
 * 
 * @author Peter Schrammel
 * @version 2017
 */
public class FcfsScheduler extends AbstractScheduler {   //The FcfsScheduler class extends AbstractScheduler, inherits 

  private Queue<Process> readyQueue;   

 /**
   * Creates an instance of the FCFS scheduler
   */
  public FcfsScheduler() {
    readyQueue = new LinkedList<Process>();    //Initialises readyQueue as a LinkedList, ensuring that processes are handled in the order they arrive.
    //  System.out.println(readyQueue);
  }

  /**
   * Adds a process to the ready queue.
   */
  public void ready(Process process, boolean usedFullTimeQuantum) {   //ready method it adds a new process in to the queue 
    readyQueue.offer(process);  //which safely adds the process to the end of the queue.
    //  System.out.println(readyQueue);
  }


  /**
   * Removes the next process to be run from the ready queue 
   * and returns it. 
   * Returns null if there is no process to run.
   */
  public Process schedule() {
    System.out.println("Scheduler selects process "+readyQueue.peek());
    return readyQueue.poll();
  }
}
