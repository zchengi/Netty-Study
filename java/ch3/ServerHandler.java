package ch3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * 服务器启动过程中打印对应的状态信息
 *
 * @author cheng
 *         2018/7/16 14:42
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("channelActive");
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        System.out.println("channelRegistered");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        System.out.println("handlerAdded");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        new Thread(()->{
            // 耗时的操作
            String result = loadFromDB();

            ctx.channel().writeAndFlush(result);
            ctx.executor().schedule(() -> {
                // ...
            }, 1, TimeUnit.SECONDS);
        }).start();
    }

    private String loadFromDB() {
        return "hello world";
    }
}
