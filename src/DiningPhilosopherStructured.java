/**
 * @author John Berkley
 * CPP Class: CS3700
 * Date Created: Oct 14, 2018
 */
public class DiningPhilosopherStructured {
    public static void main(String[] args) {
        StructuredPhilosopher[] philosophers = new StructuredPhilosopher[5];
        Object[] forks = new Object[philosophers.length];

        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Object();
        }

        for (int i = 0; i < philosophers.length; i++) {

            Object leftFork = forks[i];
            Object rightFork;
            if (i < philosophers.length - 1) {
                rightFork = forks[i + 1];
            } else {
                rightFork = forks[0];
            }

            // make last philosopher pick up right fork first to prevent circular deadlock
            if (i == philosophers.length - 1) {
                philosophers[i] = new StructuredPhilosopher(rightFork, leftFork);
            } else {
                philosophers[i] = new StructuredPhilosopher(leftFork, rightFork);
            }

            Thread t = new Thread(philosophers[i], "Philosopher " + (i + 1));
            t.start();
        }

    }
}
