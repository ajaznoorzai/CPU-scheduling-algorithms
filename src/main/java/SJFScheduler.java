import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Properties;

/**
 * Shortest Job First Scheduler
 *This Java class implements the Shortest Job First (SJF) Scheduler using Exponential 
 *Burst Time Estimation to predict process burst times dynamically. 
 *This helps in selecting the shortest process based on predicted CPU bursts rather than fixed values.
 * @version 2017
 */


public class SJFScheduler extends AbstractScheduler {

  private double initialBurstEstimate; //Initial burst time estimate.
  private double alphaBurstEstimate;  //Weight factor for exponential averaging
  private double oldestimate;  //Stores the last predicted burst time.

  private Queue<Process> readyQueue; //FIFO queue storing processes.


  public void initialize(Properties parameters) {
    initialBurstEstimate = Double.parseDouble(parameters.getProperty("initialBurstEstimate"));
    alphaBurstEstimate = Double.parseDouble(parameters.getProperty("alphaBurstEstimate"));
    readyQueue = new LinkedList<>();  
    //Reads initialBurstEstimate and alphaBurstEstimate from input parameters.
    //Initializes readyQueue as a LinkedList.
  }

  /**
   * Adds a process to the ready queue.
   * usedFullTimeQuantum is true if process is being moved to ready
   * after having fully used its time quantum.
   */
  public void ready(Process process, boolean usedFullTimeQuantum) {
    // process.setPriority((int) expAvg(process.getPriority(), process.getRecentBurst()));
    process.setPriority((int) expAvg(oldestimate, process.getRecentBurst()));
    readyQueue.offer(process);

    //Predicts next burst time using exponential averaging.
    //Sets process priority based on estimated burst time (lower values indicate shorter burst times).
    //Adds the process to readyQueue.

  }

  /**
   * Removes the next process to be run from the ready queue
   * and returns it.
   * Returns null if there is no process to run.
   */
  public Process schedule() {
    return readyQueue.poll();  // Retrieves and removes the first process in the queue
}
  }

  /**
   * Computes the new burst estimate using exponential averaging.

   */
  private double expAvg(double oldEstimate, double recentBurst) {
    double newestimate = alphaBurstEstimate * recentBurst + (1 - alphaBurstEstimate) * oldEstimate;
    oldestimate = newestimate;
    return newestimate;
   //Uses exponential averaging to predict the next burst time.
   //Stores the new estimate in oldestimate.
  }

}
//Each process has an estimated burst time.
//When a process completes, its actual burst time updates the estimate.
//Scheduler picks the process with the shortest predicted burst time.
