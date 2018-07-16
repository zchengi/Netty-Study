package ch3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author cheng
 *         2018/7/16 14:53
 */
public class AuthHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // ...
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf password) {

        if (pass(password)) {
            ctx.pipeline().remove(this);
        } else {
            ctx.close();
        }
    }

    private boolean pass(ByteBuf password) {
        return false;
    }
}
