package ch3;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.AttributeKey;

/**
 * @author cheng
 *         2018/7/16 14:42
 */
public class Server {
    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            // 配置基本线程
            b.group(bossGroup, workerGroup)
                    // 设置服务端 socket chain
                    .channel(NioServerSocketChannel.class)
                    // 设置连接的TCP 属性
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    // 连接的基本属性
                    .childAttr(AttributeKey.newInstance("childAttr"), "childAttrValue")
                    // 服务端启动过程中的逻辑
                    .handler(new ServerHandler())
                    // 新连接的处理逻辑
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
//                            ch.pipeline().addLast(new AuthHandler());
                            // ...
                        }
                    });

            // 创建服务端
            ChannelFuture f = b.bind(8888).sync();

            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
