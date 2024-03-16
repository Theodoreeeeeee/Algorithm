package tcp_learning.upload;

import cn.hutool.core.date.LocalDateTimeUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server {
    public static void main(String[] args) throws IOException {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                3,
                16,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        ServerSocket serverSocket = new ServerSocket(12345);
        while (true) {
            Socket socket = serverSocket.accept();
//            new Thread(new MyRunnable(socket)).start();
//            poolExecutor.submit(new MyRunnable(socket));
            poolExecutor.submit(new MyRunnable(socket));
        }
//        serverSocket.close();
    }
}
