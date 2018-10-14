import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author John Berkley
 * CPP Class: CS3700
 * Date Created: Oct 14, 2018
 */
public class UnstructuredPhilosopher implements Runnable {
    private final ReentrantLock leftFork;
    private final ReentrantLock rightFork;

    UnstructuredPhilosopher(ReentrantLock leftFork, ReentrantLock rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    private void think() throws InterruptedException {
        int time = ThreadLocalRandom.current().nextInt(1, 6);
        System.out.println(Thread.currentThread().getName() + " thinking for " + time + " seconds.");
        Thread.sleep(time * 1000);
    }

    private void eat() throws InterruptedException {
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
                leftFork.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " picked up left fork.");
                    System.out.println(Thread.currentThread().getName() + " attempting to acquire right fork.");
                    rightFork.lock();
                    try {
                        System.out.println(Thread.currentThread().getName() + " picked up right fork.");
                        eat();
                    } finally {
                        rightFork.unlock();
                        System.out.println(Thread.currentThread().getName() + " put down right fork.");
                    }
                } finally {
                    leftFork.unlock();
                    System.out.println(Thread.currentThread().getName() + " put down left fork.");

                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
