package Concurrent.Alternate_Outputs;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Way03_lock_condition {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < 26; i++) {
                    System.out.print(i + 1);
                    condition.signal(); // notify()
                    condition.await(); // wait()
                }
                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < 26; i++) {
                    System.out.print((char) ('A' + i));
                    condition.signal(); // notify()
                    condition.await(); // wait()
                }
                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }
}
