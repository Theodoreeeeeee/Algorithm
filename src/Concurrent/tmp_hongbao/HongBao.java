package Concurrent.tmp_hongbao;

import java.util.Random;

public class HongBao extends Thread {
    static int money = 100, count = 3;

    public void run() {

        synchronized (HongBao.class) {
            if (money <= 0 || count <= 0) {
                System.out.println(getName() + "没抢到");
            } else {
                if (count == 1) {
                    System.out.println(getName() + "抢到了" + money + "元");
                    count = 0;
                    money = 0;
                } else {
                    Random random = new Random();
                    double bound = money - (count - 1) * 0.01;
                    double rmb = random.nextDouble(bound);
                    if (rmb < 0.01) rmb = 0.01;
                    System.out.println(getName() + "抢到了" + rmb + "元");
                    money -= rmb;
                    count--;
                }
            }
        }
    }
}
