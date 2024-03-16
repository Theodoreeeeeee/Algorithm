package Concurrent.tmp_hongbao;

public class mainTest {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            HongBao hongbao = new HongBao();
            hongbao.setName("第" + (i + 1) + "个人");
            hongbao.start();
        }
    }
}
