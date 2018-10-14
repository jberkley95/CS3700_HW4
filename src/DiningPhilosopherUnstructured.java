import java.util.concurrent.locks.ReentrantLock;

/**
 * @author John Berkley
 * CPP Class: CS3700
 * Date Created: Oct 14, 2018
 */
public class DiningPhilosopherUnstructured {
    public static void main(String[] args) {
        UnstructuredPhilosopher[] philosophers = new UnstructuredPhilosopher[5];
        ReentrantLock[] forks = new ReentrantLock[philosophers.length];

        for (int i = 0; i < forks.length; i++) {
            forks[i] = new ReentrantLock();
        }

        for (int i = 0; i < philosophers.length; i++) {

            ReentrantLock leftFork = forks[i];
            ReentrantLock rightFork;
            if (i < philosophers.length - 1) {
                rightFork = forks[i + 1];
            } else {
                rightFork = forks[0];
            }

            // make last philosopher pick up right fork first to prevent circular deadlock
            if (i == philosophers.length - 1) {
                philosophers[i] = new UnstructuredPhilosopher(rightFork, leftFork);
            } else {
                philosophers[i] = new UnstructuredPhilosopher(leftFork, rightFork);
            }

            Thread t = new Thread(philosophers[i], "Philosopher " + (i + 1));
            t.start();
        }

    }
}
