import org.apache.commons.math3.distribution.ExponentialDistribution;

import java.util.concurrent.Semaphore;

public class Lab3 {
    static ExponentialDistribution busTime = new ExponentialDistribution(20 * 60);
    static ExponentialDistribution riderTime = new ExponentialDistribution(30);

    // define variables
    static int waiting = 0;
    static Semaphore mutex = new Semaphore(1);
    static Semaphore bus = new Semaphore(0);
    static Semaphore boraded = new Semaphore(0);


    public static void main(String[] args) {
        // bus creater
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep((long) Lab3.busTime.sample());
                        new Thread(new Bus()).start();
                    } catch (InterruptedException e) {}
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
                    } catch (InterruptedException e) {}
                }
            }
        }).start();

    }
}
