import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;

/**
 * Ideal Shortest Job First Scheduler
 * Shortest Job First (SJF) Scheduling – The process with the lowest CPU burst time is scheduled first.
 * Non-Preemptive – Once a process starts execution, it cannot be interrupted until it finishes.
 * Uses a Queue (readyQueue) – Stores processes waiting for execution.
 * Selects Process with the Smallest Burst Time – The scheduler scans the queue to find the process with the shortest CPU burst.
 * @version 2017
 */
public class IdealSJFScheduler extends AbstractScheduler {

  // TODO
  private Queue<Process> readyQueue;

  //readyQueue stores processes in a LinkedList (FIFO order).
  //Used for process selection based on burst time
  @Override
  public void initialize(Properties parameters) {
    readyQueue = new LinkedList<>();   //Initialises readyQueue as a LinkedList.
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
    //Adds a new process to the ready queue.
    //Since SJF is non-preemptive, usedFullTimeQuantum is ignored.

  }

  /**
   * Removes the next process to be run from the ready queue 
   * and returns it. 
   * Returns null if there is no process to run.
   */
  public Process schedule() {
    // TODO

    if (readyQueue == null || readyQueue.isEmpty()) {
      return null;  // No process available
    }
    Process selectedProcess = readyQueue.peek();  // First process in queue
    int shortestNextBurstTime = selectedProcess.getNextBurst();
    // Find the process with the shortest burst time
    for (Process process : readyQueue) {
      int nextBurstTime = process.getNextBurst();
      if (nextBurstTime < shortestNextBurstTime) {
        selectedProcess = process;
        shortestNextBurstTime = nextBurstTime;
      }
      System.out.println("Scheduler selects process "+readyQueue.peek());
    }
    readyQueue.remove(selectedProcess); // Remove selected process
    return selectedProcess;
  //Checks if readyQueue is empty → If yes, returns null.
  //Scans all processes in readyQueue.
  //Selects the process with the shortest CPU burst time (getNextBurst()).
  //Removes and returns that process for execution.

  }

  @Override
  public boolean isPreemptive() {
    return false;
  //Returns false because SJF is non-preemptive (once a process starts, it cannot be interrupted).
  }
}
//All processes are added to the queue.
//Scheduler picks the process with the shortest burst time.
//Once selected, the process runs to completion.
//The next process with the shortest burst time is selected.
//Continues until no processes remain.
