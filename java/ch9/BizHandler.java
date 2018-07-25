package ch9;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author cheng
 *         2018/7/25 12:15
 */
public class BizHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // ...
        User user = new User(21, "zhang");

        ctx.channel().writeAndFlush(user);
    }
}
