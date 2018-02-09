import java.util.Scanner;
import org.apache.commons.math3.distribution.ExponentialDistribution;

import java.util.concurrent.Semaphore;

public class Lab3 {
   static Scanner sc = new Scanner(System.in);

    static ExponentialDistribution busTime;
    static ExponentialDistribution riderTime;

    // define variables
    static int waiting = 0;
    static Semaphore mutex = new Semaphore(1);
    static Semaphore bus = new Semaphore(0);
    static Semaphore boraded = new Semaphore(0);

    public static void main(String[] args) {
        //get preffered time of execution from user
        System.out.println("Enter 1 to simulate the program at the means of 20 minutes and 30 seconds\n"
                + "Enter any other integer to simulate the program at the means of 1.2 seconds and 0.03 seconds");
        int ratio = sc.nextInt();
        if (ratio == 1) {
            busTime = new ExponentialDistribution(20 * 60 * 1000);
            riderTime = new ExponentialDistribution(30 * 1000);
        } else {
            busTime = new ExponentialDistribution(20 * 60);
            riderTime = new ExponentialDistribution(30);
        }
        // bus creater
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep((long) Lab3.busTime.sample());
                        new Thread(new Bus()).start();
                    } catch (InterruptedException e) {
                    }
                }
            }
        }).start();

        // bus creater
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep((long) Lab3.riderTime.sample());
                        new Thread(new Rider()).start();
                    } catch (InterruptedException e) {
                    }
                }
            }
        }).start();

    }
}
