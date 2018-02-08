public class Rider implements Runnable{
    public void run() {
        try {
            System.out.print((char) 27 + "[92m [" + Thread.currentThread().getId()
                    + " Created a rider]" + ((char) 27) + "[0m ");

            Lab3.mutex.acquire();
            Lab3.waiting += 1;
            Lab3.mutex.release();

            Lab3.bus.acquire();
            System.out.print((char) 27 + "[95m [Boarded " +
                    Thread.currentThread().getId() + "] " + ((char) 27) + "[0m");
            Lab3.boraded.release();
        } catch (InterruptedException e) {}
    }
}