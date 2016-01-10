package java.lang;

public class Thread implements Runnable {

    /* For autonumbering anonymous threads. */
    private static int threadInitNumber;

    private static synchronized int nextThreadNum() {
        return threadInitNumber++;
    }

    private String name;

    private Runnable target;

    public Thread() {
        init(null, "Thread-" + nextThreadNum(), 0);
    }

    public Thread(Runnable target) {
        init(target, "Thread-" + nextThreadNum(), 0);
    }

    private void init(Runnable target, String name, long stackSize) {
        this.target = target;
        this.name = name;
    }

    @Override
    public void run() {
        if (target != null) {
            target.run();
        }
    }

    public synchronized void start() {
        this.run();
    }

    /**
     * This will block JS.
     * 
     * @param millis
     * @throws InterruptedException
     */
    @Deprecated
    public static void sleep(long millis) throws InterruptedException {
        long target = System.currentTimeMillis() + millis;
        while (System.currentTimeMillis() < target) {
            // block JS
        }
    }

}
