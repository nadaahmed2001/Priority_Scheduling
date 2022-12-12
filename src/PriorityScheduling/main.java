package PriorityScheduling;

import java.util.Arrays;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose: 1-Non-Preempmtive Priority    2-Preemptive Priority");
        int option = sc.nextInt();
        switch (option) {
            case 1:
                NonPreemptive np = new NonPreemptive();
                np.getInput();
                np.nonPreemptivePriorityAlgorithm();
                break;

            case 2:
                Preemptive p = new Preemptive();
                p.getInput();
                p.PreemptivePriorityAlgorithm();

        }

    }

}
