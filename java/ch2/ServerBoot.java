package ch2;

/**
 * 服务端启动程序
 *
 * @author cheng
 *         2018/7/16 12:28
 */
public class ServerBoot {

    private static final int PORT = 8000;

    public static void main(String[] args) {
        Server server = new Server(PORT);
        server.start();
    }
}
