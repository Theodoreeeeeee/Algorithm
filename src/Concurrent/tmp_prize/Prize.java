package Concurrent.tmp_prize;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Prize extends Thread {


    static Set<Integer> prizes = new HashSet<>();

    static {
        Collections.addAll(prizes, 10, 5, 20, 50, 100, 200, 500, 800, 2, 80, 300, 700);
    }


    @Override
    public void run() {
        while (true) {
            synchronized (Prize.class) {
                if (prizes.isEmpty()) {
                    break;
                } else {
                    int key = 0;
                    for (int x : prizes) {
                        key = x;
                        break;
                    }
                    System.out.println(getName() + "又产生了一个" + key + "大奖");
                    prizes.remove(key);
                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
