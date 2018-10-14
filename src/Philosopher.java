import java.util.concurrent.ThreadLocalRandom;

/**
 * @author John Berkley
 * CPP Class: CS3700
 * Date Created: Oct 14, 2018
 */
public class Philosopher implements Runnable {
    private final Object leftFork;
    private final Object rightFork;

    public Philosopher(Object leftFork, Object rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    public void think() throws InterruptedException {
        int time = ThreadLocalRandom.current().nextInt(1, 6);
        System.out.println("Philosopher " + Thread.currentThread().getName().substring(6) + " thinking for " + time + " seconds.");
        Thread.sleep(time * 1000);
    }

    public void eat() throws InterruptedException {
        int time = ThreadLocalRandom.current().nextInt(1, 11);
        System.out.println("Philosopher " + Thread.currentThread().getName().substring(6) + " eating for " + time + " seconds.");
        Thread.sleep(time * 1000);
    }

    @Override
    public void run() {
        try {
            String philNumber = Thread.currentThread().getName().substring(6);
            while (true) {
                think();
                System.out.println("Philosopher " + philNumber + " attempting to acquire left fork.");
                synchronized (leftFork) {
                    System.out.println("Philosopher " + philNumber + " picked up left fork.");
                    System.out.println("Philosopher " + philNumber + " attempting to acquire right fork.");
                    synchronized (rightFork) {
                        System.out.println("Philosopher " + philNumber + " picked up right fork.");
                        eat();
                        System.out.println("Philosopher " + philNumber + " put down right fork.");
                    }
                    System.out.println("Philosopher " + philNumber + " put down left fork.");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
