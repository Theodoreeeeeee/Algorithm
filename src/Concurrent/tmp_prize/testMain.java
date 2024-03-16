package Concurrent.tmp_prize;

public class testMain {
    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            Prize prize = new Prize();
            prize.setName("抽奖箱" + (i + 1));
            prize.start();
        }
    }
}
