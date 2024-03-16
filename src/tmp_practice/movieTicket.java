package tmp_practice;

import java.util.ArrayList;


public class movieTicket {

    public static void main(String[] args) {
        MyThread myThread1 = new MyThread(), myThread2 = new MyThread();
        myThread1.start();
        myThread2.start();
    }
}

class MyThread extends Thread {
    static int count = 0;
    static Object lock = new Object();

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                if (count >= 1000) {
                    break;
                } else {
                    System.out.println(getName() + "卖出了第" + ++count + "张票");
                }
            }
        }
    }
}
