package Concurrent.tmp_ConcurrentWithReturn;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class TestSend {
    public static void main(String[] args) throws IOException {
        // "192.168.31.1"
        DatagramSocket ds = new DatagramSocket();

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入您要说的话: ");
            String s = sc.nextLine();
            if ("886".equals(s)) {
                break;
            }
            byte[] bytes = s.getBytes();
            InetAddress address = InetAddress.getByName("127.0.0.1");
            int port = 10086;
            DatagramPacket dp = new DatagramPacket(bytes, bytes.length, address, port);
            ds.send(dp);
        }

        ds.close();
    }
}
