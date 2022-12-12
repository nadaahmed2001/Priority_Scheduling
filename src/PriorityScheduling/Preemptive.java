package PriorityScheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.*;

public class Preemptive {

    int numOfProcess = 0;
    int TurnAroundTime[];
    int waitingTime[];
    int totalWaitTime = 0;

    int totalTurnAroundTime = 0;

    int currentTime = 0;
    int totalBurstTime = 0;
    ArrayList<Process> ProcessArray = new ArrayList<>();
    Scanner input = new Scanner(System.in);

    public Preemptive() {
    }

    public void getInput() {
        System.out.print("Enter Number of Process:   ");
        numOfProcess = input.nextInt();

        int bt = 0, pr = 0, at = 0, id = 1;
        for (int i = 0; i < numOfProcess; i++) {
            System.out.print("Enter Burst Time for P" + (i + 1) + ":   ");
            bt = input.nextInt();
            totalBurstTime += bt;

            System.out.print("Enter Priority for p" + (i + 1) + ":   ");
            pr = input.nextInt();

            System.out.print("Enter Arrival Time for p" + (i + 1) + ":   ");
            at = input.nextInt();

            ProcessArray.add(new Process(id, at, bt, pr));

            id++;
            ProcessArray.get(i).tempburstTime = bt;

        }

    }//End getInput

    public void sortByAT() {

// Sorting process by arrival time
        Collections.sort(ProcessArray, new Sort());

    }//End sort

    //function to get all process arrived in current time
    public ArrayList<Process> getCurrentProcesses(ArrayList<Process> p, int currentTime) {

        ArrayList<Process> currProc = new ArrayList<>();
        for (int i = 0; i < p.size(); i++) {
            if (p.get(i).arrivalTime >= 0 && p.get(i).arrivalTime <= currentTime && p.get(i).burstTime > 0) {
                currProc.add(p.get(i));
            }
        }
        return currProc;

    }

    //function to choose the process wit heighst priority
    public Process GetProcessWithHighestPriority(ArrayList<Process> p) {

        Process max = new Process();//Process that has max priority

        if (p.size() == 1) {
            return p.get(0);
        } else {
            max.setID(p.get(0).getProcessID());
            max.setArrivalTime(p.get(0).getArrivalTime());
            max.setBurstTime(p.get(0).getburstTime());
            max.setPriority(p.get(0).getPriority());

            for (int i = 1; i < p.size(); i++) {
                if (p.get(i).priority < max.priority) {
                    max.setID(p.get(i).getProcessID());
                    max.setArrivalTime(p.get(i).getArrivalTime());
                    max.setBurstTime(p.get(i).getburstTime());
                    max.setPriority(p.get(i).getPriority());
                }
            }
        }

//max.outtime=
        return max;
    }
//schedule process

    public void scheduling() {

        ArrayList<Process> currProc = new ArrayList<>();
        Process max = new Process();
        int id = 0;
        while (currentTime < totalBurstTime) {

            currProc = getCurrentProcesses(ProcessArray, currentTime);
            max = GetProcessWithHighestPriority(currProc);

            for (int i = 0; i < ProcessArray.size(); i++) {
                if (max.processID == ProcessArray.get(i).processID) {
                    (ProcessArray.get(i).burstTime)--;

                    if (ProcessArray.get(i).intime == -1) {

                        ProcessArray.get(i).intime = currentTime;

                        ProcessArray.get(i).waitingTime = currentTime - ProcessArray.get(i).arrivalTime;

                    } else if (ProcessArray.get(i).intime != -1 && ProcessArray.get(i).processID != id) {

                        ProcessArray.get(i).intime = currentTime;
                        ProcessArray.get(i).waitingTime += currentTime - ProcessArray.get(i).outtime;

                    }
                    ProcessArray.get(i).outtime = (currentTime + 1);
                    break;
                }

            }

            System.out.println("process id = " + max.processID + "       current time = " + currentTime);

            id = max.processID;//To know the past process 
            currentTime++;

        }

    }

    public void displayTime() {
        for (int i = 0; i < numOfProcess; i++) {
            ProcessArray.get(i).TurnaroundTime = ProcessArray.get(i).waitingTime + ProcessArray.get(i).tempburstTime;
            System.out.println("P" + (i + 1) + "---> Waiting Time" + ": " + ProcessArray.get(i).waitingTime + "        TurnAround time: " + ProcessArray.get(i).TurnaroundTime);
            totalWaitTime += ProcessArray.get(i).waitingTime;
            totalTurnAroundTime += ProcessArray.get(i).TurnaroundTime;
        }
        System.out.println("Average Waiting Time= " + ((double) totalWaitTime / numOfProcess));
        System.out.println("Average TurnAround Time= " + ((double) totalTurnAroundTime / numOfProcess));

    }

    public void PreemptivePriorityAlgorithm() {
        sortByAT();
        scheduling();
        displayTime();
    }

}
