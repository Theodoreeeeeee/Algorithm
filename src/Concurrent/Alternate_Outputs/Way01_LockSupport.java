package Concurrent.Alternate_Outputs;

import java.util.concurrent.locks.LockSupport;
// LockSupport park 当前线程阻塞
// unpark(Thread t)

/**
 * 交替输出问题
 * 用两个线程，一个输出字母， 一个输出数字，交替输出1A2B3C...26Z
 */
public class Way01_LockSupport {
    static Thread t1 = null, t2 = null;

    public static void main(String[] args) throws Exception {
        t1 = new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                System.out.print(i + 1);
                LockSupport.unpark(t2); // 叫醒T2
                LockSupport.park(); // T1阻塞，当前线程阻塞
            }
        }, "t1");
        t2 = new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                LockSupport.park(); // t2挂起
                System.out.print((char) ('A' + i));
                LockSupport.unpark(t1); // 叫醒T1
            }
        }, "t2");
        t1.start();
        t2.start();
        Object o = new Object();
//        synchronized (o){
            o.wait();
//        }
    }
}
