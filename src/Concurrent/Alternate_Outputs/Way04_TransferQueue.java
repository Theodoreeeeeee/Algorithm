package Concurrent.Alternate_Outputs;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class Way04_TransferQueue {

    public static void main(String[] args) {
        TransferQueue<String> queue = new LinkedTransferQueue<>();
        new Thread(() -> {
            try {
                for (int i = 0; i < 26; i++) {
                    queue.transfer(String.valueOf(i + 1));
                    System.out.print(queue.take());
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }, "t1").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 26; i++) {
                    System.out.print(queue.take());
                    queue.transfer(String.valueOf((char) ('A' + i)));
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "t2").start();
    }
}
