package ch12._02;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.ExecutionException;

import static ch12._02.Constant.BEGIN_PORT;
import static ch12._02.Constant.N_PORT;

/**
 * @author cheng
 *         2018/7/28 16:09
 */
public class Client {

    private static final String SERVER_HOST = "127.0.0.1";

    public static void main(String[] args) {
        new Client().start(BEGIN_PORT, N_PORT);
    }

    private void start(int beginPort, int nPort) {

        System.out.println("Client starting...");
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_REUSEADDR, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                    }
                });

        int index = 0;
        int port;
        while (!Thread.interrupted()) {
            port = beginPort + index;
            try {
                ChannelFuture channelFuture = bootstrap.connect(SERVER_HOST, port);
                channelFuture.addListener((ChannelFutureListener) future -> {
                    if (!future.isSuccess()) {
                        System.out.println("Connect failed, exit.");
                        System.exit(0);
                    }
                });
                channelFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            if (++index == nPort) {
                index = 0;
            }
        }
    }
}
