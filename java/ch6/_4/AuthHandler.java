package ch6._4;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 权限验证处理器
 *
 * @author cheng
 *         2018/7/20 15:33
 */
public class AuthHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // super.channelRead(ctx, msg);
        // 继承 SimpleChannelInboundHandler 释放 ByteBuf 类型的 msg
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf password) {

        // 通过 pass 则删除 ChannelHandler，否则保留
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
