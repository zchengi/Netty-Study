package ch12._02;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import static ch12._02.Constant.BEGIN_PORT;
import static ch12._02.Constant.N_PORT;

/**
 * @author cheng
 *         2018/7/28 16:05
 */
public class Server {
    public static void main(String[] args) {

        new Server().start(BEGIN_PORT, N_PORT);
    }

    private void start(int beginPort, int nPort) {

        System.out.println("Server starting...");

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.SO_REUSEADDR, true)
                .childHandler(new ConnectionCountHandler());

        for (int i = 0; i < nPort; i++) {
            int port = beginPort + i;
            bootstrap.bind(port).addListener(
                    (ChannelFutureListener) future -> System.out.println("bind success in port: " + port));
        }

        System.out.println("Server started.");
    }
}
