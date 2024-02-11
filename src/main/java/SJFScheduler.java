import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Properties;

/**
 * Shortest Job First Scheduler
 *
 * @version 2017
 */


public class SJFScheduler extends AbstractScheduler {

  private double initialBurstEstimate;
  private double alphaBurstEstimate;
  private double oldestimate;

  private Queue<Process> readyQueue;


  public void initialize(Properties parameters) {
    initialBurstEstimate = Double.parseDouble(parameters.getProperty("initialBurstEstimate"));
    alphaBurstEstimate = Double.parseDouble(parameters.getProperty("alphaBurstEstimate"));
    readyQueue = new LinkedList<>();
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

  }

  /**
   * Removes the next process to be run from the ready queue
   * and returns it.
   * Returns null if there is no process to run.
   */
  public Process schedule() {
    return readyQueue.poll();
  }

  /**
   * Computes the new burst estimate using exponential averaging.

   */
  private double expAvg(double oldEstimate, double recentBurst) {
    double newestimate = alphaBurstEstimate * recentBurst + (1 - alphaBurstEstimate) * oldEstimate;
    oldestimate = newestimate;
    return newestimate;

  }

}
