package ch6._7;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;

/**
 * @author cheng
 *         2018/7/21 16:00
 */
public class OutBoundHandlerC extends ChannelOutboundHandlerAdapter {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println("OutBoundHandlerC.exceptionCaught()");

        ctx.fireExceptionCaught(cause);
    }
}
