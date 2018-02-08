public class Bus implements Runnable{
    public void run() {
        try {
            System.out.print((char) 27 +"[34m \n[" + Thread.currentThread().getId()
                    + " Create Bus]" + (char) 27 + "[0m ");

            Lab3.mutex.acquire();
            int n = Math.min(50, Lab3.waiting);
            for(int i = 0; i < n; i++) {
                Lab3.bus.release();
                Lab3.boraded.acquire();
            }

            Lab3.waiting = Math.max(Lab3.waiting - 50, 0);
            Lab3.mutex.release();

            System.out.print((char) 27 +"[31m Departed " +
                    Thread.currentThread().getId() + "\n" + (char) 27 + "[0m");

        } catch (InterruptedException e) {}
    }
}