package ch6._7;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;

/**
 * @author cheng
 *         2018/7/21 16:00
 */
public class OutBoundHandlerB extends ChannelOutboundHandlerAdapter {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println("OutBoundHandlerB.exceptionCaught()");

        ctx.fireExceptionCaught(cause);
    }
}
