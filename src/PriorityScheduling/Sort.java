//This Class is for Priority scheduling
package PriorityScheduling;

import java.util.Comparator;

/**
 *
 * @author Nada
 */
class Sort implements Comparator<Process> {
    // Used for sorting in ascending order of
    // roll number
    @Override
    public int compare(Process a, Process b)
    {
        return a.arrivalTime - b.arrivalTime;
    }

    
 
}
