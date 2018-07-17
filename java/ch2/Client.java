package ch2;

import java.io.IOException;
import java.net.Socket;

/**
 * 客户端
 *
 * @author cheng
 *         2018/7/16 12:23
 */
public class Client {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8000;
    private static final int SLEEP_TIME = 5000;

    public static void main(String[] args) throws IOException {

        // 创建客户端 Socket 绑定 HOST 和 PORT
        final Socket socket = new Socket(HOST, PORT);

        new Thread(() -> {
            System.out.println("客户端启动成功!");
            // NioEventLoop 的 run() 方法 while(true) 循环
            while (true) {
                try {
                    String message = "hello world";
                    System.out.println("客户端发送数据：" + message);
                    // NioEventLoop 的 select 操作
                    socket.getOutputStream().write(message.getBytes());
                } catch (Exception e) {
                    System.out.println("写数据出错!");
                }
                sleep();
            }
        }).start();
    }

    private static void sleep() {
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
