package Concurrent.Alternate_Outputs;

import java.util.concurrent.CountDownLatch;

/**
 * 交替输出问题
 * 用两个线程，一个输出字母， 一个输出数字，交替输出1A2B3C...26Z
 */
public class Way02_sync_wait_notify {
    private static final CountDownLatch latch = new CountDownLatch(1); // 控制线程首次执行的顺序

    public static void main(String[] args) {
        final Object o = new Object();

        new Thread(() -> {
            try {
                latch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (o) {
                for (int i = 0; i < 26; i++) {
                    System.out.print(i + 1);
                    try {
                        o.notify(); // 叫醒等待队列中某个线程
                        o.wait(); // 让出锁, !!! 不可以先调用o.wait() 如果wait() 先，当前线程直接进入等待池，两个线程没有被唤醒的，都在傻等
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                o.notify(); // 必须，否则无法停止程序
            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (o) {
                for (int i = 0; i < 26; i++) {
                    System.out.print((char) ('A' + i));
                    latch.countDown();
                    try {
                        o.notify(); // 叫醒等待队列中某个线程
                        o.wait(); // 让出锁
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                o.notify(); // 必须，否则无法停止程序
            }
        }, "t2").start();
    }
}
