package ch6._6;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import java.util.concurrent.TimeUnit;

/**
 * @author cheng
 *         2018/7/21 16:00
 */
public class OutBoundHandlerB extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
        System.out.println("OutBoundHandlerB：" + msg);
        ctx.write(msg, promise);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        ctx.executor().schedule(() -> {
            ctx.channel().write("Hello World");
            // ctx.write("hello world");
        }, 3, TimeUnit.SECONDS);
    }
}
