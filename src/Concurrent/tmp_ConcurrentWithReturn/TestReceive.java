package Concurrent.tmp_ConcurrentWithReturn;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TestReceive {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket(10086);
        byte[] bytes = new byte[1024];
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length);

        while (true) {
            ds.receive(dp);
            byte[] data = dp.getData();
            int len = dp.getLength();
            String hostAddress = dp.getAddress().getHostAddress();
            String hostName = dp.getAddress().getHostName();

            System.out.println("ip为：" + hostAddress + ", 主机名为： " + hostName + "的人，发送了数据： " + new String(data, 0, len));
        }
    }
}
