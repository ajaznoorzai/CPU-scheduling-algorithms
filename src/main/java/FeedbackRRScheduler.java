import java.util.*;
import java.util.LinkedList;

/**
 * Feedback Round Robin Scheduler
 * 
 * @version 2017
 *  This Java class implements a Feedback Round Robin (FB-RR) Scheduler,
 which is a type of multilevel feedback queue scheduling algorithm. 
 It uses multiple priority levels and moves processes between levels based on their execution behavior.

 */

public class FeedbackRRScheduler extends AbstractScheduler {

  // TODO
  private Queue<Process> readyQueue; // priority queues for each level  //readyQueue: Stores processes in a FIFO (First-In, First-Out) order.
  private int timeQuantum;           // timeQuantum: The time slice each process gets before preemption. 
  

  public static final int HIGHEST_PRIORITY = 0; //most important 
  public static final int LOWEST_PRIORITY = 1000; // the least important priority


  @Override
  public void initialize(Properties parameters) {
    // Initialise the priority queues for each level
    readyQueue = new LinkedList<>();     // Initializes the readyQueue as a LinkedList (FIFO queue)
    timeQuantum = Integer.parseInt(parameters.getProperty("timeQuantum"));   //Sets timeQuantum from the provided parameters
  }

  /**
   * Adds a process to the ready queue.
   * usedFullTimeQuantum is true if process is being moved to ready
   * after having fully used its time quantum.
   */
  public void ready(Process process, boolean usedFullTimeQuantum) {

    // TODO
    if (usedFullTimeQuantum) {
      process.setPriority(process.getPriority()+1); // Decrease priority (increase value)
      readyQueue.add(process); // Add to queue with new priority
    }else{
      readyQueue.offer(process); // Add process normally
      //If a process uses its full time quantum, it gets a lower priority (higher number).
      //Otherwise, it remains at its current priority level.

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
    // Iterates through priority levels from highest (0) to lowest (1000).
    //Picks the first available process in the queue.
    //Removes it from the queue and returns it.
    //If no process is found, returns null.
    //return null;
  }







  public int getTimeQuantum() {
    return timeQuantum;
    //Returns the time slice assigned to each process.
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

//Processes enter at the highest priority (0).
//Each process gets a fixed timeQuantum.
//If a process completes within its quantum, it remains at its priority.
//If a process uses its entire quantum without finishing, it moves to a lower priority level (priority value increases).
//Lower-priority processes execute only if no higher-priority processes are available.
//Starvation is avoided since all processes eventually get CPU time.
