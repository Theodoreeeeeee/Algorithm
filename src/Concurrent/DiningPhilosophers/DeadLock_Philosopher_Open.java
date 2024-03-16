package Concurrent.DiningPhilosophers;

/**
 * 哲学家就餐问题，解决死锁
 * 通过混入1或多个左撇子， index % 2 == 0 时先抢占和 index % 2 == 1 抢占不同位置的资源，以解决死锁
 */
public class DeadLock_Philosopher_Open {
    public static void main(String[] args) {
        ChopStick cs0 = new ChopStick();
        ChopStick cs1 = new ChopStick();
        ChopStick cs2 = new ChopStick();
        ChopStick cs3 = new ChopStick();
        ChopStick cs4 = new ChopStick();

        Philosopher p0 = new Philosopher("p0", 0, cs0, cs1);
        Philosopher p1 = new Philosopher("p1", 1, cs1, cs2);
        Philosopher p2 = new Philosopher("p2", 2, cs2, cs3);
        Philosopher p3 = new Philosopher("p3", 3, cs3, cs4);
        Philosopher p4 = new Philosopher("p4", 4, cs4, cs0);

        p0.start();
        p1.start();
        p2.start();
        p3.start();
        p4.start();
    }

    public static class Philosopher extends Thread {
        String name;
        int index;
        ChopStick left, right;

        public Philosopher(String name, int index, ChopStick left, ChopStick right) {
            this.name = name;
            this.index = index;
            this.left = left;
            this.right = right;
        }

        @Override
        public void run() {
            if (index % 2 == 0) {
                synchronized (left) {
                    try {
                        Thread.sleep(500 + 100L * index);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    synchronized (right) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println(index + " 号哲学家已经吃完");
                    }
                }
            } else {
                synchronized (right) {
                    try {
                        Thread.sleep(500 + 100L * index);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    synchronized (left) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println(index + " 号哲学家已经吃完");
                    }
                }
            }
        }
    }
}
