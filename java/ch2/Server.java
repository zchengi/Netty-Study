package ch2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author cheng
 *         2018/7/16 12:16
 */
public class Server {

    private ServerSocket serverSocket;

    public Server(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
            System.out.println("服务器启动成功，端口：" + port);
        } catch (IOException e) {
            System.out.println("服务器启动失败");
        }
    }

    public void start() {
        new Thread(this::doStart).start();

    }

    private void doStart() {
        while (true) {
            try {
                // 阻塞，直到客户端接入
                Socket client = serverSocket.accept();
                new clientHandler(client).start();
            } catch (IOException e) {
                System.out.println("服务器异常");
            }
        }
    }
}
