package PriorityScheduling;

import java.util.Scanner;

public class NonPreemptive {

    int numOfProcess;
    String process[];
    int burstTime[];
    int priority[];
    int TurnAroundTime[];
    int waitingTime[];
    int totalWaitTime = 0;
    int totalTurnAroundTime = 0;
    double avgWaitingTime;
    double avgTurnAroundTime;

    public NonPreemptive() {
    }

    public void getInput() {
        System.out.print("Enter Number of Process: ");
        Scanner input = new Scanner(System.in);
        numOfProcess = input.nextInt();

        //arry of names of process
        process = new String[numOfProcess];

        int p = 1;
        for (int i = 0; i < numOfProcess; i++) {
            process[i] = "P" + p;
            p++;
        }
        //sort  process according to names
        //System.out.println(Arrays.toString(process));

        System.out.print("Enter Burst Time for the " + numOfProcess + " process: ");

        //array to get the burst time of each process
        burstTime = new int[numOfProcess];
        for (int i = 0; i < numOfProcess; i++) {
            burstTime[i] = input.nextInt();
        }

        //sort burst times
        // System.out.println(Arrays.toString(burstTime));
        //array to store the priority of each process
        System.out.print("Enter Priority for " + numOfProcess + " process: ");

        priority = new int[numOfProcess];
        for (int i = 0; i < numOfProcess; i++) {
            priority[i] = input.nextInt();
        }

    }//End getInput

    public void sortByPriority() {

        //sort priorities
        // System.out.println(Arrays.toString(priority));
// Sorting process by priority
        int temp;
        String temp2;
        for (int i = 0; i < numOfProcess - 1; i++) {
            for (int j = 0; j < numOfProcess - 1; j++) {
                if (priority[j] > priority[j + 1]) {
                    temp = priority[j];
                    priority[j] = priority[j + 1];
                    priority[j + 1] = temp;

                    temp = burstTime[j];
                    burstTime[j] = burstTime[j + 1];
                    burstTime[j + 1] = temp;

                    temp2 = process[j];
                    process[j] = process[j + 1];
                    process[j + 1] = temp2;

                }
            }
        }

    }//End sort

    public void calcTAT_WT() {
        TurnAroundTime = new int[numOfProcess + 1];
        waitingTime = new int[numOfProcess + 1];

// Calculating Waiting Time & Turn Around Time
        waitingTime[0] = 0;
        for (int i = 1; i < numOfProcess; i++) {

            waitingTime[i] = burstTime[i - 1] + waitingTime[i - 1];
            TurnAroundTime[i] = burstTime[i] + waitingTime[i];
            //waitingTime[i + 1] = TurnAroundTime[i];
            //waitingTime[i] = burstTime[i-1] + waitingTime[i-1];
        }

    }//end calculate

    public void getAverageWT_TAT() {
        for (int i = 0; i < numOfProcess; i++) {
            totalTurnAroundTime += (waitingTime[i] + burstTime[i]);
            totalWaitTime += waitingTime[i];

        }

        avgWaitingTime = totalWaitTime / (double) numOfProcess;
        avgTurnAroundTime = totalTurnAroundTime / (double) numOfProcess;

    }

    public void display() {

        System.out.println("\nProcess excution order:  ");
        System.out.println("\nProcesses     Burst Time      Priority      Waiting Time     Turn Around Time");

        for (int i = 0; i < numOfProcess; i++) {

            System.out.println(process[i] + "             " + burstTime[i] + "              " + priority[i]
                    + "               " + waitingTime[i] + "               " + (TurnAroundTime[i]));
        }
        System.out.println("\n Average Wating Time: " + avgWaitingTime);
        System.out.println(" Average Turn Around Time: " + avgTurnAroundTime);

    }//end display

    public void nonPreemptivePriorityAlgorithm() {
        sortByPriority();
        calcTAT_WT();
        getAverageWT_TAT();
        display();
    }

}
