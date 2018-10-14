import java.util.concurrent.ThreadLocalRandom;

/**
 * @author John Berkley
 * CPP Class: CS3700
 * Date Created: Oct 14, 2018
 */
public class StructuredPhilosopher implements Runnable {
    private final Object leftFork;
    private final Object rightFork;

    public StructuredPhilosopher(Object leftFork, Object rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    public void think() throws InterruptedException {
        int time = ThreadLocalRandom.current().nextInt(1, 6);
        System.out.println(Thread.currentThread().getName() + " thinking for " + time + " seconds.");
        Thread.sleep(time * 1000);
    }

    public void eat() throws InterruptedException {
        int time = ThreadLocalRandom.current().nextInt(1, 11);
        System.out.println(Thread.currentThread().getName() + " eating for " + time + " seconds.");
        Thread.sleep(time * 1000);
    }

    @Override
    public void run() {
        try {
            while (true) {
                think();
                System.out.println(Thread.currentThread().getName() + " attempting to acquire left fork.");
                synchronized (leftFork) {
                    System.out.println(Thread.currentThread().getName() + " picked up left fork.");
                    System.out.println(Thread.currentThread().getName() + " attempting to acquire right fork.");
                    synchronized (rightFork) {
                        System.out.println(Thread.currentThread().getName() + " picked up right fork.");
                        eat();
                        System.out.println(Thread.currentThread().getName() + " put down right fork.");
                    }
                    System.out.println(Thread.currentThread().getName() + " put down left fork.");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
