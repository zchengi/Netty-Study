package ch2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器
 *
 * @author cheng
 *         2018/7/16 12:16
 */
public class Server {

    private ServerSocket serverSocket;

    public Server(int port) {
        try {
            // 创建服务端 ServerSocket 绑定 port
            this.serverSocket = new ServerSocket(port);
            System.out.println("服务器启动成功，端口：" + port);
        } catch (IOException e) {
            System.out.println("服务器启动失败");
        }
    }

    public void start() {
        // 创建端口监听线程避免阻塞 ServerBoot 线程
        new Thread(this::doStart).start();
    }

    /**
     * 接收客户端连接
     */
    private void doStart() {
        while (true) {
            try {
                // NioEventLoop 的 select 操作
                Socket client = serverSocket.accept();
                new clientHandler(client).start();
            } catch (IOException e) {
                System.out.println("服务器异常");
            }
        }
    }
}
